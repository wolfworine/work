# Quarkus recipes

Companion to `../SKILL.md`. Copy-paste-ready snippets for common Quarkus tasks, drawn mainly from *Quarkus Cookbook* (O'Reilly), *Quarkus for Spring Developers* (Red Hat), and the agoncal fascicles. Load this file when the concise SKILL.md isn't enough — this is the "how exactly do I write this" layer.

## Scaffolding a new project

```bash
mvn io.quarkus.platform:quarkus-maven-plugin:create \
  -DprojectGroupId=com.example \
  -DprojectArtifactId=my-service \
  -Dextensions="resteasy-reactive-jackson,hibernate-orm-panache,jdbc-postgresql,smallrye-health"
```
Or via the web UI at `code.quarkus.io` (pick extensions, download a zip) — equivalent to `start.spring.io` for Spring Boot. Unlike a Spring Boot project, there's no generated `Application`/`main()` class — Quarkus provides its own bootstrap.

## Basic REST resource + Panache repository (blocking)

```java
@Entity
public class Fruit extends PanacheEntity {
    public String name;
    public String color;
}

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

    @GET
    public List<Fruit> list() {
        return Fruit.listAll(Sort.by("name"));
    }

    @GET
    @Path("/{id}")
    public Fruit get(@PathParam("id") Long id) {
        return Fruit.<Fruit>findByIdOptional(id)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    @POST
    @Transactional
    public Response create(Fruit fruit) {
        fruit.persist();
        return Response.status(Response.Status.CREATED).entity(fruit).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = Fruit.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
```

`@Transactional` on write methods is required for Panache persist/delete — Quarkus does not wrap every REST method in a transaction implicitly the way some Spring configurations do.

## Reactive REST resource (Mutiny + Hibernate Reactive Panache)

```java
@Entity
public class Fruit extends PanacheEntity {
    public String name;
}

@Path("/fruits")
public class FruitResource {
    @GET
    public Uni<List<Fruit>> list() {
        return Fruit.listAll();
    }

    @GET @Path("/{id}")
    public Uni<Fruit> get(@PathParam("id") Long id) {
        return Fruit.<Fruit>findById(id)
                .onItem().ifNull().failWith(() -> new WebApplicationException(404));
    }

    @POST
    public Uni<Response> create(Fruit fruit) {
        return Panache.withTransaction(fruit::persist)
                .replaceWith(Response.status(201).entity(fruit).build());
    }
}
```
Requires `quarkus-hibernate-reactive-panache` + a reactive driver (`quarkus-reactive-pg-client` for Postgres) instead of the blocking `quarkus-jdbc-postgresql`. Never mix the blocking and reactive Panache extensions for the same entity.

## Config: type-safe mapping + profiles

`application.properties`:
```properties
greeting.message=Hello
greeting.suffix=!
%dev.quarkus.log.category."com.example".level=DEBUG
%test.quarkus.datasource.jdbc.url=jdbc:h2:mem:test;DB_CLOSE_DELAY=-1
quarkus.datasource.jdbc.min-size=5
quarkus.datasource.jdbc.max-size=20
```

```java
@ConfigMapping(prefix = "greeting")
public interface GreetingConfig {
    String message();
    @WithDefault("!")
    String suffix();
}

@Path("/greeting")
public class GreetingResource {
    @Inject GreetingConfig config;

    @GET
    public String greet() { return config.message() + config.suffix(); }
}
```

## Testing with @QuarkusTest + mocked bean

```java
@QuarkusTest
class GreetingResourceTest {

    @InjectMock
    GreetingService greetingService;

    @Test
    void greetsUsingMockedService() {
        Mockito.when(greetingService.greet()).thenReturn("mocked hello");

        given()
          .when().get("/greeting")
          .then()
             .statusCode(200)
             .body(is("mocked hello"));
    }
}
```

Integration test against the packaged artifact (JAR or native binary):
```java
@QuarkusIntegrationTest
class GreetingResourceIT extends GreetingResourceTest {
    // inherits the same test methods, runs them against the built artifact instead of in-process
}
```

## Health checks (SmallRye Health)

```java
@Readiness
@ApplicationScoped
public class DependencyReadinessCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        boolean dependencyUp = checkDownstreamDependency();
        return HealthCheckResponse.named("downstream-dependency")
                .status(dependencyUp)
                .build();
    }
}

@Liveness
@ApplicationScoped
public class MemoryHealthCheck implements HealthCheck {
    private static final long THRESHOLD_BYTES = 1_000_000_000L; // 1 GB
    @Override
    public HealthCheckResponse call() {
        long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        return HealthCheckResponse.named("memory")
                .status(used < THRESHOLD_BYTES)
                .withData("usedBytes", used)
                .build();
    }
}
```
Exposed at `/q/health`, `/q/health/live`, `/q/health/ready` once `quarkus-smallrye-health` is on the classpath — wire these directly into the Kubernetes `livenessProbe`/`readinessProbe` paths described in `kubernetes-for-developers`. Same rule applies here as there: put dependency checks (`@Readiness`) not process-vitals checks (`@Liveness`) — a downstream outage should pull the pod from rotation, not restart a healthy container.

## Reactive messaging: Kafka producer + consumer

`application.properties`:
```properties
mp.messaging.outgoing.prices-out.connector=smallrye-kafka
mp.messaging.outgoing.prices-out.topic=prices
mp.messaging.outgoing.prices-out.value.serializer=org.apache.kafka.common.serialization.StringSerializer

mp.messaging.incoming.prices-in.connector=smallrye-kafka
mp.messaging.incoming.prices-in.topic=prices
mp.messaging.incoming.prices-in.value.deserializer=org.apache.kafka.common.serialization.StringDeserializer
```

```java
@ApplicationScoped
public class PriceGenerator {
    @Outgoing("prices-out")
    public Multi<String> generate() {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(5))
                .map(tick -> String.valueOf(new Random().nextInt(100)));
    }
}

@ApplicationScoped
public class PriceConsumer {
    @Incoming("prices-in")
    public void consume(String price) {
        Log.infof("Received price: %s", price);
    }
}
```
In dev/test mode, Dev Services auto-starts a Kafka broker with zero config — no `docker-compose up` needed locally. See `kafka-event-driven-java` for delivery-guarantee/idempotence decisions once this talks to a real production cluster.

## Dockerfile: JVM mode vs native image (multi-stage)

**JVM mode** (extends the general pattern from `java-devops-toolchain` — same JRE-only, non-root, layer-ordering rules apply):
```dockerfile
FROM registry.access.redhat.com/ubi8/openjdk-21:latest AS build
WORKDIR /build
COPY --chown=185 . .
RUN ./mvnw -B package -DskipTests

FROM registry.access.redhat.com/ubi8/openjdk-21-runtime:latest
WORKDIR /work
COPY --from=build --chown=185 /build/target/quarkus-app/lib/ /work/lib/
COPY --from=build --chown=185 /build/target/quarkus-app/*.jar /work/
COPY --from=build --chown=185 /build/target/quarkus-app/app/ /work/app/
COPY --from=build --chown=185 /build/target/quarkus-app/quarkus/ /work/quarkus/
USER 185
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/work/quarkus-run.jar"]
```

**Native image** — no JVM in the final image at all, hence the much smaller distroless-style base:
```dockerfile
FROM quay.io/quarkus/ubi-quarkus-native-image:22.3-java17 AS build
WORKDIR /build
COPY --chown=quarkus:quarkus . .
USER quarkus
RUN ./mvnw -B package -Pnative

FROM quay.io/quarkus/quarkus-micro-image:2.0
WORKDIR /work
COPY --from=build /build/target/*-runner /work/application
RUN chmod 775 /work/application
EXPOSE 8080
ENTRYPOINT ["./application"]
```

Or skip the Dockerfile entirely:
```bash
./mvnw package -Dquarkus.container-image.build=true -Pnative \
  -Dquarkus.container-image.image=registry.example.com/my-service:1.0-native
```

## Extensions: discover and add

```bash
./mvnw quarkus:list-extensions                                  # browse everything available
./mvnw quarkus:add-extension -Dextensions="quarkus-oidc"         # add one by name
```

## Fault tolerance: retry, timeout, bulkhead, circuit breaker

```bash
./mvnw quarkus:add-extension -Dextensions="quarkus-smallrye-fault-tolerance"
```

```java
@Timeout(2000)                              // aborts after 2s, throws TimeoutException
public String getHelloWithTimeout() { return externalService.call(); }

@Bulkhead(2)                                 // at most 2 concurrent executions; excess throws BulkheadException
public String getHelloBulkhead() { return externalService.call(); }

@Retry(maxRetries = 3, delay = 1000)
@Fallback(RecoverHelloMessageFallback.class)
public String getHelloWithFallback() {
    return externalService.call();
}

public static class RecoverHelloMessageFallback implements FallbackHandler<String> {
    @Override
    public String handle(ExecutionContext ctx) { return "fallback response"; }
}
```

Override any parameter from config without touching code — method-level, class-level, or global, in that precedence order:
```properties
org.acme.ServiceInvoker/getHelloWithFallback/Retry/maxDuration=30
org.acme.ServiceInvoker/Retry/maxDuration=3000
Retry/maxDuration=3000
# disable entirely for a test/dev profile:
org.acme.ServiceInvoker/getHelloCircuitBreaker/CircuitBreaker/enabled=false
```

## Scheduled jobs

```bash
./mvnw quarkus:add-extension -Dextensions="quarkus-scheduler"
```

```java
@ApplicationScoped
public class ReportScheduler {

    @Scheduled(every = "5s")                          // Duration#parse syntax; a bare number implies seconds
    void everyFiveSeconds(ScheduledExecution execution) {
        Log.infof("Next fire: %s", execution.getTrigger().getNextFireTime());
    }

    @Scheduled(cron = "0 30 * * * ?")                  // Quartz cron syntax — note: NOT identical to standard cron
    void hourlyAtHalfPast() { generateReport(); }
}
```
`every` and `cron` both support a config-lookup form (`@Scheduled(cron = "{report.cron.expr}")`) to externalize the schedule instead of hardcoding it.

## Application data caching

```bash
./mvnw quarkus:add-extension -Dextensions="quarkus-cache"
```

```java
@CacheResult(cacheName = "greeting-cache")
public String getMessage(String userId) {   // args are the default cache key
    return expensiveLookup(userId);
}

@CacheResult(cacheName = "greeting-cache")
public String getMessage(@CacheKey String userId, String ignoredForKeyPurposes) {
    return expensiveLookup(userId);          // only @CacheKey-annotated args form the key when present
}
```
Backed by Caffeine (in-memory) by default. First call executes the method and populates the cache; subsequent calls with the same key return the cached value without invoking the method body at all — verify this changes your method's behavior expectations if it has side effects, since a cache hit skips the body entirely.

## Calling another service: MicroProfile REST Client

Type-safe alternative to building a raw JAX-RS `WebTarget` — define the remote API as an interface:

```java
@Path("/api")
@RegisterRestClient                          // marks this interface as an injectable REST client
public interface WorldClockService {
    @GET
    @Path("/json/{timezone}/now")
    @Produces(MediaType.APPLICATION_JSON)
    WorldClock getNow(@PathParam("timezone") String timezone);
}
```

```java
@RestClient                                   // inject the generated client implementation
WorldClockService worldClockService;

@GET
@Path("{timezone}/mp")
public WorldClock getCurrentTime(@PathParam("timezone") String timezone) {
    return worldClockService.getNow(timezone);
}
```

```properties
# fully-qualified-interface-name/mp-rest/url = base host of the remote service
org.acme.WorldClockService/mp-rest/url=http://worldclockapi.com
```
Combine directly with the Fault tolerance annotations above (`@Retry`/`@Timeout`/`@CircuitBreaker` on the client interface's methods) — this is the primary intended use case MicroProfile Fault Tolerance was designed for. To turn 4xx/5xx responses into typed exceptions instead of handling status codes manually, implement `ResponseExceptionMapper<T>`.

## Securing endpoints: RBAC with @RolesAllowed + JWT

```java
@GET
@RolesAllowed("Tester")                       // 403 if the authenticated principal lacks this role
@Produces(MediaType.TEXT_PLAIN)
public String hello() { return "hello"; }
```

A JWT (`quarkus-smallrye-jwt`) is three Base64 segments — `header.claims.signature` — validated by Quarkus on every request: signature verified, expiry (`exp`) checked, then the token's `groups`/`roleMappings` claims become the roles `@RolesAllowed` checks against:
```json
{"sub": "jdoe", "groups": ["Tester", "Subscriber"], "exp": 2200814171, "iss": "https://your-issuer"}
```
For an identity source instead of (or feeding) JWT, `quarkus-elytron-security-jdbc` maps a users/roles table to Quarkus's security layer via a config-only principal query — no custom `IdentityProvider` code needed for the common case:
```properties
quarkus.security.jdbc.enabled=true
quarkus.security.jdbc.principal-query.sql=SELECT u.password, u.role FROM users u WHERE u.username=?
```
This JDBC setup is a real recipe from the source material's *example* project, not a hardened production reference — treat the schema/plaintext-query shown as illustrative of the wiring, not a security review; validate password hashing (bcrypt mapper), least-privilege queries, and secret management (see `java-devops-toolchain`) before shipping it.

## Database migrations with Flyway

```bash
./mvnw quarkus:add-extension -Dextensions="quarkus-flyway,quarkus-jdbc-postgresql"
```
```properties
quarkus.flyway.migrate-at-start=true          # run pending migrations automatically on boot
```
Migration files go in `src/main/resources/db/migration/`, named `V<version>__<description>.sql` (e.g. `V1__create_fruit_table.sql`) — Quarkus/Flyway discovers and orders them automatically, no manual registration. With multiple datasources, scope the setting per datasource name: `quarkus.flyway.<datasource-name>.migrate-at-start=true`. Prefer this over Hibernate's `quarkus.hibernate-orm.database.generation=update` for anything beyond local prototyping — Flyway migrations are explicit, versioned, and reviewable in a PR; schema auto-generation is not.
