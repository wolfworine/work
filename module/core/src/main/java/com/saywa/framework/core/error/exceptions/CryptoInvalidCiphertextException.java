package com.saywa.framework.core.error.exceptions;

import java.io.Serial;

/**
 * Thrown when ciphertext (or a MAC / GCM authentication tag) fails to
 * validate: the data was tampered with, the wrong key was used, or the
 * encryption context / additional authenticated data does not match what
 * was used at encryption time.
 *
 * <p>Usually originates from a
 * {@code software.amazon.awssdk.services.kms.model.InvalidCiphertextException}
 * on the AWS side, or from a {@code javax.crypto.AEADBadTagException} when
 * validating locally (envelope decryption), mapped by the corresponding
 * starter's {@code KmsExceptionMapper}. This is a business-level failure —
 * not a server error — and is kept as its own branch (distinct from
 * {@link CryptoConfigurationException}) so callers can react to it
 * specifically, mirroring how {@link StorageObjectNotFoundException} is
 * kept separate from generic storage configuration errors.</p>
 */
public final class CryptoInvalidCiphertextException extends CryptoException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new invalid-ciphertext exception.
     *
     * @param message descriptive error message; may be {@code null}.
     * @param cause   original cause of the error; may be {@code null} if
     *                there is no underlying cause.
     */
    public CryptoInvalidCiphertextException(String message, Throwable cause) {
        super(message, cause);
    }
}
