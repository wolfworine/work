package com.saywa.framework.core.error.exceptions;

import java.io.Serial;

/**
 * Thrown when an operation references a KMS key or alias that does not
 * exist, is not accessible from the current account/region, or has already
 * been deleted.
 *
 * <p>Usually originates from a
 * {@code software.amazon.awssdk.services.kms.model.NotFoundException} from
 * the AWS SDK, mapped by the corresponding starter's
 * {@code KmsExceptionMapper}.</p>
 */
public final class CryptoKeyNotFoundException extends CryptoException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new key-not-found exception.
     *
     * @param message descriptive error message; may be {@code null}.
     * @param cause   original cause of the error, typically the SDK
     *                exception that indicated the key does not exist; may
     *                be {@code null} if there is no underlying cause.
     */
    public CryptoKeyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
