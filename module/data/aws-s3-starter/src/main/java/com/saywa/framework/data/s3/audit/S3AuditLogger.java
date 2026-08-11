package com.saywa.framework.data.s3.audit;

import com.saywa.framework.data.s3.domain.S3Operation;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * Records S3 operation audit events ({@link S3AuditEvent}) in a structured
 * format, intended to be consumed by observability systems (log
 * aggregators, alerts, dashboards).
 * <p>
 * {@link #success(S3Operation, String, String, long)} and
 * {@link #failure(S3Operation, String, String, long, Throwable)} are the two
 * entry points callers should use — they build the {@link S3AuditEvent} and
 * log it in one step. {@link #audit(S3AuditEvent)} remains available as the
 * low-level primitive for callers that already have a fully-built event.
 * <p>
 * This logger must <strong>never receive file content, credentials, or full
 * presigned URLs</strong>: {@link S3AuditEvent} is deliberately designed to
 * be unable to carry that data, and no method of this class should be
 * extended to accept it.
 */
@Slf4j
@ApplicationScoped
public class S3AuditLogger {

    /**
     * Records a successful operation.
     *
     * @param operation  S3 operation that succeeded; must not be {@code null}.
     * @param bucketName bucket the operation was performed on.
     * @param objectKey  key (or resolved prefix) affected by the operation;
     *                   may be {@code null} for operations that don't
     *                   concern a single key.
     * @param durationMs how long the operation took, or — for
     *                   {@link S3Operation#PRESIGN} — the effective TTL of
     *                   the signed URL, in milliseconds.
     */
    public void success(S3Operation operation, String bucketName, String objectKey, long durationMs) {
        audit(new S3AuditEvent(operation, bucketName, objectKey, true, durationMs, null));
    }

    /**
     * Records a failed operation. The error is recorded only as
     * {@code error.getClass().getSimpleName()} — never its message, which
     * may contain sensitive infrastructure details.
     *
     * @param operation  S3 operation that failed; must not be {@code null}.
     * @param bucketName bucket the operation was performed on.
     * @param objectKey  key (or resolved prefix) affected by the operation;
     *                   may be {@code null} for operations that don't
     *                   concern a single key.
     * @param durationMs how long the operation took before failing, or —
     *                   for {@link S3Operation#PRESIGN} — the effective TTL
     *                   that would have applied, in milliseconds.
     * @param error      the failure; must not be {@code null}.
     */
    public void failure(S3Operation operation, String bucketName, String objectKey, long durationMs, Throwable error) {
        audit(new S3AuditEvent(operation, bucketName, objectKey, false, durationMs, error.getClass().getSimpleName()));
    }

    /**
     * Records an audit event in the log, with the structured format
     * {@code s3.audit operation={} bucket={} objectKey={} success={} durationMs={} errorCode={}}.
     * <p>
     * If {@code event} is {@code null}, the method returns silently without
     * logging anything or throwing any exception; auditing is a supporting
     * responsibility that must never interrupt the main reactive flow of
     * {@code S3StorageService}.
     *
     * @param event audit event to record; may be {@code null}.
     */
    public void audit(S3AuditEvent event) {
        if (event == null) {
            return;
        }

        log.info("s3.audit operation={} bucket={} objectKey={} success={} durationMs={} errorCode={}",
                event.operation(),
                event.bucket(),
                event.objectKey(),
                event.successful(),
                event.durationMs(),
                event.errorCode());
    }
}
