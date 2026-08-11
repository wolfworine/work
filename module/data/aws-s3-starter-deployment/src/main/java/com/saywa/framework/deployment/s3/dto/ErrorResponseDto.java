package com.saywa.framework.deployment.s3.dto;

/**
 * Standard JSON error body returned by {@code S3ExceptionMappers}.
 *
 * @param error   short error code (exception name from the starter's
 *                shared error hierarchy).
 * @param message descriptive message; never includes binary content or full
 *                presigned URLs.
 */
public record ErrorResponseDto(String error, String message) {
}
