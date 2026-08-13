---
name: enterprise-java-design-patterns
description: Use whenever designing service-to-service resilience, legacy migration, or distributed-data strategy in a Java/cloud-native backend — circuit breakers, retries, bulkheads, timeouts, rate limiting, sagas/distributed transactions, outbox/event delivery, strangler-fig migrations, anti-corruption layers, leave-and-layer, sidecar/service mesh, cache-aside — or when reviewing code that calls an external/legacy system without a resilience boundary, or that spans multiple services/databases without a consistency strategy.
---

# Enterprise Java Design Patterns (Cloud-Native / Integration-Level)

These are **system-level** patterns for distributed systems — not GoF class
patterns. Every one of them trades operational complexity for a specific
capability (safety, decoupling, incremental migration). The default answer
is **don't apply the pattern**: a single service calling one dependency with
a sane HTTP client timeout does not need a circuit breaker; a monolith with
one database does not need Saga or Outbox. Reach for a pattern only when a
symptom in the decision table below is actually present, and prefer the
smallest version of the pattern (library-level, not new infrastructure)
before escalating.

When reviewing or writing code, treat these two things as bugs, not style
preferences:
- A network call to another service/legacy system with no timeout, no
  retry policy, and no failure isolation.
- A business operation that writes to two or more services/databases with
  no plan for partial failure (no compensation, no outbox, no idempotency).

---

## 1. Migration & Legacy Integration Patterns

### Strangler Fig — incremental replacement of a monolith

**Problem it solves:** "Big Bang" rewrites fail from duration, shifting
requirements, and underestimated complexity. Strangler Fig routes traffic
gradually from the monolith to new services via an edge proxy, one bounded
context at a time, with change-data-capture (CDC) keeping both data stores
in sync during the transition.

**Use when:**
- The monolith will eventually be retired — it's not a permanent fixture.
- You can carve out clear, bounded contexts to migrate one at a time.
- The business needs faster feature velocity than the monolith allows.
- You have team capacity for a multi-month effort, not a quick fix.

**Overkill / wrong tool when:**
- The monolith is too risky to touch at all → use **Leave and Layer** instead.
- The app is being retired within 1-2 years anyway (don't migrate what you're
  about to kill).
- The business needs new features more urgently than technical rework.
- There's no organizational commitment to see an incremental migration
  through — a half-finished strangler fig is two systems to maintain forever.

**Shape of the solution (edge router + CDC):**
```java
// Edge proxy / API gateway routing rule (conceptual — Spring Cloud Gateway,
// nginx, Envoy, or a simple servlet filter all work)
@Bean
public RouteLocator routes(RouteLocatorBuilder builder) {
    return builder.routes()
        .route("user-profiles-new", r -> r
            .path("/api/users/**")
            .filters(f -> f.circuitBreaker(c -> c.setName("userSvc")))
            .uri("lb://new-user-service"))      // migrated bounded context
        .route("everything-else-legacy", r -> r
            .path("/**")
            .uri("lb://monolith"))               // default: still the monolith
        .build();
}
```
Routing rules live in config, not code, so traffic can be shifted back
instantly (feature flag / percentage rollout) if the new service misbehaves.
CDC (e.g. Debezium) tails the monolith's database and republishes changes so
the new service's local copy stays current until the cutover is complete.

---

### Leave and Layer — the core is permanent, innovate around it

**Problem it solves:** Some core systems (mainframe banking ledgers,
regulated health-care systems) are too risky, too expensive, or legally
constrained to replace. Leave and Layer accepts that and organizes the
architecture into three layers instead of migrating: **Layer 1 (core)**
stays untouched; **Layer 2 (integration)** uses an ACL + CDC to translate
legacy data into modern domain models; **Layer 3 (innovation)** is a cloud
native layer (APIs, mobile, AI features) built on top, scaling independently.

**Use when:**
- The core is the system of record and must stay authoritative.
- Replacing it is riskier or costlier than living with it.
- A different team/vendor owns the core, or compliance forbids touching it.
- The business needs rapid innovation *without* waiting on core changes.

**Overkill / wrong tool when:**
- Your org can't realistically staff and maintain *both* the legacy system
  and the new integration + innovation layers long-term.
- The integration layer (Layer 2) would end up more complex than just
  replacing the core would have been.
- The legacy system can't even support CDC/read access — layering isn't
  possible if you can't safely observe its data.

This is a macro/strategic decision (usually made once, at the architecture
level), not something to reach for per-feature — don't introduce a
three-layer split for a single integration point; that's what ACL alone is for.

---

### Anti-Corruption Layer (ACL) — tactical isolation from a messy dependency

**Problem it solves:** Integrating cleanly-modeled new code directly against
a legacy or third-party system's cryptic field names, denormalized
structures, and implicit business rules corrupts your new domain model.
The ACL sits between the two, translating in both directions, so legacy
messiness never leaks past the boundary.

**Use when:**
- The upstream system has a genuinely messy/legacy data model.
- Multiple services would otherwise each reinvent the same translation.
- You expect to eventually replace or version the upstream system — then
  only the ACL changes, not every consumer.

**Overkill / wrong tool when:**
- Only one service talks to the legacy system and there's no plan for more
  — a private adapter class inside that service is enough; don't stand up a
  separate ACL service for it.
- Translation overhead is unacceptable for a hot path with strict latency
  budgets.
- Replacement of the legacy system is already scheduled and funded — don't
  build a permanent-feeling abstraction for a short-lived problem.

**Shape of the solution:**
```java
// Domain-facing port — this is the only thing the rest of the app sees
public interface CustomerAccountPort {
    CustomerAccount findByCustomerId(String customerId);
}

// ACL adapter — the only place that knows the legacy schema exists
@Component
class LegacyMainframeAccountAdapter implements CustomerAccountPort {

    private final MainframeClient legacyClient; // cryptic COBOL-copybook fields

    public CustomerAccount findByCustomerId(String customerId) {
        LegacyAcctRec01 raw = legacyClient.getAcctRec01(customerId); // FLD-CUST-NO, FLD-BAL-AMT...
        return CustomerAccount.builder()
            .customerId(raw.getFldCustNo().trim())
            .balance(toMoney(raw.getFldBalAmt(), raw.getFldCurrCd()))
            .status(mapStatus(raw.getFldStatCd()))
            .build();
    }
}
```
Can run in-process (a library/adapter, cheapest) or out-of-process (its own
microservice, when many consumers need to share it and independent scaling
or deployment matters). Default to in-process until you have evidence
multiple teams need the same translation.

---

## 2. Service-to-Service Resilience (Runtime Plane)

### Sidecar / Service Mesh — infrastructure-level resilience

**Problem it solves:** Retries, mTLS, circuit breaking, load balancing, and
observability are cross-cutting concerns that otherwise get duplicated (or
skipped) in every service's code. A sidecar proxy deployed alongside each
service instance intercepts all traffic and handles these transparently.

**Use when:**
- You run 10+ services that need consistent security/traffic policy.
- You need advanced traffic management (canary, A/B, weighted routing).
- You need consistent service-to-service observability (traces, metrics).
- You want network concerns out of application code entirely.

**Overkill / wrong tool when:**
- You have only a handful of services — the operational cost of running
  and operating Istio/Linkerd/Consul Connect exceeds the benefit.
- Services mostly talk via message queues, not synchronous calls — a mesh
  mainly buys you value for request/response traffic.
- Your team doesn't have the platform-engineering capacity to run it; a
  half-operated mesh is worse than well-used in-process resilience libraries.

**Ambassador** is the lighter-weight cousin: a per-service proxy handling
*one specific* external/legacy dependency (protocol translation, TLS
termination, auth injection) rather than a full mesh for all traffic. Reach
for an Ambassador when you need mesh-like isolation for a single
troublesome dependency but adopting a whole service mesh isn't justified yet.

**If you don't have (or don't want) a mesh, put the same resilience
primitives in-process.** In Java this is normally Resilience4j:

```java
// Circuit breaker: stop hammering a failing dependency, fail fast instead
CircuitBreakerConfig cbConfig = CircuitBreakerConfig.custom()
    .failureRateThreshold(50)                     // trip past 50% failures
    .slowCallRateThreshold(80)
    .slowCallDurationThreshold(Duration.ofSeconds(2))
    .waitDurationInOpenState(Duration.ofSeconds(30))
    .slidingWindowSize(20)
    .build();

// Retry: only for transient, idempotent failures — never retry a POST
// that isn't safe to repeat without an idempotency key
RetryConfig retryConfig = RetryConfig.custom()
    .maxAttempts(3)
    .waitDuration(Duration.ofMillis(200))
    .retryExceptions(IOException.class, TimeoutException.class)
    .build();

// Bulkhead: cap concurrent calls to ONE dependency so it can't exhaust the
// thread pool / connection pool that other dependencies also need
ThreadPoolBulkheadConfig bulkheadConfig = ThreadPoolBulkheadConfig.custom()
    .maxThreadPoolSize(10)
    .coreThreadPoolSize(5)
    .queueCapacity(20)
    .build();

@CircuitBreaker(name = "inventorySvc", fallbackMethod = "fallbackInventory")
@Retry(name = "inventorySvc")
@Bulkhead(name = "inventorySvc", type = Bulkhead.Type.THREADPOOL)
public InventoryLevel checkInventory(String sku) {
    return inventoryClient.getLevel(sku); // must have its own connect/read timeout too
}
```

Decision heuristics for the individual primitives:
- **Timeout** — always. Every outbound call, no exceptions. This is not
  optional and is not really "a pattern," it's a baseline.
- **Retry** — only for transient failures on **idempotent** operations, with
  backoff. Retrying a non-idempotent write without a dedup/idempotency key
  turns one failure into duplicate side effects.
- **Circuit breaker** — add once a dependency has actually caused cascading
  failure (slow responses backing up threads/connections elsewhere), not
  preemptively for every call.
- **Bulkhead** — add when one flaky dependency can starve resources (threads,
  connections) needed by unrelated calls. Skip it for a service with one
  outbound dependency — there's nothing to isolate it from.
- **Rate limiter** — add at a boundary you must protect from being overrun,
  either callers hitting you or you hitting a quota-limited downstream.

---

## 3. Data Consistency Patterns

### Saga — coordinating a transaction across services

**Problem it solves:** A business process spans multiple services, each
owning its own database, so no single ACID transaction can cover it. A Saga
runs the steps as a sequence of local transactions, each with a
**compensating action** to undo it if a later step fails.

**Use when:** you must maintain consistency across services and genuinely
cannot keep the related data in one service/database. This is a real
distributed-systems investment — target completion rate > 99%, compensation
success rate > 99.9%, average duration under a few seconds. If you're
seeing worse than that in practice, the process is too complex for a Saga
or the compensations aren't well designed.

**Overkill / wrong tool when:**
- The data could reasonably live in one service/database — merge it instead
  of building a distributed transaction around an artificial boundary.
- The business process can't tolerate the user-visible latency (seconds,
  not milliseconds) or the temporary inconsistency window.
- You don't have a real compensating action for every step (e.g. "you can't
  un-send an email" — decide up front what that means for the process).

**Shape of the solution (orchestrated saga):**
```java
class OrderSagaOrchestrator {
    void placeOrder(OrderRequest req) {
        String orderId = orderService.create(req);            // step 1
        try {
            paymentService.charge(orderId, req.getAmount());  // step 2
            inventoryService.reserve(orderId, req.getItems());// step 3
            shippingService.schedule(orderId);                // step 4
        } catch (PaymentFailedException e) {
            orderService.cancel(orderId);                     // compensate 1
            throw new OrderFailedException(orderId, e);
        } catch (InventoryUnavailableException e) {
            paymentService.refund(orderId);                   // compensate 2
            orderService.cancel(orderId);                     // compensate 1
            throw new OrderFailedException(orderId, e);
        }
        // ... every forward step needs a matching compensation for every
        // step that could have already succeeded before it.
    }
}
```
Orchestration (one coordinator, as above) is easier to reason about and
debug. Choreography (each service reacts to the previous service's event,
no central coordinator) scales better organizationally but makes the overall
process harder to see — pick orchestration by default unless you already
have strong event infrastructure and team fluency in it.

---

### Outbox — guaranteed event delivery

**Problem it solves:** "Update the database AND publish an event" is not
atomic across two systems (DB + message broker) — a crash between the two
loses the event or double-publishes it. The Outbox pattern writes the event
to an `outbox` table in the *same local transaction* as the business
change, and a separate process (usually CDC via Debezium) tails that table
and publishes to the broker.

**Use when:** guaranteed event delivery is critical to the business (e.g.
billing, order state changes) and you cannot afford to silently lose an
event. Target > 99.99% delivery rate and CDC lag under ~100ms under normal
load — if you can't get there, check CDC connector health before adding
more moving parts.

**Overkill / wrong tool when:**
- Occasional lost or duplicate events are genuinely tolerable for the use
  case (e.g. best-effort analytics pings) — a direct publish is simpler.
- You don't have (or can't justify) CDC infrastructure/expertise — Outbox
  without reliable CDC is just a table nobody reads.

**Shape of the solution:**
```java
@Transactional
public void createOrder(Order order) {
    orderRepository.save(order);                       // business write
    outboxRepository.save(new OutboxEvent(              // same transaction
        "OrderCreated", order.getId(), toJson(order)));
}
// A CDC connector (Debezium) tails the outbox table's transaction log and
// publishes each row to Kafka, then the row is marked/deleted as published.
// The application never talks to Kafka directly on the write path.
```
Downstream consumers must still be **idempotent** (dedupe by event ID) —
Outbox guarantees at-least-once delivery, not exactly-once processing.

---

### CQRS & Event Sourcing — separate reads from writes / model state as events

**Problem it solves (CQRS):** a single model optimized for both
transactional writes and flexible querying often serves neither well. CQRS
splits a normalized write model (consistency, validation) from a
denormalized read model (query performance), which can even live in a
different data store.

**Problem it solves (Event Sourcing):** instead of storing current state,
store the sequence of events that produced it — giving a full audit trail,
point-in-time reconstruction, and easy new read models by replay.

**Use CQRS when** read and write workloads have genuinely different scaling
or modeling needs. **Use Event Sourcing when** you need a full audit trail
(financial, healthcare), time-travel/undo, or you're already building
event-driven services and the extra modeling cost is justified.

**Overkill / wrong tool when:** the domain is simple CRUD with one
reasonable model of the data — CQRS and Event Sourcing both roughly double
the moving parts (two models to keep in sync; a whole event store and
projection/replay machinery) for no payoff if nobody needs an audit trail
or independent read/write scaling.

---

### Cache-Aside — reducing load on a slow/expensive data source

**Problem it solves:** repeated reads of the same rarely-changing data hit
a database or downstream service harder than necessary. Cache-Aside puts
the caching decision in application code: check the cache, on a miss load
from the source and populate the cache, and evict/expire on writes.

**Use when:** read-heavy access to data that tolerates some staleness, and
the source is measurably a bottleneck or cost center.

**Overkill / wrong tool when:** the data changes on every read anyway (no
reuse to exploit), or strict read-your-own-writes consistency is required
and you haven't designed cache invalidation for it — a stale cache serving
wrong account balances is worse than a slower correct read.

```java
public InventoryLevel getInventory(String sku) {
    InventoryLevel cached = redis.get(key(sku), InventoryLevel.class);
    if (cached != null) return cached;                    // cache hit

    InventoryLevel fresh = inventoryDb.load(sku);          // cache miss
    redis.set(key(sku), fresh, Duration.ofMinutes(5));     // TTL, not forever
    return fresh;
}

public void updateInventory(String sku, InventoryLevel level) {
    inventoryDb.save(sku, level);
    redis.delete(key(sku));  // invalidate rather than update-in-place
}
```
Prefer a short TTL plus invalidation-on-write over trying to keep the cache
perfectly in sync — the failure mode of a TTL (briefly stale) is much safer
than the failure mode of a missed invalidation (permanently wrong).

---

## 4. Decision Table

| Symptom you're seeing | Pattern to consider |
|---|---|
| Rewrite of the monolith keeps stalling / too risky to do in one shot | Strangler Fig |
| Core system can never be safely touched but needs new digital features | Leave and Layer |
| New code getting polluted by legacy field names / implicit rules | Anti-Corruption Layer |
| Every service reimplements retry/mTLS/observability differently | Sidecar / Service Mesh |
| Need isolation for exactly one flaky external/legacy dependency, not a full mesh | Ambassador |
| One dependency times out and takes down unrelated requests with it | Circuit Breaker |
| Outbound calls have no timeout / hang indefinitely | Timeout (baseline — not optional) |
| Transient failures on a safe-to-repeat call | Retry with backoff (idempotent ops only) |
| One dependency's slowness exhausts threads/connections needed elsewhere | Bulkhead |
| Callers or you can overrun a quota-limited endpoint | Rate Limiter |
| Business process spans multiple services/DBs, no single transaction possible | Saga |
| DB write + event publish must both happen or neither (no lost/duplicate events) | Outbox |
| Read and write workloads need different models/scaling | CQRS |
| Need full audit trail / time-travel / replay of state changes | Event Sourcing |
| Same slow read repeated against DB/downstream constantly | Cache-Aside |
| None of the above symptoms are present | Don't add a pattern — ship the simple version |

**Default posture:** start simple (direct calls, one database, synchronous
requests). Add exactly one pattern at a time, driven by an observed
symptom above, not by "best practice" alone. Every pattern here costs real
operational complexity — team expertise, monitoring, and failure modes of
its own — and that cost must be smaller than the problem it solves.
