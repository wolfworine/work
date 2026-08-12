package unit.com.saywa.framework.deployment.s3.facade.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.saywa.framework.data.s3.domain.S3ObjectContent;
import com.saywa.framework.data.s3.domain.S3ObjectResponse;
import com.saywa.framework.data.s3.domain.S3ObjectSummary;
import com.saywa.framework.deployment.s3.facade.impl.S3FacadeImpl;
import com.saywa.framework.deployment.s3.model.api.s3.S3BodyRequest;
import com.saywa.framework.deployment.s3.model.api.s3.S3BodyResponse;
import com.saywa.framework.deployment.s3.service.S3Service;
import io.smallrye.mutiny.Uni;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link S3FacadeImpl}, focused on the DTO mapping it owns
 * ({@code S3BodyResponse.from(...)} overloads) and the {@code ttlSeconds}
 * (Long) -&gt; {@code Duration} conversion in {@link S3FacadeImpl#presigned}
 * — logic specific to this class, not already covered by
 * {@code S3ServiceImplTest} or {@code S3ControllerIT}.
 */
@ExtendWith(MockitoExtension.class)
class S3FacadeImplTest {

    private static final String BUCKET = "my-bucket";

    @Mock
    private S3Service s3Service;

    private S3FacadeImpl facade;

    @BeforeEach
    void setUp() {
        facade = new S3FacadeImpl(s3Service);
    }

    @Test
    void givenSuccessfulUpload_whenUploadInMemory_thenMapsToS3BodyResponse() {
        // Arrange
        S3ObjectResponse response = new S3ObjectResponse("file.json", BUCKET);
        when(s3Service.uploadInMemory(BUCKET, "file.json", "content".getBytes(), "application/json"))
                .thenReturn(Uni.createFrom().item(response));

        // Act
        S3BodyResponse result = facade.uploadInMemory(BUCKET, "file.json", "content".getBytes(), "application/json")
                .await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals(200, result.status());
        assertEquals("file.json", result.objectKey());
        assertEquals(BUCKET, result.bucketName());
        assertNull(result.url());
        assertNull(result.size());
    }

    @Test
    void givenFailingUpload_whenUploadInMemory_thenFailurePropagatesUnchanged() {
        // Arrange
        RuntimeException failure = new RuntimeException("boom");
        when(s3Service.uploadInMemory(BUCKET, "file.json", "content".getBytes(), "application/json"))
                .thenReturn(Uni.createFrom().failure(failure));

        // Act
        Uni<S3BodyResponse> uni = facade.uploadInMemory(BUCKET, "file.json", "content".getBytes(), "application/json");
        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> uni.subscribeAsCompletionStage().get());

        // Assert — the facade only logs on failure, it never wraps/changes the exception
        assertEquals(failure, executionException.getCause());
    }

    @Test
    void whenUploadFromPath_thenDelegatesWithRequestFieldsAndMapsToS3BodyResponse() {
        // Arrange
        S3BodyRequest request = new S3BodyRequest(BUCKET, "file.json", "sample.txt", "text/plain");
        S3ObjectResponse response = new S3ObjectResponse("file.json", BUCKET);
        when(s3Service.uploadFromPath(BUCKET, "file.json", "sample.txt", "text/plain"))
                .thenReturn(Uni.createFrom().item(response));

        // Act
        S3BodyResponse result = facade.uploadFromPath(request).await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals("file.json", result.objectKey());
        assertEquals(BUCKET, result.bucketName());
    }

    @Test
    void whenList_thenMapsEachSummaryToS3BodyResponseWithBucketAndSizeAndLastModified() {
        // Arrange
        List<S3ObjectSummary> summaries = List.of(
                new S3ObjectSummary("a.json", 10L, Instant.EPOCH),
                new S3ObjectSummary("b.json", 20L, Instant.EPOCH));
        when(s3Service.list(BUCKET, "docs/")).thenReturn(Uni.createFrom().item(summaries));

        // Act
        List<S3BodyResponse> result = facade.list(BUCKET, "docs/").await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals(2, result.size());
        assertEquals("a.json", result.getFirst().objectKey());
        assertEquals(BUCKET, result.getFirst().bucketName());
        assertEquals(10L, result.getFirst().size());
        assertEquals(Instant.EPOCH, result.getFirst().lastModified());
        assertNull(result.getFirst().url());
    }

    @Test
    void whenCopy_thenMapsToS3BodyResponse() {
        // Arrange
        S3ObjectResponse response = new S3ObjectResponse("destination.json", BUCKET);
        when(s3Service.copy(BUCKET, "source.json", "destination.json")).thenReturn(Uni.createFrom().item(response));

        // Act
        S3BodyResponse result = facade.copy(BUCKET, "source.json", "destination.json").await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals("destination.json", result.objectKey());
        assertEquals(BUCKET, result.bucketName());
    }

    @Test
    void whenMove_thenMapsToS3BodyResponse() {
        // Arrange
        S3ObjectResponse response = new S3ObjectResponse("destination.json", BUCKET);
        when(s3Service.move(BUCKET, "source.json", "destination.json")).thenReturn(Uni.createFrom().item(response));

        // Act
        S3BodyResponse result = facade.move(BUCKET, "source.json", "destination.json").await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals("destination.json", result.objectKey());
        assertEquals(BUCKET, result.bucketName());
    }

    @Test
    void givenNonNullTtlSeconds_whenPresigned_thenConvertsToDurationOfSecondsAndMapsUrl() {
        // Arrange
        ArgumentCaptor<Duration> ttlCaptor = ArgumentCaptor.forClass(Duration.class);
        when(s3Service.presigned(org.mockito.ArgumentMatchers.eq(BUCKET), org.mockito.ArgumentMatchers.eq("file.json"), ttlCaptor.capture()))
                .thenReturn(Uni.createFrom().item("https://example.com/presigned"));

        // Act
        S3BodyResponse result = facade.presigned(BUCKET, "file.json", 300L).await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals(Duration.ofSeconds(300), ttlCaptor.getValue());
        assertEquals("https://example.com/presigned", result.url());
        assertEquals("file.json", result.objectKey());
        assertEquals(BUCKET, result.bucketName());
    }

    @Test
    void givenNullTtlSeconds_whenPresigned_thenPassesNullDurationThrough() {
        // Arrange
        ArgumentCaptor<Duration> ttlCaptor = ArgumentCaptor.forClass(Duration.class);
        when(s3Service.presigned(org.mockito.ArgumentMatchers.eq(BUCKET), org.mockito.ArgumentMatchers.eq("file.json"), ttlCaptor.capture()))
                .thenReturn(Uni.createFrom().item("https://example.com/presigned"));

        // Act
        facade.presigned(BUCKET, "file.json", null).await().atMost(Duration.ofSeconds(5));

        // Assert
        assertNull(ttlCaptor.getValue());
    }

    @Test
    void whenDownload_thenPassesThroughWithoutMappingSinceItIsBinaryContent() {
        // Arrange
        S3ObjectContent content = new S3ObjectContent("hello".getBytes(), "text/plain");
        when(s3Service.download(BUCKET, "file.json")).thenReturn(Uni.createFrom().item(content));

        // Act
        S3ObjectContent result = facade.download(BUCKET, "file.json").await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals(content, result);
    }

    @Test
    void whenExists_thenPassesThroughUnchanged() {
        // Arrange
        when(s3Service.exists(BUCKET, "file.json")).thenReturn(Uni.createFrom().item(true));

        // Act
        Boolean result = facade.exists(BUCKET, "file.json").await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void whenDelete_thenPassesThroughUnchanged() {
        // Arrange
        when(s3Service.delete(BUCKET, "file.json")).thenReturn(Uni.createFrom().voidItem());

        // Act
        facade.delete(BUCKET, "file.json").await().atMost(Duration.ofSeconds(5));

        // Assert
        verify(s3Service).delete(BUCKET, "file.json");
    }
}
