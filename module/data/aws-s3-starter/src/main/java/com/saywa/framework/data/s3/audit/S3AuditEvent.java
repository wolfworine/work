package com.saywa.framework.data.s3.audit;

import com.saywa.framework.data.s3.domain.S3Operation;

/**
 * Immutable audit event describing an operation performed by
 * {@code S3StorageService} against Amazon S3, intended to be recorded by
 * {@link S3AuditLogger}.
 * <p>
 * This event must <strong>never</strong> carry file content, credentials, or
 * full presigned URLs: only operational metadata (which operation, on which
 * bucket/key, whether it succeeded, how long it took, and, on failure, an
 * error code).
 *
 * @param operation   type of S3 operation audited; never {@code null}.
 * @param bucket      name of the bucket the operation was performed on;
 *                     never {@code null}.
 * @param objectKey   key of the affected object; may be {@code null} for
 *                     operations that don't concern a specific object (for
 *                     example, a {@code LIST} with no results).
 * @param successful  {@code true} if the operation completed successfully,
 *                     {@code false} otherwise.
 * @param durationMs  duration of the operation in milliseconds; may be
 *                     {@code null} if it wasn't measured.
 * @param errorCode   error code associated with a failure; {@code null} if
 *                     {@code successful} is {@code true} or if there is no
 *                     identifiable error code.
 */
public record S3AuditEvent(
        S3Operation operation,
        String bucket,
        String objectKey,
        boolean successful,
        Long durationMs,
        String errorCode
) {
}
