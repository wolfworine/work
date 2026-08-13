---
name: kafka-event-driven-java
description: Use whenever designing or reviewing Kafka producers/consumers in a Java service, choosing delivery guarantees (at-least-once/exactly-once), picking a partition key, sizing a topic, handling consumer rebalancing or offset commits, tuning acks/idempotence/replication for durability, or debugging consumer lag, poison-pill messages, or duplicate/out-of-order records in an event-driven architecture.
---

# Kafka Event-Driven Applications (Java)

Operational reference distilled from *Effective Kafka* (Koutanov) for building and reviewing correctness-critical Kafka producers/consumers on the plain Java client (`kafka-clients`) or Spring Kafka. Not a tutorial — assumes the reader knows what Kafka is and needs the decisions that actually determine correctness in production.

## Core mental model (read this before touching config)

- **Record** = key + value + headers + partition + offset + timestamp. The key is a *classifier* for routing, not a database primary key — Kafka has no secondary index. The real "primary key" of a record is `(partition, offset)`.
- **Partition** = a totally-ordered, append-only log. Order is guaranteed *only within a partition*, and only for a single producer's writes to it. Records from different producers, or in different partitions, have no guaranteed relative order.
- **Topic** = a partial-order union of its partitions. A topic gives you parallelism *where you can* while preserving order *where you must* — that trade-off is set entirely by the choice of partition key.
- **Consumer group** = a load-balancing + mutual-exclusion mechanism. Kafka guarantees a partition is assigned to at most one consumer per group at a time — this is what makes "exactly one thread handles this partition" safe to rely on.
- **Consuming does not delete.** A topic is mutated only by producers (and retention/compaction). Multiple consumer groups can read the same topic independently, at their own pace, with zero interference.
- **Committing an offset N means:** "I have fully processed record N-1 and everything before it; never show them to me again." Commit *after* all side effects of a record (DB writes, downstream publishes, API calls) are durably done — not before, not concurrently.

## Delivery guarantee decision table

Kafka's own knob is simply *when the consumer commits relative to when it finishes processing*. There is no "exactly-once" as a broker feature — it's built by combining a delivery mode with idempotent processing.

| Mode | How it's achieved | Use when | Risk |
|---|---|---|---|
| At-most-once | Commit offset **before** processing the record | Losing an occasional record is cheap (telemetry, metrics, best-effort notifications) | Any failure between commit and finishing work silently drops the record |
| At-least-once | Commit offset **after** processing (and all side effects) completes | Default choice for anything where data loss is unacceptable | Same record may be redelivered and reprocessed after a crash/rebalance — **consumer must be idempotent** |
| Effectively exactly-once | At-least-once delivery **+ idempotent consumer** (dedupe key, upsert semantics, or Kafka transactions for consume-transform-produce chains) | Financial/ledger-style events, chained stream-processing stages that must not double-publish | Requires deliberate dedupe design; Kafka transactions add ~3-5% throughput cost and only cover Kafka-to-Kafka effects, not external side effects (DB writes, API calls) |

**Idempotent consumer rule:** always assume every record might be a duplicate. If a side effect is naturally idempotent (blind overwrite/upsert), no extra work is needed. Otherwise, check-then-act against a dedupe key (e.g. a unique record/event ID) before applying the effect. If output goes to another Kafka topic, use **transactions** (`initTransactions`/`beginTransaction`/`sendOffsetsToTransaction`/`commitTransaction`) to atomically tie the input offset commit to the output publish — this is the only way to avoid duplicate downstream records in a consume-transform-produce stage, since Kafka has no secondary index to check "did I already publish this."

## Producer safe defaults

```java
Map<String, Object> config = Map.of(
    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker1:9092,broker2:9092",
    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(),
    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(), // swap for Avro/JSON Schema serializer + schema registry in real systems
    ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true,   // atmost-once dup/reorder guard; forces acks=all, retries=MAX
    ProducerConfig.ACKS_CONFIG, "all",                // require all in-sync replicas (redundant once idempotence=true, but be explicit)
    ProducerConfig.COMPRESSION_TYPE_CONFIG, "lz4"     // near-free win; use zstd if all consumers are >=2.1.0 and network-bound
);

try (Producer<String, String> producer = new KafkaProducer<>(config)) {
    producer.send(new ProducerRecord<>(topic, matchId, payload), (metadata, exception) -> {
        if (exception != null) {
            // log + route to your own retry/alerting; do NOT silently swallow
        }
    });
} // producer.close() flushes + releases broker connections — always use try-with-resources or an explicit shutdown hook
```

Why each setting matters:
- **`enable.idempotence=true`** — without it, a timeout+retry can silently duplicate or reorder records (batch `A,B,C` can land as `A,C,B` if `B`'s ack times out and is retried after `C` succeeds). Idempotence tags each batch with a producer ID + sequence number so the broker can detect and drop duplicates/out-of-order retries. This is a Java-client default you should set explicitly — it is `false` unless you turn it on.
- **`acks`**: `0` = fire-and-forget, no offset returned, use only for genuinely disposable data (e.g. best-effort telemetry). `1` (the default when idempotence is off) = leader-local persistence only — a leader crash before replication loses the record. `all`/`-1` = wait for every in-sync replica — the only setting compatible with "if the producer thinks it published, it did."
- **`min.insync.replicas` (broker/topic-level, pair with `acks=all`)** — defaults to `1`, which silently defeats `acks=all`'s intent. For real durability, use replication factor 3 and `min.insync.replicas=2`, so a write survives one broker failure without blocking availability.
- **Partition key** — pick a **stable domain entity ID** that lives at least as long as the causal chain of events referencing it (e.g. an order ID, a match ID, a user ID) — not the finest-grained field and not a random/round-robin value if order matters at all. Records with the same key always land in the same partition and preserve their relative order; unkeyed records use a sticky/round-robin partitioner and have no ordering guarantee.
- **Never resize (shrink or grow) a topic you depend on for key-based ordering.** Kafka's key→partition hash is only consistent while the partition count is fixed; widening a topic silently breaks the ordering guarantee for existing keys since the same key can now hash to a different partition. Over-provision partition count up front instead of resizing later.

## Consumer safe defaults

```java
Map<String, Object> config = Map.of(
    ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker1:9092,broker2:9092",
    ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
    ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
    ConsumerConfig.GROUP_ID_CONFIG, "order-processor",
    ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest", // "latest" (the default) silently skips backlog on offset loss — dangerous for at-least-once
    ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false,     // take commits into your own hands — see pitfalls below
    ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 500          // cap work per poll() so processing fits inside max.poll.interval.ms
);

try (Consumer<String, String> consumer = new KafkaConsumer<>(config)) {
    consumer.subscribe(List.of(topic), new ConsumerRebalanceListener() {
        @Override public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
            // commit synchronously here — this is your last guaranteed chance before ownership moves
            consumer.commitSync(currentOffsets(partitions));
        }
        @Override public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
            // optional: seek() to a custom start point, warm caches, etc.
        }
    });

    while (running) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
        for (ConsumerRecord<String, String> record : records) {
            processWithIdempotenceCheck(record); // assume every record may be a redelivery
        }
        consumer.commitAsync(); // or commitSync() if you need the stronger "offset lag never exceeds one batch" guarantee
    }
}
```

Why each setting matters:
- **`enable.auto.commit=false`** — the default (`true`, every 5s) commits whatever the last `poll()` returned regardless of whether your application actually finished processing it; it happens to be safe *only* because of an undocumented implementation detail (commits are driven from the poll-process thread, not a timer). That's not a contract you should rely on. Commit manually, after processing.
- **`commitAsync()` vs `commitSync()`** — async is non-blocking and fine for most at-least-once pipelines (register a callback to log failures); sync blocks until the broker acknowledges and should be used when you need to minimize the reprocessing window (e.g. right before shutdown or in `onPartitionsRevoked`).
- **`auto.offset.reset=earliest`** — `latest` (the default) means that if committed offsets are ever lost (retention expiry on `__consumer_offsets`, or a brand-new group), the consumer jumps straight to the head of the topic and silently skips the entire backlog, degrading at-least-once to at-most-once at exactly the moment you can least afford it.
- **`max.poll.records` + `max.poll.interval.ms`** — a poll loop that takes longer than `max.poll.interval.ms` (default 5 min) to return to the next `poll()` call gets treated as dead and triggers a rebalance, *without the consumer itself necessarily knowing it lost the partition*. Keep per-batch processing time predictable and well under this deadline; lower `max.poll.records` if individual records are expensive to process.
- **Always register a `ConsumerRebalanceListener`.** Without one, when a partition is revoked mid-processing, any offsets you haven't committed yet are simply replayed by the new owner — that's expected under at-least-once, but if you don't commit in `onPartitionsRevoked()`, you needlessly widen the reprocessing window on every rebalance. `onPartitionsAssigned()` is also where you'd `seek()` to a custom offset if you're managing state outside of Kafka's own offset store.
- **`KafkaConsumer` is not thread-safe.** Never call `poll()` from multiple threads. If you need parallel processing, either use a thread pool to process the *returned* batch (commit only after all threads finish) or scale by adding more consumer instances/partitions.

## Common production pitfalls

- **Poison-pill messages.** A record that always fails processing (bad schema, corrupt payload, deterministic bug) will spin forever under at-least-once if you just retry-and-recommit. Set a record-level retry budget; on exhaustion, either skip-and-commit-with-alert or (preferably) publish to a **dead-letter topic** with the original headers/error context, then commit the offset so the partition keeps moving.
- **Unbounded retry loops stall the whole partition, not just one record.** If a downstream dependency (DB, API) is down and you retry indefinitely inside the poll loop without limits, you'll blow through `max.poll.interval.ms`, get evicted from the group, get reassigned the same partition (or have another consumer pick it up and hit the identical failure), and repeat — a "rebalance storm" that never actually recovers throughput even once the dependency comes back, unless you also fix the retry logic.
- **Consumer lag** (the gap between the latest produced offset and the last committed offset) is your primary health signal for a consumer group — monitor it per partition, not just in aggregate, since one stuck partition can hide behind healthy averages. Rising lag with flat throughput usually means a downstream bottleneck or a poison pill on one partition.
- **Auto-commit + slow/async processing is a silent data-loss trap.** If processing is dispatched to a thread pool but `poll()`'s auto-commit fires on its own schedule, in-flight (not-yet-processed) records can get their offsets committed before finishing — turn off auto-commit the moment processing isn't fully synchronous within the poll loop.
- **Topic/partition resize breaks key-based ordering.** Never grow a topic's partition count to "fix" a hot partition without a migration plan (double-publish to old+new topic, cut over consumers with `auto.offset.reset` handling reprocessing, retire the old topic) — see over-provisioning note above.
- **Rebalances are stop-the-world for the whole group**, not just the affected partitions — a slow `onPartitionsRevoked()` callback on one consumer delays every other consumer's reassignment. Keep rebalance-listener callbacks fast; do heavyweight cleanup asynchronously if it doesn't have to block the barrier. (Kafka 2.4+ cooperative rebalancing reduces this blast radius by only moving the partitions that actually changed hands — prefer `CooperativeStickyAssignor` over the legacy `RangeAssignor` for new consumer groups where available. See the dedicated section below for what's actually happening under the hood.)
- **Batching/throughput tuning basics:** `linger.ms` (default 0) trades a small artificial delay for larger batches — start small (0-10ms); `batch.size` (default 16 KiB) caps batch size regardless of linger; compression (`lz4`/`zstd`) is nearly always worth enabling for text payloads (JSON commonly compresses 5-7x) and costs little since it happens client-side, off the broker's critical path.
- **Schema evolution / compatibility.** Plain `String`/`ByteArray` (de)serializers are fine for prototypes but leave your producers and consumers with no compatibility contract. For anything long-lived, use Avro or JSON Schema with a schema registry (Confluent Schema Registry or equivalent) and enforce **backward-compatible** evolution (new fields optional/defaulted, no field removal/type changes without a version bump) so old consumers keep working against new producer versions and vice versa. Treat the producer/topic owner as the source of truth for schema — consumers are consulted, not vetoed.
- **Topic ownership.** In a broadcast (one-producer-many-consumers) topology, the producer owns the topic's lifecycle, schema, retention, and partitioning scheme; consumers only decide whether to subscribe. Don't let a single demanding consumer dictate the producer's domain model — insert a conditioning/transformation stage (consume, reshape, republish) instead of coupling the producer to one consumer's needs.

## Consumer group internals: static membership and cooperative rebalancing

Two mechanisms exist specifically to reduce how often and how badly rebalances hurt you — both are configuration, not code changes, and both are cheap wins once you know they exist.

**Static membership** (`group.instance.id`): gives a consumer instance a long-term, stable identity instead of a fresh dynamic one on every restart. Under the dynamic (default) model, any restart — a rolling deploy, a pod bounce, a transient crash — triggers a full rebalance because the coordinator sees a "new" member joining and an "old" one disappearing. Under static membership, a member that leaves and rejoins within `session.timeout.ms` gets its exact prior partition assignment back **with no rebalance at all**: the coordinator just parks the affected partitions (they accumulate lag but nothing gets reassigned) and resumes the returning instance from its last committed offset.

```java
ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, "order-processor-pod-3",  // unique per instance, stable across restarts
ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "45000",                 // widen well beyond your normal restart/deploy time
```

| Mode | Rebalance on restart? | Use when |
|---|---|---|
| Dynamic (default, no `group.instance.id`) | Yes, every time | Short-lived/ephemeral consumers, autoscaling groups where instance count actually changes |
| Static (`group.instance.id` set) | No, if back within `session.timeout.ms` | Long-lived consumers behind a rolling deploy or an orchestrator (Kubernetes) that restarts pods with the same identity — trades a longer detection window for eliminating restart-triggered rebalance storms |

Pair static membership with a `session.timeout.ms` wide enough to outlast a normal restart/rolling-deploy cycle — too tight and you get the rebalance you were trying to avoid anyway; too wide and a genuinely dead consumer's partitions sit idle (accumulating lag) longer before Kafka reassigns them.

**Eager vs. incremental cooperative rebalancing** — this is the actual protocol difference behind the `CooperativeStickyAssignor` recommendation above, not just an assignor swap:

| | Eager (legacy, `RangeAssignor`/`RoundRobinAssignor`) | Cooperative (`CooperativeStickyAssignor`, Kafka 2.4+/KIP-429) |
|---|---|---|
| Rounds per rebalance | One request-response round | Two rounds: round 1 = revocations only (+ fresh assignments where nothing existed before), round 2 = the actual new assignments |
| What `onPartitionsRevoked()` receives | Assumes **every** partition might be revoked — all consumers release everything | Only the partitions that are **actually** moving — everyone else keeps processing uninterrupted |
| `onPartitionsAssigned()` payload | The **complete** set of assigned partitions every time | Only the **newly acquired** partitions since the last rebalance (breaking behavioral difference if you're migrating existing code — code that assumes "full set" will silently misbehave) |
| Stop-the-world blast radius | Whole group pauses, even for a one-partition change | Only the partitions actually changing hands pause; everything else keeps flowing |
| Requirement | None | The assignment strategy must be *sticky* (preserve prior assignments as much as possible) — without stickiness, cooperative degrades to eager with extra round-trip overhead for nothing |

Practical takeaway: migrating an existing consumer group from `RangeAssignor` to `CooperativeStickyAssignor` is not a config-only change if your `onPartitionsAssigned()` logic assumes it always receives the full partition set — audit that callback first.

## When acting as a coding agent

- Default any new producer to `enable.idempotence=true`, `acks=all`; default any new grouped consumer to `enable.auto.commit=false` with manual commit-after-processing and a `ConsumerRebalanceListener`. Treat these as the safe baseline to deviate from only with an explicit, stated reason (e.g. genuinely disposable telemetry data justifies `acks=0`).
- When reviewing existing Kafka code, check in this order: (1) is idempotence/acks set explicitly, not left to environment defaults, (2) is auto-commit on when processing isn't synchronous, (3) is there a rebalance listener, (4) is there a bound on retries/poison-pill handling, (5) is the partition key a stable, appropriately-grained domain identifier.
- Ask before assuming exactly-once semantics are required — most business problems tolerate at-least-once + idempotent processing, which is far simpler and cheaper than wiring up Kafka transactions.
