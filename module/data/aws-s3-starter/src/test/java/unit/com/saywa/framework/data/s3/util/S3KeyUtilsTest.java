package unit.com.saywa.framework.data.s3.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.saywa.framework.data.s3.util.S3KeyUtils;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link S3KeyUtils}. Verify removal of a key's leading
 * slash and ensuring a prefix's trailing slash.
 */
class S3KeyUtilsTest {

    @Test
    void givenKeyWithLeadingSlash_whenRemoveLeadingSlash_thenSlashIsRemoved() {
        // Act
        String result = S3KeyUtils.removeLeadingSlash("/file.json");

        // Assert
        assertEquals("file.json", result);
    }

    @Test
    void givenKeyWithoutLeadingSlash_whenRemoveLeadingSlash_thenKeyIsUnchanged() {
        // Act
        String result = S3KeyUtils.removeLeadingSlash("file.json");

        // Assert
        assertEquals("file.json", result);
    }

    @Test
    void givenNullValue_whenRemoveLeadingSlash_thenThrowsIllegalArgumentException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> S3KeyUtils.removeLeadingSlash(null));
    }

    @Test
    void givenPrefixWithoutTrailingSlash_whenEnsureTrailingSlash_thenSlashIsAppended() {
        // Act
        String result = S3KeyUtils.ensureTrailingSlash("docs");

        // Assert
        assertEquals("docs/", result);
    }

    @Test
    void givenPrefixWithTrailingSlash_whenEnsureTrailingSlash_thenPrefixIsUnchanged() {
        // Act
        String result = S3KeyUtils.ensureTrailingSlash("docs/");

        // Assert
        assertEquals("docs/", result);
    }

    @Test
    void givenNullPrefix_whenEnsureTrailingSlash_thenThrowsIllegalArgumentException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> S3KeyUtils.ensureTrailingSlash(null));
    }
}
