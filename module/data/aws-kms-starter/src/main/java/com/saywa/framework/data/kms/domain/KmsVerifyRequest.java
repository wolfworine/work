package com.saywa.framework.data.kms.domain;

/**
 * Request to validate a MAC previously produced by {@code sign()} (KMS's
 * {@code VerifyMac}).
 *
 * @param keyRef    key id, ARN, or alias of the HMAC key.
 * @param message   original message that was signed; not empty.
 * @param mac       the MAC bytes to validate; not empty.
 * @param algorithm HMAC algorithm; if {@code null} defaults to
 *                  {@link MacAlgorithm#HMAC_SHA_256}.
 */
public record KmsVerifyRequest(String keyRef, byte[] message, byte[] mac, MacAlgorithm algorithm) {

    public KmsVerifyRequest {
        if (keyRef == null || keyRef.isBlank()) {
            throw new IllegalArgumentException("keyRef must not be blank");
        }
        if (message == null || message.length == 0) {
            throw new IllegalArgumentException("message must not be null or empty");
        }
        if (mac == null || mac.length == 0) {
            throw new IllegalArgumentException("mac must not be null or empty");
        }
        algorithm = algorithm == null ? MacAlgorithm.HMAC_SHA_256 : algorithm;
    }
}
