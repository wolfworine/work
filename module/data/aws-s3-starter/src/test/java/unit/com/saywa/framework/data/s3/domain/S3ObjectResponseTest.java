package unit.com.saywa.framework.data.s3.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.saywa.framework.data.s3.domain.S3ObjectResponse;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link S3ObjectResponse} record.
 */
class S3ObjectResponseTest {

    @Test
    void givenObjectKeyAndBucketName_whenCreate_thenFieldsAreAccessible() {
        // Act
        var response = new S3ObjectResponse("file.json", "my-bucket");

        // Assert
        assertEquals("file.json", response.objectKey());
        assertEquals("my-bucket", response.bucketName());
    }

    @Test
    void givenSameValues_whenEquals_thenRecordsAreEqual() {
        // Arrange
        var first = new S3ObjectResponse("file.json", "my-bucket");
        var second = new S3ObjectResponse("file.json", "my-bucket");

        // Act & Assert
        assertEquals(first, second);
    }
}
