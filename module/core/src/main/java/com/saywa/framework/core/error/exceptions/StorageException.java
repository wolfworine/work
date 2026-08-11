package com.saywa.framework.core.error.exceptions;

import java.io.Serial;

/**
 * Root exception of the storage error hierarchy shared by the Saywa
 * framework.
 *
 * <p>All exceptions in this hierarchy expose a single constructor
 * {@code (String message, Throwable cause)} to guarantee that the original
 * cause (for example, an AWS SDK exception) is never lost and remains
 * available via {@link #getCause()}.</p>
 *
 * <p>Sealed to exactly five subclasses ({@link StorageObjectNotFoundException},
 * {@link StorageAccessDeniedException}, {@link StorageConfigurationException},
 * {@link StorageConnectionException}, and the catch-all
 * {@link StorageGenericException} for failures that don't fit any of the
 * other four): the base type is {@code abstract} and cannot be instantiated
 * directly, so every instance is necessarily one of these five. This makes a
 * {@code switch} over this hierarchy (see the {@code StorageExceptionMapper}
 * classes) exhaustive without a {@code default} branch, and the compiler
 * rejects the build if a new subclass is added without also being added to
 * the {@code permits} clause below and handled in every such switch.</p>
 */
public abstract sealed class StorageException extends RuntimeException
        permits StorageAccessDeniedException, StorageConfigurationException,
        StorageConnectionException, StorageGenericException, StorageObjectNotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new storage exception.
     *
     * @param message descriptive error message; may be {@code null}.
     * @param cause   original cause of the error, typically an AWS SDK
     *                exception; may be {@code null} if there is no
     *                underlying cause.
     */
    protected StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
