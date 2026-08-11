package com.saywa.framework.core.error.exceptionmappers;

import com.saywa.framework.core.error.exceptions.StorageAccessDeniedException;
import com.saywa.framework.core.error.exceptions.StorageConfigurationException;
import com.saywa.framework.core.error.exceptions.StorageConnectionException;
import com.saywa.framework.core.error.exceptions.StorageException;
import com.saywa.framework.core.error.exceptions.StorageGenericException;
import com.saywa.framework.core.error.exceptions.StorageObjectNotFoundException;
import com.saywa.framework.core.error.model.ApiError;
import com.saywa.framework.core.error.model.ErrorDetail;
import com.saywa.framework.core.error.model.ErrorType;
import io.quarkus.logging.Log;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.MDC;

/**
 * Translates any {@link StorageException} thrown while processing a REST
 * request into an HTTP response with an {@link ApiError} body.
 * <p>
 * When {@code legacy.global.enabled} is active, a simplified "legacy"
 * mapping is kept (without an {@link ApiError} body) so as not to break
 * consumers that still expect only the status code.
 */
@Slf4j
@Provider
public class StorageExceptionMapper implements ExceptionMapper<StorageException> {

    @ConfigProperty(name = "legacy.global.enabled", defaultValue = "false")
    boolean enabled;

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(StorageException exception) {
        Log.error("S3 Operation failed", exception);
        MDC.put("error_detail", exception.getMessage());

        if (enabled) {
            return switch (exception) {
                case StorageObjectNotFoundException ignored ->
                        Response.status(Response.Status.NOT_FOUND).build();
                case StorageAccessDeniedException ignored ->
                        Response.status(Response.Status.FORBIDDEN).build();
                case StorageConnectionException ignored ->
                        Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
                case StorageConfigurationException ignored ->
                        Response.status(Response.Status.BAD_REQUEST).build();
                case StorageGenericException ignored ->
                        Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            };
        }

        ErrorType errorType = resolverErrorType(exception);

        String path = uriInfo != null ? uriInfo.getPath() : "unknown";

        List<ErrorDetail> errorDetails = List.of(
                ErrorDetail.builder()
                        .errorCode(errorType.getCode())
                        .message(errorType.getStatus().getReasonPhrase())
                        .url(path)
                        .category(errorType.getCategory())
                        .build()
        );

        ApiError apiError = ApiError.builder()
                .statusCode(errorType.getStatus().getStatusCode())
                .userMessage(errorType.getUserMessage())
                .timestamp(DateTimeFormatter.ISO_INSTANT.format(Instant.now()))
                .errors(errorDetails)
                .build();

        return Response.status(errorType.getStatus())
                .type(MediaType.APPLICATION_JSON)
                .entity(apiError)
                .build();
    }

    private ErrorType resolverErrorType(StorageException exception) {
        return switch (exception) {
            case StorageObjectNotFoundException ignored -> ErrorType.STORAGE_OBJECT_NOT_FOUND;
            case StorageAccessDeniedException ignored -> ErrorType.STORAGE_ACCESS_DENIED;
            case StorageConnectionException ignored -> ErrorType.STORAGE_CONNECTION_ERROR;
            case StorageConfigurationException ignored -> ErrorType.STORAGE_CONFIGURATION_ERROR;
            case StorageGenericException ignored -> ErrorType.STORAGE_GENERIC_ERROR;
        };
    }

}
