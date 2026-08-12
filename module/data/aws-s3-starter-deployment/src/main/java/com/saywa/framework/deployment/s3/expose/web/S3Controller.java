package com.saywa.framework.deployment.s3.expose.web;

import com.saywa.framework.deployment.s3.facade.S3Facade;
import com.saywa.framework.deployment.s3.model.api.s3.S3BodyRequest;
import com.saywa.framework.deployment.s3.model.api.s3.S3BodyResponse;
import com.saywa.framework.deployment.s3.model.api.s3.S3ParameterRequest;
import com.saywa.framework.deployment.s3.model.api.s3.S3TransferParameterRequest;
import com.saywa.framework.deployment.s3.model.api.s3.S3UploadFormRequest;
import com.saywa.framework.deployment.s3.model.api.s3.error.SaywaErrorResponse;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 * Reactive REST resource that exposes the operations of
 * {@code S3StorageService} (via {@link S3Facade}) over HTTP: to validate
 * the {@code aws-s3-starter} starter against LocalStack, and as the API
 * other services consume for S3 storage.
 * <p>
 * {@code bucketName} is required on every operation — there is no implicit
 * default bucket (see {@link S3Facade}) — and validated with Jakarta Bean
 * Validation (missing/blank fields fail with {@code ConstraintViolationException},
 * mapped to 400 by {@code S3ExceptionMappers}). Endpoints with real content
 * to transport ({@code uploadFromPath}) take a validated JSON body;
 * endpoints that only need identifiers take {@code @QueryParam}/{@code @RestPath},
 * grouped into a shared {@code @BeanParam} where several endpoints share
 * the exact same shape ({@link S3ParameterRequest}).
 * <p>
 * This resource does NOT reimplement S3 business logic: it only adapts
 * HTTP &lt;-&gt; {@link S3Facade} and never blocks the request thread (the
 * entire flow is chained {@link Uni}s).
 * <p>
 * Documented with MicroProfile OpenAPI annotations (see
 * {@code /q/openapi} and {@code /q/swagger-ui/}).
 * <p>
 * Each method logs its own request arrival ({@code log.info}, entry
 * point) — a different observability layer from {@code S3Facade}'s
 * outcome logging (success/failure of the operation) and the starter's
 * own {@code S3AuditLogger} (structured audit trail of what actually
 * happened against S3): entry logging here answers "did a request for
 * this operation arrive at all", independent of whether it ever
 * completed.
 */
@Slf4j
@Path("/shrd/s3")
@Tag(name = "S3 Objects", description = "Operations on S3 objects, delegated to S3StorageService (aws-s3-starter) against LocalStack.")
public class S3Controller {

    private final S3Facade s3Facade;

    public S3Controller(S3Facade s3Facade) {
        this.s3Facade = s3Facade;
    }

    /**
     * Uploads an object via multipart/form-data (in-memory mode).
     * E.g.: {@code curl -F "file=@readme.md" -F "bucketName=my-bucket" -F "objectKey=docs/readme.md" "http://localhost:8080/shrd/s3/bucket"}
     */
    @POST
    @Path("/bucket")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Upload an object (in-memory)", description = "Uploads the multipart form content to the given bucket.")
    @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = S3UploadFormRequest.class)))
    @APIResponse(responseCode = "200", description = "Object uploaded", content = @Content(schema = @Schema(implementation = S3BodyResponse.class)))
    @APIResponse(responseCode = "400", description = "Missing/invalid bucketName or objectKey", content = @Content(schema = @Schema(implementation = SaywaErrorResponse.class)))
    public Uni<S3BodyResponse> uploadInMemory(@Valid S3UploadFormRequest form) {
        log.info("[START] uploadInMemory bucketName={} objectKey={}", form.bucketName, form.objectKey);
        return readBytes(form)
                .chain(content -> {
                    String contentType = form.contentType != null ? form.contentType : form.file.contentType();
                    return s3Facade.uploadInMemory(form.bucketName, form.objectKey, content, contentType);
                });
    }

    /**
     * Uploads an object by loading it from a path on the server's local
     * filesystem (no file bytes travel over HTTP).
     */
    @POST
    @Path("/bucket/path")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Upload an object (server-side load-by-path)", description = "Reads objectFileToLoad from the server's configured upload base directory and uploads it to the given bucket.")
    @APIResponse(responseCode = "200", description = "Object uploaded", content = @Content(schema = @Schema(implementation = S3BodyResponse.class)))
    @APIResponse(responseCode = "400", description = "Missing/invalid fields, or objectFileToLoad escapes the configured base directory", content = @Content(schema = @Schema(implementation = SaywaErrorResponse.class)))
    public Uni<S3BodyResponse> uploadFromPath(@Valid S3BodyRequest request) {
        log.info("[START] uploadFromPath bucketName={} objectKey={}", request.bucketName(), request.objectKey());
        return s3Facade.uploadFromPath(request);
    }

    /** Downloads the content of an object. Returns 404 if it does not exist (see S3ExceptionMappers). */
    @GET
    @Path("/bucket/{objectKey:.+}")
    @Operation(summary = "Download an object", description = "Returns the raw content of the object with its original content-type.")
    @Parameter(name = "objectKey", in = ParameterIn.PATH, required = true, description = "Key of the object", example = "docs/informe.pdf")
    @Parameter(name = "bucketName", in = ParameterIn.QUERY, required = true, description = "Bucket to download from")
    @APIResponse(responseCode = "200", description = "Object content", content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM, schema = @Schema(type = SchemaType.STRING, format = "binary")))
    @APIResponse(responseCode = "404", description = "The object does not exist", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = SaywaErrorResponse.class)))
    public Uni<Response> download(@Valid @BeanParam S3ParameterRequest params) {
        log.info("[START] download bucketName={} objectKey={}", params.bucketName, params.objectKey);
        return s3Facade.download(params.bucketName, params.objectKey)
                .map(content -> Response
                        .ok(content.content())
                        .type(content.contentType() != null ? content.contentType() : MediaType.APPLICATION_OCTET_STREAM)
                        .build());
    }

    /** Lists objects under a prefix. */
    @GET
    @Path("/bucket")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List objects", description = "Lists objects in the given bucket whose key starts with `prefix`.")
    @Parameter(name = "bucketName", in = ParameterIn.QUERY, required = true, description = "Bucket to list")
    @Parameter(name = "prefix", in = ParameterIn.QUERY, description = "Explicit filtering prefix", example = "docs/")
    @APIResponse(responseCode = "200", description = "Objects found (may be empty)", content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = S3BodyResponse.class)))
    public Uni<List<S3BodyResponse>> list(@QueryParam("bucketName") @NotBlank String bucketName, @QueryParam("prefix") String prefix) {
        log.info("[START] list bucketName={} prefix={}", bucketName, prefix);
        return s3Facade.list(bucketName, prefix);
    }

    /** Deletes an object. Idempotent: 204 whether it existed or not. */
    @DELETE
    @Path("/bucket/{objectKey:.+}")
    @Operation(summary = "Delete an object", description = "Idempotent: responds 204 whether the object existed or not.")
    @Parameter(name = "objectKey", in = ParameterIn.PATH, required = true, description = "Key of the object")
    @Parameter(name = "bucketName", in = ParameterIn.QUERY, required = true, description = "Bucket to delete from")
    @APIResponse(responseCode = "204", description = "Deleted (or did not exist)")
    public Uni<Response> delete(@Valid @BeanParam S3ParameterRequest params) {
        log.info("[START] delete bucketName={} objectKey={}", params.bucketName, params.objectKey);
        return s3Facade.delete(params.bucketName, params.objectKey)
                .map(ignored -> Response.noContent().build());
    }

    /** Server-side copy of an object to another key within the same bucket. */
    @POST
    @Path("/bucket/{objectKey:.+}/copy")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Copy an object", description = "Server-side copy (without downloading/re-uploading) to another key within the same bucket.")
    @Parameter(name = "objectKey", in = ParameterIn.PATH, required = true, description = "Key of the source object")
    @Parameter(name = "bucketName", in = ParameterIn.QUERY, required = true, description = "Bucket for both source and destination")
    @Parameter(name = "destinationKey", in = ParameterIn.QUERY, required = true, description = "Key of the destination object", example = "docs/informe-copia.pdf")
    @APIResponse(responseCode = "200", description = "Object copied", content = @Content(schema = @Schema(implementation = S3BodyResponse.class)))
    @APIResponse(responseCode = "404", description = "The source object does not exist", content = @Content(schema = @Schema(implementation = SaywaErrorResponse.class)))
    public Uni<S3BodyResponse> copy(@Valid @BeanParam S3TransferParameterRequest params) {
        log.info("[START] copy bucketName={} objectKey={} destinationKey={}", params.bucketName, params.objectKey, params.destinationKey);
        return s3Facade.copy(params.bucketName, params.objectKey, params.destinationKey);
    }

    /**
     * Moves an object (copy + conditional delete). No automatic rollback:
     * see the Javadoc of {@code S3StorageService#move}.
     */
    @POST
    @Path("/bucket/{objectKey:.+}/move")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Move an object", description = "Copy + conditional delete. No automatic rollback if the source delete fails after the copy.")
    @Parameter(name = "objectKey", in = ParameterIn.PATH, required = true, description = "Key of the source object")
    @Parameter(name = "bucketName", in = ParameterIn.QUERY, required = true, description = "Bucket for both source and destination")
    @Parameter(name = "destinationKey", in = ParameterIn.QUERY, required = true, description = "Key of the destination object", example = "docs/informe-movido.pdf")
    @APIResponse(responseCode = "200", description = "Object moved", content = @Content(schema = @Schema(implementation = S3BodyResponse.class)))
    @APIResponse(responseCode = "404", description = "The source object does not exist", content = @Content(schema = @Schema(implementation = SaywaErrorResponse.class)))
    public Uni<S3BodyResponse> move(@Valid @BeanParam S3TransferParameterRequest params) {
        log.info("[START] move bucketName={} objectKey={} destinationKey={}", params.bucketName, params.objectKey, params.destinationKey);
        return s3Facade.move(params.bucketName, params.objectKey, params.destinationKey);
    }

    /** Indicates whether an object with the given key exists, without downloading its content. */
    @GET
    @Path("/bucket/{objectKey:.+}/exists")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Check whether an object exists", description = "Logical HEAD: does not download content, only confirms whether the key exists in the bucket.")
    @Parameter(name = "objectKey", in = ParameterIn.PATH, required = true, description = "Key of the object")
    @Parameter(name = "bucketName", in = ParameterIn.QUERY, required = true, description = "Bucket to check")
    @APIResponse(responseCode = "200", description = "true if it exists, false otherwise", content = @Content(schema = @Schema(type = SchemaType.BOOLEAN)))
    public Uni<Boolean> exists(@Valid @BeanParam S3ParameterRequest params) {
        log.info("[START] exists bucketName={} objectKey={}", params.bucketName, params.objectKey);
        return s3Facade.exists(params.bucketName, params.objectKey);
    }

    /** Generates a read presigned URL. {@code ttlSeconds} is optional (uses the starter's default TTL). */
    @GET
    @Path("/bucket/{objectKey:.+}/presigned-url")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Generate a read presigned URL", description = "Temporary URL signed by the SDK that allows downloading the object without credentials, valid for ttlSeconds (or the starter's default TTL if omitted).")
    @Parameter(name = "objectKey", in = ParameterIn.PATH, required = true, description = "Key of the object")
    @Parameter(name = "bucketName", in = ParameterIn.QUERY, required = true, description = "Bucket the object resides in")
    @Parameter(name = "ttlSeconds", in = ParameterIn.QUERY, description = "Validity of the URL in seconds", example = "300")
    @APIResponse(responseCode = "200", description = "Presigned URL", content = @Content(schema = @Schema(implementation = S3BodyResponse.class)))
    @APIResponse(responseCode = "404", description = "The object does not exist", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = SaywaErrorResponse.class)))
    public Uni<S3BodyResponse> presigned(@Valid @BeanParam S3ParameterRequest params) {
        // Never log the resulting URL (see S3Facade/S3AuditLogger) — only the request identifiers.
        log.info("[START] presigned bucketName={} objectKey={} ttlSeconds={}", params.bucketName, params.objectKey, params.ttlSeconds);
        return s3Facade.presigned(params.bucketName, params.objectKey, params.ttlSeconds);
    }

    /**
     * Reads the multipart's temporary file into memory on the worker pool,
     * so as not to block the event loop with synchronous disk I/O.
     */
    private Uni<byte[]> readBytes(S3UploadFormRequest form) {
        return Uni.createFrom().item(() -> readUploadedFileBytes(form))
                .runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }

    private byte[] readUploadedFileBytes(S3UploadFormRequest form) {
        try {
            return Files.readAllBytes(form.file.uploadedFile());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
