package com.saywa.framework.deployment.s3.facade;

import com.saywa.framework.data.s3.domain.S3ObjectContent;
import com.saywa.framework.deployment.s3.model.api.s3.S3BodyRequest;
import com.saywa.framework.deployment.s3.model.api.s3.S3BodyResponse;
import io.smallrye.mutiny.Uni;
import java.util.List;

/**
 * Orchestration layer between {@code expose/web/S3Controller} and
 * {@code service/S3Service}: builds the JSON response DTOs
 * ({@code model/api/s3/}) and logs each operation's outcome. Contains no
 * business logic of its own and never imports
 * {@code com.saywa.framework.data.s3.*} directly except for the domain
 * types it passes through unchanged ({@link S3ObjectContent}, for the
 * binary download response).
 * <p>
 * {@code S3BodyRequest} is the only {@code model/api/s3/} type this
 * interface accepts as a whole parameter ({@code uploadFromPath}) — every
 * other method takes plain values, even where the controller binds a
 * {@code model/api/s3/} type of its own ({@code S3ParameterRequest}): the
 * controller always unpacks it before calling here, so {@code S3Service}
 * and the starter never need to know the shape of the HTTP API.
 */
public interface S3Facade {

    Uni<S3BodyResponse> uploadInMemory(String bucketName, String objectKey, byte[] content, String contentType);

    Uni<S3BodyResponse> uploadFromPath(S3BodyRequest request);

    Uni<S3ObjectContent> download(String bucketName, String objectKey);

    Uni<List<S3BodyResponse>> list(String bucketName, String prefix);

    Uni<Void> delete(String bucketName, String objectKey);

    Uni<S3BodyResponse> copy(String bucketName, String sourceKey, String destinationKey);

    Uni<S3BodyResponse> move(String bucketName, String sourceKey, String destinationKey);

    Uni<Boolean> exists(String bucketName, String objectKey);

    Uni<S3BodyResponse> presigned(String bucketName, String objectKey, Long ttlSeconds);
}
