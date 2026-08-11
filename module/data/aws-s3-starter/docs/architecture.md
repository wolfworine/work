# Arquitectura del starter aws-s3-starter

## Qué es este módulo

Librería Maven JAR reusable (`modules/data/aws-s3-starter`) del framework Saywa.
Encapsula `S3AsyncClient` de AWS SDK 2.x detrás de una API reactiva limpia.
**No es una aplicación**: sin `main`, sin REST, sin `@QuarkusMain`.

## Diagrama de capas

```
Aplicación consumidora (microservicio Quarkus)
        │  @Inject S3StorageService
        ▼
┌─────────────────────────────────────────────┐
│  service/S3StorageService                   │  ← interfaz pública
│  (Uni<T> Mutiny — sin tipos del SDK)        │
└──────────────────┬──────────────────────────┘
                   │
┌──────────────────▼──────────────────────────┐
│  service/S3StorageServiceImpl               │
│  ├── factory/S3RequestFactory               │  ← construye requests AWS
│  ├── mapper/S3ResponseMapper                │  ← SDK → domain records
│  ├── audit/S3AuditLogger                    │  ← logging estructurado
│  └── exception/StorageExceptionMapper       │  ← SDK exception → jerarquía core
└──────────────────┬──────────────────────────┘
                   │
┌──────────────────▼──────────────────────────┐
│  S3AsyncClient + S3Presigner                │  ← AWS SDK 2.x (CDI beans)
│  (inyectados por quarkus-amazon-s3)         │
└──────────────────┬──────────────────────────┘
                   │
            Amazon S3 / LocalStack
```

## Principios fundamentales

### 1. API pública sin SDK de AWS
La interfaz pública `service/S3StorageService` y el paquete `domain/` no
importan nada de `software.amazon.awssdk`. El consumidor solo ve `Uni<T>`,
records Java y la jerarquía de excepciones de almacenamiento del módulo
`core`.

### 2. Reactivo puro con Mutiny
Todos los métodos retornan `Uni<T>`. Nunca:
- `.join()`, `.get()`, `.await().indefinitely()`
- `Thread.sleep()`
- `S3Client` síncrono
- `CompletableFuture.allOf()` sin límite de concurrencia

Patrón obligatorio para adaptar el SDK:
```java
Uni.createFrom().completionStage(() -> s3AsyncClient.putObject(...))
```

### 3. S3Presigner siempre inyectado
```java
@Inject S3Presigner presigner;  // ✅ correcto

// ❌ NUNCA esto dentro de un método:
S3Presigner.create()
```

### 4. Records inmutables para el dominio
```java
// ✅ Records Java — inmutables por diseño
public record S3ObjectRequest(String objectKey, byte[] content,
                               String contentType, Map<String,String> metadata) { }

// ❌ Nunca clases con setters en domain/
```

### 5. Excepciones con causa preservada
```java
// ✅ Correcto — todas las ramas del mapper
return new StorageObjectNotFoundException(e.getMessage(), e);  // causa preservada

// ❌ Incorrecto — pierde el stacktrace original
return new StorageObjectNotFoundException(e.getMessage());
```

### 6. Auditoría sin datos sensibles
```java
// ✅ Auditar presigned: solo objectKey y ttl
auditLogger.audit(new S3AuditEvent(S3Operation.PRESIGN, bucket, objectKey, true, 0L, null));

// ❌ NUNCA registrar la URL completa
log.info("presigned URL: {}", presignedUrl);  // ← prohibido
```

### 7. move() sin rollback automático
```java
// move = copy + delete condicional
return copy(sourceKey, destinationKey)
    .flatMap(response -> delete(sourceKey).map(__ -> response));
// Si delete falla: el objeto queda en origen Y destino — documentado en Javadoc
```

## Configuración

```
Prefijo Saywa:    compartamos.storage.s3.*
Prefijo Quarkus:  quarkus.s3.*  (gestionado por quarkus-amazon-s3)
```

## Reutilización por Fase 2

Los paquetes `config/`, `audit/`, `exception/` están diseñados para ser
reutilizados por `aws-s3-transfer-manager-starter` (Fase 2). No duplicar.
