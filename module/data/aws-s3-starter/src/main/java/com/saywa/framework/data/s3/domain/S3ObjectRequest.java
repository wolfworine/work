package com.saywa.framework.data.s3.domain;

import java.util.Map;

/**
 * Request to upload an object to Amazon S3.
 * <p>
 * An immutable record: exposes no setters or mutable state. The canonical
 * constructor validates that {@code bucketName} is not blank, that
 * {@code objectKey} is not blank, and that {@code content} is not null
 * before allowing the instance to be created.
 *
 * @param bucketName  name of the bucket to operate on; must not be null or
 *                    blank — every call must state explicitly which bucket
 *                    it targets, there is no implicit default.
 * @param objectKey   key of the object within the bucket; must not be null
 *                    or blank.
 * @param content     binary content of the object to upload; must not be
 *                    null.
 * @param contentType MIME type of the object, for example
 *                    {@code "application/json"}; may be {@code null} to let
 *                    the SDK detect it automatically.
 * @param metadata    custom metadata to associate with the object; never
 *                    {@code null} (use {@code Map.of()} when there is no
 *                    metadata to send).
 */
public record S3ObjectRequest(String bucketName, String objectKey, byte[] content, String contentType,
                               Map<String, String> metadata) {

    /**
     * Canonical constructor with defensive validation.
     *
     * @throws IllegalArgumentException if {@code bucketName} is null or
     *                                   blank, if {@code objectKey} is null
     *                                   or blank, or if {@code content} is
     *                                   null.
     */
    public S3ObjectRequest {
        if (bucketName == null || bucketName.isBlank()) {
            throw new IllegalArgumentException("bucketName must not be null or blank");
        }
        if (objectKey == null || objectKey.isBlank()) {
            throw new IllegalArgumentException("objectKey must not be null or blank");
        }
        if (content == null) {
            throw new IllegalArgumentException("content must not be null");
        }
    }
}
