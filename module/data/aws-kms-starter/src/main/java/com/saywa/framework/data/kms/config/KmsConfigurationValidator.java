package com.saywa.framework.data.kms.config;

import com.saywa.framework.data.kms.util.KmsConstants;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Validates, when the consuming application starts up, that the
 * {@link KmsConfiguration} resolved by Quarkus/SmallRye Config is
 * semantically correct before the starter begins operating against AWS
 * KMS.
 *
 * <p>Typically invoked from a method annotated with {@code @PostConstruct}
 * in {@code KmsCryptoServiceImpl}, so that an invalid configuration fails
 * fast and explicitly at startup, instead of manifesting as confusing
 * errors at runtime.</p>
 */
@ApplicationScoped
public class KmsConfigurationValidator {

    private final KmsConfiguration configuration;

    /**
     * Creates the validator, injecting the configuration to validate.
     *
     * @param configuration configuration resolved by CDI; never {@code null}
     *                      in a running Quarkus container.
     */
    @Inject
    public KmsConfigurationValidator(KmsConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Validates the injected configuration and throws
     * {@link IllegalArgumentException} as soon as it detects the first
     * invalid condition, in this order:
     * <ol>
     *   <li>{@code defaultKeyRef()} is {@code null} (blank/empty is allowed — it's optional).</li>
     *   <li>{@code maxDirectPlaintextSize()} not strictly positive.</li>
     *   <li>{@code maxDirectPlaintextSize()} greater than the AWS hard limit (4096 bytes).</li>
     *   <li>{@code maxMacMessageSize()} not strictly positive.</li>
     *   <li>{@code maxMacMessageSize()} greater than the AWS hard limit (4096 bytes).</li>
     * </ol>
     *
     * @throws IllegalArgumentException if the configuration violates any of the rules above.
     */
    public void validate() {
        if (configuration.defaultKeyRef() == null) {
            throw new IllegalArgumentException(
                    KmsConstants.CONFIG_PREFIX + ".default-key-ref must not be null");
        }

        if (configuration.maxDirectPlaintextSize() <= KmsConstants.MIN_SIZE_BYTES) {
            throw new IllegalArgumentException(
                    KmsConstants.CONFIG_PREFIX + ".max-direct-plaintext-size must be greater than zero");
        }
        if (configuration.maxDirectPlaintextSize() > KmsConstants.MAX_DIRECT_PLAINTEXT_BYTES) {
            throw new IllegalArgumentException(
                    KmsConstants.CONFIG_PREFIX + ".max-direct-plaintext-size must not exceed "
                            + KmsConstants.MAX_DIRECT_PLAINTEXT_BYTES + " (AWS KMS hard limit)");
        }

        if (configuration.maxMacMessageSize() <= KmsConstants.MIN_SIZE_BYTES) {
            throw new IllegalArgumentException(
                    KmsConstants.CONFIG_PREFIX + ".max-mac-message-size must be greater than zero");
        }
        if (configuration.maxMacMessageSize() > KmsConstants.MAX_MAC_MESSAGE_BYTES) {
            throw new IllegalArgumentException(
                    KmsConstants.CONFIG_PREFIX + ".max-mac-message-size must not exceed "
                            + KmsConstants.MAX_MAC_MESSAGE_BYTES + " (AWS KMS hard limit)");
        }
    }
}
