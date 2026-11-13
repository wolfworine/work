---
name: microservices-antipatterns
description: Use whenever designing, splitting, or reviewing a microservices architecture and you need to know what NOT to do — shared database/schema between services, chatty or synchronous call chains, retries without backoff (retry storms), missing timeouts/deadlines, unbounded queues and no load shedding, distributed transactions, leaking internal schema in APIs, breaking API changes, premature microservices ("Microservice Premium", Monolith First), splitting along the wrong boundaries, missing observability, or cascading failures. Cites Microsoft Azure Architecture Center, Martin Fowler, and Google SRE.
---

# Microservices Anti-patterns (from reputable sources)

Each item states the **source** it comes from. Anything not stated by a source is marked
**(inference)**. Sources actually read: Microsoft Azure Architecture Center (microservices design
pages + performance antipatterns catalog), Martin Fowler (*Microservices* article,
*Microservice Premium*, *Monolith First*), Google SRE Book ch. 22 *Addressing Cascading Failures*,
AWS "Microservices on AWS" whitepaper, microservices.io pattern index.
**Not read (HTTP 404):** the microservices.io anti-patterns article and the AWS Prescriptive Guidance
"decomposing monoliths" guide. The AWS whitepaper and microservices.io index contain no anti-pattern
list. The phrase "distributed monolith" does not appear in the pages read.

## A. Architecture and boundaries

| Anti-pattern | Problem | Do instead | Source |
|---|---|---|---|
| **Starting with microservices** | "You shouldn't start a new project with microservices, even if you're sure your application will be big enough." Teams pay the "microservice premium" in complexity and slow down early. Systems built microservices-first "ended up in serious trouble". | Modular monolith first with clean API/data boundaries; peel services off at the edges, or start with a few coarse services. | Fowler *Monolith First*, *Microservice Premium* |
| **Microservice envy** | Adopting them without a system "too complex to manage as a monolith". "The majority of software systems should be built as a single monolithic application." | Justify with size, team count, independent scaling/evolution needs. | Fowler *Microservice Premium* |
| **Unstable boundaries** | Moving functionality between services is "much harder than in a monolith". Guessing boundaries early is expensive. | Learn the domain in a monolith, then split; boundaries on business capabilities. | Fowler *Monolith First*, *Microservices* |
| **Organizing by technical layer** (UI team / logic team / DB team) | Every change crosses teams. | Organize around business capabilities; team owns the service in production ("products not projects"). | Fowler *Microservices* |
| **Services that always change together** | Changing two services together is "a sign that they should be merged". | Merge them, or redraw the boundary. | Fowler *Microservices*; Microsoft data considerations (merge chatty services) |
| **Shifting complexity into the connections** | "All you are doing is shifting complexity from inside a component to the connections between components." (A coupled set of services = what is commonly called a *distributed monolith* — **inference**, term not on the pages.) | Fewer, coarser services; invest in contracts, tracing, automation. | Fowler *Microservices* |
| **ESB-style smart middleware** | Business logic in the pipe ("Erroneous Spaghetti Box"). | "Smart endpoints and dumb pipes". | Fowler *Microservices* |
| **Centralized governance** | Forces one platform on every team. | Decentralized governance, open standards. | Fowler *Microservices* |

## B. Data

| Anti-pattern | Problem | Do instead | Source |
|---|---|---|---|
| **Shared data store / schema** | "Two services shouldn't share a data store." A schema change must be coordinated across every service using it. Sharing a *server* is OK; sharing the *schema* is the problem. | Database per service (private store); other services use its API/events. | Microsoft *data considerations*; Fowler (decentralized data) |
| **Distributed transactions (2PC)** | "Notoriously difficult to implement." | Eventual consistency, compensating transactions / Saga, durable queue + work item; `Scheduler Agent Supervisor`. | Fowler; Microsoft |
| **Same fact in many places, no owner** | Integrity and consistency drift. | Define required consistency per component; single source of truth where strong consistency is required. | Microsoft |
| **Storing more data than a service needs** | Larger blast radius, coupling. | "Store only the data that a service needs." | Microsoft |
| **Monolithic Persistence** | One data store for data with very different usage patterns. | Right store per workload (polyglot persistence). | Microsoft perf. antipatterns |
| **Busy Database / Extraneous Fetching** | Too much processing in the store; retrieving more data than needed. | Move logic to the service tier; fetch only needed columns/rows. | Microsoft perf. antipatterns |

## C. Communication and APIs

| Anti-pattern | Problem | Do instead | Source |
|---|---|---|---|
| **Chatty I/O / chatty APIs** | Many small network calls; RPC style "can lead to overly chatty APIs"; naive in-memory-to-RPC conversion "doesn't perform well". | Coarser-grained calls; if two services are always chatting, merge them. | Microsoft API design + perf. antipatterns; Fowler |
| **Long synchronous call chains** | Availability = product of the chain's availabilities (Fowler); A->B->C adds latency (Microsoft); downstream down = operation fails. | Async messaging for non-critical hops (loose coupling, load leveling); ideally one synchronous call per user request. | Fowler "Synchronous calls considered harmful"; Microsoft interservice communication |
| **Exposing internals / mirroring the DB schema in the API** | Every internal refactor becomes a breaking change. | Model the domain as a stable contract; change API for new features, not refactors. | Microsoft API design |
| **One API for all clients** | Over/under-fetching per client type. | Backends for Frontends. | Microsoft API design |
| **Breaking API changes / unversioned APIs** | Breaks consumers. | Backward-compatible changes (don't remove fields); new version for breaking changes; deprecate old ones quickly; be tolerant of supplier changes (versioning "as a last resort"). | Microsoft; Fowler |
| **Retrying non-idempotent calls** | "If the caller retries, the operation might be invoked twice." "Generally, it's not safe to retry POST or PATCH." | Idempotent operations / idempotency keys; prefer PUT where possible. | Microsoft interservice communication |
| **Generic data-access APIs (OData/GraphQL) across service boundaries** | Undermines strict boundaries and domain encapsulation. | Use domain-specific APIs when boundaries must be strict. | Microsoft API design |

## D. Resilience (cascading failure)

| Anti-pattern | Problem | Do instead | Source |
|---|---|---|---|
| **No timeouts / very long deadlines** | Requests hold threads, memory, DB connections -> "cascading failures". Servers keep working on requests whose clients gave up. | Set deadlines everywhere; propagate deadline and cancellation; check remaining time at each stage. | Google SRE ch.22; Microsoft |
| **Naive retries** | "Retries can destabilize a system." Retries multiply across layers (4 attempts x 3 layers = 64 on the DB). | Randomized exponential backoff; cap attempts per request; **server-wide retry budget** (e.g. 60 retries/min per process); never retry permanent/malformed errors. | Google SRE; Microsoft *Retry Storm* |
| **No circuit breaker / bulkhead** | Failing dependency drags callers down. | Circuit Breaker, Bulkhead, Timeout. | Fowler "Design for failure"; Microsoft |
| **No load shedding; unbounded queues** | Overload is "the most common cause of cascading failures"; queues saturate and latency explodes. | Reject early (HTTP 503) beyond N in-flight; small bounded queues (<= ~50% of thread pool); LIFO/CoDel; graceful degradation. | Google SRE ch.22 |
| **Bimodal latency / a few slow calls eat all threads** | Some requests wait the full long deadline and exhaust capacity. | Fail fast; deadlines not orders of magnitude over mean latency; per-client in-flight cap (e.g. 25% of threads). | Google SRE ch.22 |
| **Synchronous I/O blocking the calling thread** | Wasted threads under load. | Async I/O, or Java 21 virtual threads for blocking-style code (see `java21-virtual-threads-quarkus`). | Microsoft perf. antipatterns |
| **Improper Instantiation / No Caching** | Recreating shared clients/objects per request; not caching. | Reuse HTTP/DB clients; cache with TTL. | Microsoft perf. antipatterns |
| **Noisy Neighbor** | One tenant takes a disproportionate share. | Quotas, rate limits, isolation. | Microsoft perf. antipatterns |
| **Cold-cache / thundering-herd restarts** (summary-level in SRE page) | Service not provisioned for cold cache falls over on restart; unbounded recovery traffic. | Ramp traffic gradually; don't depend on slack CPU as a safety net. | Google SRE ch.22 (extracted summary — verify) |

## E. Operations and organization

| Anti-pattern | Problem | Do instead | Source |
|---|---|---|---|
| **No per-service monitoring / hidden failures** | Failures are hidden by retries/fallbacks. | Per-service health dashboards, circuit-breaker state, throughput, latency; distributed tracing, correlation IDs. | Fowler (summary part); Microsoft (tracing is a listed challenge) |
| **Skipping automation** | Many services = many deployments. | CI/CD, infrastructure automation (prerequisite of the "premium"). | Fowler *Microservice Premium* |
| **Service mesh "just because"** | Adds complexity to cluster setup/config (retry, circuit breaking, timeouts, mTLS come with it). | Adopt when you need the features. | Microsoft interservice communication |
| **Weak team skills** | "A poor team will always create a poor system." | Invest in skills before splitting. | Fowler *Microservices* (summary part) |

## Review checklist

- [ ] Does each service own its data (no shared schema)? Cross-service flows use events/Saga, not 2PC?
- [ ] Is every outbound call bounded by timeout/deadline, with backoff + retry budget, and idempotent when retried?
- [ ] Circuit breaker/bulkhead on dependencies; load shedding and bounded queues on the server?
- [ ] Longest synchronous chain <= 1–2 hops on the user path?
- [ ] APIs versioned, backward-compatible, not mirroring DB tables?
- [ ] Service boundaries follow business capability; no two services that must always deploy together?
- [ ] Tracing, correlation IDs, per-service dashboards in place?
- [ ] Did we justify microservices over a modular monolith?

Related skills: `enterprise-java-design-patterns` (Saga, Outbox, Circuit Breaker, Strangler, Cache-aside),
`kafka-event-driven-java`, `java21-virtual-threads-quarkus`, `spring-boot-high-scale-architecture`,
`java-hexagonal-architecture`.

## Sources

- https://learn.microsoft.com/en-us/azure/architecture/microservices/design/ (data-considerations, interservice-communication, api-design)
- https://learn.microsoft.com/en-us/azure/architecture/antipatterns/
- https://martinfowler.com/articles/microservices.html, /bliki/MicroservicePremium.html, /bliki/MonolithFirst.html
- https://sre.google/sre-book/addressing-cascading-failures/
- https://docs.aws.amazon.com/whitepapers/latest/microservices-on-aws/microservices-on-aws.html (no pitfall list)
- https://microservices.io/patterns/index.html (patterns only; its anti-patterns article returned 404)
