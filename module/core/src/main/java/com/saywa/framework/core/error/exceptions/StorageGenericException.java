package com.saywa.framework.core.error.exceptions;

import java.io.Serial;

/**
 * Thrown for storage failures that don't fit any of the more specific
 * categories ({@link StorageObjectNotFoundException},
 * {@link StorageAccessDeniedException}, {@link StorageConfigurationException},
 * {@link StorageConnectionException}) — the catch-all branch of the sealed
 * {@link StorageException} hierarchy.
 */
public final class StorageGenericException extends StorageException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new generic storage exception.
     *
     * @param message descriptive error message; may be {@code null}.
     * @param cause   original cause of the error; may be {@code null} if
     *                there is no underlying cause.
     */
    public StorageGenericException(String message, Throwable cause) {
        super(message, cause);
    }
}
