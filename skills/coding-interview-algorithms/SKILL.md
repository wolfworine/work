---
name: coding-interview-algorithms
description: Use whenever analyzing or stating the time/space complexity of an algorithm, optimizing a solution's memory footprint, solving a coding-interview-style algorithm problem (arrays, strings, linked lists, trees, graphs, recursion, dynamic programming), or when a solution's Big-O could likely be improved and the user wants a faster or less memory-hungry approach. Also use when reviewing code for "we're building an array/list/string we don't need to keep" or "this recurses too deep" style inefficiencies.
---

# Coding Interview Algorithms: Complexity Analysis & Memory Optimization

Operational reference for reasoning about algorithmic efficiency and — the primary focus — reducing an algorithm's **space complexity**. Distilled from the Big-O, Technical Questions, and Recursion/Dynamic Programming material in *Cracking the Coding Interview* (6th ed., McDowell). Use this whenever you state a complexity, review a diff for efficiency, or are asked to make something use less memory.

**Ground rule:** every optimization claim must be derivable, not guessed. "Derive, don't guess" — walk the code, don't pattern-match to a runtime you've seen before. The same discipline applies to space: don't say "O(1) space" without accounting for every array, hash table, recursion depth, and copy your solution allocates.

## 1. Big-O Primer: Time AND Space

### Time complexity ground rules
- **Drop constants**: `O(2N)` is `O(N)`. Two separate non-nested loops over the same input is still `O(N)`, not `O(2N)`. Never assume `O(N)` beats `O(N²)` for a specific, bounded input — Big-O describes rate of increase, not which code is faster on real data.
- **Drop non-dominant terms**: `O(N² + N)` → `O(N²)`. `O(N + log N)` → `O(N)`. But `O(A² + B)` cannot be reduced without a known relationship between `A` and `B` — never collapse two different input variables into one `N`. Give each input its own name (`a`, `b`, or better, descriptive names like `numTeams`) so you don't accidentally conflate them.
- **Add vs. multiply**: "do this, then when you're done, do that" → **add** the runtimes: `O(A + B)`. "do this for each time you do that" (nested/dependent loops) → **multiply**: `O(A * B)`. Mixing these up is one of the most common interview mistakes.
- **Amortized time**: an operation that's occasionally expensive but rarely so (e.g., a dynamic array's doubling reallocation) is described by its *amortized* cost, not its worst-case-per-call cost. Doubling a resizable array from size 1 up to N costs `1+2+4+...+N ≈ 2N` total copies for N insertions → `O(1)` amortized per insertion, even though any single insertion can be `O(N)`.
- **Recursive branching runtime**: a recursive function that branches `b` times per call and recurses to depth `d` costs roughly `O(b^d)` — count the nodes in the call tree, don't eyeball it. A binary-branching recursion to depth N is `O(2^N)`, not `O(N²)`.
- **Best conceivable runtime (BCR)**: before optimizing further, ask "what is the theoretical floor for this problem?" (e.g., you can't compare two N-element arrays in better than `O(N)` because you must touch every element at least once). Once your algorithm hits the BCR *and* uses `O(1)` extra space, you are provably done — stop optimizing time and, if you haven't already, turn to space.

### Space complexity — how to actually compute it
Space complexity is a parallel concept to time complexity, and it is where people get sloppy. Two rules cover almost every case:

1. **Every allocation that scales with input counts.** An `O(n)` array is `O(n)` space. An `n×n` matrix is `O(n²)` space. A hash set holding up to `n` items is `O(n)` space.
2. **Call-stack depth counts as space.** Each recursive call that is still "open" (waiting on a nested call to return) occupies a stack frame. A recursion that goes `n` levels deep uses `O(n)` space — *even if the function itself allocates nothing* — because the runtime must hold every frame simultaneously.

```java
// O(n) time AND O(n) space: n stack frames exist simultaneously
int sum(int n) {
    if (n <= 0) return 0;
    return n + sum(n - 1);
}
```

The critical trap: **`O(n)` total calls does not imply `O(n)` space.** What matters is how many calls exist *simultaneously* on the stack.

```java
// O(n) calls total, but they never nest — O(1) space
int pairSumSequence(int n) {
    int sum = 0;
    for (int i = 0; i < n; i++) {
        sum += pairSum(i, i + 1);   // pairSum returns immediately each time
    }
    return sum;
}
```

A recursive function with 2 branches per call recursing to depth N has `O(2^N)` *total* nodes in its call tree, but only `O(N)` nodes exist on the stack at any one instant — so its space complexity is `O(N)`, not `O(2^N)`. Always separate "how many calls happen in total" (drives time) from "how many calls are alive at once" (drives space).

**Checklist for stating space complexity of any solution:**
- What auxiliary data structures did I allocate, and how do their sizes scale with input?
- What is the maximum recursion depth, and is more than one recursive call from the same frame "in flight" at once (branching multiplies stack usage at a given depth, but depth is still what's summed for space)?
- Am I modifying the input in place, or did I copy it?
- Does a library call I'm relying on (sort, string concatenation, list resize) secretly allocate `O(n)` or more?

## 2. Memory-Reduction Technique Catalog

This is the core toolkit. For each technique: when to reach for it, and a before/after.

### 2.1 In-place transformation instead of a copy (two-pointer)
**When:** you're building a new array/string/list that's a transformed version of the input, but nothing else needs the original afterward.

```java
// Before: O(n) extra space — allocates a full reversed copy
int[] reverseCopy(int[] array) {
    int[] out = new int[array.length];
    for (int i = 0; i < array.length; i++) {
        out[array.length - 1 - i] = array[i];
    }
    return out;
}

// After: O(1) extra space — swap from both ends toward the middle
void reverseInPlace(int[] array) {
    for (int i = 0; i < array.length / 2; i++) {
        int other = array.length - i - 1;
        int temp = array[i];
        array[i] = array[other];
        array[other] = temp;
    }
}
```
The two-pointer pattern (one from each end, or a slow/fast pair) is the general mechanism behind most in-place array/string reductions: partitioning, palindrome checks, removing duplicates from a sorted array, merging two sorted ranges.

### 2.2 Iterative instead of recursive (reclaim call-stack space)
**When:** a recursive solution is clean but its depth is large enough that `O(depth)` stack space matters (deep trees, long lists, large N). Every recursive algorithm can be rewritten iteratively — sometimes at the cost of more complex code — usually with an explicit stack/queue that lives on the heap where you control its size, or with pure loop state.

```java
// Before: O(n) space — n stack frames for a list of length n
int lengthRecursive(Node head) {
    if (head == null) return 0;
    return 1 + lengthRecursive(head.next);
}

// After: O(1) space — no call stack growth
int lengthIterative(Node head) {
    int count = 0;
    while (head != null) {
        count++;
        head = head.next;
    }
    return count;
}
```
Before committing to a recursive solution in a real system (not just an interview), explicitly weigh the stack-depth cost against the readability win, and say so — this is a genuine engineering tradeoff, not just an interview talking point.

### 2.3 Bit manipulation for compact sets/flags
**When:** you need a set or boolean array over a small, bounded universe (e.g., "does this string contain each of the 26 lowercase letters," a visited-set over a bounded ID range, a permutation/subset check). A `boolean[]` or `HashSet<Integer>` costs roughly one byte (or one full object) per element; a bit vector packs 32/64 flags into a single int/long.

```java
// Before: O(n) space via a boolean array (or worse, a HashSet<Character>)
boolean isUniqueChars(String str) {
    boolean[] seen = new boolean[26];
    for (char c : str.toCharArray()) {
        int idx = c - 'a';
        if (seen[idx]) return false;
        seen[idx] = true;
    }
    return true;
}

// After: O(1) space — one int holds all 26 flags
boolean isUniqueCharsBitVector(String str) {
    int checker = 0;
    for (char c : str.toCharArray()) {
        int idx = c - 'a';
        if ((checker & (1 << idx)) != 0) return false; // getBit
        checker |= (1 << idx);                          // setBit
    }
    return true;
}
```
Core bit primitives worth having memorized as *derivations*, not rote facts:
- **Get bit i:** `(num & (1 << i)) != 0`
- **Set bit i:** `num | (1 << i)`
- **Clear bit i:** `num & ~(1 << i)`
- **Update bit i to v:** `(num & ~(1 << i)) | (v << i)`

Bit vectors are also the standard way to shrink a `Set<Integer>` of small dense IDs, implement a compact visited-set for graph/DP problems, or track subset membership in subset-enumeration DP.

### 2.4 Builder/buffer instead of repeated concatenation
**When:** building up a string (or any sequence) incrementally in a loop. Repeated `+`/concatenation on immutable strings copies the whole string each time.

```java
// Before: O(x * n^2) time, and transiently allocates a new full-length
// string on every iteration — a lot of short-lived garbage.
String joinWords(String[] words) {
    String sentence = "";
    for (String w : words) {
        sentence = sentence + w;   // copies everything built so far, every time
    }
    return sentence;
}

// After: O(x * n) time — one resizable internal buffer, one copy at the end
String joinWordsFast(String[] words) {
    StringBuilder sentence = new StringBuilder();
    for (String w : words) {
        sentence.append(w);
    }
    return sentence.toString();
}
```
This generalizes beyond strings: prefer a single growable buffer (`StringBuilder`, `ArrayList`, a pre-sized array) over repeatedly producing new immutable copies inside a loop, in any language.

### 2.5 Streaming/generators instead of materializing full collections
**When:** you only need to consume elements one at a time (find the first match, sum, count) rather than hold the whole transformed collection at once. Materializing an intermediate `O(n)` list you immediately iterate once and discard is wasted space.

```java
// Before: O(n) space — builds the entire filtered+mapped list before using it
List<String> longNames = new ArrayList<>();
for (Person p : people) {
    if (p.getName().length() > 10) longNames.add(p.getName().toUpperCase());
}
for (String name : longNames) process(name);

// After: O(1) extra space — process each qualifying element as it's found
for (Person p : people) {
    if (p.getName().length() > 10) process(p.getName().toUpperCase());
}
```
The same idea applies to reading files/query results in chunks instead of loading everything into memory, and to lazy iterators/generators over eager list-building.

### 2.6 Rolling variables instead of a full memo table (tabulation trimming)
**When:** a DP/tabulation solution stores `O(n)` results but each state only ever depends on a small constant number of previous states. This is one of the highest-value, most overlooked memory optimizations.

```java
// Before: O(n) space — full memo array, but memo[i] is only ever
// read by memo[i+1] and memo[i+2]
int fibonacciTable(int n) {
    if (n <= 1) return n;
    int[] memo = new int[n + 1];
    memo[0] = 0; memo[1] = 1;
    for (int i = 2; i <= n; i++) memo[i] = memo[i - 1] + memo[i - 2];
    return memo[n];
}

// After: O(1) space — only the last two values ever matter
int fibonacciRolling(int n) {
    if (n <= 1) return n;
    int a = 0, b = 1;
    for (int i = 2; i <= n; i++) {
        int c = a + b;
        a = b;
        b = c;
    }
    return b;
}
```
Whenever you tabulate, ask: "does state `i` depend on all prior states, or just the last `k`?" If it's the latter, collapse the table to `k` variables (or a fixed-size ring buffer for 2D DP, collapsing an `n×m` table to two rows when each row only depends on the row above it).

### 2.7 Primitive arrays over boxed collections
**When:** storing large numbers of numeric values where you don't need object semantics (nullability, generics). `int[]` stores raw values contiguously; `List<Integer>`/`ArrayList<Integer>` boxes every element as a separate object with per-element overhead (object header + pointer indirection), often 4-6x the memory of the primitive array for the same logical data, plus worse cache locality.

```java
// Before: N boxed Integer objects + an array of references to them
List<Integer> values = new ArrayList<>();
for (int i = 0; i < n; i++) values.add(compute(i));

// After: one contiguous primitive array
int[] values = new int[n];
for (int i = 0; i < n; i++) values[i] = compute(i);
```
Reach for this whenever profiling (or just scale — "n" is large) shows collection overhead matters; don't reflexively de-generify code that isn't on a hot/large-data path, since primitive arrays lose you generics, null-as-sentinel, and library ergonomics.

### 2.8 Reuse buffers instead of reallocating per call/iteration
**When:** a function or loop iteration allocates a fresh scratch array/buffer every time it runs, but the buffer's size and lifetime don't need to be per-call. Hoist the allocation out of the hot loop and clear/overwrite it instead of reallocating.

```java
// Before: allocates a new n-sized array on every one of m calls
for (int i = 0; i < m; i++) {
    int[] scratch = new int[n];
    fillAndUse(scratch, i);
}

// After: one allocation, reused m times
int[] scratch = new int[n];
for (int i = 0; i < m; i++) {
    Arrays.fill(scratch, 0);
    fillAndUse(scratch, i);
}
```
This trades a small amount of clarity for avoiding `O(m*n)` cumulative garbage when `O(n)` would do — worth it once `m` is large or the loop is hot.

## 3. Pattern-Recognition Cheat Sheet

Faster problem-solving comes from recognizing a small set of recurring shapes. Each entry: pattern → typical trigger → the time/space tradeoff it embodies.

- **Two pointers** — sorted array/string, "find a pair/triplet," in-place reversal or partitioning. `O(1)` extra space instead of nested loops or extra arrays.
- **Sliding window** — "longest/shortest substring or subarray satisfying X," contiguous-range problems. Turns an `O(n²)` brute force (recompute the window from scratch) into `O(n)` by incrementally expanding/shrinking one window, at `O(1)`-`O(k)` extra space.
- **Hash map/set for O(1) lookup — the classic time/space tradeoff** — "have I seen this before," "find the complement," "count frequencies." Explicitly trades `O(n)` extra space for turning an `O(n)` linear search per element into `O(1)`, collapsing an `O(n²)` brute force into `O(n)`. This is the single most common optimization in the "Look for BUD" technique below.
- **BFS/DFS** — trees/graphs, "shortest path in unweighted graph" (BFS), "does a path exist / enumerate all paths" (DFS), connectivity. BFS needs `O(w)` space for the widest frontier (a queue); DFS needs `O(d)` space for the deepest path (recursion or an explicit stack) — pick based on which bound is smaller for your graph shape.
- **Memoization (top-down) vs. tabulation (bottom-up) — the DP space tradeoff**: memoization caches recursive-call results in a hash map/array as you naturally recurse (simple to derive from a brute-force recursive solution; pays `O(depth)` stack space *plus* `O(states)` cache space). Tabulation builds the same table iteratively bottom-up (no recursion, so no stack cost) and, critically, often lets you apply the rolling-variable trick (§2.6) to shrink `O(n)` or `O(n*m)` table space down to `O(1)` or `O(m)` once you see each state only needs the last few rows/entries. Prefer tabulation when you need the memory savings; prefer memoization when the recursive structure is clearer and the state space is sparse (you don't visit every possible state).
- **Base Case and Build** — solve `n=1`, then `n=2`, and look for how to build case `n` from case `n-1`. Naturally produces recursive algorithms; watch for whether the resulting recursion should then be flattened per §2.2.

### Optimization moves, in order, once you have a brute force
1. **Look for BUD** — walk your brute force hunting for a **B**ottleneck (one slow step dominating total time — optimize it first; optimizing anything else is wasted effort until the bottleneck moves), **U**nnecessary work (e.g., not breaking out of a loop once an answer is found, or recomputing a value that has exactly one valid solution instead of deriving it directly), or **D**uplicated work (recomputing the same sub-result repeatedly — cache it, usually in a hash map).
2. **Do It Yourself (DIY)** — solve a large, concrete example by hand/intuition first, then reverse-engineer the algorithm you actually used. Your intuitive shortcuts (e.g., skipping obviously-irrelevant positions) often reveal the optimization directly.
3. **Simplify and Generalize** — solve an easier version of the problem (fewer constraints, simpler data type), then adapt that solution back to the full problem.
4. **Data Structure Brainstorm** — deliberately run down a short list of structures (hash table, heap, tree, two heaps for streaming median, trie, graph) and ask whether each one trivializes the problem.
5. **Best Conceivable Runtime (BCR)** — compute the theoretical floor for the problem (not your current algorithm) and use the gap between your current runtime and the BCR as a hint for how much further you can push, and where the remaining freebie work is (any step at or under the BCR is "free" and not the current priority).

## 4. Sorting Algorithms — time/space cheat sheet

Ties directly back to the space-complexity focus of this skill: the "obvious" choice (merge sort) is not the most memory-efficient one, and the right pick depends on what you know about the data, not just on habit.

| Algorithm | Time (avg / worst) | Space | Notes |
|---|---|---|---|
| Bubble sort | O(n²) / O(n²) | O(1) | Repeated adjacent swaps. Never the right interview answer except as the naive baseline to improve on. |
| Selection sort | O(n²) / O(n²) | O(1) | Repeated linear scan for the next-smallest element. Same role as bubble sort: baseline, not an answer. |
| Merge sort | O(n log n) / O(n log n) | **O(n)** | Guaranteed n log n, but pays for it with a full auxiliary array for the merge step — the classic time/space tradeoff versus quicksort. Stable sort (preserves relative order of equal elements); quicksort isn't. |
| Quicksort | O(n log n) / **O(n²)** | O(log n) | Only O(log n) space (recursion depth for in-place partitioning) — this is *why* it's usually preferred over merge sort despite the worse worst-case time: worst case is rare with a reasonable pivot choice (random or median-of-three), and the space savings are real on every run. |
| Radix sort | O(kn) | O(n + k) | Non-comparison sort — sorts by digit/byte, bucketing repeatedly. Beats the O(n log n) comparison-sort floor *only* because it isn't a comparison sort. Reach for it when keys are bounded-size integers/strings (e.g., sorting by age, by a fixed-width ID) — see the age-sorting example below. |

**Recognizing when a non-comparison sort applies:** if the problem tells you the values are drawn from a small/bounded range (ages 0–120, single-byte scores, fixed-length postal codes), that's a signal to reach for bucket sort or radix sort instead of a general comparison sort — you can hit O(n) instead of the O(n log n) floor that applies to comparison-based sorting. This is the same "look at what's special about the input" instinct as choosing a bit vector (§2.3) over a general hash set.

**Binary search space note:** the recursive formulation is O(log n) space (log n stack frames); the iterative formulation is O(1) space for the identical O(log n) time — the same recursive-vs-iterative space trade as §2.2, just at log n instead of n.

## 5. System Design & Scalability — process, not memorization

For "design X at scale" questions (design TinyURL, design a rate limiter, design a URL crawler). The source material is explicit that these questions test **process and communication**, not recall of a fixed list of components — but a working vocabulary of the components below is what lets you drive that process instead of freezing.

**The five-step process:**
1. **Scope the problem** — list concrete features/use cases before designing anything (e.g., for TinyURL: shorten a URL, redirect, click analytics, custom aliases?). You can't design what you haven't bounded.
2. **State assumptions explicitly** — reasonable scale assumptions (e.g., "1M new URLs/day"), and say them out loud so they can be corrected. "Infinite memory" or "100 users/day" are not reasonable assumptions for a scale question.
3. **Sketch the major components end-to-end** — walk a single request through the system (frontend → backend → data store) before worrying about scale; get the simple version right first.
4. **Identify the bottleneck** — where does this design break under load? (A single DB getting hit on every read is the most common one.)
5. **Redesign around that bottleneck** — usually via one of the concepts below (cache, shard, queue) — then repeat step 4, since fixing one bottleneck often surfaces the next one.

**Component vocabulary — when each one is the answer:**

| Concept | What it does | Reach for it when |
|---|---|---|
| Horizontal scaling (+ load balancer) | Add more nodes, distribute requests across them | Vertical scaling (bigger box) has hit a ceiling, or you need redundancy so one node dying doesn't take the system down |
| Caching | Key-value layer between app and data store, serves hot reads without hitting the DB | Read-heavy workload; some staleness is acceptable |
| Database denormalization | Duplicate data across tables to avoid expensive joins at read time | Joins are the measured bottleneck on a read-heavy path — costs write complexity/consistency for read speed |
| Sharding (partitioning) | Split data across multiple DB machines: **vertical** (by feature/table), **key/hash-based** (by `id mod n` — cheap but rebalancing means moving everything), or **directory-based** (lookup table — flexible but the lookup table itself is a new bottleneck/single point of failure) | Data no longer fits on one machine, or write throughput exceeds one machine's capacity |
| Async processing / queues | Defer slow work instead of making the request wait for it | An operation is slow but its result doesn't need to be synchronous (e.g., re-rendering a "most popular posts" list after a new comment) |
| MapReduce | Parallel map step (emit key/value) + reduce step (aggregate by key) | Processing very large datasets where the work is naturally parallelizable per-key |

**Metrics vocabulary** (don't conflate these three): **latency** = time for one item to travel end-to-end; **throughput** = items completed per unit time; **bandwidth** = the theoretical max throughput under ideal conditions. A "faster pipe" (bandwidth) helps throughput but not necessarily latency — shortening the pipe helps latency but not throughput. Also weigh **availability** (% of time operational) vs. **reliability** (probability of staying operational for a given stretch) and whether the system is **read-heavy** (favors caching) or **write-heavy** (favors queuing writes) — this shapes which component above is the right first move.

There is no single correct design. The interview signal is whether you scope, state assumptions, find the real bottleneck, and iterate — not whether you reproduce a textbook architecture from memory.

## 6. Object-Oriented Design questions

Distinct from SOLID/class-design review (see [`solid-design-principles`](../solid-design-principles/SKILL.md) for that) — this is about designing a class model **from scratch** for an ambiguous real-world or system prompt ("design a parking lot," "design a vending machine," "design a deck of cards").

**Four-step approach:**
1. **Handle ambiguity first** — these prompts are deliberately underspecified to see whether you ask or assume. Clarify scope before designing (an "industrial coffee maker for a restaurant" and a "simple coffee maker for the elderly" are different designs from the same one-line prompt).
2. **Define the core objects/nouns** — the entities the domain is obviously about (for a restaurant: `Table`, `Guest`, `Party`, `Order`, `Employee`).
3. **Analyze relationships** — composition ("has-a," e.g. `Party` has an array of `Guest`), inheritance ("is-a," e.g. `Server`/`Host` inherit `Employee`), and cardinality (one-to-many vs. many-to-many) between the core objects. State cardinality assumptions explicitly — they're easy to get wrong (e.g., assuming one `Party` per `Table` when communal tables mean many-to-many).
4. **Walk through the key actions** — trace a realistic sequence of operations through your objects (a `Party` requests a `Table` from the `Host`, gets seated or queued). This step routinely surfaces objects/methods you forgot in step 2.

**Design patterns worth having ready** (the source explicitly scopes this down — interviewers are testing OOD judgment, not pattern-recall trivia, so don't reach for a named pattern unless it actually fits):
- **Singleton** — exactly one instance, globally accessible (private constructor + static accessor). Reasonable for a genuine single global resource; note it's also widely considered an anti-pattern in production code because it complicates unit testing (implicit global state, hard to substitute a test double) — see the Dependency Inversion discussion in [`solid-design-principles`](../solid-design-principles/SKILL.md) for the production-code version of this caution.
- **Factory Method** — delegate object creation to a method/class instead of the caller calling `new` on a concrete type directly, so the creation logic can vary by subtype without the caller knowing which concrete class it got.

**Don't over-apply:** forcing a "real" design pattern onto every OOD answer is a tell that you're pattern-matching instead of designing for the actual problem — the source material explicitly warns against constantly hunting for "the right pattern" instead of the design that fits.

## 7. Efficiency Review Checklist

Before calling a solution done, or when reviewing someone else's:

- [ ] Have I stated *both* the time and space complexity, in terms I've named unambiguously (no reused `N` for two different input sizes)?
- [ ] Did I account for recursion depth as space, not just explicit data structures?
- [ ] Is there an auxiliary array/list/string/hash structure I built that I don't need after this call returns — could it be avoided, streamed, or shrunk to a rolling window?
- [ ] Am I recomputing anything that could be cached (duplicated work), or doing work past the point where the answer is already known (unnecessary work)?
- [ ] Does my current approach hit the Best Conceivable Runtime? If yes and I'm still using extra space, that's now the priority.
- [ ] If this is DP: could tabulation + rolling variables replace a full memo table?
- [ ] If this is a hot path with large N: would primitive arrays, a bit vector, or a reused buffer meaningfully cut memory, or is that premature given the scale actually involved?
