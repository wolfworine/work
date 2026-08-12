# Convenciones de código — aws-s3-starter-deployment

## Paquetes

```
com.saywa.framework.deployment.s3
├── expose/web/   → S3Controller (RESTEasy Reactive, @Path("/shrd/s3"))
├── facade/       → S3Facade(Impl) — orquesta, mapea a model/api/s3/
├── service/      → S3Service(Impl) — el único punto que importa
│                    com.saywa.framework.data.s3.* del starter;
│                    S3UploadPathResolver (guard de path-traversal)
├── config/       → @ConfigMapping propio de este módulo (no del starter)
├── model/api/s3/ → records de request/response JSON, y model/api/s3/error/
│                    para el DTO de error
└── mapper/       → S3ExceptionMappers (StorageException/ConstraintViolationException → HTTP)
```

`service/` de este módulo **no duplica** el `service/` del starter — es
una capa de orquestación deployment-específica (upload por path,
resiliencia) que a su vez llama a `S3StorageService` del starter, nunca
reimplementa lo que el starter ya hace (validación de tamaño, auditoría,
mapeo SDK→dominio).

## Reactividad

- Todo método de `S3Controller`/`S3Facade`/`S3Service` devuelve `Uni<T>`.
- Encadenar con `.map(...)` / `.chain(...)` / `.onItem()...` / `.onFailure()...`.
- Prohibido: `.await().indefinitely()`, `.join()`, `.get()`, `Thread.sleep()`
  en `expose/`, `facade/`, `service/` o `mapper/`.
- El único I/O bloqueante de este módulo (leer un archivo local para el
  upload por path, y leer el multipart temporal del upload en memoria)
  corre explícitamente en `Infrastructure.getDefaultWorkerPool()`, nunca
  en el event-loop.
- Los `@ServerExceptionMapper` son síncronos por contrato de RESTEasy
  Reactive — eso está permitido, no bloquean el event-loop del request en
  curso.

## DTOs (`model/api/s3/`)

- Records inmutables, igual que `domain/` del starter — **excepto**
  `S3ParameterRequest`, `S3TransferParameterRequest` y `S3UploadFormRequest`, que
  son clases con campos públicos porque RESTEasy Reactive necesita campos
  mutables para bindear `@BeanParam`/`@RestForm` (no components de record).
- **Un solo tipo de respuesta, `S3BodyResponse`**, para las 9 operaciones
  — construido siempre vía sus 3 sobrecargas de `from(...)`, nunca por
  factory methods con nombre distinto por operación (eso reintroduciría
  DTOs separados con más pasos).
- **Un `@BeanParam` por forma de request compartida real**, no uno por
  endpoint: `S3ParameterRequest` (bucketName+objectKey+ttlSeconds) cubre
  `download`/`exists`/`presigned`; `S3TransferParameterRequest`
  (bucketName+objectKey+destinationKey) cubre `copy`/`move`. `list`
  (bucketName+prefix, sin objectKey) y `delete` (bucketName+objectKey, ya
  cubierto por `S3ParameterRequest`) no fuerzan un bean compartido cuando
  su forma no calza — ver el Javadoc de cada clase antes de agregarle un
  campo nuevo.
- El contenido binario (`upload en memoria`) usa `multipart/form-data`; el
  upload por path usa JSON con la ruta del archivo (`objectFileToLoad`),
  nunca el contenido en base64 — evita el overhead de codificación y
  mantiene el límite de tamaño coherente con
  `compartamos.storage.s3.max-upload-size` del starter.

## Validación

- Jakarta Bean Validation (`quarkus-hibernate-validator`) en el borde
  HTTP: `@Valid` sobre records/clases de body/bean-param, `@NotBlank`
  directo sobre los `@QueryParam`/`@RestPath` sueltos que no forman parte
  de un bean compartido.
- `ConstraintViolationException` se mapea a 400 en `S3ExceptionMappers` —
  es la primera línea de defensa. La auto-validación de `S3ObjectRequest`
  en el starter (que lanza `IllegalArgumentException`, mapeado también a
  400) es la segunda línea, por si algo llega a `S3StorageService` sin
  pasar por este módulo.

## Naming

- Clases: `S3Controller`, `S3Facade`/`S3FacadeImpl`, `S3Service`/`S3ServiceImpl`,
  `S3ExceptionMappers`, `SaywaErrorResponse`.
- Endpoints bajo `/shrd/s3/bucket`, siguiendo el vocabulario del dominio S3
  (`objectKey`/`bucketName` en path/query, nunca `id` ni `filename`).

## Config

- Prefijo propio del starter (`compartamos.storage.s3.*`) y de la extensión
  (`quarkus.s3.*`) para todo lo que el starter ya configura — este módulo
  no lo redefine.
- Config propia de este módulo (hoy solo `deployment.s3.upload-base-dir`)
  vive en `config/DeploymentS3Configuration` (`@ConfigMapping`), **no**
  como `@ConfigProperty` suelto — mismo patrón que `S3Configuration` del
  starter.
- Perfiles Quarkus (`%dev`, `%test`, `%prod`) para separar LocalStack de un
  eventual S3 real — nunca credenciales reales en este repo.

## Tests

- `src/test/java/unit/` — Mockito puro, sin `@QuarkusTest`, para la lógica
  propia de `S3Facade`/`S3Service`/`S3ExceptionMappers`/`S3UploadPathResolver`
  (mapeo de DTOs, resolución de paths, códigos HTTP por excepción). Mismo
  patrón Gherkin (`givenX_whenY_thenZ`) que el starter.
- `src/test/java/integration/` — `@QuarkusTest` contra LocalStack real
  (perfil Maven `integration-tests`), sin mocks. Valida el cableado real:
  HTTP → CDI → starter → SDK → LocalStack, incluyendo casos que un mock no
  puede probar honestamente (bucket no-default realmente aislado, 404 real
  de S3).
