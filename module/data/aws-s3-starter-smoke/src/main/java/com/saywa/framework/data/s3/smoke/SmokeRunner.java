package com.saywa.framework.data.s3.smoke;

import com.saywa.framework.core.error.exceptions.StorageException;
import com.saywa.framework.data.s3.domain.S3ObjectRequest;
import com.saywa.framework.data.s3.domain.S3ObjectResponse;
import com.saywa.framework.data.s3.service.S3StorageService;
import io.quarkus.runtime.StartupEvent;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Manual smoke check for {@code S3DefaultConfigProcessor}
 * (aws-s3-starter-buildtime): this module declares no
 * {@code compartamos.storage.s3.*} property of its own, so simply reaching
 * {@link #onStart} without {@code S3ConfigurationValidator} throwing at
 * startup already proves the build step's
 * {@code RunTimeConfigurationDefaultBuildItem}s were applied.
 * <p>
 * Also exercises {@code bucketName} as a genuinely per-call parameter
 * (never a config-driven default) by hitting two different buckets that
 * {@code aws-s3-starter-deployment}'s {@code localstack-init} already
 * creates: {@code saywa-s3-deployment-poc} (upload + download) and
 * {@code saywa-s3-deployment-poc-secondary} (upload + copy, same-bucket —
 * {@code S3StorageService#copy} does not support cross-bucket copies).
 * Not a JUnit test: run with {@code mvn quarkus:dev} and read the console.
 */
@ApplicationScoped
public class SmokeRunner {

    private static final String PRIMARY_BUCKET = "saywa-s3-deployment-poc";
    private static final String SECONDARY_BUCKET = "saywa-s3-deployment-poc-secondary";

    @Inject
    S3StorageService s3StorageService;

    void onStart(@Observes StartupEvent event) {
        System.out.println("[smoke] arranque OK sin compartamos.storage.s3.* explicito "
                + "-> S3DefaultConfigProcessor aplico los defaults del build step.");

        runPrimaryBucketCheck();
        runSecondaryBucketCheck();
    }

    private void runPrimaryBucketCheck() {
        upload(PRIMARY_BUCKET, "smoke/primary.txt", "hola desde el bucket primario")
                .chain(response -> s3StorageService.download(response.bucketName(), response.objectKey()))
                .subscribe().with(
                        content -> System.out.println("[smoke] OK bucket primario (upload+download) -> "
                                + new String(content.content(), StandardCharsets.UTF_8)),
                        failure -> report("bucket primario", failure));
    }

    private void runSecondaryBucketCheck() {
        upload(SECONDARY_BUCKET, "smoke/secondary.txt", "hola desde el bucket secundario")
                .chain(response -> s3StorageService.copy(
                        response.bucketName(), response.objectKey(), "smoke/secondary-copia.txt"))
                .subscribe().with(
                        response -> System.out.println("[smoke] OK bucket secundario (upload+copy) -> "
                                + response.bucketName() + "/" + response.objectKey()),
                        failure -> report("bucket secundario", failure));
    }

    private Uni<S3ObjectResponse> upload(String bucketName, String objectKey, String text) {
        var request = new S3ObjectRequest(bucketName, objectKey,
                text.getBytes(StandardCharsets.UTF_8), "text/plain", Map.of());
        return s3StorageService.upload(request);
    }

    private void report(String label, Throwable failure) {
        if (failure instanceof StorageException storageException) {
            System.out.println("[smoke] FALLO " + label + " -> "
                    + storageException.getClass().getSimpleName() + ": " + storageException.getMessage());
        } else {
            System.out.println("[smoke] FALLO " + label + " -> " + failure);
        }
    }
}
