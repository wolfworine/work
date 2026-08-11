package unit.com.saywa.framework.data.s3.audit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.saywa.framework.data.s3.audit.S3AuditEvent;
import com.saywa.framework.data.s3.domain.S3Operation;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link S3AuditEvent} record.
 */
class S3AuditEventTest {

    @Test
    void givenSuccessfulOperation_whenCreate_thenFieldsAreAccessible() {
        // Arrange & Act
        var event = new S3AuditEvent(S3Operation.PUT, "mi-bucket", "file.json", true, 120L, null);

        // Assert
        assertEquals(S3Operation.PUT, event.operation());
        assertEquals("mi-bucket", event.bucket());
        assertEquals("file.json", event.objectKey());
        assertTrue(event.successful());
        assertEquals(120L, event.durationMs());
        assertNull(event.errorCode());
    }

    @Test
    void givenFailedOperation_whenCreate_thenErrorCodeIsAccessible() {
        // Arrange & Act
        var event = new S3AuditEvent(S3Operation.GET, "mi-bucket", "file.json", false, 45L,
                "NoSuchKey");

        // Assert
        assertFalse(event.successful());
        assertEquals("NoSuchKey", event.errorCode());
    }
}
