package com.saywa.framework.data.s3.service;

import com.saywa.framework.core.error.exceptions.StorageException;
import com.saywa.framework.data.s3.domain.S3ObjectContent;
import com.saywa.framework.data.s3.domain.S3ObjectRequest;
import com.saywa.framework.data.s3.domain.S3ObjectResponse;
import com.saywa.framework.data.s3.domain.S3ObjectSummary;
import io.smallrye.mutiny.Uni;
import java.time.Duration;
import java.util.List;

/**
 * Public reactive contract for storing and retrieving objects in Amazon S3.
 * <p>
 * It is the only API surface a consuming application of this starter
 * should use: neither this interface nor any of the types that appear in
 * its signatures ({@code domain/}) expose native AWS SDK types. Every
 * operation is asynchronous and non-blocking — all methods return
 * {@link Uni}, never block the calling thread, and must never be resolved
 * synchronously/blockingly within the starter's own implementation.
 * <p>
 * Errors are reported as failures of the returned {@link Uni}, using
 * exclusively the framework's shared storage exception hierarchy
 * ({@link StorageException} and its subclasses): never a raw AWS SDK
 * exception.
 */
public interface S3StorageService {

    Uni<S3ObjectResponse> upload(S3ObjectRequest request);

    Uni<S3ObjectContent> download(String objectKey);

    Uni<List<S3ObjectSummary>> list(String prefix);

    Uni<Void> delete(String objectKey);

    Uni<S3ObjectResponse> copy(String sourceKey, String destinationKey);

    Uni<S3ObjectResponse> move(String sourceKey, String destinationKey);

    Uni<Boolean> exists(String objectKey);

    Uni<String> presigned(String objectKey, Duration ttl);
}
