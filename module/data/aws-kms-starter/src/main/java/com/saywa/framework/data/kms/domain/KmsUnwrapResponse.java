package com.saywa.framework.data.kms.domain;

/**
 * Result of {@code unwrap()}: reverses envelope encryption by
 * decrypting the envelope's data key against KMS, then deciphering the
 * payload locally with AES-GCM.
 *
 * @param keyId     identifier of the CMK that decrypted the envelope's data key.
 * @param plaintext the recovered plaintext bytes.
 */
public record KmsUnwrapResponse(String keyId, byte[] plaintext) {
}
