package com.saywa.framework.deployment.s3.service;

import com.saywa.framework.deployment.s3.config.DeploymentS3Configuration;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.nio.file.Path;

/**
 * Resolves the file path requested for a server-side load-by-path upload
 * ({@code objectFileToLoad}) against a configured base directory
 * ({@code deployment.s3.upload-base-dir}), rejecting anything that escapes
 * it.
 * <p>
 * This is the only guard against path-traversal / arbitrary local file
 * read: {@code objectFileToLoad} is client-supplied, so a bare filename
 * (the normal case, when the file lives directly in the base directory)
 * must resolve inside it, and any attempt to escape (e.g. {@code
 * ../../etc/passwd}, an absolute path pointing elsewhere, or a symlink
 * that resolves outside) must be rejected before the caller ever touches
 * the filesystem.
 */
@ApplicationScoped
public class S3UploadPathResolver {

    private final Path baseDir;

    @Inject
    public S3UploadPathResolver(DeploymentS3Configuration configuration) {
        this.baseDir = Path.of(configuration.uploadBaseDir()).toAbsolutePath().normalize();
    }

    /**
     * Convenience constructor to build a resolver against an arbitrary base
     * directory without going through CDI/config — used by unit tests, and
     * available for any programmatic construction outside a CDI container.
     */
    public S3UploadPathResolver(Path baseDir) {
        this.baseDir = baseDir.toAbsolutePath().normalize();
    }

    /**
     * Resolves {@code requestedPath} against the configured base directory.
     *
     * @param requestedPath filename or relative path requested by the
     *                      caller; must not be {@code null} or blank.
     * @return the resolved, absolute, normalized {@link Path}, guaranteed
     *         to be located inside the configured base directory.
     * @throws IllegalArgumentException if {@code requestedPath} is
     *                                   {@code null}/blank, or if it
     *                                   resolves outside the base
     *                                   directory.
     */
    public Path resolve(String requestedPath) {
        if (requestedPath == null || requestedPath.isBlank()) {
            throw new IllegalArgumentException("objectFileToLoad must not be null or blank");
        }

        Path resolved = baseDir.resolve(requestedPath).normalize();

        if (!resolved.startsWith(baseDir)) {
            throw new IllegalArgumentException(
                    "objectFileToLoad must resolve inside the configured upload base directory");
        }

        return resolved;
    }
}
