package com.saywa.framework.data.kms.domain;

import java.util.Map;

/**
 * Request to move ciphertext from one symmetric CMK to another (KMS's
 * {@code ReEncrypt} operation) without ever exposing the plaintext outside
 * KMS: the source ciphertext is decrypted and the result re-encrypted
 * under the destination key in a single call.
 *
 * <p>{@code sourceKeyRef} is optional: KMS can resolve the source CMK from
 * the ciphertext blob itself, but supplying it lets KMS validate it
 * matches and is the recommended practice, same as {@link KmsDecryptRequest}.</p>
 *
 * @param sourceKeyRef               key id, ARN, or alias expected to have
 *                                    produced {@code cipherText}; may be
 *                                    {@code null}.
 * @param destinationKeyRef          key id, ARN, or alias of the CMK the
 *                                    ciphertext is re-encrypted under; not blank.
 * @param cipherText                 the encrypted bytes to re-encrypt; not empty.
 * @param sourceEncryptionContext    must be identical to the context supplied
 *                                    at the original encryption time, or the
 *                                    operation fails; may be {@code null},
 *                                    treated as {@code Map.of()}.
 * @param destinationEncryptionContext the context to bind the re-encrypted
 *                                    ciphertext to; may be {@code null},
 *                                    treated as {@code Map.of()}.
 */
public record KmsReEncryptRequest(
        String sourceKeyRef,
        String destinationKeyRef,
        byte[] cipherText,
        Map<String, String> sourceEncryptionContext,
        Map<String, String> destinationEncryptionContext) {

    public KmsReEncryptRequest {
        if (destinationKeyRef == null || destinationKeyRef.isBlank()) {
            throw new IllegalArgumentException("destinationKeyRef must not be blank");
        }
        if (cipherText == null || cipherText.length == 0) {
            throw new IllegalArgumentException("cipherText must not be null or empty");
        }
        sourceEncryptionContext = sourceEncryptionContext == null ? Map.of() : Map.copyOf(sourceEncryptionContext);
        destinationEncryptionContext = destinationEncryptionContext == null
                ? Map.of() : Map.copyOf(destinationEncryptionContext);
    }
}
