package com.saywa.framework.deployment.s3.service;

import com.saywa.framework.data.s3.domain.S3ObjectContent;
import com.saywa.framework.data.s3.domain.S3ObjectResponse;
import com.saywa.framework.data.s3.domain.S3ObjectSummary;
import com.saywa.framework.data.s3.service.S3StorageService;
import io.smallrye.mutiny.Uni;
import java.time.Duration;
import java.util.List;

/**
 * Deployment-level operations on S3 objects, backed directly by the
 * starter's {@link S3StorageService}. This is the only interface in this
 * module that imports {@code com.saywa.framework.data.s3.*} — everything
 * above it ({@code facade/}, {@code expose/web/}) works exclusively with
 * this module's own {@code model/api/s3/} DTOs.
 * <p>
 * Adds one deployment-only capability the starter doesn't need to know
 * about: {@link #uploadFromPath(String, String, String, String)} reads a
 * file from the server's local filesystem (validated against a configured
 * base directory) and uploads its bytes through the same
 * {@link S3StorageService#upload} used by the in-memory mode.
 */
public interface S3Service {

    Uni<S3ObjectResponse> uploadInMemory(String bucketName, String objectKey, byte[] content, String contentType);

    Uni<S3ObjectResponse> uploadFromPath(String bucketName, String objectKey, String objectFileToLoad, String contentType);

    Uni<S3ObjectContent> download(String bucketName, String objectKey);

    Uni<List<S3ObjectSummary>> list(String bucketName, String prefix);

    Uni<Void> delete(String bucketName, String objectKey);

    Uni<S3ObjectResponse> copy(String bucketName, String sourceKey, String destinationKey);

    Uni<S3ObjectResponse> move(String bucketName, String sourceKey, String destinationKey);

    Uni<Boolean> exists(String bucketName, String objectKey);

    Uni<String> presigned(String bucketName, String objectKey, Duration ttl);
}
