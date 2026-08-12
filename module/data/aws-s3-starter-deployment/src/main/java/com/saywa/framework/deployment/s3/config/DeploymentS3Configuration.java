package com.saywa.framework.deployment.s3.config;

import io.smallrye.config.ConfigMapping;
import jakarta.validation.constraints.NotBlank;

/**
 * Configuration owned by this deployment module (not the starter),
 * mapped under the {@code deployment.s3} prefix — mirrors the pattern
 * {@code aws-s3-starter}'s own {@code S3Configuration} already uses,
 * instead of injecting loose {@code @ConfigProperty} strings.
 */
@ConfigMapping(prefix = "deployment.s3")
public interface DeploymentS3Configuration {

    /**
     * Base directory {@link com.saywa.framework.deployment.s3.service.S3UploadPathResolver}
     * resolves server-side load-by-path uploads ({@code objectFileToLoad})
     * against; a bare filename is resolved directly inside it.
     *
     * @return the configured base directory path; never {@code null} or
     *         blank.
     */
    @NotBlank
    String uploadBaseDir();
}
