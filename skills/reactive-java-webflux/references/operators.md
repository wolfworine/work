# Extended Reactor / R2DBC reference

Companion to `../SKILL.md`. Load this file when the concise cheat sheet in the main skill isn't enough detail — extended operator list, transaction patterns, and testing.

## Extended operator catalog

### Creation
- `Mono.just(value)` / `Flux.just(v1, v2, ...)` — wrap already-available value(s). Careful: arguments are evaluated eagerly at assembly time, not on subscribe — for lazy/deferred evaluation use `Mono.fromSupplier`/`Mono.defer`.
- `Mono.fromCallable(() -> blockingCall())` — wraps a blocking call as a `Mono`; **must** be combined with `.subscribeOn(Schedulers.boundedElastic())` to avoid blocking the caller's thread.
- `Mono.defer(() -> ...)` / `Flux.defer(...)` — defers publisher creation until subscribe time; use when the publisher depends on state that must be read fresh per subscription (avoids the "captured stale value" bug).
- `Mono.empty()` / `Flux.empty()` — zero items, completes immediately.
- `Mono.error(ex)` / `Flux.error(ex)` — immediately signals an error, no items.
- `Flux.range(start, count)`, `Flux.interval(Duration)`, `Flux.fromIterable(...)`, `Flux.fromStream(...)`.

### Transformation
- `map` — sync 1:1.
- `flatMap` — async 1:1→N, concurrent/unordered (default concurrency 256; tune with the overload that takes `concurrency`).
- `flatMapSequential` — like `flatMap` but preserves the original ordering of the *outer* sequence while still subscribing to inner publishers concurrently (a middle ground between `flatMap` and `concatMap`).
- `concatMap` — sequential, ordered, one inner publisher at a time.
- `flatMapMany` — `Mono<T> -> Flux<R>`.
- `transform(fn)` — apply a reusable operator chain (extracted as a `Function<Flux<T>, Flux<R>>`) without breaking the fluent chain; good for sharing a validation/logging pipeline across multiple call sites.
- `cast(Class)`, `ofType(Class)` — type narrowing/filtering.

### Filtering / selecting
- `filter(predicate)`, `filterWhen(asyncPredicate)` (predicate itself is async — returns `Mono<Boolean>`).
- `switchIfEmpty(alternative)`, `defaultIfEmpty(value)`.
- `take(n)`, `takeLast(n)`, `takeUntil(predicate)`, `takeWhile(predicate)`.
- `skip(n)`, `skipUntil(predicate)`, `skipWhile(predicate)`.
- `distinct()`, `distinctUntilChanged()`.
- `next()` — `Flux<T> -> Mono<T>` (first element only).
- `single()` — expects exactly one element, errors otherwise (contrast with `next()` which is lenient).

### Combining
- `zip(a, b, ...)` / `Mono.zip` / `Flux.zip` — pairwise combine once all sources have an item available; total time ≈ slowest source. Best when you need N independent results before proceeding and want them fetched concurrently.
- `merge(a, b)` — subscribes to all sources eagerly, interleaves emissions as they arrive; unordered.
- `mergeSequential(a, b)` — subscribes eagerly like `merge` but preserves per-source emission order in the output.
- `concat(a, b)` — subscribes to `b` only after `a` completes; use for strict sequencing (e.g., "delete children, then delete parent").
- `firstWithValue(a, b)` — race sources, take whichever emits first (previously `firstWithSignal`/`amb`).
- `combineLatest(a, b, combiner)` — recompute a combined value whenever *any* source emits; for state derived from multiple live streams (rare in typical CRUD backends, more relevant in stream-processing/dashboard-style code).

### Error handling
- `onErrorResume(Class, fn)` — scope recovery to a specific exception type; prefer this over a blanket `onErrorResume(fn)` that would also swallow bugs.
- `onErrorMap(Class, fn)` — translate infra exceptions into domain exceptions before they leak past the service layer.
- `onErrorReturn(value)` / `onErrorReturn(Class, value)`.
- `onErrorComplete()` — treat an error as normal completion (rare — usually a smell unless the error genuinely means "nothing more to emit").
- `retry(n)` — blind resubscribe, no backoff; fine for truly instantaneous transient blips only.
- `retryWhen(Retry.backoff(3, Duration.ofMillis(100)).maxBackoff(Duration.ofSeconds(2)))` — the production-grade version: bounded retries with exponential backoff. Always bound retries; unlimited retry against a truly down dependency is a self-inflicted DoS.
- `Retry.fixedDelay(...)`, `.filter(throwable -> ...)` to only retry specific exceptions (e.g., retry on connection resets, not on validation errors).
- `timeout(Duration)` — fail fast instead of waiting forever on a stuck upstream; combine with `onErrorResume`/`retryWhen` for a full resilience story.

### Side effects (never mutate business state here — logging/metrics/tracing only)
- `doOnNext`, `doOnError`, `doOnComplete`, `doOnSubscribe`, `doOnCancel`, `doFinally` (runs on completion, error, *or* cancellation — good place for cleanup/resource release/metric decrement).

### Backpressure / flow control
- `limitRate(n)` — rebatches the request(n) amounts sent upstream; use when a downstream consumer is much slower than the source.
- `onBackpressureBuffer(maxSize, overflowStrategy)` — explicit bounded buffer with a defined overflow action (error, drop oldest, drop latest). Never leave this unbounded in production code.
- `onBackpressureDrop()` / `onBackpressureLatest()` — for sources where losing intermediate values is acceptable (e.g., high-frequency sensor ticks where only the latest matters).
- `buffer(size)` / `buffer(Duration)` / `window(size)` — batch items; `window` returns a `Flux<Flux<T>>` for further per-batch processing, `buffer` returns `Flux<List<T>>` directly.
- `sample(Duration)` — thin a fast stream down to periodic snapshots.

### Terminal / conversion
- `collectList()`, `collectMap(keyFn)`, `collectMap(keyFn, valueFn)` — materialize a bounded `Flux` into a single `Mono` container. Only use on sequences you know are bounded and reasonably sized.
- `then()`, `thenReturn(v)`, `thenMany(flux)`, `thenEmpty(mono)`.
- `blockOptional()` / `.block()` / `.blockFirst()` / `.blockLast()` — edge-of-program / test-only, per the cardinal rule in `SKILL.md`.

## Schedulers quick reference

- `Schedulers.parallel()` — CPU-bound work, fixed pool sized to core count. Never do blocking I/O here.
- `Schedulers.boundedElastic()` — the escape hatch for unavoidable blocking calls (legacy JDBC, blocking SDKs, file I/O); bounded thread pool, grows on demand, has a queue+cap to avoid unbounded thread creation.
- `Schedulers.immediate()` — runs on the calling thread; effectively a no-op scheduler, useful mainly as a default/test value.
- `subscribeOn(scheduler)` — affects where the **source**/subscription-time work happens; only the first `subscribeOn` in a chain has effect (it's an assembly-time signal, not applied at that point in the chain).
- `publishOn(scheduler)` — switches the thread for everything **downstream** of that point in the chain; can be used multiple times to hop schedulers at different chain stages.

## R2DBC transaction patterns

**Preferred (Spring-managed, declarative):**

```java
@Service
class TaskService {
    private final TaskRepository repository;

    @Transactional
    public Mono<Task> moveTask(Integer id, String newDescription) {
        return repository.findById(id)
            .switchIfEmpty(Mono.error(new TaskNotFoundException(id)))
            .flatMap(task -> {
                task.setDescription(newDescription);
                return repository.save(task);
            });
        // Spring wraps the whole reactive chain in a reactive transaction via
        // ReactiveTransactionManager; commit/rollback happen automatically based
        // on completion/error signals. Requires a ConnectionFactory-backed
        // ReactiveTransactionManager bean (Spring Boot autoconfigures one).
    }
}
```

**Programmatic, when you need transaction boundaries that don't align with a single method** (`TransactionalOperator`):

```java
@Service
class TaskService {
    private final TaskRepository repository;
    private final TransactionalOperator txOperator;

    public Mono<Void> transferOwnership(Integer fromId, Integer toId) {
        Mono<Void> work = repository.findById(fromId)
            .flatMap(from -> repository.findById(toId)
                .flatMap(to -> {
                    from.setOwnerId(to.getOwnerId());
                    return repository.save(from).then();
                }));
        return txOperator.transactional(work);
    }
}
```

Do **not** reach for the raw R2DBC SPI (`Connection.beginTransaction()`/`commitTransaction()`/`rollbackTransaction()`) in application code — that's driver-level plumbing that Spring Data R2DBC (or any R2DBC client library) already manages for you via `@Transactional`/`TransactionalOperator`. Manual SPI-level transaction handling is appropriate only when writing a client library or driver integration itself, and even reference material that demonstrates it explicitly notes it required `.block()` calls "you'd likely not do in a truly reactive application" — a sign it belongs at the SPI/library layer, not in a service.

**Savepoints**: the R2DBC SPI supports `createSavepoint()`/`rollbackTransactionToSavepoint()`/`releaseSavepoint()` for rolling back part of a transaction without aborting the whole thing (e.g., "try this optional step, and if it fails, keep everything committed up to before it"). Not all drivers implement them — a driver without savepoint support throws `UnsupportedOperationException` rather than silently no-op-ing, so check driver docs before depending on this. Like raw transaction control, this is SPI-level — reach for it only from custom transaction-management code, not typical service-layer logic.

## R2DBC connection pooling (`r2dbc-pool`)

R2DBC has no built-in pooling — `io.r2dbc:r2dbc-pool` (built on `reactor-pool`) wraps any driver's `ConnectionFactory` to add one, and Spring Boot autoconfigures it when `r2dbc-pool` is on the classpath and the URL uses the `pool` driver prefix (`r2dbc:pool:postgresql://...`). Key options (set via `ConnectionFactoryOptions` or the pooled URL's query string):

| Option | Meaning | Default |
|---|---|---|
| `initialSize` | Connections created up front | 10 |
| `maxSize` | Pool ceiling | 10 |
| `maxIdleTime` | Evict a connection idle longer than this | unset |
| `maxLifeTime` | Force-recycle a connection past this age (guards against long-lived-connection issues on the DB side) | unset |
| `maxAcquireTime` | How long a caller waits for a connection before failing | unset (waits indefinitely — set this in production, an unbounded wait under pool exhaustion just moves the failure from "clear timeout" to "silent hang") |
| `acquireRetry` | Retries on the first acquisition attempt if it fails | 1 |
| `validationQuery` | Query run before handing out a connection, to catch stale/dead connections | unset |

`maxSize` for a reactive pool is not the same sizing problem as a blocking JDBC pool (HikariCP): a blocking pool needs roughly one connection per concurrent blocking thread, but a non-blocking R2DBC pipeline can multiplex far more in-flight logical requests over far fewer actual DB connections, since nothing parks a connection while waiting on other I/O. Don't carry over a HikariCP-sized `maxSize` assumption unchanged — undersizing is a real risk (`maxAcquireTime` timeouts under load) but oversizing just pushes load onto the database's own connection limit for no throughput gain.

## DatabaseClient for queries beyond simple CRUD

When a query doesn't map cleanly onto `ReactiveCrudRepository`/derived-query-method style (dynamic filters, complex joins, bulk operations), use `DatabaseClient` directly instead of dropping to blocking JDBC:

```java
Flux<Task> overdueTasks = databaseClient.sql(
        "SELECT * FROM tasks WHERE completed = false AND due_date < :cutoff")
    .bind("cutoff", LocalDate.now())
    .map((row, meta) -> new Task(
        row.get("id", Integer.class),
        row.get("description", String.class),
        row.get("completed", Boolean.class)))
    .all();
```

## Testing Vert.x verticles (`vertx-junit5`)

```java
@ExtendWith(VertxExtension.class)
class SensorDataServiceTest {
    private SensorDataService dataService;

    @BeforeEach
    void prepare(Vertx vertx, VertxTestContext ctx) {
        vertx.deployVerticle(new DataVerticle(), ctx.succeeding(id -> {
            dataService = SensorDataService.createProxy(vertx, "sensor.data-service");
            ctx.completeNow();          // signals setup is done — deployment is itself async
        }));
    }

    @Test
    void averageIsZeroWithNoSensors(VertxTestContext ctx) {
        dataService.average(ctx.succeeding(avg -> ctx.verify(() -> {
            assertThat(avg).isEqualTo(0.0);
            ctx.completeNow();
        })));
    }
}
```
`Vertx` and `VertxTestContext` are injected via the `VertxExtension`. `ctx.succeeding(handler)` fails the test automatically if the async operation errors instead of succeeding; `ctx.verify(...)` is required around assertions made inside an async callback so a failed `assertThat` is reported as a test failure rather than an uncaught exception on an event-loop thread. Since a verticle is isolated behind the event bus, tests are closer to integration tests than unit tests — send it messages to reach a desired state, deploy lightweight mock verticles in place of ones with heavy dependencies (DB, other services) instead of mocking classes directly.

## Testing reactive code

Prefer `StepVerifier` over `.block()` in tests — it verifies both the emitted values and the completion/error signal, and fails fast on hangs (add `.verify(Duration)` to bound wait time):

```java
StepVerifier.create(taskService.updateDescription(1, "new text"))
    .expectNextMatches(task -> task.getDescription().equals("new text"))
    .verifyComplete();

StepVerifier.create(taskService.updateDescription(999, "x"))
    .expectError(TaskNotFoundException.class)
    .verify();
```
