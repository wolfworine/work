---
name: spring-boot-high-scale-architecture
description: Use whenever tuning a JVM-based Spring Boot service for high throughput or scale — choosing/tuning a garbage collector (G1GC vs ZGC), sizing HikariCP connection pools or Tomcat/Undertow thread pools, designing a caching layer, wiring Resilience4j circuit breakers/retries/bulkheads/timeouts, or reasoning through a staff-level trade-off (throughput vs tail latency, CPU cost vs latency SLA, cloud cost vs headroom) before shipping a change to a production backend.
---

# Spring Boot High-Scale Architecture

## Honest scope note

The source material for this skill is heavily weighted toward one topic: **JVM
garbage-collector selection and tuning (G1GC vs ZGC)**, which it covers in real,
citable depth (region internals, failure taxonomies, production incident
post-mortems, monitoring flags). Connection pooling, thread-pool sizing,
caching, and resilience patterns were only topic *headings* in the source — no
concrete tuning content backed them up. Those sections below are filled with
standard, well-established industry defaults (HikariCP wiki guidance,
Resilience4j docs, common Spring practice), not material distilled from a
deep source — treat them as a solid starting checklist, not as revealed
wisdom. Lead with the GC section; it's where the real depth is.

## 1. JVM Garbage Collector Selection (G1GC vs ZGC) — the deep section

This is the single highest-leverage lever for tail latency and cloud cost in
a high-scale JVM service, and the one most engineers never revisit past the
JVM defaults.

### Decision matrix

| Signal | Choose G1GC | Choose ZGC |
|---|---|---|
| Heap size | 2–32 GB (sweet spot 4–16 GB) | 16 GB–multi-TB |
| Latency tolerance | 50–200 ms jitter acceptable | Need consistent sub-10ms p99/p999 |
| CPU budget | Constrained / cost-sensitive containers | Spare, consistent CPU (16+ cores ideal) |
| Workload | Batch, ETL, reporting, typical web/API backends | HFT, fraud detection, real-time gateways, ML inference, streaming |
| Object churn | Bursty but boundable | High and unpredictable |
| Cloud cost posture | Optimize $ per request | Willing to pay CPU premium for latency |

Rule of thumb: **G1GC is the default until you have a measured tail-latency
SLA that G1 cannot hit.** Don't reach for ZGC speculatively — it costs real
CPU (roughly 1–5% baseline, up to 3–7% throughput loss under heavy load-barrier
pressure) and is *more* fragile, not less, under Kubernetes CPU throttling.

### G1GC internals that matter at scale

- Heap splits into ~2048 uniformly sized regions (1–32 MB each). Regions are
  dynamically tagged Eden / Survivor / Old / Humongous each cycle.
- G1 picks regions to evacuate by a "garbage-first" cost model: highest
  dead-to-live ratio first, subject to a pause budget:
  `-XX:MaxGCPauseMillis=200`
- **Humongous objects**: any single allocation ≥ 50% of region size bypasses
  Young Gen, lands directly in Old Gen, and fragments it. This is the most
  common G1 production failure mode.
  - Symptom: a service occasionally handling large payloads (e.g. 4 MB JSON
    blobs against a 2 MB region size) sees Old Gen fragmentation, aborted
    concurrent marking, forced Full GCs, and multi-second pauses.
  - Fix: raise the region size so typical payloads stay well under the
    humongous threshold: `-XX:G1HeapRegionSize=16M` (max 32M).
- Worst-case G1 pauses on large heaps (32–256 GB) can degrade to 500ms
  compaction pauses or multi-second Full GCs under fragmentation — G1 is not
  a "no worse than 200ms" guarantee, it's a target.

### ZGC internals that matter at scale

- Uses **colored pointers** (metadata bits embedded in the reference) plus
  **load barriers** that fire on every heap reference read, checking/patching
  the pointer if the object has been concurrently relocated. This is what
  lets ZGC compact concurrently without stopping application threads.
- Pause profile: 0.1–2 ms typical, independent of heap size (10 GB or 1 TB
  make no difference to pause length — only to concurrent-cycle CPU cost).
- Cost of that guarantee: every reference load carries extra instructions;
  memory-bound workloads feel this most. Budget 1–5% steady-state CPU
  overhead, more under heavy allocation.
- **ZGC's failure mode is not a pause, it's a stall.** If concurrent marking
  and relocation can't keep up with the allocation rate (typically because
  Kubernetes CPU limits are throttling the background GC threads), the heap
  fills and the process **hard-freezes for 5–10 seconds** — not a GC pause,
  a total allocation stall. This is the classic "we swapped G1 for ZGC,
  latency went from 200ms to <1ms, then under a traffic spike the whole pod
  froze" incident. Fix is capacity, not GC flags: raise the CPU limit/request
  so background GC threads always have headroom.
- Enable Generational ZGC on Java 21+ (`-XX:+ZGenerational`, default in newer
  JDKs) — it restores a Young/Old split inside ZGC, cutting relocation and
  marking work on short-lived objects and improving throughput 20–40% over
  non-generational ZGC. There is very little reason to run non-generational
  ZGC on a modern JDK.
- NUMA: G1 is more NUMA-sensitive (young gen often pinned to one node, cross-
  node evacuation is costly); ZGC's sparse region layout is more NUMA-stable
  but uses more memory bandwidth.

### Production debugging workflow (use this order)

1. **GC logs** — reveal pauses, mixed cycles, humongous events, relocation
   stalls.
   - G1: `-Xlog:gc*,gc+heap,gc+humongous,gc+phases=debug`
   - ZGC: `-Xlog:gc*,gc+heap,gc+reloc,gc+mark=debug`
2. **Safepoint logs** — needed for ZGC stalls, G1 sudden pauses, and JIT
   deopt storms. A thread that never reaches a safepoint (tight native loop,
   JNI spin-wait, pinned virtual-thread carrier) blocks the whole JVM, and
   ZGC is especially sensitive to this because its phases assume fast
   safepoint entry.
3. **JFR profile** — allocation hotspots, churn rate, barrier overhead,
   thread scheduling.
4. **Native Memory Tracking (NMT)** — metaspace, code cache, direct buffers,
   thread stacks (relevant when "the heap looks fine but the container OOMs").
5. **Confirm container limits** — `cpu.cfs_quota_us`, `memory.max`. Most
   "ZGC randomly freezes" incidents trace back here, not to GC tuning.

### Failure taxonomy (recognize these by symptom)

- G1: humongous fragmentation, promotion failure (Old Gen has no room during
  evacuation → Full GC), mixed-collection death spiral (reclaim efficiency
  drops, repeated marking, long compaction), CPU starvation from parallel GC
  threads competing with request threads.
- ZGC: allocation stall under CPU throttling, relocation-set backlog (churn
  exceeds relocation throughput), OS scheduler interference, high load-barrier
  overhead on memory-heavy workloads.

### The staff-level framing

A mid-level engineer asks "which GC is faster?" A staff engineer asks:
*what is the actual SLA, what is the CPU budget, and what does the failure
mode look like when the collector runs out of the resource it depends on?*
G1's failure mode degrades gracefully (longer pauses); ZGC's failure mode is
a cliff (hard freeze) if you under-provision CPU. That asymmetry — not raw
benchmark numbers — is what should drive the choice in a cost-constrained
cloud environment, and it's the kind of trade-off you should be able to state
out loud before changing `-XX:+UseZGC` in production.

## 2. HikariCP connection pool sizing (standard guidance, not from source depth)

- Don't guess a large pool "to be safe" — HikariCP's own guidance formula is
  `connections = ((core_count * 2) + effective_spindle_count)`, and in
  practice most services land on 10–20 total connections even under load,
  because a connection pool is a queue for a serial resource (the DB), not a
  throughput multiplier.
- Set `maximumPoolSize` based on measured DB-side concurrency capacity, not
  application thread-pool size — an oversized pool just moves contention
  from your app to the database's own connection/lock manager.
- Always set `connectionTimeout` (fail fast, e.g. 3–5s) and
  `maxLifetime` (slightly under your DB's/proxy's own connection-recycle
  window, e.g. 25–30 min if the DB drops idle connections at 30 min) so
  the pool never hands out a connection the DB is about to kill.
- Set `leakDetectionThreshold` (e.g. 30s) in any service handling
  unpredictable payloads — an unreturned connection under load silently
  starves the pool long before you see an OOM or timeout that points at it.

## 3. Thread pool sizing (Tomcat / Undertow / `ThreadPoolTaskExecutor`)

- For blocking I/O-bound work (typical Spring MVC + JDBC service), thread
  count should track `HikariCP pool size` roughly 1:1 to 2:1 — more request
  threads than DB connections just means requests queue at the DB instead of
  at Tomcat, with worse observability.
- Separate `corePoolSize` from `maxPoolSize` deliberately: `core` should
  absorb steady-state load; `max` is your burst headroom. A bounded
  `queueCapacity` is required — an unbounded queue turns a downstream slowdown
  into unbounded memory growth and a delayed, harder-to-diagnose OOM.
- For WebFlux/reactive stacks, thread count is not the tuning lever — the
  event-loop is fixed (roughly core-count sized); the actual risk is any
  blocking call inside the reactive chain, which stalls the whole event loop
  for every request sharing it. Any JDBC/blocking library call inside a
  WebFlux handler must go through `Schedulers.boundedElastic()` (or be
  replaced with R2DBC), never run directly on the event-loop threads.

## 4. Caching strategy

- Reach for an in-process cache (Caffeine) for hot, small, read-heavy data
  that tolerates brief staleness (reference data, config, permission checks);
  reach for a distributed cache (Redis) when multiple instances must see the
  same value or the dataset doesn't fit in one JVM's heap.
- Always set an eviction policy and TTL explicitly — an unbounded local
  cache is a slow memory leak, not a performance win.
- Cache invalidation is the actual hard problem: prefer short TTLs with
  cheap re-fetch over "invalidate everywhere on write" unless correctness
  genuinely requires it — cross-instance invalidation adds a distributed
  consistency problem that most read-heavy endpoints don't need.

## 5. Resilience patterns (Resilience4j)

- **Timeout** everything that crosses a network boundary — a call with no
  timeout is a thread (or connection-pool slot) leak waiting for its
  upstream's worst day.
- **Circuit breaker**: trips on a failure-rate/slow-call-rate threshold over
  a sliding window, giving a failing dependency time to recover instead of
  hammering it with retries from every caller simultaneously.
- **Retry**: only for idempotent operations, with backoff + jitter — naive
  fixed-interval retries synchronize across callers and turn a brief blip
  into a thundering-herd outage.
- **Bulkhead**: isolate the thread/connection budget per downstream
  dependency so one slow dependency can't starve threads needed for calls to
  a healthy one — this is the difference between a partial outage and a
  total outage.
- Order matters when composing them: Bulkhead → Timeout → Retry → Circuit
  Breaker (outermost to innermost varies by library convention — the
  principle is that timeout must be tighter than the circuit breaker's
  slow-call threshold, or the breaker never sees the slow calls it's meant to
  catch).

## 6. What separates staff-level judgment from mid-level correctness

- Mid-level: "this config change makes the number go down in a benchmark."
  Staff-level: "this config change trades throughput for tail latency (or
  CPU for memory, or consistency for availability) — here's the failure mode
  under the resource we're now more dependent on, and here's how we'll see it
  coming in production before it pages someone."
- Always name the resource a resilience/performance change makes you *more*
  dependent on (CPU headroom for ZGC, DB connection slots for a bigger
  thread pool, network bandwidth for a bigger cache fanout) and state what
  happens when that resource is constrained — that's the question a staff
  reviewer will ask if you don't answer it first.
- Prefer changes that degrade gracefully over changes that are faster on the
  happy path but fail as a cliff (see G1 vs ZGC above — this pattern repeats
  everywhere: unbounded queues, retry storms, oversized connection pools).
