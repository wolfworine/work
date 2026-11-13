---
name: quarkus-fault-tolerance
description: Use whenever adding resilience to a Quarkus service — timeouts, retries (backoff, jitter, abortOn/retryOn), circuit breaker, bulkhead, fallback, rate limit, @Asynchronous with Uni/CompletionStage, SmallRye Fault Tolerance / MicroProfile Fault Tolerance annotations and their config properties, REST Client timeouts, Kafka consumer failure strategies (dead-letter-queue), choosing what to retry (idempotency, 429/5xx), preventing retry storms and cascading failures, or reviewing code that calls a remote service without timeout/fallback.
---

# Fault Tolerance in Quarkus (SmallRye FT / MicroProfile FT 4.1)

**Sources (provider/standard only):** Quarkus "SmallRye Fault Tolerance" guide (3.40), MicroProfile Fault
Tolerance 4.1 spec, Quarkus REST client + Kafka guides, Microsoft Azure Architecture Center (Retry,
Circuit Breaker, Bulkhead, Transient faults), AWS Builders' Library (timeouts/retries/jitter), Google SRE ch.22.
**[summary]** = from a model-extracted part of a page: verify the exact name/default on the guide before relying on it.
**Not read:** smallrye.io fault-tolerance docs (404). **Not confirmed:** exact nesting order of annotations
(spec text gives only interceptor priorities), `@ApplyFaultTolerance` (doesn't appear; the guide uses
`@ApplyGuard` / the Guard API **[summary]**), virtual-thread behavior of FT.

Extension: `quarkus-smallrye-fault-tolerance` (add as a dependency).

## 1. The annotations

| Annotation | Purpose | Key attributes (defaults **[summary]**) |
|---|---|---|
| `@Timeout` | Bound a call | `value` (1 s), `unit` — throws `TimeoutException` |
| `@Retry` | Retry transient failures | `maxRetries` (3), `delay` (0), `jitter` (200 ms), `maxDuration` (3 min), `retryOn`, `abortOn` |
| `@CircuitBreaker` | Fail fast when dependency is sick | `requestVolumeThreshold` (20), `failureRatio` (0.5), `delay` (5 s), `successThreshold` (1), `failOn`, `skipOn` |
| `@Bulkhead` | Cap concurrent calls | `value` (10), `waitingTaskQueue` (10, async only) |
| `@Fallback` | Degraded answer when all else fails | `fallbackMethod`, `value` (FallbackHandler class), `applyOn`, `skipOn` |
| `@RateLimit` (SmallRye addition) | Limit calls per window | `value`, `window`, `windowUnit`, `minSpacing`, `type` (`fixed`/`rolling`/`smooth`) |
| `@Asynchronous` / `@AsynchronousNonBlocking` | Run async; must return `Future`/`CompletionStage` (or `Uni` in Quarkus) | `enabled` |
| `@ExponentialBackoff` / `@FibonacciBackoff` / `@CustomBackoff` | Backoff for `@Retry` | factor (2), maxDelay (1 min) |
| `@RetryWhen`, `@BeforeRetry` | Retry on result/exception predicate; hook before each retry | |

```java
@ApplicationScoped
public class PricingClient {
  @Inject @RestClient PricingApi api;

  @Timeout(800)                                   // ms
  @Retry(maxRetries = 2, delay = 100, jitter = 100, retryOn = IOException.class,
         abortOn = IllegalArgumentException.class)
  @CircuitBreaker(requestVolumeThreshold = 20, failureRatio = 0.5, delay = 5000)
  @Fallback(fallbackMethod = "cachedPrice")
  public Price price(String sku) { return api.get(sku); }

  Price cachedPrice(String sku) { return priceCache.getOrDefault(sku); }  // same params + return type
}
```

## 2. Semantics you must know (MP FT 4.1 spec, verbatim-based)

- **Retry decision:** returns normally -> return. Throws something matching `abortOn` -> rethrow.
  Matches `retryOn` -> retry. Otherwise rethrow.
- **Timeout:** throws `TimeoutException`; without `@Asynchronous` the thread is **interrupted** — this may not
  stop blocking I/O or CPU-bound work, and the result is discarded. Timeout **restarts on each retry**.
  A `TimeoutException` can count as a circuit-breaker failure depending on `failOn`.
- **Fallback:** runs after retries are exhausted; with a circuit breaker it runs on any failure, and always
  when the circuit is open. Fallback method must be on the same/super class or interface, with the **same
  parameter types and return type**, accessible; else `FaultToleranceDefinitionException`.
  `value` and `fallbackMethod` can't both be set.
- **Circuit breaker states:** Closed (rolling window; opens when failure proportion > `failureRatio`) ->
  Open (calls fail at once with `CircuitBreakerOpenException`; after `delay` -> Half-open) -> Half-open
  (`successThreshold` trial calls; any failure re-opens; all succeed closes). Transitions reset the records.
- **Asynchronous:** method must return `Future`/`CompletionStage` or definition error. With `CompletionStage`,
  other annotations apply to the stage's completion (so `@Retry` fires if it completes exceptionally).
- **Bulkhead:** sync = semaphore style, rejects over-limit with `BulkheadException`; with `@Asynchronous` =
  thread-pool style with a queue; full queue -> `BulkheadException` **[summary]**.
- **Interceptor priority:** base `4010` (`Priority.PLATFORM_AFTER+10`), configurable
  `mp.fault.tolerance.interceptor.priority` (startup only).
- Metrics are produced automatically for the FT annotations (Micrometer if present, else OpenTelemetry) **[summary]**.

## 3. Configuration without recompiling (Quarkus **[summary]**)

```properties
# per method / per class / global; members in kebab-case
quarkus.fault-tolerance."org.acme.PricingClient/price".retry.max-retries=1
quarkus.fault-tolerance."org.acme.PricingClient".timeout.value=500
quarkus.fault-tolerance.global.retry.max-retries=2
# MP-style key also works:  org.acme.CoffeeResource/coffees/Retry/maxRetries=6   (Quarkus keys win)
quarkus.fault-tolerance.enabled=true            # default
quarkus.fault-tolerance.mp-compatibility=false  # default
```
Mutiny: `Uni` and `CompletionStage` are supported; in the default (non-MP-compatible) mode async FT applies
without `@Asynchronous`, and retry/circuit breaker/fallback inspect the exception **cause chain**.
Alternative inside a pipeline (REST client guide): `.onFailure().retry().atMost(10)`.

## 4. Design rules (reputable sources)

1. **Timeout first, on every outbound call** (Microsoft: set a timeout before adding retries; AWS: pick it from the
   downstream p99.9, e.g. 0.1% false-timeout rate). REST Client defaults **[summary]**: connect 15 s, read 30 s —
   set them explicitly: `quarkus.rest-client."name".connect-timeout`, `.read-timeout`.
2. **Retry only transient failures on idempotent operations.** Retry 429 and 5xx; **don't** retry 400/401/403/404;
   honor `Retry-After` (Microsoft). "Generally, it's not safe to retry POST or PATCH" — use an idempotency key
   (see `idempotency-design`).
3. **Backoff + jitter + cap + budget.** Randomized exponential backoff, limit attempts, never retry forever;
   a server-wide retry budget (Google SRE example: 60 retries/min per process; Microsoft: same example).
4. **Retry in one layer only.** Retries multiply: 3 attempts at 2 layers = 9; 3 at 5 layers = 3^5 = 243x load (AWS).
   Don't stack `@Retry` on the caller and the callee and a mesh retry.
5. **Circuit breaker for long-lasting faults**; breaker + fallback so users get degraded service, not errors.
   Prefer a half-open limit so a recovering service isn't flooded (Microsoft).
6. **Bulkhead per dependency** (separate pools/semaphores) so one slow dependency can't starve the rest.
7. **Fallback must be cheap and safe** (cache, default, "pending") — never another remote call that can fail the same way.
8. **Log each retry/fallback as WARN with correlation id; alarm on breaker-open.** Use a dead-letter queue so exhausted
   work isn't lost (Microsoft).
9. Compensating steps in sagas must be **idempotent** and retried before compensating (Microsoft).

## 5. Kafka consumers (Quarkus Kafka guide **[summary]**)

`failure-strategy`: `fail` (default; stops, offset not committed), `ignore` (log + commit), `dead-letter-queue`
(commit + write record to DLT, headers `dead-letter-reason/cause/topic/partition/offset`; topic default
`dead-letter-topic-$channel`), `delayed-retry-topic`. Commit strategy `throttled` gives at-least-once even with async
processing. "Kafka consumers only guarantee at-least-once processing of single records" => consumers must be
idempotent (see `idempotency-design`). The guide does not discuss idempotent-consumer deduplication.

## 6. Anti-patterns to reject in review

| Smell | Why | Fix |
|---|---|---|
| `@Retry` with no `@Timeout` | Each retry can hang | Timeout on the call and per attempt |
| `@Retry` on POST without idempotency key | Duplicate side effects | Idempotency-Key / make operation idempotent |
| `maxRetries` high, `delay=0`, no jitter | Retry storm | Backoff + jitter + budget |
| `@Fallback` that calls the same dependency | Fails together | Cache/default |
| Catch-all `retryOn = Exception.class` | Retries 4xx/validation | `retryOn` transient only, `abortOn` the rest |
| Breaker thresholds untested | Never opens / flaps | Load-test thresholds; watch metrics |
| Timeout + `synchronized`/blocking native I/O | Interrupt doesn't free the thread | Client-level timeouts too |
| Retries at gateway, service and client | Multiplicative load | One layer |

## 7. Testing fault tolerance

- Unit-test the fallback method directly; integration-test with a stub server that returns 500/timeouts
  (e.g. `quarkus-wiremock` fault/delay stubs) and assert call counts and the fallback result.
- Assert the circuit opens after `requestVolumeThreshold` failures and re-closes after `delay` + successes.
- See `quarkus-testing-standards`.

Related: `microservices-antipatterns`, `enterprise-java-design-patterns`, `java21-virtual-threads-quarkus`,
`quarkus-observability`.

## Sources

- https://quarkus.io/guides/smallrye-fault-tolerance
- https://download.eclipse.org/microprofile/microprofile-fault-tolerance-4.1/microprofile-fault-tolerance-spec-4.1.html
- https://quarkus.io/guides/rest-client, https://quarkus.io/guides/kafka
- https://learn.microsoft.com/en-us/azure/architecture/patterns/retry, .../circuit-breaker, .../bulkhead, .../best-practices/transient-faults
- https://builder.aws.com/content/3EumjoZascWd1oZiEgL8ORlv3qE/timeouts-retries-and-backoff-with-jitter
- https://sre.google/sre-book/addressing-cascading-failures/
