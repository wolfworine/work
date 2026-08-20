package com.saywa.framework.core.error.exceptions;

import java.io.Serial;

/**
 * Catch-all exception for crypto failures that do not fit any of the other
 * branches of the {@link CryptoException} hierarchy.
 *
 * <p>Mapped by the corresponding starter's {@code KmsExceptionMapper} as
 * the default branch for any AWS SDK exception (or other {@link Throwable})
 * that isn't specifically classified as key-not-found, invalid-ciphertext,
 * access-denied, configuration, or connection related.</p>
 */
public final class CryptoGenericException extends CryptoException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new generic crypto exception.
     *
     * @param message descriptive error message; may be {@code null}.
     * @param cause   original cause of the error; may be {@code null} if
     *                there is no underlying cause.
     */
    public CryptoGenericException(String message, Throwable cause) {
        super(message, cause);
    }
}
