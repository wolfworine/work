# Saywa AWS KMS Starter

Librería Maven JAR reusable (`modules/data/aws-kms-starter`) del framework
Saywa. Encapsula `KmsAsyncClient` de AWS SDK 2.x detrás de una API reactiva
limpia para operaciones **simétricas** de AWS KMS. **No es una aplicación**:
sin `main`, sin REST, sin `@QuarkusMain`.

## Alcance de esta fase

La API pública se divide en tres servicios, uno por familia de operación:

| Servicio | Capacidad | Operación KMS | Método |
|---|---|---|---|
| `KmsCryptoService` | Cifrado/descifrado directo | `Encrypt` / `Decrypt` (≤ 4096 bytes) | `encrypt()` / `decrypt()` |
| `KmsCryptoService` | Mover ciphertext entre CMKs sin exponer el plaintext | `ReEncrypt` | `reEncrypt()` |
| `KmsKeyWrapService` | Envelope encryption | `GenerateDataKey` + AES-GCM local | `wrap()` / `unwrap()` |
| `KmsSignatureService` | Firma/verificación simétrica | `GenerateMac` / `VerifyMac` (HMAC) | `sign()` / `verify()` |

`sign()`/`verify()` están respaldados por `GenerateMac`/`VerifyMac` contra
una llave `HMAC_*` — el equivalente simétrico de una firma digital: la
misma llave firma y verifica, y nunca sale de KMS. La firma asimétrica real
(RSASSA/ECDSA contra llaves `RSA_*`/`ECC_*`) queda para una fase futura del
starter, junto con el resto del soporte asimétrico.

## Diagrama de capas

```
Aplicación consumidora (microservicio Quarkus)
   │ @Inject KmsCryptoService     │ @Inject KmsKeyWrapService   │ @Inject KmsSignatureService
   ▼                              ▼                             ▼
┌────────────────────┐  ┌────────────────────┐  ┌────────────────────┐
│ KmsCryptoService    │  │ KmsKeyWrapService   │  │ KmsSignatureService│  ← interfaces públicas
│ encrypt()/decrypt() │  │ wrap()/unwrap()     │  │ sign()/verify()    │    (Uni<T> Mutiny — sin
│ /reEncrypt()        │  │                     │  │                    │     tipos del SDK)
└──────────┬──────────┘  └──────────┬──────────┘  └──────────┬─────────┘
           │                        │                        │
┌──────────▼──────────┐  ┌──────────▼──────────┐  ┌──────────▼─────────┐
│ service/impl/        │  │service/impl/        │  │service/impl/       │
│ KmsCryptoServiceImpl │  │KmsKeyWrapServiceImpl│  │KmsSignatureService-│
│                      │  │(+ crypto/AesGcmCipher)│  │Impl               │
└──────────┬──────────┘  └──────────┬──────────┘  └──────────┬─────────┘
           │                        │                        │
           └────────────┬───────────┴────────────┬───────────┘
                         ▼                        ▼
              service/KmsOperationSupport   factory/KmsRequestFactory
              (config, validación de       mapper/KmsResponseMapper
               tamaño, auditoría vía        audit/KmsAuditLogger
               audit/KmsAuditLogger,        exception/KmsExceptionMapper
               mapeo de excepciones)
                         │
                         ▼
              KmsAsyncClient  ← AWS SDK 2.x (CDI bean, inyectado por quarkus-amazon-kms)
                         │
                      AWS KMS
```

`KmsAsyncClient` se inyecta **directamente** dentro de cada `*ServiceImpl`
— el mismo patrón que `aws-s3-starter` usa con `S3AsyncClient` — sin módulo
adaptador ni puerto intermedio. Las tres implementaciones comparten
`KmsRequestFactory`, `KmsResponseMapper` y el helper interno
`KmsOperationSupport` (validación de tamaño + auditoría + mapeo de
excepciones), para no repetir esa lógica tres veces.

## Principios fundamentales

### 1. API pública sin SDK de AWS
`service/KmsCryptoService` y `domain/` no importan nada de
`software.amazon.awssdk`. El consumidor solo ve `Uni<T>`, records Java y la
jerarquía `Crypto*Exception` compartida del módulo `core`.

### 2. Reactivo puro con Mutiny
Todos los métodos retornan `Uni<T>`. El cliente async usa transporte Netty
(`netty-nio-client`), no `url-connection-client` (ese es solo para el
cliente síncrono). Patrón obligatorio para adaptar el SDK:
```java
Uni.createFrom().completionStage(() -> kmsAsyncClient.encrypt(...))
```
El cifrado AES-GCM local (envelope) es trabajo de CPU síncrono — se
encadena con `.onItem().transform(...)`, sin envolverlo artificialmente en
otro `Uni.createFrom().item(...)` asíncrono.

### 3. Envelope encryption: la clave en claro nunca se persiste
```java
byte[] plaintextKey = dataKey.plaintextKey();
try {
    ciphertext = localCipher.encrypt(plaintextKey, payload, aad);
} finally {
    Arrays.fill(plaintextKey, (byte) 0); // wipe inmediato
}
```

### 4. Encryption context = AAD del cifrado local
El `encryptionContext` se usa tanto para `GenerateDataKey`/`Decrypt` en KMS
como para el AAD del AES-GCM local: si cambia, el tag deja de validar y el
`unwrap()` falla con `CryptoInvalidCiphertextException`.

### 5. Excepciones con causa preservada
Toda excepción del starter extiende `CryptoException` (sellada, 6 subclases,
módulo `core`), con causa siempre preservada:
```java
// ✅ Correcto
return new CryptoInvalidCiphertextException(e.getMessage(), e);
// ❌ Incorrecto — pierde el stacktrace original
return new CryptoInvalidCiphertextException(e.getMessage(), null);
```

### 6. Auditoría sin datos sensibles
```java
// ✅ Solo keyId, tamaño, éxito/duración/error
auditLogger.audit(new KmsAuditEvent(SIGN, keyId, size, true, durationMs, null));

// ❌ NUNCA loguear plaintext, claves en claro ni valores de MAC/firma
log.info("mac: {}", macValue); // ← prohibido
```

## Configuración

```
Prefijo Saywa:    compartamos.crypto.kms.*
Prefijo Quarkus:  quarkus.kms.*  (gestionado por quarkus-amazon-kms)
```

| Property | Default | Descripción |
|---|---|---|
| `compartamos.crypto.kms.default-key-ref` | `""` | Referencia de conveniencia; no forzada — cada request sigue exigiendo su propio `keyRef` |
| `compartamos.crypto.kms.audit-enabled` | `true` | Emitir evento de auditoría por operación |
| `compartamos.crypto.kms.max-direct-plaintext-size` | `4096` | Límite propio para `encrypt()`/`decrypt()`, ≤ límite duro de AWS |
| `compartamos.crypto.kms.max-mac-message-size` | `4096` | Límite propio para `sign()`/`verify()`, ≤ límite duro de AWS |

## Ejemplos de uso

```java
@Inject KmsCryptoService cryptoService;
@Inject KmsKeyWrapService keyWrapService;
@Inject KmsSignatureService signatureService;

// Cifrado directo (payload pequeño)
CipherText cipher = cryptoService.encrypt(new KmsEncryptRequest("alias/my-key", plaintext, null))
        .await().indefinitely();
KmsDecryptResponse decrypted = cryptoService.decrypt(
        new KmsDecryptRequest("alias/my-key", cipher.value(), null))
        .await().indefinitely();

// Mover ciphertext de una CMK a otra sin exponer el plaintext
KmsReEncryptResponse reEncrypted = cryptoService.reEncrypt(
        new KmsReEncryptRequest("alias/my-key", "alias/other-key", cipher.value(), null, null))
        .await().indefinitely();

// Envelope encryption (payload de cualquier tamaño)
KmsWrapResponse envelope = keyWrapService.wrap(
        new KmsWrapRequest("alias/my-key", largePayload, DataKeySpec.AES_256, Map.of("tenant", "acme")))
        .await().indefinitely();
KmsUnwrapResponse recovered = keyWrapService.unwrap(
        new KmsUnwrapRequest(envelope, Map.of("tenant", "acme")))
        .await().indefinitely();

// Firma simétrica (HMAC)
KmsSignResponse signature = signatureService.sign(new KmsSignRequest("alias/my-hmac-key", message, null))
        .await().indefinitely();
KmsVerifyResponse result = signatureService.verify(
        new KmsVerifyRequest("alias/my-hmac-key", message, signature.value(), null))
        .await().indefinitely();
```

(En código real de aplicación Quarkus, encadenar con `Uni`/Mutiny en vez de
`.await().indefinitely()`, que solo es válido en tests.)

## Manejo de errores

`KmsExceptionMapper` traduce toda excepción del SDK KMS (y el
`AesGcmTagMismatchException` del cifrado local) a la jerarquía compartida
`Crypto*Exception` del módulo `core`:

| Excepción | Origen típico |
|---|---|
| `CryptoKeyNotFoundException` | `NotFoundException` |
| `CryptoInvalidCiphertextException` | `InvalidCiphertextException`, tag AES-GCM/MAC inválido |
| `CryptoAccessDeniedException` | `AccessDeniedException`, `DisabledException`, `KmsInvalidStateException`, `KeyUnavailableException` |
| `CryptoConfigurationException` | `InvalidKeyUsageException`, validaciones propias (tamaño excedido) |
| `CryptoConnectionException` | `SdkClientException` |
| `CryptoGenericException` | catch-all |

## Testing

Los tests unitarios (`src/test/java/unit/`) mockean `KmsAsyncClient` con
Mockito — sin `@QuarkusTest`. `KmsRequestFactory`, `KmsResponseMapper`,
`AesGcmCipher` y `KmsExceptionMapper` se usan en su implementación real.

```bash
mvn test
```

Para tests de integración contra LocalStack, ver
`src/test/resources/application.properties`. **Nota**: `GenerateMac`/
`VerifyMac` (usados por `sign()`/`verify()`) requieren LocalStack Pro; la
edición community no los soporta.

## Fase futura (asimétrica)

Fuera de alcance de este starter por ahora: cifrado con llaves `RSA_*`
(`RSAES_OAEP_*`) y firma digital real con `RSA_*`/`ECC_*`
(`RSASSA_PSS_*`, `RSASSA_PKCS1_V1_5_*`, `ECDSA_*`), además del ciclo de
vida completo de llaves (`CreateKey`, rotación, `ScheduleKeyDeletion`).
