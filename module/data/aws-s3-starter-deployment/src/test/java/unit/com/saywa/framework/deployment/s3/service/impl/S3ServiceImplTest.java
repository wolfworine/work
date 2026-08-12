package unit.com.saywa.framework.deployment.s3.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.saywa.framework.data.s3.domain.S3ObjectContent;
import com.saywa.framework.data.s3.domain.S3ObjectRequest;
import com.saywa.framework.data.s3.domain.S3ObjectResponse;
import com.saywa.framework.data.s3.domain.S3ObjectSummary;
import com.saywa.framework.data.s3.service.S3StorageService;
import com.saywa.framework.deployment.s3.service.S3UploadPathResolver;
import com.saywa.framework.deployment.s3.service.impl.S3ServiceImpl;
import io.smallrye.mutiny.Uni;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link S3ServiceImpl}. {@link S3StorageService} is
 * mocked (it's the starter's contract, already covered by
 * {@code aws-s3-starter}'s own tests); {@link S3UploadPathResolver} is
 * used in its real implementation, pointed at a temp directory, to verify
 * the load-by-path mode end-to-end (including the path-traversal guard)
 * without needing LocalStack.
 */
@ExtendWith(MockitoExtension.class)
class S3ServiceImplTest {

    private static final String BUCKET = "my-bucket";

    @Mock
    private S3StorageService s3StorageService;

    private Path baseDir;
    private S3ServiceImpl service;

    @BeforeEach
    void setUp() throws IOException {
        baseDir = Files.createTempDirectory("upload-base-dir-test");
        S3UploadPathResolver uploadPathResolver = new S3UploadPathResolver(baseDir);
        service = new S3ServiceImpl(s3StorageService, uploadPathResolver);
    }

    @AfterEach
    void tearDown() throws IOException {
        try (var paths = Files.walk(baseDir)) {
            paths.sorted(Comparator.reverseOrder()).forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        }
    }

    @Test
    void givenValidFields_whenUploadInMemory_thenBuildsS3ObjectRequestAndDelegatesToStorageService() {
        // Arrange
        byte[] content = "hello".getBytes();
        S3ObjectResponse response = new S3ObjectResponse("file.json", BUCKET);
        when(s3StorageService.upload(any(S3ObjectRequest.class))).thenReturn(Uni.createFrom().item(response));

        // Act
        S3ObjectResponse result = service.uploadInMemory(BUCKET, "file.json", content, "application/json")
                .await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals(response, result);
        verify(s3StorageService).upload(argThat(request ->
                request.bucketName().equals(BUCKET)
                        && request.objectKey().equals("file.json")
                        && request.content() == content
                        && request.contentType().equals("application/json")));
    }

    @Test
    void givenFileInsideBaseDir_whenUploadFromPath_thenReadsFileAndDelegatesToStorageService() throws IOException {
        // Arrange
        Path file = baseDir.resolve("sample.txt");
        Files.writeString(file, "sample content");
        S3ObjectResponse response = new S3ObjectResponse("docs/sample.txt", BUCKET);
        when(s3StorageService.upload(any(S3ObjectRequest.class))).thenReturn(Uni.createFrom().item(response));

        // Act
        S3ObjectResponse result = service.uploadFromPath(BUCKET, "docs/sample.txt", "sample.txt", "text/plain")
                .await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals(response, result);
        verify(s3StorageService).upload(argThat(request ->
                request.bucketName().equals(BUCKET)
                        && request.objectKey().equals("docs/sample.txt")
                        && new String(request.content()).equals("sample content")
                        && request.contentType().equals("text/plain")));
    }

    @Test
    void givenPathEscapingBaseDir_whenUploadFromPath_thenFailsWithIllegalArgumentExceptionWithoutCallingStorageService() {
        // Act
        Uni<S3ObjectResponse> uni = service.uploadFromPath(BUCKET, "file.json", "../../etc/passwd", "text/plain");
        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> uni.subscribeAsCompletionStage().get());

        // Assert
        org.junit.jupiter.api.Assertions.assertInstanceOf(IllegalArgumentException.class, executionException.getCause());
    }

    @Test
    void givenMissingFile_whenUploadFromPath_thenFailsWithUncheckedIOException() {
        // Act
        Uni<S3ObjectResponse> uni = service.uploadFromPath(BUCKET, "file.json", "does-not-exist.txt", "text/plain");
        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> uni.subscribeAsCompletionStage().get());

        // Assert
        org.junit.jupiter.api.Assertions.assertInstanceOf(UncheckedIOException.class, executionException.getCause());
    }

    @Test
    void whenDownload_thenDelegatesToStorageServiceWithSameArguments() {
        // Arrange
        S3ObjectContent content = new S3ObjectContent("hello".getBytes(), "text/plain");
        when(s3StorageService.download(BUCKET, "file.json")).thenReturn(Uni.createFrom().item(content));

        // Act
        S3ObjectContent result = service.download(BUCKET, "file.json").await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals(content, result);
    }

    @Test
    void whenList_thenDelegatesToStorageServiceWithSameArguments() {
        // Arrange
        List<S3ObjectSummary> summaries = List.of(new S3ObjectSummary("a.json", 10L, Instant.EPOCH));
        when(s3StorageService.list(BUCKET, "docs/")).thenReturn(Uni.createFrom().item(summaries));

        // Act
        List<S3ObjectSummary> result = service.list(BUCKET, "docs/").await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals(summaries, result);
    }

    @Test
    void whenDelete_thenDelegatesToStorageServiceWithSameArguments() {
        // Arrange
        when(s3StorageService.delete(BUCKET, "file.json")).thenReturn(Uni.createFrom().voidItem());

        // Act
        service.delete(BUCKET, "file.json").await().atMost(Duration.ofSeconds(5));

        // Assert
        verify(s3StorageService).delete(BUCKET, "file.json");
    }

    @Test
    void whenCopy_thenDelegatesToStorageServiceWithSameArguments() {
        // Arrange
        S3ObjectResponse response = new S3ObjectResponse("destination.json", BUCKET);
        when(s3StorageService.copy(BUCKET, "source.json", "destination.json")).thenReturn(Uni.createFrom().item(response));

        // Act
        S3ObjectResponse result = service.copy(BUCKET, "source.json", "destination.json").await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals(response, result);
    }

    @Test
    void whenMove_thenDelegatesToStorageServiceWithSameArguments() {
        // Arrange
        S3ObjectResponse response = new S3ObjectResponse("destination.json", BUCKET);
        when(s3StorageService.move(BUCKET, "source.json", "destination.json")).thenReturn(Uni.createFrom().item(response));

        // Act
        S3ObjectResponse result = service.move(BUCKET, "source.json", "destination.json").await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals(response, result);
    }

    @Test
    void whenExists_thenDelegatesToStorageServiceWithSameArguments() {
        // Arrange
        when(s3StorageService.exists(BUCKET, "file.json")).thenReturn(Uni.createFrom().item(true));

        // Act
        Boolean result = service.exists(BUCKET, "file.json").await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void whenPresigned_thenDelegatesToStorageServiceWithSameArguments() {
        // Arrange
        Duration ttl = Duration.ofMinutes(15);
        when(s3StorageService.presigned(BUCKET, "file.json", ttl))
                .thenReturn(Uni.createFrom().item("https://example.com/presigned"));

        // Act
        String result = service.presigned(BUCKET, "file.json", ttl).await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals("https://example.com/presigned", result);
    }
}
