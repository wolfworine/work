package com.saywa.framework.data.kms.audit;

import com.saywa.framework.data.kms.domain.KmsOperation;

/**
 * A single audited crypto operation. Never carries plaintext, key
 * material, MAC/signature values, or any other sensitive bytes — see
 * {@link KmsAuditLogger}.
 *
 * @param operation   the operation performed.
 * @param keyId       identifier of the CMK/HMAC key involved.
 * @param payloadSize size, in bytes, of the plaintext or ciphertext involved; never the bytes themselves.
 * @param successful  whether the operation completed successfully.
 * @param durationMs  wall-clock duration of the operation, in milliseconds.
 * @param errorCode   short error classification when {@code successful} is {@code false}; {@code null} otherwise.
 */
public record KmsAuditEvent(
        KmsOperation operation,
        String keyId,
        int payloadSize,
        boolean successful,
        Long durationMs,
        String errorCode) {
}
