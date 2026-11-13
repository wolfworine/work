---
name: idempotency-design
description: Use whenever an operation might be executed twice — retries, client timeouts, double-clicks, at-least-once message delivery (Kafka), redelivery after failure — or when designing POST/PATCH endpoints, an Idempotency-Key header, deduplication tables with unique constraints, idempotent Kafka consumers, saga/compensation steps, outbox publishing, or deciding which HTTP methods are safe to retry. Covers HTTP semantics (RFC 9110), the IETF Idempotency-Key draft, AWS client-token pattern and a Quarkus/Panache implementation sketch.
---

# Idempotency Design

**Sources:** IETF `draft-ietf-httpapi-idempotency-key-header-08` (an **Internet-Draft, not an RFC**; last updated
2026-10-22, expires 2027-04-25), RFC 9110 (HTTP semantics; those sections came via an extracted summary),
AWS Builders' Library "Making retries safe with idempotent APIs", Microsoft Azure Architecture Center
(API implementation, Retry, Compensating Transaction), Quarkus Kafka guide. The Quarkus Kafka and transaction
guides do **not** discuss idempotent-consumer/unique-constraint dedup — section 5 is a design sketch, not a documented Quarkus feature.

## 1. Definitions

- **Safe** methods (no state change): GET, HEAD, OPTIONS, TRACE. **Idempotent** methods (repeating = same
  effect as once): PUT, DELETE and all safe methods (RFC 9110). **POST and PATCH are not idempotent**
  (draft cites RFC 9110; Microsoft: "POST methods aren't guaranteed to be idempotent").
- Idempotent means same *server state* result — not necessarily the same response (a repeated DELETE may return 404).
- Why it matters: a request can succeed on the server while the response is lost; the caller then retries (Microsoft).

## 2. When you need it

| Situation | Idempotency needed? |
|---|---|
| Client/gateway/mesh retries a POST | Yes — Idempotency-Key |
| Payment/order/claim registration endpoints | Yes |
| Kafka/queue consumer (at-least-once) | Yes — dedupe by event/message id |
| Saga step + compensation | Yes — each step idempotent |
| Outbox relay publishing events | Yes — consumers dedupe |
| GET/PUT/DELETE implemented correctly | Naturally idempotent — keep it so |

## 3. HTTP `Idempotency-Key` (draft-08)

- Header `Idempotency-Key` is an **Item Structured Header** whose value MUST be a **String** (quoted):
  `Idempotency-Key: "8e03978e-40d5-43e8-bc93-6894a57f9324"`. A UUID is RECOMMENDED.
- The key MUST be unique and MUST NOT be reused with a **different request payload**. Client SHOULD send a new key
  per logical request and **reuse the same key when retrying**.
- Server: first request processed normally; duplicate after completion -> **response consistent with the original**;
  duplicate while still processing -> conflict.
- Errors (SHOULD): missing key on an operation that requires it -> **400**; same key + different payload -> **422**
  (Unprocessable Content); retry while original in flight -> **409** (client may retry with no change; for the others
  it MUST correct the request first). Use problem details bodies (RFC 9457, see `quarkus-rest-api-standards`).
- Optional **fingerprint** (checksum of payload/selected fields/signature) stored with the key to detect payload mismatch.
- Resources MUST publish their idempotency specification including any **expiration policy**.
- **Security:** validate key format; build the cache key from the key **plus client identity** (tenant/user) so
  low-entropy keys can't be used to read another client's stored response.

## 4. AWS pattern (client token) — rules that generalize

1. Caller supplies a unique **client request token**; same token = same request.
2. **Record the token and all mutating work atomically** (one ACID transaction) so token and resources never diverge.
3. Repeat of a completed request returns a **semantically equivalent response**, not "already exists".
4. Same token with different parameters -> validation (parameter mismatch) error.
5. Retain tokens for the resource lifetime plus a safety interval (AWS gives no number — define your own).
6. SDKs may auto-generate and reuse the token across retries.

## 5. Implementation sketch for Quarkus (design, not a Quarkus feature)

Table with a **unique constraint** on (client_id, idempotency_key); status `IN_PROGRESS|DONE`; request fingerprint;
stored status + response body; `created_at` for expiry.

```java
@Entity @Table(name = "idempotency_record",
  uniqueConstraints = @UniqueConstraint(columnNames = {"clientId", "idemKey"}))
public class IdempotencyRecord extends PanacheEntity {
  public String clientId, idemKey, fingerprint, status;   // status: IN_PROGRESS / DONE
  public int httpStatus; @Lob public String responseBody; public Instant createdAt;
}

@ApplicationScoped
public class IdempotencyService {
  @Transactional
  public Optional<IdempotencyRecord> begin(String client, String key, String fingerprint) {
    IdempotencyRecord r = IdempotencyRecord.find("clientId = ?1 and idemKey = ?2", client, key).firstResult();
    if (r != null) {
      if (!r.fingerprint.equals(fingerprint)) throw new UnprocessableException();   // -> 422
      if ("IN_PROGRESS".equals(r.status))   throw new ConflictException();          // -> 409
      return Optional.of(r);                                                        // replay stored response
    }
    // insert IN_PROGRESS; a concurrent duplicate hits the unique constraint -> treat as 409
    ...
    return Optional.empty();
  }
}
```
Key points: insert the key in the **same transaction** as the business write (`@Transactional`, default `REQUIRED`;
commits unless a runtime exception crosses the method boundary, per the Quarkus transactions guide); rely on the
**database unique constraint**, not a read-then-write check, to win races; clean up expired rows with a scheduled job.

## 6. Messaging and consumers

- Quarkus Kafka: "Kafka consumers only guarantee at-least-once processing of single records" -> assume duplicates.
- Dedupe by **event id** (store processed ids in the same transaction as the side effect) or make the handler
  naturally idempotent (upsert by business key, `SET` not `INCREMENT`).
- Producer: transactional producers need `acks=all` and a unique `transactional.id`, which implies
  `enable.idempotence=true` (Quarkus Kafka guide). That protects producer->broker duplicates only.
- Outbox: relay at-least-once; consumers dedupe (see `enterprise-java-design-patterns`, `kafka-event-driven-java`).
- Ordering note: `@RunOnVirtualThread` on Reactive Messaging methods makes processing concurrent and **unordered**
  (see `java21-virtual-threads-quarkus`) — make sure handlers tolerate it.

## 7. Making operations idempotent without a key

- Prefer **PUT** with client-chosen id, `INSERT ... ON CONFLICT DO NOTHING`/upsert, state-machine guards
  ("only move from PENDING to PAID"), conditional updates with `If-Match`/version columns (Microsoft: 412 on ETag mismatch, 428 when missing).
- Long-running work: validate, durably persist, return **202 + Location** to a status endpoint, and make workers idempotent (Microsoft).
- Compensations: "design each step as an idempotent command"; record progress so the workflow can resume (Microsoft).

## 8. Checklist

- [ ] Every non-idempotent write has a key (header or natural business key) and a unique constraint.
- [ ] Same key + same payload => same response; different payload => 422; in-flight => 409; missing => 400.
- [ ] Key stored atomically with the work; scoped by client; has an expiry policy.
- [ ] Retries (client, mesh, `@Retry`) only on idempotent or keyed operations (see `quarkus-fault-tolerance`).
- [ ] Consumers dedupe by event id; handlers safe under redelivery and unordered processing.
- [ ] Idempotency rules documented in the OpenAPI contract (`quarkus-contract-first-openapi`).

## Sources

- https://datatracker.ietf.org/doc/draft-ietf-httpapi-idempotency-key-header/
- https://www.rfc-editor.org/rfc/rfc9110.html#name-idempotent-methods
- https://aws.amazon.com/builders-library/making-retries-safe-with-idempotent-APIs/
- https://learn.microsoft.com/en-us/azure/architecture/best-practices/api-implementation, .../patterns/retry, .../patterns/compensating-transaction
- https://quarkus.io/guides/kafka, https://quarkus.io/guides/transaction
