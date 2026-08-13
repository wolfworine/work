package com.saywa.framework.data.s3.buildtime;

import io.quarkus.deployment.IsProduction;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.RunTimeConfigurationDefaultBuildItem;

/**
 * Supplies default values for {@code compartamos.storage.s3.*} (the
 * config {@code aws-s3-starter}'s {@code S3Configuration} maps) so that a
 * consuming application doesn't have to declare them explicitly to run in
 * dev/test mode.
 * <p>
 * Only real Quarkus extensions (an artifact with
 * {@code META-INF/quarkus-extension.properties} pointing here as its
 * {@code deployment-artifact}) get this build step executed — it never
 * runs for a plain library dependency. This class itself is never shipped
 * to any consumer's runtime jar: it lives in the deployment-only artifact
 * {@code aws-s3-starter-buildtime}, used exclusively during augmentation
 * ({@code mvn quarkus:dev}, {@code mvn package}).
 * <p>
 * {@code RunTimeConfigurationDefaultBuildItem} registers a lowest-priority
 * default: any value the consuming application declares in its own
 * {@code application.properties} (or an env var, system property, etc.)
 * still wins.
 * <p>
 * Gated with {@code onlyIfNot = IsProduction.class} on purpose: a real
 * production deployment must declare its own bucket explicitly, never
 * silently fall back to the PoC bucket name below.
 */
public class S3DefaultConfigProcessor {

    @BuildStep(onlyIfNot = IsProduction.class)
    void applyS3Default(BuildProducer<RunTimeConfigurationDefaultBuildItem> configProducer) {
        configProducer.produce(new RunTimeConfigurationDefaultBuildItem(
                "compartamos.storage.s3.bucket-name", "saywa-s3-deployment-poc"));
        configProducer.produce(new RunTimeConfigurationDefaultBuildItem(
                "compartamos.storage.s3.default-prefix", "poc"));
        configProducer.produce(new RunTimeConfigurationDefaultBuildItem(
                "compartamos.storage.s3.max-upload-size", "20971520"));
        configProducer.produce(new RunTimeConfigurationDefaultBuildItem(
                "compartamos.storage.s3.max-download-size", "20971520"));
        configProducer.produce(new RunTimeConfigurationDefaultBuildItem(
                "compartamos.storage.s3.presigned-ttl", "PT10M"));
    }
}
