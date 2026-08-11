# Convenciones de código — aws-s3-starter

## Naming

| Elemento | Convención | Ejemplo |
|---|---|---|
| Clases | PascalCase | `S3StorageServiceImpl` |
| Métodos y variables | camelCase | `objectKey`, `bucketName` |
| Constantes | UPPER_SNAKE_CASE | `DEFAULT_TTL` |
| Paquetes | lowercase | `com.saywa.framework.data.s3` |
| Records de dominio | Prefijo `S3Object*` o `S3*` | `S3ObjectRequest`, `S3AuditEvent` |
| Excepciones | Jerarquía compartida `com.saywa.framework.core.error.exceptions`, sufijo `Exception` | `StorageException`, `StorageObjectNotFoundException` |

> Las excepciones de este starter **no** se definen localmente en
> `data.s3.exception` — ese paquete solo contiene `StorageExceptionMapper`,
> que traduce excepciones del SDK de AWS a la jerarquía `Storage*Exception`
> del módulo `core`, compartida por todos los starters de datos del
> framework Saywa (junto con el contrato `ApiError`/`ErrorDetail`/`ErrorType`
> para respuestas HTTP consistentes).

## Javadoc (OBLIGATORIO)

Este starter se empaqueta como JAR. El consumidor **no ve el código fuente** —
el Javadoc es su única documentación en el IDE.

### Qué lleva Javadoc

- Toda interfaz pública
- Todo record en `domain/` (cada campo con `@param`)
- Toda excepción propia
- Todo método público de `config/`
- `StorageExceptionMapper.map()` explicando cada rama

### Formato

```java
/**
 * Solicitud de carga de un objeto a Amazon S3.
 *
 * @param objectKey   clave del objeto dentro del bucket; no puede ser nula ni vacía.
 * @param content     contenido binario del objeto; no puede ser nulo.
 * @param contentType tipo MIME del objeto, por ejemplo {@code "application/json"};
 *                    puede ser {@code null} para que el SDK lo detecte.
 * @param metadata    metadatos personalizados; nunca {@code null} (usar {@code Map.of()}).
 */
public record S3ObjectRequest(...) { }
```

### Qué NO es Javadoc aceptable

```java
/** TODO */                          // ❌
/** S3ObjectRequest */               // ❌ (repite el nombre)
/** Request object for S3 uploads */ // ✅ (mínimo aceptable)
```

## Tests unitarios

### Ubicación
```
src/test/java/unit/com/saywa/framework/data/s3/
├── config/   → S3ConfigurationValidatorTest
├── domain/   → tests de records y validaciones
├── service/  → S3StorageServiceImplTest (principal)
├── factory/  → S3RequestFactoryTest
├── mapper/   → S3ResponseMapperTest
├── audit/    → S3AuditLoggerTest
└── exception/→ S3ExceptionMapperTest
```

### Framework
- `@ExtendWith(MockitoExtension.class)` — **sin** `@QuarkusTest`
- Mocks: `@Mock S3AsyncClient s3Client; @Mock S3Presigner presigner;`
- Para simular el SDK: `CompletableFuture.completedFuture(response)` y `CompletableFuture.failedFuture(exception)`
- Para ejecutar `Uni<T>` en tests: `uni.await().atMost(Duration.ofSeconds(5))`

### Naming (Gherkin)

```java
@Test
void givenObjectKeyExists_whenExists_thenReturnTrue() { }

@Test
void givenObjectKeyNotFound_whenExists_thenReturnFalse() { }

@Test
void givenSdkThrowsAccessDenied_whenUpload_thenMapToStorageAccessDeniedException() { }
```

### Estructura AAA

```java
@Test
void givenValidRequest_whenUpload_thenAuditPutSuccess() {
    // Arrange
    var request = new S3ObjectRequest("file.json", content, "application/json", Map.of());
    when(s3Client.putObject(any(PutObjectRequest.class), any(AsyncRequestBody.class)))
        .thenReturn(CompletableFuture.completedFuture(PutObjectResponse.builder().build()));

    // Act
    var result = service.upload(request).await().atMost(Duration.ofSeconds(5));

    // Assert
    assertNotNull(result);
    assertEquals("file.json", result.objectKey());
    verify(auditLogger).audit(argThat(e -> e.operation() == S3Operation.PUT && e.successful()));
}
```

### Tests críticos (obligatorios)

| Feature | Test obligatorio |
|---|---|
| presigned | Verificar que el evento de auditoría NO contiene la URL completa |
| move | Verificar que si delete falla, el error se propaga pero copy NO se revierte |
| download | Verificar que falla con StorageConfigurationException si excede max-download-size |
| StorageExceptionMapper | Verificar `exception.getCause() != null` en cada rama de mapeo |

## Reactive patterns

```java
// ✅ Correcto — adaptar CompletableFuture del SDK a Uni
return Uni.createFrom().completionStage(() ->
    s3AsyncClient.putObject(request, body))
    .onItem().transform(response -> responseMapper.toResponse(objectKey, bucket))
    .onFailure().transform(exceptionMapper::map);

// ✅ Correcto — headObject previo para validar tamaño
return Uni.createFrom().completionStage(() ->
    s3AsyncClient.headObject(builder -> builder.bucket(bucket).key(key)))
    .onItem().transformToUni(head -> {
        if (head.contentLength() > configuration.maxDownloadSize()) {
            return Uni.createFrom().failure(new StorageConfigurationException(..., null));
        }
        return Uni.createFrom().completionStage(() ->
            s3AsyncClient.getObject(..., AsyncResponseTransformer.toBytes()));
    });

// ❌ Incorrecto — bloqueante
return service.upload(request).await().indefinitely();
```

## Logging

- Usar `@Slf4j` (Lombok) o `Logger log = LoggerFactory.getLogger(getClass())`
- No loguear: contenido de archivos, credenciales, URLs prefirmadas completas
- Formato estructurado para auditoría: `"s3.audit operation={} bucket={} objectKey={}"`
