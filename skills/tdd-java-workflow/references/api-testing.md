# API / HTTP-Level Testing

This is a companion to the main TDD workflow, not a replacement for it. The
main skill covers the **unit layer**: driving a class's behavior with
JUnit/Mockito, one collaborator boundary at a time. This file covers the
**HTTP/API layer**: testing what a client actually sees when it calls an
endpoint — status codes, headers, payload shape, auth, and failure modes. Use
it when the task is "write/review a test that hits a controller or a live
endpoint," not "write a test for this domain class."

The RGR loop, FIRST properties, and Arrange-Act-Assert still apply here —
"Act" is just an HTTP call instead of a method call. What changes is *what
you assert on* and *which layer of the pyramid the test belongs in*.

## The API testing pyramid

Same principle as the unit-test pyramid (fewer, slower tests as you go up),
mapped onto HTTP concerns. Put each concern at the *lowest* layer that can
still catch it — do not push business-rule edge cases up into slow layers.

| Layer | Tests | Java tooling | What belongs here |
|---|---|---|---|
| **Unit** | Most numerous | JUnit + Mockito (see main SKILL.md) | Domain/business logic, with HTTP concerns and collaborators stubbed out entirely. Validation rules, pricing, discount logic — anything that doesn't need a real request/response cycle. |
| **Component / contract** | Fewer | Spring `WebTestClient` or `MockMvc` against a loaded (or sliced, e.g. `@WebMvcTest`) application context; consumer-driven contract tools (Pact, Spring Cloud Contract) for cross-service schema agreement | Does this controller wire routing, serialization, validation annotations, and status codes correctly? Does the response *shape* match what a consumer expects (required fields, types)? No real downstream dependencies — those are stubbed/mocked. |
| **Integration** | Fewer still | RestAssured or `WebTestClient` against a real running instance (e.g. via Testcontainers) talking to a real database/queue | Does the whole assembled service, including its real adapters, behave correctly end to end for one path? Auth flows against a real identity provider, a real persistence round-trip. |
| **End-to-end / acceptance** | Fewest | Postman/Newman collections, or RestAssured/Cucumber against a deployed environment | Critical user journeys across multiple real services. Reserve for the handful of flows that matter most — not exhaustive case coverage. |

If you find yourself writing an integration or e2e test to check "what status
code do I get for a missing field," that case belongs at the component layer
(or even a unit test on the validator) — push it down.

## HTTP fundamentals that drive test design

### Methods and idempotency

Idempotency and safety determine what a test suite must guarantee about
*repeating* a call — this is a common source of missed test cases (e.g., "call
DELETE twice" or "retry a POST after a timeout").

| Method | Idempotent? | Safe? | Test implication |
|---|---|---|---|
| GET | Yes | Yes | Repeated calls must return the same result (barring real state changes elsewhere); never mutates state — a test that shows a GET changing data is a bug. |
| POST | No | No | Each call is expected to create a new resource (or trigger a new side effect) — test that calling it twice with the same body produces two resources, unless the API explicitly documents dedup/idempotency-key behavior. |
| PUT | Yes | No | Calling it N times with the same body must leave the resource in the same end state as calling it once. Test this explicitly (call twice, assert state is identical, not "twice as updated"). |
| PATCH | Usually not | No | May have cumulative effects (e.g. `{"count": increment}` semantics) — do not assume repeat calls are safe without checking the API's contract. |
| DELETE | Yes | No | First call removes the resource (204); the *second* call on the same resource must return 404, not another 204 or a 500 — this "delete twice" case is easy to forget and worth its own test. |

### Status codes as test oracles

Status codes are often the first (cheapest) assertion in a test — get this
right before asserting on the body.

- **2xx** — 200 (GET/PUT/PATCH success), 201 (POST created), 202 (accepted,
  async processing), 204 (DELETE success, no body — assert the body is empty,
  not just that the code is 204).
- **3xx** — 301/302 (redirects), 304 (not modified, conditional GET with
  `If-None-Match`/`ETag`).
- **4xx** — 400 (malformed request), 401 (missing/invalid auth), 403
  (authenticated but not authorized), 404 (resource doesn't exist), 405
  (wrong method), 409 (conflict — e.g. duplicate unique field), 422
  (semantically invalid data — validation failure), 429 (rate limited).
- **5xx** — 500/502/503/504. Your own service returning 5xx in a test run is
  always a defect to investigate, never something to assert as "expected"
  (except in a resilience/chaos test that intentionally forces one).

**401 vs 403 is a frequent bug and a frequent test gap**: 401 means "I don't
know who you are" (no token, invalid token, expired token). 403 means "I know
who you are, but you're not allowed to do this" (valid token, wrong
role/scope). Any endpoint with role-based access needs both cases tested
separately — don't let a wrong-role test silently accept 401.

## What makes a good API test case

For every endpoint, work through this heuristic before writing the test list.
It mirrors "triangulation" from the main workflow: start with the happy path,
then systematically add cases along each axis below.

1. **Happy path (functional).** Valid input, valid auth, expected role →
   correct status code and correct response shape.
2. **Auth axis.**
   - No credentials → 401.
   - Invalid/garbage token → 401.
   - Expired token → 401.
   - Valid token, wrong role/scope → 403 (not 401).
3. **Input-validation axis (negative testing).**
   - Missing each required field, one at a time → 422 (or 400, per the
     API's convention — be consistent and assert it).
   - Wrong data type for a field (string where a number is expected).
   - Empty string vs. null vs. absent field — these are three different
     cases, not one.
   - Malformed payload (invalid JSON, wrong `Content-Type`) → 400.
   - Oversized payload → 413.
4. **Boundary values.** Off-by-one at every documented limit: minimum length
   − 1 / minimum length / minimum length + 1, and the same at the maximum;
   negative numbers where only positive make sense; zero where the domain
   doesn't allow it.
5. **State/uniqueness axis.**
   - Duplicate of a unique field (e.g. email already registered) → 409.
   - Acting on a non-existent resource (`GET /users/99999`) → 404.
   - Acting twice on a resource with side effects (double DELETE, double
     "cancel order") → confirm the second call is handled gracefully, not
     just "does the first call work."
6. **Injection / sanitization spot-check.** A payload containing something
   like `'; DROP TABLE users;--` or a script tag should be rejected or safely
   escaped, never reflected unsanitized or causing a 500.
7. **Response contract, on every case above, not just the happy path.**
   Every test — including the negative ones — should still assert the error
   response has the documented shape (e.g. a `message`/`error` field), not
   just the status code. Consumers build error handling around that shape too.

### The six things to assert on every response

Do not stop at the status code. A response check that only reads
`assertEquals(200, status)` is the API-layer equivalent of a unit test with no
real assertion — it would pass even if the body were garbage.

1. **Status code** — correct for this operation and this input.
2. **Response time** — under the layer's budget (see thresholds below);
   only assert this in integration/e2e/performance tests, not in fast
   component tests where the "server" is in-process and timing is noise.
3. **Schema / required fields** — every field the contract promises is
   present.
4. **Data types** — fields are the type the contract promises (string, int,
   boolean, array), not just "present."
5. **`Content-Type` header** — `application/json` (or whatever the contract
   promises) is actually being sent, not assumed.
6. **Data integrity** — values round-trip correctly (what you sent in the
   request body matches what comes back), not just "some value is there."

### Response-time budget (for integration/performance tests only)

| Category | Threshold | Meaning |
|---|---|---|
| Excellent | < 200ms | Feels instant |
| Good | 200-500ms | Acceptable for most APIs |
| Acceptable | 500ms-1s | User might notice |
| Slow | 1s-2s | Investigate |
| Fails the test | > 2s | Treat as a defect, not a flake to retry away |

Use averages *and* a high percentile (p95) across repeated calls for load
scenarios — a single fast sample hides tail latency.

## Code sketch: RestAssured + JUnit 5

Component/integration-layer example, applying the checklist above. Uses
JUnit 5's `@ParameterizedTest` the same way the main skill's triangulation
technique suggests generalizing a unit test — one test, several inputs, one
behavior ("invalid payload is rejected with a proper error body").

```java
class UserApiTest {

    private static final String BASE = "https://api.example.test/v1";

    @Test
    void createsUserAndReturnsLocationAndBody() {
        var payload = Map.of("name", "Ada", "email", "ada@test.com");

        given()
            .baseUri(BASE)
            .contentType(ContentType.JSON)
            .header("Authorization", "Bearer " + validToken())
            .body(payload)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .time(lessThan(2000L))
            .contentType(containsString("application/json"))
            .body("id", notNullValue())
            .body("email", equalTo("ada@test.com"));
    }

    @ParameterizedTest
    @MethodSource("invalidUserPayloads")
    void rejectsInvalidPayloadWith422(Map<String, Object> payload) {
        given()
            .baseUri(BASE)
            .contentType(ContentType.JSON)
            .header("Authorization", "Bearer " + validToken())
            .body(payload)
        .when()
            .post("/users")
        .then()
            .statusCode(422)
            .body("message", notNullValue()); // error shape, not just the code
    }

    static Stream<Map<String, Object>> invalidUserPayloads() {
        return Stream.of(
            Map.of(),                                        // missing everything
            Map.of("name", "Ada"),                            // missing email
            Map.of("name", "", "email", "ada@test.com"),      // empty required field
            Map.of("name", "Ada", "email", "not-an-email")    // malformed value
        );
    }

    @Test
    void deletingTwiceReturns404OnSecondCall() {
        String userId = createUser();

        given().baseUri(BASE).auth().oauth2(validToken())
            .when().delete("/users/" + userId)
            .then().statusCode(204);

        given().baseUri(BASE).auth().oauth2(validToken())
            .when().delete("/users/" + userId)
            .then().statusCode(404); // idempotency edge case, easy to forget
    }

    @Test
    void noTokenReturns401AndWrongRoleReturns403() {
        given().baseUri(BASE)
            .when().get("/users")
            .then().statusCode(401);

        given().baseUri(BASE).auth().oauth2(viewerToken()) // valid but wrong role
            .when().delete("/users/123")
            .then().statusCode(403); // not 401 — this distinction is the common bug
    }
}
```

For Spring apps, prefer `WebTestClient` (reactive/non-blocking, and usable
against a sliced `@WebMvcTest`/`@SpringBootTest(webEnvironment = RANDOM_PORT)`
context) or `MockMvc` when you don't need a real HTTP transport — both give
the same assertion shape as above without requiring RestAssured as a
dependency:

```java
@Test
void rejectsInvalidPayloadWith422(@Autowired WebTestClient client) {
    client.post().uri("/users")
        .contentType(MediaType.APPLICATION_JSON)
        .header("Authorization", "Bearer " + validToken())
        .bodyValue(Map.of("name", "Ada"))     // missing email
        .exchange()
        .expectStatus().isEqualTo(422)
        .expectBody().jsonPath("$.message").exists();
}
```

## Manual/exploratory testing and CI wiring

Automated tests (above) are what you commit; a tool like **Postman** is what you use *before* a test exists — to explore an endpoint interactively, or to hand a QA engineer a runnable collection without asking them to read Java. A Postman collection is a group of saved requests; **environments** hold per-stage variables (`{{base_url}}`, `{{token}}`) so the same collection runs against dev/staging/prod. Assertions live in a request's **Tests** tab as JavaScript, and can chain requests together (e.g. capture a login token and store it for the next request):
```javascript
pm.test('Status is 201', () => pm.response.to.have.status(201));
pm.test('Response time < 2000ms', () => pm.expect(pm.response.responseTime).to.be.below(2000));
pm.environment.set('auth_token', pm.response.json().token); // feed the next request in the collection
```
Run the same collection headlessly in CI with **Newman** (Postman's CLI runner) — this is how a Postman collection built for manual exploration becomes a regression check instead of a one-off:
```bash
newman run collection.json --environment staging.json --reporters cli,html --bail
```

**Wire API tests into CI** so they run on every push, not just locally — the failure mode without this is a broken endpoint that only surfaces when a human happens to test it manually:
```yaml
# .github/workflows/api-tests.yml
on:
  push: { branches: [main] }
  pull_request: { branches: [main] }
jobs:
  api-tests:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with: { java-version: '21', distribution: 'temurin' }
      - run: mvn test -Dtest=*ApiTest          # or ./mvnw, matching this skill's RestAssured/WebTestClient tests
```
Run a fast smoke subset on every push, the full suite on a schedule/nightly — mirrors the pyramid principle of "don't put slow checks on the fast feedback path."

**Load/performance testing** beyond the per-request response-time assertions above needs a tool that simulates concurrent users, not just sequential timing — e.g. **Locust** or **k6** ramping up virtual users against an endpoint and reporting average/p95/p99 under load. Treat this as its own test suite run on a schedule or before a release, not part of the fast component/integration suite — it's testing capacity, a different concern from correctness.

## Contract testing, briefly

When a component test asserts response *shape* (required fields present,
types correct) for a consumer that's a different team/service, that is
already informal contract testing. Formalize it with a consumer-driven
contract tool (Pact, or Spring Cloud Contract in a Spring ecosystem) once more
than one consumer depends on the shape staying stable — it turns "did we
break the response shape" into a fast, automated check that runs in CI on
both the provider's and the consumer's pipeline, without needing a full
integration environment.

## Checklist: is this API test worth keeping?

- [ ] Does it assert more than the status code (schema, types, `Content-Type`,
      data integrity — see the six things above)?
- [ ] Is it at the lowest pyramid layer that can catch the thing it's
      checking (a validation rule doesn't need a live integration test)?
- [ ] Does every negative-path test also check the error response shape, not
      just the code?
- [ ] Have you covered both 401 (no/bad auth) and 403 (valid auth, wrong
      permission) separately, if the endpoint has role checks?
- [ ] Have you covered the idempotency edge case for PUT/DELETE (repeat call
      behavior), if relevant to this endpoint?
- [ ] Does a performance assertion, if present, live in an integration/e2e
      test rather than a fast component test where timing is meaningless?
- [ ] Would this test catch a real regression (wrong field renamed, status
      code changed, auth check removed) rather than just re-confirming the
      framework works?
