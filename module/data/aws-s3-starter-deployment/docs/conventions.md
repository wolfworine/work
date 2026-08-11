# Convenciones de código — aws-s3-starter-deployment

## Paquetes

```
com.saywa.framework.deployment.s3
├── resource/    → recursos JAX-RS / RESTEasy Reactive (@Path)
├── dto/         → records de request/response JSON
├── mapper/      → conversión dto <-> domain del starter, y ExceptionMapper JAX-RS
```

No crear paquetes `service/`, `factory/`, `audit/` aquí — esa lógica ya
existe en el starter y NO se duplica.

## Reactividad

- Todo método de `S3Resource` devuelve `Uni<T>` o `Uni<RestResponse<T>>`.
- Encadenar con `.map(...)` / `.chain(...)` / `.onFailure(...).transform(...)`.
- Prohibido: `.await().indefinitely()`, `.join()`, `.get()`, `Thread.sleep()`
  en el código de `resource/` o `mapper/`.
- Los `@ServerExceptionMapper` (o `ExceptionMapper` JAX-RS) son síncronos por
  contrato de RESTEasy Reactive — eso está permitido, no bloquean el
  event-loop del request en curso.

## DTOs

- Records inmutables, igual que `domain/` del starter.
- `S3ObjectResponseDto(String objectKey, String bucketName)` — espejo 1:1 de
  `S3ObjectResponse`, se puede mapear directo.
- `S3ObjectSummaryDto(String objectKey, long size, Instant lastModified)` —
  espejo 1:1 de `S3ObjectSummary`.
- El contenido binario (`upload`/`download`) usa `multipart/form-data` o el
  cuerpo crudo (`application/octet-stream`) — **no** JSON con base64, para
  evitar el overhead de codificación y mantener el límite de tamaño
  coherente con `compartamos.storage.s3.max-upload-size` del starter.

## Naming

- Clases: `S3Resource`, `S3ObjectResponseDto`, `S3RestExceptionMapper`.
- Endpoints bajo `/s3/objects`, siguiendo el vocabulario del dominio S3
  (`objectKey` en path/query, nunca `id` ni `filename`).

## Config

- Prefijo propio del starter (`compartamos.storage.s3.*`) y de la extensión
  (`quarkus.s3.*`) — este proyecto **no** define su propio `@ConfigMapping`
  para nada que ya configure el starter.
- Perfiles Quarkus (`%dev`, `%test`, `%prod`) para separar LocalStack de un
  eventual S3 real — nunca credenciales reales en este repo.

## Tests

- `src/test/java/integration/` — `@QuarkusTest` contra LocalStack real
  (perfil Maven `integration-tests`), sin mocks. Los mocks del contrato
  `S3StorageService` ya se cubrieron exhaustivamente en el starter base;
  aquí se valida el cableado real: HTTP → CDI → SDK → LocalStack.
