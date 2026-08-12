package com.saywa.framework.deployment.s3.model.api.s3;

import jakarta.validation.constraints.NotBlank;

/**
 * JSON body for {@code POST /shrd/s3/bucket/path} — the server-side
 * load-by-path upload mode: no file bytes travel over HTTP, the app reads
 * {@code objectFileToLoad} itself from its configured upload base
 * directory.
 * <p>
 * Sibling of {@link S3UploadFormRequest} (the in-memory multipart upload mode) —
 * see its Javadoc for why they're two classes instead of one.
 * <p>
 * Validated by Jakarta Bean Validation ({@code @Valid} on the controller
 * parameter) — no defensive validation in the canonical constructor: this
 * is a pure API DTO deserialized by Jackson before {@code @Valid} runs, so
 * a throwing constructor would preempt Bean Validation and produce a
 * less-informative error than {@code ConstraintViolationException}. Unlike
 * the starter's {@code S3ObjectRequest}, which self-validates because it's
 * a library type constructed directly by arbitrary Java callers, not only
 * through HTTP.
 *
 * @param bucketName       name of the bucket to upload to; must not be
 *                         null or blank.
 * @param objectKey        key of the object within the bucket; must not be
 *                         null or blank.
 * @param objectFileToLoad path of the file to load, resolved against the
 *                         configured {@code deployment.s3.upload-base-dir}
 *                         (a bare filename is enough when the file lives
 *                         directly in that directory); must not be null or
 *                         blank.
 * @param contentType      MIME type of the object; may be {@code null} to
 *                         let the starter detect it from
 *                         {@code objectKey}'s extension.
 */
public record S3BodyRequest(
        @NotBlank String bucketName,
        @NotBlank String objectKey,
        @NotBlank String objectFileToLoad,
        String contentType) {
}
