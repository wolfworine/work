package com.saywa.framework.data.s3.buildtime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.builditem.RunTimeConfigurationDefaultBuildItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

/**
 * Pure unit test of {@link S3DefaultConfigProcessor#applyS3Default}: no
 * Quarkus bootstrap, no augmentation, no LocalStack — {@link BuildProducer}
 * is a plain functional interface, so it's faked with a collecting lambda.
 * Verifies exactly the values a real Quarkus build would receive from this
 * build step; it does not prove they actually get applied to a consuming
 * application's config (that requires real augmentation — see
 * {@code aws-s3-starter-smoke}, which exercises it end to end against
 * LocalStack).
 */
class S3DefaultConfigProcessorTest {

    @Test
    void givenApplyS3Default_whenInvoked_thenProducesExpectedDefaults() {
        List<RunTimeConfigurationDefaultBuildItem> produced = new ArrayList<>();
        BuildProducer<RunTimeConfigurationDefaultBuildItem> producer = produced::add;

        new S3DefaultConfigProcessor().applyS3Default(producer);

        Map<String, String> byKey = produced.stream()
                .collect(Collectors.toMap(RunTimeConfigurationDefaultBuildItem::getKey,
                        RunTimeConfigurationDefaultBuildItem::getValue));

        assertEquals(5, produced.size());
        assertEquals("saywa-s3-deployment-poc", byKey.get("compartamos.storage.s3.bucket-name"));
        assertEquals("poc", byKey.get("compartamos.storage.s3.default-prefix"));
        assertEquals("20971520", byKey.get("compartamos.storage.s3.max-upload-size"));
        assertEquals("20971520", byKey.get("compartamos.storage.s3.max-download-size"));
        assertEquals("PT10M", byKey.get("compartamos.storage.s3.presigned-ttl"));
    }
}
