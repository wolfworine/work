package unit.com.saywa.framework.deployment.s3.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.saywa.framework.core.error.exceptions.StorageAccessDeniedException;
import com.saywa.framework.core.error.exceptions.StorageConfigurationException;
import com.saywa.framework.core.error.exceptions.StorageConnectionException;
import com.saywa.framework.core.error.exceptions.StorageGenericException;
import com.saywa.framework.core.error.exceptions.StorageObjectNotFoundException;
import com.saywa.framework.deployment.s3.mapper.S3ExceptionMappers;
import com.saywa.framework.deployment.s3.model.api.s3.error.SaywaErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.ws.rs.core.Response;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link S3ExceptionMappers} — each {@code @ServerExceptionMapper}
 * method is called directly as a plain method (RESTEasy Reactive's own
 * wiring of {@code @ServerExceptionMapper} to the JAX-RS pipeline is a
 * framework concern, already exercised end-to-end by {@code S3ControllerIT}).
 * <p>
 * {@link Response} is {@link AutoCloseable} — each one obtained here is
 * closed via try-with-resources, even though these particular instances
 * hold no real resource (the entity is a plain POJO set via
 * {@code .entity(...)}, not a stream backed by an HTTP connection).
 */
class S3ExceptionMappersTest {

    private final S3ExceptionMappers mappers = new S3ExceptionMappers();

    @Test
    void whenMapNotFound_thenReturns404WithExceptionNameAndMessage() {
        // Act
        try (Response response = mappers.mapNotFound(new StorageObjectNotFoundException("missing", null))) {
            // Assert
            assertEquals(404, response.getStatus());
            SaywaErrorResponse body = (SaywaErrorResponse) response.getEntity();
            assertEquals("StorageObjectNotFoundException", body.error());
            assertEquals("missing", body.message());
        }
    }

    @Test
    void whenMapAccessDenied_thenReturns403() {
        // Act
        try (Response response = mappers.mapAccessDenied(new StorageAccessDeniedException("denied", null))) {
            // Assert
            assertEquals(403, response.getStatus());
            assertEquals("StorageAccessDeniedException", ((SaywaErrorResponse) response.getEntity()).error());
        }
    }

    @Test
    void whenMapConfiguration_thenReturns400() {
        // Act
        try (Response response = mappers.mapConfiguration(new StorageConfigurationException("bad config", null))) {
            // Assert
            assertEquals(400, response.getStatus());
            assertEquals("StorageConfigurationException", ((SaywaErrorResponse) response.getEntity()).error());
        }
    }

    @Test
    void whenMapConnection_thenReturns503() {
        // Act
        try (Response response = mappers.mapConnection(new StorageConnectionException("unreachable", null))) {
            // Assert
            assertEquals(503, response.getStatus());
            assertEquals("StorageConnectionException", ((SaywaErrorResponse) response.getEntity()).error());
        }
    }

    @Test
    void whenMapStorage_thenReturns500() {
        // Act
        try (Response response = mappers.mapStorage(new StorageGenericException("unclassified", null))) {
            // Assert
            assertEquals(500, response.getStatus());
            assertEquals("StorageException", ((SaywaErrorResponse) response.getEntity()).error());
        }
    }

    @Test
    void whenMapIllegalArgument_thenReturns400() {
        // Act
        try (Response response = mappers.mapIllegalArgument(new IllegalArgumentException("bucketName must not be null or blank"))) {
            // Assert
            assertEquals(400, response.getStatus());
            SaywaErrorResponse body = (SaywaErrorResponse) response.getEntity();
            assertEquals("IllegalArgumentException", body.error());
            assertEquals("bucketName must not be null or blank", body.message());
        }
    }

    @Test
    void whenMapValidation_thenReturns400WithPropertyPathAndMessageJoined() {
        // Arrange
        Path propertyPath = mock(Path.class);
        when(propertyPath.toString()).thenReturn("uploadInMemory.bucketName");
        ConstraintViolation<?> violation = mock(ConstraintViolation.class);
        when(violation.getPropertyPath()).thenReturn(propertyPath);
        when(violation.getMessage()).thenReturn("must not be blank");
        ConstraintViolationException exception = new ConstraintViolationException(Set.of(violation));

        // Act
        try (Response response = mappers.mapValidation(exception)) {
            // Assert
            assertEquals(400, response.getStatus());
            SaywaErrorResponse body = (SaywaErrorResponse) response.getEntity();
            assertEquals("ConstraintViolationException", body.error());
            assertTrue(body.message().contains("uploadInMemory.bucketName"));
            assertTrue(body.message().contains("must not be blank"));
        }
    }
}
