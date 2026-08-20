package com.saywa.framework.data.kms.domain;

/**
 * Result of {@code decrypt()}. Backed by KMS's {@code Decrypt} operation
 * against ciphertext previously produced by {@code encrypt()}.
 *
 * @param keyId     identifier of the CMK that decrypted the ciphertext.
 * @param plaintext the recovered plaintext bytes.
 */
public record KmsDecryptResponse(String keyId, byte[] plaintext) {
}
