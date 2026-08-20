package com.saywa.framework.data.kms.domain;

/**
 * Result of {@code reEncrypt()}: the ciphertext re-encrypted under the
 * destination CMK, plus both key identifiers involved.
 *
 * @param sourceKeyId      identifier of the CMK that decrypted the original ciphertext.
 * @param destinationKeyId identifier of the CMK that produced the returned ciphertext.
 * @param cipherText       the re-encrypted bytes.
 */
public record KmsReEncryptResponse(String sourceKeyId, String destinationKeyId, byte[] cipherText) {
}
