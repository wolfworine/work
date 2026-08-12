package com.saywa.framework.deployment.s3.model.api.s3;

import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

/**
 * Bean-param binding shared by the read-only endpoints that all need the
 * same core shape — {@code bucketName} + {@code objectKey}:
 * {@code download}, {@code exists}, {@code presigned}. Bound in one shot
 * with {@code @BeanParam @Valid} in {@code S3Controller}, the same pattern
 * {@link S3UploadFormRequest} already uses for multipart form fields, applied here
 * to query/path params instead.
 * <p>
 * A plain class with public fields, not a record: RESTEasy Reactive's
 * {@code @BeanParam} binding needs mutable fields to inject into, not
 * record canonical-constructor components.
 * <p>
 * {@code list} and {@code delete}/{@code copy}/{@code move} deliberately
 * do NOT use this class — their shapes genuinely differ (no
 * {@code objectKey}, or an extra {@code destinationKey} required only for
 * copy/move) and are only 2-3 fields, not worth forcing into a shared
 * bean with endpoint-conditional required fields.
 */
public class S3ParameterRequest {

    @PathParam("objectKey")
    @NotBlank
    public String objectKey;

    @QueryParam("bucketName")
    @NotBlank
    public String bucketName;

    /** Only read by {@code presigned}; {@code download}/{@code exists} ignore it. */
    @QueryParam("ttlSeconds")
    public Long ttlSeconds;
}
