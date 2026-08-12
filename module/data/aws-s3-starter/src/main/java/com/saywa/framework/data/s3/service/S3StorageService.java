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
 * Behaves like a connection to a database: the connection itself (the
 * underlying {@code S3AsyncClient}/{@code S3Presigner}, credentials,
 * region, timeouts) is established once via {@code S3Configuration}, but
 * every call states explicitly which bucket it operates on — there is no
 * implicit default bucket. {@code bucketName} is therefore a required
 * parameter on every method (or field of {@link S3ObjectRequest} for
 * {@link #upload(S3ObjectRequest)}); passing {@code null} or a blank value
 * fails the operation.
 * <p>
 * Errors are reported as failures of the returned {@link Uni}, using
 * exclusively the framework's shared storage exception hierarchy
 * ({@link StorageException} and its subclasses): never a raw AWS SDK
 * exception.
 */
public interface S3StorageService {

    Uni<S3ObjectResponse> upload(S3ObjectRequest request);

    Uni<S3ObjectContent> download(String bucketName, String objectKey);

    Uni<List<S3ObjectSummary>> list(String bucketName, String prefix);

    Uni<Void> delete(String bucketName, String objectKey);

    Uni<S3ObjectResponse> copy(String bucketName, String sourceKey, String destinationKey);

    Uni<S3ObjectResponse> move(String bucketName, String sourceKey, String destinationKey);

    Uni<Boolean> exists(String bucketName, String objectKey);

    Uni<String> presigned(String bucketName, String objectKey, Duration ttl);
}
