package com.saywa.framework.core.error.exceptions;

import java.io.Serial;

/**
 * Thrown when network communication with the storage service (e.g. Amazon
 * S3) fails, without the service ever processing the request: timeouts,
 * connection refused, unresolved DNS, or any other transport failure.
 *
 * <p>Usually originates from a
 * {@code software.amazon.awssdk.core.exception.SdkClientException}: unlike
 * an {@code S3Exception} (which represents an error returned by the S3
 * service after processing the request), an {@code SdkClientException}
 * represents a client-side failure that occurred before or during the
 * sending of the request — for example, being unable to establish the
 * connection to the endpoint — which is characteristic of a connectivity
 * problem.</p>
 */
public final class StorageConnectionException extends StorageException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new connectivity exception.
     *
     * @param message descriptive error message; may be {@code null}.
     * @param cause   original cause of the error, typically the SDK
     *                exception that indicated the connectivity failure; may
     *                be {@code null} if there is no underlying cause.
     */
    public StorageConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
