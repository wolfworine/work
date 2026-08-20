package com.saywa.framework.data.kms.domain;

/**
 * Request to sign a message (KMS's {@code GenerateMac}, against an
 * {@code HMAC_*} key).
 *
 * @param keyRef    key id, ARN, or alias of the HMAC key.
 * @param message   data to sign; not empty. KMS accepts up to 4096 bytes
 *                  for MAC operations.
 * @param algorithm HMAC algorithm; if {@code null} defaults to
 *                  {@link MacAlgorithm#HMAC_SHA_256}.
 */
public record KmsSignRequest(String keyRef, byte[] message, MacAlgorithm algorithm) {

    public KmsSignRequest {
        if (keyRef == null || keyRef.isBlank()) {
            throw new IllegalArgumentException("keyRef must not be blank");
        }
        if (message == null || message.length == 0) {
            throw new IllegalArgumentException("message must not be null or empty");
        }
        algorithm = algorithm == null ? MacAlgorithm.HMAC_SHA_256 : algorithm;
    }
}
