package unit.com.saywa.framework.data.s3.domain;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.saywa.framework.data.s3.domain.S3Operation;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link S3Operation} enum.
 */
class S3OperationTest {

    @Test
    void givenS3Operation_whenValues_thenContainsAllSevenOperations() {
        // Act
        var values = S3Operation.values();

        // Assert
        assertArrayEquals(
                new S3Operation[] {
                        S3Operation.PUT,
                        S3Operation.GET,
                        S3Operation.DELETE,
                        S3Operation.LIST,
                        S3Operation.COPY,
                        S3Operation.HEAD,
                        S3Operation.PRESIGN
                },
                values);
    }

    @Test
    void givenOperationName_whenValueOf_thenReturnsMatchingEnumConstant() {
        // Act & Assert
        assertEquals(S3Operation.PRESIGN, S3Operation.valueOf("PRESIGN"));
    }
}
