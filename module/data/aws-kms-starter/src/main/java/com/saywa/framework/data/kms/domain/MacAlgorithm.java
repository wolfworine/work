package com.saywa.framework.data.kms.domain;

/**
 * HMAC algorithm used by {@code sign()}/{@code verify()}, backed by KMS's
 * {@code GenerateMac}/{@code VerifyMac} operations against an
 * {@code HMAC_*} key.
 *
 * <p>This is the symmetric equivalent of a digital signature: the same
 * {@code HMAC_*} key both produces and validates the MAC, and the key
 * material never leaves KMS. True asymmetric signing (RSASSA/ECDSA with
 * {@code RSA_*}/{@code ECC_*} keys) is out of scope for this phase of the
 * starter and will be added alongside the rest of asymmetric support.</p>
 */
public enum MacAlgorithm {

    HMAC_SHA_224,
    HMAC_SHA_256,
    HMAC_SHA_384,
    HMAC_SHA_512
}
