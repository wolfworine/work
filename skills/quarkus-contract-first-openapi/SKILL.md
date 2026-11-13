---
name: quarkus-contract-first-openapi
description: Use whenever practicing contract-first / API-first development in Quarkus — writing the OpenAPI document before the code, generating JAX-RS server interfaces and models from it (Quarkiverse OpenAPI Generator, Apicurio or OpenAPITools), generating REST clients, serving a static openapi.yml with SmallRye OpenAPI, merging static and annotation-based specs, choosing OpenAPI 3.0 vs 3.1, keeping the contract and implementation in sync, versioning and backward-compatible evolution, consumer-driven contract tests, or reviewing an API where code and spec have drifted.
---

# Contract-First (API-First) in Quarkus

**Sources:** Quarkus "OpenAPI & Swagger UI" guide (SmallRye OpenAPI, 3.40), Quarkiverse OpenAPI Generator docs
(index, server, apicurio, openapitools pages), OpenAPI Specification site, RFC 9457, Microsoft Azure Architecture Center
(API design), microservices.io pattern index. **[summary]** = extracted part of a page — verify.
**Not read:** the generator's *client* page, `quarkus-openapi-generator` minimum Quarkus version (not stated),
CORS/Dev Services pages. The generator's **server** support is labeled **Preview (Apicurio) / Experimental (OpenAPITools)** in its own docs —
treat generated code as something you review, not blindly trust.

## 1. The methodology

1. **Design the contract first** (OpenAPI YAML) with the consumers: resources, schemas, status codes, error model (RFC 9457),
   pagination, security schemes, idempotency headers. Review it like code (PR).
2. **Generate** server interfaces + models (or client stubs) from the contract — the contract is the single source of truth.
3. **Implement** the generated interface in a CDI bean; business logic only. Never edit generated code.
4. **Verify** the running service against the contract (contract tests, schema validation in tests).
5. **Evolve** compatibly; breaking change => new version.
Why: consumers and providers work in parallel, docs never drift, bad API shapes are caught before code exists.

## 2. Option A — Quarkiverse OpenAPI Generator (server side)

Dependencies (names from the docs): `io.quarkiverse.openapi.generator:quarkus-openapi-generator-server` and
`io.quarkus:quarkus-smallrye-openapi` (generated resources carry MicroProfile OpenAPI annotations by default).
`quarkus-maven-plugin` goals: `build`, `generate-code`, `generate-code-tests`. Generate with `mvn compile` (or `quarkus:generate-code`).

```properties
# SERVER namespace is quarkus.openapi.generator.server.*  (note "openapi.generator", not "openapi-generator")
quarkus.openapi.generator.server.use=apicurio            # default; or openapitools
quarkus.openapi.generator.server.input-base-dir=src/main/resources/openapi   # default
quarkus.openapi.generator.server.spec=claims.yaml        # omit => all files in the dir are auto-discovered
quarkus.openapi.generator.server.base-package=org.acme   # NOTE: docs conflict (apicurio prose says io.apicurio.api) — set it explicitly
quarkus.openapi.generator.server.use-bean-validation=true   # default false
quarkus.openapi.generator.server.use-reactive=false         # default false
quarkus.openapi.generator.server.use-rest-response=false    # openapitools only
```
Other server properties: `use-builder`, `keep-local-refs` (apicurio), `skip-if-unchanged`, `include`/`exclude`, `operation-ids."<id>".return-type`.
Corrections to common assumptions: `quarkus.openapi-generator.codegen.spec.<file>.*` is the **client** namespace; the properties
`generate-interfaces`, `beans-reflection`, `type-mappings`, `additional-api-type-annotations` were **not found** in the server docs.

What you get: **one JAX-RS resource interface per tag** (e.g. `PetResource`) + model beans. Output dir: Apicurio -> `target/generated-sources/jaxrs`;
OpenAPITools -> `target/generated-sources/quarkus-openapi-generator-server`.

```java
@ApplicationScoped
public class ClaimsResourceImpl implements ClaimsResource {   // generated interface
  @Override public Claim registerClaim(ClaimRequest data) { return service.register(data); }
}
```
OpenAPITools + `use-reactive` returns `Uni<...>`; with `use-rest-response` -> `RestResponse<T>`/`Uni<RestResponse<T>>`
(per-operation `x-use-rest-response: true`, `x-return-type`).
Apicurio customization is by spec extensions: root `x-codegen` (`bean-annotations`, `contextRoot`, `suppress-date-time-formatting`),
per schema `x-codegen-package`, `x-codegen-annotations`, `x-codegen-type`, `x-codegen-extendsClass`, per operation `x-codegen-async` **[summary]**.
Pick **one** generator (`use=`); Apicurio is the documented default.

## 3. Option B — Static spec served by SmallRye OpenAPI (no code generation)

- Extension `quarkus-smallrye-openapi`; endpoint **`/q/openapi`** (`?format=json`); path via `quarkus.smallrye-openapi.path`.
- Put the hand-written spec in `META-INF/openapi.yml` (`.yaml`/`.json` also work) **[summary]**; by default it is **merged** with the
  model scanned from annotations. `mp.openapi.scan.disable=true` serves **only** the static document (pure contract-first);
  `quarkus.smallrye-openapi.ignore-static-document` (default false) does the opposite **[summary]**.
- Global info: `@OpenAPIDefinition` on an `Application` subclass or `quarkus.smallrye-openapi.info-title|info-version|...`.
- `quarkus.smallrye-openapi.store-schema-directory` writes the final schema at build (use it to diff against the contract in CI) **[summary]**.
- Swagger UI at `/q/swagger-ui` only in dev/test; `quarkus.swagger-ui.always-include=true` to ship it (default false) **[summary]**.
- `quarkus.smallrye-openapi.security-scheme` (`jwt`, `oidc`, ...) adds a security scheme **[summary]**.
- Filters: `@OpenApiFilter(stages = BUILD|RUNTIME_STARTUP|RUNTIME_PER_REQUEST)` on an `OASFilter`.
- The OAS site lists **3.2.1** as latest (2026-09-10), but SmallRye generates **3.1.0** by default — confirm your generator/tooling
  supports the version you write. In 3.1 `format` no longer affects content encoding (`contentEncoding`/`contentMediaType` instead) **[summary]**.

## 4. Contract content standards

- Error model: `application/problem+json` (RFC 9457) schema shared by all operations; document 400/401/403/404/409/422/429/5xx.
- Document **idempotency** (`Idempotency-Key` header, which operations are retry-safe) and pagination (`limit`/`offset`/cursor + max).
- Use `operationId` on every operation (stable names for generated code); one tag per resource.
- Schemas: required fields, formats, `minLength/maximum`, enums — they become Bean Validation when `use-bean-validation=true`.
- Never expose DB structure; model the domain (Microsoft). Separate request and response schemas.
- Security: declare schemes and per-operation requirements; contract says *who*, code enforces (see `quarkus-security-standards`).

## 5. Evolution rules (Microsoft API design; microservices.io)

- Additive changes only on the same version: new optional fields, new endpoints. **Don't remove or rename fields, tighten validation, or change types.**
- Breaking change => new version (URI, header or media type), keep old, deprecate quickly.
- Consumers should be tolerant of supplier changes (Fowler); apply a **consumer-driven contract test** pattern (listed in the microservices.io index)
  so providers learn what consumers rely on. (Tooling choice not researched.)
- Run a spec diff / schema comparison in CI against the last released contract (tool choice not researched — pick one and gate the build).

## 6. Review checklist

- [ ] Contract reviewed and merged before implementation; consumers signed off.
- [ ] Generated code in `target/`, not committed; implementation only implements interfaces.
- [ ] Generator choice explicit (`use=`), base package explicit, bean validation on.
- [ ] `mp.openapi.scan.disable=true` if the static spec is authoritative; otherwise reviewed merge result.
- [ ] CI compares `/q/openapi` (or stored schema) with the contract and fails on drift or breaking changes.
- [ ] Error model, idempotency, pagination, security declared in the contract.
- [ ] Preview/Experimental generator status acknowledged in the ADR.

Related: `quarkus-rest-api-standards`, `idempotency-design`, `quarkus-security-standards`, `quarkus-testing-standards`.

## Sources

- https://quarkus.io/guides/openapi-swaggerui
- https://docs.quarkiverse.io/quarkus-openapi-generator/dev/index.html, .../server.html, .../apicurio.html, .../openapitools.html
- https://spec.openapis.org/oas/latest.html, https://www.rfc-editor.org/rfc/rfc9457.html
- https://learn.microsoft.com/en-us/azure/architecture/best-practices/api-design
- https://microservices.io/patterns/index.html
