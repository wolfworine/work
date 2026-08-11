package com.saywa.framework.core.error.model;

import jakarta.ws.rs.core.Response;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Catalog of business error types exposed by the Saywa framework, each with
 * its own code, category, user-facing message, and the HTTP status to
 * respond with.
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum ErrorType {

    STORAGE_OBJECT_NOT_FOUND("ST-01", ErrorCategory.BUSINESS, "Error: the requested object does not exist in storage.", Response.Status.NOT_FOUND),
    STORAGE_ACCESS_DENIED("ST-02", ErrorCategory.SECURITY, "Error: insufficient permissions to access the requested resource.", Response.Status.FORBIDDEN),
    STORAGE_CONNECTION_ERROR("ST-03", ErrorCategory.TECH, "Error communicating with the storage service.", Response.Status.SERVICE_UNAVAILABLE),
    STORAGE_CONFIGURATION_ERROR("ST-04", ErrorCategory.TECH, "Storage service configuration error.", Response.Status.BAD_REQUEST),
    STORAGE_GENERIC_ERROR("ST-05", ErrorCategory.TECH, "Internal error during the operation with the storage service.", Response.Status.INTERNAL_SERVER_ERROR);

    private final String code;
    private final ErrorCategory category;
    private final String userMessage;
    private final Response.Status status;
    
}
