package com.saywa.framework.data.kms.domain;

import java.util.Map;

/**
 * Request to reverse envelope encryption: decrypts {@code envelope}'s data
 * key against KMS, then deciphers the payload locally with AES-GCM.
 *
 * @param envelope          the response previously produced by {@code wrap()}.
 * @param encryptionContext must be identical to the context supplied at
 *                          encryption time (used both for {@code Decrypt}
 *                          and as AES-GCM AAD); may be {@code null}, treated
 *                          as {@code Map.of()}.
 */
public record KmsUnwrapRequest(KmsWrapResponse envelope, Map<String, String> encryptionContext) {

    public KmsUnwrapRequest {
        if (envelope == null) {
            throw new IllegalArgumentException("envelope must not be null");
        }
        encryptionContext = encryptionContext == null ? Map.of() : Map.copyOf(encryptionContext);
    }
}
