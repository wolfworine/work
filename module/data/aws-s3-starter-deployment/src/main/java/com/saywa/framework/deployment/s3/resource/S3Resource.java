package com.saywa.framework.deployment.s3.resource;

import com.saywa.framework.data.s3.domain.S3ObjectRequest;
import com.saywa.framework.data.s3.service.S3StorageService;
import com.saywa.framework.deployment.s3.dto.ErrorResponseDto;
import com.saywa.framework.deployment.s3.dto.S3ObjectResponseDto;
import com.saywa.framework.deployment.s3.dto.S3ObjectSummaryDto;
import com.saywa.framework.deployment.s3.dto.UploadForm;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
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
import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestPath;

/**
 * Reactive REST resource that exposes the 8 operations of
 * {@link S3StorageService} over HTTP, to validate the {@code
 * aws-s3-starter} starter against LocalStack.
 * <p>
 * This resource does NOT reimplement S3 business logic: it only adapts HTTP
 * &lt;-&gt; {@link S3StorageService} and never blocks the request thread
 * (the entire flow is chained {@link Uni}s).
 * <p>
 * Documented with MicroProfile OpenAPI annotations (see
 * {@code /q/openapi} and {@code /q/swagger-ui/}) so the endpoints can be
 * tested from Swagger UI or the specification imported into Postman
 * without depending on the LocalStack Web App.
 */
@Path("/s3/objects")
@Tag(name = "S3 Objects", description = "Operations on S3 objects, delegated to S3StorageService (aws-s3-starter) against LocalStack.")
public class S3Resource {

    private final S3StorageService s3StorageService;

    public S3Resource(S3StorageService s3StorageService) {
        this.s3StorageService = s3StorageService;
    }

    /**
     * Uploads an object via multipart/form-data.
     * E.g.: {@code curl -F "file=@readme.md" "http://localhost:8080/s3/objects?objectKey=docs/readme.md"}
     */
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Upload an object", description = "Uploads the multipart form content to the configured bucket. The starter's default-prefix, if configured, is prepended to objectKey.")
    @Parameter(name = "objectKey", in = ParameterIn.QUERY, required = true, description = "Logical key of the object within the bucket", example = "docs/informe.pdf")
    @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = UploadForm.class)))
    @APIResponse(responseCode = "200", description = "Object uploaded", content = @Content(schema = @Schema(implementation = S3ObjectResponseDto.class)))
    @APIResponse(responseCode = "400", description = "Invalid objectKey or invalid starter configuration", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    public Uni<S3ObjectResponseDto> upload(@QueryParam("objectKey") String objectKey, UploadForm form) {
        return readBytes(form)
                .chain(content -> {
                    String contentType = form.contentType != null
                            ? form.contentType
                            : form.file.contentType();
                    S3ObjectRequest request = new S3ObjectRequest(objectKey, content, contentType, Map.of());
                    return s3StorageService.upload(request);
                })
                .map(S3ObjectResponseDto::from);
    }

    /** Downloads the content of an object. Returns 404 if it does not exist (see S3ExceptionMappers). */
    @GET
    @Path("/{objectKey:.+}")
    @Operation(summary = "Download an object", description = "Returns the raw content of the object with its original content-type.")
    @Parameter(name = "objectKey", in = ParameterIn.PATH, required = true, description = "Key of the object", example = "docs/informe.pdf")
    @APIResponse(responseCode = "200", description = "Object content", content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM, schema = @Schema(type = SchemaType.STRING, format = "binary")))
    @APIResponse(responseCode = "404", description = "The object does not exist", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponseDto.class)))
    public Uni<Response> download(@RestPath String objectKey) {
        return s3StorageService.download(objectKey)
                .map(content -> Response
                        .ok(content.content())
                        .type(content.contentType() != null ? content.contentType() : MediaType.APPLICATION_OCTET_STREAM)
                        .build());
    }

    /** Lists objects under a prefix (or the starter's default prefix if omitted). */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List objects", description = "Lists objects whose key starts with `prefix`. If omitted, uses the configured default-prefix; if passed explicitly, it is used as-is (without prepending the default-prefix).")
    @Parameter(name = "prefix", in = ParameterIn.QUERY, description = "Explicit filtering prefix", example = "docs/")
    @APIResponse(responseCode = "200", description = "Objects found (may be empty)", content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = S3ObjectSummaryDto.class)))
    public Uni<List<S3ObjectSummaryDto>> list(@QueryParam("prefix") String prefix) {
        return s3StorageService.list(prefix)
                .map(summaries -> summaries.stream().map(S3ObjectSummaryDto::from).toList());
    }

    /** Deletes an object. Idempotent: 204 whether it existed or not. */
    @DELETE
    @Path("/{objectKey:.+}")
    @Operation(summary = "Delete an object", description = "Idempotent: responds 204 whether the object existed or not.")
    @Parameter(name = "objectKey", in = ParameterIn.PATH, required = true, description = "Key of the object")
    @APIResponse(responseCode = "204", description = "Deleted (or did not exist)")
    public Uni<Response> delete(@RestPath String objectKey) {
        return s3StorageService.delete(objectKey)
                .map(ignored -> Response.noContent().build());
    }

    /** Server-side copy of an object to another key within the same bucket. */
    @POST
    @Path("/{objectKey:.+}/copy")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Copy an object", description = "Server-side copy (without downloading/re-uploading) to another key within the same bucket.")
    @Parameter(name = "objectKey", in = ParameterIn.PATH, required = true, description = "Key of the source object")
    @Parameter(name = "destinationKey", in = ParameterIn.QUERY, required = true, description = "Key of the destination object", example = "docs/informe-copia.pdf")
    @APIResponse(responseCode = "200", description = "Object copied", content = @Content(schema = @Schema(implementation = S3ObjectResponseDto.class)))
    @APIResponse(responseCode = "404", description = "The source object does not exist", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    public Uni<S3ObjectResponseDto> copy(@RestPath String objectKey, @QueryParam("destinationKey") String destinationKey) {
        return s3StorageService.copy(objectKey, destinationKey).map(S3ObjectResponseDto::from);
    }

    /**
     * Moves an object (copy + conditional delete). No automatic rollback:
     * see the Javadoc of {@link S3StorageService#move(String, String)}.
     */
    @POST
    @Path("/{objectKey:.+}/move")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Move an object", description = "Copy + conditional delete. No automatic rollback if the source delete fails after the copy — see the Javadoc of S3StorageService#move.")
    @Parameter(name = "objectKey", in = ParameterIn.PATH, required = true, description = "Key of the source object")
    @Parameter(name = "destinationKey", in = ParameterIn.QUERY, required = true, description = "Key of the destination object", example = "docs/informe-movido.pdf")
    @APIResponse(responseCode = "200", description = "Object moved", content = @Content(schema = @Schema(implementation = S3ObjectResponseDto.class)))
    @APIResponse(responseCode = "404", description = "The source object does not exist", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    public Uni<S3ObjectResponseDto> move(@RestPath String objectKey, @QueryParam("destinationKey") String destinationKey) {
        return s3StorageService.move(objectKey, destinationKey).map(S3ObjectResponseDto::from);
    }

    /** Indicates whether an object with the given key exists, without downloading its content. */
    @GET
    @Path("/{objectKey:.+}/exists")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Check whether an object exists", description = "Logical HEAD: does not download content, only confirms whether the key exists in the bucket.")
    @Parameter(name = "objectKey", in = ParameterIn.PATH, required = true, description = "Key of the object")
    @APIResponse(responseCode = "200", description = "true if it exists, false otherwise", content = @Content(schema = @Schema(type = SchemaType.BOOLEAN)))
    public Uni<Boolean> exists(@RestPath String objectKey) {
        return s3StorageService.exists(objectKey);
    }

    /** Generates a read presigned URL. {@code ttlSeconds} is optional (uses the starter's default TTL). */
    @GET
    @Path("/{objectKey:.+}/presigned")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Generate a read presigned URL", description = "Temporary URL signed by the SDK that allows downloading the object without credentials, valid for ttlSeconds (or the starter's default TTL if omitted).")
    @Parameter(name = "objectKey", in = ParameterIn.PATH, required = true, description = "Key of the object")
    @Parameter(name = "ttlSeconds", in = ParameterIn.QUERY, description = "Validity of the URL in seconds", example = "300")
    @APIResponse(responseCode = "200", description = "Presigned URL", content = @Content(mediaType = MediaType.TEXT_PLAIN, schema = @Schema(type = SchemaType.STRING)))
    @APIResponse(responseCode = "404", description = "The object does not exist", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorResponseDto.class)))
    public Uni<String> presigned(@RestPath String objectKey, @QueryParam("ttlSeconds") Long ttlSeconds) {
        Duration ttl = ttlSeconds != null ? Duration.ofSeconds(ttlSeconds) : null;
        return s3StorageService.presigned(objectKey, ttl);
    }

    /**
     * Reads the multipart's temporary file into memory on the worker pool,
     * so as not to block the event loop with synchronous disk I/O.
     */
    private Uni<byte[]> readBytes(UploadForm form) {
        return Uni.createFrom().item(() -> {
            try {
                return Files.readAllBytes(form.file.uploadedFile());
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }).runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }
}
