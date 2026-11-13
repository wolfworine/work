---
name: redis-oss-elasticache
description: Use whenever working with Amazon ElastiCache for Redis OSS 7.1 (node-based or Serverless) — creating or modifying a cluster/replication group, choosing the engine version string, deciding between cluster mode enabled/disabled, configuring TLS/AUTH/ACL (RBAC), parameter groups, eviction (maxmemory-policy), sizing nodes, using Redis Functions or sharded Pub/Sub, connecting from a Java/Quarkus/Spring client (Lettuce, Quarkus Redis client), planning an upgrade or a move to Valkey, or checking whether a Redis feature/command is available on ElastiCache. Not for self-hosted Redis or Valkey-specific features.
---

# ElastiCache for Redis OSS 7.1 (AWS)

Target: **ElastiCache for Redis OSS 7.1**, the newest Redis OSS engine AWS offers.
Facts below were checked against the AWS ElastiCache docs (engine-versions page) and
endoflife.date on 2026-11-13. Items marked **(verify)** were NOT confirmed in those sources.

## 1. Versions: what is and isn't available

| Fact | Source status |
|---|---|
| ElastiCache supports Redis OSS 7.1, 7.0, 6.2, 6.0, 5.0.6, 4.0.10 (5.0.0–5.0.5 deprecated; 3.x/2.x past EOL) | AWS docs |
| **7.1 is the highest Redis OSS version.** "7.2 and higher versions are Valkey only" | endoflife.date |
| 7.1 is API-compatible with Redis OSS 7.0 | AWS docs |
| Newer engines (Valkey 7.2.6, 8.x, 9.x) exist as a separate engine | AWS docs |
| Cross-engine upgrade Redis OSS -> Valkey 7.2 is supported; downgrade Valkey 7.2 -> Redis OSS 7.1 is also supported | endoflife.date |

Consequences:
- You can never move **within Redis OSS** past 7.1. Any future engine upgrade means a
  cross-engine move to Valkey (plan it; test client compatibility first).
- Do not assume "Redis 7.2" features exist on ElastiCache Redis OSS. They exist in Valkey.
- No standard-support/EOL date for Redis OSS 7.x was found in the sources **(verify** in the
  AWS "Extended Support and End of Life schedule" table before promising a date**)**.
  For reference: Redis OSS 4 and 5 standard support ended 2026-01-31; Redis OSS 6 ends
  2027-01-31; after standard support ends, paid Extended Support applies (up to 3 years).

## 2. What 7.1 adds (and what it doesn't)

7.1 is a **performance release** — no new commands or data types are listed.
- Enhanced I/O threads also handle the presentation layer (read + parse client input).
- Better memory access pattern (interleaved data-structure operations).
- AWS claim: up to ~100% more throughput and ~50% lower P99 vs 7.0; >1M RPS/node on
  Graviton3 R7g.4xlarge+. These are AWS benchmark claims, not guarantees.
- Only active on nodes with **>= 8 physical cores** (2xlarge on Graviton, 4xlarge on x86).
  On smaller nodes you get no 7.1 speed-up, so don't size down and expect the numbers.
- Needs no client changes.

Inherited from 7.0 (still true on 7.1):
- **Redis Functions**: store server-side Lua functions on the cluster instead of re-sending scripts.
- **ACL improvements**: multiple permission sets on specific keys/keyspaces per user.
- **Sharded Pub/Sub** (`SPUBLISH`/`SSUBSCRIBE`): only meaningful in **Cluster Mode Enabled**;
  a channel is bound to a shard, so it scales with shards instead of broadcasting cluster-wide.
- **Enhanced I/O multiplexing**.

Inherited from 6.x: RBAC users/groups, client-side caching, TLS performance work, JSON
support from 6.2.6 (**verify** JSON availability on 7.1 before relying on it).

## 3. Specifying the engine version

- Use the **major.minor only**: `7.1`. ElastiCache picks the patch version.
  (The docs state the "reject specific patch version" rule for 6.0 and the
  "major.minor, preferred patch auto-applied" rule for 6.2; applying it to 7.1 is the
  consistent convention but is **(verify)** in the 7.x docs.)
- The API reports `EngineVersion` as major.minor; the real patch is in
  `CacheEngineVersionDescription` (`describe-cache-engine-versions`).
- Parameter group family for 7.x is `redis7` **(verify** with
  `aws elasticache describe-cache-parameter-group-families`**)**. Use a custom parameter
  group; default groups can't be edited.

```bash
aws elasticache describe-cache-engine-versions --engine redis \
  --query "CacheEngineVersions[].[EngineVersion,CacheParameterGroupFamily]"
```

## 4. Cluster mode and topology

- **Cluster Mode Disabled (CMD)**: 1 shard (1 primary + up to 5 replicas). Simple, any
  multi-key command works. Scale up vertically or add read replicas.
- **Cluster Mode Enabled (CME)**: data split over slots/shards. Required for sharded
  Pub/Sub and horizontal write scaling. Multi-key commands, `MULTI/EXEC`, Lua/Functions
  must touch keys in the **same slot** -> use hash tags: `{user:42}:profile`, `{user:42}:cart`.
- Use Multi-AZ with automatic failover for production. Clients must follow the
  primary/configuration endpoint, never hard-code a node.
- Serverless exists and supports Redis OSS 7.1 and earlier; there you don't pick node sizes.

## 5. Security checklist

1. Put the cluster in private subnets; security group allows 6379 only from app SGs.
2. Enable **in-transit encryption (TLS)** and at-rest encryption at creation — in-transit
   encryption on an existing cluster is a migration concern, decide up front.
3. Authenticate with **RBAC (users + user groups)** rather than a shared AUTH token; give
   each service a user with least-privilege command/key ACLs.
4. Never put the password/token in `application.properties` committed to git — read it from
   AWS Secrets Manager (see `quarkus-extension-verification` for secrets-as-config).
5. Clients must use `rediss://` (TLS) when in-transit encryption is on.

## 6. Memory and eviction

- Default eviction is `volatile-lru`: keys **without TTL are never evicted**; if memory fills
  with TTL-less keys, writes fail with `OOM command not allowed`. For a pure cache use
  `allkeys-lru` (or `allkeys-lfu`) **and** set TTLs anyway. For a primary store use `noeviction`
  and alarm on memory.
- Set `reserved-memory-percent` (default ~25%) generously if you use snapshots/backups or
  heavy replication — fork + copy-on-write can need headroom.
- Alarm on CloudWatch: `DatabaseMemoryUsagePercentage`, `EngineCPUUtilization`, `Evictions`,
  `CurrConnections`, `ReplicationLag`. `EngineCPUUtilization` (single main thread) matters more
  than host `CPUUtilization`.

## 7. Java client guidance (Lettuce / Quarkus / Spring)

- Lettuce is the default in Spring Data Redis and what Quarkus Redis builds on; one
  shared connection is thread-safe — don't open one per request.
- Cluster mode: enable **topology refresh** (periodic + adaptive on `MOVED`/`ASK`) so failover
  and resharding don't leave stale routing.
- Set explicit **timeouts** (connect ~2s, command ~1s) and a retry/backoff policy; a hung
  cache must not hang the service. Wrap the cache behind a resilience boundary (see
  `enterprise-java-design-patterns`: cache-aside + circuit breaker, fall back to the DB).
- TLS: use `rediss://host:6379`; the ElastiCache cert chains to a public CA, so the default
  JVM trust store works.
- Pipeline/batch with the async/reactive API instead of many sequential round trips.

## 8. Key design and usage patterns

- Namespaced keys with a version: `app:v1:entity:{id}`; always set TTL on cache entries
  and add jitter to avoid synchronized expiry (stampede).
- Avoid big values (>~100 KB) and big collections; no `KEYS *` — use `SCAN`.
- Distributed lock: `SET key token NX PX ttl` + release with a compare-and-delete script/Function.
  It is not a strict-correctness lock under failover; use fencing tokens if correctness matters.
- Cache-aside is the default pattern; write-through only when staleness is unacceptable.

## 9. Upgrade checklist (e.g. 6.x -> 7.1, or 7.1 -> Valkey)

1. Read the engine-versions page for breaking changes between your current and target version.
2. Create a snapshot; test the upgrade on a restored copy first.
3. Replication groups upgrade with little downtime (failover); check clients re-resolve endpoints.
4. Redis OSS 6 standard support ends 2027-01-31 — 7.1 is the natural landing spot; beyond it,
   plan the Valkey move.
5. After any upgrade re-run your client integration tests (TLS, RBAC, cluster slots).

## Sources

- AWS docs: https://docs.aws.amazon.com/AmazonElastiCache/latest/dg/engine-versions.html
- endoflife.date: https://endoflife.date/amazon-elasticache-redis (page last updated 2026-05-01)
- AWS re:Post thread (versions compatible with ElastiCache for Redis): could not be read (HTTP 403);
  nothing in this skill relies on it.

Sections 4–8 combine documented facts with standard Redis/ElastiCache operating practice;
where a figure or parameter name matters for a production change, confirm it in the current AWS docs.
