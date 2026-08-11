package unit.com.saywa.framework.data.s3.domain;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.saywa.framework.data.s3.domain.S3ObjectContent;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link S3ObjectContent} record.
 */
class S3ObjectContentTest {

    @Test
    void givenContentAndContentType_whenCreate_thenFieldsAreAccessible() {
        // Arrange
        byte[] content = "hello".getBytes();

        // Act
        var objectContent = new S3ObjectContent(content, "text/plain");

        // Assert
        assertArrayEquals(content, objectContent.content());
        assertEquals("text/plain", objectContent.contentType());
    }

    @Test
    void givenNullContentType_whenCreate_thenContentTypeIsNull() {
        // Arrange
        byte[] content = "hello".getBytes();

        // Act
        var objectContent = new S3ObjectContent(content, null);

        // Assert
        assertNull(objectContent.contentType());
    }
}
