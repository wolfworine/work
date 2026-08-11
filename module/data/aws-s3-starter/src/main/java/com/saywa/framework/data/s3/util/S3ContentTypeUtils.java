package com.saywa.framework.data.s3.util;

import java.util.Locale;
import java.util.Map;

/**
 * General-purpose utility for detecting a file's MIME type from its name
 * (extension), without depending on any AWS SDK type or the starter's
 * configuration.
 *
 * <p>An independent, reusable utility class: not a CDI bean, stateless,
 * with exclusively static methods. Intended to fill in the
 * {@code contentType} of an {@code S3ObjectRequest} when the consumer
 * doesn't know it beforehand.</p>
 */
public final class S3ContentTypeUtils {

    /** Default MIME type for unknown or missing extensions. */
    public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";

    private static final Map<String, String> CONTENT_TYPES_BY_EXTENSION = Map.ofEntries(
            Map.entry("pdf", "application/pdf"),
            Map.entry("json", "application/json"),
            Map.entry("csv", "text/csv"),
            Map.entry("png", "image/png"),
            Map.entry("txt", "text/plain"),
            Map.entry("jpg", "image/jpeg"),
            Map.entry("jpeg", "image/jpeg"),
            Map.entry("gif", "image/gif"),
            Map.entry("xml", "application/xml"),
            Map.entry("zip", "application/zip"),
            Map.entry("html", "text/html"),
            Map.entry("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
            Map.entry("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"));

    private S3ContentTypeUtils() {
        // Utility class: not instantiable.
    }

    /**
     * Detects a file's MIME type from its name's extension.
     *
     * @param fileName file name (with or without a path), for example
     *                 {@code "invoice.pdf"}; may be {@code null}, in which
     *                 case {@link #DEFAULT_CONTENT_TYPE} is returned.
     * @return the MIME type corresponding to {@code fileName}'s extension
     *         (case-insensitive comparison); if {@code fileName} is
     *         {@code null}, has no extension, or the extension is unknown,
     *         {@link #DEFAULT_CONTENT_TYPE} ({@code "application/octet-stream"})
     *         is returned.
     */
    public static String detect(String fileName) {
        if (fileName == null) {
            return DEFAULT_CONTENT_TYPE;
        }

        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex < 0 || dotIndex == fileName.length() - 1) {
            return DEFAULT_CONTENT_TYPE;
        }

        String extension = fileName.substring(dotIndex + 1).toLowerCase(Locale.ROOT);
        return CONTENT_TYPES_BY_EXTENSION.getOrDefault(extension, DEFAULT_CONTENT_TYPE);
    }
}
