package com.saywa.framework.deployment.s3.service.impl;

import com.saywa.framework.core.error.exceptions.StorageConnectionException;
import com.saywa.framework.data.s3.domain.S3ObjectContent;
import com.saywa.framework.data.s3.domain.S3ObjectRequest;
import com.saywa.framework.data.s3.domain.S3ObjectResponse;
import com.saywa.framework.data.s3.domain.S3ObjectSummary;
import com.saywa.framework.data.s3.service.S3StorageService;
import com.saywa.framework.deployment.s3.service.S3Service;
import com.saywa.framework.deployment.s3.service.S3UploadPathResolver;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.Retry;

/**
 * Default implementation of {@link S3Service}. Injects
 * {@link S3StorageService} directly — the starter's public contract is
 * already the "S3 client" this class needs, the same way a database
 * starter's consumer injects a {@code DataSource} rather than reinventing
 * one.
 * <p>
 * The class-level {@code @Retry} applies to every business method (all 8
 * share the exact same policy — MicroProfile Fault Tolerance lets a
 * fault-tolerance annotation be placed once at the class level instead of
 * repeated on each method): up to 3 retries, 200ms apart, on
 * {@link StorageConnectionException} only. A transient network problem
 * talking to S3/LocalStack is worth retrying, but a genuine business
 * outcome ({@link com.saywa.framework.core.error.exceptions.StorageObjectNotFoundException},
 * {@link com.saywa.framework.core.error.exceptions.StorageAccessDeniedException},
 * {@link com.saywa.framework.core.error.exceptions.StorageConfigurationException},
 * or a request-validation failure) never is: retrying a 404 or a 403
 * doesn't change the outcome, it just delays the response. SmallRye Fault
 * Tolerance supports {@code @Retry} on {@link Uni}-returning methods
 * natively — it doesn't block the event loop.
 */
@Slf4j
@ApplicationScoped
@Retry(retryOn = StorageConnectionException.class, maxRetries = 3, delay = 200, delayUnit = ChronoUnit.MILLIS)
public class S3ServiceImpl implements S3Service {

    private final S3StorageService s3StorageService;
    private final S3UploadPathResolver uploadPathResolver;

    @Inject
    public S3ServiceImpl(S3StorageService s3StorageService, S3UploadPathResolver uploadPathResolver) {
        this.s3StorageService = s3StorageService;
        this.uploadPathResolver = uploadPathResolver;
    }

    @Override
    public Uni<S3ObjectResponse> uploadInMemory(String bucketName, String objectKey, byte[] content, String contentType) {
        S3ObjectRequest request = new S3ObjectRequest(bucketName, objectKey, content, contentType, Map.of());
        return s3StorageService.upload(request);
    }

    /**
     * Reads {@code objectFileToLoad} from the server's local filesystem
     * (blocking I/O, dispatched to the default worker pool so the event
     * loop is never blocked) and uploads it through the same
     * {@link S3StorageService#upload} the in-memory mode uses.
     * {@link S3UploadPathResolver#resolve(String)} is what actually
     * enforces that the requested path cannot escape the configured
     * upload base directory.
     * <p>
     * Retried independently of {@link #uploadInMemory}: the call below is
     * a same-class invocation, so it bypasses the CDI interceptor that
     * powers the class-level {@code @Retry} for that method — this
     * method's own (identical) retry is what actually covers the upload
     * it triggers.
     */
    @Override
    public Uni<S3ObjectResponse> uploadFromPath(String bucketName, String objectKey, String objectFileToLoad, String contentType) {
        return Uni.createFrom().item(() -> readFile(objectFileToLoad))
                .runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
                .onItem().transformToUni(content -> uploadInMemory(bucketName, objectKey, content, contentType));
    }

    private byte[] readFile(String objectFileToLoad) {
        try {
            return Files.readAllBytes(uploadPathResolver.resolve(objectFileToLoad));
        } catch (IOException e) {
            throw new UncheckedIOException("Unable to read '" + objectFileToLoad + "' for upload", e);
        }
    }

    @Override
    public Uni<S3ObjectContent> download(String bucketName, String objectKey) {
        return s3StorageService.download(bucketName, objectKey);
    }

    @Override
    public Uni<List<S3ObjectSummary>> list(String bucketName, String prefix) {
        return s3StorageService.list(bucketName, prefix);
    }

    @Override
    public Uni<Void> delete(String bucketName, String objectKey) {
        return s3StorageService.delete(bucketName, objectKey);
    }

    @Override
    public Uni<S3ObjectResponse> copy(String bucketName, String sourceKey, String destinationKey) {
        return s3StorageService.copy(bucketName, sourceKey, destinationKey);
    }

    @Override
    public Uni<S3ObjectResponse> move(String bucketName, String sourceKey, String destinationKey) {
        return s3StorageService.move(bucketName, sourceKey, destinationKey);
    }

    @Override
    public Uni<Boolean> exists(String bucketName, String objectKey) {
        return s3StorageService.exists(bucketName, objectKey);
    }

    @Override
    public Uni<String> presigned(String bucketName, String objectKey, Duration ttl) {
        return s3StorageService.presigned(bucketName, objectKey, ttl);
    }
}
