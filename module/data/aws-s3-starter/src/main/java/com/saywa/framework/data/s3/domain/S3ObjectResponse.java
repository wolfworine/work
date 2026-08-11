package com.saywa.framework.data.s3.domain;

/**
 * Result of a write or transfer operation on an S3 object (for example,
 * after an {@code upload}, {@code copy}, or {@code move}).
 * <p>
 * An immutable record: exposes no setters or mutable state.
 *
 * @param objectKey  key of the object within the bucket the operation was
 *                   performed on.
 * @param bucketName name of the Amazon S3 bucket the object resides in.
 */
public record S3ObjectResponse(String objectKey, String bucketName) {
}
