package unit.com.saywa.framework.data.s3.mapper;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.saywa.framework.data.s3.domain.S3ObjectContent;
import com.saywa.framework.data.s3.domain.S3ObjectResponse;
import com.saywa.framework.data.s3.domain.S3ObjectSummary;
import com.saywa.framework.data.s3.mapper.S3ResponseMapper;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

/**
 * Unit tests for {@link S3ResponseMapper}. Verify the translation of SDK
 * responses ({@code GetObjectResponse}, {@code ListObjectsV2Response}) into
 * the starter's domain records.
 */
@ExtendWith(MockitoExtension.class)
class S3ResponseMapperTest {

    private S3ResponseMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new S3ResponseMapper();
    }

    @Test
    void givenObjectKeyAndBucketName_whenToResponse_thenReturnsS3ObjectResponse() {
        // Act
        S3ObjectResponse result = mapper.toResponse("docs/file.json", "my-bucket");

        // Assert
        assertEquals("docs/file.json", result.objectKey());
        assertEquals("my-bucket", result.bucketName());
    }

    @Test
    void givenResponseBytes_whenToContent_thenReturnsS3ObjectContentWithBytesAndContentType() {
        // Arrange
        byte[] bytes = "hello".getBytes();
        GetObjectResponse getObjectResponse = GetObjectResponse.builder()
                .contentType("application/json")
                .build();
        ResponseBytes<GetObjectResponse> responseBytes = ResponseBytes.fromByteArray(getObjectResponse, bytes);

        // Act
        S3ObjectContent result = mapper.toContent(responseBytes);

        // Assert
        assertArrayEquals(bytes, result.content());
        assertEquals("application/json", result.contentType());
    }

    @Test
    void givenListObjectsV2Response_whenToSummaries_thenReturnsSummariesWithKeySizeAndLastModified() {
        // Arrange
        Instant lastModified = Instant.parse("2026-01-01T00:00:00Z");
        S3Object s3Object = S3Object.builder()
                .key("docs/file.json")
                .size(1024L)
                .lastModified(lastModified)
                .build();
        ListObjectsV2Response response = ListObjectsV2Response.builder()
                .contents(s3Object)
                .build();

        // Act
        List<S3ObjectSummary> result = mapper.toSummaries(response);

        // Assert
        assertEquals(1, result.size());
        S3ObjectSummary summary = result.getFirst();
        assertEquals("docs/file.json", summary.objectKey());
        assertEquals(1024L, summary.size());
        assertEquals(lastModified, summary.lastModified());
    }

    @Test
    void givenListObjectsV2ResponseWithoutContents_whenToSummaries_thenReturnsEmptyList() {
        // Arrange
        ListObjectsV2Response response = ListObjectsV2Response.builder().build();

        // Act
        List<S3ObjectSummary> result = mapper.toSummaries(response);

        // Assert
        assertTrue(result.isEmpty());
    }
}
