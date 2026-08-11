package com.saywa.framework.core.error.exceptions;

import java.io.Serial;

/**
 * Thrown when there is a configuration problem that prevents an operation
 * against Amazon S3 from completing, for example missing or invalid
 * credentials, an unresolved region, an invalid bucket, or a size limit
 * (upload/download) configured in {@code S3Configuration} that is exceeded
 * before invoking the SDK.
 *
 * <p>When the origin is an SDK exception, it usually originates from a
 * {@code software.amazon.awssdk.core.exception.SdkClientException}: unlike
 * an {@code S3Exception} (which represents an error returned by the S3
 * service after processing the request), an {@code SdkClientException}
 * represents a client-side failure that occurred before or during the
 * construction/sending of the request — for example, being unable to
 * resolve credentials or region — which is characteristic of a
 * configuration problem.</p>
 */
public final class StorageConfigurationException extends StorageException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new configuration exception.
     *
     * @param message descriptive error message; may be {@code null}.
     * @param cause   original cause of the error, typically the SDK
     *                exception or the internal validation that detected the
     *                configuration problem; may be {@code null} if there is
     *                no underlying cause.
     */
    public StorageConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
