package unit.com.saywa.framework.data.s3.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.saywa.framework.data.s3.util.S3ContentTypeUtils;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link S3ContentTypeUtils}. Verify MIME type detection
 * from the file name's extension, including the default value for unknown
 * extensions.
 */
class S3ContentTypeUtilsTest {

    @Test
    void givenPdfFileName_whenDetect_thenReturnsApplicationPdf() {
        // Act
        String result = S3ContentTypeUtils.detect("factura.pdf");

        // Assert
        assertEquals("application/pdf", result);
    }

    @Test
    void givenJsonFileName_whenDetect_thenReturnsApplicationJson() {
        // Act
        String result = S3ContentTypeUtils.detect("data.json");

        // Assert
        assertEquals("application/json", result);
    }

    @Test
    void givenCsvFileName_whenDetect_thenReturnsTextCsv() {
        // Act
        String result = S3ContentTypeUtils.detect("report.csv");

        // Assert
        assertEquals("text/csv", result);
    }

    @Test
    void givenPngFileName_whenDetect_thenReturnsImagePng() {
        // Act
        String result = S3ContentTypeUtils.detect("logo.png");

        // Assert
        assertEquals("image/png", result);
    }

    @Test
    void givenTxtFileName_whenDetect_thenReturnsTextPlain() {
        // Act
        String result = S3ContentTypeUtils.detect("notes.txt");

        // Assert
        assertEquals("text/plain", result);
    }

    @Test
    void givenUnknownExtension_whenDetect_thenReturnsApplicationOctetStream() {
        // Act
        String result = S3ContentTypeUtils.detect("archive.xyz");

        // Assert
        assertEquals("application/octet-stream", result);
    }

    @Test
    void givenFileNameWithoutExtension_whenDetect_thenReturnsApplicationOctetStream() {
        // Act
        String result = S3ContentTypeUtils.detect("README");

        // Assert
        assertEquals("application/octet-stream", result);
    }

    @Test
    void givenNullFileName_whenDetect_thenReturnsApplicationOctetStream() {
        // Act
        String result = S3ContentTypeUtils.detect(null);

        // Assert
        assertEquals("application/octet-stream", result);
    }

    @Test
    void givenUppercaseExtension_whenDetect_thenExtensionMatchIsCaseInsensitive() {
        // Act
        String result = S3ContentTypeUtils.detect("FOTO.PNG");

        // Assert
        assertEquals("image/png", result);
    }
}
