package com.saywa.framework.data.s3.factory;

import static com.saywa.framework.data.s3.util.S3KeyUtils.removeLeadingSlash;

import com.saywa.framework.data.s3.config.S3Configuration;
import com.saywa.framework.data.s3.domain.S3ObjectRequest;
import com.saywa.framework.data.s3.util.S3Constants;
import com.saywa.framework.data.s3.util.S3ContentTypeUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.Duration;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

/**
 * Builds the AWS SDK 2.x requests ({@code *Request}) needed to operate
 * against Amazon S3, from the configured bucket ({@link S3Configuration})
 * and the starter's domain records.
 *
 * <p>Together with {@code mapper/S3ResponseMapper}, it is the only point in
 * the {@code service/} layer that translates between {@code domain/} and
 * {@code software.amazon.awssdk} types; neither the public
 * {@code service/S3StorageService} interface nor {@code domain/} should
 * import these types, so the starter's consumer never sees the AWS SDK.</p>
 */
@ApplicationScoped
public class S3RequestFactory {

    private final S3Configuration configuration;

    /**
     * Creates the factory, injecting the starter's configuration.
     *
     * @param configuration configuration resolved by CDI, from which the
     *                       bucket name and default prefix are read; never
     *                       {@code null} in a running Quarkus container.
     */
    @Inject
    public S3RequestFactory(S3Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * Builds the upload request ({@code PutObject}) corresponding to a
     * domain {@link S3ObjectRequest}.
     *
     * @param request domain request with the bucket, key, content, MIME
     *                type, and metadata to send; must not be {@code null}
     *                (already validated by the record itself, including
     *                {@code bucketName}).
     * @return the SDK's {@link PutObjectRequest} with {@code request}'s
     *         bucket, the normalized key ({@link #normalizeKey(String)}),
     *         {@code request}'s metadata, and {@code request}'s
     *         {@code contentType} — or, if that is {@code null}, the MIME
     *         type detected from {@code request.objectKey()}'s extension via
     *         {@link S3ContentTypeUtils#detect(String)}.
     */
    public PutObjectRequest createPutRequest(S3ObjectRequest request) {
        String contentType = request.contentType() != null
                ? request.contentType()
                : S3ContentTypeUtils.detect(request.objectKey());

        return PutObjectRequest.builder()
                .bucket(request.bucketName())
                .key(normalizeKey(request.objectKey()))
                .contentType(contentType)
                .metadata(request.metadata())
                .build();
    }


    public AsyncRequestBody createRequestBody(byte[] content) {
        if(content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        if(content.length == 0) {
            throw new IllegalArgumentException("Content cannot be empty");
        }
        return AsyncRequestBody.fromBytes(content);
    }


    /**
     * Builds the download request ({@code GetObject}) for an object key.
     *
     * @param bucketName name of the bucket to operate on; must not be
     *                   {@code null} or blank.
     * @param objectKey  key of the object to download, unnormalized; must
     *                   not be {@code null}.
     * @return the SDK's {@link GetObjectRequest} with {@code bucketName}
     *         and the normalized key ({@link #normalizeKey(String)}).
     */
    public GetObjectRequest createGetRequest(String bucketName, String objectKey) {
        return GetObjectRequest.builder()
                .bucket(requireBucketName(bucketName))
                .key(normalizeKey(objectKey))
                .build();
    }

    /**
     * Builds the deletion request ({@code DeleteObject}) for an object key.
     *
     * @param bucketName name of the bucket to operate on; must not be
     *                   {@code null} or blank.
     * @param objectKey  key of the object to delete, unnormalized; must not
     *                   be {@code null}.
     * @return the SDK's {@link DeleteObjectRequest} with {@code bucketName}
     *         and the normalized key ({@link #normalizeKey(String)}).
     */
    public DeleteObjectRequest createDeleteRequest(String bucketName, String objectKey) {
        return DeleteObjectRequest.builder()
                .bucket(requireBucketName(bucketName))
                .key(normalizeKey(objectKey))
                .build();
    }

    /**
     * Builds the metadata lookup request ({@code HeadObject}) for an object
     * key.
     *
     * @param bucketName name of the bucket to operate on; must not be
     *                   {@code null} or blank.
     * @param objectKey  key of the object to query, unnormalized; must not
     *                   be {@code null}.
     * @return the SDK's {@link HeadObjectRequest} with {@code bucketName}
     *         and the normalized key ({@link #normalizeKey(String)}).
     */
    public HeadObjectRequest createHeadRequest(String bucketName, String objectKey) {
        return HeadObjectRequest.builder()
                .bucket(requireBucketName(bucketName))
                .key(normalizeKey(objectKey))
                .build();
    }

    /**
     * Builds the server-side copy request ({@code CopyObject}) of an object
     * to another key, within the given bucket, without downloading or
     * re-uploading the content.
     *
     * @param bucketName     name of the bucket used as both source and
     *                       destination; must not be {@code null} or blank.
     * @param sourceKey      key of the source object, unnormalized; must
     *                       not be {@code null}.
     * @param destinationKey key of the destination object, unnormalized;
     *                       must not be {@code null}.
     * @return the SDK's {@link CopyObjectRequest} with {@code bucketName}
     *         as both source and destination, and both keys normalized
     *         ({@link #normalizeKey(String)}).
     */
    public CopyObjectRequest createCopyRequest(String bucketName, String sourceKey, String destinationKey) {
        String resolvedBucket = requireBucketName(bucketName);
        String normalizedSource = normalizeKey(sourceKey);
        String normalizedDestination = normalizeKey(destinationKey);

        return CopyObjectRequest.builder()
                .sourceBucket(resolvedBucket)
                .sourceKey(normalizedSource)
                .destinationBucket(resolvedBucket)
                .destinationKey(normalizedDestination)
                .build();
    }

    /**
     * Builds the signing request ({@code GetObjectPresignRequest}) that
     * {@code S3Presigner} needs to generate a read-only (GET) presigned URL
     * for an object, with the given time-to-live.
     *
     * @param bucketName        name of the bucket to operate on; must not
     *                          be {@code null} or blank.
     * @param objectKey         key of the object to presign, unnormalized;
     *                          must not be {@code null}.
     * @param signatureDuration effective time-to-live (TTL) of the
     *                          presigned URL; must not be {@code null}.
     * @return the SDK's {@link GetObjectPresignRequest}, with
     *         {@code signatureDuration} and an inner {@code GetObjectRequest}
     *         with {@code bucketName} and the normalized key
     *         ({@link #normalizeKey(String)}).
     */
    public GetObjectPresignRequest createPresignRequest(String bucketName, String objectKey, Duration signatureDuration) {
        String resolvedBucket = requireBucketName(bucketName);

        return GetObjectPresignRequest.builder()
                .signatureDuration(signatureDuration)
                .getObjectRequest(builder ->
                        builder.bucket(resolvedBucket).
                                key(normalizeKey(objectKey)
                                ))
                .build();
    }

    /**
     * Builds the listing request ({@code ListObjectsV2}) for a given
     * prefix.
     *
     * @param bucketName name of the bucket to operate on; must not be
     *                   {@code null} or blank.
     * @param prefix     prefix to filter by; if {@code null} or blank, it
     *                   resolves to the configuration's default prefix
     *                   ({@link #resolvePrefix(String)}).
     * @return the SDK's {@link ListObjectsV2Request} with {@code bucketName}
     *         and the resolved prefix.
     */
    public ListObjectsV2Request createListRequest(String bucketName, String prefix) {
        return ListObjectsV2Request.builder()
                .bucket(requireBucketName(bucketName))
                .prefix(resolvePrefix(prefix))
                .build();
    }

    /**
     * Validates that {@code bucketName} was explicitly provided by the
     * caller — every operation must state which bucket it targets, there
     * is no implicit default (see {@link S3ObjectRequest}'s equivalent
     * validation for {@code upload}).
     *
     * @param bucketName bucket name to validate.
     * @return {@code bucketName}, unchanged, if valid.
     * @throws IllegalArgumentException if {@code bucketName} is {@code null}
     *                                   or blank.
     */
    private String requireBucketName(String bucketName) {
        if (bucketName == null || bucketName.isBlank()) {
            throw new IllegalArgumentException("bucketName must not be null or blank");
        }
        return bucketName;
    }

    /**
     * Normalizes an object key before sending it to the SDK: removes
     * leading slashes ({@code '/'}) from {@code objectKey} and prepends the
     * configured {@code defaultPrefix}, unless the key already includes it.
     *
     * <p>Examples (with {@code defaultPrefix = "docs"}):</p>
     * <ul>
     *   <li>{@code "/invoice.pdf"} → {@code "docs/invoice.pdf"}</li>
     *   <li>{@code "docs/invoice.pdf"} → {@code "docs/invoice.pdf"}
     *       (prefix not duplicated)</li>
     *   <li>{@code "documents/invoice.pdf"} → {@code "docs/documents/invoice.pdf"}
     *       (not a real prefix match, it only shares the first letters)</li>
     * </ul>
     *
     * @param objectKey key of the object to normalize; must not be
     *                  {@code null}.
     * @return the normalized key, without a leading slash and with
     *         {@code defaultPrefix} applied where applicable.
     * @throws IllegalArgumentException if {@code objectKey} is {@code null}.
     */
    public String normalizeKey(String objectKey) {
        if (objectKey == null) {
            throw new IllegalArgumentException("objectKey must not be null");
        }

        String normalizedKey = removeLeadingSlash(objectKey);
        String prefix = configuration.defaultPrefix();

        if (prefix == null || prefix.isBlank()) {
            return normalizedKey;
        }

        String normalizedPrefix = stripTrailingSlash(removeLeadingSlash(prefix));

        if (normalizedKey.equals(normalizedPrefix) || normalizedKey.startsWith(normalizedPrefix + S3Constants.PATH_SEPARATOR)) {
            return normalizedKey;
        }
        return normalizedPrefix + S3Constants.PATH_SEPARATOR + normalizedKey;
    }

    /**
     * Resolves the prefix to use in a listing operation: if {@code prefix}
     * is {@code null} or blank, the configured {@code defaultPrefix} is
     * used; otherwise, {@code prefix} is returned without its leading slash
     * ({@code '/'}).
     *
     * @param prefix prefix explicitly requested by the caller, or
     *               {@code null}/blank to use the default prefix.
     * @return the resolved prefix, without a leading slash; never
     *         {@code null} (may be an empty string if no
     *         {@code defaultPrefix} is configured).
     */
    public String resolvePrefix(String prefix) {
        if (prefix == null || prefix.isBlank()) {
            return normalizePrefix(configuration.defaultPrefix());
        }
        return normalizePrefix(prefix);
    }

    private String normalizePrefix(String prefix) {
        if (prefix == null || prefix.isBlank()) {
            return S3Constants.EMPTY_STRING;
        }
        return removeLeadingSlash(prefix);
    }

    /**
     * Removes all trailing slashes ({@code '/'}) from a string.
     *
     * @param value string to remove the trailing slashes from; must not be
     *              {@code null}.
     * @return the string without trailing slashes; never {@code null}.
     */
    private String stripTrailingSlash(String value) {
        int end = value.length();
        while (end > 0 && value.charAt(end - 1) == '/') {
            end--;
        }
        return value.substring(0, end);
    }
}
