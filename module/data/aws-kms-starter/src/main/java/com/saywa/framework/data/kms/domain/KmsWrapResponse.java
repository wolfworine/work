package com.saywa.framework.data.kms.domain;

/**
 * Result of envelope encryption: the data key encrypted by KMS plus the
 * payload encrypted locally with AES-GCM. This is the pattern AWS
 * recommends for payloads larger than the 4096-byte limit of KMS's direct
 * {@code Encrypt} operation, since the payload never travels to AWS.
 *
 * @param keyId            identifier of the CMK that wrapped {@code encryptedDataKey}.
 * @param dataKeySpec      length of the AES data key that ciphered {@code ciphertext}.
 * @param encryptedDataKey the data key, encrypted under the CMK; required to unwrap.
 * @param ciphertext       {@code iv (12 bytes) || ciphertext || tag (16 bytes)},
 *                         produced by the local AES-GCM cipher.
 */
public record KmsWrapResponse(
        String keyId,
        DataKeySpec dataKeySpec,
        byte[] encryptedDataKey,
        byte[] ciphertext) {
}
