# Skills — Java Backend Engineering

Librería de skills agnósticos (no atados a un proyecto puntual) para que cualquier agente los consulte al escribir, revisar o refactorizar código backend. Cada carpeta es un skill independiente con su `SKILL.md` (frontmatter `name` + `description` que dispara el uso, y un cuerpo operativo con heurísticas y ejemplos).

**Estado:** 33 skills vigentes (actualizado 2026-11-13). Se retiraron los libros fuente (`arquetipos-md`, `quarkus-md`) y los skills superados.

**Criterio de fuentes:** los skills nuevos se construyen solo con documentación del proveedor o del framework (Oracle/OpenJDK, Quarkus, AWS, Microsoft, etc.). Cada skill lista sus fuentes y marca lo que no pudo verificar.

## Java: lenguaje, arquitectura y diseño

| Skill | Cuándo se activa |
|---|---|
| [`java-hexagonal-architecture`](java-hexagonal-architecture/SKILL.md) | Estructura de paquetes, ports & adapters, lógica de negocio filtrándose a controllers/repositorios |
| [`solid-design-principles`](solid-design-principles/SKILL.md) | Clases que hacen demasiado, jerarquías frágiles, acoplamiento a implementaciones concretas |
| [`modern-java-language-features`](modern-java-language-features/SKILL.md) | Records, sealed, pattern matching, streams, `Optional`, virtual threads |
| [`java21-virtual-threads-quarkus`](java21-virtual-threads-quarkus/SKILL.md) | Java 21 virtual threads (Loom) y su uso en Quarkus: pinning, `@RunOnVirtualThread`, pools, Semaphore, diagnóstico |
| [`google-java-style-guide`](google-java-style-guide/SKILL.md) | Formato y naming Java, imports, Javadoc |
| [`lombok-best-practices`](lombok-best-practices/SKILL.md) | Lombok vs records y sus trampas |
| [`enterprise-java-design-patterns`](enterprise-java-design-patterns/SKILL.md) | Circuit breaker, retry, saga, outbox, strangler, cache-aside |
| [`microservices-antipatterns`](microservices-antipatterns/SKILL.md) | Antipatrones de microservicios (Microsoft, Fowler, Google SRE): BD compartida, retries, timeouts, llamadas síncronas, monolito primero |
| [`coding-interview-algorithms`](coding-interview-algorithms/SKILL.md) | Big-O tiempo/espacio y reducción de memoria |
| [`reactive-java-webflux`](reactive-java-webflux/SKILL.md) | Reactor, WebFlux, R2DBC, `map` vs `flatMap` |

## Frameworks, mensajería e IA

| Skill | Cuándo se activa |
|---|---|
| [`quarkus-cloud-native-java`](quarkus-cloud-native-java/SKILL.md) | CDI, Panache, JAX-RS, perfiles, JVM vs native |
| [`quarkus-extension-verification`](quarkus-extension-verification/SKILL.md) | Extensiones Quarkus de terceros, versiones y secretos como archivo |
| [`spring-boot-high-scale-architecture`](spring-boot-high-scale-architecture/SKILL.md) | GC, pools, caché y Resilience4j en servicios de alto tráfico |
| [`kafka-event-driven-java`](kafka-event-driven-java/SKILL.md) | Productores/consumidores Kafka, entrega, particiones, offsets |
| [`genai-java-integration`](genai-java-integration/SKILL.md) | LLM, RAG, embeddings y MCP dentro de apps Java |

## Quarkus: estándares de desarrollo

| Skill | Cuándo se activa |
|---|---|
| [`quarkus-contract-first-openapi`](quarkus-contract-first-openapi/SKILL.md) | Contract-first / API-first: OpenAPI antes del código, generación de interfaces con Quarkiverse OpenAPI Generator, spec estática, evolución compatible |
| [`quarkus-rest-api-standards`](quarkus-rest-api-standards/SKILL.md) | Diseño de endpoints, códigos HTTP, validación, errores RFC 9457, paginación, ETag, hilos event-loop/worker |
| [`quarkus-fault-tolerance`](quarkus-fault-tolerance/SKILL.md) | `@Timeout`, `@Retry`, `@CircuitBreaker`, `@Bulkhead`, `@Fallback`, backoff/jitter, retry storms, Kafka DLQ |
| [`idempotency-design`](idempotency-design/SKILL.md) | `Idempotency-Key`, deduplicación, consumidores idempotentes, reintentos seguros, sagas |
| [`quarkus-observability`](quarkus-observability/SKILL.md) | Health (live/ready/started), Micrometer, OpenTelemetry, logs JSON con traceId, golden signals |
| [`quarkus-security-standards`](quarkus-security-standards/SKILL.md) | OIDC/JWT, `@RolesAllowed`, deny-by-default, TLS, CORS, OWASP REST y API Top 10 |
| [`quarkus-config-profiles`](quarkus-config-profiles/SKILL.md) | Perfiles dev/test/prod, precedencia de config, `@ConfigMapping`, secretos, 12-factor |
| [`quarkus-testing-standards`](quarkus-testing-standards/SKILL.md) | `@QuarkusTest`, `@InjectMock`, `@TestProfile`, Dev Services, WireMock, pirámide de tests |
| [`junit5-testing-best-practices`](junit5-testing-best-practices/SKILL.md) | JUnit 5 + Mockito + AssertJ, fixtures JSON, tests parametrizados, `StepVerifier`, `Clock`; revisión de `DocumentServiceImpl2Test` |

## Datos, infraestructura y flujo de trabajo

| Skill | Cuándo se activa |
|---|---|
| [`redis-oss-elasticache`](redis-oss-elasticache/SKILL.md) | Amazon ElastiCache for Redis OSS 7.1: versiones, cluster mode, seguridad, eviction, clientes Java |
| [`postgresql-practical-reference`](postgresql-practical-reference/SKILL.md) | Esquemas, índices, EXPLAIN, JSONB en Postgres |
| [`sql-fundamentals`](sql-fundamentals/SKILL.md) | SQL estándar: JOIN, NULL, GROUP BY, CTEs, transacciones |
| [`java-devops-toolchain`](java-devops-toolchain/SKILL.md) | Dockerfile Java, memoria de la JVM en contenedores, CI/CD |
| [`kubernetes-for-developers`](kubernetes-for-developers/SKILL.md) | Manifiestos K8s, probes, recursos, autoscaling, depuración |
| [`git-version-control`](git-version-control/SKILL.md) | Merge vs rebase, commits, conflictos, recuperación |
| [`tdd-java-workflow`](tdd-java-workflow/SKILL.md) | TDD con JUnit y Mockito, mocks vs fakes |
| [`ai-assisted-software-development`](ai-assisted-software-development/SKILL.md) | Prompting y flujo spec-first con asistentes de código |
| [`task-observer`](task-observer/SKILL.md) | Observa las tareas y propone mejoras a los skills |
