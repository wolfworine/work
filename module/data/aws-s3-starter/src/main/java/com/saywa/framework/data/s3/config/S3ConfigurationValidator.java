package com.saywa.framework.data.s3.config;

import com.saywa.framework.data.s3.util.S3Constants;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.Duration;

/**
 * Validates, when the consuming application starts up, that the
 * {@link S3Configuration} resolved by Quarkus/SmallRye Config is
 * semantically correct before the starter begins operating against Amazon
 * S3.
 *
 * <p>Typically invoked from a method annotated with {@code @PostConstruct}
 * in {@code S3StorageServiceImpl}, so that an invalid configuration fails
 * fast and explicitly at startup, instead of manifesting as confusing
 * errors at runtime.</p>
 */
@ApplicationScoped
public class S3ConfigurationValidator {

    private final S3Configuration configuration;

    /**
     * Creates the validator, injecting the configuration to validate.
     *
     * @param configuration configuration resolved by CDI; never {@code null}
     *                       in a running Quarkus container.
     */
    @Inject
    public S3ConfigurationValidator(S3Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * Validates the injected configuration and throws
     * {@link IllegalArgumentException} as soon as it detects the first
     * invalid condition, in this order:
     * <ol>
     *   <li>{@code bucketName()} null or empty/blank.</li>
     *   <li>{@code defaultPrefix()} null.</li>
     *   <li>{@code maxUploadSize()} less than or equal to zero.</li>
     *   <li>{@code maxDownloadSize()} less than or equal to zero.</li>
     *   <li>{@code presignedTtl()} null or not strictly positive.</li>
     *   <li>{@code defaultPrefix()} contains the sequence {@code "//"}
     *       (indicating a malformed prefix with empty segments).</li>
     * </ol>
     *
     * @throws IllegalArgumentException if the configuration violates any of
     *                                   the rules above.
     */
    public void validate() {
        String bucketName = configuration.bucketName();
        if (bucketName == null || bucketName.isBlank()) {
            throw new IllegalArgumentException(
                    S3Constants.CONFIG_PREFIX + ".bucket-name must not be null or empty");
        }

        String defaultPrefix = configuration.defaultPrefix();
        if (defaultPrefix == null) {
            throw new IllegalArgumentException(
                    S3Constants.CONFIG_PREFIX + ".default-prefix must not be null");
        }

        if (configuration.maxUploadSize() <= S3Constants.MIN_SIZE_BYTES) {
            throw new IllegalArgumentException(
                    S3Constants.CONFIG_PREFIX + ".max-upload-size must be greater than zero");
        }

        if (configuration.maxDownloadSize() <= S3Constants.MIN_SIZE_BYTES) {
            throw new IllegalArgumentException(
                    S3Constants.CONFIG_PREFIX + ".max-download-size must be greater than zero");
        }

        Duration presignedTtl = configuration.presignedTtl();
        if (presignedTtl == null || !presignedTtl.isPositive()) {
            throw new IllegalArgumentException(
                    S3Constants.CONFIG_PREFIX + ".presigned-ttl must be a positive duration");
        }

        if (defaultPrefix.contains(S3Constants.DOUBLE_PATH_SEPARATOR)) {
            throw new IllegalArgumentException(
                    S3Constants.CONFIG_PREFIX + ".default-prefix must not contain '" + S3Constants.DOUBLE_PATH_SEPARATOR + "'");
        }
    }
}
