package integration.com.saywa.framework.deployment.s3;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * End-to-end test against real LocalStack (no mocks). Requires:
 * <pre>
 *   docker compose up -d   # LocalStack running and bucket created
 *   mvn verify -Pintegration-tests
 * </pre>
 * Covers the full cycle of the 8 {@code S3StorageService} operations
 * through the {@code S3Resource} HTTP resource.
 */
@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class S3ResourceIT {

    // Logical keys used in the requests: compartamos.storage.s3.default-prefix=poc
    // (application.properties) makes the starter prepend "poc/" to them when
    // normalizing (S3RequestFactory#normalizeKey) -- that's why the responses
    // return the already-prefixed key even though the requests use the logical one.
    private static final String OBJECT_KEY = "it/hello.txt";
    private static final String COPY_KEY = "it/hello-copy.txt";
    private static final String MOVED_KEY = "it/hello-moved.txt";
    private static final String CONTENT = "hello from the integration test";

    private static final String OBJECT_KEY_PREFIXED = "poc/" + OBJECT_KEY;
    private static final String COPY_KEY_PREFIXED = "poc/" + COPY_KEY;
    private static final String MOVED_KEY_PREFIXED = "poc/" + MOVED_KEY;

    @Test
    @Order(1)
    void uploadCreaElObjeto() {
        given()
                .contentType(ContentType.MULTIPART)
                .multiPart("file", "hello.txt", CONTENT.getBytes(), "text/plain")
                .queryParam("objectKey", OBJECT_KEY)
                .when().post("/s3/objects")
                .then()
                .statusCode(200)
                .body("objectKey", equalTo(OBJECT_KEY_PREFIXED));
    }

    @Test
    @Order(2)
    void existsDevuelveTrueTrasUpload() {
        given()
                .when().get("/s3/objects/{key}/exists", OBJECT_KEY)
                .then()
                .statusCode(200)
                .body(equalTo("true"));
    }

    @Test
    @Order(3)
    void listIncluyeElObjetoSubido() {
        // Unlike individual keys, the listing prefix is sent as-is to the
        // SDK when the caller specifies it explicitly
        // (S3RequestFactory#resolvePrefix) -- default-prefix is not prepended to it.
        given()
                .queryParam("prefix", "poc/it/")
                .when().get("/s3/objects")
                .then()
                .statusCode(200)
                .body("objectKey", hasItem(OBJECT_KEY_PREFIXED))
                .body("size", hasItem(greaterThanOrEqualTo(0)));
    }

    @Test
    @Order(4)
    void downloadDevuelveElContenidoSubido() {
        given()
                .when().get("/s3/objects/{key}", OBJECT_KEY)
                .then()
                .statusCode(200)
                .body(equalTo(CONTENT));
    }

    @Test
    @Order(5)
    void downloadDeClaveInexistenteDevuelve404() {
        given()
                .when().get("/s3/objects/{key}", "it/no-existe.txt")
                .then()
                .statusCode(404)
                .body("error", equalTo("StorageObjectNotFoundException"));
    }

    @Test
    @Order(6)
    void copyDuplicaElObjeto() {
        given()
                .queryParam("destinationKey", COPY_KEY)
                .when().post("/s3/objects/{key}/copy", OBJECT_KEY)
                .then()
                .statusCode(200)
                .body("objectKey", equalTo(COPY_KEY_PREFIXED));
    }

    @Test
    @Order(7)
    void moveTrasladaElObjetoCopiado() {
        given()
                .queryParam("destinationKey", MOVED_KEY)
                .when().post("/s3/objects/{key}/move", COPY_KEY)
                .then()
                .statusCode(200)
                .body("objectKey", equalTo(MOVED_KEY_PREFIXED));

        given()
                .when().get("/s3/objects/{key}/exists", COPY_KEY)
                .then()
                .statusCode(200)
                .body(equalTo("false"));
    }

    @Test
    @Order(8)
    void presignedDevuelveUnaUrlHttp() {
        given()
                .queryParam("ttlSeconds", 120)
                .when().get("/s3/objects/{key}/presigned", OBJECT_KEY)
                .then()
                .statusCode(200)
                .body(org.hamcrest.Matchers.containsString("http"));
    }

    @Test
    @Order(9)
    void deleteEliminaTodosLosObjetosDePrueba() {
        for (String key : new String[] { OBJECT_KEY, MOVED_KEY }) {
            given()
                    .when().delete("/s3/objects/{key}", key)
                    .then()
                    .statusCode(204);
        }
    }
}
