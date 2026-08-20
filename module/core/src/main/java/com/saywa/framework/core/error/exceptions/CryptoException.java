package com.saywa.framework.core.error.exceptions;

import java.io.Serial;

/**
 * Root exception of the cryptography error hierarchy shared by the Saywa
 * framework, used by starters that wrap AWS KMS (or another key
 * management / crypto provider).
 *
 * <p>All exceptions in this hierarchy expose a single constructor
 * {@code (String message, Throwable cause)} to guarantee that the original
 * cause (for example, an AWS SDK exception) is never lost and remains
 * available via {@link #getCause()}.</p>
 *
 * <p>Sealed to exactly six subclasses ({@link CryptoKeyNotFoundException},
 * {@link CryptoInvalidCiphertextException}, {@link CryptoAccessDeniedException},
 * {@link CryptoConfigurationException}, {@link CryptoConnectionException}, and
 * the catch-all {@link CryptoGenericException} for failures that don't fit
 * any of the other five): the base type is {@code abstract} and cannot be
 * instantiated directly, so every instance is necessarily one of these six.
 * This makes a {@code switch} over this hierarchy (see the
 * {@code KmsExceptionMapper} classes) exhaustive without a {@code default}
 * branch, and the compiler rejects the build if a new subclass is added
 * without also being added to the {@code permits} clause below and handled
 * in every such switch.</p>
 */
public abstract sealed class CryptoException extends RuntimeException
        permits CryptoKeyNotFoundException, CryptoInvalidCiphertextException,
        CryptoAccessDeniedException, CryptoConfigurationException,
        CryptoConnectionException, CryptoGenericException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new crypto exception.
     *
     * @param message descriptive error message; may be {@code null}.
     * @param cause   original cause of the error, typically an AWS SDK
     *                exception; may be {@code null} if there is no
     *                underlying cause.
     */
    protected CryptoException(String message, Throwable cause) {
        super(message, cause);
    }
}
