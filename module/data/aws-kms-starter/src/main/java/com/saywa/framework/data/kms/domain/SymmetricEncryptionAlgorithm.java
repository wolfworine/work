package com.saywa.framework.data.kms.domain;

/**
 * Encryption algorithm used by the {@code Encrypt}/{@code Decrypt} KMS
 * operation against a symmetric CMK.
 *
 * <p>{@link #SYMMETRIC_DEFAULT} (AES-256-GCM under a {@code SYMMETRIC_DEFAULT}
 * CMK) is the only value, since this starter's direct encrypt/decrypt only
 * targets symmetric keys. The {@code RSAES_OAEP_*} algorithms used by
 * asymmetric KMS keys live in their own {@code AsymmetricEncryptionAlgorithm}
 * when this starter grows asymmetric support.</p>
 */
public enum SymmetricEncryptionAlgorithm {

    SYMMETRIC_DEFAULT
}
