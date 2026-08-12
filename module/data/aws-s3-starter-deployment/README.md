# aws-s3-starter-deployment

> PoC de consumo y validación operativa de `aws-s3-starter` (Fase 3
> del plan de desarrollo Saywa). App Quarkus REST reactiva (RESTEasy Reactive
> + Mutiny, Java 21) que inyecta `S3StorageService` por CDI y expone sus
> operaciones vía HTTP (base `/shrd/s3`), probadas contra LocalStack en
> Docker Compose.

Este proyecto **no reimplementa nada del starter**: solo lo consume como
dependencia Maven y traduce HTTP &lt;-&gt; `S3StorageService`. Toda la lógica
de negocio (validaciones, auditoría, mapeo de errores del SDK) vive en
`aws-s3-starter`.

`bucketName` es **obligatorio en cada llamada** — no hay bucket implícito
(ver [Arquitectura](#arquitectura)). El upload tiene 2 modos: en memoria
(multipart) y por path (el servidor lee un archivo de su propio
`deployment.s3.upload-base-dir`, con protección contra path-traversal).
La app también expone `/q/health` (liveness/readiness) y reintenta
automáticamente (`@Retry`) los fallos de conexión transitorios contra S3.

## Quickstart

### Paso 0 — inicializar la plantilla (una sola vez por copia)

Este repo es un template parametrizable (igual que `aws-s3-starter`): viene
con `{{placeholders}}` en `pom.xml`, `docker-compose.yml`, etc., bloqueado
por `.template-guard` hasta que corres:

```bash
./template-init.sh
```

Te pregunta artifact id del deployment, starter a consumir (y su versión) y
nombre del bucket — Enter en todas usa los valores de `aws-s3-starter`.
Detalle completo en [`TEMPLATE.md`](TEMPLATE.md).

### Opción A — un solo comando (recomendado)

```bash
./scripts/bootstrap.sh /ruta/al/repo/saywa
```

Instala `aws-s3-starter` en tu `.m2` si falta, levanta LocalStack, espera el
healthcheck, compila esta app y corre el test de integración end-to-end
contra LocalStack real. Requiere Docker y Maven/Java 21 en la máquina donde
lo corras (este scaffold se generó en un sandbox sin Docker; ejecútalo en tu
entorno local con Docker Hub).

### Opción B — paso a paso

```bash
# 1. Instalar el starter en tu .m2 local (una sola vez, desde el repo del starter)
cd <ruta-al-repo-saywa>/module/data/aws-s3-starter
mvn install -DskipTests
cd -

# 2. Levantar LocalStack (crea el bucket automáticamente)
docker compose up -d
docker compose logs -f localstack   # esperar "Ready."

# 3. Verificar entorno (Java/Maven/Docker/starter en .m2/LocalStack)
./init.sh

# 4. Levantar la app en modo dev
mvn quarkus:dev
```

La app queda en `http://localhost:8080`.

## Probar los endpoints con curl

`bucketName` es obligatorio en los 9 — si falta, 400 con
`SaywaErrorResponse` (`ConstraintViolationException`).

```bash
BASE=http://localhost:8080/shrd/s3/bucket
BUCKET=saywa-s3-deployment-poc

# 1. upload en memoria (multipart)
curl -s -F "file=@README.md;type=text/markdown" -F "bucketName=$BUCKET" -F "objectKey=docs/readme.md" "$BASE"

# 2. upload por path (el servidor lee el archivo de su propio deployment.s3.upload-base-dir)
curl -s -X POST -H "Content-Type: application/json" \
  -d "{\"bucketName\":\"$BUCKET\",\"objectKey\":\"docs/sample.txt\",\"objectFileToLoad\":\"sample.txt\"}" \
  "$BASE/path"

# 3. list
curl -s "$BASE?bucketName=$BUCKET&prefix=docs/"

# 4. download
curl -s "$BASE/docs/readme.md?bucketName=$BUCKET" -o /tmp/readme-descargado.md

# 5. exists
curl -s "$BASE/docs/readme.md/exists?bucketName=$BUCKET"

# 6. copy
curl -s -X POST "$BASE/docs/readme.md/copy?bucketName=$BUCKET&destinationKey=docs/readme-copia.md"

# 7. move
curl -s -X POST "$BASE/docs/readme-copia.md/move?bucketName=$BUCKET&destinationKey=docs/readme-movido.md"

# 8. presigned
curl -s "$BASE/docs/readme.md/presigned-url?bucketName=$BUCKET&ttlSeconds=300"

# 9. delete
curl -s -X DELETE -o /dev/null -w "%{http_code}\n" "$BASE/docs/readme-movido.md?bucketName=$BUCKET"

# health check
curl -s http://localhost:8080/q/health
```

Más ejemplos y el checklist de verificación completo en
[`docs/verification.md`](docs/verification.md).

## Test de integración automatizado (sin mocks, contra LocalStack real)

```bash
docker compose up -d
mvn verify -Pintegration-tests
```

Cubre el ciclo completo: upload en memoria → upload por path (+ intento de
traversal rechazado) → exists → list → download → 404 esperado → copy →
move → presigned → `bucketName` faltante rechazado → bucket no-default
aislado del bucket por defecto → delete (`src/test/java/integration/`).

Sin Maven, el mismo ciclo se puede correr por curl con
[`scripts/smoke-test.sh`](scripts/smoke-test.sh) (ver
[`docs/verification.md`](docs/verification.md#3-smoke-test-automatizado-por-curl-sin-maven)).

## OpenAPI / Postman

Si no tienes cuenta en el [LocalStack Web App](https://app.localstack.cloud)
(requiere `LOCALSTACK_AUTH_TOKEN`) para navegar el bucket con una UI, la app
expone su propia documentación OpenAPI de los 9 endpoints:

- `http://localhost:8080/q/openapi` — documento OpenAPI (JSON), importable
  directo en Postman (**File → Import**, pegando la URL o el archivo).
- `http://localhost:8080/q/swagger-ui/` — UI interactiva para probar los
  endpoints con datos de prueba desde el navegador, sin Postman.
- `http://localhost:8080/q/health` — liveness/readiness (`quarkus-smallrye-health`).

Detalle en [`docs/verification.md`](docs/verification.md#4-openapi--postman--alternativa-al-localstack-web-app).

## Arquitectura

```
Cliente HTTP
    │
    ▼
S3Controller (RESTEasy Reactive, /shrd/s3, Uni<T>)      ──┐
    │ @BeanParam validado (S3ParameterRequest /            │
    │ S3TransferParameterRequest) o body JSON (S3BodyRequest)
    ▼                                                       │
S3Facade → construye model/api/s3/S3BodyResponse            │  este proyecto
    │                                                        │
    ▼                                                        │
S3Service (@Retry en fallos de conexión transitorios)        │
    │ @Inject S3StorageService                                │
    ▼                                                       ──┘
aws-s3-starter (JAR, dependencia Maven)
    │ S3AsyncClient / S3Presigner (beans CDI de quarkus-amazon-s3)
    ▼
LocalStack (Docker, :4566)
```

`S3ExceptionMappers` (mapper/) intercepta en paralelo cualquier excepción
que escape de esa cadena y la traduce a `SaywaErrorResponse` — incluida
`ConstraintViolationException` (Bean Validation) para los campos
obligatorios sin mandar. Usa `@ServerExceptionMapper` de RESTEasy Reactive
(no `jakarta.ws.rs.ext.ExceptionMapper`) para que se resuelva siempre el
mapper más específico sin bloquear el event loop:

| Excepción | HTTP | Origen |
|---|---|---|
| `StorageObjectNotFoundException` | 404 | Clave inexistente en el bucket (jerarquía compartida del módulo `core`, ver el README de `aws-s3-starter`). |
| `StorageAccessDeniedException` | 403 | S3/LocalStack rechaza la operación por permisos. |
| `StorageConfigurationException` | 400 | Límite de tamaño excedido, o un campo requerido (`bucketName`/`objectKey`) llega nulo o en blanco hasta el starter. |
| `StorageConnectionException` | 503 | Fallo de conectividad con S3/LocalStack (timeout, conexión rechazada). |
| `StorageException` (genérico) | 500 | Catch-all: cualquier otra excepción de la jerarquía no cubierta por un mapper más específico. |
| `IllegalArgumentException` | 400 | Validación defensiva que no pasó por el starter (por ejemplo, argumentos inválidos detectados en este módulo). |
| `ConstraintViolationException` | 400 | Bean Validation (`@Valid`/`@NotBlank`) sobre los DTOs de request o los parámetros `@BeanParam`/`@QueryParam` del controller — típicamente `bucketName`/`objectKey` faltantes. |

En todos los casos el mensaje crudo de excepciones de infraestructura (por
ejemplo, credenciales) nunca llega al cuerpo de la respuesta.

Detalle completo, incluida la diferencia entre el `docker-compose.yml`
explícito de este repo y los Dev Services de `quarkus-amazon-s3`, en
[`docs/architecture.md`](docs/architecture.md).

## LocalStack: dos formas de levantarlo

1. **`docker-compose.yml`** (la que usa este proyecto por defecto):
   reproducible, igual en local y CI, con bucket creado por
   `localstack-init/01-create-bucket.sh`.
2. **Quarkus Dev Services** (`quarkus.s3.devservices.enabled=true`):
   `quarkus-amazon-s3` levanta y destruye su propio contenedor LocalStack
   automáticamente al entrar/salir de `mvn quarkus:dev` o los tests,
   mediante un `@BuildStep` de su módulo `-deployment` que produce un
   `RunTimeConfigurationDefaultBuildItem` cuando `LaunchMode.current()` es
   dev o test — el mismo patrón de procesador de build documentado en la
   [guía de Quarkus REST Client](https://es.quarkus.io/guides/rest-client).
   Está deshabilitado por defecto en este repo para no chocar con el
   contenedor manual del compose; se puede activar en `%dev` si prefieres
   no mantener `docker-compose.yml` corriendo aparte.

## Estructura del harness

Este repo se generó y se sigue evolucionando con el mismo patrón
implementer/reviewer usado en `aws-s3-starter`:

| Archivo | Propósito |
|---|---|
| `feature_list.json` | Cola de trabajo con acceptance criteria |
| `CLAUDE.md` | Protocolo del orquestador para Claude Code |
| `AGENTS.md` | Mapa de navegación para cualquier agente |
| `CHECKPOINTS.md` | Criterios objetivos de "terminado" |
| `.claude/agents/` | Subagentes `leader` / `implementer` / `reviewer` |
| `docs/architecture.md`, `docs/conventions.md`, `docs/verification.md` | Contexto técnico |

Para continuar el trabajo (p.ej. la feature 5, ya implementada, o features
nuevas que agregues a `feature_list.json`), corre `claude` dentro de esta
carpeta — leerá `CLAUDE.md` y seguirá el protocolo automáticamente.

## Docker

Ver [`DOCKER.md`](DOCKER.md) para levantar la app también en contenedor
(`docker compose --profile app up --build`) y troubleshooting.

## Troubleshooting rápido

- **`mvn compile` no resuelve `aws-s3-starter`** → no está instalado en
  `.m2`, ver Quickstart paso 1.
- **`NoSuchBucket`** → LocalStack no corrió el script de init; `docker
  compose down -v && docker compose up -d`.
- **Timeouts de conexión** → `docker compose ps`, esperar `healthy`.
