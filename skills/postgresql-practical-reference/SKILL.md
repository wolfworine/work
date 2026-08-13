---
name: postgresql-practical-reference
description: Use whenever designing a Postgres schema or index, diagnosing a slow query with EXPLAIN/EXPLAIN ANALYZE, choosing a column data type (TIMESTAMPTZ vs TIMESTAMP, NUMERIC vs FLOAT/MONEY for money, TEXT vs VARCHAR(n)), working with JSONB columns and their indexes (jsonb_ops vs jsonb_path_ops, containment queries), or reasoning about transaction isolation levels (read committed default) and connection pooling / max_connections in a Postgres-backed service.
---

# PostgreSQL Practical Reference

This distills the topics a backend developer/agent hits constantly — indexing, `EXPLAIN`, JSONB,
data-type gotchas, transaction isolation, connection pooling, table partitioning, full text search,
backup/restore, and replication — from the **complete official PostgreSQL 18 manual** (the first pass
was a curated subset built from a sampled PDF; this version was expanded against the full manual
text, targeted at each topic's own chapter). It still isn't exhaustive documentation — full
admin/tuning, PL/pgSQL, and internals are out of scope. When a question falls outside these topics,
consult the actual PostgreSQL documentation instead of assuming this file covers it. Version
reference: PostgreSQL 18. For SQL that isn't Postgres-specific (JOINs, subqueries, window functions,
transactions as a language concept), see the companion skill `sql-fundamentals` instead — this file
only covers what's specific to *how Postgres* implements things.

## Indexing

**Default to B-tree; reach for GIN/GiST only when the data or operator needs it.**

- **B-tree** (the `CREATE INDEX` default) handles `<  <=  =  >=  >`, `BETWEEN`, `IN`, `IS [NOT] NULL`,
  and anchored pattern matches (`col LIKE 'foo%'`, not `'%foo'`). It's also the only index type that
  can satisfy `ORDER BY` without a separate sort step.
- **GIN** ("inverted index") is for columns holding *multiple component values per row* — arrays,
  `jsonb`, full-text `tsvector`. It indexes each component separately, so it efficiently answers
  "does this row contain X" queries (`@>`, `?`, array `&&`).
- **GiST** is an extensible framework, most commonly seen for geometric types, range types, and
  full-text search alternatives; it also supports nearest-neighbor (`ORDER BY col <-> point LIMIT n`)
  searches that B-tree/GIN cannot do.
- **Hash** indexes only support `=`; rarely worth it over B-tree in practice. **BRIN** is a
  cheap, tiny index for huge tables whose column values correlate with physical row order (e.g. an
  append-only `created_at`).

```sql
-- default B-tree
CREATE INDEX idx_orders_customer ON orders (customer_id);

-- GIN for containment/array/jsonb searches
CREATE INDEX idx_orders_tags ON orders USING GIN (tags);
```

**Multicolumn indexes**: put the column used in equality filters first; only the leading columns
determine how much of the index the planner can skip. Rule of thumb — indexes with more than 3
columns rarely help unless usage is very stylized.

### Partial indexes

Index only the subset of rows your queries actually care about. Two classic uses:

```sql
-- 1) Exclude the common/uninteresting case to shrink the index and speed writes
CREATE INDEX idx_orders_unbilled ON orders (order_nr) WHERE billed IS NOT TRUE;

-- 2) Enforce uniqueness only among a subset of rows
CREATE UNIQUE INDEX uq_tests_success ON tests (subject, target) WHERE success;
```

Caveat: the query planner can only use a partial index when it can prove the query's `WHERE`
clause *implies* the index predicate — matching is syntactic/simple-inequality, not a general
theorem prover, and it happens at planning time (so a parameterized `x < $1` will never match a
predicate like `x < 2`). Do not build a family of non-overlapping partial indexes as a substitute
for table partitioning — the planner tests each one, which gets expensive; use one composite index
or real partitioning instead.

### When an index is NOT used (and why that can be correct)

- **Common values**: if a value accounts for more than a few percent of rows, a sequential scan is
  usually cheaper than random-access index lookups — this is expected planner behavior, not a bug.
- **Small tables**: if the whole table fits in a page or a few pages, a seq scan always wins.
- **Function/expression mismatch**: `WHERE lower(col) = 'x'` cannot use a plain index on `col`; you
  need `CREATE INDEX ON t (lower(col))` (an expression index), and the query must use the identical
  expression.
- **Stale statistics**: always run `ANALYZE` after bulk loads before judging index usage — cost
  estimates without real statistics are close to meaningless.
- **Type/collation mismatch** or a leading-column gap in a multicolumn index also silently
  disqualify an index.
- To confirm the planner's reasoning, force plan shapes off (`SET enable_seqscan = off;` /
  `enable_indexscan`, `enable_nestloop`, etc. — session-scoped, for diagnosis only) and compare with
  `EXPLAIN ANALYZE`. If disabling seq scan doesn't produce a faster plan, the planner was right.

## EXPLAIN / EXPLAIN ANALYZE — diagnosing a slow query

`EXPLAIN` shows the *planned* query tree with cost estimates; `EXPLAIN ANALYZE` actually **runs**
the query and adds real timings and row counts, so estimated vs actual can be compared directly.
`EXPLAIN (ANALYZE, BUFFERS)` (BUFFERS is implied by ANALYZE) additionally shows shared-buffer
hits/reads, the fastest way to spot pages not cached in memory.

```sql
EXPLAIN ANALYZE
SELECT * FROM orders WHERE customer_id = 42 AND status = 'open';
```

Reading a plan:

```
Nested Loop  (cost=4.65..118.50 rows=10 width=488) (actual time=0.017..0.051 rows=10.00 loops=1)
  ->  Bitmap Heap Scan on tenk1 t1  (cost=4.36..39.38 rows=10 width=244) (actual time=... rows=10.00 loops=1)
        Recheck Cond: (unique1 < 10)
        ->  Bitmap Index Scan on tenk1_unique1  (cost=0.00..4.36 rows=10 width=0)
              Index Cond: (unique1 < 10)
  ->  Index Scan using tenk2_unique2 on tenk2 t2  (cost=0.29..7.90 rows=1 width=244) (actual ... loops=10)
```

- Read bottom-up / inside-out: leaf nodes are scans, parent nodes combine/transform their children.
- `cost=startup..total` is in arbitrary planner units, not milliseconds — don't compare it to
  `actual time` (which *is* real milliseconds and, under `loops > 1`, is a **per-loop average**;
  multiply by `loops` for the total time spent in that node).
- **The single most useful diagnostic signal**: compare the planner's `rows=` estimate against the
  `actual ... rows=` count. A large gap (10x+) means statistics are stale or the predicate is
  hard to estimate (e.g. correlated columns) — `ANALYZE` the table or consider extended statistics.
  Estimates dead-on and the query still slow points elsewhere (missing index, wrong join type, disk
  I/O — check `Buffers: shared hit=... read=...`, since `read` means it wasn't cached).
- `Filter:` on a scan node means the condition is checked row-by-row *after* fetching — it doesn't
  reduce the number of rows physically scanned, only the count returned. Compare that to
  `Index Cond:`, which does limit what's fetched. `Rows Removed by Filter: N` tells you exactly how
  wasteful a filter-only condition is.
- `Seq Scan` isn't automatically bad (see indexing section above) — check whether row counts and
  table size justify it.
- Common node types worth recognizing: `Seq Scan`, `Index Scan`, `Index Only Scan` (no heap access
  needed — fastest), `Bitmap Heap Scan` + `Bitmap Index Scan` (used when moderately selective, sorts
  matches before random heap access), `Nested Loop` (good for small outer sets), `Hash Join` (builds
  an in-memory hash table — watch for `Batches > 1`, meaning it spilled to disk), `Merge Join`
  (requires sorted inputs), `Sort` (check `Sort Method: external merge` = spilled to disk, a red flag
  — increase `work_mem` or reduce rows earlier in the plan).

## JSONB usage and indexing

- Prefer **`jsonb`** over `json` for anything queried or indexed — it's stored pre-parsed
  (binary), supports indexing, and is faster to process; `json` only wins if you need to preserve
  exact input text/key order/whitespace.
- **Containment** (`@>`) and **existence** (`?`, `?|`, `?&`) are the workhorse operators:

```sql
-- containment: does the document contain this structure?
SELECT * FROM api WHERE jdoc @> '{"company": "Magnafone"}';

-- existence: does this key/array element appear at the top level?
SELECT * FROM api WHERE jdoc ? 'active';
```

- **Index with GIN**, and choose the operator class deliberately:

```sql
-- default jsonb_ops: supports @>, ?, ?|, ?&, @?, @@  (more flexible, bigger index)
CREATE INDEX idx_api_jdoc ON api USING GIN (jdoc);

-- jsonb_path_ops: supports only @>, @?, @@  (smaller, faster, more selective)
CREATE INDEX idx_api_jdoc_path ON api USING GIN (jdoc jsonb_path_ops);
```

  Use `jsonb_path_ops` whenever the workload is purely containment/jsonpath queries — it produces a
  smaller, more selective index (it hashes key+value together instead of indexing keys and values
  separately) and is generally faster. Its downside: it can't answer plain key-existence queries
  (`?`), and it produces no index entry for empty objects/arrays, forcing a full scan for
  `{"a": {}}`-style searches.
- A GIN index on the whole column only helps queries that apply an indexable operator **directly to
  the indexed column**. `jdoc -> 'tags' ? 'qui'` will **not** use a plain GIN index on `jdoc` (the
  operator is applied to an expression, not the column). Either query by containment instead
  (`jdoc @> '{"tags": ["qui"]}'`), or build a targeted expression index:
  `CREATE INDEX ON api USING GIN ((jdoc -> 'tags'));`
- Row-level locking applies to the whole row on any JSONB update — keep documents reasonably sized
  (an atomic unit of update), not sprawling blobs, to avoid lock contention.

## Data type gotchas

| Situation | Use | Avoid | Why |
|---|---|---|---|
| Money / any exact quantity | `NUMERIC(precision, scale)` | `REAL` / `DOUBLE PRECISION` (float), `MONEY` | Floats are binary approximations — rounding errors compound in arithmetic. `NUMERIC` is exact. `MONEY` is locale/`lc_monetary`-dependent (breaks across DB dumps with different locale settings) and division truncates toward zero unexpectedly. |
| Point in time, cross-timezone correctness | `TIMESTAMPTZ` | `TIMESTAMP` (without time zone) | `TIMESTAMPTZ` is stored as UTC internally and converted to the session's `TimeZone` on display/input — it survives server/client timezone changes correctly. `TIMESTAMP` stores the literal clock value with **no** timezone context; if application servers or DB sessions run in different zones, values silently mean different instants. Default to `TIMESTAMPTZ` unless you specifically need "wall clock time, no zone" (e.g. a recurring local appointment time). |
| Variable-length text | `TEXT` or `VARCHAR` (no length) | `VARCHAR(n)` / `CHAR(n)` as a "safety limit" | In Postgres there is **no performance difference** between `TEXT`, `VARCHAR`, and `VARCHAR(n)` — unlike some other databases. `CHAR(n)` is actually usually the *slowest* of the three (blank-padded storage). Only add `VARCHAR(n)` when `n` is a genuine business rule you want the database to enforce, not for perceived storage/speed gains. |
| Integer identifiers | `integer`/`bigint` (or `GENERATED ALWAYS AS IDENTITY`) | oversized `NUMERIC` | Plain integer types are faster and adequate for virtually all counters/IDs; reserve `bigint` when you might exceed ~2.1 billion rows over the table's lifetime. |

```sql
-- money: exact, no locale dependency
CREATE TABLE invoices (amount NUMERIC(12, 2) NOT NULL);

-- always know your timezone story
CREATE TABLE events (occurred_at TIMESTAMPTZ NOT NULL DEFAULT now());
```

## Transaction isolation

Postgres implements MVCC: readers never block writers and vice versa. Four SQL standard levels are
requestable; Postgres implements three (Read Uncommitted silently behaves like Read Committed).

| Level | Dirty read | Non-repeatable read | Phantom read | Serialization anomaly |
|---|---|---|---|---|
| Read Committed (**default**) | no | possible | possible | possible |
| Repeatable Read | no | no | no (PG is stricter than the spec here) | possible |
| Serializable | no | no | no | no |

- **Read Committed** (the default — no `SET TRANSACTION` needed to get it): each *statement* sees a
  fresh snapshot as of when that statement started. Two `SELECT`s in the same transaction can see
  different data if another transaction commits in between. This is fine for simple, single-row
  read-modify-write logic (e.g. transferring a fixed amount between two accounts) but is **not**
  safe for logic that reads a set of rows and later assumes that set is still consistent (e.g.
  "compute a total, then insert a row based on it" — a classic race condition).
- **Repeatable Read**: the whole transaction sees one snapshot from its first statement. Concurrent
  updates to a row you're updating cause your transaction to fail with `could not serialize access
  due to concurrent update` — the application must catch this and retry the transaction from the
  start.
- **Serializable**: strongest guarantee — behaves as if transactions ran one at a time. Also requires
  retry logic on `SQLSTATE 40001` failures. Recommended only when Repeatable/Read Committed
  correctness is genuinely insufficient, since it adds monitoring overhead and more retries.

```sql
BEGIN;
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
-- ... application must be prepared to catch a serialization failure and retry the whole
-- transaction from BEGIN, not just the failing statement.
COMMIT;
```

**Practical default**: leave transactions at Read Committed unless you have identified a specific
read-then-write race; reaching for Serializable everywhere is usually a performance mistake, not a
safety improvement.

## Table partitioning

Splits one logical table into smaller physical pieces (partitions). Consider it once a table's size
exceeds physical memory and queries/maintenance concentrate on a subset (e.g. "mostly the last
month"), not before — partitioning adds real complexity (see limitations below) that isn't worth
paying for a table that's merely "big."

**Three built-in partitioning strategies:**

| Strategy | Splits by | Typical use |
|---|---|---|
| **Range** | Non-overlapping ranges of a key (dates, IDs) | Time-series data — one partition per day/month/year, drop old ones cheaply |
| **List** | Explicit list of key values per partition | Discrete categories known up front (region, tenant, status) |
| **Hash** | `hash(key) % modulus` | Even write/storage distribution when there's no natural range/list boundary |

```sql
-- 1. Declare the parent as partitioned — it has no storage of its own
CREATE TABLE measurement (
    city_id int NOT NULL,
    logdate date NOT NULL,
    peaktemp int,
    unitsales int
) PARTITION BY RANGE (logdate);

-- 2. Create partitions — ordinary tables bound to a range (lower bound inclusive, upper exclusive)
CREATE TABLE measurement_y2024m01 PARTITION OF measurement
    FOR VALUES FROM ('2024-01-01') TO ('2024-02-01');
CREATE TABLE measurement_y2024m02 PARTITION OF measurement
    FOR VALUES FROM ('2024-02-01') TO ('2024-03-01');

-- 3. Index the partition key on the parent — automatically applies to every partition, present and future
CREATE INDEX ON measurement (logdate);
```

**Why partition** (only worth it for genuinely large tables):
- Query performance improves when most accessed rows live in one/few partitions — the planner
  prunes partitions outside the query's range entirely (`enable_partition_pruning`, on by default).
- **Bulk delete becomes near-instant**: `DROP TABLE measurement_y2024m01;` or
  `ALTER TABLE measurement DETACH PARTITION measurement_y2024m01;` removes a month of data
  without the `VACUUM` overhead a bulk `DELETE` would cause. This is the single most common reason
  to partition — rolling-window retention (keep N months, drop the oldest).
- Old partitions can be moved to cheaper storage (a different tablespace).

**Real limitations to check before committing to it:**
- A unique/primary key constraint on a partitioned table **must include every partition-key column**
  — you can't have a table-wide unique constraint on a column that isn't part of the partition key.
- `BEFORE ROW` triggers on `INSERT` cannot redirect a row to a different partition.
- Attaching an existing table as a partition (`ATTACH PARTITION`) scans and validates it under an
  `ACCESS EXCLUSIVE` lock unless you pre-create a matching `CHECK` constraint — do that first on any
  table you're about to attach to avoid a long lock on a big table.
- New partitions must be added manually (or via a scheduled job) before data that would fall into
  them arrives — there's no auto-creation.

**Don't reach for a family of overlapping partial indexes as a substitute for partitioning** (see the
Indexing section above) — if the access pattern is genuinely "mostly recent data, bulk-drop old
data," partitioning is the right tool; a pile of partial indexes on one big table is not.

## Full text search

Postgres has built-in linguistic search — stemming, ranking, stop-word removal — that plain
`LIKE`/regex can't do (`LIKE '%satisfy%'` won't match "satisfies"; there's no ranking by relevance;
and without an index it's a full scan on every search).

**Core types**: `tsvector` (a preprocessed, normalized document — parsed into lexemes, stop words
removed, stored as a sorted array) and `tsquery` (a preprocessed search query). The `@@` match
operator tests whether a `tsvector` satisfies a `tsquery`:

```sql
SELECT 'a fat cat sat on a mat and ate a fat rat'::tsvector @@ 'cat & rat'::tsquery;  -- true

-- typical real usage: search a text column on the fly
SELECT * FROM articles
WHERE to_tsvector('english', title || ' ' || body) @@ to_tsquery('english', 'postgres & index');
```

**Index it — don't compute `to_tsvector()` on every row at query time for anything beyond a toy
table.** Two options:
```sql
-- 1) Expression index — simplest, computes the tsvector at index-build/update time
CREATE INDEX idx_articles_fts ON articles USING GIN (to_tsvector('english', title || ' ' || body));

-- 2) Generated/stored tsvector column — precomputed once per write, indexed like any column;
--    preferred when the same document is searched often or the concatenation expression is complex
ALTER TABLE articles ADD COLUMN fts tsvector
    GENERATED ALWAYS AS (to_tsvector('english', title || ' ' || body)) STORED;
CREATE INDEX idx_articles_fts ON articles USING GIN (fts);
```
Query against the indexed expression/column with the *identical* expression used at index time
(same rule as any expression index — see the Indexing section) or against the generated column
directly. Rank results with `ts_rank()`/`ts_rank_cd()` when you need relevance ordering instead of
just a boolean match. For anything beyond basic English-language search — multi-language documents,
typo tolerance, fuzzy matching — full text search's built-in dictionaries may not be enough; that's
when teams reach for an external search engine (Elasticsearch/OpenSearch/Meilisearch) instead.

## Backup and restore

Three fundamentally different approaches — pick based on what you're protecting against:

| Method | Tool | Recovery granularity | Notes |
|---|---|---|---|
| **SQL dump** | `pg_dump` / `pg_dumpall` | Point-in-time snapshot, whole DB or filtered (`-n schema`, `-t table`) | Portable across Postgres versions and even OS architectures — the only method that is. Internally consistent (a single-transaction snapshot); doesn't block concurrent reads/writes (except operations needing `ACCESS EXCLUSIVE`, e.g. most `ALTER TABLE`). |
| **File system backup** | Copy the data directory while the server is stopped, or via a snapshot | Full cluster only | Simple but requires downtime (or filesystem-level snapshot support) and is not portable across major versions/architectures. |
| **Continuous archiving (WAL)** | `pg_basebackup` + WAL archiving | Point-in-time recovery (PITR) to any instant, not just backup-time snapshots | The only method that lets you restore to "5 minutes before the bad `DELETE`" instead of only to the last full backup. More operational complexity: a base backup plus a continuously-archived stream of WAL segments. |

```bash
# SQL dump — single database, restorable into a newer Postgres version
pg_dump dbname > dumpfile
createdb -T template0 dbname_restored
psql -X --set ON_ERROR_STOP=on dbname_restored < dumpfile

# Whole cluster (roles, tablespaces, every database) — pg_dump alone does NOT capture cluster-wide objects
pg_dumpall > dumpfile
```
`pg_dump` needs read access to everything being dumped — for a full-database dump that generally
means running it as a superuser (or narrowing scope with `-n`/`-t` to what a limited role can read).
After restoring, always run `ANALYZE` — a freshly restored database has no planner statistics yet,
so the first queries will get bad plans until statistics exist.

**Practical default**: `pg_dump` on a schedule covers the common case (accidental data loss,
migrating to a new server, portable backups). Reach for continuous WAL archiving/PITR only once
you need to recover to an arbitrary point in time, not just to the last dump — typically once the
cost of losing "everything since last night's dump" becomes unacceptable.

## Replication

| Type | Mechanism | Use when |
|---|---|---|
| **Streaming (physical) replication** | Primary streams WAL records to a standby as they're generated; standby replays them, byte-for-byte identical to the primary | High availability / failover, read replicas — the standby is a full copy of the entire cluster |
| **Logical replication** | Publish/subscribe on specific tables; the subscriber receives row-level changes, not raw WAL — can be a different Postgres major version | Replicating a subset of tables, consolidating multiple DBs into one for analytics, replicating across major versions or even OS platforms, giving one group of users access to only part of the data |

**Streaming replication essentials**: asynchronous by default (small replication lag, typically
sub-second under normal load — synchronous is available but adds commit latency on the primary).
The standby needs `primary_conninfo` pointing at the primary and a role with the `REPLICATION`
privilege (deliberately *not* `SUPERUSER` — replication access shouldn't imply data-modification
access). Monitor lag by comparing `pg_current_wal_lsn()` on the primary against the last WAL
position received/replayed on the standby — a growing gap means the standby can't keep up.

**Logical replication essentials**: a publisher defines a `PUBLICATION` (a set of tables/changes,
optionally filtered to specific DML operation types); a subscriber creates a `SUBSCRIPTION` against
it, gets an initial snapshot copy, then a continuous stream of row-level changes applied in commit
order. Because it's row-level rather than byte-level, the subscriber database is a fully independent,
writable Postgres instance in its own right — the trade-off for that flexibility is more moving parts
than physical replication and the possibility of write conflicts if the subscriber isn't kept
read-only.

**Practical default**: reach for streaming replication first for HA/failover/read-scaling — it's the
simpler, better-tested path. Reach for logical replication specifically when you need partial-table
replication, cross-version migration, or multiple sources feeding one analytics database — not as a
general-purpose HA mechanism.

## Connection pooling — why it matters on the Postgres side too

Every open Postgres connection is a full OS backend process, not a lightweight thread — each one
costs real memory and scheduler overhead independent of whether it's doing anything:

- `max_connections` defaults to ~100 and can only be changed at server restart. PostgreSQL sizes
  several shared-memory structures directly off this number, so raising it isn't free even when
  connections are idle.
- Each active connection can independently use up to `work_mem` (default 4MB) *per sort/hash
  operation* — a query with several sort/hash nodes, multiplied across many concurrently active
  connections, can multiply memory usage far past what's obvious from `work_mem` alone.
- Consequence: a client-side pool (e.g. R2DBC's connection pool, HikariCP, PgBouncer) exists not
  just to save connection-setup latency, but to keep the **number of backend processes Postgres has
  to context-switch and allocate memory for** bounded and well below `max_connections`, leaving
  headroom for admin/superuser connections and other services. Sizing a pool far larger than the
  database can comfortably host defeats the purpose and can starve the server under load.
- Rule of thumb: size pools per-service conservatively (a common starting heuristic is a small
  multiple of CPU cores, not "as many as the framework defaults to"), and make sure the sum of all
  application pools across all service instances stays comfortably under the server's
  `max_connections`, leaving reserved slots for migrations/admin work. If you routinely need more
  concurrent logical clients than the DB can hold as physical backends, put a pooler like PgBouncer
  in transaction-pooling mode between the app and the database rather than raising `max_connections`
  indefinitely.
