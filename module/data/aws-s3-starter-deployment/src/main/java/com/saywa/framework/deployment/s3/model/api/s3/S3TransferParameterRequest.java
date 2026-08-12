package com.saywa.framework.deployment.s3.model.api.s3;

import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

/**
 * Bean-param binding shared by {@code copy} and {@code move} — the only
 * two endpoints that need a destination key alongside {@code bucketName}
 * + {@code objectKey}. Deliberately a separate class from
 * {@link S3ParameterRequest} rather than adding {@code destinationKey} to
 * it: {@code download}/{@code exists}/{@code presigned}/{@code delete}
 * don't send a {@code destinationKey}, so annotating it {@code @NotBlank}
 * on the shared class would break their validation the same way
 * {@code objectKey} would break {@code list} (see that class' Javadoc) —
 * each bean-param class only has fields its own callers all genuinely
 * require.
 */
public class S3TransferParameterRequest {

    @PathParam("objectKey")
    @NotBlank
    public String objectKey;

    @QueryParam("bucketName")
    @NotBlank
    public String bucketName;

    @QueryParam("destinationKey")
    @NotBlank
    public String destinationKey;
}
