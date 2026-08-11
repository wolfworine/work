package com.saywa.framework.deployment.s3.dto;

import com.saywa.framework.data.s3.domain.S3ObjectSummary;
import java.time.Instant;

/**
 * JSON DTO, a 1:1 mirror of the starter's {@link S3ObjectSummary}.
 *
 * @param objectKey    key of the object within the bucket.
 * @param size         size of the object in bytes.
 * @param lastModified instant of the last modification.
 */
public record S3ObjectSummaryDto(String objectKey, long size, Instant lastModified) {

    public static S3ObjectSummaryDto from(S3ObjectSummary summary) {
        return new S3ObjectSummaryDto(summary.objectKey(), summary.size(), summary.lastModified());
    }
}
