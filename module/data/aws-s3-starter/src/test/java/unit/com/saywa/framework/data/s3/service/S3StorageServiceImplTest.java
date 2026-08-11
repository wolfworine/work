package unit.com.saywa.framework.data.s3.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.saywa.framework.core.error.exceptions.StorageConfigurationException;
import com.saywa.framework.core.error.exceptions.StorageException;
import com.saywa.framework.core.error.exceptions.StorageObjectNotFoundException;
import com.saywa.framework.data.s3.audit.S3AuditLogger;
import com.saywa.framework.data.s3.config.S3Configuration;
import com.saywa.framework.data.s3.config.S3ConfigurationValidator;
import com.saywa.framework.data.s3.domain.S3ObjectContent;
import com.saywa.framework.data.s3.domain.S3ObjectRequest;
import com.saywa.framework.data.s3.domain.S3ObjectResponse;
import com.saywa.framework.data.s3.domain.S3ObjectSummary;
import com.saywa.framework.data.s3.domain.S3Operation;
import com.saywa.framework.data.s3.exception.StorageExceptionMapper;
import com.saywa.framework.data.s3.factory.S3RequestFactory;
import com.saywa.framework.data.s3.mapper.S3ResponseMapper;
import com.saywa.framework.data.s3.service.S3StorageServiceImpl;
import io.smallrye.mutiny.Uni;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
import software.amazon.awssdk.services.s3.model.CopyObjectResponse;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

/**
 * Unit tests for {@link S3StorageServiceImpl}, focused on
 * {@link S3StorageServiceImpl#upload(S3ObjectRequest)} and
 * {@link S3StorageServiceImpl#download(String)} (feature 8), on
 * {@link S3StorageServiceImpl#list(String)}, {@link S3StorageServiceImpl#delete(String)},
 * {@link S3StorageServiceImpl#copy(String, String)}, and
 * {@link S3StorageServiceImpl#move(String, String)} (feature 9), and on
 * {@link S3StorageServiceImpl#exists(String)} and
 * {@link S3StorageServiceImpl#presigned(String, Duration)} (feature 10).
 * <p>
 * {@link S3AsyncClient} and {@link S3Presigner} are simulated with Mockito
 * (SDK responses via {@link CompletableFuture#completedFuture} /
 * {@link CompletableFuture#failedFuture}); {@link S3RequestFactory},
 * {@link S3ResponseMapper}, and {@link StorageExceptionMapper} are used in
 * their real implementation (with no state of their own beyond the
 * simulated configuration) to verify the service's end-to-end behavior.
 * <p>
 * Audit assertions verify calls to {@link S3AuditLogger#success(S3Operation, String, String, long)}
 * / {@link S3AuditLogger#failure(S3Operation, String, String, long, Throwable)}
 * directly rather than capturing an {@code S3AuditEvent}; {@code durationMs}
 * is timing-dependent for every operation except {@code presigned} (whose
 * {@code durationMs} is the deterministic effective TTL), so it is matched
 * with {@code anyLong()} except where noted.
 */
@ExtendWith(MockitoExtension.class)
class S3StorageServiceImplTest {

    private static final String BUCKET = "my-bucket";

    @Mock
    private S3AsyncClient s3AsyncClient;

    @Mock
    private S3Presigner s3Presigner;

    @Mock
    private S3Configuration configuration;

    @Mock
    private S3AuditLogger auditLogger;

    @Mock
    private S3ConfigurationValidator configurationValidator;

    private S3StorageServiceImpl service;

    @BeforeEach
    void setUp() {
        lenient().when(configuration.bucketName()).thenReturn(BUCKET);
        lenient().when(configuration.defaultPrefix()).thenReturn("");
        lenient().when(configuration.maxUploadSize()).thenReturn(10_485_760L);
        lenient().when(configuration.maxDownloadSize()).thenReturn(10_485_760L);

        S3RequestFactory requestFactory = new S3RequestFactory(configuration);
        S3ResponseMapper responseMapper = new S3ResponseMapper();
        StorageExceptionMapper exceptionMapper = new StorageExceptionMapper();

        service = new S3StorageServiceImpl(s3AsyncClient, s3Presigner, configuration, requestFactory,
                responseMapper, auditLogger, exceptionMapper, configurationValidator);
    }

    @Test
    void givenValidRequest_whenUpload_thenReturnsResponseAndAuditsPutSuccess() {
        // Arrange
        S3ObjectRequest request = new S3ObjectRequest("file.json", "content".getBytes(), "application/json",
                Map.of());
        lenient().when(s3AsyncClient.putObject(any(PutObjectRequest.class), any(AsyncRequestBody.class)))
                .thenReturn(CompletableFuture.completedFuture(PutObjectResponse.builder().build()));

        // Act
        S3ObjectResponse result = service.upload(request).await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals("file.json", result.objectKey());
        assertEquals(BUCKET, result.bucketName());

        verify(auditLogger).success(eq(S3Operation.PUT), eq(BUCKET), eq("file.json"), anyLong());
    }

    @Test
    void givenContentExceedsMaxUploadSize_whenUpload_thenFailsWithStorageConfigurationExceptionAndAuditsFailureWithoutCallingSdk() {
        // Arrange
        when(configuration.maxUploadSize()).thenReturn(3L);
        S3ObjectRequest request = new S3ObjectRequest("file.json", "too-big-content".getBytes(), "application/json",
                Map.of());

        // Act
        Uni<S3ObjectResponse> uni = service.upload(request);
        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> uni.subscribeAsCompletionStage().get());

        // Assert
        assertInstanceOf(StorageConfigurationException.class, executionException.getCause());

        verifyNoInteractions(s3AsyncClient);
        verify(auditLogger).failure(eq(S3Operation.PUT), eq(BUCKET), eq("file.json"), anyLong(), any(Throwable.class));
    }

    @Test
    void givenValidObjectKey_whenDownload_thenReturnsContentAndAuditsGetSuccess() {
        // Arrange
        byte[] content = "downloaded-content".getBytes();
        lenient().when(s3AsyncClient.headObject(any(HeadObjectRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(
                        HeadObjectResponse.builder().contentLength((long) content.length).build()));
        GetObjectResponse getObjectResponse = GetObjectResponse.builder().contentType("application/json").build();
        ResponseBytes<GetObjectResponse> responseBytes = ResponseBytes.fromByteArray(getObjectResponse, content);
        lenient().when(s3AsyncClient.getObject(any(GetObjectRequest.class), anyResponseTransformer()))
                .thenReturn(CompletableFuture.completedFuture(responseBytes));

        // Act
        S3ObjectContent result = service.download("file.json").await().atMost(Duration.ofSeconds(5));

        // Assert
        assertArrayEquals(content, result.content());
        assertEquals("application/json", result.contentType());

        verify(auditLogger).success(eq(S3Operation.GET), eq(BUCKET), eq("file.json"), anyLong());
    }

    @Test
    void givenHeadObjectReportsSizeExceedsMax_whenDownload_thenFailsWithStorageConfigurationExceptionWithoutCallingGetObject() {
        // Arrange
        when(configuration.maxDownloadSize()).thenReturn(5L);
        lenient().when(s3AsyncClient.headObject(any(HeadObjectRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(HeadObjectResponse.builder().contentLength(50L).build()));

        // Act
        Uni<S3ObjectContent> uni = service.download("big-file.json");
        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> uni.subscribeAsCompletionStage().get());

        // Assert
        assertInstanceOf(StorageConfigurationException.class, executionException.getCause());

        verify(s3AsyncClient, never()).getObject(any(GetObjectRequest.class), anyResponseTransformer());
        verify(auditLogger).failure(eq(S3Operation.GET), eq(BUCKET), eq("big-file.json"), anyLong(), any(Throwable.class));
    }

    @Test
    void givenObjectDoesNotExist_whenDownload_thenFailsWithStorageObjectNotFoundException() {
        // Arrange
        NoSuchKeyException noSuchKeyException = NoSuchKeyException.builder()
                .message("The specified key does not exist.")
                .build();
        lenient().when(s3AsyncClient.headObject(any(HeadObjectRequest.class)))
                .thenReturn(CompletableFuture.failedFuture(noSuchKeyException));

        // Act
        Uni<S3ObjectContent> uni = service.download("missing.json");
        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> uni.subscribeAsCompletionStage().get());

        // Assert
        assertInstanceOf(StorageObjectNotFoundException.class, executionException.getCause());
        assertEquals(noSuchKeyException, executionException.getCause().getCause());

        verify(s3AsyncClient, never()).getObject(any(GetObjectRequest.class), anyResponseTransformer());
        verify(auditLogger).failure(eq(S3Operation.GET), eq(BUCKET), eq("missing.json"), anyLong(), any(Throwable.class));
    }

    @Test
    void givenPrefixWithNoObjects_whenList_thenReturnsEmptyListAndAuditsListSuccess() {
        // Arrange
        lenient().when(s3AsyncClient.listObjectsV2(any(ListObjectsV2Request.class)))
                .thenReturn(CompletableFuture.completedFuture(ListObjectsV2Response.builder().contents(List.of()).build()));

        // Act
        List<S3ObjectSummary> result = service.list("reports").await().atMost(Duration.ofSeconds(5));

        // Assert
        assertTrue(result.isEmpty());

        verify(auditLogger).success(eq(S3Operation.LIST), eq(BUCKET), eq("reports"), anyLong());
    }

    @Test
    void givenPrefixWithMultipleObjects_whenList_thenReturnsSummariesAndAuditsListSuccess() {
        // Arrange
        S3Object first = S3Object.builder().key("reports/a.json").size(10L).lastModified(Instant.EPOCH).build();
        S3Object second = S3Object.builder().key("reports/b.json").size(20L).lastModified(Instant.EPOCH).build();
        lenient().when(s3AsyncClient.listObjectsV2(any(ListObjectsV2Request.class)))
                .thenReturn(CompletableFuture
                        .completedFuture(ListObjectsV2Response.builder().contents(first, second).build()));

        // Act
        List<S3ObjectSummary> result = service.list("reports").await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals(2, result.size());
        assertEquals("reports/a.json", result.get(0).objectKey());
        assertEquals("reports/b.json", result.get(1).objectKey());

        verify(auditLogger).success(eq(S3Operation.LIST), eq(BUCKET), eq("reports"), anyLong());
    }

    @Test
    void givenValidObjectKey_whenDelete_thenCompletesAndAuditsDeleteSuccess() {
        // Arrange
        lenient().when(s3AsyncClient.deleteObject(any(DeleteObjectRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(DeleteObjectResponse.builder().build()));

        // Act
        service.delete("file.json").await().atMost(Duration.ofSeconds(5));

        // Assert
        verify(s3AsyncClient).deleteObject(any(DeleteObjectRequest.class));
        verify(auditLogger).success(eq(S3Operation.DELETE), eq(BUCKET), eq("file.json"), anyLong());
    }

    @Test
    void givenSdkFails_whenDelete_thenFailsAndAuditsDeleteFailure() {
        // Arrange
        RuntimeException sdkFailure = new RuntimeException("s3 delete boom");
        lenient().when(s3AsyncClient.deleteObject(any(DeleteObjectRequest.class)))
                .thenReturn(CompletableFuture.failedFuture(sdkFailure));

        // Act
        Uni<Void> uni = service.delete("file.json");
        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> uni.subscribeAsCompletionStage().get());

        // Assert
        assertInstanceOf(StorageException.class, executionException.getCause());
        assertEquals(sdkFailure, executionException.getCause().getCause());

        verify(auditLogger).failure(eq(S3Operation.DELETE), eq(BUCKET), eq("file.json"), anyLong(), any(Throwable.class));
    }

    @Test
    void givenValidKeys_whenCopy_thenReturnsResponseAndAuditsCopySuccess() {
        // Arrange
        lenient().when(s3AsyncClient.copyObject(any(CopyObjectRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(CopyObjectResponse.builder().build()));

        // Act
        S3ObjectResponse result = service.copy("source.json", "destination.json").await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals("destination.json", result.objectKey());
        assertEquals(BUCKET, result.bucketName());

        verify(auditLogger).success(eq(S3Operation.COPY), eq(BUCKET), eq("destination.json"), anyLong());
    }

    @Test
    void givenValidKeys_whenMove_thenCopiesThenDeletesSourceAndReturnsResponse() {
        // Arrange
        lenient().when(s3AsyncClient.copyObject(any(CopyObjectRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(CopyObjectResponse.builder().build()));
        lenient().when(s3AsyncClient.deleteObject(any(DeleteObjectRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(DeleteObjectResponse.builder().build()));

        // Act
        S3ObjectResponse result = service.move("source.json", "destination.json").await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals("destination.json", result.objectKey());
        assertEquals(BUCKET, result.bucketName());

        verify(s3AsyncClient).copyObject(any(CopyObjectRequest.class));
        verify(s3AsyncClient).deleteObject(any(DeleteObjectRequest.class));

        verify(auditLogger).success(eq(S3Operation.COPY), eq(BUCKET), eq("destination.json"), anyLong());
        verify(auditLogger).success(eq(S3Operation.DELETE), eq(BUCKET), eq("source.json"), anyLong());
    }

    @Test
    void givenDeleteFailsAfterCopySucceeds_whenMove_thenPropagatesErrorAndDoesNotRevertCopy() {
        // Arrange
        RuntimeException deleteFailure = new RuntimeException("s3 delete boom");
        lenient().when(s3AsyncClient.copyObject(any(CopyObjectRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(CopyObjectResponse.builder().build()));
        lenient().when(s3AsyncClient.deleteObject(any(DeleteObjectRequest.class)))
                .thenReturn(CompletableFuture.failedFuture(deleteFailure));

        // Act
        Uni<S3ObjectResponse> uni = service.move("source.json", "destination.json");
        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> uni.subscribeAsCompletionStage().get());

        // Assert — the delete error propagates...
        assertInstanceOf(StorageException.class, executionException.getCause());
        assertEquals(deleteFailure, executionException.getCause().getCause());

        // ...but the copy is NOT reverted: copyObject was invoked exactly once
        // and no kind of "undo" (e.g. deleting the destination) is ever attempted.
        verify(s3AsyncClient, times(1)).copyObject(any(CopyObjectRequest.class));
        verify(s3AsyncClient, times(1)).deleteObject(any(DeleteObjectRequest.class));

        verify(auditLogger).success(eq(S3Operation.COPY), eq(BUCKET), eq("destination.json"), anyLong());
        verify(auditLogger).failure(eq(S3Operation.DELETE), eq(BUCKET), eq("source.json"), anyLong(), any(Throwable.class));
    }

    @Test
    void givenObjectExists_whenExists_thenReturnsTrueAndAuditsHeadSuccess() {
        // Arrange
        lenient().when(s3AsyncClient.headObject(any(HeadObjectRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(HeadObjectResponse.builder().contentLength(10L).build()));

        // Act
        Boolean result = service.exists("file.json").await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals(Boolean.TRUE, result);

        verify(auditLogger).success(eq(S3Operation.HEAD), eq(BUCKET), eq("file.json"), anyLong());
    }

    @Test
    void givenObjectDoesNotExist_whenExists_thenReturnsFalseAndAuditsHeadSuccess() {
        // Arrange
        NoSuchKeyException noSuchKeyException = NoSuchKeyException.builder()
                .message("The specified key does not exist.")
                .build();
        lenient().when(s3AsyncClient.headObject(any(HeadObjectRequest.class)))
                .thenReturn(CompletableFuture.failedFuture(noSuchKeyException));

        // Act
        Boolean result = service.exists("missing.json").await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals(Boolean.FALSE, result);

        verify(auditLogger).success(eq(S3Operation.HEAD), eq(BUCKET), eq("missing.json"), anyLong());
    }

    @Test
    void givenSdkFailsWithNonNotFoundError_whenExists_thenPropagatesErrorAndAuditsHeadFailure() {
        // Arrange
        RuntimeException sdkFailure = new RuntimeException("s3 head boom");
        lenient().when(s3AsyncClient.headObject(any(HeadObjectRequest.class)))
                .thenReturn(CompletableFuture.failedFuture(sdkFailure));

        // Act
        Uni<Boolean> uni = service.exists("file.json");
        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> uni.subscribeAsCompletionStage().get());

        // Assert
        assertInstanceOf(StorageException.class, executionException.getCause());
        assertEquals(sdkFailure, executionException.getCause().getCause());

        verify(auditLogger).failure(eq(S3Operation.HEAD), eq(BUCKET), eq("file.json"), anyLong(), any(Throwable.class));
    }

    @Test
    void givenObjectExists_whenPresigned_thenReturnsUrlAndAuditsPresignSuccessWithoutFullUrl() throws MalformedURLException {
        // Arrange
        lenient().when(s3AsyncClient.headObject(any(HeadObjectRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(HeadObjectResponse.builder().contentLength(10L).build()));
        URL presignedUrl = URI.create(
                "https://my-bucket.s3.amazonaws.com/file.json?X-Amz-Signature=super-secret-signature").toURL();
        PresignedGetObjectRequest presignedGetObjectRequest = mock(PresignedGetObjectRequest.class);
        lenient().when(presignedGetObjectRequest.url()).thenReturn(presignedUrl);
        lenient().when(s3Presigner.presignGetObject(any(GetObjectPresignRequest.class)))
                .thenReturn(presignedGetObjectRequest);

        // Act
        String result = service.presigned("file.json", Duration.ofMinutes(30)).await().atMost(Duration.ofSeconds(5));

        // Assert
        assertEquals(presignedUrl.toString(), result);

        // Critical test (docs/conventions.md): auditing NEVER records the full presigned URL —
        // enforced structurally here, since S3AuditLogger#success(S3Operation, String, String, long)
        // has no parameter capable of carrying a URL; durationMs is the effective TTL, not elapsed time.
        verify(auditLogger).success(eq(S3Operation.PRESIGN), eq(BUCKET), eq("file.json"),
                eq(Duration.ofMinutes(30).toMillis()));
    }

    @Test
    void givenTtlIsNull_whenPresigned_thenUsesConfiguredDefaultTtl() throws MalformedURLException {
        // Arrange
        when(configuration.presignedDefaultTtl()).thenReturn(Duration.ofMinutes(15));
        lenient().when(s3AsyncClient.headObject(any(HeadObjectRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(HeadObjectResponse.builder().contentLength(10L).build()));
        URL presignedUrl = URI.create("https://my-bucket.s3.amazonaws.com/file.json?X-Amz-Signature=abc").toURL();
        PresignedGetObjectRequest presignedGetObjectRequest = mock(PresignedGetObjectRequest.class);
        lenient().when(presignedGetObjectRequest.url()).thenReturn(presignedUrl);
        lenient().when(s3Presigner.presignGetObject(any(GetObjectPresignRequest.class)))
                .thenReturn(presignedGetObjectRequest);

        // Act
        service.presigned("file.json", null).await().atMost(Duration.ofSeconds(5));

        // Assert
        ArgumentCaptor<GetObjectPresignRequest> requestCaptor = ArgumentCaptor.forClass(GetObjectPresignRequest.class);
        verify(s3Presigner).presignGetObject(requestCaptor.capture());
        assertEquals(Duration.ofMinutes(15), requestCaptor.getValue().signatureDuration());

        verify(auditLogger).success(eq(S3Operation.PRESIGN), eq(BUCKET), eq("file.json"),
                eq(Duration.ofMinutes(15).toMillis()));
    }

    @Test
    void givenObjectDoesNotExist_whenPresigned_thenFailsWithStorageObjectNotFoundExceptionAndAuditsPresignFailureWithoutCallingPresigner() {
        // Arrange
        NoSuchKeyException noSuchKeyException = NoSuchKeyException.builder()
                .message("The specified key does not exist.")
                .build();
        lenient().when(s3AsyncClient.headObject(any(HeadObjectRequest.class)))
                .thenReturn(CompletableFuture.failedFuture(noSuchKeyException));

        // Act
        Uni<String> uni = service.presigned("missing.json", Duration.ofMinutes(10));
        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> uni.subscribeAsCompletionStage().get());

        // Assert
        assertInstanceOf(StorageObjectNotFoundException.class, executionException.getCause());
        assertEquals(noSuchKeyException, executionException.getCause().getCause());

        verify(s3Presigner, never()).presignGetObject(any(GetObjectPresignRequest.class));
        verify(auditLogger).failure(eq(S3Operation.PRESIGN), eq(BUCKET), eq("missing.json"),
                eq(Duration.ofMinutes(10).toMillis()), any(Throwable.class));
    }

    @Test
    void givenBeanConstructed_whenPostConstructInitializeInvoked_thenValidatesConfiguration() throws Exception {
        // Arrange
        Method initialize = S3StorageServiceImpl.class.getDeclaredMethod("initialize");
        initialize.setAccessible(true);

        // Act
        initialize.invoke(service);

        // Assert
        verify(configurationValidator).validate();
    }

    /**
     * Helper for {@code any()} over a generic {@link AsyncResponseTransformer}
     * ({@code AsyncResponseTransformer<GetObjectResponse,
     * ResponseBytes<GetObjectResponse>>}), avoiding repeating the matcher
     * with its full generic type in every test.
     */
    @SuppressWarnings("unchecked")
    private static AsyncResponseTransformer<GetObjectResponse, ResponseBytes<GetObjectResponse>> anyResponseTransformer() {
        return any(AsyncResponseTransformer.class);
    }
}
