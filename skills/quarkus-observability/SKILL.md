---
name: quarkus-observability
description: Use whenever adding or reviewing observability in a Quarkus service — health checks (liveness/readiness/startup, @Liveness @Readiness @Startup, /q/health), Micrometer metrics (/q/metrics, tags, cardinality, @Counted/@Timed), OpenTelemetry tracing/metrics/logs (quarkus.otel.*, OTLP exporter, sampler, @WithSpan), structured JSON logging, trace id in logs, MDC/correlation ids, log levels per category, Kubernetes probe design, the four golden signals, or SLO/alert design.
---

# Quarkus Observability: health, metrics, traces, logs

**Sources:** Quarkus guides (SmallRye Health, Micrometer, OpenTelemetry, Logging — v3.40), OpenTelemetry docs (signals, HTTP semantic
conventions), Google SRE book (*Monitoring Distributed Systems*), Microsoft Health Endpoint Monitoring pattern.
**[summary]** = extracted part of a page — verify names/defaults. **Not read:** Quarkus telemetry overview (404), last ~57k chars of the Logging guide.
The SmallRye Health page does **not** state a Kubernetes probe mapping — the mapping below is standard practice plus Microsoft's guidance.

## 1. Health (`quarkus-smallrye-health`)

- Endpoints: `/q/health/live`, `/q/health/ready`, `/q/health/started`, `/q/health` (all). JSON `status` is the **logical AND** of all checks.
- Annotations `@Liveness`, `@Readiness`, `@Startup` (`org.eclipse.microprofile.health.*`; `@Startup` clashes with `io.quarkus.runtime.Startup` — mind imports).
- Implement `HealthCheck.call()` returning `HealthCheckResponse.up("name")` / `.down("name")` / builder `withData(k, v)`. Scope `@ApplicationScoped`
  or `@Singleton` (default if none). Reactive: `AsyncHealthCheck` -> `Uni<HealthCheckResponse>` **[summary]**.
- Management interface: `quarkus.management.enabled=true` (build time) moves `/q` endpoints to a separate port — recommended so probes/metrics aren't public (OWASP: don't expose management endpoints to the internet).
- Config **[summary]**: `quarkus.smallrye-health.root-path=health`, `liveness-path=live`, `readiness-path=ready`, `startup-path=started`, `include-problem-details=false`, Health UI only in dev/test unless `ui.always-include`.

```java
@Readiness @ApplicationScoped
public class DbReadiness implements HealthCheck {
  @Inject DataSource ds;
  public HealthCheckResponse call() {
    try (var c = ds.getConnection()) { return HealthCheckResponse.up("db"); }   // bound it with a timeout!
    catch (Exception e) { return HealthCheckResponse.down("db"); }
  }
}
```
Design rules (Microsoft Health Endpoint Monitoring):
- **Liveness = shallow** (process responds; no dependency calls) — a failing dependency must **not** restart your pod.
- **Readiness = deep for required dependencies only** (200/503); bound each check with a timeout; exclude optional dependencies; cache expensive checks.
- Deep probes can cause cascading removal of all instances — keep them cheap and read-only; secure the endpoints.
- Startup probe: Quarkus says it "should not be needed" for fast starts; use for slow initialization.

## 2. Metrics — Micrometer (`quarkus-micrometer` + a registry)

- "Micrometer is the recommended approach to metrics for Quarkus." Prometheus: `quarkus-micrometer-registry-prometheus` -> **`/q/metrics`**
  (`quarkus.micrometer.export.prometheus.path` **[summary]**); `quarkus-micrometer-opentelemetry` sends via OTel.
- Names use dots (`http.server.requests` -> `http_server_requests_duration_seconds` in Prometheus).
- **Tags must be low-cardinality** — unbounded values cause "cardinality explosion" (never user id, order id, raw URI). HTTP server tags: `uri`, `method`, `status`, `outcome`;
  `quarkus.micrometer.binder.http-server.max-uri-tags` default 100, `ignore-patterns` **[summary]**.
- API: inject `MeterRegistry`; `Counter` (only increases), `Timer`, `Gauge` ("only use a gauge when you can't use something else"); `@Counted(value="x", extraTags={...})`, `@Timed`, `@MeterTag` **[summary]**. Meters are created lazily.
- Customize with `MeterFilter`, `MeterRegistryCustomizer`, `HttpServerMetricsTagsContributor`/`HttpClientMetricsTagsContributor`.
- Virtual-thread binder: `quarkus.micrometer.binder.virtual-threads.enabled` **[summary]**. Fault-tolerance metrics are emitted automatically (see `quarkus-fault-tolerance`).

## 3. Tracing / metrics / logs — OpenTelemetry (`quarkus-opentelemetry`)

- "The use of the OpenTelemetry Agent is not needed nor recommended" — Quarkus instruments its own extensions.
- **Tracing is on by default; metrics and logs are tech preview and off**: `quarkus.otel.metrics.enabled=true`, `quarkus.otel.logs.enabled=true` (build time).
- Exporter: OTLP gRPC default `http://localhost:4317`:
  ```properties
  quarkus.application.name=claims-service            # becomes service.name
  quarkus.otel.exporter.otlp.endpoint=http://otel-collector:4317
  quarkus.otel.exporter.otlp.headers=authorization=Bearer ${OTLP_TOKEN}   # never commit tokens
  quarkus.otel.traces.sampler=parentbased_traceidratio    # default parentbased_always_on [summary]
  quarkus.otel.traces.sampler.arg=0.1                      # default 1.0 [summary]
  ```
  Switches: `quarkus.otel.enabled`, `quarkus.otel.sdk.disabled` (runtime), `quarkus.otel.traces.exporter` (`otlp|cdi|none`), `quarkus.otel.simple=true` (export immediately, for serverless),
  `quarkus.otel.resource.attributes=k=v,...`. Propagators default `tracecontext,baggage` (W3C) **[summary]**. `/q` endpoints are not traced by default **[summary]**.
- `@WithSpan`, `@SpanAttribute`, `@AddingSpanAttributes` (`io.opentelemetry.instrumentation.annotations`) **[summary]**; JDBC tracing needs `quarkus.datasource.jdbc.telemetry=true` **[summary]**.
- OTel HTTP semantic conventions: span name `{method} {http.route}` (low-cardinality; **never default to the raw URI path**); SERVER spans for inbound, CLIENT for outbound;
  server 4xx leaves status unset, 5xx = Error; **redact** sensitive query params/credentials in `url.full`.
- Kubernetes: with `quarkus-kubernetes`, `QUARKUS_OTEL_K8S_RESOURCE_*` env vars are added automatically.

## 4. Logging (JBoss Log Manager)

- Use `org.jboss.logging.Logger`, `@Inject Logger`, or static `io.quarkus.logging.Log` (application classes only; rewritten at build time). Don't use unbounded dynamic logger names.
- Levels: OFF FATAL ERROR WARN INFO DEBUG TRACE ALL. `quarkus.log.level` (INFO), `quarkus.log.min-level` (DEBUG, **build-time** — lower it to use TRACE).
- Per category (quote the name): `quarkus.log.category."org.hibernate".level=DEBUG`.
- **Structured logs:** `quarkus-logging-json`; `quarkus.log.console.json.log-format=default|ecs|gcp`; `%dev.quarkus.log.console.json.enabled=false` for readable dev logs **[summary]**.
- **Trace id in logs** (from the OTel guide):
  `quarkus.log.console.format=%d{HH:mm:ss} %-5p traceId=%X{traceId}, parentId=%X{parentId}, spanId=%X{spanId}, sampled=%X{sampled} [%c{2.}] (%t) %s%e%n`
- MDC with `%X{key}` (JSON puts MDC under `mdc`). Put a **correlation/request id** in MDC at the edge and propagate it downstream.
- Never log secrets/PII; sanitize log input (OWASP); log security events before/after; log retries/fallbacks as WARN.

## 5. What to measure — the four golden signals (Google SRE)

| Signal | Measure |
|---|---|
| **Latency** | Time to serve a request — track **error latency separately** from success latency |
| **Traffic** | Demand: requests/s |
| **Errors** | Rate of failing requests: explicit (500), implicit (200 with wrong content), by policy (too slow) |
| **Saturation** | How "full": most constrained resource (DB pool, threads, queue depth, memory) — systems degrade before 100% |

Alert on symptoms users feel (latency/error SLO burn), not on every cause. Add saturation of your own limits: Agroal pool usage, worker-thread queue,
Kafka consumer lag, circuit-breaker open count, bulkhead rejections.

## 6. Checklist

- [ ] `/q/health/live` shallow; `/q/health/ready` checks required dependencies with timeouts; management interface separated.
- [ ] Metrics exposed, tags low-cardinality, golden signals + saturation of own pools charted.
- [ ] `quarkus.application.name` set; OTLP endpoint per environment (`%prod.`); sampler tuned (ratio) for prod.
- [ ] JSON logs in prod, traceId in every line, correlation id propagated, no PII/secrets.
- [ ] Probes in Kubernetes mapped: liveness -> `/q/health/live`, readiness -> `/q/health/ready` (see `kubernetes-for-developers`).
- [ ] Alerts on SLO symptoms, not noise.

Related: `quarkus-config-profiles`, `quarkus-fault-tolerance`, `kubernetes-for-developers`.

## Sources

- https://quarkus.io/guides/smallrye-health, /telemetry-micrometer, /opentelemetry, /opentelemetry-tracing, /logging
- https://opentelemetry.io/docs/concepts/signals/ and https://opentelemetry.io/docs/specs/semconv/http/http-spans/
- https://sre.google/sre-book/monitoring-distributed-systems/
- https://learn.microsoft.com/en-us/azure/architecture/patterns/health-endpoint-monitoring
