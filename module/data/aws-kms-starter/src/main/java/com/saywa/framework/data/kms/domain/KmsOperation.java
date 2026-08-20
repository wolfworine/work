package com.saywa.framework.data.kms.domain;

/**
 * Cryptographic operations audited by {@code KmsAuditLogger}.
 */
public enum KmsOperation {
    ENCRYPT_SYMMETRIC,
    DECRYPT_SYMMETRIC,
    REENCRYPT,
    WRAP_ENVELOPE,
    UNWRAP_ENVELOPE,
    SIGN_MAC,
    VERIFY_MAC
}
