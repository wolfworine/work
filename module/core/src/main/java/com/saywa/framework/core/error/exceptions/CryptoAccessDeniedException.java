package com.saywa.framework.core.error.exceptions;

import java.io.Serial;

/**
 * Thrown when AWS KMS rejects an operation due to insufficient permissions,
 * a disabled key, or a key state that does not allow the requested
 * operation (for example, pending deletion).
 *
 * <p>Usually originates from a
 * {@code software.amazon.awssdk.services.kms.model.KmsException} subtype
 * such as {@code AccessDeniedException}, {@code DisabledException}, or
 * {@code KMSInvalidStateException} from the AWS SDK, mapped by the
 * corresponding starter's {@code KmsExceptionMapper}.</p>
 */
public final class CryptoAccessDeniedException extends CryptoException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new access-denied exception.
     *
     * @param message descriptive error message; may be {@code null}.
     * @param cause   original cause of the error, typically the SDK
     *                exception that indicated the permission rejection; may
     *                be {@code null} if there is no underlying cause.
     */
    public CryptoAccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
