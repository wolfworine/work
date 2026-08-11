package com.saywa.framework.data.s3.domain;

import java.time.Instant;

/**
 * Summary of an Amazon S3 object as it appears in a listing
 * ({@code ListObjectsV2}), without downloading its content.
 * <p>
 * An immutable record: exposes no setters or mutable state.
 *
 * @param objectKey    key of the object within the bucket.
 * @param size         size of the object in bytes.
 * @param lastModified instant of the object's last modification, as
 *                      reported by Amazon S3; represented with
 *                      {@link java.time.Instant}.
 */
public record S3ObjectSummary(String objectKey, long size, Instant lastModified) {
}
