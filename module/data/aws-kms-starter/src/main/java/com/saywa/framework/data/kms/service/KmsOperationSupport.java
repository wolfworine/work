package com.saywa.framework.data.kms.service;

import com.saywa.framework.core.error.exceptions.CryptoConfigurationException;
import com.saywa.framework.data.kms.audit.KmsAuditEvent;
import com.saywa.framework.data.kms.audit.KmsAuditLogger;
import com.saywa.framework.data.kms.config.KmsConfiguration;
import com.saywa.framework.data.kms.config.KmsConfigurationValidator;
import com.saywa.framework.data.kms.domain.KmsOperation;
import com.saywa.framework.data.kms.exception.KmsExceptionMapper;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;

/**
 * Collaborators and behavior shared by {@code KmsCryptoServiceImpl},
 * {@code KmsKeyWrapServiceImpl}, and {@code KmsSignatureServiceImpl} (in
 * {@code service.impl}): configuration access, request-size validation,
 * and the audit-and-map-exceptions wrapper every operation of the three
 * services applies to its {@code Uni}. An internal collaborator — not meant
 * to be injected by consuming applications — but public, with public
 * members, so {@code service.impl} and unit tests can use it the same way
 * they use {@code KmsRequestFactory} and {@code KmsResponseMapper}.
 */
@Slf4j
@ApplicationScoped
public class KmsOperationSupport {

    private final KmsConfiguration configuration;
    private final KmsAuditLogger auditLogger;
    private final KmsExceptionMapper exceptionMapper;
    private final KmsConfigurationValidator configurationValidator;

    @Inject
    public KmsOperationSupport(KmsConfiguration configuration,
                         KmsAuditLogger auditLogger,
                         KmsExceptionMapper exceptionMapper,
                         KmsConfigurationValidator configurationValidator) {
        this.configuration = configuration;
        this.auditLogger = auditLogger;
        this.exceptionMapper = exceptionMapper;
        this.configurationValidator = configurationValidator;
    }

    /**
     * Validates the injected configuration as soon as this bean is
     * constructed, so that an invalid {@link KmsConfiguration} fails fast
     * at the consuming application's startup instead of manifesting as
     * confusing errors at runtime.
     *
     * @throws IllegalArgumentException if {@link KmsConfigurationValidator#validate()}
     *                                   detects an invalid configuration.
     */
    @PostConstruct
    void initialize() {
        configurationValidator.validate();
        log.info("KMS crypto services initialized");
    }

    public KmsConfiguration configuration() {
        return configuration;
    }

    /**
     * @return {@code request}, unchanged, if {@code actualSize} does not
     *         exceed {@code maxSize}; otherwise a failed {@code Uni} carrying
     *         a {@code CryptoConfigurationException}, raised before any call
     *         reaches KMS.
     */
    public <T> Uni<T> validateSize(T request, int actualSize, int maxSize, String subject, String operation) {
        if (actualSize > maxSize) {
            return Uni.createFrom().failure(new CryptoConfigurationException(String.format(
                    "%s (%d bytes) exceeds configured max size for %s (%d bytes)",
                    subject, actualSize, operation, maxSize), null));
        }
        return Uni.createFrom().item(request);
    }

    /**
     * Wraps {@code uni} with the audit-and-map-exceptions behavior shared by
     * every operation of the three KMS services: on success, audits
     * {@code operation} as successful with the elapsed time since
     * {@code startedAt}; on failure, audits it as failed and replaces the
     * failure with the exception returned by {@link KmsExceptionMapper#map(Throwable)}.
     * Never includes plaintext, key material, or MAC/signature values in the
     * audit event — only {@code keyId} and {@code payloadSize}.
     *
     * @param uni         the in-flight operation to audit.
     * @param operation   operation being audited.
     * @param keyId       key reference involved.
     * @param payloadSize size, in bytes, of the plaintext or ciphertext involved.
     * @param startedAt   a {@link System#nanoTime()} timestamp captured at the start of the operation.
     * @return {@code uni}, unchanged on success; on failure, its cause
     *         replaced with the exception mapped by {@code exceptionMapper}.
     */
    public <T> Uni<T> audited(Uni<T> uni, KmsOperation operation, String keyId, int payloadSize, long startedAt) {
        if (!configuration.auditEnabled()) {
            return uni.onFailure().transform(exceptionMapper::map);
        }
        return uni
                .onItem().invoke(ignored -> auditLogger.audit(
                        new KmsAuditEvent(operation, keyId, payloadSize, true, elapsedMs(startedAt), null)))
                .onFailure().transform(throwable -> {
                    RuntimeException mapped = exceptionMapper.map(throwable);
                    auditLogger.audit(new KmsAuditEvent(operation, keyId, payloadSize, false,
                            elapsedMs(startedAt), mapped.getClass().getSimpleName()));
                    return mapped;
                });
    }

    private long elapsedMs(long startedAt) {
        return Duration.ofNanos(System.nanoTime() - startedAt).toMillis();
    }
}
