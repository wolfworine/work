---
name: java21-virtual-threads-quarkus
description: Use whenever deciding how to handle threads/concurrency in a Java 21 service — Project Loom virtual threads vs platform threads vs reactive, creating threads or executors (Thread.ofVirtual, Executors.newVirtualThreadPerTaskExecutor), @RunOnVirtualThread in Quarkus REST/Messaging/gRPC/Scheduler, which thread Quarkus runs a method on (event loop vs worker vs virtual), limiting concurrency with Semaphore instead of pools, pinning (synchronized/native/JDBC drivers), ThreadLocal misuse, carrier monopolization by CPU-bound work, scheduler properties (jdk.virtualThreadScheduler.*), StructuredTaskScope/ScopedValue status, testing for pinning with @ShouldNotPin, or diagnosing with jcmd/JFR/jdk.tracePinnedThreads.
---

# Java 21 Virtual Threads (Project Loom) + Quarkus

**Source policy:** only the technology provider's own documentation — Oracle Java SE 21 docs
(docs.oracle.com, Core Libraries guide + API javadoc), inside.java, and the Quarkus project
(quarkus.io guides + the Quarkus team's blog). Blogs/LinkedIn/third-party sites are excluded.
**Not read (HTTP 403):** openjdk.org JEP 444/453/446, jdk.java.net release notes. Nothing below
is taken from them. Items tagged **[summary]** came from a model-extracted part of a page —
re-check the exact name on the page before using it in code. Quarkus page version checked: 3.40.

## 1. What a virtual thread is (and isn't)

- A `java.lang.Thread` not bound to an OS thread. Blocking I/O unmounts it and frees the carrier
  (platform) thread, so plain blocking code scales.
- Oracle: "not faster threads … They exist to provide **scale (higher throughput), not speed
  (lower latency)**". Not for long CPU-intensive operations.
- Rule of thumb (Oracle): if you never have ~10,000 concurrent virtual threads, you are unlikely to benefit.
- Virtual threads are **final in Java 21** (no preview marker on the Thread/Executors API pages).
- Quarkus needs Java 21+ (`maven.compiler.release=21`). No global "run everything on virtual
  threads" switch exists in Quarkus (Quarkus blog).

Thread API facts (Oracle javadoc, Java 21):
- Always **daemon** (don't keep the JVM alive), **fixed priority** (can't change), **no name by default**
  (`getName()` is `""`), run in a special ThreadGroup; not counted by `activeCount()`/`enumerate()`
  and absent from `getAllStackTraces()` **[summary]**. `isVirtual()` tells them apart.
- Creating one does not capture the caller's security context.

## 2. Creating them

```java
Thread t = Thread.ofVirtual().name("worker-", 0).start(task);   // worker-0, worker-1, ...
Thread.startVirtualThread(task);                                 // shorthand
ThreadFactory f = Thread.ofVirtual().factory();                  // Thread.Builder.OfVirtual

try (var ex = Executors.newVirtualThreadPerTaskExecutor()) {     // new thread per task, NOT a pool
  var a = ex.submit(() -> callA());
  var b = ex.submit(() -> callB());
  use(a.get(), b.get());                                         // close() waits for all tasks
}
```
`newVirtualThreadPerTaskExecutor()` (Since 21) is **unbounded** — bound it yourself (Semaphore).

## 3. The rules (Oracle Core Libraries guide)

1. **Thread-per-request, simple blocking code.** "Blocking them is cheap and encouraged."
   Don't mix blocking code into asynchronous frameworks.
2. **Represent every concurrent task as a virtual thread; never pool them.** They're never reused
   by unrelated tasks. Replace `newFixedThreadPool(n)` used for concurrency.
3. **Limit concurrency with `Semaphore`,** not pool size: `acquire()` … `finally { release(); }`.
   A DB connection pool already acts as a semaphore.
4. **ThreadLocal:** fine for context (transaction, user id), but never cache expensive reusable
   objects in it (millions of threads = millions of copies): `SimpleDateFormat` -> shared
   immutable `DateTimeFormatter`. Prefer **scoped values** for one-way context. `InheritableThreadLocal`
   copies per thread ("expensive inheritance"); call `remove()` to avoid leaks.
5. **Don't run long CPU-bound work on them:** the scheduler doesn't preempt, so it "monopolizes the
   carrier thread" (Quarkus).

## 4. Scheduler (Oracle Thread javadoc, JDK reference implementation)

| Property | Meaning | Default |
|---|---|---|
| `jdk.virtualThreadScheduler.parallelism` | platform threads available to schedule virtual threads | number of available processors |
| `jdk.virtualThreadScheduler.maxPoolSize` | max platform threads the scheduler may use | 256 |

## 5. Pinning — the main production risk

Pinned = the virtual thread keeps its carrier while blocked. Doesn't break correctness; hurts scalability.

| Java | Pins when |
|---|---|
| 21–23 | blocking inside `synchronized` block/method, or inside a `native` method / foreign function |
| 24+ (JEP 491, "Synchronize Virtual Threads without Pinning", per inside.java and Quarkus page) | `synchronized` no longer pins; native code calling back into blocking Java still can |

- Oracle: short or infrequent work under `synchronized` has "no adverse effect". Replace
  `synchronized` only around **long-lived, frequent blocking**, with `ReentrantLock`
  (`lock(); try {…} finally { unlock(); }`).
- The Oracle pages do **not** state whether file I/O or `Object.wait()` pins in 21 — don't assume;
  measure with JFR.
- **JDBC:** PostgreSQL driver < **42.6.0** pins frequently on Java 21–23 (Quarkus). Upgrade it.
- **ThreadLocal-pooling libraries** (Jackson, Netty) are counter-productive on virtual threads (Quarkus blog 1).
- Quarkus's five caveats (blog 1): pinning, monopolization, carrier-pool elasticity (memory),
  ThreadLocal object pooling, thread safety. Quarkus states it's "not possible to use virtual threads in a carefree manner".

## 6. Quarkus: which thread runs your code

REST (Quarkus REST guide, "Execution model"):
- Returns `Uni` / `Multi` / `CompletionStage` / `Publisher` / Kotlin `suspend` -> **event loop** (never block it).
- Returns a plain value -> **worker thread** (pool from `quarkus.thread-pool.*`).
- A method/class with `@jakarta.transaction.Transactional` is treated as **blocking**.
- `@Blocking` / `@NonBlocking` (`io.smallrye.common.annotation`) on method, class, or the `Application` subclass (default for the app).
- `@RunOnVirtualThread` (`io.smallrye.common.annotation.RunOnVirtualThread`) runs the method on a
  **new virtual thread**; in Quarkus REST it "can only be used on endpoints annotated with
  `@Blocking` or considered blocking because of their signature". Falls back to platform threads
  when virtual threads aren't supported.
- Worker pool defaults **[summary]**: `quarkus.thread-pool.core-threads=1`, max-threads = max(8 x CPUs, 200),
  keep-alive 30s, queue unbounded.

```java
@GET
@RunOnVirtualThread
public List<Fortune> list() { return repository.listAll(); }   // blocking style, plain return type
```

Other extensions (each has its own guide):
- **Reactive Messaging:** `@RunOnVirtualThread` only on methods eligible for `@Blocking` (e.g. `void consume(I in)`,
  `Uni<Void>`, `CompletionStage<Void>`); class-level applies to `@Blocking` methods. By default messages are
  processed **sequentially**; `@RunOnVirtualThread` => concurrent, **unordered**. Default max concurrency **1024**;
  override `smallrye.messaging.worker.<virtual-thread>.max-concurrency`, or a named pool via `@Blocking("my-worker")`
  + `smallrye.messaging.worker.my-worker.max-concurrency=30`.
- **gRPC:** `@RunOnVirtualThread` on `@GrpcService` methods; **methods receiving streams (e.g. `Multi`) cannot use it.**
- **Scheduler:** `@RunOnVirtualThread` on `@Scheduled` methods; must return `void`; control overlap with `concurrentExecution = SKIP`.
- **REST Client**, Redis client, mailer, Vert.x SQL clients (`andAwait()`) are virtual-thread friendly (REST client "does not pin").
- **Config:** `quarkus.virtual-threads.name-prefix` (default `quarkus-virtual-thread-`; empty disables) **[summary]**.
  `quarkus.execution-model-annotations.detection-mode` (default `fail`; `warn`/`disabled`) validates misuse of
  `@Blocking`/`@NonBlocking`/`@RunOnVirtualThread` **[summary]**. An injectable `@VirtualThreads ExecutorService` is experimental **[summary]**.
- **Event loop rule (duplicated-context guide):** "You must NEVER block these event loops." REST, gRPC, REST Client,
  Reactive Messaging, scheduler and CDI request scope run on **duplicated contexts**; `@RunOnVirtualThread` methods
  inherit it, but ThreadLocals are not propagated **[summary]**.
- **Context propagation guide:** `quarkus-smallrye-context-propagation`; `@RequestScoped/@ApplicationScoped/@Singleton`
  propagate, `@Dependent` doesn't; `ManagedExecutor` uses the Quarkus thread pool.

## 7. Where the bottleneck moves: the DB pool

Virtual threads remove the thread limit but not downstream limits. Agroal
`quarkus.datasource.jdbc.max-size` defaults to **50** (all-config; the 2023 blog's "20" is stale).
Size it deliberately and expect requests to queue on the pool (`acquisition-timeout` default 5s).
Quarkus/Narayana/Hibernate were patched to avoid pinning (Quarkus blog). No Quarkus page read gives
a REST-level concurrency cap for virtual-thread endpoints — use the pool, a Semaphore, or rate limiting.

## 8. Testing and diagnosing

- Java 21–23: `-Djdk.tracePinnedThreads=full|short` (Surefire/Failsafe `<argLine>`); prints stacks, doesn't fail tests;
  not available on 24+.
- Quarkus test extension **[summary]**: `io.quarkus.junit:junit-virtual-threads` (test scope), `@VirtualThreadUnit`,
  `@ShouldNotPin`, `@ShouldPin(atMost = 1)` in `io.quarkus.test.junit.virtual`; detects pinning via JFR.
  (The 2023 blog used `me.escoffier.loom:loom-unit` 0.3.0 — superseded.)
- **JFR events** (Oracle): `jdk.VirtualThreadPinned` (on by default, 20 ms threshold), `jdk.VirtualThreadSubmitFailed`
  (on), `jdk.VirtualThreadStart/End` (off).
- **Thread dump** with virtual threads: `jcmd <PID> Thread.dump_to_file -format=json <file>` (or `text`).
- Micrometer binder **[summary]**: `quarkus.micrometer.binder.virtual-threads.enabled` (needs `micrometer-java21`).
- Native image supported on Java 21+ with GraalVM/Mandrel **[summary]**.

## 9. Structured concurrency and scoped values — still PREVIEW in Java 21

- `StructuredTaskScope` (+ `ShutdownOnFailure`, `ShutdownOnSuccess`) and `ScopedValue` are **preview APIs in 21**:
  need `--enable-preview` (flags not named on the pages; verify). Don't use them in production code on 21.
- API names (Oracle javadoc): `fork(Callable)` -> `Subtask`, `join()`, `joinUntil(Instant)`, `shutdown()`;
  `ScopedValue.newInstance()`, `where(key,value)`, `runWhere`, `callWhere`, `get()`, `isBound()`, `orElse`.
  Overload signatures of `throwIfFailed`/`result` differed between pages — check the nested class javadoc.
- inside.java shows the API kept changing: structured concurrency re-previewed in JDK 22, 23 … and
  scoped values targeted final in JDK 25 (JEP 506). **Don't assume the 21 API is stable.**

## 10. Decision guide

1. I/O-bound request/response service, imperative team -> **blocking code + `@RunOnVirtualThread`**.
2. Streaming, backpressure, huge fan-out, or gRPC streams -> keep **reactive (Mutiny)** on the event loop.
3. CPU-bound -> bounded platform pool (~cores).
4. A dependency pins (old JDBC, long `synchronized` + I/O) -> upgrade/replace, or keep that path on worker threads.
5. Always bound concurrency (Semaphore / DB pool / messaging max-concurrency) and set timeouts on every
   outbound call (see `microservices-antipatterns`).

## 11. Review checklist

- [ ] No pool around virtual threads; limits via `Semaphore` / connection pool / `max-concurrency`.
- [ ] No long blocking I/O inside `synchronized` (Java 21–23); `ReentrantLock` instead.
- [ ] No expensive objects cached in ThreadLocal; formatters immutable.
- [ ] No CPU-heavy loops on virtual threads.
- [ ] PostgreSQL JDBC >= 42.6.0; Agroal `max-size` sized on purpose.
- [ ] Nothing blocks the event loop; `@RunOnVirtualThread` only on blocking-signature methods (not on `Uni`, not on gRPC streams).
- [ ] `@ShouldNotPin` test or JFR `jdk.VirtualThreadPinned` checked under load.
- [ ] Messaging ordering requirements checked before enabling `@RunOnVirtualThread` (it removes ordering).
- [ ] No preview APIs (`StructuredTaskScope`, `ScopedValue`) on Java 21 in production.

Related: `modern-java-language-features`, `quarkus-cloud-native-java`, `reactive-java-webflux`,
`spring-boot-high-scale-architecture`, `microservices-antipatterns`.

## Sources (provider/framework only)

- https://docs.oracle.com/en/java/javase/21/core/virtual-threads.html and .../thread-local-variables.html
- https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Thread.html, .../util/concurrent/Executors.html,
  .../StructuredTaskScope.html, .../ScopedValue.html, and Core Libraries pages for structured concurrency / scoped values
- https://inside.java/tag/loom/ (JEP 491 and preview timeline; titles only)
- https://quarkus.io/guides/virtual-threads, /rest, /rest-virtual-threads, /messaging-virtual-threads, /grpc-virtual-threads,
  /scheduler-reference, /duplicated-context, /context-propagation, /quarkus-reactive-architecture, /all-config
- https://quarkus.io/blog/virtual-thread-1/, /virtual-threads-2/, /virtual-threads-3/ (Sept–Oct 2023; some details outdated)
