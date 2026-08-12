package com.saywa.framework.deployment.s3.mapper;

import com.saywa.framework.core.error.exceptions.StorageAccessDeniedException;
import com.saywa.framework.core.error.exceptions.StorageConfigurationException;
import com.saywa.framework.core.error.exceptions.StorageConnectionException;
import com.saywa.framework.core.error.exceptions.StorageException;
import com.saywa.framework.core.error.exceptions.StorageObjectNotFoundException;
import com.saywa.framework.deployment.s3.model.api.s3.error.SaywaErrorResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

/**
 * Translates the framework's shared storage exception hierarchy (module
 * {@code core}) and request-validation failures into HTTP responses.
 * {@code @ServerExceptionMapper} (RESTEasy Reactive) is used instead of
 * {@code jakarta.ws.rs.ext.ExceptionMapper} so that RESTEasy Reactive
 * resolves the most specific mapper based on the actual exception type,
 * without blocking the event loop.
 * <p>
 * The raw message of infrastructure exceptions (e.g. credentials) is never
 * recorded in the response body; only the exception name and a descriptive
 * message already sanitized by the starter.
 */
@Slf4j
public class S3ExceptionMappers {

    @ServerExceptionMapper
    public Response mapNotFound(StorageObjectNotFoundException e) {
        log.debug("S3 object not found", e);
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new SaywaErrorResponse("StorageObjectNotFoundException", e.getMessage()))
                .build();
    }

    @ServerExceptionMapper
    public Response mapAccessDenied(StorageAccessDeniedException e) {
        log.warn("Access denied by S3/LocalStack", e);
        return Response.status(Response.Status.FORBIDDEN)
                .entity(new SaywaErrorResponse("StorageAccessDeniedException", e.getMessage()))
                .build();
    }

    @ServerExceptionMapper
    public Response mapConfiguration(StorageConfigurationException e) {
        log.warn("S3 configuration error", e);
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new SaywaErrorResponse("StorageConfigurationException", e.getMessage()))
                .build();
    }

    @ServerExceptionMapper
    public Response mapConnection(StorageConnectionException e) {
        log.error("Connectivity error with S3/LocalStack", e);
        return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                .entity(new SaywaErrorResponse("StorageConnectionException", e.getMessage()))
                .build();
    }

    /**
     * Generic catch-all: any {@link StorageException} not covered by the
     * more specific mappers above (RESTEasy Reactive picks the most specific
     * mapper first).
     */
    @ServerExceptionMapper
    public Response mapStorage(StorageException e) {
        log.error("Unclassified error from S3StorageService", e);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new SaywaErrorResponse("StorageException", e.getMessage()))
                .build();
    }

    @ServerExceptionMapper
    public Response mapIllegalArgument(IllegalArgumentException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new SaywaErrorResponse("IllegalArgumentException", e.getMessage()))
                .build();
    }

    /**
     * Jakarta Bean Validation failures ({@code @Valid}/{@code @NotBlank} on
     * request DTOs and {@code @BeanParam}/{@code @QueryParam} controller
     * parameters) — e.g. a missing {@code bucketName} or {@code objectKey}.
     */
    @ServerExceptionMapper
    public Response mapValidation(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining("; "));
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new SaywaErrorResponse("ConstraintViolationException", message))
                .build();
    }
}
