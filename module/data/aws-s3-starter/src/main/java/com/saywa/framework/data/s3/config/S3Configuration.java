package com.saywa.framework.data.s3.config;

import com.saywa.framework.data.s3.util.S3Constants;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;
import java.time.Duration;

/**
 * Configuration for the {@code aws-s3-starter}, mapped by Quarkus/SmallRye
 * Config under the {@code compartamos.storage.s3} prefix. The consuming
 * application overrides these values in its {@code application.properties}
 * (for example {@code compartamos.storage.s3.bucket-name=my-bucket}).
 *
 * <p>This interface is a SmallRye Config {@code @ConfigMapping}, resolved by
 * CDI at startup; it must not be implemented manually.</p>
 */
@ConfigMapping(prefix = S3Constants.CONFIG_PREFIX)
public interface S3Configuration {

    /**
     * Name of the Amazon S3 bucket the starter will operate on.
     * There is no default value: it must be explicitly configured by the
     * consuming application.
     *
     * @return the bucket name; should never be {@code null} or empty in a
     * valid configuration (verified by {@link S3ConfigurationValidator}).
     */
    @WithName("bucket-name")
    String bucketName();

    /**
     * Default prefix prepended to object keys when the operation doesn't
     * specify its own (used for example when normalizing keys or listing
     * objects without an explicit prefix).
     *
     * @return the default prefix; never {@code null}. Empty string by
     * default, which is equivalent to "no prefix".
     */
    @WithDefault("")
    @WithName("default-prefix")
    String defaultPrefix();

    /**
     * Maximum size, in bytes, allowed for the content of an upload to S3.
     * If the content exceeds this limit, the operation fails before
     * invoking the SDK.
     *
     * @return the maximum upload size in bytes; 10 MiB (10485760) by
     *         default.
     */
    @WithDefault(S3Constants.DEFAULT_MAX_OBJECT_SIZE_BYTES)
    @WithName("max-upload-size")
    long maxUploadSize();

    /**
     * Maximum size, in bytes, allowed for the content of a download from
     * S3. Validated against the {@code contentLength} obtained via
     * {@code headObject} before transferring the bytes.
     *
     * @return the maximum download size in bytes; 10 MiB (10485760) by
     *         default.
     */
    @WithDefault(S3Constants.DEFAULT_MAX_OBJECT_SIZE_BYTES)
    @WithName("max-download-size")
    long maxDownloadSize();

    /**
     * Default time-to-live (TTL) for presigned URLs generated when the
     * {@code presigned} operation doesn't specify an explicit duration.
     *
     * @return the default duration of presigned URLs; 15 minutes
     *         ({@code PT15M}, {@link Duration} ISO-8601 format) by default.
     */
    @WithDefault("PT15M")
    @WithName("presigned-ttl")
    Duration presignedTtl();
}
