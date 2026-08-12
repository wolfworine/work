package com.saywa.framework.deployment.s3.facade.impl;

import com.saywa.framework.data.s3.domain.S3ObjectContent;
import com.saywa.framework.deployment.s3.facade.S3Facade;
import com.saywa.framework.deployment.s3.model.api.s3.S3BodyRequest;
import com.saywa.framework.deployment.s3.model.api.s3.S3BodyResponse;
import com.saywa.framework.deployment.s3.service.S3Service;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.Duration;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * Default implementation of {@link S3Facade}. Every method logs its
 * outcome (success/failure) at the facade boundary, on top of the audit
 * logging the starter itself already performs — this is deployment-level
 * request logging, not a replacement for {@code S3AuditLogger}.
 */
@Slf4j
@ApplicationScoped
public class S3FacadeImpl implements S3Facade {

    private final S3Service s3Service;

    @Inject
    public S3FacadeImpl(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @Override
    public Uni<S3BodyResponse> uploadInMemory(String bucketName, String objectKey, byte[] content, String contentType) {
        return s3Service.uploadInMemory(bucketName, objectKey, content, contentType)
                .onItem().transform(S3BodyResponse::from)
                .onItem().invoke(response -> log.info("Uploaded (in-memory) bucket={} objectKey={}", bucketName, objectKey))
                .onFailure().invoke(e -> log.error("Failed to upload (in-memory) bucket={} objectKey={}: {}",
                        bucketName, objectKey, e.getMessage()));
    }

    @Override
    public Uni<S3BodyResponse> uploadFromPath(S3BodyRequest request) {
        return s3Service.uploadFromPath(request.bucketName(), request.objectKey(), request.objectFileToLoad(), request.contentType())
                .onItem().transform(S3BodyResponse::from)
                .onItem().invoke(response -> log.info("Uploaded (from path) bucket={} objectKey={}",
                        request.bucketName(), request.objectKey()))
                .onFailure().invoke(e -> log.error("Failed to upload (from path) bucket={} objectKey={}: {}",
                        request.bucketName(), request.objectKey(), e.getMessage()));
    }

    @Override
    public Uni<S3ObjectContent> download(String bucketName, String objectKey) {
        return s3Service.download(bucketName, objectKey);
    }

    @Override
    public Uni<List<S3BodyResponse>> list(String bucketName, String prefix) {
        return s3Service.list(bucketName, prefix)
                .onItem().transform(summaries -> summaries.stream()
                        .map(summary -> S3BodyResponse.from(summary, bucketName))
                        .toList());
    }

    @Override
    public Uni<Void> delete(String bucketName, String objectKey) {
        return s3Service.delete(bucketName, objectKey);
    }

    @Override
    public Uni<S3BodyResponse> copy(String bucketName, String sourceKey, String destinationKey) {
        return s3Service.copy(bucketName, sourceKey, destinationKey)
                .onItem().transform(S3BodyResponse::from);
    }

    @Override
    public Uni<S3BodyResponse> move(String bucketName, String sourceKey, String destinationKey) {
        return s3Service.move(bucketName, sourceKey, destinationKey)
                .onItem().transform(S3BodyResponse::from);
    }

    @Override
    public Uni<Boolean> exists(String bucketName, String objectKey) {
        return s3Service.exists(bucketName, objectKey);
    }

    @Override
    public Uni<S3BodyResponse> presigned(String bucketName, String objectKey, Long ttlSeconds) {
        Duration ttl = ttlSeconds != null ? Duration.ofSeconds(ttlSeconds) : null;

        return s3Service.presigned(bucketName, objectKey, ttl)
                .onItem().transform(url -> S3BodyResponse.from(objectKey, bucketName, url));
    }
}
