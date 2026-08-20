package com.saywa.framework.core.error.exceptions;

import java.io.Serial;

/**
 * Thrown when a crypto request is malformed or incompatible with the
 * target key: an invalid parameter, a payload that exceeds the size limit
 * for the operation, an algorithm not supported by the key's spec, or a
 * {@code KeyUsage} mismatch (for example, trying to sign with an
 * encrypt/decrypt key).
 *
 * <p>Usually originates from a
 * {@code software.amazon.awssdk.services.kms.model.KMSInvalidStateException}
 * or {@code InvalidKeyUsageException} on the AWS side, or from a local
 * validation failure (missing bucket/keyId, payload too large) enforced by
 * the corresponding starter, mapped by its {@code KmsExceptionMapper}.</p>
 */
public final class CryptoConfigurationException extends CryptoException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new configuration exception.
     *
     * @param message descriptive error message; may be {@code null}.
     * @param cause   original cause of the error; may be {@code null} if
     *                there is no underlying cause.
     */
    public CryptoConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
