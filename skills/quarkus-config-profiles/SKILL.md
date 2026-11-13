---
name: quarkus-config-profiles
description: Use whenever working with Quarkus configuration — application.properties, profiles (%dev, %test, %prod, custom profiles, quarkus.profile / QUARKUS_PROFILE), environment variables and .env, config source precedence (ordinals), @ConfigProperty vs @ConfigMapping, build-time vs runtime properties, per-environment overrides, externalizing secrets (12-factor), Dev Services vs prod connection settings, quarkus.config.locations, or reviewing a service whose config is hard-coded or differs between environments.
---

# Quarkus Configuration and Profiles

**Sources:** Quarkus "Configuration reference", "Config mappings", "Config" guides (v3.40), Dev Services guide, Twelve-Factor App (config).
**[summary]** = extracted part of a page — verify. **Not found / not verified:** `@ConfigRoot` (absent from the guides read — don't rely on it),
`smallrye-config-source-keystore` (not found; only `smallrye-config-crypto` named for `${handler::value}` expressions), the separate config-secrets guide.

## 1. Source precedence (highest ordinal wins, per property)

| Ordinal | Source |
|---|---|
| 400 | System properties (`-Dname=value`) |
| 300 | Environment variables |
| 295 | `.env` in working directory (**dev mode only**; don't commit; not visible to `System.getenv`) |
| 260 | `$PWD/config/application.properties` |
| 250 | `application.properties` on the classpath |
| 100 | `META-INF/microprofile-config.properties` |

- Env var lookup: exact name, then non-alphanumerics -> `_`, then uppercase: `quarkus.datasource.password` -> `QUARKUS_DATASOURCE_PASSWORD`.
- `quarkus.config.locations` takes a comma-separated list of `file:`, `classpath`, `jar:`, `http:` URIs; they inherit the ordinal of the source that set the property (`config_ordinal` overrides).

## 2. Profiles

- Built-in: **`dev`** (`quarkus:dev`), **`test`** (tests), **`prod`** (default when running the built app). Select with `quarkus.profile` / **`QUARKUS_PROFILE`**
  (comma-separated; the **first listed is checked first**) **[summary]**.
- Per-property prefix: `%dev.quarkus.log.level=DEBUG`, `%prod.quarkus.datasource.jdbc.url=...`. Profile files: `application-{profile}.properties` **[summary]**.
- `quarkus.config.profile.parent` defines a fallback profile (e.g. `staging` -> `prod`) **[summary]**.
- Custom profiles (`staging`, `uat`) are valid; keep the set small.

```properties
# application.properties — defaults safe for every env; real values via env vars
quarkus.application.name=claims-service
quarkus.datasource.db-kind=postgresql
%prod.quarkus.datasource.jdbc.url=${DB_URL}
%prod.quarkus.datasource.username=${DB_USER}
%prod.quarkus.datasource.password=${DB_PASSWORD}     # injected by the platform, never committed
%dev.quarkus.log.console.json.enabled=false
```
**Dev Services:** enabled by default when you don't configure a connection (DB, Kafka, Redis, Keycloak...), in **dev and test only**. Put real connection settings under `%prod.`
(the guide's recommendation); don't accidentally set a global URL that disables Dev Services for dev/test. Disable all: `quarkus.devservices.enabled=false`.

## 3. Twelve-Factor rules (12factor.net/config)

- Store config in **environment variables**; strict separation of config from code.
- Don't group config into named "environments" in code; each variable is managed independently per deploy.
- Quarkus implication: profiles are for Quarkus lifecycle (dev/test/prod); **per-deployment values (hosts, credentials, endpoints) come from env vars/secret store**,
  not from `application-staging.properties` checked into git with real values.

## 4. `@ConfigProperty` vs `@ConfigMapping`

- `@ConfigProperty(name="x", defaultValue="y")` for a single value; `Optional<T>` for optional; **missing value without default fails startup**.
- The **`quarkus.` prefix is reserved** — don't use it for application keys.
- **Prefer `@ConfigMapping`** for groups (the guide recommends it):
```java
@ConfigMapping(prefix = "claims")
public interface ClaimsConfig {
  @WithDefault("30") int maxDocuments();
  Optional<String> sponsorCode();
  Retry retry();                                  // nested group => claims.retry.*
  interface Retry {
    @WithDefault("3") int attempts();
    @WithName("backoff-ms") @WithDefault("200") long backoff();
  }
}
```
- Rules: public interface; kebab-case names by default (`theHost` -> `the-host`; `VERBATIM`/`SNAKE_CASE` via `namingStrategy`); missing value throws
  `NoSuchElementException` unless `Optional*`; `@WithName`, `@WithParentName`, `@WithDefault`, `@WithConverter`; `List` keeps order, `Set` doesn't; `Map<String,Group>` for dynamic keys.
- **Bean Validation works on mappings** when `quarkus-hibernate-validator` is present (`@Size`, `@Max`, `@NotBlank`) — fail fast at startup on bad config.
- `@StaticInitSafe` needed to use a mapping during static init. Inject with `@Inject`; outside CDI use `ConfigProvider.getConfig().unwrap(SmallRyeConfig.class).getConfigMapping(X.class)`.

## 5. Build-time vs runtime

- Build-time properties (lock icon in the all-config reference) are **fixed at build** — changing the env var at runtime won't change them (e.g. `quarkus.log.min-level`,
  `quarkus.management.enabled`, `quarkus.otel.metrics.enabled`, `quarkus.http.ssl`-type switches vary — check each). Runtime ones are overridable per deploy.
- Build once, deploy the same artifact to every environment; supply only runtime config per environment. `quarkus.config.fixed-at-build-time` freezes everything **[summary]**.
- Native image: build-time properties matter even more; verify with a native smoke test.

## 6. Secrets

- Never in git, images or `application.properties`. Use env vars / orchestrator secrets / a vault; `.env` only for local dev and git-ignored.
- Expression syntax `${handler::value}` exists with `smallrye-config-crypto` **[summary]**. Public certs/keys may be bundled (see `quarkus-extension-verification`).
- Redact secrets in logs and error bodies (`quarkus-security-standards`).

## 7. Test profiles

- Tests run in the `test` profile (`%test.` keys). Per-test overrides: `QuarkusTestProfile.getConfigOverrides()` with `@TestProfile` (see `quarkus-testing-standards`).
- `@QuarkusIntegrationTest` runs the built artifact with profile `prod` by default (`quarkus.test.integration-test-profile`) **[summary]**.

## 8. Checklist

- [ ] Same artifact in all environments; no environment-specific builds.
- [ ] `%prod.` holds placeholders only (`${ENV}`); real values from the platform.
- [ ] Config grouped in `@ConfigMapping` with `@WithDefault`, validated by Bean Validation.
- [ ] No `quarkus.`-prefixed custom keys; no secrets in git; `.env` git-ignored.
- [ ] Dev Services config doesn't leak into prod; prod connections explicit.
- [ ] Build-time vs runtime properties understood for each changed key.
- [ ] Timeouts, pool sizes, retry counts, thresholds are configurable (`quarkus-fault-tolerance`, Agroal `max-size` default 50).

Related: `quarkus-security-standards`, `quarkus-observability`, `quarkus-extension-verification`, `java-devops-toolchain`, `kubernetes-for-developers`.

## Sources

- https://quarkus.io/guides/config-reference, /config-mappings, /config, /dev-services
- https://12factor.net/config
