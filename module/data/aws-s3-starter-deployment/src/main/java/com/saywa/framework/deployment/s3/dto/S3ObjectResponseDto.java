package com.saywa.framework.deployment.s3.dto;

import com.saywa.framework.data.s3.domain.S3ObjectResponse;

/**
 * JSON DTO, a 1:1 mirror of the starter's {@link S3ObjectResponse}.
 * Mapped directly since both fields are serializable types with no changes.
 *
 * @param objectKey  key of the object within the bucket.
 * @param bucketName name of the bucket where the object resides.
 */
public record S3ObjectResponseDto(String objectKey, String bucketName) {

    public static S3ObjectResponseDto from(S3ObjectResponse response) {
        return new S3ObjectResponseDto(response.objectKey(), response.bucketName());
    }
}
