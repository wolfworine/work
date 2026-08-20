package com.saywa.framework.core.error.exceptionmappers;

import com.saywa.framework.core.error.exceptions.CryptoAccessDeniedException;
import com.saywa.framework.core.error.exceptions.CryptoConfigurationException;
import com.saywa.framework.core.error.exceptions.CryptoConnectionException;
import com.saywa.framework.core.error.exceptions.CryptoException;
import com.saywa.framework.core.error.exceptions.CryptoGenericException;
import com.saywa.framework.core.error.exceptions.CryptoInvalidCiphertextException;
import com.saywa.framework.core.error.exceptions.CryptoKeyNotFoundException;
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
 * Translates any {@link CryptoException} thrown while processing a REST
 * request into an HTTP response with an {@link ApiError} body.
 * <p>
 * When {@code legacy.global.enabled} is active, a simplified "legacy"
 * mapping is kept (without an {@link ApiError} body) so as not to break
 * consumers that still expect only the status code.
 */
@Slf4j
@Provider
public class CryptoExceptionMapper implements ExceptionMapper<CryptoException> {

    @ConfigProperty(name = "legacy.global.enabled", defaultValue = "false")
    boolean enabled;

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(CryptoException exception) {
        Log.error("KMS Operation failed", exception);
        MDC.put("error_detail", exception.getMessage());

        if (enabled) {
            return switch (exception) {
                case CryptoKeyNotFoundException ignored ->
                        Response.status(Response.Status.NOT_FOUND).build();
                case CryptoInvalidCiphertextException ignored ->
                        Response.status(Response.Status.BAD_REQUEST).build();
                case CryptoAccessDeniedException ignored ->
                        Response.status(Response.Status.FORBIDDEN).build();
                case CryptoConnectionException ignored ->
                        Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
                case CryptoConfigurationException ignored ->
                        Response.status(Response.Status.BAD_REQUEST).build();
                case CryptoGenericException ignored ->
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

    private ErrorType resolverErrorType(CryptoException exception) {
        return switch (exception) {
            case CryptoKeyNotFoundException ignored -> ErrorType.CRYPTO_KEY_NOT_FOUND;
            case CryptoInvalidCiphertextException ignored -> ErrorType.CRYPTO_INVALID_CIPHERTEXT;
            case CryptoAccessDeniedException ignored -> ErrorType.CRYPTO_ACCESS_DENIED;
            case CryptoConnectionException ignored -> ErrorType.CRYPTO_CONNECTION_ERROR;
            case CryptoConfigurationException ignored -> ErrorType.CRYPTO_CONFIGURATION_ERROR;
            case CryptoGenericException ignored -> ErrorType.CRYPTO_GENERIC_ERROR;
        };
    }

}
