package com.saywa.framework.data.kms.domain;

/**
 * Result of {@code verify()}. An invalid MAC is not an infrastructure
 * error: it's a valid business answer (the message was altered, the wrong
 * key was used, or the MAC was forged), so it's modeled as data rather than
 * as an exception.
 *
 * @param keyId     identifier of the HMAC key used to verify.
 * @param algorithm HMAC algorithm used.
 * @param valid     {@code true} if the MAC matches the message under this key.
 */
public record KmsVerifyResponse(
        String keyId,
        MacAlgorithm algorithm,
        boolean valid) {

    public static KmsVerifyResponse valid(String keyId, MacAlgorithm algorithm) {
        return new KmsVerifyResponse(keyId, algorithm, true);
    }

    public static KmsVerifyResponse invalid(String keyId, MacAlgorithm algorithm) {
        return new KmsVerifyResponse(keyId, algorithm, false);
    }
}
