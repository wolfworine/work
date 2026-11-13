---
name: quarkus-testing-standards
description: Use whenever writing or reviewing tests for a Quarkus application — @QuarkusTest, @QuarkusIntegrationTest, @TestHTTPEndpoint, REST Assured, @InjectMock / @InjectSpy / QuarkusMock, @TestProfile with QuarkusTestProfile, @TestTransaction, Dev Services (databases, Kafka, Keycloak) in tests, QuarkusTestResourceLifecycleManager, WireMock via quarkus-wiremock, test ports/timeouts, pinning tests for virtual threads, testing fault tolerance and security, continuous testing, or deciding what belongs in a unit test vs a Quarkus integration test (test pyramid).
---

# Quarkus Testing Standards

**Sources:** Quarkus "Testing your application" guide, Dev Services guides (general, databases, Kafka), Quarkiverse WireMock docs (v3.40 era), Testcontainers docs,
Martin Fowler (test pyramid). **[summary]** = extracted part of a page — verify names. **Not read:** continuous-testing guide, Testing Security guide
(`@TestSecurity` is known to exist from the Quarkus guide links but its API was not verified here), `@QuarkusMainTest` details.
For plain JUnit/Mockito/StepVerifier practice see `junit5-testing-best-practices`.

## 1. Test pyramid for Quarkus

| Level | Tool | Scope | Speed |
|---|---|---|---|
| Unit | JUnit 5 + Mockito/AssertJ, **no Quarkus boot** | One class / rule / validator | ms |
| Component/integration | `@QuarkusTest` (+ Dev Services/WireMock) | CDI wiring, REST layer, DB, Kafka | seconds |
| Packaged | `@QuarkusIntegrationTest` | The built artifact (jar/native/container) | slower |

Fowler: write tests at different granularities, "the more high-level you get, the fewer tests you should have"; push cases down the pyramid. Put business-rule matrices
in plain unit tests; use `@QuarkusTest` for wiring, REST contract, persistence mappings, config and security.

## 2. `@QuarkusTest` basics (Quarkus testing guide)

- Dependencies: `io.quarkus:quarkus-junit` (test), `io.rest-assured:rest-assured` (test; "not required but convenient").
- Tests are **full CDI beans** — `@Inject` works. Tests run in the **`test`** profile; HTTP test port **8081** by default (`quarkus.http.test-port`, `0` = random;
  `quarkus.http.test-ssl-port`); `quarkus.http.test-timeout` default 30 s.
- `@TestHTTPEndpoint(GreetingResource.class)` (`io.quarkus.test.common.http`) sets the REST Assured base path (class or method level); `@TestHTTPResource` injects a URL.
```java
@QuarkusTest
@TestHTTPEndpoint(ClaimsResource.class)
class ClaimsResourceTest {
  @Test void should_return_201_with_location_when_claim_is_valid() {
    given().contentType(JSON).body(Fixtures.text("requests/register_claim_ok.json"))
      .when().post()
      .then().statusCode(201).header("Location", containsString("/claims/"));
  }
  @Test void should_return_problem_json_when_amount_is_negative() {
    given().contentType(JSON).body(Fixtures.text("requests/register_claim_negative.json"))
      .when().post()
      .then().statusCode(400).contentType("application/problem+json");     // see quarkus-rest-api-standards
  }
}
```
Reuse your **JSON fixture** style here: request/response JSON files under `src/test/resources`, loaded from the classpath.

## 3. Mocking in `@QuarkusTest`

- **`@InjectMock`** (`io.quarkus.test.InjectMock`) replaces a bean with a Mockito mock for each test; needs `quarkus-junit-mockito` (test). Works for **normal-scoped** beans
  (`@ApplicationScoped`); for `@Singleton` use `@MockitoConfig(convertScopes = true)`. Startup-time beans can't be mocked this way — use a CDI `@Alternative`. **[summary]**
- **`@InjectSpy`** wraps the real bean (partial mock). `QuarkusMock.installMockForType/Instance` for programmatic installation **[summary]**.
- **REST clients:** `@RegisterRestClient` interfaces should be `@ApplicationScoped` to be mockable **[summary]**; better, stub the **HTTP server** with WireMock (below) so the client config and serialization are tested too.
- Prefer real beans + fake external boundaries over mocking your own services (see `junit5-testing-best-practices` §5).

## 4. Profiles and config in tests

- `@TestProfile(MyProfile.class)` with `QuarkusTestProfile`: `getConfigOverrides()` overrides config; `tags()` (default empty) matches `quarkus.test.profile.tags`. Profile CDI beans only
  apply to tests using that profile. Each distinct profile **restarts the application** (slower) — group tests by profile. **[summary]**
- `%test.` prefixed properties in `application.properties` apply to every test. See `quarkus-config-profiles`.
- `@EnabledIfConfig` / `@DisabledIfConfig` exist **[summary]**. `quarkus.test.hang-detection-timeout` default 10 min; `quarkus.test.arg-line` passes JVM args **[summary]**.
- `@TestTransaction` runs the test in a transaction and rolls it back **[summary]**.
- `@QuarkusIntegrationTest` runs the packaged artifact in profile `prod` by default (`quarkus.test.integration-test-profile`); injection into the test class doesn't reach app beans; run it in the integration-test phase (failsafe), not mixed with `@QuarkusTest` **[summary]**.

## 5. Dev Services in tests (zero-config containers)

- "Quarkus supports the automatic provisioning of unconfigured services in development and test mode" (usually via Testcontainers). Enabled **unless** you configure the connection;
  disable with `quarkus.devservices.enabled=false` or per service (`quarkus.<service>.devservices.enabled`). Startup timeout 60 s (`quarkus.devservices.timeout`).
- **Databases:** default images e.g. PostgreSQL `postgres:18`, MySQL `mysql:9.5`, MariaDB `mariadb:12.1`, MSSQL/DB2/Oracle need license acceptance; credentials `quarkus`/`quarkus`;
  properties `quarkus.datasource.devservices.image-name|port|init-script-path|reuse|container-env."X"`. With mapped volumes Hibernate may **wipe the DB at startup** — set
  `quarkus.hibernate-orm.schema-management.strategy=none|validate` if you reuse data.
- **Kafka:** default `apache/kafka-native`; `quarkus.kafka.devservices.provider` (`upstream-kafka`, `redpanda`, `strimzi`), topics via
  `quarkus.kafka.devservices.topic-partitions.<topic>=<n>`. "Sharing is enabled by default in dev mode, but disabled in test mode."
- Use the **same engine as production** (don't test Postgres code on H2). Real connection settings live under `%prod.` (guide recommendation).
- Reuse across runs requires `testcontainers.reuse.enable=true` in `.testcontainers.properties`. Testcontainers core is `org.testcontainers:testcontainers` (2.x docs); use it directly only for services without Dev Services.
- Custom setup before the app starts: `QuarkusTestResourceLifecycleManager` with `@QuarkusTestResource` / `@WithTestResource` (scope `MATCHING_RESOURCES` default, `RESTRICTED_TO_CLASS`, `GLOBAL`) **[summary]**.

## 6. Stubbing HTTP dependencies — `quarkus-wiremock` (Quarkiverse)

- Artifacts: `io.quarkiverse.wiremock:quarkus-wiremock` (provided) and `quarkus-wiremock-test` (test, for programmatic stubs). Versions per docs: 1.8.1 at time of reading.
- `@QuarkusTest` + `@ConnectWireMock` injects a `WireMock` field; `@ConnectWireMock(restClient = "my-api")` redirects that REST client's base URL to WireMock.
```java
wiremock.register(get(urlEqualTo("/prices/123")).willReturn(aResponse().withStatus(200).withBody(PRICE_JSON)));
wiremock.register(get(urlEqualTo("/prices/999")).willReturn(aResponse().withStatus(503)));   // drive retry/fallback tests
```
- Config **[summary]**: `quarkus.wiremock.devservices.enabled` (true), `.port`, `.files-mapping` (default `src/test/resources`, with `mappings/` and `__files/` — **fits your JSON fixtures**),
  `.global-response-templating`, `.reload`. Runs as a Dev Service in dev/test only.
- Use it to test **timeouts, 5xx, delays and fault injection** against `@Timeout/@Retry/@CircuitBreaker/@Fallback` (see `quarkus-fault-tolerance`): assert number of calls and fallback result.

## 7. Specialized tests

- **Virtual-thread pinning:** `io.quarkus.junit:junit-virtual-threads` (test) with `@VirtualThreadUnit`, `@ShouldNotPin`, `@ShouldPin(atMost = 1)` (`io.quarkus.test.junit.virtual`) **[summary]**;
  `-Djdk.tracePinnedThreads` only on Java 21–23. See `java21-virtual-threads-quarkus`.
- **Security:** test with token/identity helpers from the Quarkus security testing support (`@TestSecurity`; verify in the Testing Security guide) and assert 401/403 and **object-level** access. See `quarkus-security-standards`.
- **Health/metrics:** call `/q/health/ready`, `/q/metrics` in a test to guard against accidental removal (`quarkus-observability`).
- **Contract:** assert `/q/openapi` equals/compatible with the contract (`quarkus-contract-first-openapi`).
- **Idempotency:** same request twice with the same key => same response, one side effect (`idempotency-design`).
- **Logging in tests:** the logging guide has a "Configure logging for @QuarkusTest" section (not extracted).

## 8. Anti-patterns

| Smell | Fix |
|---|---|
| Every test is `@QuarkusTest` | Move rule logic to plain unit tests; boot Quarkus only for wiring/REST/DB |
| Many different `@TestProfile`s | Each restarts the app; consolidate |
| H2 for a Postgres production | Dev Services with the same engine |
| `@InjectMock` on everything | Fake only external boundaries |
| Hard-coded ports/URLs | Use `@TestHTTPResource`/`@TestHTTPEndpoint` and random ports |
| Tests depend on execution order/shared DB state | `@TestTransaction` or per-test cleanup |
| Testing only happy path | Add 4xx/5xx, timeout, retry, fallback, auth failure cases |
| `Thread.sleep` for async | Awaitility/`StepVerifier`/`Uni.await().atMost(...)` |

## 9. Checklist

- [ ] Pyramid respected: most tests are unit tests; a focused set of `@QuarkusTest`; few `@QuarkusIntegrationTest`.
- [ ] Dev Services use the production engine; prod URLs only under `%prod.`.
- [ ] External HTTP stubbed with WireMock incl. failure scenarios; call counts asserted.
- [ ] JSON fixtures on the classpath, small and intent-named.
- [ ] Security, validation (400/problem+json), idempotency and OpenAPI-contract tests exist.
- [ ] Pinning test for virtual-thread code; no `Thread.sleep`.

Related: `junit5-testing-best-practices`, `quarkus-fault-tolerance`, `quarkus-config-profiles`, `quarkus-observability`, `tdd-java-workflow`.

## Sources

- https://quarkus.io/guides/getting-started-testing
- https://quarkus.io/guides/dev-services, /databases-dev-services, /kafka-dev-services
- https://docs.quarkiverse.io/quarkus-wiremock/dev/index.html
- https://java.testcontainers.org/
- https://martinfowler.com/articles/practical-test-pyramid.html
