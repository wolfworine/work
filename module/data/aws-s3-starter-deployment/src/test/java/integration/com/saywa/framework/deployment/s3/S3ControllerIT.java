package integration.com.saywa.framework.deployment.s3;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * End-to-end test against real LocalStack (no mocks). Requires:
 * <pre>
 *   docker compose up -d   # LocalStack running, both buckets created
 *   mvn verify -Pintegration-tests
 * </pre>
 * Covers the {@code S3StorageService} operations through {@code S3Controller}
 * (base path {@code /shrd/s3}), both upload modes (in-memory multipart and
 * server-side load-by-path), and that {@code bucketName} is a genuine
 * per-request choice — not a fallback to a configured default.
 */
@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class S3ControllerIT {

    // compartamos.storage.s3.default-prefix=poc (application.properties)
    // still prepends "poc/" to logical keys when normalizing, regardless of
    // which bucket is targeted.
    private static final String BUCKET = "saywa-s3-deployment-poc";
    private static final String SECONDARY_BUCKET = "saywa-s3-deployment-poc-secondary";

    private static final String OBJECT_KEY = "it/hello.txt";
    private static final String COPY_KEY = "it/hello-copy.txt";
    private static final String MOVED_KEY = "it/hello-moved.txt";
    private static final String PATH_UPLOAD_KEY = "it/from-path.txt";
    private static final String SECONDARY_KEY = "it/secondary.txt";
    private static final String CONTENT = "hello from the integration test";

    private static final String OBJECT_KEY_PREFIXED = "poc/" + OBJECT_KEY;
    private static final String COPY_KEY_PREFIXED = "poc/" + COPY_KEY;
    private static final String MOVED_KEY_PREFIXED = "poc/" + MOVED_KEY;
    private static final String SECONDARY_KEY_PREFIXED = "poc/" + SECONDARY_KEY;

    @Test
    @Order(1)
    void uploadInMemoryCreaElObjeto() {
        given()
                .contentType(ContentType.MULTIPART)
                .multiPart("file", "hello.txt", CONTENT.getBytes(), "text/plain")
                .multiPart("bucketName", BUCKET)
                .multiPart("objectKey", OBJECT_KEY)
                .when().post("/shrd/s3/bucket")
                .then()
                .statusCode(200)
                .body("objectKey", equalTo(OBJECT_KEY_PREFIXED))
                .body("bucketName", equalTo(BUCKET));
    }

    @Test
    @Order(2)
    void uploadFromPathCreaElObjeto() {
        given()
                .contentType(ContentType.JSON)
                .body("""
                        {"bucketName":"%s","objectKey":"%s","objectFileToLoad":"sample.txt"}
                        """.formatted(BUCKET, PATH_UPLOAD_KEY))
                .when().post("/shrd/s3/bucket/path")
                .then()
                .statusCode(200)
                .body("objectKey", equalTo("poc/" + PATH_UPLOAD_KEY))
                .body("bucketName", equalTo(BUCKET));
    }

    @Test
    @Order(3)
    void uploadFromPathConTraversalEsRechazado() {
        given()
                .contentType(ContentType.JSON)
                .body("""
                        {"bucketName":"%s","objectKey":"it/should-not-exist.txt","objectFileToLoad":"../../../../etc/passwd"}
                        """.formatted(BUCKET))
                .when().post("/shrd/s3/bucket/path")
                .then()
                .statusCode(400);
    }

    @Test
    @Order(4)
    void existsDevuelveTrueTrasUpload() {
        given()
                .queryParam("bucketName", BUCKET)
                .when().get("/shrd/s3/bucket/{key}/exists", OBJECT_KEY)
                .then()
                .statusCode(200)
                .body(equalTo("true"));
    }

    @Test
    @Order(5)
    void listIncluyeElObjetoSubido() {
        // Como con list explícito no se antepone default-prefix, se manda ya prefijado.
        given()
                .queryParam("bucketName", BUCKET)
                .queryParam("prefix", "poc/it/")
                .when().get("/shrd/s3/bucket")
                .then()
                .statusCode(200)
                .body("objectKey", hasItem(OBJECT_KEY_PREFIXED));
    }

    @Test
    @Order(6)
    void downloadDevuelveElContenidoSubido() {
        given()
                .queryParam("bucketName", BUCKET)
                .when().get("/shrd/s3/bucket/{key}", OBJECT_KEY)
                .then()
                .statusCode(200)
                .body(equalTo(CONTENT));
    }

    @Test
    @Order(7)
    void downloadDeClaveInexistenteDevuelve404() {
        given()
                .queryParam("bucketName", BUCKET)
                .when().get("/shrd/s3/bucket/{key}", "it/no-existe.txt")
                .then()
                .statusCode(404)
                .body("error", equalTo("StorageObjectNotFoundException"));
    }

    @Test
    @Order(8)
    void copyDuplicaElObjeto() {
        given()
                .queryParam("bucketName", BUCKET)
                .queryParam("destinationKey", COPY_KEY)
                .when().post("/shrd/s3/bucket/{key}/copy", OBJECT_KEY)
                .then()
                .statusCode(200)
                .body("objectKey", equalTo(COPY_KEY_PREFIXED))
                .body("bucketName", equalTo(BUCKET));
    }

    @Test
    @Order(9)
    void moveTrasladaElObjetoCopiado() {
        given()
                .queryParam("bucketName", BUCKET)
                .queryParam("destinationKey", MOVED_KEY)
                .when().post("/shrd/s3/bucket/{key}/move", COPY_KEY)
                .then()
                .statusCode(200)
                .body("objectKey", equalTo(MOVED_KEY_PREFIXED));

        given()
                .queryParam("bucketName", BUCKET)
                .when().get("/shrd/s3/bucket/{key}/exists", COPY_KEY)
                .then()
                .statusCode(200)
                .body(equalTo("false"));
    }

    @Test
    @Order(10)
    void presignedDevuelveUnaUrlHttp() {
        given()
                .queryParam("bucketName", BUCKET)
                .queryParam("ttlSeconds", 120)
                .when().get("/shrd/s3/bucket/{key}/presigned-url", OBJECT_KEY)
                .then()
                .statusCode(200)
                .body("url", org.hamcrest.Matchers.startsWith("http"));
    }

    @Test
    @Order(11)
    void bucketEsUnaEleccionPorRequestNoUnDefaultImplicito() {
        // Sube al bucket secundario...
        given()
                .contentType(ContentType.MULTIPART)
                .multiPart("file", "secondary.txt", "secondary bucket content".getBytes(), "text/plain")
                .multiPart("bucketName", SECONDARY_BUCKET)
                .multiPart("objectKey", SECONDARY_KEY)
                .when().post("/shrd/s3/bucket")
                .then()
                .statusCode(200)
                .body("bucketName", equalTo(SECONDARY_BUCKET));

        // ... aparece listando el bucket secundario...
        given()
                .queryParam("bucketName", SECONDARY_BUCKET)
                .queryParam("prefix", "poc/it/")
                .when().get("/shrd/s3/bucket")
                .then()
                .statusCode(200)
                .body("objectKey", hasItem(SECONDARY_KEY_PREFIXED));

        // ... pero NO en el bucket por defecto.
        given()
                .queryParam("bucketName", BUCKET)
                .queryParam("prefix", "poc/it/")
                .when().get("/shrd/s3/bucket")
                .then()
                .statusCode(200)
                .body("objectKey", not(hasItem(SECONDARY_KEY_PREFIXED)));

        given()
                .queryParam("bucketName", SECONDARY_BUCKET)
                .when().delete("/shrd/s3/bucket/{key}", SECONDARY_KEY)
                .then()
                .statusCode(204);
    }

    @Test
    @Order(12)
    void bucketNameFaltanteDevuelve400() {
        given()
                .when().get("/shrd/s3/bucket/{key}/exists", OBJECT_KEY)
                .then()
                .statusCode(400);
    }

    @Test
    @Order(99)
    void limpieza() {
        for (String key : new String[] {OBJECT_KEY, MOVED_KEY, PATH_UPLOAD_KEY}) {
            given()
                    .queryParam("bucketName", BUCKET)
                    .when().delete("/shrd/s3/bucket/{key}", key)
                    .then()
                    .statusCode(204);
        }
    }
}
