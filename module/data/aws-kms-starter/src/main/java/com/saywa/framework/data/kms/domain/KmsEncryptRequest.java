package com.saywa.framework.data.kms.domain;

import java.util.Map;

/**
 * Request to cipher a small payload directly against a symmetric CMK
 * (KMS's {@code Encrypt} operation, limited to 4096 bytes of plaintext).
 * For larger payloads use envelope encryption instead
 * ({@link EnvelopeEncryptRequest}).
 *
 * @param keyRef            key id, ARN, or alias of the symmetric CMK to use.
 * @param plaintext         data to cipher; not empty.
 * @param encryptionContext additional authenticated data (AAD); may be
 *                          {@code null}, treated as {@code Map.of()}. The
 *                          exact same context must be supplied to decrypt.
 */
public record KmsEncryptRequest(String keyRef, byte[] plaintext, Map<String, String> encryptionContext) {

    public KmsEncryptRequest {
        if (keyRef == null || keyRef.isBlank()) {
            throw new IllegalArgumentException("keyRef must not be blank");
        }
        if (plaintext == null || plaintext.length == 0) {
            throw new IllegalArgumentException("plaintext must not be null or empty");
        }
        encryptionContext = encryptionContext == null ? Map.of() : Map.copyOf(encryptionContext);
    }
}
