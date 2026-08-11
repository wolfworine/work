package unit.com.saywa.framework.data.s3.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.saywa.framework.core.error.exceptions.StorageAccessDeniedException;
import com.saywa.framework.core.error.exceptions.StorageConnectionException;
import com.saywa.framework.core.error.exceptions.StorageGenericException;
import com.saywa.framework.core.error.exceptions.StorageObjectNotFoundException;
import com.saywa.framework.data.s3.exception.StorageExceptionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.awscore.exception.AwsErrorDetails;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.S3Exception;

/**
 * Unit tests for {@link StorageExceptionMapper}. Verify the mapping
 * branches described in the Javadoc of
 * {@link StorageExceptionMapper#map(Throwable)} and that the original
 * cause is never lost.
 */
@ExtendWith(MockitoExtension.class)
class S3ExceptionMapperTest {

    private StorageExceptionMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StorageExceptionMapper();
    }

    @Test
    void givenNoSuchKeyException_whenMap_thenReturnsStorageObjectNotFoundExceptionWithCause() {
        // Arrange
        NoSuchKeyException noSuchKeyException = NoSuchKeyException.builder()
                .message("The specified key does not exist.")
                .build();

        // Act
        RuntimeException result = mapper.map(noSuchKeyException);

        // Assert
        assertInstanceOf(StorageObjectNotFoundException.class, result);
        assertNotNull(result.getCause());
        assertSame(noSuchKeyException, result.getCause());
    }

    @Test
    void givenS3ExceptionWithStatusCode403_whenMap_thenReturnsStorageAccessDeniedExceptionWithCause() {
        // Arrange
        S3Exception accessDenied = (S3Exception) S3Exception.builder()
                .message("Access Denied")
                .statusCode(403)
                .awsErrorDetails(AwsErrorDetails.builder()
                        .errorCode("AccessDenied")
                        .errorMessage("Access Denied")
                        .build())
                .build();

        // Act
        RuntimeException result = mapper.map(accessDenied);

        // Assert
        assertInstanceOf(StorageAccessDeniedException.class, result);
        assertNotNull(result.getCause());
        assertSame(accessDenied, result.getCause());
    }

    @Test
    void givenSdkClientException_whenMap_thenReturnsStorageConnectionExceptionWithCause() {
        // Arrange
        SdkClientException sdkClientException = SdkClientException.builder()
                .message("Unable to execute HTTP request: Connection refused")
                .build();

        // Act
        RuntimeException result = mapper.map(sdkClientException);

        // Assert
        assertInstanceOf(StorageConnectionException.class, result);
        assertNotNull(result.getCause());
        assertSame(sdkClientException, result.getCause());
    }

    @Test
    void givenGenericThrowable_whenMap_thenReturnsStorageExceptionWithCause() {
        // Arrange
        RuntimeException genericException = new RuntimeException("unexpected failure");

        // Act
        RuntimeException result = mapper.map(genericException);

        // Assert
        assertEquals(StorageGenericException.class, result.getClass());
        assertNotNull(result.getCause());
        assertSame(genericException, result.getCause());
    }

    @Test
    void givenAlreadyMappedStorageException_whenMap_thenReturnsSameInstance() {
        // Arrange — mapping an exception that already belongs to the
        // framework's own hierarchy must be idempotent: it is never wrapped twice.
        StorageObjectNotFoundException alreadyMapped = new StorageObjectNotFoundException("missing", null);

        // Act
        RuntimeException result = mapper.map(alreadyMapped);

        // Assert
        assertSame(alreadyMapped, result);
    }

    @Test
    void givenS3ExceptionWithoutAwsErrorDetails_whenMap_thenDoesNotThrowAndPreservesCause() {
        // Arrange — statusCode 403 but without awsErrorDetails() (defensive null)
        S3Exception accessDeniedNoDetails = (S3Exception) S3Exception.builder()
                .message("Forbidden")
                .statusCode(403)
                .build();

        // Act
        RuntimeException result = mapper.map(accessDeniedNoDetails);

        // Assert: without awsErrorDetails(), "AccessDenied" cannot be
        // confirmed, so it falls into the generic branch instead of StorageAccessDeniedException.
        assertEquals(StorageGenericException.class, result.getClass());
        assertNotNull(result.getCause());
        assertSame(accessDeniedNoDetails, result.getCause());
    }

    @Test
    void givenNullThrowable_whenMap_thenReturnsGenericStorageExceptionWithoutThrowing() {
        // Act
        RuntimeException result = mapper.map(null);

        // Assert
        assertEquals(StorageGenericException.class, result.getClass());
        assertNotNull(result.getMessage());
    }
}
