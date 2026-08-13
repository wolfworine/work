# Skills — Java Backend Engineering

Librería de skills agnósticos (no atados a ningún proyecto puntual) destilados de los libros/documentos de esta carpeta. Pensados para que cualquier agente los consulte al escribir, revisar o refactorizar código backend — arquitectura, principios de diseño, testing, features del lenguaje, programación reactiva, mensajería, infraestructura, bases de datos, IA aplicada y flujo de trabajo con IA.

Cada carpeta es un skill independiente con su `SKILL.md` (frontmatter `name` + `description` que dispara el uso, y un cuerpo operativo con heurísticas y ejemplos de código). Si más adelante un proyecto concreto necesita una variante específica, se puede derivar de estos sin tocar los genéricos.

**Estado (última pasada completa):** los 17 skills fueron auditados contra el corpus completo en `markitdown/arquetipos-md/` y `markitdown/quarkus-md/` (texto íntegro, no muestreo). 10 skills existentes se enriquecieron con contenido que el método de extracción original (PyPDF2, índice + muestreo) se había perdido; 3 skills son nuevos (`sql-fundamentals`, `quarkus-cloud-native-java`, `genai-java-integration`). Ver "Notas y honestidad del proceso" al final para el detalle línea por línea.

## Java: lenguaje y arquitectura

| Skill | Fuente | Cuándo se activa |
|---|---|---|
| [`java-hexagonal-architecture`](java-hexagonal-architecture/SKILL.md) | *Designing Hexagonal Architecture with Java (and Quarkus)*, 2nd ed. (Davi Vieira) | Diseñar/revisar estructura de paquetes, ports & adapters, lógica de negocio filtrándose a controllers/repositorios, wiring con Quarkus CDI/Panache |
| [`solid-design-principles`](solid-design-principles/SKILL.md) | *SOLID: The Software Design and Architecture Handbook* (Stemmler) | Escribir/revisar OOP, clases que hacen demasiado, jerarquías frágiles, acoplamiento a implementaciones concretas, IoC/Hollywood Principle |
| [`tdd-java-workflow`](tdd-java-workflow/SKILL.md) (+ `references/api-testing.md`) | *TDD with Java* + *API Testing Guide* | Escribir tests primero, ciclo red-green-refactor, Classicist vs Mockist, tests frágiles/sobre-mockeados, JUnit5/Mockito, testing de APIs HTTP/Postman |
| [`modern-java-language-features`](modern-java-language-features/SKILL.md) | *Modern Java in Action* + *Code with Java 21* | Records/sealed classes/pattern matching, Streams/Collectors avanzados, `CompletableFuture`, Sequenced Collections, uso correcto de `Optional`, virtual threads |
| [`reactive-java-webflux`](reactive-java-webflux/SKILL.md) (+ `references/operators.md`) | *Reactive Systems in Java*, *Vert.x in Action*, *R2DBC Revealed* (*Reactive Java Programming* descartada — PDF escaneado sin texto útil) | Project Reactor / WebFlux / R2DBC, `map` vs `flatMap`, `block()` accidental, Vert.x event bus/backpressure, decidir si algo necesita ser reactivo |
| [`enterprise-java-design-patterns`](enterprise-java-design-patterns/SKILL.md) | *Enterprise Java Design Patterns* (Markus Eisele) | Resiliencia servicio-a-servicio, migraciones (Strangler Fig), Saga/Outbox, Circuit Breaker/Bulkhead |
| [`spring-boot-high-scale-architecture`](spring-boot-high-scale-architecture/SKILL.md) | *Engineering Playbook: Spring Boot Staff Level — High-Scale Architecture* (COVAIB Deeplearn) | GC (G1GC vs ZGC), connection pools, thread pools, caching, trade-offs de nivel staff |
| [`kafka-event-driven-java`](kafka-event-driven-java/SKILL.md) | *Effective Kafka* (Koutanov) | Producers/consumers Kafka, garantías de entrega, partition keys, static membership, eager vs cooperative rebalancing, consumer lag, poison-pill messages |
| [`coding-interview-algorithms`](coding-interview-algorithms/SKILL.md) | *Cracking the Coding Interview* (6th ed.) | Complejidad Big-O tiempo/espacio, **reducción de memoria** (in-place, bit vectors, rolling DP), patrones de algoritmos, sorting, system design, OO design |
| [`google-java-style-guide`](google-java-style-guide/SKILL.md) | Google Java Style Guide (web, vigente) + PDF archivado 2014 | Formato y naming de código Java: braces, column limit, imports, `UPPER_SNAKE_CASE` vs `lowerCamelCase`, Javadoc, orden de modificadores |
| [`quarkus-cloud-native-java`](quarkus-cloud-native-java/SKILL.md) (+ `references/recipes.md`) | *Quarkus for Spring Developers*, *Understanding/Practising Quarkus* (fascicles), *Quarkus Cookbook*, *Hands-On Cloud-Native Applications with Java and Quarkus*, *Quarkus in Action* (MEAP) | CDI/scopes/Panache, `application.properties`/profiles, JAX-RS vs RESTEasy Reactive, JVM vs native image (GraalVM), `@QuarkusTest`, migrar/comparar contra Spring Boot |
| [`genai-java-integration`](genai-java-integration/SKILL.md) 🆕 | *Applied AI for Enterprise Java Development* (Soto Bueno, Eisele, Vinto) | Integrar un LLM/chatbot en un backend Java (no "usar IA para programar" — eso es `ai-assisted-software-development`): LangChain4j, RAG, embeddings, vector stores, MCP client/server en Quarkus |

## Bases de datos

| Skill | Fuente | Cuándo se activa |
|---|---|---|
| [`sql-fundamentals`](sql-fundamentals/SKILL.md) 🆕 | *Learning SQL* (Alan Beaulieu) + *SQL QuickStart Guide* (Walter Shields) | Escribir/revisar JOINs, subqueries vs CTE, GROUP BY/HAVING, transacciones, NULL, anti-patrones (Cartesian join accidental, N+1) — agnóstico de motor de BD |
| [`postgresql-practical-reference`](postgresql-practical-reference/SKILL.md) | PostgreSQL 18 Docs oficial completo (18.3/18.4) | Índices, `EXPLAIN ANALYZE`, JSONB, tipos de datos, aislamiento de transacciones, connection pooling, particionamiento, full-text search, backup/restore, replicación — específico de Postgres |

## Infraestructura y operación

| Skill | Fuente | Cuándo se activa |
|---|---|---|
| [`git-version-control`](git-version-control/SKILL.md) | *Git y GitHub desde cero* (Brais Moure) + *Pro Git* (Chacon/Straub) | Comandos git, merge vs rebase, mensajes de commit, resolución de conflictos, recuperación de desastres, flujo de PR, internals (objects/refs/packfiles), hooks, submodules, bisect, rerere |
| [`java-devops-toolchain`](java-devops-toolchain/SKILL.md) | *DevOps Tools for Java Developers* + cheat-sheet Docker | Dockerfile multi-stage, memoria de JVM en contenedor, etapas de CI/CD, comandos Docker, SAST vs DAST, scoring CVSS |
| [`kubernetes-for-developers`](kubernetes-for-developers/SKILL.md) | *Kubernetes for Developers* (William Denniss) | Deployment/Service YAML, probes liveness/readiness, requests/limits y OOMKill, CrashLoopBackOff, HPA, Ingress, StatefulSet, Namespaces |

## Meta: cómo trabajar

| Skill | Fuente | Cuándo se activa |
|---|---|---|
| [`ai-assisted-software-development`](ai-assisted-software-development/SKILL.md) | 4 PDFs de prompts/curso IA/Spec-Driven Development | Prompts efectivos por tipo de tarea, priming de contexto de proyecto, flujo Spec-Driven Development (spec → ADR → código) — **usar IA para programar**, no confundir con `genai-java-integration` (meter IA como feature del producto) |

## Libros/documentos descartados (triage)

Revisados y confirmados sin contenido técnico distintivo propio, o ya cubiertos por otra fuente — no generaron skill propio:

- `learning-java-...5nbsped` — referencia general O'Reilly (Swing, servlets, I/O clásico), sin tratamiento moderno de Streams/records.
- `programming-with-java-mastering-programming-languages-series` — curso bootcamp de sintaxis/OOP/GUI básico.
- `introduction-to-programming-with-greenfoot-...` — enseñanza de OOP para principiantes vía juegos.
- `object-oriented-programming-with-java-...` (Hanumanth Ladwa) — libro académico introductorio (fundamentos de OOP, tipos de datos, applets, AWT, Swing); currícula clásica sin contenido a nivel senior, incluye tecnología obsoleta (Applets).
- `Ejercicio_JAVA-Especialista_Integracion...pdf` — enunciado de una prueba técnica distinta (API de registro de usuarios, empresa BCI), es contexto de proyecto puntual, no material para un skill agnóstico.
- Dos PDFs con nombre UUID ("Dashboard + APP SaaS + IA + Ventas") — flyer de marketing sin contenido técnico.
- `designing-hexagonal-architecture-with-java-2nbsped` (`arq-015`) — **no es un segundo libro**: es la misma edición exacta que la fuente de `java-hexagonal-architecture` (`arq-016`), solo una extracción PDF→texto distinta y más ruidosa. Confirmado por título/autor/ISBN idénticos.
- `postgresql-18-A4` versión 18.3 (`arq-029`) — duplicado de la doc oficial 18.4 (`arq-006`) usada para `postgresql-practical-reference`; misma documentación, versión anterior.
- De la carpeta `CursoMicroservicios/Quarkus`: `introduction-to-java-through-game-development` (introductorio, mismo criterio que los libros de Java básico descartados arriba), `kubernetes-for-developers-final-release` (`qrk-004`, duplicado del mismo libro ya cubierto por `kubernetes-for-developers`).
- `applied-ai-enterprise-java-ebook` (`qrk-001`) — **ya no está descartado**, es la fuente de `genai-java-integration` (ver arriba).

## Notas y honestidad del proceso

- **Método de extracción (histórico, primera pasada)**: los skills se construyeron originalmente con PyPDF2 (índice/outline + muestreo dirigido de las secciones relevantes), no con una lectura completa página por página de cada libro — sería impracticable para libros de 300-3000+ páginas. Los libros cortos (<100 páginas) sí se leyeron casi en su totalidad.
- **Método de extracción (actual)**: desde que existe el proyecto `markitdown` (`D:\Capacitacion\markitdown`), todos los PDFs de `arquetipos/` y de `CursoMicroservicios/Quarkus/` (raíz) están convertidos a texto Markdown completo en `markitdown/arquetipos-md/` (prefijo `arq-NNN`) y `markitdown/quarkus-md/` (prefijo `qrk-NNN`), cada uno con su `INDEX.md`/`index.json`. Esto reemplaza la necesidad de PyPDF2 — se puede grep/leer el `.md` completo directamente en vez de solo índice+muestreo. Script para regenerar: `markitdown/scripts_batch_convert.py --src <carpeta> --out <carpeta> --prefix <pfx>`.
- **Pasada de re-análisis con texto completo (esta ronda)**: se auditaron los 17 skills contra el corpus completo para confirmar que nada de valor se hubiera perdido con el muestreo original. Resultado por skill:
  - `git-version-control`: **hueco real encontrado y corregido** — nunca se había usado *Pro Git* (`arq-030`, 149k palabras), el libro oficial de Git, pese a estar disponible. Se agregaron internals (objects/refs/packfiles), hooks, submodules, `bisect`, `rerere`.
  - `genai-java-integration`: **hueco real encontrado y corregido** — `qrk-001` (104k palabras, mismo autor que `enterprise-java-design-patterns`) estaba completamente sin usar. Se convirtió en skill propio.
  - `postgresql-practical-reference`: se pasó de "subset curado" a manual oficial completo; se agregaron particionamiento, full-text search, backup/restore y replicación (gap-analysis dirigido, no lectura de las 1.1M palabras completas — quedan fuera PL/pgSQL, internals, alta disponibilidad más allá de streaming replication).
  - `coding-interview-algorithms`: se agregaron Sorting, System Design & Scalability y Object-Oriented Design — capítulos reales del libro que el muestreo original no había extraído.
  - `modern-java-language-features`: se agregaron Collectors avanzados, `CompletableFuture`, Sequenced Collections y String templates (con caveat de que String templates se retiró como preview y no llegó a estable). Se **reconfirmó** que sealed classes/pattern matching NO tienen capítulo propio en ninguno de los dos libros fuente — siguen siendo conocimiento estándar añadido, ahora explícitamente re-verificado en vez de asumido.
  - `java-hexagonal-architecture`: el supuesto "segundo libro" resultó ser la misma edición duplicada (ver triage arriba). El hueco real era que el libro se llama *"...with Java and Quarkus"* — 5 capítulos sobre wiring con Quarkus CDI/Panache que el skill original no usaba; agregados con cross-link a `quarkus-cloud-native-java`.
  - `reactive-java-webflux`: se confirmó formalmente que `arq-033` (*Reactive Java Programming*) es un PDF escaneado sin texto extraíble (6 palabras, solo portada) — cualquier contenido previo atribuido a ese libro no puede ser extracción real, ahora flagueado en el skill. Se agregó Vert.x event bus/backpressure y R2DBC savepoints/pool sizing desde las 3 fuentes sí utilizables.
  - `kafka-event-driven-java`: se agregó static membership y eager vs cooperative rebalancing (Chapter 15 del libro). Se descartó explícitamente agregar Kafka Streams/Schema Registry — el libro fuente no los cubre, no se completó con relleno genérico.
  - `kubernetes-for-developers`: se agregó HPA, Ingress, StatefulSet y Namespaces (huecos reales confirmados contra el libro). ConfigMaps/Secrets ya estaban bien cubiertos, se descartó como falso-hueco.
  - `tdd-java-workflow`: se agregó la distinción Classicist/Chicago vs London/Mockist (con cómo la arquitectura hexagonal reconcilia ambas escuelas), testing de adaptadores de BD con fixtures, y cuándo test-first NO es la opción automática. En `references/api-testing.md` se agregó testing manual con Postman/Newman — la fuente resultó ser Python-céntrica, la mayoría de su contenido HTTP ya estaba cubierto por la versión Java/RestAssured existente.
  - `java-devops-toolchain`: se agregó SAST vs DAST y scoring CVSS (Capítulo 6, "Securing Your Binaries"). Se descartó agregar pipelines YAML concretos de GitHub Actions/Nexus — el libro no los tiene, no se inventaron.
  - `solid-design-principles`: **se reconfirmó con texto completo** (no solo muestreo) que OCP/LSP/ISP siguen sin desarrollo real en el libro — solo aparecen como títulos/TOC. Sí se encontró y agregó contenido real sobre IoC/"Hollywood Principle" que el muestreo original no había extraído.
  - `enterprise-java-design-patterns`, `google-java-style-guide`, `ai-assisted-software-development`: no re-analizados en esta pasada — son fuentes cortas donde el muestreo original ya cubría casi todo el documento (el propio README lo documenta así desde la primera pasada).
  - `quarkus-cloud-native-java`: pasada de seguimiento completada — se leyeron a fondo *Quarkus Cookbook* y *Quarkus in Action* (antes solo TOC-confirmados). Se agregó fault tolerance (`@Retry`/`@Timeout`/`@Bulkhead`/`@CircuitBreaker`/`@Fallback`, cross-link a `enterprise-java-design-patterns`) al `SKILL.md`, y 6 recetas nuevas a `references/recipes.md`: scheduled jobs, caching (`@CacheResult`), MicroProfile REST Client, seguridad RBAC/JWT, migraciones con Flyway. *Quarkus in Action* (MEAP temprano, solo caps. 1-6) aportó poco nuevo — casi todo vino del cookbook. Quedan sin explotar a propósito: gRPC, Qute templates, OpenAPI/Swagger, métricas/OpenTelemetry, recetas de MongoDB/Neo4j/DynamoDB/AMQP/MQTT.
  - Queda pendiente, señalado explícitamente por el propio skill como oportunidad futura: `genai-java-integration` no explotó a fondo los capítulos de LangGraph4j (orquestación multi-agente) ni procesamiento de imágenes del libro fuente.
- **Contenido no extraído del libro fuente (histórico, sigue vigente)**: en `spring-boot-high-scale-architecture`, el documento fuente (`arq-001`) resultó ser mayormente marketing y solo la sección de garbage collectors (G1GC/ZGC) es contenido real — el resto (HikariCP, thread pools, caching, Resilience4j) es buena práctica estándar de la industria, no extracción del PDF. En `kubernetes-for-developers`, la sección de sizing de heap JVM en contenedor se añadió aparte porque el libro usa ejemplos en Python, no Java. En `sql-fundamentals`, window functions/CTEs/formas normales (1NF/2NF/3NF) no están en ninguno de los dos libros fuente y se completaron con conocimiento estándar, flagueado en el propio skill. En `quarkus-cloud-native-java`, las magnitudes cuantitativas de la tabla JVM-vs-native (ms de startup, % memoria) son cifras típicas de la industria, no un benchmark de un libro puntual. El mini-doc `arq-011` ("Spring without... Spring Boot tames the beast") se incorporó como nota de contexto dentro de `quarkus-cloud-native-java`, sección "Migrating from Spring Boot".
- `google-java-style-guide` es el único skill construido a partir de una página web (fetched directamente de `google.github.io/styleguide/javaguide.html`) en vez de un PDF; se cruzó contra el PDF archivado de 2014 (`arq-004`) y se documentó explícitamente en el propio skill dónde cambió la norma.
- Los scripts `read_books*.py` de la carpeta padre son extractores ad-hoc con PyPDF2 usados para el análisis original; no forman parte de ningún skill.
- Los `.md` en `arquetipo-ref/` son análisis específicos del proyecto `wallet-api` (gaps, plan de acción) — material de referencia del proyecto, no de los skills genéricos.
- Para usar estos skills en Claude Code, cópialos o enlázalos a `.claude/skills/` (a nivel usuario o de proyecto) cuando corresponda.
