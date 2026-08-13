---
name: java-hexagonal-architecture
description: Use whenever designing, reviewing, or refactoring a Java backend's package/module structure, when the user mentions hexagonal architecture, ports and adapters, clean architecture, domain isolation, use cases, input/output ports, or dependency inversion at the architecture level, or when reviewing code where business logic is found leaking into a controller, REST resource, JPA repository, or a "fat service" class. Also use when deciding how to structure a new Java module, how to keep the domain layer framework-free, or how to test each architectural layer in isolation.
---

# Java Hexagonal Architecture (Ports & Adapters)

Operational reference distilled from *Designing Hexagonal Architecture with Java* (Davi Vieira, Packt, 2nd ed. — full title: *"...Build maintainable and long-lasting applications with Java and Quarkus"*). Applies to any Java backend regardless of framework (Spring, Quarkus, Micronaut, plain Java). Use it to design new modules, review existing code, or refactor a layered application toward ports & adapters. For the Quarkus-specific wiring the book itself uses in its second half (CDI, RESTEasy Reactive, Panache), see the dedicated section near the end of this skill plus [`quarkus-cloud-native-java`](../quarkus-cloud-native-java/SKILL.md) for everything Quarkus-generic (scopes, config, testing) that isn't specific to the hexagonal wiring itself.

## Core mental model

Three concentric hexagons. Dependencies only point **inward**. Nothing inward ever imports anything outward.

```
Framework hexagon  (adapters: REST controllers, JPA/Mongo/Kafka adapters, CLI, gRPC)
        ↓ implements ports, calls use cases
Application hexagon (use cases, input ports, output port interfaces)
        ↓ calls
Domain hexagon      (entities, value objects, aggregates, domain services, specifications/policies)
```

- **Domain hexagon** — pure business logic. Zero framework imports, zero persistence annotations, zero HTTP concepts. Plain Java (POJOs).
- **Application hexagon** — orchestrates. Defines *what* the system does (use cases) and *what data it needs from outside* (output ports), without deciding *how*. No business rules live here — only data-flow coordination and calls into the Domain hexagon.
- **Framework hexagon** — decides *how*: which web framework exposes the API, which database/message broker persists or emits data. This is the only hexagon allowed to import Spring/Quarkus/JPA/Kafka classes.

Golden rule for review: if you can point at a piece of logic and ask "does this exist because of a business rule, or because of a technology choice?" — business-rule code belongs in Domain, technology-choice code belongs in Framework. Application hexagon code should answer neither question; it just wires the two together.

## When hexagonal architecture is worth it

Reach for it when any of these apply — otherwise a plain layered (API → service → data) structure is faster to bootstrap and perfectly fine:
- The team expects to swap or add a technology later (different DB, add gRPC alongside REST, replace a message broker) without wanting to touch business logic.
- The domain has real business rules worth protecting from accidental coupling to an ORM/framework.
- Multiple teams/vendors will touch the codebase and need a shallow, standardized learning curve.
- You need to unit-test business rules without spinning up a database, container, or web server.

Don't force it onto CRUD-only services with no real business logic — the extra indirection (ports for everything) has no payoff there.

## Package / module structure template

Maven multi-module (or Gradle equivalent), one module per hexagon so the dependency direction is enforced by the build, not just convention:

```
my-system/
├── pom.xml                          (parent/aggregator)
├── domain/
│   ├── pom.xml                      (zero framework dependencies)
│   └── src/main/java/.../domain/
│       ├── entity/                  (Router, Switch, ... — identity + behavior)
│       ├── vo/                      (value objects: Id, Location, IP, Model, Vendor)
│       ├── service/                 (domain services: cross-entity operations)
│       ├── specification/           (Specification<T> business predicates)
│       └── policy/                  (interchangeable algorithms/strategies)
├── application/
│   ├── pom.xml                      (depends on: domain)
│   └── src/main/java/.../application/
│       ├── usecase/                 (use case interfaces — the "what")
│       ├── port/input/              (input port impls — implement use case interfaces)
│       └── port/output/             (output port interfaces — data needed from outside)
└── framework/
    ├── pom.xml                      (depends on: domain, application, + Spring/Quarkus/JPA/etc.)
    └── src/main/java/.../framework/
        ├── adapter/input/rest/      (REST controllers -> call use case interfaces)
        ├── adapter/input/cli/       (alternate input adapter, same use case)
        └── adapter/output/jpa/      (JPA/Mongo/etc. -> implement output ports)
```

`domain`'s `pom.xml`/`module-info.java` should have **no** compile dependency capable of leaking a framework type into a domain class. That's the enforceable version of "purity" — a code reviewer shouldn't have to eyeball every import; the build should refuse to compile if someone adds `spring-web` to `domain`.

## Domain hexagon rules

**Entities** — have identity, business rules, and business data together (never just data). Identity via a wrapped `Id` value object, not a raw `Long`/`String`. Use static factory methods to separate "reconstituting an existing entity" from "creating a new one":

```java
public class RouterId {
    private final UUID value;
    private RouterId(UUID value) { this.value = value; }
    public static RouterId withId(String id) { return new RouterId(UUID.fromString(id)); }
    public static RouterId withoutId() { return new RouterId(UUID.randomUUID()); }
}
```

**Value objects** — no identity, immutable, describe/quantify/measure something. Use them instead of primitives whenever a primitive carries implicit meaning (an `Activity` instead of a raw `String`, a `Type` enum instead of a boolean flag). Prevents "primitive obsession" and stops parsing logic from leaking into every caller.

**Aggregates** — a consistency boundary. Pick an aggregate root; all mutations to child objects go through the root, and persistence commits through the root too. Keep aggregates small (favor performance/scalability) unless there's a clear reason to grow them.

**Domain services** — for operations that don't naturally belong to any single entity or value object (e.g., "find router by predicate across a collection", "create network only if the address isn't already used"). Distinct from a Spring/Quarkus `@Service` — a domain service is a plain class with zero framework awareness, used purely to avoid dumping unrelated behavior onto an entity.

**Specifications & policies** — for encapsulating business rules and interchangeable algorithms explicitly, rather than scattering `if` conditions:

```java
public interface Specification<T> {
    boolean isSatisfiedBy(T t);
    Specification<T> and(Specification<T> other);
}
```
A **policy** (a.k.a. strategy) encapsulates *how* something is computed/processed and is meant to be swappable (e.g., two different `EventParser` implementations — regex-based vs. split-based). A **specification** encapsulates a *predicate* about an object's validity/state, and specifications compose (`and`, `or`).

**Everything in Domain is a POJO.** No `@Entity`, `@Service`, `@Component`, `@Transactional`, no JPA/Hibernate imports, no Jackson annotations. If persistence or serialization needs shape hints, that mapping belongs in the Framework hexagon's adapter, translating between a framework-specific data object and the domain object.

## Application hexagon rules

**Use cases** — interfaces expressing user/system intent, independent of transport. Name them after the actor's goal (`RouterManagementUseCase`, not `RouterService`). Optionally back each use case with a short written description (Cockburn-style) or a Cucumber feature file before writing the interface — it keeps the method list honest about what the system actually needs to do.

**Input ports** (a.k.a. primary ports) — the concrete class implementing a use case interface. This is where orchestration happens: pull data via output ports, hand it to domain entities/services for business rules, then persist/emit the result via output ports again. An input port must **not** contain business rules itself — if you find an `if` that encodes a business constraint here, move it into the Domain hexagon.

```java
public interface RouterManagementUseCase {
    Router changeLocation(Router router, Location location);
}

public class RouterManagementInputPort implements RouterManagementUseCase {
    private final RouterManagementOutputPort outputPort;   // injected

    public RouterManagementInputPort(RouterManagementOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    @Override
    public Router changeLocation(Router router, Location location) {
        router.changeLocation(location);          // business rule lives on the entity
        return outputPort.persistRouter(router);   // delegate persistence, don't know how
    }
}
```

**Output ports** (a.k.a. secondary ports) — interfaces stating *what data the application needs from outside*, technology-agnostic. Do **not** model them as generic repositories tied to a persistence framework:

```java
// Good: expresses intent, not persistence tech
public interface RouterManagementOutputPort {
    Router retrieveRouter(RouterId id);
    Router persistRouter(Router router);
}

// Bad (anti-pattern): leaks Spring Data/JPA into a port meant to be technology-agnostic
public interface RouterRepository extends JpaRepository<RouterEntity, Long> { ... }
```
The problem with the "bad" example: every method inherited from `JpaRepository` becomes part of the contract whether or not the use case needs it, and the port can no longer be backed by a file, a cache, or a different DB without lying about its shape. Output ports are broader than "repository" — they cover databases, message queues, files, external HTTP APIs, anything living outside the application.

Application hexagon has zero business rules of its own — it's pure data-flow control.

## Framework hexagon rules

**Input adapters** — translate an external protocol (HTTP, CLI, gRPC, message consumer) into a call on a use case interface. An input adapter should be thin: parse/validate the transport payload, map it to domain/value objects, call the use case, map the result back to a transport response. Multiple input adapters can front the same use case (a REST adapter and a CLI adapter both calling `RouterManagementUseCase`) — this is cheap and low-risk.

**Output adapters** — implement output port interfaces using a specific technology (JPA/Hibernate, MongoDB, Kafka producer, REST client to another system, local file). Own all the translation between the domain model and whatever schema/DTO the technology requires (mappers). Multiple output adapter categories (e.g., DB adapter + legacy-subsystem adapter both implementing the same port during a migration) are **more expensive** to maintain than multiple input adapters, because each one needs its own translation mechanism — flag this as a maintainability risk in review, not just an implementation detail.

Framework hexagon is the only place allowed to know about Spring/Quarkus DI annotations, `@RestController`/`@Path`, `@Entity`, `@Transactional`, message broker clients, etc.

## Dependency direction & wiring

Enforce the Dependency Inversion Principle at the **architecture** level, not just the class level: high-level modules (Domain, Application) never import low-level modules (Framework). Wiring options, from simplest to most rigorous:

1. **Plain DI container (Spring/Quarkus/CDI)** — the everyday approach. Input/output ports are interfaces in `application`; adapters in `framework` implement them and get autowired/injected by the container. The container is the only thing that "knows" both sides.
2. **Java Platform Module System (JPMS)** — stricter, compiler-enforced. `application`'s `module-info.java` declares `provides UseCase with InputPortImpl`; `framework` declares `uses UseCase` and resolves it via `ServiceLoader`. Same idea for output ports (`framework` provides `OutputPortImpl` for an `application`-declared output port). Worth it when you need the build itself to fail if someone tries to bypass the abstraction, or want a minimal custom runtime image.

Either way, the adapter/controller class should depend on the **interface** (`RouterManagementUseCase`), never on the concrete input port class (`RouterManagementInputPort`) — that's the DIP in one line, and it's the single most common thing to check in review.

## Wiring the Framework hexagon with Quarkus (CDI, RESTEasy Reactive, Panache)

The source book spends its second half turning the JPMS-wired example above into a Quarkus application — this is the concrete "how" behind the generic "Plain DI container" wiring option, and it's worth knowing even outside Quarkus because the shape of the refactor (remove manual wiring, let the container inject) is the same for Spring or Micronaut.

**From manual/JPMS wiring to CDI beans** — three mechanical changes, always in this order:
1. **Output adapter → managed bean.** Put `@ApplicationScoped` on the adapter class implementing the output port (e.g. `RouterManagementH2Adapter implements RouterManagementOutputPort`). Nothing else changes yet.
2. **Input port → managed bean, output port injected.** Put `@ApplicationScoped` on the input port class and replace its manual `setOutputPort(...)` method (or constructor parameter, in a non-CDI/JPMS version) with a field-level `@Inject` of the output port interface. CDI resolves it to the adapter from step 1 by type — the input port never names the concrete adapter class.
   ```java
   @ApplicationScoped
   public class RouterManagementInputPort implements RouterManagementUseCase {
       @Inject RouterManagementOutputPort routerManagementOutputPort;
       // no more setOutputPort() — CDI supplies it
   }
   ```
3. **Input adapter → inject the use case.** The REST/CLI/etc. input adapter drops its manual construction of the input port and instead does `@Inject private RouterManagementUseCase routerManagementUseCase;` on itself. It still only ever refers to the **interface** — swapping the input port implementation never touches the adapter.

The payoff: `setOutputPort()`-style manual wiring methods and hand-written constructors disappear entirely from ports/adapters; the only thing that changes is which `@ApplicationScoped`/`@Inject` beans exist. This is a good litmus test in review — a hexagonal Quarkus codebase that still has manual setter-based wiring alongside CDI usage has an incomplete migration.

**Imperative vs Reactive input adapters — decision table** (RESTEasy Reactive; source book, Ch. 12):

| | Imperative | Reactive |
|---|---|---|
| Threading model | One blocking worker thread per in-flight request (Tomcat-style thread pool) | A small pool of non-blocking I/O threads, each handling many concurrent requests via continuations |
| Code shape | Sequential, straightforward to read | Continuation/pipeline-style (`Uni`/`Multi` in Quarkus/Mutiny — see [`reactive-java-webflux`](../reactive-java-webflux/SKILL.md) for the reactive operator vocabulary itself) |
| Fails under | High concurrency with long-running requests — thread pool exhaustion causes outright request failures, not just slowness | Rarely thread-starves, but adds real complexity: reactive code is harder to write, debug, and reason about |
| Default choice | CPU-bound work, low/moderate concurrency, or team unfamiliar with reactive | High-concurrency I/O-bound services (many slow downstream calls in flight at once) |

This is the same imperative-vs-reactive judgment call as anywhere else — don't reach for reactive input adapters by default just because Quarkus makes it easy; the source book itself frames it as a genuine trade-off, not a strict upgrade.

**Panache as an output adapter's implementation detail**: Panache (Active Record via `extends PanacheEntity`, or Repository via `implements PanacheRepository<T>`) is what actually implements the output adapter's persistence logic once you're on Quarkus + Hibernate Reactive. The **output port interface stays exactly the same, technology-agnostic contract described above** — Panache is purely an implementation choice inside the Framework hexagon's output adapter, never something the Application or Domain hexagon knows about. See [`quarkus-cloud-native-java`](../quarkus-cloud-native-java/SKILL.md) for the full Panache active-record-vs-repository decision table; here the only architectural rule that matters is that the `@Entity`-annotated Panache class **is** the output adapter's internal persistence model, and if it also plays double-duty as the Domain hexagon's entity, that's the "anemic domain model" / "framework annotations in Domain" anti-pattern from the checklist above creeping back in through Panache specifically — keep a separate mapping between the Panache entity and the pure-POJO domain entity if the two need to diverge.

The book's remaining chapters (Docker image for the hexagonal app, Kubernetes Deployment/Service objects, deploying to minikube) are generic containerization/deployment concerns, not hexagonal-specific — see [`java-devops-toolchain`](../java-devops-toolchain/SKILL.md) and [`kubernetes-for-developers`](../kubernetes-for-developers/SKILL.md) for that material instead of duplicating it here.

## Testing strategy per hexagon

| Hexagon | What to test | How | What NOT to use |
|---|---|---|---|
| Domain | Entities, value objects, aggregates, domain services, specifications | Plain unit tests, no mocks needed — everything is a POJO | Spring context, database, HTTP, mocking frameworks |
| Application | Input ports orchestrate correctly, output ports called with right data | Unit tests with output ports mocked/faked; or BDD (Cucumber) tied to written use-case descriptions | Real database/broker — application hexagon has no framework dependency to spin up |
| Framework | Adapters translate correctly end-to-end; the whole assembled system behaves | Integration tests hitting real (or in-memory/H2/Testcontainers) adapters through the input adapter down to the output adapter | N/A — this is the layer where real infra belongs |

A healthy hexagonal codebase has the most tests in Domain (cheap, fast, no infra) and the fewest in Framework (slow, needs infra) — if it's inverted, that's itself a smell that logic has leaked outward.

## Anti-pattern checklist (use during code review)

- **Anemic domain model** — entity classes are just getters/setters/fields with business rules implemented elsewhere (in a "service" or a controller). Fix: move the behavior onto the entity/aggregate/domain service that owns the data.
- **Framework annotations in Domain** — `@Entity`, `@Table`, `@Service`, `@Component`, `@Transactional`, `@JsonProperty`, Lombok is fine (pure code-gen, no runtime framework coupling) but JPA/Spring/Jakarta annotations are not.
- **Output ports shaped like `JpaRepository<T, ID>`** — leaks persistence technology into what should be a technology-agnostic contract; also leaks unwanted inherited methods.
- **Business logic in the controller/input adapter** — validation beyond basic input shape, conditionals that encode business rules, or direct manipulation of domain state inside a `@RestController`/`@Path` class. Fix: push it down into a use case / domain entity.
- **Fat / god service class** — one `XyzService` doing persistence, business rules, and orchestration all at once (classic layered-architecture smell). Fix: split into domain service (rules) + input port (orchestration) + output adapter (persistence).
- **Input port containing `if` statements that encode business constraints** — that's domain logic hiding in the application hexagon.
- **Domain module with a compile dependency on a web/persistence framework** — even one leftover import is a crack that grows over time. Enforce via build module boundaries, not just review vigilance.
- **Primitive obsession** — raw `String`/`long`/`boolean` standing in for a concept that has its own rules (an ID, a status, a measurement). Fix: wrap in a value object.
- **Too many output adapter categories for the same port** (e.g., DB + legacy system both implementing the same output port during an unfinished migration) — each needs its own translation mechanism; flag as a maintenance cost, plan to retire one.
- **Skipping the Domain hexagon and starting from the database schema** — if entity classes mirror database tables 1:1 with no behavior, the "domain model" is actually a persistence model wearing a costume.

## SOLID quick-mapping

| Principle | Where it shows up in this architecture |
|---|---|
| SRP | Each hexagon has one reason to change: Domain changes for business-rule changes, Framework changes for technology changes. A class serving two stakeholders (e.g., a use case coupled to a specific UI's needs) violates it. |
| OCP | New router type → new subclass (`CoreRouter`, `EdgeRouter` extending `Router`), not a modified `if/else` chain in an existing class. New protocol → new input adapter, not a modified existing one. |
| LSP | Any concrete entity subtype (`CoreRouter`, `EdgeRouter`) must work anywhere the parent type (`Router`) is expected — a method accepting `Router` shouldn't need to know which subtype it got. |
| ISP | Use case interfaces should expose exactly the operations a client needs — split by actor/goal rather than one giant interface with unused methods. |
| DIP | Input adapters and input ports both depend on the use case **interface**; output-port consumers depend on the **interface**, never on a concrete adapter. This is the architectural spine of the whole pattern. |

## Domain-Driven Design techniques worth borrowing

- **Ubiquitous language** — name entities, value objects, and use cases after terms the business actually uses; if you need a glossary to explain a class name to a domain expert, rename it.
- **Subdomains** — group supporting/generic capabilities (e.g., "equipment status monitoring") separately from the core domain when they're not the reason the business makes money, even if they're technically necessary.
- **Bounded contexts** — when the same term means different things in different parts of the system (or the same domain element serves clearly different concerns), split it into separate bounded contexts rather than forcing one ambiguous shared model.
- Do knowledge-crunching with domain experts before modeling — reading code (or a ticket) is not a substitute for understanding *why* a rule exists.

## Refactoring a layered app into hexagonal (checklist order)

1. Identify entities and pull business rules out of the existing "service layer" into entity/aggregate/domain-service classes with zero dependencies — this becomes the Domain hexagon.
2. Define use case interfaces describing the same behaviors the old service layer exposed, plus output port interfaces for whatever the old service layer got from repositories/DAOs.
3. Implement input ports (old service-layer orchestration logic minus the business rules, which now live in step 1) and re-point them at the output ports instead of the concrete repositories.
4. Move the old controller (API layer) to become an input adapter calling the use case interface; move the old repository/DAO to become an output adapter implementing the output port.
5. Re-run/rewrite tests at each layer per the testing table above — expect the Domain-hexagon tests to get dramatically faster since they no longer need a database.

Layered architecture is not wrong — it's simpler and faster to start. Only pay the hexagonal complexity cost when the coupling it removes (business logic to particular tech) is a coupling you actually expect to regret later.
