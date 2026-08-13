---
name: modern-java-language-features
description: Use whenever writing or reviewing Java 17-21 code and deciding between a class/enum/record, whether to use Stream API vs a loop, how to model a closed set of types (sealed classes/pattern matching), how to use Optional correctly, how to choose switch expression vs statement, when text blocks pay off, or whether virtual threads apply to a given concurrency problem. Triggers on phrases like "should this be a record", "is a stream cleaner here", "NullPointerException risk", "Optional field", "exhaustive switch", "sealed interface", "virtual threads vs @Async/reactive", "compact constructor validation".
---

# Modern Java Language Features (17-21)

Decision guidance for an agent writing or reviewing production Java. Each section
gives a one-line heuristic first, then a minimal before/after. Apply these as
defaults; deviate when the codebase's existing conventions say otherwise.

## Stream API: map/filter/reduce/collect

**Use a stream when** the code is a pipeline of transformations over a collection
(filter, map, group, sum, collect) that reads left-to-right as a single expression.
**Avoid a stream when** the loop body has side effects on external state, needs to
break/continue/return early from an enclosing method, mutates a loop-external
accumulator by hand, or is trivial (one or two elements, a single field access).
Forcing a `for` loop into `.stream().forEach(...)` just to look "modern" is a
regression — `forEach` with a side-effecting lambda is not the point of streams and
hides control flow. Deeply nested/chained streams (>1 level of `flatMap` of `flatMap`,
or a pipeline stretching past ~6-8 operations) are also a smell — extract named
intermediate variables or private methods instead of one giant chain.

```java
// Avoid: streaming for the sake of it — a loop is clearer and debuggable
list.forEach(item -> {
    if (item.isValid()) {
        process(item);
        counter.incrementAndGet(); // mutating external state from a lambda
    }
});

// Prefer: plain loop when there's a side effect and early-exit potential
for (Item item : list) {
    if (!item.isValid()) continue;
    process(item);
    counter++;
}
```

```java
// Prefer: stream when it IS a transform/aggregate pipeline
List<String> activeNames = users.stream()
        .filter(User::isActive)
        .map(User::getName)
        .sorted()
        .toList();

// Avoid: hand-rolled loop reimplementing collect/groupingBy
Map<Boolean, List<User>> byActive = new HashMap<>();
for (User u : users) {
    byActive.computeIfAbsent(u.isActive(), k -> new ArrayList<>()).add(u);
}
// vs.
Map<Boolean, List<User>> byActive = users.stream()
        .collect(Collectors.partitioningBy(User::isActive));
```

Reduction rules of thumb:
- Prefer a named `Collector` (`toList()`, `groupingBy`, `joining`, `summarizing*`)
  over hand-written `reduce` — `reduce` is for genuine folds without a ready-made
  collector, and it gets unreadable fast once the accumulator is a mutable object.
- Prefer primitive streams (`IntStream`, `LongStream`, `DoubleStream`) for numeric
  aggregation over large ranges/arrays — avoids boxing.
- Parallel streams (`.parallel()`) are for CPU-bound work on large in-memory
  datasets with no shared mutable state and cheap, side-effect-free element
  processing; do not reach for them to speed up I/O-bound work (that's what
  virtual threads / async I/O are for), and never mutate shared collections from
  a parallel stream.
- A `Stream` is single-use (`IllegalStateException` on reuse) and does not support
  checked-exception-throwing lambdas cleanly — wrap or unwrap accordingly rather
  than swallowing exceptions inside `map`/`forEach`.

## Advanced Collectors: downstream collectors and multi-level grouping

**Use a downstream collector inside `groupingBy`/`partitioningBy`** whenever you'd otherwise group into `Map<K, List<T>>` and then loop over the values to reduce them — `Collectors` composes the reduction into the same pipeline instead of a second pass.

```java
// Avoid: group, then a second manual pass to reduce each bucket
Map<Dish.Type, List<Dish>> byType = menu.stream().collect(groupingBy(Dish::getType));
Map<Dish.Type, Long> countByType = new HashMap<>();
byType.forEach((type, dishes) -> countByType.put(type, (long) dishes.size()));

// Prefer: downstream collector does the reduction in the same collect()
Map<Dish.Type, Long> countByType =
        menu.stream().collect(groupingBy(Dish::getType, counting()));
```

Common downstream collectors, by what you need out of each group: `counting()` (how many), `summingInt`/`averagingInt` (numeric rollup), `mapping(fn, toList())` (transform each element before collecting it), `maxBy`/`minBy` (the extreme element, wrapped in `Optional`), `collectingAndThen(collector, finisher)` (post-process the collected result — e.g. wrap in an unmodifiable list), and `toSet()` when duplicates in a group should collapse.

**Multi-level grouping**: nest `groupingBy` inside `groupingBy` to group by more than one key at once — the result type nests accordingly (`Map<K1, Map<K2, List<T>>>`):
```java
Map<String, Map<String, List<Car>>> byBrandThenColor =
        cars.stream().collect(groupingBy(Car::getBrand, groupingBy(Car::getColor)));
```
Don't nest more than two levels for readability — past that, extract intermediate named methods or reconsider whether a flat stream of a small record (`brand`, `color`, `count`) reads better than a triple-nested map that every caller has to unwrap.

## Lambdas and functional interfaces

**Use a lambda/method reference when** you're passing behavior into an API that
takes a functional interface (`Predicate`, `Function`, `Consumer`, `Supplier`,
`Comparator`, or a custom `@FunctionalInterface`). **Prefer a method reference over a
lambda** whenever the lambda body is just "call this existing method" — it's shorter
and self-documenting. **Avoid a lambda** when the body needs more than a few lines
of logic, has multiple exit paths, or needs to be independently unit-tested — extract
it into a named method/class first.

```java
// Avoid
list.sort((a, b) -> a.getName().compareTo(b.getName()));

// Prefer
list.sort(Comparator.comparing(Item::getName));
```

```java
// Avoid: an inline lambda hiding real business logic — hard to test in isolation
orders.stream().filter(o -> {
    // 15 lines of eligibility rules
    return eligible;
}).toList();

// Prefer: extract to a named, independently testable method
orders.stream().filter(OrderPolicy::isEligibleForDiscount).toList();
```

Don't invent a new `@FunctionalInterface` when `java.util.function` already has the
shape you need (`BiFunction`, `UnaryOperator`, `Predicate.not(...)`, `.andThen()`,
`.compose()`). Only define a custom one when the method name adds real domain
meaning (e.g. a `Validator<T>` with a `validate` method reads better at call sites
than a raw `Predicate<T>`) or when a checked exception must be part of the contract.

## Optional: correct usage vs common misuse

**Use `Optional<T>` only as a method return type** to signal "this may legitimately
have no value" to callers, and immediately chain `map`/`flatMap`/`filter`/`orElse*`
instead of calling `.get()`. **Never use `Optional` as:**
- a field type (it isn't `Serializable`, adds an indirection object per instance,
  and doesn't solve anything a nullable field with a getter can't — expose the
  optionality only at the accessor: `public Optional<Car> getCar()` backed by a
  plain nullable field);
- a method/constructor **parameter** type — it forces every caller to wrap a value
  just to call the method; use overloads or make the parameter genuinely required;
- a field in a `record` for the same serialization/indirection reasons.

Also avoid: calling `.get()` without checking `isPresent()` first (defeats the
purpose — same NPE risk as before, just moved), and using the primitive variants
`OptionalInt`/`OptionalLong`/`OptionalDouble` — they lack `map`/`flatMap`/`filter`
and can't be composed with the boxed `Optional`, so the "avoid boxing" argument for
primitive streams doesn't carry over to a container of at most one element.

```java
// Avoid: Optional as a field and as a parameter
public class Person {
    private Optional<Car> car;             // adds indirection, breaks serialization
    public void register(Optional<String> nickname) { ... } // forces caller to wrap
}

// Prefer: nullable field, Optional only at the accessor boundary
public class Person {
    private Car car; // nullable internally
    public Optional<Car> getCar() { return Optional.ofNullable(car); }
    public void register(String nickname) { ... } // null or overload if optional
}
```

```java
// Avoid: get() without a check — same crash risk as null, just later
Optional<Insurance> insurance = car.getInsurance();
String name = insurance.get().getName();

// Prefer: chain through map/flatMap/orElse, never dereference directly
String name = person.flatMap(Person::getCar)
        .flatMap(Car::getInsurance)
        .map(Insurance::getName)
        .orElse("Unknown");
```

Use `Optional.ofNullable(...)` to wrap a legacy/JDK API that returns null (e.g.
`map.get(key)`), and prefer `orElseGet(Supplier)` over `orElse(value)` whenever the
default is expensive to construct — `orElse`'s argument is always evaluated eagerly.

## Records: immutable data carriers

**Use a record when** the type's entire job is to hold a fixed set of related
values and be compared/hashed/printed by those values (DTOs, value objects, tuple
returns, event/message payloads, keys). **Avoid a record when** the type needs
mutable state, inheritance (records are implicitly `final` and cannot extend a
class), or its identity/equality shouldn't be based purely on structural field
equality (e.g. entities keyed by a database ID that can outlive field changes).

```java
// Before: hand-written immutable POJO — constructor, getters, equals/hashCode/toString
public final class Point {
    private final int x, y;
    public Point(int x, int y) { this.x = x; this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }
    @Override public boolean equals(Object o) { /* ... */ }
    @Override public int hashCode() { /* ... */ }
    @Override public String toString() { /* ... */ }
}

// After
public record Point(int x, int y) {}
```

**Always validate invariants in a compact constructor**, not by adding a second
regular constructor or a static factory that's easy to bypass — the compact
constructor runs for every construction path including deserialization frameworks
that call the canonical constructor via reflection:

```java
public record Room(String name, String description, List<String> exits) {
    public Room {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        exits = List.copyOf(exits); // defensive copy + immutability, not just a null check
    }
}
```

A compact constructor can also normalize (trim strings, round numbers) but should
not silently change semantics the caller wouldn't expect — throw for genuinely
invalid input rather than clamping it silently. Records support extra instance
methods (e.g. a derived `getExits()`-style helper) and static factories; use those
instead of subclassing when you need alternate construction paths.

## Sealed classes/interfaces + exhaustive pattern matching

**Use a sealed hierarchy when** you have a closed, known-at-compile-time set of
variants (a result type, an AST node, a payment method, a state-machine state) and
you want the compiler to force every `switch` over it to handle all cases —
catching missing-case bugs at compile time instead of at runtime with a forgotten
`default`. **Don't use it** for extension points meant to be implemented by code
outside your control (plugin SPIs, anything a downstream module or third party
should be able to implement) — that's what a plain, non-sealed interface is for.

```java
public sealed interface PaymentMethod
        permits CreditCard, BankTransfer, DigitalWallet {}

public record CreditCard(String number, YearMonth expiry) implements PaymentMethod {}
public record BankTransfer(String iban) implements PaymentMethod {}
public record DigitalWallet(String provider, String accountId) implements PaymentMethod {}
```

```java
// Before: instanceof chain, easy to forget a branch, no compiler help
String describe(PaymentMethod m) {
    if (m instanceof CreditCard cc) {
        return "Card ending " + cc.number().substring(cc.number().length() - 4);
    } else if (m instanceof BankTransfer bt) {
        return "Transfer from " + bt.iban();
    } else {
        return "Unknown"; // silently wrong if a new variant is added later
    }
}

// After: exhaustive switch with record patterns — compiler errors if a
// permitted subtype is added and not handled, no default needed/wanted
String describe(PaymentMethod m) {
    return switch (m) {
        case CreditCard(String number, var expiry) ->
                "Card ending " + number.substring(number.length() - 4);
        case BankTransfer(String iban) -> "Transfer from " + iban;
        case DigitalWallet(String provider, var id) -> provider + " wallet " + id;
    };
}
```

Deliberately **do not** add a `default` branch to an exhaustive sealed switch — a
`default` defeats the entire benefit (the compiler no longer forces you to revisit
every switch when a new permitted subtype is added). Add `default` only for a
non-sealed type where exhaustiveness truly cannot be guaranteed.

Use guarded patterns (`case CreditCard cc when cc.expiry().isBefore(now) -> ...`)
instead of a pattern-match followed by a nested `if` — keeps each branch's
condition next to its case label.

## Switch expressions vs statements

**Use a switch *expression*** (`->` arrows, no fallthrough) whenever the switch
produces a value assigned to a variable, returned, or passed as an argument — it's
exhaustiveness-checked, cannot fall through by accident, and each arm is one
expression or a `yield`. **Use a switch *statement*** (`case:` with `break`) only
when you need pure side effects per branch and no resulting value, and even then
prefer the expression form's arrow syntax to eliminate fallthrough bugs.

```java
// Avoid: statement form for something that's really just computing a value
int score;
switch (grade) {
    case "A": score = 4; break;
    case "B": score = 3; break;
    default:  score = 0;
}

// Prefer: expression form — exhaustive, no fallthrough, no forgotten break
int score = switch (grade) {
    case "A" -> 4;
    case "B" -> 3;
    default -> 0;
};
```

Multi-statement arms use `yield`, not `return`, to produce the switch's value:

```java
int result = switch (op) {
    case ADD -> a + b;
    case DIVIDE -> {
        if (b == 0) throw new ArithmeticException("divide by zero");
        yield a / b;
    }
};
```

## Text blocks

**Use a text block (`"""`)** for any multi-line string literal — SQL, JSON/YAML
fixtures, HTML fragments, help text — where the old approach was string
concatenation or a wall of `\n`. **Don't use one** for genuinely short, single-line
strings; the triple-quote ceremony adds nothing there.

```java
// Before
String query = "SELECT id, name, email\n" +
               "FROM users\n" +
               "WHERE active = true\n" +
               "ORDER BY name";

// After
String query = """
        SELECT id, name, email
        FROM users
        WHERE active = true
        ORDER BY name""";
```

Watch incidental whitespace: the closing `"""` position sets the common
indentation stripped from every line — misplacing it is the most common text-block
bug. Use `.stripIndent()`/`.formatted(...)` for further processing and
interpolation-style substitution rather than falling back to `+` concatenation
inside the block.

## Virtual threads (Project Loom)

**Reach for virtual threads when** the bottleneck is *many concurrent blocking I/O
calls* (HTTP calls to other services, JDBC queries, file I/O) written in ordinary
synchronous/blocking style, and you currently need more platform threads than is
practical (thread-per-request servers under high concurrency, thread-pool
starvation, `Executors.newFixedThreadPool` sized in the low hundreds hitting a
ceiling). A virtual thread is cheap to create and blocks the *virtual* thread, not
the OS thread — thousands of them can be in-flight while only a handful of OS
carrier threads are used.

```java
// Before: bounded platform-thread pool caps concurrency; blocking calls tie up
// expensive OS threads while waiting on I/O
ExecutorService pool = Executors.newFixedThreadPool(200);

// After: virtual threads — same blocking code, unbounded-in-practice concurrency
ExecutorService pool = Executors.newVirtualThreadPerTaskExecutor();
```

Retrofitting existing threaded code is usually this mechanical:

```java
// Legacy
Thread.startVirtualThread(this);   // instead of: new Thread(this).start();
```

**Virtual threads do NOT replace reactive/async programming** in every case:
- They only help *blocking* code become cheap to scale; they add nothing to
  genuinely CPU-bound work (CPU-bound work is still limited by core count —
  size a bounded platform-thread/fork-join pool for that, not virtual threads).
- Pin risk: a virtual thread pins its carrier OS thread for the duration of a
  `synchronized` block or a native call — heavy use of `synchronized` around
  blocking I/O defeats the scalability benefit (prefer `java.util.concurrent`
  locks in hot paths that also block on I/O under a virtual thread).
- Use a `Semaphore` to cap concurrent virtual threads hitting a downstream
  system (e.g. a rate-limited API or a fixed-size connection pool) — do not
  reuse a bounded thread pool for that purpose; the JVM manages the pool of
  carrier threads for you, virtual threads themselves are not pooled and
  should not be pooled.
- All virtual threads are daemon threads and their priority cannot be tuned —
  don't rely on thread priority for scheduling behavior.
- If the system is already built around a reactive pipeline (e.g. Project
  Reactor/RxJava end-to-end, backpressure-aware streaming), virtual threads are
  not an automatic replacement — they solve "blocking code doesn't scale," not
  "I need backpressure, cancellation propagation, or non-blocking composition
  across an already-async stack." Mixing them in only where a leaf call is
  blocking (e.g. a blocking JDBC driver) is reasonable; rewriting a reactive
  pipeline into blocking-plus-virtual-threads for its own sake is not automatically
  a win — decide based on whether you actually need reactive operators
  (backpressure/merging/windowing) or just needed blocking I/O to stop being
  expensive.

## CompletableFuture: composable async without a reactive framework

**Reach for `CompletableFuture`** when you need to run a handful of independent blocking calls concurrently and combine their results, but don't want to pull in a full reactive stack (Project Reactor/RxMutiny) for what's fundamentally "start these in parallel, join the results." **Prefer virtual threads (see below) instead** when the code is naturally sequential blocking I/O and you just need it to not tie up a platform thread — `CompletableFuture`'s value is specifically the *combinator* API (`thenCombine`, `thenCompose`, `allOf`) for orchestrating multiple concurrent operations, not blocking-call cheapness on its own.

```java
// Kick off independent async work with supplyAsync — runs on the common ForkJoinPool
// (or a supplied Executor) instead of blocking the calling thread
CompletableFuture<Double> priceFuture =
        CompletableFuture.supplyAsync(() -> calculatePrice(product));
```

**`thenCombine`** joins two independent futures once both complete — use it instead of calling `.get()`/`.join()` on each in sequence, which serializes work that could run in parallel:
```java
// Avoid: sequential .join() defeats the purpose of starting both async
CompletableFuture<Double> a = CompletableFuture.supplyAsync(() -> f(x));
CompletableFuture<Double> b = CompletableFuture.supplyAsync(() -> g(x));
double result = a.join() + b.join(); // b doesn't even start until a's supplyAsync call returns, but join() still blocks serially here if not careful

// Prefer: thenCombine composes without either side blocking the other
CompletableFuture<Double> c = a.thenCombine(b, (y, z) -> y + z);
double result = c.get();
```
Other combinators, by shape of the composition needed: `thenApply(fn)` (transform this future's result, stays a `CompletableFuture`), `thenCompose(fn)` (flatten when `fn` itself returns a `CompletableFuture` — the async equivalent of `Optional.flatMap`, avoids `CompletableFuture<CompletableFuture<T>>`), `thenAccept`/`thenRun` (side-effect-only continuations), `CompletableFuture.allOf(futures...)` (wait for all, no combined result — pair with `.join()` per-future after), `orTimeout`/`completeOnTimeout` (bound how long you'll wait for a slow dependency instead of hanging indefinitely).

**Don't** call `.get()`/`.join()` immediately after `supplyAsync` in a loop over N items — that serializes the "async" work back into sequential execution; collect the futures first (`.toList()` on a stream of `CompletableFuture`), *then* join them all.

## Sequenced Collections (Java 21)

Java 21 added `SequencedCollection`, `SequencedSet`, and `SequencedMap` — every `List` and any `Set`/`Map` with a defined iteration order now expose consistent first/last-element operations, replacing type-specific workarounds (`list.get(0)` vs `deque.getFirst()` vs `((LinkedHashMap) map).firstEntry()` — no more.) **Use these methods instead of index-0/`size()-1` access** on a `List` — they read as intent ("first", "last") rather than an off-by-one-prone index computation, and they work identically across `List`, `LinkedHashSet`, and `LinkedHashMap`.

```java
// Avoid: index arithmetic to get the first/last element, error-prone on empty lists
String first = list.get(0);
String last = list.get(list.size() - 1);

// Prefer: SequencedCollection — same intent, no index math
String first = list.getFirst();
String last = list.getLast();
```
Also available: `addFirst`/`addLast`, `removeFirst`/`removeLast`, and `.reversed()` (returns a view, not a copy) on collections; `SequencedMap` mirrors this with `putFirst`/`putLast`, `firstEntry()`/`lastEntry()`, `pollFirstEntry()`/`pollLastEntry()`, and `.reversed()`. Prefer `.reversed()` over `Collections.reverse(list)` when you don't want to mutate the original — the latter reverses in place and returns `void`.

## String templates — preview feature, verify JDK version before using

Java 21 previewed string templates (`STR."Hello \{user.getFirstName()}, balance $\{user.getBalance()}"`) as a safer, more readable replacement for `+`-concatenation or `StringBuilder` chains for runtime string interpolation. **Treat this as unstable, not a default recommendation**: it shipped as a *preview* feature in JDK 21 (and a second preview in 22) requiring `--enable-preview`, and the JEP was later withdrawn/reworked rather than finalized as originally designed — check the actual target JDK's finalized feature set before recommending `STR."..."` syntax in code that needs to compile without preview flags. Where it's genuinely available, it still doesn't replace **text blocks** (see above) for genuinely static multi-line content — the two solve different problems (interpolation vs. multi-line literals) and compose together when both are needed.

## Quick decision table

| Situation | Reach for |
|---|---|
| Transform/aggregate a collection, no side effects, no early exit | Stream + Collector |
| Loop needs side effects, early return/break, or is trivial | Plain `for`/`for-each` |
| Passing "what to do" into an API | Lambda / method reference |
| Lambda body >2-3 lines or needs its own unit test | Extract to a named method |
| Method may legitimately return "nothing" | `Optional<T>` return type |
| Field or parameter might be absent | Nullable field/param, `Optional` only at accessor |
| Immutable bag of fields (DTO, value object, event) | `record` (+ compact constructor for validation) |
| Closed, known set of variants; want compile-time exhaustiveness | `sealed` interface/class + pattern-matching `switch` |
| Extension point for external/plugin implementers | Plain (non-sealed) interface |
| Switch that produces a value | Switch *expression* (`->`, exhaustive) |
| Switch that only performs side effects | Switch *statement* (still prefer arrow form) |
| Multi-line string literal (SQL, JSON, HTML) | Text block `"""` |
| Group + reduce each group (count/sum/max per key) | `groupingBy` with a downstream collector, not group-then-loop |
| Grouping by more than one key | Nested `groupingBy` (max 2 levels before extracting a method) |
| A few independent blocking calls to run concurrently and combine | `CompletableFuture` (`thenCombine`/`thenCompose`/`allOf`) |
| First/last element access on a List/Set/Map | `SequencedCollection`/`SequencedMap` (`getFirst`/`getLast`/`.reversed()`), not index math |
| Runtime string interpolation | Text block + explicit concatenation by default; `STR."..."` only if confirmed non-preview on the target JDK |
| Many concurrent blocking I/O calls (HTTP/JDBC/file) | Virtual threads |
| CPU-bound parallel work | Platform-thread/fork-join pool, or parallel streams |
| Already-reactive pipeline needing backpressure/composition | Keep reactive; virtual threads aren't a substitute |

## Honesty note on sources

Verified directly against source text (not standard-knowledge filler): Collectors/downstream-collector examples and the `groupingBy`-with-multiple-keys pattern, `CompletableFuture` combinators (`thenCombine`, `supplyAsync`) and the price-aggregation example, all three of Java 21's own headline additions per *Code with Java 21* — virtual threads (including the exact daemon-thread and no-priority-tuning caveats, already present in this skill and now confirmed as literal book content, not added standard knowledge), **Sequenced Collections** (new section above — the book covers it in ~1 page, enough to ground the method list but not deep edge cases), and **string templates** (new section above, including the preview-feature caveat, which is standard knowledge added on top since the book presents `STR."..."` as if already stable — it wasn't, and understanding that gap matters more than the syntax itself).

Confirmed still NOT covered by either source book, remaining standard-knowledge fill added by this skill (not book-extracted): sealed classes/interfaces, exhaustive pattern-matching `switch`, and record patterns. *Code with Java 21*'s only "pattern matching" mention is about `String`/regex pattern matching (Chapter 3), unrelated to switch pattern matching — its actual Java-21-features section (reproduced above almost verbatim: virtual threads, sequenced collections, string templates) does not include sealed types or switch patterns at all despite both being real JDK 21 features. Text blocks likewise have no dedicated source coverage in either book. These sections remain accurate JDK behavior, just not something this skill can cite a source page for.
