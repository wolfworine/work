package com.saywa.framework.deployment.s3.model.api.s3;

import com.saywa.framework.data.s3.domain.S3ObjectResponse;
import com.saywa.framework.data.s3.domain.S3ObjectSummary;
import java.time.Instant;

/**
 * The single JSON response body for all 9 {@code S3Controller} operations:
 * upload, copy, move (write), presigned-url, and list. Which fields are
 * populated depends on the operation — {@code size}/{@code lastModified}
 * only for list items, {@code url} only for presigned-url, the rest for
 * everything else.
 * <p>
 * Built exclusively through the {@link #from} overloads below, one per
 * source shape, rather than differently-named factory methods per
 * operation (that would just be the old per-operation DTOs again, wrapped
 * in extra steps) — mirrors this module's existing {@code .from(...)}
 * convention for mapping a starter domain type into an API DTO.
 *
 * @param status        HTTP status code of the underlying operation.
 * @param description   short human-readable outcome (for example {@code "OK"}).
 * @param objectKey     key of the object the operation was performed on.
 * @param bucketName    bucket the object resides in; {@code null} for list
 *                      items where the caller already knows it.
 * @param url            presigned URL; {@code null} except for
 *                      {@code presigned-url}.
 * @param size          size in bytes; {@code null} except for list items.
 * @param lastModified  last modification instant; {@code null} except for
 *                      list items.
 */
public record S3BodyResponse(int status, String description, String objectKey, String bucketName, String url,
                              Long size, Instant lastModified) {

    public static S3BodyResponse from(S3ObjectResponse response) {
        return new S3BodyResponse(200, "OK", response.objectKey(), response.bucketName(), null, null, null);
    }

    public static S3BodyResponse from(S3ObjectSummary summary, String bucketName) {
        return new S3BodyResponse(200, "OK", summary.objectKey(), bucketName, null, summary.size(), summary.lastModified());
    }

    public static S3BodyResponse from(String objectKey, String bucketName, String url) {
        return new S3BodyResponse(200, "OK", objectKey, bucketName, url, null, null);
    }
}
