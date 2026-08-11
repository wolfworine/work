package com.saywa.framework.data.s3.mapper;

import com.saywa.framework.core.error.exceptions.StorageGenericException;
import com.saywa.framework.data.s3.domain.S3ObjectContent;
import com.saywa.framework.data.s3.domain.S3ObjectResponse;
import com.saywa.framework.data.s3.domain.S3ObjectSummary;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

/**
 * Converts the AWS SDK 2.x responses returned by {@code S3AsyncClient} into
 * the starter's immutable {@code domain/} records.
 *
 * <p>Together with {@code factory/S3RequestFactory}, it is the only point
 * in the {@code service/} layer that translates between
 * {@code software.amazon.awssdk} types and {@code domain/}; it keeps no
 * state of its own, so its instances are safe to share across threads.</p>
 */
@ApplicationScoped
public class S3ResponseMapper {

    /**
     * Builds the domain response of a write or transfer operation (for
     * example, after an {@code upload}, {@code copy}, or {@code move})
     * from the key and bucket the operation was performed on.
     *
     * @param objectKey  key of the object the operation was performed on;
     *                   must not be {@code null}.
     * @param bucketName name of the bucket the object resides in; must not
     *                   be {@code null}.
     * @return the corresponding {@link S3ObjectResponse}.
     */
    public S3ObjectResponse toResponse(String objectKey, String bucketName) {
        return new S3ObjectResponse(objectKey, bucketName);
    }

    /**
     * Converts the bytes downloaded for an object ({@code GetObject}),
     * together with its metadata response, into the domain record
     * {@link S3ObjectContent}.
     *
     * @param response downloaded bytes and associated {@code GetObject}
     *                 response, as produced by
     *                 {@code AsyncResponseTransformer.toBytes()}; must not
     *                 be {@code null}.
     * @return the {@link S3ObjectContent} with the binary content and the
     *         {@code contentType} reported by S3 (may be {@code null} if S3
     *         doesn't report it).
     */
    public S3ObjectContent toContent(ResponseBytes<GetObjectResponse> response) {
        return new S3ObjectContent(
                response.asByteArray(),
                response.response().contentType());
    }

    /**
     * Converts the content of a listing response ({@code ListObjectsV2})
     * into a list of domain summaries.
     *
     * @param response {@code ListObjectsV2} response returned by the SDK;
     *                 must not be {@code null}.
     * @return the list of {@link S3ObjectSummary}, one per object contained
     *         in {@code response}; never {@code null} (empty list if the
     *         listing contains no objects).
     */
    public List<S3ObjectSummary> toSummaries(ListObjectsV2Response response) {
        if(response == null){
            throw new StorageGenericException("S3 returned a null response while Listing objects", null);
        }

        return response.contents()
                .stream()
                .map(s3Object -> new S3ObjectSummary(s3Object.key(), s3Object.size(), s3Object.lastModified()))
                .toList();
    }

    public S3ObjectSummary toSummary(S3Object item){
        return new S3ObjectSummary(item.key(), item.size(), item.lastModified());
    }


}
