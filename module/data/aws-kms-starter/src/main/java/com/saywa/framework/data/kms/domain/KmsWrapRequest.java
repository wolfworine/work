package com.saywa.framework.data.kms.domain;

import java.util.Map;

/**
 * Request to envelope-encrypt a payload of any size: KMS generates and
 * protects a one-time AES data key ({@code GenerateDataKey}), and the
 * payload is ciphered locally with AES-GCM using the plaintext key, which
 * is discarded immediately after.
 *
 * @param keyRef            key id, ARN, or alias of the symmetric CMK that
 *                          will wrap the generated data key.
 * @param plaintext         data to cipher; not empty. Unlike
 *                          {@link KmsEncryptRequest}, there is no 4096-byte
 *                          limit here.
 * @param dataKeySpec       length of the AES data key to generate; if
 *                          {@code null} defaults to {@link DataKeySpec#AES_256}.
 * @param encryptionContext additional authenticated data, applied both to
 *                          {@code GenerateDataKey} and as AAD of the local
 *                          AES-GCM cipher; may be {@code null}, treated as
 *                          {@code Map.of()}.
 */
public record KmsWrapRequest(String keyRef, byte[] plaintext, DataKeySpec dataKeySpec,
        Map<String, String> encryptionContext) {

    public KmsWrapRequest {
        if (keyRef == null || keyRef.isBlank()) {
            throw new IllegalArgumentException("keyRef must not be blank");
        }
        if (plaintext == null || plaintext.length == 0) {
            throw new IllegalArgumentException("plaintext must not be null or empty");
        }
        dataKeySpec = dataKeySpec == null ? DataKeySpec.AES_256 : dataKeySpec;
        encryptionContext = encryptionContext == null ? Map.of() : Map.copyOf(encryptionContext);
    }
}
