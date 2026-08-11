package com.saywa.framework.data.s3.domain;

/**
 * Binary content downloaded from an Amazon S3 object, together with its
 * MIME type.
 * <p>
 * An immutable record: exposes no setters or mutable state.
 *
 * @param content     binary content downloaded from the object; may be an
 *                     empty array but never {@code null}.
 * @param contentType MIME type reported by S3 for the object, for example
 *                     {@code "application/pdf"}; may be {@code null} if the
 *                     object has no associated content-type.
 */
public record S3ObjectContent(byte[] content, String contentType) {
}
