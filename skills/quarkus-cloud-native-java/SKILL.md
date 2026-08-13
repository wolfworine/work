---
name: quarkus-cloud-native-java
description: Use whenever writing or reviewing Quarkus code — CDI beans and scopes (@ApplicationScoped/@RequestScoped/@Singleton), Panache entities/repositories, JAX-RS/RESTEasy Reactive endpoints, application.properties and %dev/%test/%prod profiles, @QuarkusTest/@QuarkusIntegrationTest. Also use when deciding JVM vs native-image (GraalVM/Mandrel) packaging, containerizing a Quarkus service, wiring SmallRye Reactive Messaging (Kafka), choosing a Quarkus extension, or migrating/comparing a Spring Boot service to Quarkus.
---

# Quarkus: Cloud-Native Java

Operational reference for Quarkus, distilled from *Quarkus for Spring Developers* (Red Hat), *Understanding Quarkus* and *Practising Quarkus* (agoncal fascicles), *Quarkus Cookbook* (O'Reilly), *Hands-On Cloud-Native Applications with Java and Quarkus* (Packt), and *Quarkus in Action* (Manning MEAP). Complements, doesn't replace, the generic Java skills already in this library: for hexagonal package structure use [`java-hexagonal-architecture`](../java-hexagonal-architecture/SKILL.md), for records/streams/virtual threads use [`modern-java-language-features`](../modern-java-language-features/SKILL.md), for Kafka delivery semantics use [`kafka-event-driven-java`](../kafka-event-driven-java/SKILL.md), for Kubernetes manifests/probes use [`kubernetes-for-developers`](../kubernetes-for-developers/SKILL.md), for Dockerfile/JVM-container memory basics use [`java-devops-toolchain`](../java-devops-toolchain/SKILL.md). This skill covers what's specific to Quarkus itself. Concrete copy-paste snippets live in `references/recipes.md`.

## Core mental model: build-time augmentation

Quarkus's central trick is doing at **build time** what most Java frameworks do at **runtime**: classpath scanning, bean discovery, proxy generation, annotation processing (via Jandex, an annotation indexer) and bytecode generation (via Gizmo) all happen once, when the application is compiled — not on every JVM boot. This is why a Quarkus app starts in milliseconds instead of seconds, and why it can be reduced to a GraalVM native image at all (native image generation needs a mostly-closed, statically-analyzable world; deferring all that reflection/scanning to build time is what makes the app "AOT-friendly").

Practical consequence for how you write code: things that are fixed once you decide them (which JDBC driver, whether caching is enabled, which extensions are active) are **build-time** properties; things that legitimately change per environment (a DB URL, a feature flag value) are **runtime** properties. Quarkus's own config system enforces this distinction (see Configuration below) — you'll hit a build failure, not a runtime surprise, if you try to change a build-time property outside of build.

## Dev mode and live reload

```bash
./mvnw quarkus:dev        # or ./gradlew quarkusDev
```

Dev mode watches your source tree; on the next incoming HTTP request after a file changes, it recompiles just the changed classes and redeploys in place before serving that request — there is no full JVM restart. This extends to resources: editing `import.sql` or `application.properties` while `quarkus:dev` is running is picked up immediately, schema and data included, with no restart. It also launches the **Dev UI** (`/q/dev` by default) — a live console for browsing active extensions, endpoints, config, and Dev Services (see below); conceptually similar to a Spring Boot Actuator page, but Dev UI is available only in dev mode, whereas an Actuator endpoint can (and typically does) stay enabled in production.

**Dev Services**: in dev/test mode, Quarkus auto-provisions backing infrastructure (a Postgres/MySQL container, a Kafka broker, etc. via Testcontainers under the hood) with zero configuration the moment an extension that needs it is on the classpath — no `docker-compose up` step, no manual connection string. This is a genuine Quarkus-specific convenience with no direct Spring Boot equivalent; Spring Boot integration tests instead wire Testcontainers explicitly per test class.

## CDI: beans, scopes, qualifiers, events

CDI (Contexts and Dependency Injection) is Quarkus's core DI mechanism; most extensions assume it. Beans are auto-discovered — no XML, usually no explicit `@Named` needed.

**Scope decision table:**

| Scope | Lifetime | Use for |
|---|---|---|
| `@ApplicationScoped` | Entire application lifetime; one instance, created lazily on first use, destroyed at shutdown | Stateless services, utility/helper beans — the default choice for a `@Service`-equivalent class |
| `@Singleton` | Instantiated once, but **eagerly, with no client proxy** (unlike `@ApplicationScoped`, which is proxied for laziness) | Same use case as `@ApplicationScoped` but when you specifically want eager init or need to avoid proxy semantics (e.g. injecting into a static context, or the class is `final`) |
| `@RequestScoped` | One instance per HTTP request/method invocation, discarded when it ends | Per-request state that shouldn't leak across requests (an in-flight command object, request-scoped auth context) |
| `@SessionScoped` | Bound to an HTTP session | User preferences/session state — rare in typical stateless REST microservices, more relevant to server-rendered apps |
| `@Dependent` | New instance every time it's injected, tied to its injection point's lifecycle | CDI's default scope when none is specified — usually you want to be explicit instead |

Scopes compose freely — a `@SessionScoped` bean can be injected into an `@ApplicationScoped` one and vice versa.

**Injection and qualifiers** — when more than one implementation of an interface exists, use a qualifier to disambiguate instead of `@Named` string matching:

```java
public interface NumberGenerator {
    String generateNumber();
}

@ThirteenDigits @ApplicationScoped
public class IsbnGenerator implements NumberGenerator { ... }

@EightDigits @ApplicationScoped
public class IssnGenerator implements NumberGenerator { ... }

@ApplicationScoped
public class BookService {
    @Inject @ThirteenDigits
    NumberGenerator numberGenerator;   // unambiguous injection point
}
```

Use `@Alternative` (+ enabling it in `application.properties`) for swap-in test/mock beans (e.g. a `MockGenerator`) without touching production wiring.

**Events** — CDI events are Quarkus's built-in in-process pub/sub, and are **synchronous by default** (the firing thread runs every observer inline; use `fireAsync` for async dispatch):

```java
@ApplicationScoped
public class BookService {
    @Inject Event<Book> bookCreatedEvent;
    public void createBook(Book b) {
        // ... persist ...
        bookCreatedEvent.fire(b);
    }
}

@Singleton
public class InventoryService {
    public void onBookCreated(@Observes Book book) { inventory.add(book); }
}
```

Prefer CDI events for **in-process** decoupling (e.g. "notify inventory when a book is created" without `BookService` importing `InventoryService`). For cross-service/durable decoupling, that's a job for SmallRye Reactive Messaging / Kafka, not CDI events — see below.

## Configuration: application.properties and profiles

Single unified config source — `src/main/resources/application.properties` (or `.yaml`) — read via `@ConfigProperty` injection or type-safe `@ConfigMapping` interfaces:

```properties
greeting.message=Hello
greeting.suffix=!
%dev.greeting.message=Hello (dev)
%test.quarkus.datasource.jdbc.url=jdbc:h2:mem:test
```

```java
@ConfigProperty(name = "greeting.message")
String message;   // required — build/startup fails fast if missing and no default is given
```

**Profiles** (`%dev`, `%test`, `%prod`, or custom names) prefix any property to override it per-environment in the *same file* — no separate `application-dev.properties` unless you prefer that split. `%test` is active automatically under `@QuarkusTest`; `%dev` under `quarkus:dev`; `%prod` is the default when neither applies.

**Type-safe config groups** (equivalent to Spring's `@ConfigurationProperties`), via an interface rather than a class — Quarkus generates the implementation at build time, no getters/setters to hand-write:

```java
@ConfigMapping(prefix = "greeting")
public interface GreetingConfig {
    String message();
    @WithDefault("!")
    String suffix();
    Content content();
    interface Content {
        int prizeAmount();
        List<String> recipients();
    }
}
```

Where Quarkus and Spring genuinely differ: Spring's `@ConfigurationProperties` treats every field as optional unless you separately add Bean Validation (`@Validated` + `spring-boot-starter-validation`); a Quarkus `@ConfigMapping` interface method is **required by default** — startup fails immediately with a clear error if a non-defaulted property is missing, rather than silently leaving a field `null` until something NPEs later.

## REST endpoints: JAX-RS vs RESTEasy Reactive

Quarkus supports both the classic blocking `quarkus-resteasy` (JAX-RS) extension and `quarkus-resteasy-reactive`, built on Vert.x, non-blocking by default. New projects should default to RESTEasy Reactive — it subsumes the classic feature set and is what `code.quarkus.io` scaffolds by default for new REST projects.

```java
@Path("/fruits")
public class FruitResource {
    @Inject FruitRepository repository;

    @GET
    public List<Fruit> list() { return repository.listAll(); }

    @GET @Path("/{id}")
    public Fruit get(@PathParam("id") Long id) {
        return repository.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @POST
    @Transactional
    public Response create(Fruit fruit) {
        repository.persist(fruit);
        return Response.status(Response.Status.CREATED).entity(fruit).build();
    }
}
```

A method can return a plain object (RESTEasy Reactive serializes it synchronously) **or** a reactive type (`Uni<T>`/`Multi<T>` from Mutiny, Quarkus's own reactive library — distinct from Project Reactor's `Mono`/`Flux` used in Spring WebFlux, though the concepts map directly: `Uni` ≈ `Mono`, `Multi` ≈ `Flux`). Returning `Uni`/`Multi` from an endpoint lets the request be processed non-blockingly on the Vert.x event loop instead of a worker thread — same "never block the event loop" rule that applies to Reactor (see `reactive-java-webflux`), just with Mutiny's operator names (`onItem().transform(...)` instead of `map`, `onFailure().recoverWithItem(...)` instead of `onErrorResume`).

## Persistence: Panache — active record vs repository

Panache (`quarkus-hibernate-orm-panache`) sits on top of standard JPA/Hibernate ORM and eliminates JPA boilerplate: no hand-written getters/setters (public fields, Panache rewrites field access to use generated accessors under the hood), an auto-generated ID, and drastically simplified queries.

**Decision table:**

| Pattern | Shape | Use when |
|---|---|---|
| **Active record** (`extends PanacheEntity`) | Entity class carries both data (public fields) *and* behavior (static query methods) | Default choice — less code, queries co-located with the entity they operate on, easy to find |
| **Repository** (`implements PanacheRepository<T>`) | Entity holds only state/mapping; a separate `@ApplicationScoped` class holds all query/persistence methods | You have legacy plain-JPA entities you can't change to extend `PanacheEntity`; or you want a clean separation between persistence and domain state (e.g. to keep entities framework-agnostic per [`java-hexagonal-architecture`](../java-hexagonal-architecture/SKILL.md) — active record couples the domain class directly to Hibernate, which a hexagonal Domain hexagon should avoid) |

Both expose the exact same convenience methods (`persist`, `listAll`, `findById`, `count`, `delete`, simplified JPQL) — pick based on the domain-coupling trade-off, not capability.

```java
// Active record
@Entity
public class Publisher extends PanacheEntity {
    public String name;
    public static Optional<Publisher> findByName(String name) {
        return find("name", name).firstResultOptional();
    }
}
Publisher.findByName("O'Reilly");           // static call on the entity itself

// Repository
@ApplicationScoped
public class PublisherRepository implements PanacheRepository<Publisher> {
    public Optional<Publisher> findByName(String name) {
        return find("name", name).firstResultOptional();
    }
}
@Inject PublisherRepository repo;
repo.findByName("O'Reilly");                // same query, injected repository call
```

Simplified queries take just the JPQL fragment after `WHERE` — no `SELECT`/`FROM` boilerplate for the common case:

```java
Book.list("nbOfPage > 100 ORDER BY title");                       // full simplified form
Book.list("unitCost between ?1 and ?2", min, max);                 // positional params
Book.list("unitCost between :min and :max", Parameters.with("min", min).and("max", max));  // named params
PanacheQuery<Book> q = Book.find("language", Language.ENGLISH);
q.page(Page.ofSize(20)).list();                                    // paging
Author.listAll(Sort.by("firstName").and("lastName", Descending));  // sorting
```

Extend `PanacheEntityBase` instead of `PanacheEntity` when you need a custom (non-auto-generated) ID strategy. `quarkus-hibernate-reactive-panache` is the non-blocking counterpart (returns `Uni<T>`/`Multi<T>`) for services built end-to-end on RESTEasy Reactive/Mutiny — don't mix blocking Panache calls into a reactive endpoint any more than you'd `.block()` inside a Reactor chain (see `reactive-java-webflux`'s cardinal rule; the same principle applies here, just with different types).

## JVM mode vs native image (GraalVM/Mandrel) — the defining trade-off

This is the single most consequential packaging decision in a Quarkus project, and it's per-deployment, not per-codebase — the same source compiles to either target.

GraalVM's native-image tool performs **ahead-of-time (AOT) compilation**: static analysis finds every class reachable from `main()`, then compiles the whole closed world (app classes + dependencies + a minimal JDK runtime) into one standalone native executable — no JVM installed on the target host at all, like a compiled Go binary. Quarkus's build-time augmentation (see Core mental model) is precisely what makes this tractable for a full-featured framework: reflection, dynamic proxies, and classpath scanning are resolved and "baked in" at build time instead of needing to work at native-image analysis time.

Red Hat's **Mandrel** is a downstream, OpenJDK-based distribution of the GraalVM native-image generator, purpose-built to track upstream GraalVM closely while optimizing specifically for building Quarkus native images — use it (via the `quay.io/quarkus/ubi-quarkus-native-image` container images) instead of full GraalVM if you only need native-image generation and not GraalVM's polyglot features.

| | JVM mode | Native image |
|---|---|---|
| Startup time | ~1-2s typical for a Quarkus app (already fast vs. traditional Java frameworks) | Tens of **milliseconds** |
| Memory (RSS) | Standard JVM footprint | A fraction of JVM RSS — the headline win for high-density container packing |
| Build time | Fast (normal `mvn package`) | Much slower — full AOT compilation, can be minutes per build |
| Peak throughput (long-running) | Higher — JIT keeps optimizing hot paths over the process lifetime | Lower peak — no JIT warm-up curve, so it never reaches the JIT's best-case throughput |
| Reflection/dynamic class loading | Works normally | Needs explicit `reflection-config.json` hints (or a Quarkus extension that already provides them) for anything not statically discoverable — arbitrary runtime reflection (some serialization libraries, certain ORM proxy tricks) can break silently at native build or runtime unless registered |
| Debuggability | Standard JVM tooling (debugger, profiler, familiar stack traces) | More limited; debugging a native binary is less mature than debugging a JVM process |
| Best fit | Long-running services where sustained throughput matters more than boot time; local dev always uses JVM mode via `quarkus:dev` regardless of the prod target | Scale-to-zero/serverless, CLI tools, short-lived batch jobs, high pod-density Kubernetes deployments where fast cold start and low memory directly cut infrastructure cost |

Building one:

```bash
./mvnw package -Pnative -Dquarkus.native.container-build=true   # builds inside a container — no local GraalVM install needed
```

`-Dquarkus.native.container-build=true` is the pragmatic default for CI and for developers who don't want to install GraalVM/Mandrel locally — it pulls the Mandrel builder image and runs the AOT compilation inside it, producing a Linux native binary regardless of the host OS (important if you develop on macOS/Windows but deploy to Linux containers). Extension authors are responsible for providing the native-image reflection metadata their extension needs; when using officially-supported Quarkus extensions this "just works" — problems arise mostly with arbitrary third-party libraries not designed with native-image in mind.

**Don't default to native for everything** — the build-time cost and reduced debuggability are real, and JVM mode already gives Quarkus most of its startup-time advantage over a traditional Spring Boot app. Reach for native image specifically when fast cold-start or minimal memory footprint is the deciding constraint (serverless/FaaS, autoscaling to zero, very high pod density), not as a default "because Quarkus can."

## Testing

```java
@QuarkusTest
class GreetingResourceTest {
    @InjectMock
    GreetingService greetingService;   // Mockito mock injected into the CDI container

    @Test
    void testHelloEndpoint() {
        Mockito.when(greetingService.greet()).thenReturn("mocked");
        given().when().get("/hello").then().statusCode(200).body(is("mocked"));
    }
}
```

- `@QuarkusTest` starts the application **once and reuses it across every `@QuarkusTest`-annotated class in the run** — contrast with Spring's `@SpringBootTest`, which (without extra caching config) restarts the application context per test class. This makes Quarkus test suites noticeably faster at scale, but also means test classes are not isolated from each other's CDI container state by default — be deliberate about mutable shared state (a Panache-backed DB, a static field) the same way you would with any shared-fixture test suite.
- `@InjectMock` is Quarkus's equivalent of Spring's `@MockBean` — swaps a CDI bean for a Mockito mock for the duration of the test.
- `@QuarkusIntegrationTest` runs against the **packaged artifact** (the built JAR or native executable) rather than an in-process test instance — use it to validate the actual thing you're shipping, especially to catch native-image-only failures that `@QuarkusTest` (JVM-mode, in-process) can't see.
- **Dev Services in tests**: same zero-config container provisioning as dev mode — a `@QuarkusTest` needing a real Postgres gets one automatically via Testcontainers under the hood, no `@Testcontainers`/`@Container` boilerplate required, unlike a typical Spring Boot + Testcontainers setup where the container lifecycle is wired explicitly per test class.
- REST-assured (`given().when().get(...)`) is the idiomatic way to test JAX-RS/RESTEasy Reactive endpoints end-to-end within `@QuarkusTest` — analogous to Spring's `MockMvc`/`WebTestClient`.

## Reactive messaging (SmallRye Reactive Messaging)

Channel-based, not topic-based, in application code — you connect a Java method to a **channel name**; a connector (Kafka, AMQP, MQTT, etc., configured in `application.properties`) binds that channel to the actual broker/topic.

```java
@Incoming("prices-in")                 // consumes from the "prices-in" channel
public void consume(String price) { ... }

@Outgoing("prices-out")                // publishes to the "prices-out" channel
public Multi<String> produce() { ... }

@Incoming("from") @Outgoing("to")      // processor: consumes "from", republishes to "to"
public String translate(String text) { return translate(text); }
```

```properties
mp.messaging.incoming.prices-in.connector=smallrye-kafka
mp.messaging.incoming.prices-in.topic=prices
mp.messaging.incoming.prices-in.bootstrap.servers=localhost:9092
```

A channel may have **at most one** `@Outgoing`-annotated producer method — attempting a second on the same channel is a deployment-time error, not a runtime surprise. For everything about delivery guarantees, partition keys, consumer lag, and idempotent-consumer design once you're talking to real Kafka, defer to [`kafka-event-driven-java`](../kafka-event-driven-java/SKILL.md) — this section only covers the Quarkus-specific wiring layer on top.

## Fault tolerance (MicroProfile Fault Tolerance)

`quarkus-smallrye-fault-tolerance` adds four composable annotations for resilient service-to-service calls (works on any CDI bean method, including a MicroProfile REST Client interface — see `references/recipes.md`):

| Annotation | What it does | Key params |
|---|---|---|
| `@Retry` | Re-invokes the method automatically if it throws | `maxRetries`, `delay` |
| `@Timeout` | Aborts and throws `TimeoutException` if the call runs too long | `value` (ms) |
| `@Bulkhead` | Caps concurrent executions; excess synchronous calls throw `BulkheadException` immediately instead of queuing | the concurrency limit, e.g. `@Bulkhead(2)` |
| `@CircuitBreaker` | Trips open after a failure ratio within a rolling request window, short-circuiting further calls with `CircuitBreakerOpenException` until a `delay` elapses and it half-opens to test recovery | `requestVolumeThreshold`, `failureRatio`, `delay` |
| `@Fallback` | Supplies a recovery value/`FallbackHandler` instead of propagating the failure — combine with any of the above | `value = SomeFallbackHandler.class` |

```java
@Retry(maxRetries = 3, delay = 1000)
@Fallback(RecoverHelloMessageFallback.class)
public String getHelloWithFallback() {
    return externalService.call();
}

public static class RecoverHelloMessageFallback implements FallbackHandler<String> {
    @Override
    public String handle(ExecutionContext ctx) { return "fallback response"; }
}

@CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.75, delay = 2000)
public String getHelloCircuitBreaker() { return externalService.call(); }
```

These compose (`@CircuitBreaker` + `@Retry` + `@Fallback` on the same method is a normal combination) and can be overridden per-method/per-class/globally from `application.properties` without touching code — `fully.qualified.Class/methodName/Retry/maxRetries=5`. This is Quarkus's declarative-annotation implementation of the same resilience patterns [`enterprise-java-design-patterns`](../enterprise-java-design-patterns/SKILL.md) covers conceptually (Circuit Breaker/Bulkhead) — reach for that skill for the *why*/*when* of each pattern, this section for the Quarkus-specific *how*.

## Extensions: Quarkus's equivalent of Spring Boot Starters — and the key difference

A Quarkus **extension** looks like a Spring Boot Starter from the outside (add one Maven/Gradle dependency, get auto-configuration + libraries for a whole feature area) but is structured differently under the hood: every extension has a **deployment module** (build-time augmentation logic — bean registration, native-image reflection hints, config validation) and a **runtime module** (the actual runtime classes/API you code against). This split is what lets Quarkus front-load work to build time instead of paying for classpath scanning on every boot.

```bash
./mvnw quarkus:list-extensions          # see everything available
./mvnw quarkus:add-extension -Dextensions="resteasy-reactive,hibernate-orm-panache,jdbc-postgresql"
```

Common extensions and their rough Spring Boot Starter equivalent (400+ extensions exist; browse `code.quarkus.io` for the full catalog):

| Quarkus extension | Rough Spring Boot equivalent |
|---|---|
| `quarkus-resteasy-reactive` (+ `-jackson`) | `spring-boot-starter-web` / `-webflux` |
| `quarkus-hibernate-orm-panache` | `spring-boot-starter-data-jpa` |
| `quarkus-hibernate-reactive-panache` | `spring-boot-starter-data-r2dbc` |
| `quarkus-smallrye-reactive-messaging-kafka` | `spring-cloud-stream-binder-kafka` |
| `quarkus-smallrye-health` | `spring-boot-starter-actuator` (health subset) |
| `quarkus-smallrye-openapi` | `springdoc-openapi` (no first-party Spring Boot Starter for OpenAPI) |
| `quarkus-oidc` | `spring-boot-starter-oauth2-resource-server` |
| `quarkus-container-image-jib` / `-docker` | `spring-boot-maven-plugin`'s `build-image` goal |

If you're migrating an existing Spring Boot app incrementally rather than rewriting, the `quarkus-spring-boot-properties`, `quarkus-spring-cache`, `quarkus-spring-di`, `quarkus-spring-web`, and `quarkus-spring-data-jpa` extensions let Quarkus understand a subset of Spring annotations directly (`@Autowired`, `@RestController`, `@ConfigurationProperties`, etc.) as a bridge — useful for a phased migration, not intended as the end state of a Quarkus-native codebase.

## Packaging for containers

Three build strategies, from most to least automated (cross-reference [`java-devops-toolchain`](../java-devops-toolchain/SKILL.md) for the general multi-stage-Dockerfile/JVM-memory-in-container guidance that still applies underneath):

| Strategy | How it works | Notes |
|---|---|---|
| `quarkus-container-image-jib` | Builds the image directly from build tooling (Maven/Gradle plugin), no Docker daemon needed, no handwritten Dockerfile | Fastest inner loop; good default for CI without Docker-in-Docker |
| `quarkus-container-image-docker` | Uses a Quarkus-generated (or your own) Dockerfile + local Docker daemon | Most control, closest to the general Java container guidance in `java-devops-toolchain` |
| Buildpacks | Cloud-Native Buildpacks build the image without any Dockerfile | Less commonly used with Quarkus than JIB/Docker; more mature on the Spring Boot side (`spring-boot:build-image`) |

```bash
./mvnw package -Dquarkus.container-image.build=true \
  -Dquarkus.container-image.image=registry.example.com/my-service:1.0
```

The resulting image differs sharply by target: a JVM-mode image needs a JRE base layer (same guidance as `java-devops-toolchain`'s multi-stage pattern); a native-image build can ship on a `distroless`/`scratch`-style base with **no JVM at all**, since the binary is self-contained — this is where native image's container-size and density advantage really shows up, beyond just startup time.

## Migrating from Spring Boot — quick-reference

**Context for the table below**: Spring Boot is not a separate framework from Spring Framework — it's an auto-configuration layer on top of it. Spring Framework itself provides the actual mechanics (the IoC container/`ApplicationContext`, bean lifecycle management, transaction management, Spring MVC, security integrations, data access abstraction); Spring Boot's job is auto-configuring all of that from starter dependencies and classpath scanning so you don't hand-wire beans in XML or Java config classes. This matters for the comparison because it's the same shape of trade-off Quarkus makes with CDI + build-time augmentation: both frameworks moved "assemble the container from what's on the classpath" from a manual step to an automatic one — Spring Boot does it via runtime auto-configuration, Quarkus does it via build-time bean discovery (see Core mental model above). A developer who only knows Spring Boot's annotations without understanding what `ApplicationContext` actually is will hit the same kind of "magic" confusion moving to Quarkus's CDI container — understanding the underlying container model (whichever one) pays off in both directions.

| Concept | Spring Boot | Quarkus |
|---|---|---|
| DI annotation | `@Component`/`@Service`/`@Repository` + `@Autowired` | CDI `@ApplicationScoped`/`@Singleton` + `@Inject` |
| REST controller | `@RestController` + `@RequestMapping` | `@Path` (JAX-RS) — no separate "controller" stereotype needed |
| Config properties class | `@ConfigurationProperties` (fields optional unless `@Validated`) | `@ConfigMapping` interface (methods **required** by default) |
| Test bootstrap | `@SpringBootTest` (new context per test class by default) | `@QuarkusTest` (app started once, reused across the whole run) |
| Mocking a bean in tests | `@MockBean` | `@InjectMock` |
| Global exception handling | `@RestControllerAdvice` + `@ExceptionHandler` | `ExceptionMapper<T>` (JAX-RS), or handle inline — no advice annotation needed |
| Reactive stack | Spring WebFlux + Project Reactor (`Mono`/`Flux`) | RESTEasy Reactive + Mutiny (`Uni`/`Multi`) |
| Event-driven messaging | Spring Cloud Stream | SmallRye Reactive Messaging (`@Incoming`/`@Outgoing`) |
| Actuator-style health/ops | Spring Boot Actuator | `quarkus-smallrye-health` (`/q/health`) + Dev UI (dev-mode only) |
| Container image build | `spring-boot-maven-plugin` `build-image` (Buildpacks) | `quarkus-container-image-jib`/`-docker` |
| Test infra bootstrap | Testcontainers wired explicitly per test | Dev Services — automatic, zero-config |
| No-args bootstrap class | Required (`@SpringBootApplication` + `main()`) | Not required — no `Application` class needed at all |

## Pitfalls checklist (review heuristics)

- **Runtime property set on a build-time-only config key** — fails the build with a clear error; if you hit this, the fix is to make the value environment-agnostic at build time or restructure so it's genuinely a runtime concern (check the extension's docs for which of its properties are build-time vs runtime — this isn't always obvious from the property name alone).
- **Reaching for native image "because Quarkus supports it"** on a long-running service where sustained JIT-optimized throughput matters more than cold-start time — JVM mode is still the right default for most always-on services; see the trade-off table above.
- **Mixing blocking Panache/JDBC calls into a RESTEasy Reactive endpoint that returns `Uni`/`Multi`** — the same event-loop-blocking hazard as `.block()` inside a Reactor chain (see `reactive-java-webflux`), just with Mutiny types. Use `quarkus-hibernate-reactive-panache` for a genuinely non-blocking persistence path, or explicitly offload the blocking call to a worker thread.
- **A second `@Outgoing` method on a channel that already has one** — deployment-time error, not a subtle bug; if you need to fan a single source out to multiple consumers, that's what multiple `@Incoming` subscribers on the same channel are for, not multiple producers.
- **Assuming `@QuarkusTest` isolates state between test classes** the way a fresh Spring context per `@SpringBootTest` class would — it reuses the same running app across the suite for speed; design tests (especially Panache-backed ones) to be resilient to shared state, or use transactional test rollback patterns.
- **Hand-writing getters/setters on a Panache active-record entity** — defeats the point; use public fields and let Panache generate accessors, reserve manual getters/setters only for fields needing custom logic on access.
- **Choosing active-record Panache entities inside a Domain hexagon** (per `java-hexagonal-architecture`) — active record couples the entity directly to `PanacheEntity`/Hibernate, which is exactly the kind of framework leakage that architecture is designed to prevent; use the Panache **repository** pattern (or plain JPA output adapters) when hexagonal purity matters more than terseness.
- **Stacking `@CircuitBreaker`/`@Retry`/`@Bulkhead` without reading how they interact** — e.g. `@Retry` on top of `@CircuitBreaker` means each retry attempt is itself recorded as a success/failure against the circuit breaker's rolling window, which can trip the breaker faster than expected under sustained failure; know the composition rules (see Fault tolerance above) before combining more than one.

## Honesty note on sourcing

CDI scopes/qualifiers/events, Panache (active record and repository, queries/paging/sorting), the Spring↔Quarkus comparison tables (config, testing, extensions, container build strategies), GraalVM/Mandrel architecture, dev mode/Dev Services, the reactive-messaging `@Incoming`/`@Outgoing` mechanics, and the MicroProfile Fault Tolerance annotations (§Fault tolerance, plus the retry/timeout/bulkhead/circuit-breaker/caching/scheduling/REST-client/JWT/Flyway recipes in `references/recipes.md`) are all drawn directly from the source books with concrete listing/table/recipe references — the fault-tolerance and recipes.md additions came from a full pass over *Quarkus Cookbook* (chapters 8, 11, 13, and 16.9–16.10) and *Quarkus in Action*, which the first version of this skill had only TOC-checked, not read. The JVM-vs-native trade-off table's qualitative directions (startup, memory, build time, debuggability) come from the sources; the specific throughput/startup magnitudes given are typical industry figures, not measurements reproduced from a single book benchmark — validate against your own workload before trusting them for a capacity-planning decision. The "don't default to native for everything" guidance and the Mutiny↔Reactor conceptual mapping are standard current Quarkus practice layered on top of source material that doesn't itself make that recommendation explicitly. The JWT recipe's security config (Elytron JDBC identity provider) is from the cookbook's testing/example setup, not a production-hardened reference — treat the schema/query shown as illustrative, not a security review.
