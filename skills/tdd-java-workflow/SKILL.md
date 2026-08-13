---
name: tdd-java-workflow
description: Use whenever the user asks to write code test-first, mentions TDD/red-green-refactor, wants unit tests written before implementation, asks how to structure a JUnit/Mockito test, needs guidance on naming tests or choosing between a mock/stub/fake, or when an existing test is brittle/overmocked/testing implementation details and needs to be redesigned around behavior instead.
---

# TDD Workflow for Java

Operational guidance for writing Java code test-first with JUnit 5, AssertJ, and
Mockito. This is about the *workflow and testing craft* — the discipline of
writing a failing test, making it pass with minimal code, then cleaning up. It
assumes SOLID and hexagonal-architecture knowledge lives elsewhere; when this
skill touches those topics it does so only enough to explain how tests interact
with them.

## Core discipline: never write production code without a failing test

If you are asked to implement a class, method, or feature in Java and no
instruction says otherwise, default to writing the test first. Do not write
the implementation and then backfill tests — that produces tests that confirm
what the code already does rather than tests that specify what it should do.

## The Red-Green-Refactor (RGR) loop

Treat this as a literal, repeatable sequence. Each iteration should be small —
minutes, not hours.

1. **RED — write a failing test.**
   - Pick the smallest next behavior to specify (see "Choosing the next test"
     below).
   - Write it using Arrange-Act-Assert (see next section).
   - Run it. It must fail. If it passes immediately, either the behavior
     already exists or the test is broken (e.g., the assertion is checking
     something trivially true) — stop and fix that before continuing.
   - A compile error counts as "red." It's fine to write a test against a
     class or method that doesn't exist yet, then create empty skeletons
     (throwing `UnsupportedOperationException` or returning a dummy value)
     just to get to a real, meaningful failure.

2. **GREEN — write the simplest code that makes it pass.**
   - Use the least amount of code that could possibly work: a hardcoded
     return value, an `if` statement, whatever. Do not generalize ahead of
     what the test demands.
   - Do not add behavior the current test doesn't require, even if you know
     it's coming. The next test will drive it out.
   - Run the full test suite, not just the new test — confirm nothing else
     broke.

3. **REFACTOR — clean up while the test stays green.**
   - Now, and only now, engineer the code properly: extract methods, rename
     for clarity, remove duplication, split classes, tighten encapsulation.
   - Refactor both production code and test code — duplicated Arrange logic
     across tests should be extracted into helper methods or `@BeforeEach`.
   - Re-run the tests after every small refactoring step. If a test breaks,
     you changed behavior, not just structure — revert and try again.
   - Do not add new behavior in this step. If you notice a new behavior is
     needed, write it down and go back to step 1 for it.

Repeat. The rhythm — not any single test — is what produces a well-designed
system: each cycle is a checkpoint where the design can be revised cheaply
because a passing test suite gives you permission to change code fearlessly.

### Choosing the next test

Favor the smallest increment that still forces a genuine design decision:
start with the simplest input/output pair for a behavior (e.g., one correct
letter before a full word), then add edge cases (empty input, boundary
values, error conditions) one at a time. This technique — writing several
small, closely related tests that each generalize the implementation a bit
further — is called **triangulation**. If you find yourself wanting to write
a large, complex test to cover "everything at once," that's a sign to break it
into a sequence of smaller RGR cycles instead.

## Structuring a test: Arrange-Act-Assert

Every test method has three parts. Keep them visually distinct (blank line or
comment between them) even though the framework doesn't require it:

```java
class UsernameTest {

    @Test
    void convertsToLowerCase() {
        var username = new Username("SirJakington35179");   // Arrange

        String actual = username.asLowerCase();              // Act

        assertThat(actual).isEqualTo("sirjakington35179");   // Assert
    }
}
```

- **Arrange**: build the object under test and its collaborators, supply
  inputs. If Arrange is messy or huge, that's design feedback — the class
  under test probably has too many dependencies or a constructor doing too
  much.
- **Act**: exactly one call into the code under test. If you need more than
  one call to exercise "one behavior," the API is probably missing a method
  that should do it in one step.
- **Assert**: one logical assertion. Multiple `assertThat` calls are fine if
  they check facets of the *same* outcome (e.g., four fields of one event);
  it's a smell if they check unrelated outcomes — that means the test is
  covering more than one behavior and should be split.

Working through Arrange-Act-Assert *before* the implementation exists is an
outside-in design exercise: you are deciding the constructor signature, the
method name and parameters, and the shape of the return value purely from the
perspective of "what would be pleasant to call," before worrying about how it
works internally.

## The FIRST properties of a good test

Use this as a review checklist for any test you write or inherit:

- **Fast** — milliseconds, not seconds. A slow test suite gets run less often,
  which defeats the purpose of tight TDD feedback.
- **Isolated** — any test, or any subset, in any order, must produce the same
  result. No test may depend on another having run first, on shared mutable
  state, or on execution order.
- **Repeatable** — the same production code always yields the same pass/fail
  result. Sources of non-repeatability to watch for: real random number
  generators, the system clock, real databases, real network calls, real file
  systems.
- **Self-verifying** — the test itself decides pass/fail via an assertion. No
  "print the output and eyeball it."
- **Timely** — written right before the production code it exercises, not
  long before (locks in undiscovered design mistakes) and not long after
  (loses design feedback, tends not to happen at all).

## Naming tests so they document behavior

A test name is documentation. A reader should understand *what the system
does* from the name alone, without reading the body.

Bad: `test1()`, `testGuess()`, `testWord()` — describes nothing.

Good — name the behavior and, where relevant, the condition that produces it:

```java
@Test
void oneCorrectLetter() { ... }

@Test
void rejectsOrderWhenItemIsOutOfStock() { ... }

@Test
void reportsGameOverAfterFiveIncorrectGuesses() { ... }
```

A useful template is `<outcome>When<condition>` or, for the simplest cases,
just `<outcome>` (`convertsToLowerCase`, `oneCorrectLetter`). Whichever
convention you pick, apply it consistently within a test class. The class
name itself should also tell a story — `UsernameTest`, `OrderServiceTest` —
naming the unit of behavior under test, not an implementation detail like
`UsernameImplTest`.

Do not name a test after the method it happens to call (`testGetTotal`);
name it after the business rule the call proves (`totalIncludesAppliedDiscount`).
If you can't come up with a name that describes a behavior, that's often a
sign the test — or the code — is trying to do too much at once.

## Test doubles: stub, mock, fake, or the real thing

A **test double** replaces a real collaborator so a test can control it. The
underlying enabler is always Dependency Inversion — the code under test
depends on an interface, and you inject whichever implementation you want
(stub/mock/fake in tests, real implementation in production). If a class
creates its own collaborator with `new` internally, you cannot substitute a
double for it — treat that as design feedback, not a reason to reach for
reflection tricks.

| Kind | What it does | Use it when | Example |
|---|---|---|---|
| **Stub** | Returns pre-canned values | The SUT *pulls* data from a collaborator (a query) | Repository returning a fixed `Optional<Word>`; a clock returning a fixed `Instant` |
| **Mock** | Records interactions so you can verify a call happened | The SUT *pushes* a command to a collaborator with no useful return value | Verifying `sendEmail(...)` was called; verifying `inventory.reserve(sku, qty)` was called |
| **Fake** | A lightweight working implementation (e.g. in-memory map instead of a database) | You need realistic collaborator *behavior* across several calls, not just one canned answer | `InMemoryUserRepository` backing several sequential test steps |
| **Real object** | The genuine collaborator | It's a value object, or it's fast/deterministic/side-effect-free already | `Money`, `LocalDate`, a pure calculator class |

Rules of thumb, in priority order:

1. **Don't mock value objects.** If it has a content-based `equals()`/
   `hashCode()`, just construct a real instance — mocking it buys nothing and
   adds noise.
2. **Don't mock code you don't own.** Wrap third-party classes behind your
   own interface first, then mock *your* interface. Otherwise a library
   upgrade that removes a method you mocked leaves your tests green while
   production code fails to compile or behaves differently.
3. **Don't overuse mocks.** A test with many mocked collaborators and many
   `verify()` calls is usually mocking implementation detail, not an
   abstraction — it locks in one specific internal structure and breaks on
   any valid refactor. Prefer asserting on the *outcome* (return value,
   resulting state) and reserve `verify()` for the one or two calls that are
   genuinely the point of the test (an irreversible side effect: send this
   email, persist this record, publish this event).
4. **Don't test the double.** If your stub contains complex logic that
   mimics the real collaborator's internals, you're now testing that logic
   against itself. Keep stubs trivially simple — a fixed return value, not a
   simulation.
5. **Prefer a stub/fake over a mock whenever the collaboration is a pull
   (query).** Reserve mocks (and `verify()`) for genuine pushes (commands)
   where there's no return value to assert on.

### Mockito basics

```java
@ExtendWith(MockitoExtension.class)
class UserGreetingTest {

    @Mock
    private UserProfiles profiles;

    @Test
    void formatsGreetingWithNickname() {
        when(profiles.fetchNicknameFor(USER_ID)).thenReturn("Alan"); // stub a query

        var greeting = new UserGreeting(profiles);

        assertThat(greeting.formatGreeting(USER_ID))
                .isEqualTo("Hello and welcome, Alan");
    }
}
```

`when(...).thenReturn(...)` sets up a *stub* response even though it's built
with the `@Mock` annotation — Mockito doesn't force you to pick a vocabulary
up front. `verify(mock).method(args)` is what turns that same object into a
*mock* in the behavioral sense: it asserts a call happened.

### Before/after: fixing an overmocked, brittle test

**Before — brittle, tests the call sequence instead of the outcome:**

```java
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock private InventoryRepository inventory;
    @Mock private PricingEngine pricing;
    @InjectMocks private OrderService service;

    @Test
    void placesOrder() {
        when(inventory.isInStock("SKU-1")).thenReturn(true);
        when(pricing.basePrice("SKU-1")).thenReturn(new BigDecimal("10.00"));
        when(pricing.discountFor("SKU-1")).thenReturn(BigDecimal.ZERO);

        service.placeOrder("SKU-1", 2);

        verify(inventory).isInStock("SKU-1");
        verify(pricing).basePrice("SKU-1");
        verify(pricing).discountFor("SKU-1");
        verify(inventory).reserve("SKU-1", 2);
        verifyNoMoreInteractions(inventory, pricing);
    }
}
```

Problems: it never checks what `placeOrder` actually *produces* (no
assertion on total price, or order status). It verifies query calls
(`isInStock`, `basePrice`, `discountFor`) that were already exercised via
`when(...)` — verifying them again only pins down the exact internal call
sequence. Renaming an internal helper, combining `basePrice`/`discountFor`
into one `priceFor` call, or reordering internal steps breaks this test even
though the observable behavior is unchanged. `verifyNoMoreInteractions` makes
it worse: every new internal call the implementation happens to make now
fails the test, regardless of whether it affects behavior.

**After — asserts on outcome, verifies only the one side effect that matters:**

```java
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock private InventoryRepository inventory;
    @Mock private PricingEngine pricing;
    @InjectMocks private OrderService service;

    @Test
    void confirmsOrderAndReservesStockWhenItemIsAvailable() {
        when(inventory.isInStock("SKU-1")).thenReturn(true);
        when(pricing.priceFor("SKU-1", 2)).thenReturn(new BigDecimal("20.00"));

        Order result = service.placeOrder("SKU-1", 2);

        assertThat(result.status()).isEqualTo(OrderStatus.CONFIRMED);
        assertThat(result.total()).isEqualTo(new BigDecimal("20.00"));
        verify(inventory).reserve("SKU-1", 2); // the irreversible side effect
    }

    @Test
    void rejectsOrderWhenItemIsOutOfStock() {
        when(inventory.isInStock("SKU-1")).thenReturn(false);

        Order result = service.placeOrder("SKU-1", 2);

        assertThat(result.status()).isEqualTo(OrderStatus.REJECTED);
        verify(inventory, never()).reserve(any(), anyInt());
    }
}
```

This version survives any internal refactor that preserves behavior (merging
pricing calls, renaming private methods, changing call order) because it
only pins down what the caller actually observes: the returned `Order` and
the one side effect (`reserve`) that a caller cannot recover from if it's
missed or wrongly duplicated. Two smaller, well-named tests also replaced one
test trying to cover both the happy path and the rejection path.

## How writing the test first shapes the design

Each Arrange-Act-Assert section forces a specific design decision *before*
any implementation exists:

- Arrange forces you to decide what collaborators and configuration the
  object actually needs — a bloated Arrange step is early warning that a
  constructor takes too much.
- Act forces you to design the method signature as its caller wants to use
  it: a good outcome-describing name, few parameters, no boolean flags that
  silently switch behavior, no requirement to call multiple methods in a
  fragile sequence to get one thing done.
- Assert forces you to decide what shape the result takes and how a caller
  extracts what they need from it.

This is naturally a **tell-don't-ask** pressure: because Act is a single call
whose result gets asserted on directly, you're pushed toward objects that
*do things and return outcomes* rather than objects that expose a pile of
getters for the caller to inspect and decide on. Interfaces also tend to stay
small — you only add a method to a collaborator's interface when a specific
test demands it, never speculatively. Full treatment of SOLID and hexagonal
architecture is out of scope here; the short version is that ports (in the
hexagonal sense) are exactly the seams TDD needs — an interface at a port
boundary is where you inject a stub or mock instead of a real adapter
(database, mail server, payment gateway), keeping the domain tests fast and
deterministic while the adapter itself gets covered by a smaller number of
integration tests.

## Two schools of TDD: inside-out vs outside-in

There are two traditional starting points for TDD, and hexagonal architecture
gives them a natural place to meet.

| School | Also known as | Starts from | Strengths | Weaknesses |
|---|---|---|---|---|
| **Inside-out** | Classicist / Chicago TDD (Kent Beck) | A domain component (a class/small cluster deep in the design), building outward | Fast to start — pure Java, JUnit/AssertJ, no web/db/UI setup; good when the design is already familiar; produces fine-grained, fast domain tests | Risk of building components that turn out unneeded (sunk-cost temptation to keep using them anyway); no guarantee the pieces compose into something a user actually needs yet |
| **Outside-in** | London / Mockist TDD (Freeman & Pryce, *Growing Object-Oriented Software, Guided by Tests*) | A simulated external request (HTTP call, form submission), working inward, using test doubles for everything not yet built | Delivers user-visible value fast; produces minimal code, nothing speculative | Application logic can leak into the adapter layer if you stop at "makes the test pass"; if *all* your tests are outside-in, your test suite silently inverts into mostly end-to-end tests, which is the opposite of what the test pyramid wants |

**Hexagonal architecture reconciles both, rather than forcing a choice**: use inside-out for the domain model (the inner hexagon) — it's cheap to test finely there and the components are stable. Use outside-in for adapters (the boundary layer) — an adapter's whole job is satisfying an external contract, so starting from that contract and mocking the domain behind it is the natural fit. The port/adapter boundary *is* the natural test boundary between the two schools — see `java-hexagonal-architecture` for the structural side of this. Don't treat "which school" as a global project decision; it's a per-component choice driven by whether you're building domain logic or a boundary adapter.

## Test pyramid: how much of each kind

Unit tests (FIRST, no external systems, using test doubles at the
boundaries) should vastly outnumber every other kind:

- **Unit tests** — base of the pyramid, the majority of tests. Fast,
  deterministic, isolated from databases/network/filesystem/clock. Can reach
  100% *meaningful* coverage of domain logic when every behavior is
  test-driven.
- **Integration tests** — fewer, slower, verify one adapter really talks to
  one real external system correctly (e.g., the repository implementation
  against a real Postgres instance). Write these for the adapter side of a
  port, not for domain logic already covered by unit tests.
- **End-to-end / acceptance tests** — fewest, slowest, most brittle; exercise
  the whole assembled system the way a real user or client would. Reserve
  these for critical user journeys, not exhaustive case coverage — that's
  what the unit test layer is for.

If you find yourself writing an integration or end-to-end test to cover a
business-rule edge case (e.g., "what if the discount is negative"), that's
usually a sign the case belongs in a unit test instead, with the external
system stubbed out.

**Shape of a database-adapter integration test**: Arrange loads known fixture
data into a real (usually containerized) database before the test runs, Act
calls the adapter method under test, Assert checks the returned domain value.
Fixture-loading libraries (e.g. database-rider's `@DataSet` annotation loading
a JSON/YAML fixture file) or Testcontainers-managed instances both serve the
same purpose — get the database into a known state without hand-written setup
SQL scattered through the test. This is still Arrange-Act-Assert; only the
Arrange step got heavier because a real external system is involved.

```java
@Test
@DataSet("adapters/data/wordTable.json")   // Arrange: known fixture data
void fetchesWord() {
    var adapter = new WordRepositoryPostgres(dataSource);
    String actual = adapter.fetchWordByNumber(27);         // Act
    assertThat(actual).isEqualTo("ARISE");                  // Assert
}
```
Keep exactly one adapter under test per integration test class — this is the
same "one collaborator boundary" discipline as a unit test, just with a real
collaborator instead of a double.

For HTTP/API-level test design beyond this unit-test layer — status codes,
idempotency, auth failure cases, negative/boundary test-case design, and
RestAssured/WebTestClient code patterns — see
`references/api-testing.md`.

## When test-first isn't the automatic right call

Test-first is the default in this skill, but it's not free of trade-offs, and it isn't universal:

- **Test-first is fundamentally a design tool, not just a correctness check.** Each AAA step forces a design decision *before* the implementation exists (see "How writing the test first shapes the design" above) — this is the actual reason to prefer it, more than "catches bugs."
- **Test-later (write code, then tests shortly after) is a legitimate, research-ambiguous alternative** — evidence on TDD vs. test-later defect rates is mixed, and test-later has a gentler learning curve for TDD beginners. Its real risks: as cyclomatic complexity grows, it gets easy to lose track of which execution paths still need a test (nothing forces the count to stay at zero); and "shortly after" quietly becomes "never" under deadline pressure. If you use test-later, keep the gap small — a few lines of code, then its tests — not a whole feature.
- **Never make code coverage a target, only a diagnostic.** The moment coverage becomes a mandated number, Goodhart's law kicks in: people satisfy the metric instead of the goal (the canonical failure mode is a real assertion commented out just to keep a test "passing" and the file's line-coverage up — this happened on a real project, and the test suite went green while a genuine regression shipped). Use coverage reports to find *untested* lines, never as a pass/fail gate on its own.
- **Don't write all your tests upfront as a spec, then implement.** This defeats the entire point — TDD's value is the tight feedback loop of one small test → one small implementation → refactor, learning from each cycle. A giant pre-written test list is waterfall wearing a TDD costume; it gives you no chance to let early feedback change the design of later pieces.
- **Some code genuinely gets less automated-test coverage, and that's a legitimate call, not laziness**: one-off ETL/data-migration scripts (verify manually against representative source data instead), front-end visual/look-and-feel work (automatable interaction tests exist, but "does this look right" still needs a human), and infrastructure-as-code (Terraform et al.) where good test tooling is still immature. Don't force TDD ceremony onto these just for consistency — spend the discipline where it pays for itself.
- **Whatever you skip, understand you're only choosing *when* it gets tested, not *whether*.** Untested code doesn't stay untested — it gets tested by QA, by production incidents, or by users, all of which are more expensive and more damaging to trust than a fast local feedback loop would have been (this is the "shift-left" argument: push the testing timeline as early as possible, because it isn't optional, just deferred).

## Checklist: is this test worth keeping?

Run any test — new or inherited — through these questions. If more than one
answer is "no," the test needs rework, not just a shrug:

- [ ] Does it name a behavior or business rule, not a method name or
      implementation step?
- [ ] Does it fail for exactly one reason? (One logical assertion, one
      behavior under test.)
- [ ] Would it survive a refactor that preserves behavior but changes
      internal structure (renamed private method, merged helper, reordered
      internal calls)?
- [ ] Does it assert on an outcome (return value, resulting state, or a
      genuine irreversible side effect) rather than on the sequence of
      internal calls that produced it?
- [ ] Are all its mocks standing in for abstractions you own, not concrete
      third-party classes or value objects?
- [ ] Is every `verify()` call checking something a caller actually depends
      on, rather than every call the SUT happens to make?
- [ ] Does it run in milliseconds, with no real database/network/clock/
      filesystem/random-number-generator involved?
- [ ] Could you delete the production code entirely and expect this test to
      go red? (If it would still pass, it isn't testing anything.)

If a test fails the "survives a refactor" check specifically because of
over-verification, that's the brittle-mock pattern from the before/after
example above — strip the `verify()` calls down to the one or two
side-effecting commands that matter, and add or strengthen assertions on the
returned outcome instead.
