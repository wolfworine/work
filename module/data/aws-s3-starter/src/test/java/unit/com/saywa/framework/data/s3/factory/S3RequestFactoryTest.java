package unit.com.saywa.framework.data.s3.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import com.saywa.framework.data.s3.config.S3Configuration;
import com.saywa.framework.data.s3.domain.S3ObjectRequest;
import com.saywa.framework.data.s3.factory.S3RequestFactory;
import java.time.Duration;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

/**
 * Unit tests for {@link S3RequestFactory}. Verify the construction of each
 * SDK request type and, in particular, key normalization
 * ({@link S3RequestFactory#normalizeKey(String)}) and prefix resolution
 * ({@link S3RequestFactory#resolvePrefix(String)}).
 */
@ExtendWith(MockitoExtension.class)
class S3RequestFactoryTest {

    private static final String BUCKET = "my-bucket";

    @Mock
    private S3Configuration configuration;

    private S3RequestFactory factory;

    @BeforeEach
    void setUp() {
        lenient().when(configuration.bucketName()).thenReturn(BUCKET);
        lenient().when(configuration.defaultPrefix()).thenReturn("");
        factory = new S3RequestFactory(configuration);
    }

    @Test
    void givenValidRequest_whenCreatePutRequest_thenReturnsPutObjectRequestWithBucketKeyContentTypeAndMetadata() {
        // Arrange
        Map<String, String> metadata = Map.of("owner", "saywa");
        S3ObjectRequest request = new S3ObjectRequest("file.json", "content".getBytes(), "application/json", metadata);

        // Act
        PutObjectRequest result = factory.createPutRequest(request);

        // Assert
        assertEquals(BUCKET, result.bucket());
        assertEquals("file.json", result.key());
        assertEquals("application/json", result.contentType());
        assertEquals(metadata, result.metadata());
    }

    @Test
    void givenNullContentType_whenCreatePutRequest_thenDetectsContentTypeFromObjectKeyExtension() {
        // Arrange
        S3ObjectRequest request = new S3ObjectRequest("invoice.pdf", "content".getBytes(), null, Map.of());

        // Act
        PutObjectRequest result = factory.createPutRequest(request);

        // Assert
        assertEquals("application/pdf", result.contentType());
    }

    @Test
    void givenObjectKey_whenCreateGetRequest_thenReturnsGetObjectRequestWithBucketAndNormalizedKey() {
        // Act
        GetObjectRequest result = factory.createGetRequest("/file.json");

        // Assert
        assertEquals(BUCKET, result.bucket());
        assertEquals("file.json", result.key());
    }

    @Test
    void givenObjectKey_whenCreateDeleteRequest_thenReturnsDeleteObjectRequestWithBucketAndNormalizedKey() {
        // Act
        DeleteObjectRequest result = factory.createDeleteRequest("/file.json");

        // Assert
        assertEquals(BUCKET, result.bucket());
        assertEquals("file.json", result.key());
    }

    @Test
    void givenObjectKey_whenCreateHeadRequest_thenReturnsHeadObjectRequestWithBucketAndNormalizedKey() {
        // Act
        HeadObjectRequest result = factory.createHeadRequest("/file.json");

        // Assert
        assertEquals(BUCKET, result.bucket());
        assertEquals("file.json", result.key());
    }

    @Test
    void givenSourceAndDestinationKeys_whenCreateCopyRequest_thenReturnsCopyObjectRequestWithBucketAndNormalizedKeys() {
        // Act
        CopyObjectRequest result = factory.createCopyRequest("/source.json", "/destination.json");

        // Assert
        assertEquals(BUCKET, result.sourceBucket());
        assertEquals("source.json", result.sourceKey());
        assertEquals(BUCKET, result.destinationBucket());
        assertEquals("destination.json", result.destinationKey());
    }

    @Test
    void givenDefaultPrefixConfigured_whenCreateCopyRequest_thenPrefixIsAppliedToBothSourceAndDestinationKeys() {
        // Arrange
        when(configuration.defaultPrefix()).thenReturn("docs");

        // Act
        CopyObjectRequest result = factory.createCopyRequest("source.json", "archive/destination.json");

        // Assert
        assertEquals(BUCKET, result.sourceBucket());
        assertEquals("docs/source.json", result.sourceKey());
        assertEquals(BUCKET, result.destinationBucket());
        assertEquals("docs/archive/destination.json", result.destinationKey());
    }

    @Test
    void givenObjectKeyAndTtl_whenCreatePresignRequest_thenReturnsPresignRequestWithSignatureDurationAndNormalizedKey() {
        // Act
        GetObjectPresignRequest result = factory.createPresignRequest("/file.json", Duration.ofMinutes(15));

        // Assert
        assertEquals(Duration.ofMinutes(15), result.signatureDuration());
        assertEquals(BUCKET, result.getObjectRequest().bucket());
        assertEquals("file.json", result.getObjectRequest().key());
    }

    @Test
    void givenDefaultPrefixConfigured_whenCreatePresignRequest_thenPrefixIsAppliedToObjectKey() {
        // Arrange
        when(configuration.defaultPrefix()).thenReturn("docs");

        // Act
        GetObjectPresignRequest result = factory.createPresignRequest("file.json", Duration.ofMinutes(5));

        // Assert
        assertEquals(Duration.ofMinutes(5), result.signatureDuration());
        assertEquals("docs/file.json", result.getObjectRequest().key());
    }

    @Test
    void givenPrefix_whenCreateListRequest_thenReturnsListObjectsV2RequestWithBucketAndResolvedPrefix() {
        // Act
        ListObjectsV2Request result = factory.createListRequest("/reports");

        // Assert
        assertEquals(BUCKET, result.bucket());
        assertEquals("reports", result.prefix());
    }

    @Test
    void givenNullPrefix_whenCreateListRequest_thenUsesDefaultPrefix() {
        // Arrange
        when(configuration.defaultPrefix()).thenReturn("docs");

        // Act
        ListObjectsV2Request result = factory.createListRequest(null);

        // Assert
        assertEquals("docs", result.prefix());
    }

    @Test
    void givenKeyWithLeadingSlash_whenNormalizeKey_thenLeadingSlashIsRemoved() {
        // Act
        String result = factory.normalizeKey("/file.json");

        // Assert
        assertEquals("file.json", result);
    }

    @Test
    void givenKeyWithoutLeadingSlashAndNoDefaultPrefix_whenNormalizeKey_thenKeyIsUnchanged() {
        // Act
        String result = factory.normalizeKey("file.json");

        // Assert
        assertEquals("file.json", result);
    }

    @Test
    void givenDefaultPrefixConfiguredAndKeyDoesNotIncludeIt_whenNormalizeKey_thenPrefixIsPrepended() {
        // Arrange
        when(configuration.defaultPrefix()).thenReturn("docs");

        // Act
        String result = factory.normalizeKey("file.json");

        // Assert
        assertEquals("docs/file.json", result);
    }

    @Test
    void givenDefaultPrefixConfiguredAndKeyAlreadyIncludesIt_whenNormalizeKey_thenPrefixIsNotDuplicated() {
        // Arrange
        when(configuration.defaultPrefix()).thenReturn("docs");

        // Act
        String result = factory.normalizeKey("docs/file.json");

        // Assert
        assertEquals("docs/file.json", result);
    }

    @Test
    void givenDefaultPrefixConfiguredWithSlashesAndKeyEqualsPrefix_whenNormalizeKey_thenPrefixIsNotDuplicated() {
        // Arrange
        when(configuration.defaultPrefix()).thenReturn("/docs/");

        // Act
        String result = factory.normalizeKey("docs");

        // Assert
        assertEquals("docs", result);
    }

    @Test
    void givenKeyOnlySharesPrefixCharacters_whenNormalizeKey_thenPrefixIsStillPrepended() {
        // Arrange — "documentos" shares characters with "docs" but is not the same prefix
        when(configuration.defaultPrefix()).thenReturn("docs");

        // Act
        String result = factory.normalizeKey("documentos/file.json");

        // Assert
        assertEquals("docs/documentos/file.json", result);
    }

    @Test
    void givenNullObjectKey_whenNormalizeKey_thenThrowsIllegalArgumentException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.normalizeKey(null));
    }

    @Test
    void givenNullPrefix_whenResolvePrefix_thenReturnsDefaultPrefix() {
        // Arrange
        when(configuration.defaultPrefix()).thenReturn("docs");

        // Act
        String result = factory.resolvePrefix(null);

        // Assert
        assertEquals("docs", result);
    }

    @Test
    void givenBlankPrefix_whenResolvePrefix_thenReturnsDefaultPrefix() {
        // Arrange
        when(configuration.defaultPrefix()).thenReturn("docs");

        // Act
        String result = factory.resolvePrefix("   ");

        // Assert
        assertEquals("docs", result);
    }

    @Test
    void givenExplicitPrefixWithLeadingSlash_whenResolvePrefix_thenLeadingSlashIsRemoved() {
        // Act
        String result = factory.resolvePrefix("/reports");

        // Assert
        assertEquals("reports", result);
    }

    @Test
    void givenNoDefaultPrefixConfigured_whenResolvePrefix_thenReturnsEmptyString() {
        // Act
        String result = factory.resolvePrefix(null);

        // Assert
        assertEquals("", result);
    }
}
