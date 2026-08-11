package com.saywa.framework.data.s3.util;

/**
 * General-purpose utilities for manipulating S3 object keys and prefixes,
 * without depending on {@code S3Configuration} or any AWS SDK type.
 *
 * <p>An independent, reusable utility class: not a CDI bean, stateless,
 * with exclusively static methods. Does not replace
 * {@code factory/S3RequestFactory#normalizeKey(String)}, which additionally
 * resolves the starter's configured {@code defaultPrefix}; this class only
 * offers basic string manipulation operations on keys and prefixes, useful
 * both inside and outside this module.</p>
 */
public final class S3KeyUtils {

    private S3KeyUtils() {
        // Utility class: not instantiable.
    }

    /**
     * Removes the leading slash ({@code '/'}) from a key, if it has one.
     *
     * @param value key (or other string) to remove the leading slash from;
     *              must not be {@code null}.
     * @return {@code value} without the leading slash; if {@code value}
     *         doesn't start with {@code '/'}, it is returned as-is.
     * @throws IllegalArgumentException if {@code value} is {@code null}.
     */
    public static String removeLeadingSlash(String value) {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }
        if (value.startsWith(S3Constants.PATH_SEPARATOR)) {
            return value.substring(1);
        }
        return value;
    }

    /**
     * Adds a trailing slash ({@code '/'}) to a prefix, if it doesn't
     * already have one.
     *
     * @param prefix prefix to ensure a trailing slash on; must not be
     *               {@code null}.
     * @return {@code prefix} ending in {@code '/'}; if {@code prefix}
     *         already ends in {@code '/'}, it is returned as-is (including
     *         the empty string, which is returned unmodified).
     * @throws IllegalArgumentException if {@code prefix} is {@code null}.
     */
    public static String ensureTrailingSlash(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("prefix must not be null");
        }
        if (prefix.isEmpty() || prefix.endsWith(S3Constants.PATH_SEPARATOR)) {
            return prefix;
        }
        return prefix + S3Constants.PATH_SEPARATOR;
    }
}
