# Arquitectura — aws-s3-starter-deployment

## Propósito

Este proyecto **no** es el starter. Es la Fase 3 del plan Saywa: una app
Quarkus REST reactiva que consume `aws-s3-starter` como dependencia Maven
para probar sus operaciones (base `/shrd/s3`) en un entorno controlado
(LocalStack), sin acoplar el starter base a ninguna pila de pruebas.

```
Cliente HTTP (curl / Postman / test de integración)
        │
        ▼
┌───────────────────────────────────────────────────────────────┐
│  aws-s3-starter-deployment (este proyecto)                     │
│  ├── expose/web/S3Controller     ← RESTEasy Reactive, Uni<T>,   │
│  │     bucketName/objectKey validados (Bean Validation:        │
│  │     @Valid @BeanParam / @Valid body / @NotBlank en params)  │
│  ├── facade/S3Facade(Impl)       ← orquesta, mapea a           │
│  │     model/api/s3/S3BodyResponse (único tipo de respuesta)   │
│  ├── service/S3Service(Impl)     ← @Retry en fallos de         │
│  │     conexión transitorios; el único punto que importa       │
│  │     com.saywa.framework.data.s3.* del starter                │
│  ├── service/S3UploadPathResolver ← guard de path-traversal    │
│  │     para el upload por path (deployment.s3.upload-base-dir) │
│  ├── model/api/s3/*               ← DTOs JSON (no exponen SDK) │
│  └── mapper/S3ExceptionMappers    ← StorageException/          │
│        ConstraintViolationException/IllegalArgumentException   │
│        → HTTP (SaywaErrorResponse)                             │
└──────────────────┬───────────────────────────────────────────┘
                    │ @Inject S3StorageService (Uni<T>, bucketName obligatorio)
┌───────────────────▼────────────────────────────┐
│  aws-s3-starter (JAR, dependencia Maven)        │
│  domain / service / config / audit /            │
│  exception / factory / mapper                   │
└───────────────────┬────────────────────────────┘
                    │ S3AsyncClient + S3Presigner (beans CDI, quarkus-amazon-s3)
                    ▼
             LocalStack (Docker, puerto 4566)
```

`bucketName` viaja explícito en cada llamada de principio a fin — no hay
bucket implícito en ningún punto de la cadena (ver `aws-s3-starter/README.md`
para la analogía con una conexión a base de datos).

## Dos formas de correr LocalStack

### 1. Docker Compose explícito (recomendado para este proyecto)

`docker-compose.yml` en la raíz levanta un contenedor `localstack` con
`SERVICES=s3`, healthcheck en `/_localstack/health` y un script de init
(`localstack-init/01-create-bucket.sh`, montado en
`/etc/localstack/init/ready.d/`) que crea el bucket configurado apenas
LocalStack está listo. Es reproducible, igual en local y en CI, y no
depende de que Quarkus esté en modo dev.

```bash
docker compose up -d
docker compose logs -f localstack   # ver "Ready."
```

### 2. Quarkus Dev Services (loop rápido, opcional)

`quarkus-amazon-s3` incluye su propio módulo `-deployment` con un
`@BuildStep` (`Processor`) que, cuando `LaunchMode.current().isDevMode()`
(o test) **y** `quarkus.s3.devservices.enabled=true`, produce un
`RunTimeConfigurationDefaultBuildItem` con el `endpoint-override` apuntando
a un contenedor LocalStack que la propia extensión levanta y destruye
automáticamente al arrancar/parar `quarkus:dev` o los tests — sin tocar
`docker-compose.yml`. Es el mismo patrón descrito en la guía de Quarkus REST
Client (procesador de build → `ConfigurationBuildItem`/`RunTimeConfig
DefaultBuildItem` según el `LaunchMode`): https://es.quarkus.io/guides/rest-client
documenta el patrón general de procesadores de build; el caso concreto de
Dev Services de S3 vive en el módulo `deployment` de
`quarkus-amazon-s3` (no en este repo).

Cuándo usar cada uno:

| Escenario | Usar |
|---|---|
| `mvn quarkus:dev` día a día, iterar rápido | Dev Services (`quarkus.s3.devservices.enabled=true`, sin `endpoint-override` manual) |
| `mvn verify -Pintegration-tests`, CI, reproducibilidad exacta | `docker-compose.yml` explícito |
| Depurar el propio LocalStack (logs, bucket persistente entre corridas) | `docker-compose.yml` explícito |

Por defecto este proyecto usa **la opción 1** (`docker-compose.yml`) porque
es la que pidió la validación operativa del plan Saywa (1.10/1.11 punto 6):
reproducible y sin acoplar el starter a Dev Services. La opción 2 queda
documentada como atajo opcional, deshabilitada por defecto
(`%dev.quarkus.s3.devservices.enabled=false` en `application.properties`
para no chocar con el contenedor manual del compose).

## Principios de diseño

- **Este módulo no reimplementa nada del starter.** `S3Service` es la
  única clase que inyecta `S3StorageService` y llama al starter; todo lo
  demás (Controller, Facade) solo conoce los DTOs propios de
  `model/api/s3/`.
- **`bucketName` es obligatorio en cada operación**, validado en el borde
  HTTP (Bean Validation) antes de llegar al starter — no hay fallback a
  un bucket configurado por defecto.
- **Un solo tipo de respuesta, `S3BodyResponse`**, para las 9 operaciones
  (campos no aplicables quedan `null` según la operación: `url` solo en
  presigned, `size`/`lastModified` solo en list). Reemplaza los DTOs de
  respuesta separados que tenía cada endpoint antes de esta iteración.
- **Dos modos de upload**: en memoria (`S3UploadFormRequest`, multipart) y por
  path (`S3BodyRequest`, JSON, el servidor lee el archivo de su propio
  `deployment.s3.upload-base-dir` vía `S3UploadPathResolver`, que rechaza
  cualquier intento de escapar ese directorio). Son DTOs hermanos —
  mismos campos `bucketName`/`objectKey`/`contentType`, solo difieren en
  cómo viaja el contenido — no se pueden unificar en una sola clase
  porque RESTEasy Reactive los bindea desde `@Consumes` incompatibles
  (multipart vs JSON).
- **Reactivo de punta a punta**: el recurso REST nunca bloquea el
  event-loop; todo el camino son `Uni<T>` encadenados con `.map`/`.chain`,
  y el único I/O bloqueante (leer el archivo en el upload por path) corre
  en el worker pool (`Infrastructure.getDefaultWorkerPool()`).
- **`@Retry` en `S3ServiceImpl`**: cada método reintenta hasta 3 veces
  (200ms de por medio) solo ante `StorageConnectionException` — un
  problema de red transitorio contra S3/LocalStack vale la pena
  reintentarlo; un 404/403/validación no (reintentar no cambia el
  resultado, solo demora la respuesta).
- **`S3ExceptionMappers`** (`@ServerExceptionMapper` de RESTEasy Reactive, no
  confundir con `StorageExceptionMapper`, el traductor interno SDK→dominio
  del starter) traduce la jerarquía de excepciones de almacenamiento
  compartida del módulo `core`, más las fallas de validación propias de
  este módulo, a códigos HTTP:
  `StorageObjectNotFoundException`→404, `StorageAccessDeniedException`→403,
  `StorageConfigurationException`→400, `StorageConnectionException`→503,
  `StorageException`→500, `ConstraintViolationException`/`IllegalArgumentException`→400.
- **`/q/health`** (`quarkus-smallrye-health`) expone liveness/readiness
  para correr esta app como servicio.
