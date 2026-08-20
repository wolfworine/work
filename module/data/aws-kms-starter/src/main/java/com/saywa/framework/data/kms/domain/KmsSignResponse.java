package com.saywa.framework.data.kms.domain;

/**
 * Result of {@code sign()}. Backed by KMS's {@code GenerateMac} operation
 * against an {@code HMAC_*} key: {@code value} is the raw MAC bytes, not a
 * digital signature in the RSASSA/ECDSA sense.
 *
 * @param keyId     identifier of the HMAC key that produced this MAC.
 * @param algorithm HMAC algorithm used.
 * @param value     the MAC bytes, as returned by KMS.
 */
public record KmsSignResponse(
        String keyId,
        MacAlgorithm algorithm,
        byte[] value) {
}
