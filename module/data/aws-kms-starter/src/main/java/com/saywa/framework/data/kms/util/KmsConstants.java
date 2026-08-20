package com.saywa.framework.data.kms.util;

/**
 * Shared constants for the {@code aws-kms-starter} module.
 */
public final class KmsConstants {

    /**
     * Prefix under which {@code KmsConfiguration} is mapped by
     * Quarkus/SmallRye Config (e.g. {@code compartamos.crypto.kms.default-key-ref}).
     */
    public static final String CONFIG_PREFIX = "compartamos.crypto.kms";

    /** Generic message used by {@code KmsExceptionMapper} for unclassified SDK failures. */
    public static final String KMS_REQUEST_FAILED_MESSAGE = "The request to AWS KMS failed";

    /** Hard limit KMS enforces on the {@code Encrypt}/{@code Decrypt} plaintext for symmetric keys. */
    public static final int MAX_DIRECT_PLAINTEXT_BYTES = 4096;

    /** {@link #MAX_DIRECT_PLAINTEXT_BYTES} as a {@code String}, for {@code @WithDefault}, which only accepts compile-time constant strings. */
    public static final String MAX_DIRECT_PLAINTEXT_BYTES_DEFAULT = "4096";

    /** Hard limit KMS enforces on the message passed to {@code GenerateMac}/{@code VerifyMac}. */
    public static final int MAX_MAC_MESSAGE_BYTES = 4096;

    /** {@link #MAX_MAC_MESSAGE_BYTES} as a {@code String}, for {@code @WithDefault}. */
    public static final String MAX_MAC_MESSAGE_BYTES_DEFAULT = "4096";

    /** Exclusive lower bound for a valid size configuration (bytes must be strictly greater than this). */
    public static final long MIN_SIZE_BYTES = 0L;

    private KmsConstants() {
        // Utility class: not instantiable.
    }
}
