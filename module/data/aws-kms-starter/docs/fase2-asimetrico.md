# Plan de fase 2 — soporte asimétrico (RSA / ECC)

**Estado:** planificación, sin implementar. Este documento consolida la
investigación y las decisiones de diseño discutidas antes de escribir
código de fase 2. Nada de lo que describe existe todavía en `src/main`.

## Recomendaciones para cerrar fase 1 (antes de empezar fase 2)

Revisadas antes de comprometer el diseño de abajo, para no arrastrar
inconsistencias de nombres a la fase asimétrica:

| # | Recomendación | Riesgo / alcance | Estado |
|---|---|---|---|
| 1 | Renombrar `domain/EncryptionAlgorithm` → `SymmetricEncryptionAlgorithm` | Bajo — 2 usos (`CipherText`, `KmsResponseMapper`) | ✅ Hecho |
| 2 | Renombrar constantes de `domain/KmsOperation` (`ENCRYPT`→`ENCRYPT_SYMMETRIC`, `DECRYPT`→`DECRYPT_SYMMETRIC`, `SIGN`→`SIGN_MAC`, `VERIFY`→`VERIFY_MAC`) | Nulo — enum interno, solo usado en logs de auditoría | ✅ Hecho |
| 3 | Agregar `reEncrypt()` a `KmsCryptoService` (operación `ReEncrypt` de KMS — mueve ciphertext entre CMKs sin exponer el plaintext) | Medio — nuevo request/response, nueva cobertura de test | ✅ Hecho — `KmsReEncryptRequest`/`Response`, `KmsOperation.REENCRYPT`, tests en `KmsCryptoServiceImplTest` y `DomainRequestValidationTest` |
| 4 | (Opcional) simplificar `wrapEnvelope`/`unwrapEnvelope` → `wrap`/`unwrap` en `KmsKeyWrapService` | Bajo pero toca tests + README | Pendiente, sin urgencia |

## Por qué NO dividir por algoritmo (AES/RSA/ECC)

Validado contra el diseño de **Google Tink** (la referencia más citada en
diseño de librerías cripto, mantenida por el equipo de seguridad de
Google): Tink separa por *primitiva/capacidad* (`Aead`, `Mac`,
`HybridEncrypt`/`HybridDecrypt`, `PublicKeySign`/`PublicKeyVerify`), nunca
por algoritmo concreto. Una clase por algoritmo (`AesService`,
`RsaService`, `EccService`) acopla a quien consume con la elección de
algoritmo en vez de con la operación que necesita, y encarece migrar de
algoritmo después. RSA y ECC para firma comparten literalmente las mismas
operaciones SDK (`Sign`/`Verify`) — solo cambia el valor de
`SigningAlgorithm` — así que van en un solo servicio, no dos.

## Estructura de servicios — mapeo simétrico → asimétrico

| Ya existe (simétrico, fase 1) | Fase 2 (asimétrico) | Operación KMS | Algoritmos |
|---|---|---|---|
| `KmsCryptoService` (encrypt/decrypt directo, ≤4096B, con `encryptionContext`) | `KmsAsymmetricCryptoService` (encrypt/decrypt directo, ~190-470B según key spec+algoritmo, **sin** `encryptionContext`) | `Encrypt`/`Decrypt` (mismas operaciones SDK, otro `EncryptionAlgorithmSpec`) | Solo RSA — ECC no soporta `Encrypt`/`Decrypt` en KMS |
| `KmsKeyWrapService` (envelope: `GenerateDataKey` + AES-GCM local, cualquier tamaño) | `KmsHybridEncryptionService` (genera AES key local, la cifra con RSA vía `Encrypt`/localmente con `Cipher` + la pública de `GetPublicKey`, AES-GCM el payload local) | `Encrypt`/`Decrypt` con algoritmo RSA (no existe `GenerateDataKey` para llaves asimétricas) | Solo RSA |
| `KmsSignatureService` (HMAC, `GenerateMac`/`VerifyMac`) | `KmsDigitalSignatureService` (firma real, `Sign`/`Verify`) | `Sign`/`Verify` | **RSA y ECC en el mismo servicio** |
| — (no existía) | `KmsKeyPairService` (`GenerateDataKeyPair`/`GenerateDataKeyPairWithoutPlaintext`) | Provisión de pares desechables RSA/ECC protegidos por una CMK simétrica, para uso offline | RSA y ECC |

`KmsOperationSupport` (auditoría + validación de tamaño + mapeo de
excepciones) se reutiliza para los cuatro servicios nuevos, igual que hoy
con los tres de fase 1 — no hace falta tocarlo salvo agregar las nuevas
constantes de `KmsOperation`.

## Enums nuevos

- `AsymmetricEncryptionAlgorithm { RSAES_OAEP_SHA_1, RSAES_OAEP_SHA_256 }` — para `KmsAsymmetricCryptoService` y `KmsHybridEncryptionService`.
- `SigningAlgorithm { RSASSA_PSS_SHA_256, RSASSA_PSS_SHA_384, RSASSA_PSS_SHA_512, RSASSA_PKCS1_V1_5_SHA_256, RSASSA_PKCS1_V1_5_SHA_384, RSASSA_PKCS1_V1_5_SHA_512, ECDSA_SHA_256, ECDSA_SHA_384, ECDSA_SHA_512, ED25519_SHA_512, ED25519_PH_SHA_512 }` — para `KmsDigitalSignatureService`. RSA y ECC comparten el enum.
- `KmsAsymmetricKeySpec { RSA_2048, RSA_3072, RSA_4096, ECC_NIST_P256, ECC_NIST_P384, ECC_NIST_P521, ECC_SECG_P256K1, ECC_NIST_EDWARDS25519 }` con helpers `isRsa()`/`isEllipticCurve()`/`supports(KeyUsage)` — inspirado en el patrón visto en el proyecto de referencia (`quarkus-aws-kms` del zip revisado), que ya calzaba casi 1:1 con la tabla oficial de AWS.

## Detalles técnicos validados contra documentación oficial

**Key usage es exclusivo por llave, fijado al crearla — no se puede mezclar:**

| Key spec | Uso permitido |
|---|---|
| `SYMMETRIC_DEFAULT` (fase 1) | `ENCRYPT_DECRYPT` |
| `RSA_2048`/`3072`/`4096` | `ENCRYPT_DECRYPT` **o** `SIGN_VERIFY` (no ambos) |
| `ECC_NIST_P256`/`P384`/`P521` | `SIGN_VERIFY` (o `KEY_AGREEMENT`, elegido al crear la llave) |
| `ECC_SECG_P256K1`, `ECC_NIST_EDWARDS25519` | `SIGN_VERIFY` únicamente |
| `HMAC_*` (fase 1) | `GENERATE_VERIFY_MAC` |

**Límite de tamaño para `Encrypt` con RSA** (vs. 4096 bytes simétrico):

| Key spec | `RSAES_OAEP_SHA_1` | `RSAES_OAEP_SHA_256` |
|---|---|---|
| RSA_2048 | 214 bytes | 190 bytes |
| RSA_3072 | 342 bytes | 318 bytes |
| RSA_4096 | 470 bytes | 446 bytes |

**`Sign`/`Verify` tiene un parámetro que `GenerateMac`/`VerifyMac` no tiene:**
`MessageType` (`RAW` = KMS hashea el mensaje; `DIGEST` = ya se manda el
hash calculado). Hay que exponerlo en `KmsDigitalSignRequest`, con `RAW`
como default razonable (igual que hace el adapter del proyecto de
referencia revisado).

**`GenerateDataKeyPair` no es el "wrap" asimétrico** — es una operación
distinta: genera un par de llaves RSA/ECC desechable protegido por una CMK
*simétrica*, para uso offline (firmar o cifrar localmente sin llamar a KMS
cada vez). No reemplaza a `KmsHybridEncryptionService`; son capacidades
separadas y así quedan modeladas en la tabla de arriba.

## Cripto nativo de Java (JCA/JCE — no existe `jakarta.crypto`)

La criptografía en Java se quedó explícitamente fuera de la migración de
`javax.*` a `jakarta.*` — sigue siendo `javax.crypto`/`java.security` en
Java 21.

- **`javax.crypto.Cipher`** con `"RSA/ECB/OAEPWithSHA-256AndMGF1Padding"` —
  para cifrar localmente la data key AES en `KmsHybridEncryptionService`
  cuando ya se tiene la pública (`GetPublicKey`), evitando una llamada a
  KMS `Encrypt`. Es byte-compatible con `RSAES_OAEP_SHA_256` de KMS porque
  ambos implementan el mismo estándar PKCS#1 v2.2 OAEP. El *decrypt* del
  lado privado sigue siendo obligatoriamente una llamada a KMS `Decrypt`
  (la privada nunca sale de KMS).
- **`java.security.Signature`** (`"SHA256withRSA/PSS"`, `"SHA256withECDSA"`,
  etc.) — para verificar firmas de KMS localmente sin llamar a `Verify`,
  con la pública descargada. Validado contra el blog oficial de AWS sobre
  verificación offline de firmas KMS.
- **`javax.crypto.KEM`** (JEP 452, específico de Java 21, finalizado no
  preview) — **no aplica** al patrón híbrido de arriba, porque el
  proveedor "RSA-KEM" de `KEM` es una construcción distinta al OAEP puro
  que usa KMS (no sería compatible con `Decrypt` de KMS). Queda anotado
  como candidato para cuando se agregue `DeriveSharedSecret` (ECDH) más
  adelante, no para RSA encrypt/decrypt ni Sign/Verify.

## Explícitamente fuera de alcance de fase 2

- `DeriveSharedSecret` (ECDH / acuerdo de claves) — familia de operación
  distinta a cifrado/firma, se evalúa por separado.
- SM2 (solo disponible en regiones China).
- ML-DSA (firma post-cuántica, muy nueva en el API de KMS).

## Pendiente de verificar antes de implementar

- Si LocalStack Community soporta `Sign`/`Verify`/`GetPublicKey`/
  `GenerateDataKeyPair` para tests de integración locales — el README ya
  advierte que `GenerateMac`/`VerifyMac` requieren LocalStack Pro; falta
  confirmar lo mismo para las operaciones asimétricas antes de diseñar la
  estrategia de test de esta fase.

## Fuentes consultadas

- [Key spec reference — AWS KMS](https://docs.aws.amazon.com/kms/latest/developerguide/symm-asymm-choose-key-spec.html)
- [Sign — AWS KMS API Reference](https://docs.aws.amazon.com/kms/latest/APIReference/API_Sign.html)
- [GenerateDataKeyPair — AWS KMS API Reference](https://docs.aws.amazon.com/kms/latest/APIReference/API_GenerateDataKeyPair.html)
- [GenerateDataKeyPairWithoutPlaintext — AWS KMS API Reference](https://docs.aws.amazon.com/kms/latest/APIReference/API_GenerateDataKeyPairWithoutPlaintext.html)
- [KmsAsyncClient — AWS SDK for Java 2.x](https://docs.aws.amazon.com/java/api/latest/software/amazon/awssdk/services/kms/KmsAsyncClient.html)
- [Amazon KMS Client — Quarkiverse Documentation](https://docs.quarkiverse.io/quarkus-amazon-services/dev/amazon-kms.html)
- [Primitives and Interfaces — Tink](https://developers.google.com/tink/design/primitives_and_interfaces)
- [JEP 452: Key Encapsulation Mechanism API](https://openjdk.org/jeps/452)
- [How to verify AWS KMS asymmetric key signatures locally with OpenSSL — AWS Security Blog](https://aws.amazon.com/blogs/security/how-to-verify-aws-kms-asymmetric-key-signatures-locally-with-openssl/)
- [platform/namespace/unaffected-packages.adoc — jakartaee/platform](https://github.com/eclipse-ee4j/jakartaee-platform/blob/master/namespace/unaffected-packages.adoc)
