package unit.com.saywa.framework.data.s3.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.saywa.framework.data.s3.domain.S3ObjectRequest;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link S3ObjectRequest} record, in particular the
 * defensive validations of its canonical constructor.
 */
class S3ObjectRequestTest {

    @Test
    void givenValidFields_whenCreate_thenFieldsAreAccessible() {
        // Arrange
        byte[] content = "hello".getBytes();

        // Act
        var request = new S3ObjectRequest("file.json", content, "application/json", Map.of("k", "v"));

        // Assert
        assertEquals("file.json", request.objectKey());
        assertEquals(content, request.content());
        assertEquals("application/json", request.contentType());
        assertEquals(Map.of("k", "v"), request.metadata());
    }

    @Test
    void givenNullObjectKey_whenCreate_thenThrowIllegalArgumentException() {
        // Arrange
        byte[] content = "hello".getBytes();

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> new S3ObjectRequest(null, content, "application/json", Map.of()));
    }

    @Test
    void givenBlankObjectKey_whenCreate_thenThrowIllegalArgumentException() {
        // Arrange
        byte[] content = "hello".getBytes();

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> new S3ObjectRequest("   ", content, "application/json", Map.of()));
    }

    @Test
    void givenNullContent_whenCreate_thenThrowIllegalArgumentException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> new S3ObjectRequest("file.json", null, "application/json", Map.of()));
    }

    @Test
    void givenNullContentTypeAndMetadata_whenCreate_thenNoExceptionThrown() {
        // Arrange
        byte[] content = "hello".getBytes();

        // Act
        var request = new S3ObjectRequest("file.json", content, null, null);

        // Assert
        assertEquals("file.json", request.objectKey());
        assertEquals(null, request.contentType());
        assertEquals(null, request.metadata());
    }
}
