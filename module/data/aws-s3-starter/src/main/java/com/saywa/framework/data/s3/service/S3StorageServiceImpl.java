package com.saywa.framework.data.s3.service;

import com.saywa.framework.core.error.exceptions.StorageConfigurationException;
import com.saywa.framework.core.error.exceptions.StorageObjectNotFoundException;
import com.saywa.framework.data.s3.audit.S3AuditLogger;
import com.saywa.framework.data.s3.config.S3Configuration;
import com.saywa.framework.data.s3.config.S3ConfigurationValidator;
import com.saywa.framework.data.s3.domain.S3ObjectContent;
import com.saywa.framework.data.s3.domain.S3ObjectRequest;
import com.saywa.framework.data.s3.domain.S3ObjectResponse;
import com.saywa.framework.data.s3.domain.S3ObjectSummary;
import com.saywa.framework.data.s3.domain.S3Operation;
import com.saywa.framework.data.s3.exception.StorageExceptionMapper;
import com.saywa.framework.data.s3.factory.S3RequestFactory;
import com.saywa.framework.data.s3.mapper.S3ResponseMapper;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.Duration;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.CopyObjectResponse;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

/**
 * Default reactive implementation of {@link S3StorageService}, backed by
 * {@link S3AsyncClient} for operations against Amazon S3 and by
 * {@link S3Presigner} for generating presigned URLs.
 * <p>
 * Delegates to {@link S3RequestFactory} the construction of SDK requests,
 * to {@link S3ResponseMapper} the translation of SDK responses into
 * {@code domain/} records, to {@link StorageExceptionMapper} the
 * translation of any SDK failure into the framework's shared storage
 * exception hierarchy, and to {@link S3AuditLogger} the audit logging of
 * each operation. No method of this class may block the calling thread:
 * every interaction with the SDK is adapted to {@link Uni} via
 * {@code Uni.createFrom().completionStage(...)}.
 * <p>
 * {@code bucketName} is required on every operation (see
 * {@link S3StorageService}'s Javadoc): this class never falls back to a
 * configured default bucket, it only uses {@link S3Configuration} for
 * concerns unrelated to bucket selection ({@code default-prefix},
 * upload/download size limits, presigned TTL).
 */
@Slf4j
@ApplicationScoped
public class S3StorageServiceImpl implements S3StorageService {

    private final S3AsyncClient s3AsyncClient;
    private final S3Presigner s3Presigner;
    private final S3Configuration configuration;
    private final S3RequestFactory requestFactory;
    private final S3ResponseMapper responseMapper;
    private final S3AuditLogger auditLogger;
    private final StorageExceptionMapper exceptionMapper;
    private final S3ConfigurationValidator configurationValidator;

    /**
     * Creates the service, injecting all its collaborators via the
     * constructor.
     *
     * @param s3AsyncClient           asynchronous AWS SDK client, managed by
     *                                {@code quarkus-amazon-s3}; never
     *                                {@code null} in a running Quarkus
     *                                container.
     * @param s3Presigner             AWS SDK presigned-URL signer, managed
     *                                by CDI; never instantiated manually
     *                                within a method of this class.
     * @param configuration           the starter's resolved configuration.
     * @param requestFactory          factory for SDK requests built from
     *                                {@code domain/} records.
     * @param responseMapper          mapper from SDK responses to
     *                                {@code domain/} records.
     * @param auditLogger             audit event recorder.
     * @param exceptionMapper         translator of SDK exceptions into the
     *                                shared storage exception hierarchy.
     * @param configurationValidator  validator for {@link S3Configuration},
     *                                invoked in {@link #initialize()}.
     */
    @Inject
    public S3StorageServiceImpl(S3AsyncClient s3AsyncClient,
                                 S3Presigner s3Presigner,
                                 S3Configuration configuration,
                                 S3RequestFactory requestFactory,
                                 S3ResponseMapper responseMapper,
                                 S3AuditLogger auditLogger,
                                 StorageExceptionMapper exceptionMapper,
                                 S3ConfigurationValidator configurationValidator) {
        this.s3AsyncClient = s3AsyncClient;
        this.s3Presigner = s3Presigner;
        this.configuration = configuration;
        this.requestFactory = requestFactory;
        this.responseMapper = responseMapper;
        this.auditLogger = auditLogger;
        this.exceptionMapper = exceptionMapper;
        this.configurationValidator = configurationValidator;
    }

    /**
     * Validates the injected configuration as soon as the bean is
     * constructed, so that an invalid {@link S3Configuration} fails fast at
     * the consuming application's startup instead of manifesting as
     * confusing errors at runtime.
     *
     * @throws IllegalArgumentException if {@link S3ConfigurationValidator#validate()}
     *                                    detects an invalid configuration.
     */
    @PostConstruct
    void initialize() {
        configurationValidator.validate();
        log.info("S3StorageService initialized (default bucket: {})", configuration.bucketName());
    }

    /**
     * {@inheritDoc}
     * <p>
     * Before invoking the SDK, validates that
     * {@code request.content().length} does not exceed
     * {@link S3Configuration#maxUploadSize()}; if it does, the operation
     * fails with {@link StorageConfigurationException} without having
     * contacted Amazon S3, and is audited as a failed {@link S3Operation#PUT}.
     * If the size is valid, delegates to {@link S3AsyncClient#putObject}.
     */
    @Override
    public Uni<S3ObjectResponse> upload(S3ObjectRequest request) {
        long startedAt = System.nanoTime();
        String bucketName = request.bucketName();
        String objectKey = request.objectKey();

        Uni<S3ObjectResponse> uploaded = validateUploadRequest(request)
                .onItem().transformToUni(this::executeUpload);

        return audited(uploaded, S3Operation.PUT, bucketName, objectKey, startedAt);
    }

    /**
     * {@inheritDoc}
     * <p>
     * First performs a {@code headObject} to learn the object's actual size
     * without downloading its content: if {@code contentLength} exceeds
     * {@link S3Configuration#maxDownloadSize()}, the operation fails with
     * {@link StorageConfigurationException} without transferring any bytes.
     * Otherwise, downloads the content with
     * {@code AsyncResponseTransformer.toBytes()}. Both stages are audited
     * as {@link S3Operation#GET}.
     */
    @Override
    public Uni<S3ObjectContent> download(String bucketName, String objectKey) {
        long startedAt = System.nanoTime();
        String normalizedKey = requestFactory.normalizeKey(objectKey);

        Uni<S3ObjectContent> downloaded = validateDownloadPreconditions(bucketName, normalizedKey)
                .onItem().transformToUni(validKey -> executeDownload(bucketName, validKey));

        return audited(downloaded, S3Operation.GET, bucketName, objectKey, startedAt);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Resolves the effective prefix with
     * {@link S3RequestFactory#resolvePrefix(String)} and delegates to
     * {@link S3AsyncClient#listObjectsV2}, translating the response with
     * {@link S3ResponseMapper#toSummaries(software.amazon.awssdk.services.s3.model.ListObjectsV2Response)}.
     * The operation is audited as {@link S3Operation#LIST}, both on success
     * and on failure.
     */
    @Override
    public Uni<List<S3ObjectSummary>> list(String bucketName, String prefix) {
        long startedAt = System.nanoTime();
        String resolvedPrefix = requestFactory.resolvePrefix(prefix);

        Uni<List<S3ObjectSummary>> listed = Uni.createFrom()
                .completionStage(() -> s3AsyncClient.listObjectsV2(requestFactory.createListRequest(bucketName, prefix)))
                .onItem().transform(responseMapper::toSummaries);

        return audited(listed, S3Operation.LIST, bucketName, resolvedPrefix, startedAt);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Delegates to {@link S3AsyncClient#deleteObject}; the SDK response is
     * discarded (Amazon S3 does not distinguish between deleting an
     * existing or a nonexistent key) and the {@link Uni} completes with
     * {@code null} via {@code replaceWithVoid()}. The operation is audited
     * as {@link S3Operation#DELETE}, both on success and on failure.
     */
    @Override
    public Uni<Void> delete(String bucketName, String objectKey) {
        long startedAt = System.nanoTime();
        String normalizedKey = requestFactory.normalizeKey(objectKey);

        Uni<Void> deleted = executeDelete(bucketName, normalizedKey).replaceWithVoid();

        return audited(deleted, S3Operation.DELETE, bucketName, objectKey, startedAt);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Delegates to {@link S3AsyncClient#copyObject} to copy the object
     * server-side, without downloading or re-uploading its content. The
     * operation is audited as {@link S3Operation#COPY} (with the
     * destination key), both on success and on failure.
     */
    @Override
    public Uni<S3ObjectResponse> copy(String bucketName, String sourceKey, String destinationKey) {
        long startedAt = System.nanoTime();
        String normalizedSource = requestFactory.normalizeKey(sourceKey);
        String normalizedDestination = requestFactory.normalizeKey(destinationKey);

        Uni<S3ObjectResponse> copied = executeCopy(bucketName, normalizedSource, normalizedDestination)
                .onItem().transform(ignored -> responseMapper.toResponse(normalizedDestination, bucketName));

        return audited(copied, S3Operation.COPY, bucketName, destinationKey, startedAt);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Implemented as {@link #copy(String, String, String)} followed by
     * {@link #delete(String, String)} on the source key, with no automatic
     * rollback: if the copy fails, {@code delete} is never invoked; if the
     * copy succeeds but {@code delete} fails, the {@code delete} failure is
     * propagated as-is and the copied object is <strong>not</strong>
     * removed from the destination — the object remains present in both
     * the source and the destination, as documented by
     * {@link S3StorageService#move(String, String, String)}.
     */
    @Override
    public Uni<S3ObjectResponse> move(String bucketName, String sourceKey, String destinationKey) {
        return copy(bucketName, sourceKey, destinationKey)
                .chain(response -> delete(bucketName, sourceKey).onItem().transform(ignored -> response));
    }

    /**
     * {@inheritDoc}
     * <p>
     * Delegates to {@link S3AsyncClient#headObject} to check whether the
     * object exists without downloading its content. If the failure is
     * translated (via {@link StorageExceptionMapper#map(Throwable)}) into
     * {@link StorageObjectNotFoundException} (the key doesn't exist), that
     * failure is recovered internally by returning {@code false}: it is
     * never propagated as a failure of the {@link Uni}. Any other failure
     * (for example, a permissions error) is propagated already mapped. The
     * operation is audited as {@link S3Operation#HEAD}, whether the object
     * exists or not, and also on a failure other than "not found" — this
     * three-way branching doesn't fit {@link #audited(Uni, S3Operation, String, String, long)},
     * so it is handled directly here.
     */
    @Override
    public Uni<Boolean> exists(String bucketName, String objectKey) {
        long startedAt = System.nanoTime();
        String normalizedKey = requestFactory.normalizeKey(objectKey);

        return Uni.createFrom()
                .completionStage(() -> s3AsyncClient.headObject(requestFactory.createHeadRequest(bucketName, normalizedKey)))
                .onItem().transform(response -> {
                    auditLogger.success(S3Operation.HEAD, bucketName, objectKey, elapsedMs(startedAt));
                    return Boolean.TRUE;
                })
                .onFailure().recoverWithUni(throwable -> {
                    RuntimeException mappedException = exceptionMapper.map(throwable);
                    if (mappedException instanceof StorageObjectNotFoundException) {
                        auditLogger.success(S3Operation.HEAD, bucketName, objectKey, elapsedMs(startedAt));
                        return Uni.createFrom().item(Boolean.FALSE);
                    }
                    auditLogger.failure(S3Operation.HEAD, bucketName, objectKey, elapsedMs(startedAt), throwable);
                    return Uni.createFrom().failure(mappedException);
                });
    }

    /**
     * {@inheritDoc}
     * <p>
     * First performs a {@code headObject} to distinguish a nonexistent
     * object ({@link StorageObjectNotFoundException}) from any other failure
     * (for example, a permissions error), before signing anything. This
     * check is not audited separately; only the final result of
     * {@code presigned} is (unlike {@link #exists(String, String)}, which is
     * a public operation audited on its own). If the check succeeds,
     * computes the effective TTL ({@code ttl} if not {@code null},
     * otherwise {@link S3Configuration#presignedTtl()}) and builds the
     * signing request with
     * {@link S3RequestFactory#createPresignRequest(String, String, Duration)}.
     * <p>
     * {@code S3Presigner#presignGetObject(GetObjectPresignRequest)} signs
     * the URL locally and synchronously (it makes no network call to
     * Amazon S3), so it is invoked directly inside
     * {@code onItem().transform(...)}, chained after the existence check —
     * the calling thread is never blocked waiting for network I/O. The
     * {@link S3Presigner} used is always the one injected by CDI in the
     * constructor; this method never instantiates it manually.
     * <p>
     * The operation is audited as {@link S3Operation#PRESIGN}, recording
     * only the object key and the effective TTL (in milliseconds, in the
     * audit event's {@code durationMs} field) — the full presigned URL is
     * <strong>never</strong> included in the audit event or logged
     * anywhere. {@code durationMs} here is the TTL, not the elapsed time,
     * so this method doesn't use
     * {@link #audited(Uni, S3Operation, String, String, long)} either.
     */
    @Override
    public Uni<String> presigned(String bucketName, String objectKey, Duration ttl) {
        Duration effectiveTtl = resolveTtl(ttl);
        String normalizedKey = requestFactory.normalizeKey(objectKey);

        return validateObjectExists(bucketName, normalizedKey)
                .onItem().transformToUni(validKey -> executePresign(bucketName, validKey, effectiveTtl))
                .onItem().invoke(url -> auditLogger.success(S3Operation.PRESIGN, bucketName, objectKey, effectiveTtl.toMillis()))
                .onFailure().transform(throwable -> {
                    auditLogger.failure(S3Operation.PRESIGN, bucketName, objectKey, effectiveTtl.toMillis(), throwable);
                    return exceptionMapper.map(throwable);
                });
    }

    // Validation

    private Uni<S3ObjectRequest> validateUploadRequest(S3ObjectRequest request) {
        if (request.content().length > configuration.maxUploadSize()) {
            return Uni.createFrom().failure(new StorageConfigurationException(buildSizeExceedMessage(
                    request.objectKey(), request.content().length, configuration.maxUploadSize(), "UPLOAD"), null));
        }
        return Uni.createFrom().item(request);
    }

    private Uni<String> validateObjectExists(String bucketName, String objectKey) {
        return Uni.createFrom()
                .completionStage(() -> s3AsyncClient.headObject(requestFactory.createHeadRequest(bucketName, objectKey)))
                .onItem().transform(ignored -> objectKey);
    }

    private Uni<String> validateDownloadPreconditions(String bucketName, String objectKey) {
        return Uni.createFrom()
                .completionStage(() -> s3AsyncClient.headObject(requestFactory.createHeadRequest(bucketName, objectKey)))
                .onItem().transformToUni(headResponse -> {
                    long contentLength = headResponse.contentLength() != null ? headResponse.contentLength() : 0L;

                    if (contentLength > configuration.maxDownloadSize()) {
                        return Uni.createFrom().failure(new StorageConfigurationException(buildSizeExceedMessage(
                                objectKey, contentLength, configuration.maxDownloadSize(), "DOWNLOAD"), null));
                    }
                    return Uni.createFrom().item(objectKey);
                });
    }

    // Execution

    private Uni<S3ObjectResponse> executeUpload(S3ObjectRequest request) {
        String normalizedKey = requestFactory.normalizeKey(request.objectKey());

        return Uni.createFrom()
                .completionStage(() -> s3AsyncClient.putObject(
                        requestFactory.createPutRequest(request),
                        requestFactory.createRequestBody(request.content())))
                .onItem().transform(ignored -> responseMapper.toResponse(normalizedKey,
                        request.bucketName()));
    }

    private Uni<S3ObjectContent> executeDownload(String bucketName, String objectKey) {
        return Uni.createFrom()
                .completionStage(() -> s3AsyncClient.getObject(
                        requestFactory.createGetRequest(bucketName, objectKey),
                        AsyncResponseTransformer.toBytes()))
                .onItem().transform(responseMapper::toContent);
    }

    private Uni<DeleteObjectResponse> executeDelete(String bucketName, String objectKey) {
        return Uni.createFrom()
                .completionStage(() -> s3AsyncClient.deleteObject(
                        requestFactory.createDeleteRequest(bucketName, objectKey)));
    }

    private Uni<CopyObjectResponse> executeCopy(String bucketName, String sourceKey, String destinationKey) {
        return Uni.createFrom()
                .completionStage(() -> s3AsyncClient.copyObject(
                        requestFactory.createCopyRequest(bucketName, sourceKey, destinationKey)));
    }

    private Uni<String> executePresign(String bucketName, String objectKey, Duration ttl) {
        GetObjectPresignRequest presignRequest = requestFactory.createPresignRequest(bucketName, objectKey, ttl);

        return Uni.createFrom()
                .item(() -> s3Presigner.presignGetObject(presignRequest).url().toExternalForm());
    }

    // Audit

    /**
     * Wraps {@code uni} with the audit-and-map-exceptions behavior shared by
     * {@link #upload(S3ObjectRequest)}, {@link #download(String, String)},
     * {@link #list(String, String)}, {@link #delete(String, String)}, and
     * {@link #copy(String, String, String)}: on success, audits
     * {@code operation} as successful with the elapsed time since
     * {@code startedAt}; on failure, audits it as failed and replaces the
     * failure with the exception returned by
     * {@link StorageExceptionMapper#map(Throwable)}.
     * <p>
     * Not used by {@link #exists(String, String)} (which has a third "not
     * found" branch that must not be audited as a failure) or
     * {@link #presigned(String, String, Duration)} (whose
     * {@code durationMs} is the effective TTL, not the elapsed time).
     * Both have audit logic specific enough that sharing this helper would
     * obscure more than it simplifies.
     *
     * @param uni        the in-flight operation to audit.
     * @param operation  operation being audited.
     * @param bucketName bucket the operation was performed on.
     * @param objectKey  key (or resolved prefix) affected by the operation.
     * @param startedAt  a {@link System#nanoTime()} timestamp captured at
     *                   the start of the operation.
     * @return {@code uni}, unchanged on success; on failure, its cause
     *         replaced with the exception mapped by {@code exceptionMapper}.
     */
    private <T> Uni<T> audited(Uni<T> uni, S3Operation operation, String bucketName, String objectKey, long startedAt) {
        return uni
                .onItem().invoke(ignored -> auditLogger.success(operation, bucketName, objectKey, elapsedMs(startedAt)))
                .onFailure().transform(throwable -> {
                    auditLogger.failure(operation, bucketName, objectKey, elapsedMs(startedAt), throwable);
                    return exceptionMapper.map(throwable);
                });
    }

    // Utilities

    private Duration resolveTtl(Duration ttl) {
        return ttl == null ? configuration.presignedTtl() : ttl;
    }

    private long elapsedMs(long startedAt) {
        return Duration.ofNanos(System.nanoTime() - startedAt).toMillis();
    }

    private String buildSizeExceedMessage(String objectKey, long actualSize, long maxSize, String operation) {
        return String.format("Object '%s' (%d bytes) exceeds configured max-%s-size (%d bytes)",
                objectKey, actualSize, operation, maxSize);
    }
}
