package com.saywa.framework.data.kms.domain;

import java.util.Map;

/**
 * Ciphertext returned by KMS's {@code Encrypt} operation. The blob already
 * carries the key identifier embedded internally, but it is also carried
 * here explicitly so callers don't need to track it separately.
 *
 * @param keyId             identifier of the CMK that produced this ciphertext.
 * @param algorithm         algorithm used to produce this ciphertext.
 * @param value             the encrypted bytes, as returned by KMS.
 * @param encryptionContext additional authenticated data (AAD) supplied at
 *                          encryption time; never {@code null} — use
 *                          {@code Map.of()} when there is none. The exact
 *                          same context must be supplied again to
 *                          {@code decrypt()}, or the operation fails.
 */
public record CipherText(
        String keyId,
        SymmetricEncryptionAlgorithm algorithm,
        byte[] value,
        Map<String, String> encryptionContext) {

    public CipherText {
        encryptionContext = encryptionContext == null ? Map.of() : Map.copyOf(encryptionContext);
    }

    /**
     * @return the size, in bytes, of the ciphertext value, or {@code 0} if it is {@code null}.
     */
    public int sizeInBytes() {
        return value == null ? 0 : value.length;
    }
}
