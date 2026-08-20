package com.saywa.framework.data.kms.domain;

import java.util.Map;

/**
 * Request to decrypt ciphertext previously produced by {@code encrypt()}.
 *
 * <p>{@code keyRef} is optional: KMS can resolve the CMK from the
 * ciphertext blob itself, but supplying it lets KMS validate it matches and
 * is the recommended practice.</p>
 *
 * @param keyRef            key id, ARN, or alias expected to have produced
 *                          {@code cipherText}; may be {@code null}.
 * @param cipherText        the encrypted bytes to decrypt; not empty.
 * @param encryptionContext must be identical to the context supplied at
 *                          encryption time, or the operation fails; may be
 *                          {@code null}, treated as {@code Map.of()}.
 */
public record KmsDecryptRequest(String keyRef, byte[] cipherText, Map<String, String> encryptionContext) {

    public KmsDecryptRequest {
        if (cipherText == null || cipherText.length == 0) {
            throw new IllegalArgumentException("cipherText must not be null or empty");
        }
        encryptionContext = encryptionContext == null ? Map.of() : Map.copyOf(encryptionContext);
    }
}
