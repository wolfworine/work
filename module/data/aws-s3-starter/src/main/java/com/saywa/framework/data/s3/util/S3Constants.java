package com.saywa.framework.data.s3.util;

/**
 * Centralized constants for magic strings and numbers repeated across
 * {@code aws-s3-starter}, so each value is defined exactly once here and
 * referenced from every call site instead of being duplicated inline.
 *
 * <p>An independent, reusable utility class: not a CDI bean, stateless, with
 * no methods — only {@code public static final} fields.</p>
 */
public final class S3Constants {

    /**
     * Prefix under which {@code S3Configuration} is mapped by
     * Quarkus/SmallRye Config (e.g. {@code compartamos.storage.s3.bucket-name}).
     */
    public static final String CONFIG_PREFIX = "compartamos.storage.s3";

    /**
     * Default value (10 MiB, in bytes) for both {@code max-upload-size} and
     * {@code max-download-size} — must be a {@code String} because
     * {@code @WithDefault} only accepts compile-time constant strings.
     */
    public static final String DEFAULT_MAX_OBJECT_SIZE_BYTES = "10485760";

    /**
     * Generic fallback message used by {@code exception/StorageExceptionMapper}
     * when a failure cannot be mapped to a more specific storage exception.
     */
    public static final String S3_REQUEST_FAILED_MESSAGE = "S3 request failed";

    /** Path separator used to join key/prefix segments (e.g. {@code "docs" + PATH_SEPARATOR + "file.json"}). */
    public static final String PATH_SEPARATOR = "/";

    /** Two consecutive {@link #PATH_SEPARATOR}, indicating a malformed prefix with an empty segment. */
    public static final String DOUBLE_PATH_SEPARATOR = PATH_SEPARATOR + PATH_SEPARATOR;

    /** Empty string, returned when there is no prefix to apply. */
    public static final String EMPTY_STRING = "";

    /** Exclusive lower bound for a valid size configuration (bytes must be strictly greater than this). */
    public static final long MIN_SIZE_BYTES = 0L;

    private S3Constants() {
        // Utility class: not instantiable.
    }
}
