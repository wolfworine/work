package com.saywa.framework.core.error.exceptions;

import java.io.Serial;

/**
 * Thrown when the application is unable to communicate with AWS KMS at
 * all: network failures, timeouts, or client-side errors unrelated to a
 * specific request.
 *
 * <p>Usually originates from a
 * {@code software.amazon.awssdk.core.exception.SdkClientException} from the
 * AWS SDK, mapped by the corresponding starter's
 * {@code KmsExceptionMapper}.</p>
 */
public final class CryptoConnectionException extends CryptoException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new connection exception.
     *
     * @param message descriptive error message; may be {@code null}.
     * @param cause   original cause of the error, typically the SDK client
     *                exception; may be {@code null} if there is no
     *                underlying cause.
     */
    public CryptoConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
