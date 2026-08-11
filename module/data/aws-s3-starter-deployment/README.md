# aws-s3-starter-deployment

> PoC de consumo y validación operativa de `aws-s3-starter` (Fase 3
> del plan de desarrollo Saywa). App Quarkus REST reactiva (RESTEasy Reactive
> + Mutiny, Java 21) que inyecta `S3StorageService` por CDI y expone sus 8
> operaciones vía HTTP, probadas contra LocalStack en Docker Compose.

Este proyecto **no reimplementa nada del starter**: solo lo consume como
dependencia Maven y traduce HTTP &lt;-&gt; `S3StorageService`. Toda la lógica
de negocio (validaciones, auditoría, mapeo de errores del SDK) vive en
`aws-s3-starter`.

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

## Probar los 8 endpoints con curl

```bash
BASE=http://localhost:8080/s3/objects

# 1. upload
curl -s -F "file=@README.md;type=text/markdown" "$BASE?objectKey=docs/readme.md"

# 2. list
curl -s "$BASE?prefix=docs/"

# 3. download
curl -s "$BASE/docs/readme.md" -o /tmp/readme-descargado.md

# 4. exists
curl -s "$BASE/docs/readme.md/exists"

# 5. copy
curl -s -X POST "$BASE/docs/readme.md/copy?destinationKey=docs/readme-copia.md"

# 6. move
curl -s -X POST "$BASE/docs/readme-copia.md/move?destinationKey=docs/readme-movido.md"

# 7. presigned
curl -s "$BASE/docs/readme.md/presigned?ttlSeconds=300"

# 8. delete
curl -s -X DELETE -o /dev/null -w "%{http_code}\n" "$BASE/docs/readme-movido.md"
```

Más ejemplos y el checklist de verificación completo en
[`docs/verification.md`](docs/verification.md).

## Test de integración automatizado (sin mocks, contra LocalStack real)

```bash
docker compose up -d
mvn verify -Pintegration-tests
```

Cubre el ciclo completo: upload → exists → list → download → 404 esperado →
copy → move → presigned → delete (`src/test/java/integration/`).

Sin Maven, el mismo ciclo se puede correr por curl con
[`scripts/smoke-test.sh`](scripts/smoke-test.sh) (ver
[`docs/verification.md`](docs/verification.md#3-smoke-test-automatizado-por-curl-sin-maven)).

## OpenAPI / Postman

Si no tienes cuenta en el [LocalStack Web App](https://app.localstack.cloud)
(requiere `LOCALSTACK_AUTH_TOKEN`) para navegar el bucket con una UI, la app
expone su propia documentación OpenAPI de los 8 endpoints:

- `http://localhost:8080/q/openapi` — documento OpenAPI (JSON), importable
  directo en Postman (**File → Import**, pegando la URL o el archivo).
- `http://localhost:8080/q/swagger-ui/` — UI interactiva para probar los
  endpoints con datos de prueba desde el navegador, sin Postman.

Detalle en [`docs/verification.md`](docs/verification.md#4-openapi--postman--alternativa-al-localstack-web-app).

## Arquitectura

```
Cliente HTTP
    │
    ▼
S3Resource (RESTEasy Reactive, Uni<T>)  ──┐
S3ExceptionMappers (excepción → HTTP)     │  este proyecto
    │ @Inject S3StorageService            │
    ▼                                   ──┘
aws-s3-starter (JAR, dependencia Maven)
    │ S3AsyncClient / S3Presigner (beans CDI de quarkus-amazon-s3)
    ▼
LocalStack (Docker, :4566)
```

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
