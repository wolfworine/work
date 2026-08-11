package com.saywa.framework.core.error.exceptions;

import java.io.Serial;

/**
 * Thrown when an operation is requested on an Amazon S3 object that does
 * not exist in the configured bucket, for example when trying to download,
 * generate a presigned URL, or copy a nonexistent key.
 *
 * <p>Usually originates from a
 * {@code software.amazon.awssdk.services.s3.model.NoSuchKeyException} from
 * the AWS SDK, mapped by the corresponding starter's
 * {@code StorageExceptionMapper}.</p>
 */
public final class StorageObjectNotFoundException extends StorageException {

    @Serial
    private static final long serialVersionUID = 1L;

    public StorageObjectNotFoundException(String message) {
        this(message, null);
    }

    /**
     * Creates a new object-not-found exception.
     *
     * @param message descriptive error message; may be {@code null}.
     * @param cause   original cause of the error, typically the SDK
     *                exception that indicated the key does not exist; may
     *                be {@code null} if there is no underlying cause.
     */
    public StorageObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
