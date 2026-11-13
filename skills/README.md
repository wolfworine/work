# Skills — Java Backend Engineering

Librería de skills agnósticos (no atados a un proyecto puntual) para que cualquier agente los consulte al escribir, revisar o refactorizar código backend. Cada carpeta es un skill independiente con su `SKILL.md` (frontmatter `name` + `description` que dispara el uso, y un cuerpo operativo con heurísticas y ejemplos).

**Estado:** 22 skills vigentes (actualizado 2026-11-13). Se retiraron los libros fuente (`arquetipos-md`, `quarkus-md`) y los skills superados.

## Java: lenguaje, arquitectura y diseño

| Skill | Cuándo se activa |
|---|---|
| [`java-hexagonal-architecture`](java-hexagonal-architecture/SKILL.md) | Estructura de paquetes, ports & adapters, lógica de negocio filtrándose a controllers/repositorios |
| [`solid-design-principles`](solid-design-principles/SKILL.md) | Clases que hacen demasiado, jerarquías frágiles, acoplamiento a implementaciones concretas |
| [`modern-java-language-features`](modern-java-language-features/SKILL.md) | Records, sealed, pattern matching, streams, `Optional`, virtual threads |
| [`google-java-style-guide`](google-java-style-guide/SKILL.md) | Formato y naming Java, imports, Javadoc |
| [`lombok-best-practices`](lombok-best-practices/SKILL.md) | Lombok vs records y sus trampas |
| [`enterprise-java-design-patterns`](enterprise-java-design-patterns/SKILL.md) | Circuit breaker, retry, saga, outbox, strangler, cache-aside |
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
