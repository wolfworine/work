---
name: quarkus-security-standards
description: Use whenever securing a Quarkus REST service — OIDC bearer-token authentication (quarkus-oidc), SmallRye JWT, roles and permissions (@RolesAllowed, @PermitAll, @Authenticated, @PermissionsAllowed), deny-by-default (quarkus.security.jaxrs.deny-unannotated-endpoints), HTTP path permissions, TLS and HTTP-to-HTTPS enforcement, CORS, request size limits, secrets handling, JWT validation rules (iss/aud/exp, alg none), OWASP REST Security Cheat Sheet and OWASP API Security Top 10 (2023), or reviewing endpoints for broken object-level authorization.
---

# Quarkus Security Standards

**Sources:** Quarkus security guides (OIDC bearer token, authorization reference, SmallRye JWT, HTTP reference — v3.40), OWASP REST Security
Cheat Sheet, OWASP API Security Top 10 2023. **[summary]** = extracted part of a page — verify. **Not verified:** Dev Services for
Keycloak/OIDC (page 404), `quarkus.http.cors.*` property names/defaults (CORS guide not fetched), exact coordinates of `quarkus-oidc`.

## 1. Authentication

**OIDC bearer tokens (`quarkus-oidc`)** — service (resource server) validating tokens from Keycloak/IdP:
```properties
quarkus.oidc.auth-server-url=https://idp.example.com/realms/acme
quarkus.oidc.application-type=service            # in the guide's examples (no default stated)
quarkus.oidc.token.audience=claims-api           # verify aud  [summary]
quarkus.oidc.token.issuer=https://idp.example.com/realms/acme   # verify iss [summary]
quarkus.oidc.roles.role-claim-path=realm_access/roles           # default is the 'groups' claim
```
- Roles: default `groups` claim; Keycloak `realm_access/roles` and `resource_access/<client-id>/roles`; opaque tokens use `scope`.
- JWKs fetched by `kid`; `quarkus.oidc.token.forced-jwk-refresh-interval` default 10 min; falls back to introspection
  (`allow-jwt-introspection`, `allow-opaque-token-introspection`, `require-jwt-introspection-only`).
- Inject `JsonWebToken` to read claims. Scope-based access: `@PermissionsAllowed` (`io.quarkus.security.PermissionsAllowed`).

**SmallRye JWT (`quarkus-smallrye-jwt`)** — you verify with a configured key:
`mp.jwt.verify.publickey.location=publicKey.pem`, `mp.jwt.verify.issuer=https://example.com/issuer`; in native add
`quarkus.native.resources.includes=publicKey.pem`. MP-JWT RBAC requires **RSA-256-signed** JWTs; `groups` claim maps to roles. Other **[summary]**:
`smallrye.jwt.path.groups`, `smallrye.jwt.token.schemes` (default `Bearer`), `smallrye.jwt.require.named-principal` (default true).

## 2. Authorization — deny by default

- Annotations: `@DenyAll`, `@PermitAll`, `@RolesAllowed("admin")`, `@Authenticated` (= any authenticated user), `@PermissionsAllowed` **[summary]**.
- **`quarkus.security.jaxrs.deny-unannotated-endpoints=true`** (default **false**) makes every unannotated endpoint `@DenyAll` — turn it on so a forgotten
  annotation can't expose an endpoint. The guide recommends annotations (+ this or `default-roles-allowed`) over path rules.
- Path-based policies (`quarkus.http.auth.permission.<name>.paths|policy|methods`, policies `deny|permit|authenticated`, roles policy
  `quarkus.http.auth.policy.<name>.roles-allowed`): longest path wins; method-specific beats method-less; `*` suffix = sub-paths. **HTTP path permissions
  are evaluated before annotations**, so `@PermitAll` cannot override an HTTP-level deny **[summary]**.
- Custom policy: CDI bean implementing `HttpSecurityPolicy`; bind with `@AuthorizationPolicy(name=...)`.
- **Object-level checks are your code:** annotations say *which role*, not *which record*. Always verify the caller owns/can access the specific resource (OWASP API1).

## 3. Transport and HTTP hardening

- HTTPS only; mutual TLS for privileged services (OWASP). Quarkus: central TLS registry `quarkus.tls.key-store.pem.0.cert|key` or `quarkus.tls.key-store.p12.path|password`;
  `quarkus.http.insecure-requests=disabled` rejects plain HTTP (`enabled` default, `redirect` also exists **[summary]**); `quarkus.http.tls-configuration-name` selects a named config.
  `quarkus.http.ssl-port` default 8443 **[summary]**. Don't keep the default keystore password.
- Limits: `quarkus.http.limits.max-body-size` (default 10240K **[summary]**) — reject oversized requests with 413.
- CORS: **disable unless needed; list specific origins** (OWASP). Configure with `quarkus.http.cors.*` (verify names in the Quarkus CORS guide).
- Management endpoints (`/q`) on a separate port/host, not internet-exposed (`quarkus.management.enabled=true`).
- Security headers for browser-consumed APIs: `Cache-Control: no-store`, `Content-Security-Policy: frame-ancestors 'none'`, correct `Content-Type`,
  `Strict-Transport-Security`, `X-Content-Type-Options: nosniff`, `X-Frame-Options: DENY` (OWASP).
- Never put credentials/tokens/API keys in URLs.

## 4. JWT validation rules (OWASP REST Cheat Sheet)

- Signature (or MAC) required; **reject `alg: none`**; the relying party chooses the algorithm from its own config, not the token header.
- Validate **`iss`, `aud`, `exp`** (and `nbf` if profile requires); deny-list `jti` on logout when you support revocation.
- API keys are not the sole protection for sensitive resources. Rate-limit and return **429**.
- Use the right statuses: 401 missing/bad authentication; 403 authenticated but not authorized; 405 method not allowed (allowlist methods); 413; 415; 406.

## 5. Secrets

Never commit secrets; supply via environment/secret store per environment (`quarkus-config-profiles`, 12-factor). The OTLP header token, DB password,
client secrets, signing keys are config, not code. Public keys can be bundled as resources. (See `quarkus-extension-verification`.)

## 6. OWASP API Security Top 10 (2023) — review against it

API1 Broken Object Level Authorization · API2 Broken Authentication · API3 Broken Object Property Level Authorization (DTO allowlists, no over-posting) ·
API4 Unrestricted Resource Consumption (paging max, body limits, rate limits, timeouts) · API5 Broken Function Level Authorization · API6 Unrestricted Access to
Sensitive Business Flows · API7 Server Side Request Forgery · API8 Security Misconfiguration · API9 Improper Inventory Management (document & retire old versions) ·
API10 Unsafe Consumption of APIs (validate third-party responses; timeouts).

## 7. Errors and logging

Generic error messages, no stack traces (RFC 9457 problem details, see `quarkus-rest-api-standards`); audit log before/after security events; sanitize logged input;
enforce workflow state transitions on the server (out-of-order API execution).

## 8. Checklist

- [ ] `deny-unannotated-endpoints=true`; every endpoint explicitly `@PermitAll`/`@RolesAllowed`/`@Authenticated`.
- [ ] iss/aud/exp validated; roles claim path configured for your IdP; no `alg none`.
- [ ] Object-level authorization tests (user A cannot read user B's record) exist.
- [ ] TLS enforced; `insecure-requests=disabled`; CORS closed or specific; body size limited.
- [ ] Management interface isolated; secrets from the environment; no secrets in URLs/logs.
- [ ] DTO allowlists; rate limiting/429; paging max.
- [ ] Security tests via the Quarkus test security support (see `quarkus-testing-standards`).

Related: `quarkus-rest-api-standards`, `quarkus-config-profiles`, `microservices-antipatterns`.

## Sources

- https://quarkus.io/guides/security-oidc-bearer-token-authentication, /security-authorize-web-endpoints-reference, /security-jwt, /http-reference
- https://cheatsheetseries.owasp.org/cheatsheets/REST_Security_Cheat_Sheet.html
- https://api-security.owasp.org/editions/2023/en/0x11-t10
