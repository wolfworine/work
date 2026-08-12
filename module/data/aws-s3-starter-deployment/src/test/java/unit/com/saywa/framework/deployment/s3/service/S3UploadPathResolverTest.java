package unit.com.saywa.framework.deployment.s3.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.saywa.framework.deployment.s3.service.S3UploadPathResolver;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link S3UploadPathResolver} — the only guard against
 * path-traversal / arbitrary local file read for the server-side
 * load-by-path upload mode ({@code POST /shrd/s3/bucket/path}).
 */
class S3UploadPathResolverTest {

    private Path baseDir;
    private S3UploadPathResolver resolver;

    @BeforeEach
    void setUp() throws IOException {
        baseDir = Files.createTempDirectory("upload-base-dir-test");
        resolver = new S3UploadPathResolver(baseDir);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(baseDir);
    }

    @Test
    void givenBareFilename_whenResolve_thenResolvesInsideBaseDir() {
        // Act
        Path result = resolver.resolve("sample.txt");

        // Assert
        assertEquals(baseDir.resolve("sample.txt").normalize(), result);
    }

    @Test
    void givenRelativeSubdirectoryPath_whenResolve_thenResolvesInsideBaseDir() {
        // Act
        Path result = resolver.resolve("docs/sample.txt");

        // Assert
        assertEquals(baseDir.resolve("docs/sample.txt").normalize(), result);
    }

    @Test
    void givenParentDirectoryTraversal_whenResolve_thenThrowsIllegalArgumentException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> resolver.resolve("../../../../etc/passwd"));
    }

    @Test
    void givenTraversalThatEventuallyReturnsInsideBaseDir_whenResolve_thenResolvesInsideBaseDir() {
        // Arrange — "docs/../sample.txt" normalizes back to "sample.txt": not an
        // escape attempt, must not be rejected just because it contains "..".
        // Act
        Path result = resolver.resolve("docs/../sample.txt");

        // Assert
        assertEquals(baseDir.resolve("sample.txt").normalize(), result);
    }

    @Test
    void givenAbsolutePathOutsideBaseDir_whenResolve_thenThrowsIllegalArgumentException() {
        // Arrange
        String root = baseDir.getRoot().toString();
        String absolutePath = root + "some" + java.io.File.separator + "other" + java.io.File.separator + "place.txt";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> resolver.resolve(absolutePath));
    }

    @Test
    void givenNullRequestedPath_whenResolve_thenThrowsIllegalArgumentException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> resolver.resolve(null));
    }

    @Test
    void givenBlankRequestedPath_whenResolve_thenThrowsIllegalArgumentException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> resolver.resolve("   "));
    }
}
