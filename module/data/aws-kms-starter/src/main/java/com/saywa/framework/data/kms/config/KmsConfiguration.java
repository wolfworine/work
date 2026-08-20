package com.saywa.framework.data.kms.config;

import com.saywa.framework.data.kms.util.KmsConstants;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;

/**
 * Configuration for the {@code aws-kms-starter}, mapped by Quarkus/SmallRye
 * Config under the {@code compartamos.crypto.kms} prefix. The consuming
 * application overrides these values in its {@code application.properties}
 * (for example {@code compartamos.crypto.kms.default-key-ref=alias/my-key}).
 *
 * <p>This interface is a SmallRye Config {@code @ConfigMapping}, resolved by
 * CDI at startup; it must not be implemented manually.</p>
 */
@ConfigMapping(prefix = KmsConstants.CONFIG_PREFIX)
public interface KmsConfiguration {

    /**
     * Key id, ARN, or alias used as a convenience default by consuming
     * applications that only ever operate against a single CMK; not
     * enforced by the starter itself — every {@code *Request} record still
     * requires its own {@code keyRef} explicitly.
     *
     * @return the default key reference; empty string by default (no default configured).
     */
    @WithDefault("")
    @WithName("default-key-ref")
    String defaultKeyRef();

    /**
     * Maximum plaintext size, in bytes, this starter allows for direct
     * {@code encrypt()}/{@code decrypt()} (KMS's {@code Encrypt} operation).
     * Operators may lower this below the AWS hard limit of 4096 bytes; the
     * starter never allows a value above it.
     *
     * @return the maximum direct-encrypt plaintext size in bytes; 4096 by default.
     */
    @WithDefault(KmsConstants.MAX_DIRECT_PLAINTEXT_BYTES_DEFAULT)
    @WithName("max-direct-plaintext-size")
    int maxDirectPlaintextSize();

    /**
     * Maximum message size, in bytes, this starter allows for
     * {@code sign()}/{@code verify()} (KMS's {@code GenerateMac}/
     * {@code VerifyMac} operations). Operators may lower this below the AWS
     * hard limit of 4096 bytes; the starter never allows a value above it.
     *
     * @return the maximum MAC message size in bytes; 4096 by default.
     */
    @WithDefault(KmsConstants.MAX_MAC_MESSAGE_BYTES_DEFAULT)
    @WithName("max-mac-message-size")
    int maxMacMessageSize();

    /**
     * Whether each crypto operation emits a {@code KmsAuditEvent} via
     * {@code KmsAuditLogger}.
     *
     * @return {@code true} if audit events are emitted; {@code true} by default.
     */
    @WithDefault("true")
    @WithName("audit-enabled")
    boolean auditEnabled();
}
