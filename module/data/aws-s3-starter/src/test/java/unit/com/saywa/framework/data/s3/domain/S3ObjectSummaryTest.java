package unit.com.saywa.framework.data.s3.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.saywa.framework.data.s3.domain.S3ObjectSummary;
import java.time.Instant;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link S3ObjectSummary} record.
 */
class S3ObjectSummaryTest {

    @Test
    void givenKeySizeAndLastModified_whenCreate_thenFieldsAreAccessible() {
        // Arrange
        Instant lastModified = Instant.parse("2026-08-07T10:00:00Z");

        // Act
        var summary = new S3ObjectSummary("file.json", 1024L, lastModified);

        // Assert
        assertEquals("file.json", summary.objectKey());
        assertEquals(1024L, summary.size());
        assertEquals(lastModified, summary.lastModified());
    }
}
