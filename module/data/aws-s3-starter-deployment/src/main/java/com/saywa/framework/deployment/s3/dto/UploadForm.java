package com.saywa.framework.deployment.s3.dto;

import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

/**
 * multipart/form-data form for {@code POST /s3/objects}.
 * <p>
 * RESTEasy Reactive writes the uploaded file to a temporary file
 * (streaming, it does not load everything into memory during reception)
 * and exposes it as {@link FileUpload}; the content is read into a
 * {@code byte[]} only when building the {@code S3ObjectRequest} for the
 * starter.
 */
public class UploadForm {

    @RestForm("file")
    public FileUpload file;

    @RestForm
    @PartType(TEXT_PLAIN)
    public String contentType;
}
