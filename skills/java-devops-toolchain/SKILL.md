---
name: java-devops-toolchain
description: Use whenever writing or reviewing a Dockerfile for a Java service, sizing JVM memory/heap for a container, choosing a base image (JDK vs JRE, distroless, Alpine), designing a CI/CD pipeline's stages, managing Maven/Gradle dependencies and container image versioning, or needing common Docker commands (build, run, logs, exec, volumes, networks, compose, prune) for a containerized Java app.
---

# Java DevOps Toolchain

Operational reference for containerizing and shipping Java services. Distilled from *DevOps Tools for Java Developers* (Chin, McKay, Ruiz, Sadogursky — O'Reilly) for CI/CD philosophy, Maven/Gradle dependency and image-versioning practice, and supply-chain/security framing; JVM container-memory flags and the Dockerfile mechanics are filled in from standard current practice where the source is silent. Docker command tables are translated from a Docker quick-reference cheat sheet. Applies to any Java backend project — this is process and configuration guidance, not tied to a specific codebase.

## Mental model

A Java service goes through the same loop every change: **commit -> build -> test -> package -> containerize -> scan -> deploy -> observe**, feeding back into the next commit. Two things repeatedly go wrong when teams containerize Java specifically:

1. **The image is built like a workstation, not an artifact.** JDK, build tools, and source code end up in the runtime image, bloating it and widening the attack surface.
2. **The JVM doesn't know it's in a container.** Left to defaults (or a stale hardcoded `-Xmx`), it can size its heap against the *host's* memory rather than the *container's* cgroup limit, and gets OOM-killed under load that would have fit fine.

Everything below exists to close those two gaps, plus give the pipeline around the container a concrete shape.

## Multi-stage Dockerfile for a Java service

Use two stages: a heavyweight **build** stage with the full JDK and build tool, and a minimal **runtime** stage that only has what's needed to execute the app. Only the final stage ships.

```dockerfile
# ---- Stage 1: build (JDK + build tool, not shipped) ----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copy only dependency descriptors first -> this layer is cached
# as long as pom.xml / build.gradle don't change, even if source does.
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN ./mvnw -B dependency:go-offline

# Now copy source and build. This invalidates the cache on every
# code change, but the dependency layer above stays warm.
COPY src src
RUN ./mvnw -B -o package -DskipTests

# ---- Stage 2: runtime (JRE only, minimal attack surface) ----
FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app

# Run as non-root
RUN addgroup -S app && adduser -S app -G app
USER app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Layer-order rationale (the part teams get backwards):

- **Least-changing instructions go first.** Dependency manifests (`pom.xml`, `build.gradle*`, lockfiles) rarely change between commits; source changes on every commit. Copying manifests and resolving dependencies *before* copying source means Docker's build cache reuses the (slow) dependency-download layer on almost every rebuild, and only re-runs the (fast) compile/package layer.
- **`.dockerignore` matters as much as `COPY`.** Exclude `target/`, `build/`, `.git`, local `.env` files, and IDE metadata — anything not excluded gets sent to the build context and can bust cache or leak into layers.
- **Multi-stage build, not a single fat image.** The build stage's JDK, Maven/Gradle cache, and source tree never reach the runtime image — only the compiled JAR crosses the `COPY --from=build` boundary. This is the single biggest lever on final image size and CVE surface.
- **JRE, not JDK, at runtime.** The runtime stage needs to *execute* bytecode, not compile it — `javac`, build tools, and headers add size and attack surface for no runtime benefit. Use a `-jre` tag, not a `-jdk` tag, for the final stage.
- **Prefer slim/Alpine or distroless bases for the runtime stage.** Fewer packages means fewer CVEs to patch and a smaller pull. If you need `native-image` (GraalVM) output instead of a JAR, a distroless or "scratch"-style base works even better since there's no JVM to ship at all — evaluate case by case (native images trade startup time and memory for longer build times and less mature debugging).
- **Pin exact image tags, never `latest`.** The book's Maven/Gradle dependency-management chapter draws the parallel directly: relying on `latest` for a base or parent image is the container equivalent of a SNAPSHOT dependency in production — the build is no longer reproducible, and a bug that appears in production may be impossible to bisect because you can't get back the exact base image that shipped last week. Pin `eclipse-temurin:21.0.3_9-jre-alpine`-style exact tags (or digests) for anything that ships.
- **Use only trusted, minimal base images.** Prefer official/verified publishers, enable image signing/content-trust verification where your registry supports it, and treat every unnecessary package in the base image as attack surface you didn't need to accept.
- **Run as a non-root user.** Create a dedicated user in the runtime stage; don't execute the app as root inside the container even though the container is "isolated."

## JVM memory flags in a container

The classic failure mode: a JVM launched with no flags, or with an `-Xmx` value someone hardcoded years ago for a specific host, ignores the container's actual memory limit (the cgroup limit set by `docker run -m` / Kubernetes `resources.limits.memory`). Modern JVMs (10+) are cgroup-aware by default, but you should still be explicit:

```dockerfile
ENTRYPOINT ["java", \
  "-XX:MaxRAMPercentage=75.0", \
  "-XX:InitialRAMPercentage=50.0", \
  "-XX:+ExitOnOutOfMemoryError", \
  "-jar", "app.jar"]
```

- **`-XX:MaxRAMPercentage=<pct>`** (not `-Xmx<fixed-value>`) sizes the heap as a percentage of the *container's* memory limit, discovered from the cgroup — not the host's total RAM. This is what makes the same image correctly-sized whether it's deployed with a 512Mi limit or a 4Gi limit, with no rebuild.
  - Leave headroom for non-heap memory (thread stacks, metaspace, direct buffers, JIT code cache, native libraries): 75% is a reasonable starting point for a typical web service; drop lower (50-60%) if the app does heavy off-heap I/O buffering (Netty, gRPC) or you run many threads.
  - Avoid `-Xmx` fixed values in container images unless you deliberately want a heap size independent of the container limit — that independence is exactly the stale-config trap this section exists to warn about.
- **`-XX:InitialRAMPercentage`** avoids slow heap growth on startup for latency-sensitive services; omit it if you'd rather let the JVM grow the heap lazily to save memory on low-traffic instances.
- **`-XX:+ExitOnOutOfMemoryError`** makes an OOM a clean container restart (let the orchestrator reschedule) instead of a JVM limping along in a broken state.
- **Set the container's memory *limit* generously above `MaxRAMPercentage`'s ceiling**, not equal to it — the JVM's own overhead (metaspace, code cache, GC structures, thread stacks) lives outside the percentage-of-heap calculation. A container with a 512Mi limit and `MaxRAMPercentage=75` gets a ~384Mi heap; budget the remaining ~128Mi for everything else, and watch actual RSS in staging before trusting the math.
- **Match CPU requests/limits to `-XX:ActiveProcessorCount` expectations.** The JVM sizes its default thread pools (GC threads, ForkJoinPool common pool, etc.) off visible CPU count; a container capped at 1 CPU but scheduled on a 32-core host can still see 32 "processors" unless the runtime or orchestrator makes cgroup CPU quotas visible. Recent JVMs handle this automatically — verify with `java -XX:+PrintFlagsFinal -version | grep ActiveProcessorCount` inside the actual container image if threading behavior looks off.
- **Verify, don't assume**: run `docker stats` (or check pod memory metrics) under realistic load before shipping a memory limit; the "right" percentage depends on the app's off-heap footprint, not a universal constant.

## CI/CD pipeline stage checklist

A pipeline is the automation of the same loop every developer already runs by hand locally; the value is running it identically and immediately on every change (this is the core argument for Continuous Integration: catch a bug when one commit is suspect, not when a hundred are). Shape it as discrete stages with a clear pass/fail gate between each — don't let a later stage start if an earlier one failed.

| Stage | Purpose | Typical contents | Fails fast on |
|---|---|---|---|
| **Build** | Compile and resolve dependencies | `mvn -B compile` / `gradle compileJava`; dependency resolution against a locked/pinned version set, not floating ranges | Compilation errors, unresolved/conflicting dependencies |
| **Test** | Verify behavior | Unit tests, static analysis/lint, code coverage gate | Failing tests, coverage regression |
| **Package** | Produce the deployable artifact | `mvn package` / `gradle build`; publish the JAR/POM to an artifact repository (Nexus, Artifactory, or Maven Central for OSS) with an immutable version — never republish the same version | Packaging failure, missing/invalid metadata |
| **Containerize** | Build the image | Multi-stage `docker build`, tag with an immutable version (commit SHA or semantic version, never `latest`), push to a registry | Docker build failure, missing base image pin |
| **Scan** | Supply-chain / vulnerability check | Image vulnerability scan (`docker scan`, Snyk, Trivy, Grype), dependency vulnerability check (OWASP Dependency-Check, Snyk), optionally SBOM generation and artifact signing | Critical/high CVEs above policy threshold, unsigned/unverifiable artifact |
| **Deploy** | Ship to an environment | Progressive rollout — canary or blue/green rather than all-at-once — to catch bad releases against a fraction of traffic before full exposure | Health-check/readiness failures, error-rate or latency regression on canary |
| **Observe** | Confirm it's actually healthy | Post-deploy monitoring window tied to rollback automation; feed metrics back into the next planning cycle | SLO breach triggers automatic rollback |

Notes worth keeping in mind when designing the pipeline, not just the stage table:

- **Treat the CI pipeline itself as a security-sensitive asset.** The 2020 SolarWinds breach happened because attackers compromised the build pipeline, not the source repository — every build silently produced a backdoored binary that looked legitimate because it carried the vendor's real signature. Lock down who can modify pipeline definitions and build-agent configuration as tightly as production credentials.
- **Small, frequent integrations beat large ones.** The smaller each change entering the pipeline, the faster a failure is isolated (fewer commits to bisect) and the less painful conflicts are to resolve. Design the pipeline to run cheaply and fast enough that developers actually integrate several times a day rather than batching a week of work.
- **Pin everything that crosses a stage boundary**: dependency versions, base image tags, plugin versions. Reproducibility is what makes "roll back to the last green build" meaningful.
- **Track the four metrics the pipeline exists to improve** (from the DORA/Accelerate research the book leans on): deployment frequency, lead time for changes, mean time to restore service, and change failure rate. If a proposed pipeline change doesn't move one of these, question whether it's worth the complexity.

## Security scanning in depth: SAST vs DAST, and CVSS scoring

The "Scan" pipeline stage above names tools; this section is what the book actually explains about *how* those tools work and how to act on what they find — useful when a scan finding needs triage, not just a pass/fail gate.

**SAST (Static Application Security Testing)** — white-box, source-code-only, no running app needed. Finds tainted-input paths, unsafe file/class loading, and app-level interaction gaps *before* code ships; findings are cheap to fix because they surface during development, not after a release. Weakness: it can't see anything that only manifests at runtime (misconfiguration, environment-specific issues), and teams that lean on it exclusively skip the runtime tests that would have caught those.

**DAST (Dynamic Application Security Testing)** — black-box, attacks a *running* app the way an external attacker would, with zero knowledge of the tech stack underneath. Catches runtime/config issues SAST structurally cannot (a firewall misrule, an exposed running service), and has a low false-positive rate because it's exercising real behavior, not pattern-matching source. Weakness: expensive to run (scans can take days), only applies to running web apps/services (not libraries or CLIs), and by definition finds problems late in the SDLC — a critical DAST finding after code is already in QA is a much costlier fix than the same bug caught by SAST at commit time.

| | SAST | DAST |
|---|---|---|
| Access needed | Source code (white-box) | A running, deployed application (black-box) |
| Perspective | Developer / inside-out | Attacker / outside-in |
| When in the SDLC | As soon as code is feature-complete | After deployment to a test/staging environment |
| Cost to fix a finding | Low (caught pre-release) | Higher (often pushed to next release cycle, or an emergency patch) |
| Runtime/environment issues | Cannot detect | Can detect (misconfig, upstream LB/firewall behavior) |
| Applies to | Any codebase (web app, library, CLI, thick client) | Web apps/services only |

They're complementary, not competing — run both. SAST on every commit (cheap, fast, catches known-pattern issues early); DAST on a deployed test environment before release (catches what only a running system exposes). Running DAST directly against production trades safety for the most realistic signal — most teams run it against a staging environment that mirrors production closely enough to still be meaningful, accepting they lose some fidelity in exchange for not attacking their own live system.

**CVSS (Common Vulnerability Scoring System)** — the standard for turning "we found a vulnerability" into an actionable, comparable number. Score is `probability of occurrence × maximum possible damage`, computed from three weighted metric groups (Base, Temporal, Environmental) into a 0.0-10.0 value, bucketed into None/Low/Medium/High/Critical. The practical payoff: it lets a team define a policy threshold ("block the pipeline on High or above") instead of a human manually judging each finding's severity — which is what the "Fails fast on: Critical/high CVEs above policy threshold" cell in the pipeline table above actually means in practice.

**Full impact graph**: a vulnerability's real severity depends on where it's actually used, not just that it exists — a CVE in a library nobody calls is close to zero risk; the same CVE in a jar packaged into a war, layered into a Docker image, and deployed via a Helm chart to production is a live path an attacker can reach. Tools that only scan one layer (just the jar, or just the image) miss this — evaluate scanning tools on whether they trace dependencies *across* technology boundaries (source → package → image → deployment artifact), not just within one.

**Why this matters beyond "run a scanner"**: the SolarWinds breach (referenced above) wasn't caught by scanning the shipped binary for known CVEs — the binary was signed and looked legitimate. The attack compromised the *build pipeline itself*, so every artifact it produced was backdoored from a trusted source. This is the concrete case for treating pipeline infrastructure (who can edit build configs, what the build agents can access) as security-sensitive in its own right, not just scanning what comes out the other end.

## Maven/Gradle notes worth keeping in mind

- **Dependency resolution conflicts are resolved by "nearest wins" (Maven) or configurable strategies (Gradle)** — always run `mvn dependency:tree` / `gradle dependencies --configuration compileClasspath` when a version looks wrong instead of guessing; both tools tell you which requested version got overridden and why.
- **`mvn verify` vs `mvn install`**: `install` runs the full lifecycle up through copying artifacts into the local `~/.m2/repository` cache; `verify` stops one phase earlier (compile, test, package, integration-test) without polluting local Maven repo state. Default to `verify` in CI unless a later step in the same pipeline actually needs the artifact resolved from Maven Local.
- **Container images are a dependency too** — apply the same discipline used for library versions (explicit, pinned, reviewed) to base/parent image tags. An unpinned base image is a floating dependency exactly like an unpinned library version, with the same reproducibility failure mode.

## Docker command quick reference

Translated and organized from a Docker shortcuts cheat sheet; covers the commands used day to day when building, running, and debugging a containerized Java service.

### Images

| Action | Command |
|---|---|
| Pull an image | `docker pull IMAGE_NAME:TAG` |
| Build and tag an image from the Dockerfile in the current dir | `docker build -t myimage:mytag .` |
| Rebuild ignoring the cache | `docker build --no-cache -t myimage:mytag .` |
| Re-tag a local image (e.g. before pushing) | `docker tag myimage:mytag myuser/myimage:mytag` |
| List local images | `docker images` |
| Remove a specific image | `docker rmi IMAGE_NAME` (or `docker image rm <image-id>`) |
| Remove dangling (untagged) images | `docker image prune` |
| Remove all unused images | `docker image prune -a` |
| Show an image's layer history | `docker image history IMAGE_NAME` |
| Scan an image for vulnerabilities | `docker scan IMAGE_NAME[:TAG]` |
| Log in to a registry | `docker login -u <username>` |

### Containers

| Action | Command |
|---|---|
| Run a container, detached, mapping a port | `docker run -dp 8080:8080 IMAGE_NAME` |
| Run with a specific name | `docker run --name myservice IMAGE_NAME` |
| List running containers | `docker ps` (or `docker container ls`) |
| List all containers, including stopped | `docker ps -a` |
| Stop and remove a container | `docker stop <id>` then `docker rm <id>` |
| Force-stop and remove one or more containers | `docker rm -f <id1> <id2> ...` |
| Start a previously created container | `docker start <id>` |
| View logs | `docker logs <id>` |
| Follow logs live | `docker logs --follow <id>` |
| Live resource usage stats | `docker stats` |
| Open a shell inside a running container | `docker exec -it <id> /bin/sh` (or `bash` if present) |
| Get help for any command | `docker <command> --help` |

### Volumes and networks

| Action | Command |
|---|---|
| Create a named volume | `docker volume create my-data` |
| List volumes | `docker volume ls` |
| Inspect a volume | `docker volume inspect my-data` |
| Remove unused volumes | `docker volume prune` |
| Run with a named volume mounted | `docker run -v my-data:/var/lib/app IMAGE_NAME` |
| Bind-mount a host path (dev workflow) | `docker run -v "$(pwd):/app" -w /app IMAGE_NAME` |
| Create a network so containers can reach each other by name | `docker network create my-net` |
| Run attached to a network with a DNS alias | `docker run --network my-net --network-alias db IMAGE_NAME` |
| List / inspect / prune networks | `docker network ls` / `docker network inspect my-net` / `docker network prune` |

Golden rule for networking: containers on the same user-defined network can reach each other by container/alias name; containers on different networks (or the default bridge without an alias) cannot.

### Compose

| Action | Command |
|---|---|
| Start all services in the background | `docker compose up -d` |
| Follow logs for all services | `docker compose logs -f` |
| Stop and remove containers + network | `docker compose down` |

Minimal `docker-compose.yml` shape for a Java service plus a database:

```yaml
version: '3'
services:
  app:
    build:
      context: .
      dockerfile: Dockerfile
    depends_on:
      - db
    ports:
      - "8080:8080"
    environment:
      DB_HOST: db
      DB_PASSWORD: ${DB_PASSWORD}
    restart: always
  db:
    image: postgres:16-alpine
    volumes:
      - db-data:/var/lib/postgresql/data
    environment:
      POSTGRES_PASSWORD: ${DB_PASSWORD}
volumes:
  db-data:
```

Keep secrets (`${DB_PASSWORD}`-style) out of the file itself — source them from a `.env` file that's gitignored, or from the orchestrator's secret store in real deployments.

### Glossary (quick orientation)

- **Image**: a layered, immutable file built from a Dockerfile — code, runtime, config, and dependencies bundled together.
- **Container**: a running instance of an image, isolated from the host and other containers.
- **Layer**: each Dockerfile instruction produces one layer (a diff from the previous filesystem state); layers are cached and reused across builds when the instructions above them haven't changed — this is why instruction order matters (see the multi-stage section above).
- **Registry**: a server that stores and distributes images (Docker Hub, ECR, GCR, GitHub Container Registry, self-hosted Artifactory/Nexus).
- **`.dockerignore`**: like `.gitignore`, but for the build context — excludes files from being sent to the Docker daemon during `build`.
