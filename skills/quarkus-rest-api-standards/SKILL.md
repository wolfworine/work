---
name: quarkus-rest-api-standards
description: Use whenever designing or reviewing REST endpoints in Quarkus (Quarkus REST / RESTEasy Reactive) — resource naming, HTTP methods and status codes, Bean Validation and its 400 response, ExceptionMapper / @ServerExceptionMapper, RFC 9457 problem+json error bodies, pagination, filtering, ETag/conditional requests, 202 async pattern, API versioning, DTOs vs entities, @Blocking/@NonBlocking, and which thread a method runs on.
---

# Quarkus REST API Standards

**Sources:** Quarkus REST guide + Validation guide (3.40), RFC 9457, Microsoft Azure Architecture Center "API design" and
"API implementation", OWASP REST Security Cheat Sheet. **[summary]** = from an extracted part of a page; verify before relying.
For error formats in particular, read RFC 9457. Not verified: `@RestResponse` annotation name (the verified type is
`org.jboss.resteasy.reactive.RestResponse`); content negotiation and `@Valid` details beyond the Validation guide.

## 1. Dependencies and basics

- `quarkus-rest` (+ `quarkus-rest-jackson` for JSON), `quarkus-hibernate-validator` for validation.
- `@Path` (class/method), `@ApplicationPath` or build-time `quarkus.rest.path` for the root; `@GET/@POST/@PUT/@DELETE/@PATCH/...`.
- `@Produces`/`@Consumes` — declare them explicitly (OWASP: never copy `Accept` into `Content-Type`).
- Parameters: `@RestPath`, `@RestQuery`, `@RestHeader`, `@RestCookie`, `@RestForm`, `@RestMatrix` (compile with `-parameters`).
- Duplicate endpoints fail the build (`quarkus.rest.fail-on-duplicate=false` downgrades to warning — don't).

## 2. Resource design (Microsoft API design)

- URIs are **nouns, plural**: `/orders`, `/orders/{id}`; not `/create-order`. Keep nesting to collection/item/collection.
- Don't mirror database tables; model the domain. Avoid chatty fine-grained APIs.
- Use **DTOs** with an allowlist — never bind request bodies to entities (over-posting protection; Microsoft).
- Status codes:

| Op | Success | Errors |
|---|---|---|
| GET | 200 (204 empty search) | 404, 406 |
| POST create | **201 + `Location`** (server assigns URI); 202 for async | 400, 409, 415 |
| PUT | 200/201/204 (idempotent) | 400, 409 |
| PATCH | 200/204 with `application/merge-patch+json` (RFC 7396) or `json-patch+json` (RFC 6902) | 400, 409, 415 |
| DELETE | 204 | 404 |
| any | | 401 (not authenticated), 403 (not authorized), 413, 415, 429/503 + `Retry-After` |

- **Async/long-running:** validate + persist, return **202** with `Location` to a status endpoint (+ `Retry-After`);
  when done, status endpoint returns **303 See Other** to the new resource.
- **Idempotency:** GET/HEAD/PUT/DELETE must be idempotent; POST/PATCH aren't — use an Idempotency-Key (see `idempotency-design`).

## 3. Collections: pagination, filtering, sorting

- Paging with `limit`/`offset` (e.g. `limit=25&offset=0`) **with an enforced maximum**; Microsoft example default
  limit 10, range 1–100, offset 0–10000; return next-page link; prefer **cursor pagination** for large/volatile sets.
- Filtering by query params (`status=`, `minCost=`), sorting `sort=`, field selection `fields=`.
- Never return unbounded collections (OWASP API4: unrestricted resource consumption).

## 4. Validation (Quarkus Validation guide)

```java
@POST
public Response create(@Valid CreateClaimRequest req) { ... }   // @Valid cascades into the bean

public record CreateClaimRequest(@NotBlank String policyId, @Positive BigDecimal amount) {}
```
- Parameter violation on a REST endpoint -> **400 automatically**, with Quarkus's own report:
  `{"title":"Constraint Violation","status":400,"violations":[{"field":"...","message":"..."}]}`
  (built-in `ExceptionMapper`). Replace it with your own `ExceptionMapper<ConstraintViolationException>` to emit RFC 9457.
- Violations on **return values or service-method parameters become 5xx** unless you catch `ConstraintViolationException`
  — validate input at the REST boundary with `@Valid`.
- Validate length/range/format/type; reject oversize bodies with 413 (`quarkus.http.limits.max-body-size` default 10240K **[summary]**).
- `quarkus.hibernate-validator.fail-fast` default false **[summary]**.

## 5. Error responses: RFC 9457 `application/problem+json`

Fields: `type` (URI; absent = `about:blank`), `title` (stable per problem type), `status` (also sent as the real HTTP
status), `detail` (occurrence-specific, helps the client fix it — not debug info), `instance` (URI of this occurrence);
extension members allowed (clients MUST ignore unknown ones). A new problem type MUST document its type URI, title and status.

```java
@ServerExceptionMapper                         // method in a resource: applies to that class only [summary]
public RestResponse<ProblemDetails> map(ConstraintViolationException e) {
  var p = new ProblemDetails("https://api.example.com/problems/validation", "Validation failed", 422,
      "One or more fields are invalid", Map.of("errors", toErrors(e)));
  return RestResponse.status(Response.Status.fromStatusCode(422), p);   // media type application/problem+json
}
```
(`@ServerExceptionMapper` / `jakarta.ws.rs.ext.ExceptionMapper` both exist; `@UnwrapException` available **[summary]**.)
Rules: no stack traces or internals in production, generic messages, log details server-side with a correlation id
(OWASP, Microsoft); keep 4xx and 5xx distinct; don't log secrets.

## 6. Thread model (Quarkus REST guide) — affects correctness

- Returns `Uni`/`Multi`/`CompletionStage`/`Publisher`/Kotlin `suspend` -> **event loop (never block)**.
- Returns a plain value -> **worker thread**; `@Transactional` methods are treated as blocking.
- `@Blocking` / `@NonBlocking` on method, class or the `Application` subclass. For virtual threads see `java21-virtual-threads-quarkus`.
- Filters: `@ServerRequestFilter` (optionally pre-matching), `@ServerResponseFilter` **[summary]**.

## 7. Caching and concurrency control (Microsoft API implementation)

- `Cache-Control` (`max-age`, `private/public`, `no-cache`, `no-store`); `ETag` + `If-None-Match` -> **304** no body.
- Optimistic concurrency: `If-Match` mismatch -> **412**; missing `If-Match` on protected updates -> **428**; check atomically in the data store.
- Partial content: `Accept-Ranges`, `206` + `Content-Range`.

## 8. Versioning and evolution

- Options: none, URI (`/v2/orders`), query (`?version=`), header, media type (`application/vnd.acme.v1+json`).
- Make changes **backward compatible** (don't remove fields); breaking change => new version, keep old, **deprecate quickly** (Microsoft).
- See `quarkus-contract-first-openapi` for contract-driven evolution.

## 9. Anti-patterns

| Smell | Fix |
|---|---|
| Entities returned/accepted directly | DTOs |
| 200 with error payload | Correct status + problem+json |
| Verbs in URIs / `POST /doSomething` for everything | Resource nouns, proper methods |
| Unbounded list endpoints | Paging with max |
| Validation only in service layer | `@Valid` at the boundary |
| Stack traces in responses | Generic problem details + correlation id |
| Blocking code returning `Uni` | `@Blocking` or plain return type |
| API mirrors DB schema | Domain-shaped contract (Microsoft) |

## Sources

- https://quarkus.io/guides/rest, https://quarkus.io/guides/validation
- https://www.rfc-editor.org/rfc/rfc9457.html
- https://learn.microsoft.com/en-us/azure/architecture/best-practices/api-design and .../api-implementation
- https://cheatsheetseries.owasp.org/cheatsheets/REST_Security_Cheat_Sheet.html
