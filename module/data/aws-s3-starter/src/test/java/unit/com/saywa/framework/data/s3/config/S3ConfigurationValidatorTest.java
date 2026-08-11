package unit.com.saywa.framework.data.s3.config;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;

import com.saywa.framework.data.s3.config.S3Configuration;
import com.saywa.framework.data.s3.config.S3ConfigurationValidator;
import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link S3ConfigurationValidator}. The {@link S3Configuration}
 * interface is mocked directly with Mockito (no {@code @QuarkusTest}, no
 * resolving the real {@code @ConfigMapping}).
 */
@ExtendWith(MockitoExtension.class)
class S3ConfigurationValidatorTest {

    @Mock
    private S3Configuration configuration;

    private S3ConfigurationValidator validator;

    @BeforeEach
    void setUp() {
        validator = new S3ConfigurationValidator(configuration);
    }

    /**
     * Configures the mock with valid default values, so that tests only
     * need to override the value under test.
     */
    private void stubValidDefaults() {
        lenient().when(configuration.bucketName()).thenReturn("mi-bucket");
        lenient().when(configuration.defaultPrefix()).thenReturn("");
        lenient().when(configuration.maxUploadSize()).thenReturn(10_485_760L);
        lenient().when(configuration.maxDownloadSize()).thenReturn(10_485_760L);
        lenient().when(configuration.presignedDefaultTtl()).thenReturn(Duration.ofMinutes(15));
    }

    @Test
    void givenEmptyBucketName_whenValidate_thenThrowsIllegalArgumentException() {
        // Arrange
        stubValidDefaults();
        lenient().when(configuration.bucketName()).thenReturn("");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> validator.validate());
    }

    @Test
    void givenNullBucketName_whenValidate_thenThrowsIllegalArgumentException() {
        // Arrange
        stubValidDefaults();
        lenient().when(configuration.bucketName()).thenReturn(null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> validator.validate());
    }

    @Test
    void givenNullDefaultPrefix_whenValidate_thenThrowsIllegalArgumentException() {
        // Arrange
        stubValidDefaults();
        lenient().when(configuration.defaultPrefix()).thenReturn(null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> validator.validate());
    }

    @Test
    void givenDefaultPrefixWithDoubleSlash_whenValidate_thenThrowsIllegalArgumentException() {
        // Arrange
        stubValidDefaults();
        lenient().when(configuration.defaultPrefix()).thenReturn("documentos//privados");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> validator.validate());
    }

    @Test
    void givenMaxUploadSizeZero_whenValidate_thenThrowsIllegalArgumentException() {
        // Arrange
        stubValidDefaults();
        lenient().when(configuration.maxUploadSize()).thenReturn(0L);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> validator.validate());
    }

    @Test
    void givenMaxUploadSizeNegative_whenValidate_thenThrowsIllegalArgumentException() {
        // Arrange
        stubValidDefaults();
        lenient().when(configuration.maxUploadSize()).thenReturn(-1L);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> validator.validate());
    }

    @Test
    void givenMaxDownloadSizeZero_whenValidate_thenThrowsIllegalArgumentException() {
        // Arrange
        stubValidDefaults();
        lenient().when(configuration.maxDownloadSize()).thenReturn(0L);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> validator.validate());
    }

    @Test
    void givenValidConfiguration_whenValidate_thenDoesNotThrow() {
        // Arrange
        stubValidDefaults();

        // Act & Assert
        assertDoesNotThrow(() -> validator.validate());
    }
}
