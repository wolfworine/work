package com.saywa.framework.core.error.exceptions;

import java.io.Serial;

/**
 * Thrown when Amazon S3 rejects an operation due to insufficient
 * permissions, typically when the service responds with HTTP status code
 * 403 (Forbidden).
 *
 * <p>Usually originates from a
 * {@code software.amazon.awssdk.services.s3.model.S3Exception} from the AWS
 * SDK with {@code statusCode() == 403}, mapped by the corresponding
 * starter's {@code StorageExceptionMapper}.</p>
 */
public final class StorageAccessDeniedException extends StorageException {

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
    public StorageAccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
