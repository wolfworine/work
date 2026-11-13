---
name: dynamodb-single-table-quarkus
description: Use whenever designing or reviewing Amazon DynamoDB data access — modeling several entities in one physical table (single-table design), choosing partition/sort keys, composite sort keys with begins_with, GSI overloading, sparse indexes, adjacency lists, item collections, write sharding and hot partitions, access-pattern-first modeling, capacity modes, conditional writes, optimistic locking (@DynamoDbVersionAttribute), TransactWriteItems, TTL, Streams, PITR, retry/timeout/error handling, or using DynamoDB from Quarkus with the AWS SDK v2 Enhanced Client (quarkus-amazon-dynamodb, DynamoDbEnhancedClient, @DynamoDbBean, Dev Services for testing).
---

# DynamoDB Single-Table Design + Quarkus

**Sources (provider only):** AWS DynamoDB Developer Guide (best-practices pages, Constraints/ServiceQuotas, transactions, TTL, Streams, PITR,
read consistency, errors), AWS SDK for Java v2 developer guide (Enhanced Client, retry strategy, best practices), Javadoc of
`DynamoDbVersionAttribute`/`VersionedRecordExtension`, Quarkiverse *Quarkus Amazon Services* docs (4.0.0.Beta line), and one AWS Database Blog guest post
(Alex DeBrie, 2022 — a guest opinion, not an official AWS doc; partly read).
**[summary]** = extracted part of a page — verify. **NOT verified (couldn't read these pages):** `@DynamoDbSubtypes`/inheritance mapping for several entity types in one
table, `@DynamoDbConvertedBy`, `@DynamoDbFlatten`, `@DynamoDbUpdateBehavior`, auto-generated timestamp/atomic-counter extensions, the exception class thrown on a version
conflict, Quarkus-level retry/health/metrics properties for DynamoDB, the full credentials-provider list. The `quarkus.io` DynamoDB guide returned an empty page; Quarkiverse docs were used.
**Convention note:** names like `PK`, `SK`, `GSI1PK` are common community conventions — **not** stated by AWS in the pages read.

## 1. Mindset: access patterns first

- "You shouldn't start designing your schema for DynamoDB until you know the questions it will need to answer" (AWS). List every read/write pattern, its frequency and
  latency target **before** choosing keys.
- DynamoDB has no JOINs; related data is stored **together** using key design (locality of reference) and read with one `Query`.
- Data-shape factors: size, shape, velocity (AWS).

## 2. Single table vs multi-table — what AWS actually says

| Source | Position |
|---|---|
| `bp-general-nosql-design` | "Maintain as few tables as possible." Exceptions: **high-volume time-series data**, **datasets with very different access patterns**. A single table with inverted indexes can serve complex hierarchical structures. |
| `bp-table-design` | "In the majority of cases, we recommend that you consider using a single table." |
| `bp-modeling-nosql` (more balanced) | Single table fits "entities with **high access correlation and similar operational characteristics**". Multi-table is preferred for **independent operational requirements, different access patterns, or clear operational boundaries**. |
| AWS blog (guest, partial) | One table **per service/bounded context**; "items accessed together should be stored together"; pre-join (materialize) with a shared partition key; split small frequently updated attributes (e.g. counters) into their own items to cut WCU. |

**Rule for your case (several entities in one physical table):** group in one table only entities that are **queried together** and share operational needs
(throughput, TTL, backup, retention, permissions). One table **per microservice/bounded context** — never a shared table across services (see `microservices-antipatterns`: shared data store).
Don't force unrelated entities in; keep time-series/high-volume streams in their own (period) tables. Document the entity catalog and access patterns in an ADR.

## 3. Key design

- **Partition key:** high cardinality, uniform access (good: user id, device id with similar traffic; bad: status code, rounded date, one dominant device). A partition supports about
  **3,000 read units/s and 1,000 write units/s** (AWS example math: a 20 KB item = 5 read units per consistent read).
- **Hot partitions:** pick a better key, or **write-shard**: random suffix (e.g. 1–200) or calculated suffix (hash mod N); reads then `Query` each suffix and merge. For time series prefer the time-series pattern (table per period).
  Adaptive capacity helps but isn't a design substitute.
- **Sort key:** composite hierarchy `country#region#state#city` queryable at any level with `begins_with`, `between`, `>`, `<`. Don't pack unrelated concerns into one SK.
- **Version history (AWS sort-key pattern):** a `v0_` item holds the latest version plus `v1_`, `v2_`… revisions; query `v0_` for current, whole partition for history (filter out `v0_` when listing history).
- Key size limits: partition key 1–2048 bytes, sort key 1–1024 bytes. Use object types (`Long`, not `long`) in Java entities.

### Illustrative single-table layout (convention, adapt names)

| PK | SK | entityType | attributes |
|---|---|---|---|
| `CUSTOMER#123` | `PROFILE` | `Customer` | name, email, … |
| `CUSTOMER#123` | `ORDER#2026-11-13#9001` | `Order` | status, total, … |
| `CUSTOMER#123` | `ORDER#2026-11-13#9001#ITEM#1` | `OrderItem` | sku, qty |

`Query PK=CUSTOMER#123` + `SK begins_with ORDER#` returns the customer's orders (an **item collection**); one request returns profile + orders when you query the whole PK.
Keep an explicit **`entityType`** attribute on every item (AWS' own example uses a `Type` attribute on edge items) so readers can map to the right Java class.

## 4. Indexes

- **GSI** for additional access patterns: eventually consistent only; key attributes must exist for an item to appear (**sparse by default** — e.g. an `isOpen`/`OrderOpenDate` attribute removed
  when fulfilled gives a cheap "open orders" index).
- **GSI overloading:** one generic attribute reused by different entity types (AWS example: a generic `Data` attribute is the GSI sort key). Lets one GSI serve several patterns.
- **Inverted index** (GSI with PK/SK swapped) for many-to-many reverse lookups (adjacency list pattern: relationships are items in the partition with SK = target id; for graphs with multi-hop queries AWS points to Neptune).
- **GSI sharding** for time windows: GSI PK = shard number (`ShardNumber#0..9`), SK = ISO-8601 timestamp; query all shards in parallel and merge.
- **Limits/guidance:** 20 GSIs (default, adjustable) and 5 LSIs per table; "keep the number of indexes to a minimum"; project only needed attributes (`ALL` "doubles storage and write cost" in most cases);
  **LSI => item collection max 10 GB** — avoid LSIs unless you need strongly consistent index reads (LSIs and tables support strong consistency; GSIs and Streams never).
- Transactions **cannot be performed using indexes**.

## 5. Limits you must design around (DynamoDB Constraints page)

| Item | Limit |
|---|---|
| Item size | **400 KB** (attribute names count) |
| Query/Scan result | **1 MB per call** → paginate with `LastEvaluatedKey` |
| BatchGetItem | 100 items / 16 MB |
| BatchWriteItem | 25 Put/Delete / 16 MB (no updates; **returns unprocessed items you must retry yourself**) |
| TransactWriteItems/GetItems | 100 items, 4 MB, no two actions on the same item |
| Nested attribute depth | 32 |
| Expression length | 4 KB |
| Tables per Region | 2,500 initial (up to 10,000 by request) |
| Per-table throughput | 40,000 RCU/WCU (provisioned) |
| 1 RCU | one strongly consistent read/s (or two eventually consistent) of ≤ 4 KB; transactional read = 2 RCU |
| 1 WCU | one write/s of ≤ 1 KB; transactional write = 2 WCU |

Large items: **compress**, **vertical-partition** into several items under one PK (item collection, distinguished by SK), or store the blob in **S3** and keep the id in the item
(no transaction spans S3 and DynamoDB).

## 6. Capacity and cost

- **On-demand** is "the default and recommended throughput option for most workloads" (AWS); provisioned for predictable steady load (hourly provisioned charge; auto scaling).
  Switching provisioned→on-demand max 4 times per rolling 24 h.
- Avoid `Scan` on large tables (inefficient; eventually consistent 1 MB scan of 4 KB items = 128 read ops, strongly consistent = 256). Parallel scan only for tables ≥ 20 GB (about one segment per 2 GB).
- Cost levers: right-size item/attribute size (attribute names count), projections, sparse indexes, TTL, Standard-IA table class for rarely accessed data.

## 7. Writes, consistency, transactions

- **Conditional writes:** `attribute_not_exists(...)` on `PutItem` to prevent overwrite; failure = `ConditionalCheckFailedException` ("The conditional request failed") — **not retryable**; handle as a business conflict (409).
- **Optimistic locking (Enhanced Client):** `@DynamoDbVersionAttribute` on a numeric (`Long/Integer`) getter; `VersionedRecordExtension` is **loaded by default**; each write increments the
  version and requires the old one to match. First version = `startAt + incrementBy` (defaults 0 + 1 = 1; use `startAt = -1` to start at 0). The page doesn't name the exception on conflict —
  expect a conditional-check failure and **verify** in your SDK version. Optimistic locking fits low-conflict, single-item updates; it does **not** work across Regions in global tables (last-writer-wins).
- **Transactions (`TransactWriteItems`)**: synchronous, idempotent (`ClientRequestToken` valid **10 minutes** after completion; reuse with different params → `IdempotentParameterMismatch`),
  all-or-nothing, 2× capacity per item (consumed even if cancelled). Failure → `TransactionCanceledException` with `CancellationReasons` ordered like the request; **SDKs don't retry it**;
  item-level conflicts raise `TransactionConflictException`; SDK retries `TransactionInProgressException`. Use for cross-entity invariants within one table/Region (e.g. order + stock check), not as a general ORM unit of work.
- **Idempotency:** `InternalServerError` on a write "may have succeeded or failed" → use conditional writes or `TransactWriteItems` with a client token (see `idempotency-design`).
- **Consistency:** reads are eventually consistent by default; pass `consistentRead(true)` for strong (tables/LSIs only, 2× cost). GSI and Streams reads are always eventually consistent — design read-after-write flows accordingly.
- **TTL:** attribute must be a **Number in Unix epoch seconds**; deletion is "typically within a few days" after expiry (not instant) → filter expired items in queries; TTL deletes appear in Streams.
- **Streams:** 24-hour log; views `KEYS_ONLY|NEW_IMAGE|OLD_IMAGE|NEW_AND_OLD_IMAGES`; ordering guaranteed **per item**, not per partition; for transactions "consumers shouldn't assume transaction atomicity or ordering".
  Using Streams for an outbox/CDC is a reasonable inference (AWS frames it as change data capture) — consumers must be idempotent.
- **PITR:** up to **35 days**, per-second granularity; enable for production tables; re-enabling resets the window.

## 8. Quarkus integration

Extensions (Quarkiverse Quarkus Amazon Services; BOM `io.quarkiverse.amazonservices:quarkus-amazon-services-bom` 4.0.0.Beta5 at time of reading):
`quarkus-amazon-dynamodb` and `quarkus-amazon-dynamodb-enhanced` (**[summary]**; confirm coordinates in the extension list).

```properties
quarkus.dynamodb.aws.region=us-east-1                # no default; from aws.region / profile / instance profile
quarkus.dynamodb.aws.credentials.type=default        # default provider chain  [summary]
quarkus.dynamodb.sync-client.type=url                # default URL-connection client; apache5 is the supported Apache variant (apache is deprecated)
quarkus.dynamodb.async-client.type=netty             # [summary] default netty; aws-crt optional
%prod.quarkus.dynamodb.sync-client.connection-timeout=2S   # defaults [summary]: connect 2S, socket 30S, max-connections 50
quarkus.aws.api-call-timeout=10s                     # whole call incl. retries (documented for generic clients)
```
- Inject `DynamoDbClient` / `DynamoDbAsyncClient`, plus `DynamoDbEnhancedClient`/`DynamoDbEnhancedAsyncClient` **[summary]**; `@NamedDynamoDbTable("T")` injects a `DynamoDbTable<T>` **[summary]**.
  Named clients: `@AmazonClient("custom")` + `quarkus.dynamodb."custom".*`.
- **One shared client per application** (thread-safe; AWS best practice); don't build clients per request.
- **Async + event loop:** "Never block an event loop thread waiting for an async client future (e.g. with `join()`)" — deadlock risk. Bridge with
  `Uni.createFrom().completionStage(() -> asyncClient.getItem(req))`. From worker/virtual threads the sync client is simplest (see `java21-virtual-threads-quarkus`).
- **Overriding SDK config:** only documented route is a `@Produces @ApplicationScoped DynamoDbClient` method taking `DynamoDbClientBuilder` and calling `builder.overrideConfiguration(...)`
  — it **replaces the whole override configuration**, so set everything you need there (retry strategy, timeouts).
- **Dev Services:** enabled automatically per extension unless you configure a connection; disable `quarkus.dynamodb.devservices.enabled=false`; needs Docker. The **default provider is Floci**
  (`floci/floci:latest`), not LocalStack — alternatives `ministack`, `moto`, `localstack` (`localstack/localstack:4.13`) via `quarkus.aws.devservices.provider` or
  `quarkus.dynamodb.devservices.provider`. Containers shared in dev mode, **never shared in test mode**. Put real endpoints/credentials under `%prod.` and don't leave prod credentials active in dev/test.
  Emulators are not DynamoDB: verify capacity, transaction and GSI-consistency behavior against the real service for critical paths.
- Native: with CRT clients the native library must be embedded in the image **[verbatim]**; otherwise see the extension docs.

### Enhanced Client usage (verified API names)

```java
@DynamoDbBean
public class OrderItemRecord {
  private String pk, sk, entityType; private Long version; private String gsi1pk, gsi1sk;
  @DynamoDbPartitionKey public String getPk() { return pk; }   // annotations go on getters OR setters, not both
  @DynamoDbSortKey      public String getSk() { return sk; }
  @DynamoDbSecondaryPartitionKey(indexNames = "gsi1") public String getGsi1pk() { return gsi1pk; }
  @DynamoDbSecondarySortKey(indexNames = "gsi1")      public String getGsi1sk() { return gsi1sk; }
  @DynamoDbVersionAttribute public Long getVersion() { return version; }
  // setters ...
}

static final TableSchema<OrderItemRecord> SCHEMA = TableSchema.fromBean(OrderItemRecord.class); // build ONCE at startup (moderately expensive)
DynamoDbTable<OrderItemRecord> table = enhanced.table("claims_table", SCHEMA);

table.query(QueryConditional.sortBeginsWith(k -> k.partitionValue("CUSTOMER#123").sortValue("ORDER#")));  // item collection slice
table.index("gsi1").query(...);                                   // DynamoDbIndex for GSIs
enhanced.transactWriteItems(r -> r.addPutItem(...).addConditionCheck(table, c -> c.key(k).conditionExpression(expr)));
```
Verified API: `table()`, `putItem/getItem/updateItem/deleteItem`, `query(QueryConditional...)` (`keyEqualTo`, `sortGreaterThanOrEqualTo`, `sortBeginsWith` on GSI page), `scan()`, `batchGetItem`, `batchWriteItem`,
`transactGetItems/transactWriteItems` (`addConditionCheck/addUpdateItem/addDeleteItem`), `index(name)`, results as `PageIterable` (sync) / `PagePublisher` (async), `ScanEnhancedRequest.consistentRead(true)`,
`Expression.builder().expression(...).expressionValues(...)` for filters. **Null handling:** `putItem` omits nulls; `updateItem` **removes** null attributes by default — use `ignoreNullsMode(IgnoreNullsMode.SCALAR_ONLY|MAPS_ONLY)`.
`createTable()` generates GSIs from annotations (SDK ≥ 2.20.86; defaults 20 RCU/20 WCU, projection ALL). Composite GSI keys: up to four partition and four sort attributes with `Order.FIRST..FOURTH`.

### Several entity types in the same table with the Enhanced Client

Verified building blocks: `enhanced.table(name, schema)` accepts any `TableSchema`. **Design (inference from that API, not a documented AWS recipe):** keep **one bean/record per entity** and obtain one
`DynamoDbTable<Entity>` per entity pointing at the **same table name**, each with its own `TableSchema`; share key attributes by convention and write an `entityType` discriminator.
Read mixed collections as raw `Map<String, AttributeValue>` (low-level `DynamoDbClient.query`) or via a per-type table with a filter on `entityType`, then dispatch.
`@DynamoDbSubtypes` (polymorphic mapping) exists in the SDK but its documentation could **not** be read in this research — verify on the SDK Javadoc before adopting it.
Wrap all of this in a **repository per aggregate** so the single-table key scheme never leaks into the domain (see `java-hexagonal-architecture`).

## 9. Errors, retries, timeouts

| Exception | Retry? |
|---|---|
| `ProvisionedThroughputExceededException`, `ThrottlingException`, `RequestLimitExceeded`, `InternalServerError`(500), `ServiceUnavailable`(503), `ItemCollectionSizeLimitExceededException`(see note) | Yes (SDK retries throttling automatically) |
| `ConditionalCheckFailedException`, `ValidationException`, `ResourceNotFoundException`, `AccessDeniedException` | **No** |
| `TransactionCanceledException` | SDK doesn't retry; inspect `CancellationReasons`, decide |
- SDK strategies: **standard** (recommended; 3 attempts, 100 ms base / 1000 ms throttling, 500-token bucket), **legacy** (deprecated, but the default when none is set), **adaptive** (assumes one resource per client).
  DynamoDB clients use a default max retry count of **8** (page text; conflicts with the table) — set it explicitly.
  `o.retryStrategy(RetryMode.STANDARD)` or `o.retryStrategy(b -> b.maxAttempts(5))`; env `AWS_RETRY_MODE=standard`. Give each client its own `RetryStrategy` instance.
- Backoff guidance on the errors page: retry delays up to 50 ms, 100 ms, … and stop around **one minute**.
- **Always set both timeouts:** `apiCallAttemptTimeout` (single HTTP attempt) and `apiCallTimeout` (whole call incl. retries). Combine with Quarkus fault tolerance at the *service* boundary (see `quarkus-fault-tolerance`) but retry in **one layer** only.

## 10. Anti-patterns

| Smell | Fix |
|---|---|
| Designing keys before listing access patterns | Access-pattern workshop first |
| Low-cardinality PK (status, date) | Composite/high-cardinality PK, shard if needed |
| `Scan` in request paths | Query on key/GSI, sparse index |
| One table shared by several microservices | One table per bounded context |
| Everything in a single table incl. time-series/unrelated entities | AWS exceptions: period tables, independent operational needs |
| Too many GSIs / projecting `ALL` | Keep minimal, project needed attributes |
| LSIs without need (10 GB item-collection cap) | Prefer GSIs |
| Read-modify-write without condition/version | Conditional write or `@DynamoDbVersionAttribute` |
| Retrying `ConditionalCheckFailedException` | Treat as a conflict |
| Assuming GSI/Streams are read-after-write | Strong read on base table, or tolerate staleness |
| Relying on TTL for exact expiry | Filter expired items; TTL is eventual |
| Building clients/TableSchemas per request | Create once, share |
| Blocking an event loop on async client futures | `Uni.createFrom().completionStage`, or worker/virtual thread |
| Items near 400 KB or huge attribute names | Compress, split items, S3 pointer; short attribute names matter |

## 11. Checklist

- [ ] Access-pattern catalog + entity catalog documented; keys/GSIs derived from it.
- [ ] Single table scoped to one bounded context; exceptions (time series, divergent needs) justified.
- [ ] No hot partitions (cardinality, sharding); item sizes and collection sizes within limits.
- [ ] `entityType` on every item; repository layer hides key scheme.
- [ ] Conditional writes/versioning on updates; transactions only where needed; idempotent writes.
- [ ] Capacity mode chosen (on-demand default); alarms on throttling/consumed capacity.
- [ ] TTL, PITR and Streams (idempotent consumers) decided per table.
- [ ] Single shared client; timeouts + retry strategy set; Dev Services/emulator tests plus a check against real DynamoDB for critical paths.
- [ ] Quarkus config per environment (`%prod.` real endpoints), credentials never in git (`quarkus-config-profiles`).

Related: `idempotency-design`, `quarkus-fault-tolerance`, `quarkus-testing-standards`, `java-hexagonal-architecture`, `microservices-antipatterns`, `quarkus-observability`.

## Sources

- https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/best-practices.html and bp-general-nosql-design, bp-table-design, bp-modeling-nosql, bp-relational-modeling,
  bp-partition-key-design, bp-partition-key-uniform-load, bp-partition-key-sharding, bp-sort-keys, bp-indexes-general, bp-indexes-general-sparse-indexes, bp-gsi-overloading, bp-indexes-gsi-sharding,
  bp-adjacency-graphs, bp-time-series, bp-use-s3-too, bp-query-scan (all under the same developerguide path)
- .../developerguide/Constraints.html, ServiceQuotas.html, capacity-mode.html, Expressions.ConditionExpressions.html, transaction-apis.html, HowItWorks.ReadConsistency.html, TTL.html, Streams.html,
  Point-in-time-recovery.html, Programming.Errors.html, BestPractices_ImplementingVersionControl.html
- https://aws.amazon.com/blogs/database/single-table-vs-multi-table-design-in-amazon-dynamodb/ (guest post, partly read)
- https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/ (ddb-en-client-use, ddb-en-client-adv-features, ddb-en-client-use-secindex, ddb-en-client-use-multirecord, retry-strategy, best-practices)
- https://docs.aws.amazon.com/java/api/latest/software/amazon/awssdk/enhanced/dynamodb/extensions/annotations/DynamoDbVersionAttribute.html and .../extensions/VersionedRecordExtension.html
- https://docs.quarkiverse.io/quarkus-amazon-services/dev/amazon-dynamodb.html, .../common-features.html, .../dev-services.html
