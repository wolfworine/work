---
name: reactive-java-webflux
description: Use whenever writing or reviewing reactive Java code (Project Reactor, Spring WebFlux, R2DBC, Mono/Flux), when deciding map vs flatMap, handling errors in a reactive chain, spotting an accidental block()/subscribe() inside a non-blocking pipeline, designing R2DBC repositories or transactions, or deciding whether a feature actually needs reactive/async design versus a simpler blocking or virtual-threads approach.
---

# Reactive Java (Project Reactor / WebFlux / R2DBC)

Operational reference for reactive Java backends built on Project Reactor and Spring WebFlux, with R2DBC for relational data access. Distilled for correctness review and hands-on coding — not a tutorial. For the extended operator catalog and longer R2DBC/transaction examples, see `references/operators.md`.

## Core mental model

- `Mono<T>` = 0 or 1 result. `Flux<T>` = 0..N results. Both are **Publishers** — lazy, declarative descriptions of a pipeline, not values and not running computations.
- **Nothing happens until something subscribes.** Building a chain (`repo.findById(id).map(...).flatMap(...)`) only assembles a pipeline. Assembly time (where operators are wired) and subscription time (where data actually flows) are different moments — this is why side effects placed incorrectly (e.g. inside `map` as a mutation, or code that runs "once" but is actually inside a `Mono` that gets subscribed to multiple times) surprise people.
- Reactor streams are **cold** by default: each new subscriber re-runs the pipeline from the source (a new DB query, a new HTTP call) unless you explicitly share/cache (`.cache()`, `.share()`).
- In a WebFlux app, the framework subscribes for you (once per incoming request, when writing the HTTP response). Application code should almost never call `.subscribe()` itself.

## The cardinal rule: never `.block()` / `.subscribe()` inside a reactive chain

Calling `.block()`, `.blockFirst()`, `.blockLast()`, `.toIterable()`, or `.subscribe()` from *within* code that is itself part of a reactive pipeline (a controller, service, repository, or anything invoked from one) defeats the entire non-blocking model: it parks the event-loop/worker thread that WebFlux/Reactor Netty needs for other requests, and under load this degrades or deadlocks the whole application (the classic "everything hangs at 200 rps" bug).

```java
// WRONG — blocks a Netty event-loop thread inside the reactive chain
public Task getTask(Integer id) {
    return taskRepository.findById(id).block(); // never do this in service/controller code
}

// WRONG — subscribing manually inside a chain double-runs the pipeline
// and returns before the async work is even done (fire-and-forget bug)
public Mono<Task> createTask(Task t) {
    taskRepository.save(t).subscribe();
    return Mono.just(t); // stale/incomplete object returned to caller
}

// RIGHT — return the Publisher, let the framework subscribe
public Mono<Task> getTask(Integer id) {
    return taskRepository.findById(id);
}
```

The **only** legitimate places for `.block()`/`.subscribe()`:
- The very edge of the program: a `main()` method, a CLI entry point, or a batch job bootstrap that has no caller expecting a `Mono`/`Flux` back.
- Test code (`StepVerifier` is preferred over `.block()`, but `.block()` in a JUnit test is fine).
- Deliberately bridging a **legacy blocking API** you cannot avoid calling, isolated behind `Schedulers.boundedElastic()` so it doesn't steal event-loop threads — and even then, only if you can't push the blocking call to the true system boundary instead.

Review heuristic: if a method's return type is `Mono<T>`/`Flux<T>` and its body contains `.block()`/`.subscribe()`, that's very likely a bug, not a style choice.

## map vs flatMap — the most common mistake

- **`map(Function<T,R>)`** — synchronous, one-to-one, in-memory transformation. No I/O, no Reactor types in, no Reactor types out. Use for pure data shaping (DTO conversion, field extraction, arithmetic).
- **`flatMap(Function<T, Publisher<R>>)`** — the function itself returns a `Mono`/`Flux` (i.e., triggers more async work: another DB call, another HTTP call). `flatMap` subscribes to the inner publisher and flattens the result into the outer stream. Use it whenever the next step is itself asynchronous.

```java
// WRONG — map() with a function that returns a Mono produces Mono<Mono<Task>>,
// forcing an ugly and usually incorrect .block() or a compile error downstream
Mono<Mono<Task>> broken = repo.findById(id).map(t -> repo.save(t));

// RIGHT — flatMap unwraps/flattens the inner publisher
Mono<Task> chained = repo.findById(id)
    .flatMap(t -> repo.save(t));
```

Corollary: `flatMapMany` goes `Mono<T> -> Flux<R>` (one item fans out into many, e.g. loading a parent then streaming its children). `concatMap` is `flatMap` that preserves strict ordering of inner sequences at the cost of concurrency — use it when order matters more than throughput.

## Operator cheat sheet (the ones you'll use constantly)

| Operator | Semantics | Use when |
|---|---|---|
| `map` | sync 1:1 transform | pure data shaping, no I/O |
| `flatMap` | async 1:1→N, unordered/concurrent | next step is itself a `Mono`/`Flux` (DB/HTTP call) |
| `concatMap` | like `flatMap` but preserves order, sequential | order matters, concurrency doesn't |
| `filter` | drop items not matching a predicate | validation/exclusion mid-chain |
| `switchIfEmpty(Mono/Flux)` | supply an alternative publisher if upstream completes empty | "not found → return default / 404 / fallback lookup" instead of nesting ifs |
| `defaultIfEmpty(value)` | supply a plain fallback value if empty | same as above but a static value, not another async call |
| `onErrorResume(fn)` | catch an error, resume with another `Mono`/`Flux` | recover with a fallback data source, or turn an error into a domain-friendly empty/default result |
| `onErrorMap(fn)` | catch an error, rethrow as a different (typically domain) exception | translate technical exceptions (`R2dbcException`) into business exceptions before they reach the controller |
| `onErrorReturn(value)` | catch an error, emit one static value | simplest fallback, no further async work needed |
| `retry(n)` / `retryWhen(spec)` | resubscribe to the source on error | transient failures (network blips); use `retryWhen` with `Retry.backoff(...)` for real systems, never bare unlimited `retry()` |
| `zip(a, b, ...)` | wait for all sources, combine emissions pairwise | need results from N independent calls before proceeding, want the fastest possible total latency |
| `merge(a, b)` | interleave emissions as they arrive, concurrently | combine multiple streams of the same type, order doesn't matter |
| `concat(a, b)` | run sequentially, `a` fully completes before `b` starts | strict ordering of independent publishers required |
| `doOnNext` / `doOnError` / `doOnSubscribe` / `doFinally` | side effects only — logging, metrics | never mutate business state here; use `map`/`flatMap` for transformations that produce the next value |
| `collectList()` / `collectMap()` | `Flux<T> -> Mono<List<T>>` | you genuinely need everything materialized at once (careful: unbounded → memory risk) |
| `then()` / `thenReturn(v)` / `thenMany(flux)` | discard upstream value(s), continue with something else | "do this write, then return that other thing" |

Extended list (buffer/window, sampling, timeout, `Mono.zipWith`, testing with `StepVerifier`, `Retry.backoff` details) lives in `references/operators.md`.

## Backpressure basics

Reactive Streams backpressure is a **pull** protocol: a subscriber calls `request(n)` to tell the publisher how many items it can currently handle; the publisher must never emit more than requested. This is what prevents a fast producer (e.g., streaming rows from a DB, or a fast upstream service) from overwhelming a slow consumer (e.g., a browser on a slow connection, or a rate-limited downstream API).

- In ordinary WebFlux request/response handling you rarely touch this directly — the HTTP layer and R2DBC driver negotiate it for you.
- It matters when: streaming large result sets (`Flux<Row>` from a big table), fanning out to a slow external API, or writing a custom `Subscriber`.
- Tools when you need to intervene: `limitRate(n)` (re-batches requests upstream), `onBackpressureBuffer()/Drop()/Latest()` (explicit overflow strategy — pick deliberately, don't default to unbounded buffer), and simply not calling `collectList()`/`toIterable()` on something that could be unbounded.
- Red flag in review: any `Flux` from a DB or external stream getting `.collectList()`'d or `.buffer(Integer.MAX_VALUE)`'d with no size bound — this converts backpressure-safe streaming into an OOM risk.

## Reactive anti-patterns to flag in review

- `.block()`, `.blockFirst()`, `.blockLast()`, `.subscribe()`, `.toIterable()` anywhere inside a method that returns or participates in a `Mono`/`Flux` chain.
- A blocking call (JDBC, blocking HTTP client, `Thread.sleep`, synchronous file I/O) invoked from inside a reactive pipeline without pushing it onto `Schedulers.boundedElastic()` — or better, replaced with a non-blocking equivalent (R2DBC instead of JDBC, WebClient instead of RestTemplate).
- Side effects (mutating shared state, incrementing counters, writing to a field) inside `map`/`flatMap` instead of `doOnNext` — makes intent unclear and breaks under re-subscription/retry.
- Swallowing errors: `onErrorResume(e -> Mono.empty())` with no logging, hiding failures as silent "not found" results.
- Unbounded buffering: `collectList()`/`buffer()` on a potentially large or infinite `Flux` with no limit.
- Treating a cold `Mono`/`Flux` as if it were a cached value — subscribing to it twice re-executes the DB call/HTTP call twice; use `.cache()` if a single result should be shared.
- Fire-and-forget `.subscribe()` on a write operation without capturing/propagating its errors (errors thrown inside a bare `.subscribe()` are only sent to the global hook, not the caller).
- Using `flatMap` where `concatMap` was needed (ordering bugs that only show up under concurrency/load) or vice versa (unnecessary loss of parallelism).
- Wrapping already-reactive return types in `CompletableFuture` or blocking wrapper types "to make them easier to use" — this reintroduces a thread-blocking boundary.

## R2DBC essentials (vs JDBC/JPA)

R2DBC is a **non-blocking** SPI for relational databases — no thread parks while waiting on the network/database round-trip, unlike JDBC (which is blocking by contract, hence Spring Data JPA is always blocking under the hood no matter how "reactive" the layer above tries to look). Key practical differences from JPA:

- No first-level cache, no lazy-loading proxies, no automatic dirty checking. What you fetch is what you get — related entities must be fetched explicitly (extra query or projection), there's no `@OneToMany` lazy magic.
- Repositories return `Mono`/`Flux`, never `List`/`Optional`/plain objects.
- Connection pooling is explicit via `r2dbc-pool`, and transactions must be reactive-aware (`ReactiveTransactionManager` / `TransactionalOperator`), not the classic JDBC `PlatformTransactionManager`.

Minimal repository + entity:

```java
@Table("tasks")
class Task {
    @Id
    private Integer id;
    private String description;
    private Boolean completed;
    // getters/setters/constructor
}

interface TaskRepository extends ReactiveCrudRepository<Task, Integer> {

    @Modifying
    @Query("UPDATE tasks SET completed = :completed WHERE id = :id")
    Mono<Integer> updateStatus(Integer id, Boolean completed);
}
```

Service layer — note `flatMap` chaining read→write, and `@Transactional` on a method returning `Mono` (Spring wraps the reactive chain in a reactive transaction, no manual commit/rollback calls):

```java
@Service
class TaskService {
    private final TaskRepository repository;

    @Transactional
    public Mono<Task> updateDescription(Integer id, String description) {
        return repository.findById(id)
            .switchIfEmpty(Mono.error(new TaskNotFoundException(id)))
            .flatMap(task -> {
                task.setDescription(description);
                return repository.save(task);
            });
    }
}
```

Never call `.block()` to "simplify" a repository call inside a service — every example above returns the `Mono`/`Flux` all the way up to the controller, where WebFlux subscribes. For multi-statement transactions, custom SQL beyond simple CRUD, or joining across the reactive driver's SPI directly, see `references/operators.md`.

## Does this actually need to be reactive? (decision heuristic)

The Reactive Manifesto's four traits — **Responsive** (timely responses even under load/failure), **Resilient** (failure in one part doesn't cascade), **Elastic** (scales up/down with load), **Message-driven** (components communicate via async messages, enabling location/time decoupling) — are a means to an end, not a checklist to satisfy for its own sake. Reactive/non-blocking earns its complexity when:

- The system is **I/O-bound with high concurrency**: many simultaneous slow network calls (downstream services, streaming DB queries, WebSockets/SSE) where thread-per-request would exhaust the thread pool.
- You need **elasticity** under bursty load without linearly scaling threads/memory with concurrent connections.
- The architecture is genuinely **message/event-driven** (Kafka, AMQP) and reactive composition matches the domain (event pipelines, not simple request/response CRUD).

It is usually **not** worth it — plain blocking code (or Java virtual threads, which give blocking-style code non-blocking-scale concurrency without the reactive operator chain, debugging pain, or stack-trace obscurity) is the better call when:

- The workload is **computation-bound** rather than I/O-bound (reactive buys you nothing there and adds real overhead).
- Request volume is low/internal, or it's a simple CRUD service with no fan-out to slow dependencies.
- The team is not fluent in reactive idioms yet — a wrong `map`/`flatMap` choice or an accidental `.block()` is a subtle production incident, not a compile error, and reactive stack traces are harder to debug.
- The one path that must stay blocking touches a library with no non-blocking equivalent — better to isolate it (its own thread pool / virtual thread) than to force everything else into reactive style to accommodate it.

Rule of thumb for review: if a codebase is fighting the reactive model everywhere (blocking calls wrapped in `Mono.fromCallable` + `boundedElastic` scattered throughout, or lots of `.block()` "just this once"), that's a signal the feature — or the whole service — didn't need to be reactive in the first place.

## Vert.x: verticles, the event bus, and its own backpressure model

Vert.x achieves non-blocking I/O the same way Reactor/Netty does — a small pool of event-loop threads that must **never block** — but exposes it through callbacks, `Future`/`Promise` composition (`map`, `recover`, `compose`), or optionally RxJava/Kotlin coroutines on top, rather than Reactor's `Mono`/`Flux` operator chains being the primary API. The underlying rule is identical to the cardinal rule above: never block an event-loop thread; push unavoidable blocking work to a worker pool (Vert.x's "worker verticle" / `executeBlocking`, analogous to Reactor's `Schedulers.boundedElastic()`). There is no universally "best" async model — plain futures/promises are simpler for straightforward chaining, Reactor/RxJava-style operators shine for rich event-stream composition (retries, windowing, merging streams), and coroutine-style code reads the most like synchronous code at the cost of language-level support. Pick per problem, don't assume Reactor is the only valid shape for "non-blocking."

**Verticles** are Vert.x's deployment/concurrency unit — actor-inspired: each verticle instance has private state and processes all its events (timers, HTTP requests, event-bus messages) on a **single event-loop thread**, so a plain `long` counter field needs no `synchronized`/`AtomicLong` inside one verticle (contrast with Reactor, which has no equivalent "one thread owns this object" guarantee — a `Mono`/`Flux` pipeline can hop schedulers via `publishOn`). `AbstractVerticle` exposes `start()`/`stop()` lifecycle hooks (setup/teardown — e.g., closing DB connections in `stop()`); you deploy instances via `vertx.deployVerticle(...)`, and can deploy many instances of the same verticle to spread event-loop load across cores (Vert.x picks a different event-loop thread per instance, round-robin).

**Event bus** — verticle-to-verticle messaging, decoupling producers from consumers by destination name (a free-form string, dot-separated by convention, e.g. `datastore.new-purchase-orders`). Three patterns:

| Pattern | Semantics | Use when |
|---|---|---|
| Point-to-point | One of possibly many consumers gets each message (round-robin) | Work distribution across consumer instances |
| Request-reply | Point-to-point + a temporary reply destination auto-generated per call | Mimicking an RPC, but non-blocking — caller doesn't park waiting |
| Publish/subscribe | Every subscriber gets every message | Fan-out where you don't know/care how many listeners exist |

**The event bus is not a message broker** — it has no acknowledgments, no priorities, no durability (a message is lost if no consumer is up when it's sent), no routing/transformation rules. It's a fast in-process (optionally clustered) conveyor for verticle-to-verticle events; reach for Kafka/RabbitMQ (see `kafka-event-driven-java`) when a message must survive a crash or needs broker-level guarantees.

**Vert.x's own backpressure model** (distinct from Reactor's `request(n)` pull protocol, though solving the same problem — e.g., streaming a multi-GB file to a slow HTTP client without buffering the whole thing in memory): a `ReadStream` exposes `pause()`/`resume()`/`fetch(n)`; a `WriteStream` exposes `setWriteQueueMaxSize(int)`, `writeQueueFull()`, and `drainHandler(...)`. The standard recipe — write each chunk, check `writeQueueFull()`, `pause()` the read side if full, `resume()` it from the `drainHandler` callback once the write queue has drained — is a push/pause protocol (the source pauses itself) rather than Reactor's pull protocol (the sink requests a bounded amount up front), but both exist to stop a fast producer from exhausting memory ahead of a slow consumer.

Testing verticles is closer to integration testing than unit testing (a verticle is an opaque box you interact with over the event bus or a network protocol it exposes) via the `vertx-junit5` module: inject `Vertx` and `VertxTestContext` into a `@BeforeEach`, deploy the verticle under test, and use `ctx.succeeding(...)`/`ctx.completeNow()` to bridge Vert.x's async deployment callback into a synchronous-looking JUnit 5 test. Full example and connection-pooling config table live in `references/operators.md`.

**Cross-link**: *Reactive Systems in Java* (one of this skill's sources) is subtitled *Resilient, Event-Driven Architecture with Quarkus* — if you're building on Quarkus specifically, see `quarkus-cloud-native-java` for the CDI/Panache/native-image side; this skill stays framework-agnostic on the reactive-programming model itself.

**Known source gap**: a fourth book, *Reactive Java Programming* (Andrea Maglie), was originally listed as a source for this skill but the PDF in this project's corpus is a scanned image with no extractable text layer (`arquetipos-md/arq-033`, 6 words recovered — just the cover). Any content in this skill that might read as attributed to that specific book should be treated as standard RxJava/reactive-programming knowledge, not a literal extraction — the book itself was never actually readable.
