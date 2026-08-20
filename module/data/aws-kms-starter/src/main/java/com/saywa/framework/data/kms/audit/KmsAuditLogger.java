package com.saywa.framework.data.kms.audit;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * Reusable audit logging for crypto operations.
 *
 * <p><strong>Must never receive plaintext, key material (including data
 * keys, even "just the plaintext form before wipe"), MAC/signature values,
 * or encryption context values</strong> — only operation metadata
 * ({@code keyId}, size, success, duration, error code). This mirrors the
 * same rule {@code S3AuditLogger} enforces for presigned URLs: what gets
 * logged must never be enough to reconstruct or leak the sensitive
 * material.</p>
 */
@Slf4j
@ApplicationScoped
public class KmsAuditLogger {

    /**
     * Logs a single audited crypto operation.
     *
     * @param event the event to log; if {@code null}, this method returns
     *              silently without throwing, so a missing/misconfigured
     *              audit event never breaks the crypto operation it would
     *              have described.
     */
    public void audit(KmsAuditEvent event) {
        if (event == null) {
            return;
        }
        log.info("kms.audit operation={} keyId={} payloadSize={} success={} durationMs={} errorCode={}",
                event.operation(), event.keyId(), event.payloadSize(), event.successful(),
                event.durationMs(), event.errorCode());
    }
}
