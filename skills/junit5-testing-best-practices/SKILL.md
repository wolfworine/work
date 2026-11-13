---
name: junit5-testing-best-practices
description: Use whenever writing, reviewing or refactoring Java unit tests with JUnit 5 (Jupiter), Mockito and AssertJ — parameterized tests (@CsvSource, @MethodSource, @CsvFileSource, ArgumentsProvider), loading JSON fixtures with Jackson, decision-table tests for business rules, testing reactive code with Reactor StepVerifier instead of assertions inside subscribe(), mocking time with java.time.Clock instead of mockStatic, avoiding duplicated test bodies, test naming (should_X_when_Y), assertAll/assertThrows, test pyramid, mocks vs stubs vs fakes. Includes a concrete review of the DocumentServiceImpl2Test style (JSON fixtures + @CsvSource).
---

# JUnit 5 Testing Best Practices (Java)

**Sources:** JUnit 6.1.3 user guide pages (annotations, assertions, lifecycle, parameterized tests — note: the docs fetched are **JUnit 6.x**, check your
JUnit version before using `@FieldSource`, `argumentSet`, `@ParameterizedClass`), Project Reactor reference "Testing" (3.8.8), Mockito site + `Mockito.java` javadoc (main branch),
AssertJ docs (3.27.7), Oracle Java 21 `java.time.Clock`, Martin Fowler (*Mocks Aren't Stubs*, *Practical Test Pyramid*, *TestPyramid*, *UnitTest*).
**[summary]** = extracted part of a page — verify. **Not verified:** Mockito `mockStatic` thread-locality/strict-stubs semantics (not in the text read),
the Google Testing Blog article, AssertJ `satisfies`. Reactor's reference page does **not** state *why* asserting inside `subscribe()` is wrong — that argument
below is engineering reasoning (and your own `02-ANALISIS-TESTING.md`), not a quote.

## 1. Verdict on your approach (`DocumentServiceImpl2Test` + JSON fixtures)

**What you did well — keep it**
| Practice | Why it's good |
|---|---|
| Realistic domain fixtures in JSON (`models/productClaim.json`, `attachmentList.json`, `polity.json`) loaded with Jackson | Real-shaped data, readable by analysts, no giant builders in code |
| `@ParameterizedTest` decision-table over business rules (product × amount × dates -> tramo + required docs) | The right tool for rule matrices: many cases, one logic |
| Real collaborators (`DebtCalculator`, `PolityMatcher`, `DocumentMapper`, validators) with mocks only at the repository boundary | Classicist style (Fowler): state/behavior through real objects, doubles only for awkward collaborators (DB) |
| `@ExtendWith(MockitoExtension.class)`, `@DisplayName`, `{displayName}_case_{index}_ok` | Clear names, traceable failures; MockitoExtension gives strict stubs [summary: strictness not verified from docs] |
| Fresh fixture per case in `@BeforeEach` | JUnit lifecycle is per-method; `@BeforeEach` runs before each `@ParameterizedTest` method (annotations page) — each invocation follows the normal lifecycle |

**Problems found in the file (all verified in the code)**
| # | Finding | Impact | Fix |
|---|---|---|---|
| 1 | Assertions are inside `.subscribe(response -> {...})` (lines 150, 213, 269, 314, 355) | If the Mono is ever async (`subscribeOn/publishOn`) or never emits, the test **passes without asserting** | `StepVerifier` (add `io.projectreactor:reactor-test`; **missing from your `build.gradle`**) |
| 2 | `MockedStatic<LocalDate>` mocking `LocalDate.now()` in every test | Static mocking is heavy and hides a design smell (hidden dependency on the system clock) | Inject `java.time.Clock`; test with `Clock.fixed(...)` |
| 3 | Expectations packed as strings `"1-true,10-false"` and split by `-` in the test | Unreadable, untyped, no IDE help; one typo = wrong test | Typed records / JSON scenario file / `ArgumentsAccessor` |
| 4 | 5 test methods with identical bodies differing only in arity (`expected1..3`) | Duplication: any logic change must be edited 5x | One parameterized method; expectations as a list |
| 5 | Only happy paths: no `assertThrows`, no error branches (empty attachments, claim not found, missing claimId) | The error branches of the service are untested | Add negative tests (your own analysis flagged this) |
| 6 | `anyMatch` assertions only prove *some* register matches | Wrong extra/missing documents go unnoticed | Assert the **full expected set** with `containsExactlyInAnyOrder` |
| 7 | Loose `anyList()/anyLong()` matchers and no `verify` on repo calls | Can't tell if the right id/args reached the repo | `eq`/captors where the argument matters (use `verifyNoMoreInteractions` sparingly) |
| 8 | JSON read with `new File("src/test/resource/...")` | Depends on the working directory; the folder `resource` (singular) is not a Gradle resource dir in your `build.gradle` (no `sourceSets` config) | Standard `src/test/resources` + classpath loading |
| 9 | `.java.bak` files in `src/test` | Noise in the repo | Delete (git has history) |
| 10 | `ReflectionTestUtils.setField(debtCalculator, "moneyChangeDollar", ...)` | Tests peek at private state | Constructor injection of the rate (or a config object) |

**Overall:** the *strategy* (JSON fixtures + parameterized decision tables + real collaborators) is sound and is a recognized good pattern; the *execution* has
fragile spots (#1 especially). Fix #1, #2, #3/#4 and #5 and it's a very good style. For rule logic, push cases **down** to small unit tests of each
strategy/validator (Fowler's test pyramid: "push your tests as far down the test pyramid as you can") and keep few orchestration tests.

## 2. JSON fixtures — how to do it well

JUnit has **no built-in JSON argument source** (none described in the parameterized-tests page **[summary]**). Options:

1. **Domain fixtures (what you do)** — load once via a tiny helper, mutate in test:
```java
final class Fixtures {
  private static final ObjectMapper MAPPER = new ObjectMapper().findAndRegisterModules();
  static <T> T read(String path, Class<T> type) {
    try (var in = Fixtures.class.getResourceAsStream("/" + path)) {            // classpath, not File
      return MAPPER.readValue(Objects.requireNonNull(in, path), type);
    } catch (IOException e) { throw new UncheckedIOException(e); }
  }
  static <T> T read(String path, TypeReference<T> type) { ... }
}
```
   Keep fixtures **minimal and named by intent** (`claim_regular_tramo.json`), not one giant shared file mutated by every test. Prefer a **Test Data Builder / Object Mother**
   for variations (a `ProductClaimBuilder` seeded from JSON then `.withAverageBalance(...)`) instead of 6 setters in every test.
2. **Scenario files (recommended for rule matrices)** — one JSON per rule with explicit expected output, fed by `@MethodSource`:
```json
[
  { "name": "001 / cobertura 8 / balance 250.30 -> REGULAR",
    "productCode": "001", "coverage": "8", "averageBalance": "250.30", "currency": "PEN",
    "effectiveDate": "2022-05-10T11:30:40", "claimDate": "2022-10-10T11:30:40", "today": "2022-10-11",
    "expectedStretch": "REGULAR",
    "expectedDocuments": [ {"code": "1", "required": true}, {"code": "10", "required": true} ] }
]
```
```java
record Scenario(String name, String productCode, String coverage, BigDecimal averageBalance, String currency,
                LocalDateTime effectiveDate, LocalDateTime claimDate, LocalDate today,
                String expectedStretch, List<Doc> expectedDocuments) {
  record Doc(String code, boolean required) {}
  @Override public String toString() { return name; }          // shows in the test report
}

static Stream<Scenario> scenarios() {                            // @MethodSource("scenarios")
  return Fixtures.read("scenarios/getDocuments.json", new TypeReference<List<Scenario>>() {}).stream();
}

@ParameterizedTest(name = "{index}: {0}")
@MethodSource("scenarios")
void should_getDocuments_by_rule(Scenario s) { ... }
```
   Benefits: typed, one test body, reviewable diffs, business can edit the file. Use `@CsvFileSource` (classpath resource, `numLinesToSkip`) for flat tables **[summary]**.
3. **Inline `@CsvSource`** only for small, flat, primitive tables. Keep columns typed and few; avoid packing structures into strings.
4. **Golden master / approval files** for large responses: compare the actual JSON to an expected file with `JSONAssert` (non-strict mode forgives reordering/extension; README doesn't list the compare modes) or AssertJ `usingRecursiveComparison()`.
   Never assert large JSON by string equality.

## 3. Reactive code — `StepVerifier`

Dependency: `io.projectreactor:reactor-test` (test scope; version from the Reactor BOM). Verified API: `StepVerifier.create(publisher)`, `expectNext`, `expectNextCount`,
`consumeNextWith(Consumer)`, `expectError*`, `expectErrorMessage`, `verifyComplete()`, `verify(Duration)`, `verifyError(...)`, `then(Runnable)`, `expectSubscription()`,
`StepVerifier.withVirtualTime(Supplier)` (create the Flux **inside** the supplier), `TestPublisher`, `PublisherProbe`.
(`assertNext` is a commonly used alias not mentioned on the reference page; `consumeNextWith` is the documented one.) **`verify()` has no timeout by default** —
set `StepVerifier.setDefaultTimeout(Duration)` or `verify(Duration)` so a never-completing publisher fails instead of hanging.

```java
StepVerifier.create(documentService.getDocuments(1L, "user", idTx))
    .consumeNextWith(r -> {
        assertThat(r.getStretch()).isEqualTo(expectedStretch);
        assertThat(r.getRegisters()).extracting(a -> a.getTypeAttachment().getCode(), a -> a.getTypeAttachment().getRequired())
            .containsExactlyInAnyOrder(tuple("1", true), tuple("10", true));
    })
    .verifyComplete();                                              // fails if nothing is emitted

StepVerifier.create(service.getDocuments(null, "user", idTx))
    .expectError(BusinessException.class).verify(Duration.ofSeconds(2));   // error branch
```
Why not `.subscribe(asserts)`: the test finishes when the method returns; if emission is deferred/asynchronous or never happens, nothing is asserted and the test is green.

## 4. Time, randomness, static state

Oracle: "Best practice for applications is to pass a `Clock` into any method that requires the current instant and time-zone." Use `LocalDate.now(clock)`; in tests
`Clock.fixed(Instant.parse("2022-10-11T00:00:00Z"), ZoneOffset.UTC)`; `Clock.offset(...)` simulates future/past. Don't hard-code `systemDefaultZone()`.
Mockito 5: the inline mock maker is the default ("starting from 5.0.0"), so `mockito-inline` is **not required** for `mockStatic` (your Spring Boot 2.7.5 project
declares it explicitly — fine for older Mockito). Treat `mockStatic` as a last resort for code you can't change.

## 5. Mockito and doubles

- Mockito's own "Remember": **do not mock types you don't own, do not mock value objects, do not mock everything.**
- Fowler's taxonomy: Dummy, Fake (working shortcut, e.g. in-memory DB), Stub (canned answers), Spy (records calls), Mock (expectations; behavior verification). Prefer **stubs/fakes + state
  verification**; use `verify` when the interaction *is* the behavior (publish event, save, send). Mockist tests couple to implementation and can stay green while hiding errors.
- `ArgumentCaptor` with `verify(...).m(captor.capture())` then assert on `getValue()`; `verifyNoMoreInteractions` "not recommended in every test method" — use only when relevant [summary].
- `@Mock`, `@InjectMocks`, `@Spy`, `@Captor`, BDD `given/when`; `MockitoExtension` for JUnit 5.

## 6. AssertJ and JUnit assertions

- AssertJ **[summary]**: `assertThat(x).extracting("a","b").contains(tuple(..))`, `containsExactlyInAnyOrder`, `anySatisfy/allSatisfy`, `usingRecursiveComparison()`
  (`ignoringFields(...)`, `ignoringActualNullFields()`, `withStrictTypeChecking()`), soft assertions with `assertSoftly`. Call `as()/withFailMessage()` **before** the assertion or it is ignored.
- JUnit: `assertAll(...)` reports all failures; `assertThrows(Class, Executable)` returns the exception (assert its message/code); `assertTimeout` runs on the calling thread,
  `assertTimeoutPreemptively` on another (ThreadLocal caveat); messages may be `Supplier<String>`.

## 7. Structure and naming

- One behavior per test; Arrange–Act–Assert / given–when–then; test through the **public interface**, observable behavior not internal structure; don't test trivial getters/setters (Fowler).
- Names: `should_<expected>_when_<condition>`; `@DisplayName` for humans (not inherited), `{index}`/`{arguments}`/`{0}` placeholders in parameterized names; `@Nested` for grouping (not inherited).
- Default lifecycle `PER_METHOD` (new instance per test) — keep tests independent; `@TestInstance(PER_CLASS)` only when you need non-static `@BeforeAll`.
  `@MethodSource` factories must be `static` unless PER_CLASS.
- Speed: commit suite within ~10 minutes (Fowler quoting Beck); unit tests in seconds. Failing high-level test? First write a unit test reproducing the bug.
- Cover error branches: every `throw` in the code under test should have a test.

## 8. Refactor sketch of your test (before -> after)

```java
@ParameterizedTest(name = "{index}: {0}")
@MethodSource("scenarios")
void should_getDocuments_by_business_rule(Scenario s) {
  Clock clock = Clock.fixed(s.today().atStartOfDay(ZoneOffset.UTC).toInstant(), ZoneOffset.UTC);
  var service = new DocumentServiceImpl2(attachmentRepo, claimRepo, polityRepo, new DebtCalculator(RATE_375),
      new PolityMatcher(), new DocumentMapper(), validators, clock);          // Clock injected
  var claim = ProductClaimBuilder.fromJson("models/productClaim.json").with(s).build();

  when(claimRepo.findBySolicitudid(1L)).thenReturn(new LinkedList<>(List.of(claim)));
  when(attachmentRepo.findBySolicitudidOrderBySolicitudid(1L)).thenReturn(byCoverage(allAttachments, s.coverage()));
  when(polityRepo.findByCodigoproductoIn(anyList())).thenReturn(polities);

  StepVerifier.create(service.getDocuments(1L, "user", TX))
      .consumeNextWith(r -> {
          assertThat(r.getStretch()).isEqualTo(s.expectedStretch());
          assertThat(r.getRegisters()).extracting(a -> a.getTypeAttachment().getCode(), a -> a.getTypeAttachment().getRequired())
              .containsExactlyInAnyOrderElementsOf(s.expectedAsTuples());
      })
      .verifyComplete();
}
```
Plus targeted negative tests: `claimId == null`, empty attachments, empty claim list -> `expectError(BusinessException.class)`.
Plus move rule-level cases to per-strategy tests (`DesgravamenValidatorTest`, ...), keeping only a handful of orchestration tests in the service test.

## 9. Checklist

- [ ] No assertion hidden in `subscribe`; `StepVerifier` + `reactor-test` (and a timeout).
- [ ] Time via `Clock`; no `mockStatic` for time.
- [ ] Typed scenario data (records/JSON), no string-packed expectations; one test body per behavior.
- [ ] Fixtures on the classpath (`src/test/resources`), small, intent-named; builders for variations.
- [ ] Full-result assertions (`containsExactlyInAnyOrder`/recursive comparison), not just `anyMatch`.
- [ ] Error branches covered with `assertThrows`/`expectError`.
- [ ] Mocks only at boundaries you don't own; `verify` only where interaction is behavior.
- [ ] Rule logic tested at the lowest level (strategies/validators); few orchestration tests.
- [ ] No `.bak` files; coverage (JaCoCo) read as a hint, not a goal.

Related: `tdd-java-workflow`, `reactive-java-webflux`, `quarkus-testing-standards`, `solid-design-principles`.

## Sources

- https://docs.junit.org/current/writing-tests/ (annotations, assertions, test-instance-lifecycle, parameterized-classes-and-tests)
- https://projectreactor.io/docs/core/release/reference/testing.html
- https://site.mockito.org/ and https://github.com/mockito/mockito/blob/main/mockito-core/src/main/java/org/mockito/Mockito.java
- https://assertj.github.io/doc/ ; https://github.com/skyscreamer/JSONassert
- https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/Clock.html
- https://martinfowler.com/articles/mocksArentStubs.html, /articles/practical-test-pyramid.html, /bliki/TestPyramid.html, /bliki/UnitTest.html
