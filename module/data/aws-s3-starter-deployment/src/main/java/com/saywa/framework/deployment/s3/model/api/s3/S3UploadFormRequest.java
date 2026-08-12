package com.saywa.framework.deployment.s3.model.api.s3;

import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

/**
 * multipart/form-data form for {@code POST /shrd/s3/bucket} — the
 * in-memory upload mode.
 * <p>
 * Sibling of {@link S3BodyRequest} (the server-side load-by-path upload
 * mode): same {@code bucketName}/{@code objectKey}/{@code contentType}
 * fields, in the same order — they differ only in how the content itself
 * travels ({@code file}, a streamed multipart part, vs
 * {@code objectFileToLoad}, a server-local path). Kept as two separate
 * classes rather than one because RESTEasy Reactive binds them from
 * incompatible media types ({@code multipart/form-data} vs
 * {@code application/json}) and a JSON-deserializable record has no way
 * to carry a {@link FileUpload}.
 * <p>
 * RESTEasy Reactive writes the uploaded file to a temporary file
 * (streaming, it does not load everything into memory during reception)
 * and exposes it as {@link FileUpload}; the content is read into a
 * {@code byte[]} only when building the {@code S3ObjectRequest} for the
 * starter. {@code bucketName} is required, same as every other operation
 * in this module — validated via {@code @Valid} on the controller
 * parameter.
 */
public class S3UploadFormRequest {

    @RestForm
    @PartType(TEXT_PLAIN)
    @NotBlank
    public String bucketName;

    @RestForm
    @PartType(TEXT_PLAIN)
    @NotBlank
    public String objectKey;

    @RestForm("file")
    @NotNull
    public FileUpload file;

    @RestForm
    @PartType(TEXT_PLAIN)
    public String contentType;
}
