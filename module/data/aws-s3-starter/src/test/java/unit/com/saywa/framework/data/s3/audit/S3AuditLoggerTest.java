package unit.com.saywa.framework.data.s3.audit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.saywa.framework.data.s3.audit.S3AuditEvent;
import com.saywa.framework.data.s3.audit.S3AuditLogger;
import com.saywa.framework.data.s3.domain.S3Operation;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import org.jboss.logmanager.LogContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link S3AuditLogger}. Does not use {@code @QuarkusTest}:
 * it captures the actually emitted messages by hooking a {@link Handler}
 * into the underlying JBoss LogManager logger (the effective SLF4J binding
 * in this starter), which allows verifying both the observable behavior
 * (doesn't throw with a {@code null} event) and the exact format of the
 * audit message.
 */
class S3AuditLoggerTest {

    private final S3AuditLogger auditLogger = new S3AuditLogger();

    private final List<String> capturedMessages = new ArrayList<>();

    private org.jboss.logmanager.Logger jbossLogger;

    private Handler captureHandler;

    @BeforeEach
    void setUp() {
        jbossLogger = LogContext.getLogContext().getLogger(S3AuditLogger.class.getName());
        jbossLogger.setLevel(Level.ALL);

        captureHandler = new Handler() {
            @Override
            public void publish(LogRecord logRecord) {
                capturedMessages.add(logRecord.getMessage());
            }

            @Override
            public void flush() {
                // no-op: no buffer to flush
            }

            @Override
            public void close() {
                // no-op: no resources to release
            }
        };
        jbossLogger.addHandler(captureHandler);
    }

    @AfterEach
    void tearDown() {
        jbossLogger.removeHandler(captureHandler);
    }

    @Test
    void givenNullEvent_whenAudit_thenDoesNotThrowAndDoesNotLog() {
        // Arrange (null event, nothing to prepare)

        // Act & Assert
        assertDoesNotThrow(() -> auditLogger.audit(null));
        assertTrue(capturedMessages.isEmpty());
    }

    @Test
    void givenSuccessfulEvent_whenAudit_thenLogsFormattedMessage() {
        // Arrange
        var event = new S3AuditEvent(S3Operation.PUT, "mi-bucket", "documentos/file.json",
                true, 120L, null);

        // Act
        auditLogger.audit(event);

        // Assert
        assertEquals(1, capturedMessages.size());
        assertEquals(
                "s3.audit operation=PUT bucket=mi-bucket objectKey=documentos/file.json "
                        + "success=true durationMs=120 errorCode=null",
                capturedMessages.getFirst());
    }

    @Test
    void givenFailedEvent_whenAudit_thenLogsErrorCode() {
        // Arrange
        var event = new S3AuditEvent(S3Operation.GET, "mi-bucket", "documentos/file.json",
                false, 45L, "NoSuchKey");

        // Act
        auditLogger.audit(event);

        // Assert
        assertEquals(1, capturedMessages.size());
        assertEquals(
                "s3.audit operation=GET bucket=mi-bucket objectKey=documentos/file.json "
                        + "success=false durationMs=45 errorCode=NoSuchKey",
                capturedMessages.getFirst());
    }

    @Test
    void givenEventWithNullDurationAndObjectKey_whenAudit_thenLogsNullPlaceholders() {
        // Arrange
        var event = new S3AuditEvent(S3Operation.LIST, "mi-bucket", null, true, null, null);

        // Act
        auditLogger.audit(event);

        // Assert
        assertEquals(1, capturedMessages.size());
        assertEquals(
                "s3.audit operation=LIST bucket=mi-bucket objectKey=null "
                        + "success=true durationMs=null errorCode=null",
                capturedMessages.getFirst());
    }

    @Test
    void givenOperationDetails_whenSuccess_thenLogsSuccessfulEventWithNoErrorCode() {
        // Act
        auditLogger.success(S3Operation.PUT, "mi-bucket", "documentos/file.json", 120L);

        // Assert
        assertEquals(1, capturedMessages.size());
        assertEquals(
                "s3.audit operation=PUT bucket=mi-bucket objectKey=documentos/file.json "
                        + "success=true durationMs=120 errorCode=null",
                capturedMessages.getFirst());
    }

    @Test
    void givenOperationDetailsAndError_whenFailure_thenLogsFailedEventWithErrorSimpleName() {
        // Act
        auditLogger.failure(S3Operation.GET, "mi-bucket", "documentos/file.json", 45L,
                new IllegalStateException("boom"));

        // Assert
        assertEquals(1, capturedMessages.size());
        assertEquals(
                "s3.audit operation=GET bucket=mi-bucket objectKey=documentos/file.json "
                        + "success=false durationMs=45 errorCode=IllegalStateException",
                capturedMessages.getFirst());
    }
}
