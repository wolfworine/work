---
name: google-java-style-guide
description: Use whenever writing, formatting, or reviewing Java source code and the user mentions Google Java Style, wants code that passes google-java-format/checkstyle's Google config, asks about naming conventions (UpperCamelCase, lowerCamelCase, CONSTANT_CASE), import ordering, brace style, column limits, Javadoc formatting, or when reviewing a Java diff for style consistency (indentation, whitespace, wildcard imports, missing @Override, switch exhaustiveness).
---

# Google Java Style Guide

Operational reference distilled from the canonical source (`google.github.io/styleguide/javaguide.html`, current) plus the archived March 2014 edition for historical context. This is a **formatting and naming standard**, not an architecture guide — pair it with `java-hexagonal-architecture` / `solid-design-principles` for design-level concerns.

## Source file basics

- Encoding: **UTF-8**. Only the ASCII horizontal space (0x20) is used for whitespace in source — **never tabs** for indentation.
- Special-escape characters (`\b \t \n \f \r \" \' \\`) use their escape sequence, never the equivalent octal or Unicode escape (e.g. write `\n`, not the four-digit Unicode escape for line feed).
- Non-ASCII characters: prefer the actual Unicode character in the source (e.g. the literal "mu" character in `"μs"`) over its numeric Unicode escape — reserve the escape form for non-printable characters, with a comment explaining what it is. Never mangle readability out of fear some tool can't handle UTF-8 — fix the tool.
- File name = the case-sensitive name of its single top-level class + `.java`.

## Source file structure — fixed order

```
1. License/copyright header (if present)
2. package statement
3. import statements
4. exactly one top-level class
```
Exactly one blank line separates each present section.

- `package` and `import` lines are **never line-wrapped** and the column limit does not apply to them.
- **No wildcard imports**, ever (`import java.util.*;` is always wrong), static or otherwise.
- Import grouping (current guide — simpler than the 2014 edition, see note below):
  1. All **static** imports, one group, ASCII-sorted by imported name.
  2. All other imports, one group, ASCII-sorted by imported name.
  3. Exactly one blank line between the two groups if both are present; no other blank lines between imports.
- Exactly one top-level class per file.
- Overloaded methods and multiple constructors of the same class appear **contiguously**, never split apart by other members — even if their modifiers (`static`, `private`) differ.
- Member ordering within a class has no universal rule, but it must be **some** logical order you could explain if asked — "chronological by when I added it" is not a logical order.

> **Historical note:** the 2014 PDF specified a 5-tier import grouping (static → `com.google` → third-party per top-level package → `java` → `javax`). The current guide dropped that ceremony down to just static-vs-non-static. If you're matching an older codebase's convention, check which era it was formatted under before "fixing" its import grouping.

## Formatting

### Braces
- Braces are **mandatory** on `if/else/for/do/while`, even for a single-statement or empty body — no bare `if (x) return;`.
- Nonempty blocks use **K&R ("Egyptian") style**: no break before `{`, break after `{`, break before `}`, break after `}` only if that brace ends a statement/method/class body (no break if followed by `else` or a comma).
- Empty blocks may collapse to `{}` inline (`void doNothing() {}`) **except** inside a multi-block construct (`if/else`, `try/catch/finally`) — there, `} catch (Exception e) {}` on its own concise line is not acceptable; keep K&R form.

### Indentation, statements, column limit
- Block indent: **+2 spaces** per nesting level (never tabs).
- One statement per line — no `a(); b();` on one line.
- **Column limit: 100 characters** (current guide — the 2014 edition let projects pick 80 or 100; 100 is now the fixed standard). Exceptions: unavoidable lines (long URLs), `package`/`import` lines, text-block contents, shell command-lines inside a comment.

### Line-wrapping
- Prime directive: **break at the highest syntactic level possible**.
- Break **before** the symbol for non-assignment "operator-like" tokens: `.` (dot), `::`, the `&` in `<T extends Foo & Bar>`, the `|` in `catch (FooException | BarException e)`.
- Break **after** the symbol for assignment operators (either is acceptable) and the `:` in an enhanced `for`.
- A method/constructor name stays glued to its `(`. A comma stays glued to what precedes it.
- Never break immediately next to a lambda `->` or switch-rule `->`, except a break is allowed right after `->` if what follows is one unbraced expression.
- Continuation lines indent **at least +4** from the start of the original line.

### Whitespace
**Vertical** — exactly one blank line:
- Between consecutive members/initializers of a class (fields may skip this if logically grouped).
- Wherever else this doc requires it (see import grouping above).
- Anywhere else it aids readability — but multiple consecutive blank lines, while allowed, are never required or encouraged.

**Horizontal** — a single space appears *only*:
1. Between a keyword (`if`, `for`, `catch`) and the `(` that follows it.
2. Between a keyword (`else`, `catch`) and the `}` that precedes it.
3. Before `{`, except `@SomeAnnotation({a, b})` and no forced space between `{{`.
4. On both sides of any binary/ternary operator, plus the `&` in bounds, `|` in multi-catch, `:` in enhanced-for, and lambda/switch-rule `->` — but **not** around `::` or `.`.
5. After `,` `:` `;` or a cast's closing `)`.
6. On both sides of a `//` that opens a trailing comment.
7. Between a declaration's type and identifier: `List<String> list`.
8. Optionally just inside array-initializer braces: `{5, 6}` or `{ 5, 6 }` both valid.

**Horizontal alignment is never required**, and there's no obligation to preserve it once it exists — a one-line change is allowed to "ruin" hand-aligned columns; re-aligning everything nearby just to look tidy again creates review noise and merge-conflict risk for no functional gain.

### Grouping parentheses
Omit optional parentheses only when author and reviewer both agree there's zero chance of misreading — don't assume every reader has Java's operator-precedence table memorized.

## Specific constructs

| Construct | Rule |
|---|---|
| **Enum classes** | Line break after each constant's comma is optional. A method-free, undocumented enum may collapse to array-initializer style: `private enum Suit { CLUBS, HEARTS, SPADES, DIAMONDS }`. |
| **Variable declarations** | One variable per declaration — never `int a, b;` (an exception exists for the header of a `for` loop). Declare locals close to first use, not all up front. |
| **Arrays** | `String[] args`, never `String args[]`. Array initializers may optionally format as a block: `new int[] {\n  0, 1, 2, 3\n}`. |
| **Switch** | Contents indent +2. Old-style (`case X:`) statement groups must terminate abruptly (`break`/`return`/`throw`) or carry an explicit `// fall through` comment. **Every switch must be exhaustive** — add a `default` (even empty) if the compiler wouldn't otherwise require one. New-style switch (`case X ->`) is required for switch *expressions*. |
| **Numeric literals** | `long` literals use uppercase `L`: `3000000000L`, never `3000000000l` (looks like a 1). |
| **Text blocks** | Opening `"""` on its own new line; closing `"""` on its own line at matching indentation; content may exceed the column limit. |

### Annotations
- Class/package/module annotations: one per line, immediately after the Javadoc block.
- Method/constructor: same, one per line — **except** a single parameterless annotation may share the signature's first line: `@Override public int hashCode() { ... }`.
- Field annotations: multiple annotations may share one line: `@Partial @Mock DataLoader loader;`.
- Type-use annotations sit immediately before the annotated type: `final @Nullable String name;`.

### Comments
- Block comments indent with the surrounding code; multi-line `/* */` comments align every continuation line's `*` under the opener's `*`. Never box comments in asterisk borders.
- `TODO` format: `// TODO: <bug/issue link> - <what and why>` — always capitalized `TODO`, always with a way to trace the context later, never a bare "TODO: fix this."

### Modifier order
`public protected private abstract default static final sealed non-sealed transient volatile synchronized native strictfp` (module `requires` directives: `transitive static`).

## Naming — the actual conventions people get wrong

| Identifier | Case | Notes |
|---|---|---|
| Package | all lowercase, no underscores | `com.example.deepspace`, not `deepSpace`/`deep_space` |
| Class / Interface | `UpperCamelCase` | Nouns (`ImmutableList`) or, for interfaces, sometimes adjectives (`Readable`) |
| Test class | `UpperCamelCase` + `Test` suffix | `HashImplTest` for a class testing `HashImpl` |
| Method | `lowerCamelCase` | Verb/verb phrase: `sendMessage`, `stop` |
| Test method | `lowerCamelCase`, underscores allowed to separate components | `transferMoney_deductsFromSource` |
| Constant | `UPPER_SNAKE_CASE` | Only for `static final` fields that are **deeply immutable with no side-effecting methods** — see trap below |
| Non-constant field | `lowerCamelCase` | Same even if `static` |
| Parameter / local variable | `lowerCamelCase` | Avoid one-character names (locals may use them only for loop/temp vars) |
| Type variable | `E`, `T`, `X`, `T2` **or** `RequestT`/`FooBarT` | Single capital [+ digit], or ClassName + `T` |

**The constant-naming trap:** a field being `static final` does NOT make it a constant for naming purposes. `static final Set<String> mutableCollection = new HashSet<>();` is NOT a constant (the referent is mutable) — it stays `lowerCamelCase`. Only things like `static final int NUMBER = 5;` or `static final ImmutableList<String> NAMES = ImmutableList.of(...)` earn `UPPER_SNAKE_CASE`. Merely *intending* not to mutate something is not enough.

No special prefixes/suffixes anywhere: `name_`, `mName`, `s_name`, `kName` are all off-style.

Unnamed variables/parameters use the `_` syntax wherever legal: `Predicate<String> alwaysTrue = _ -> true;`.

### Camel-case conversion algorithm (for ambiguous acronyms/compounds)
1. Convert to plain ASCII, drop apostrophes.
2. Split into words on spaces/punctuation.
3. Lowercase everything, including acronyms, then uppercase only the first letter of each word (all words for UpperCamel, all-but-first for lowerCamel).
4. Join.

| Prose | Correct | Wrong |
|---|---|---|
| "XML HTTP request" | `XmlHttpRequest` | `XMLHTTPRequest` |
| "new customer ID" | `newCustomerId` | `newCustomerID` |
| "supports IPv6 on iOS?" | `supportsIpv6OnIos` | `supportsIPv6OnIOS` |

## Programming practices

- **`@Override` is mandatory** everywhere it's legal — overriding a superclass method, implementing an interface method, an interface re-specifying a super-interface method, or a record's explicit accessor override. Only skip it when the parent method is `@Deprecated`.
- **Never silently swallow a caught exception.** Log it, or if it's genuinely believed impossible, rethrow as `AssertionError`. If truly no action is correct, say why in a comment:
  ```java
  try {
    int i = Integer.parseInt(response);
    return handleNumericResponse(i);
  } catch (NumberFormatException _) {
    // it's not numeric; that's fine, just continue
  }
  return handleTextResponse(response);
  ```
- **Static members are called through the class, never through an instance reference:**
  ```java
  Foo.aStaticMethod();          // good
  aFoo.aStaticMethod();         // bad — reads like an instance call
  somethingThatYieldsAFoo().aStaticMethod(); // very bad — hides a wasted allocation
  ```
- **Never override `Object.finalize()`** — it's deprecated for removal; there is no legitimate reason to reach for it in new code.

## Javadoc

- **Required at minimum** for every visible (public, or protected-in-a-visible-class) class, member, and record component — with two exceptions: (1) truly self-explanatory accessors like `getFoo()` where there is genuinely nothing more to say than "the foo" (this is not a license to skip docs on anything non-trivially named — `getCanonicalName()` still needs a real explanation); (2) method overrides don't always need their own Javadoc.
- Every Javadoc block opens with a **summary fragment** — a noun/verb phrase, not a full sentence, capitalized and punctuated as if it were one. `/** @return the customer ID */` is wrong; use `/** Returns the customer ID. */` or the newer `/** {@return the customer ID} */`.
- Block tags appear in this fixed order when present: `@param`, `@return`, `@throws`, `@deprecated` — none of them may have an empty description.
- One blank "line" (containing only the aligned `*`) separates paragraphs and precedes the block-tag group; every paragraph after the first opens with `<p>` (no following space).
- Single-line form `/** Short doc. */` is fine whenever the whole comment fits on one line **and** there are no block tags.

## Reviewer checklist

- [ ] No wildcard imports; static imports and regular imports each ASCII-sorted, one blank line between the two groups.
- [ ] No tabs; 2-space block indent; 100-column limit (barring the stated exceptions).
- [ ] Braces present on every `if/for/while/do`, K&R style, no naked single-statement bodies.
- [ ] Every `switch` is exhaustive (has `default` or is provably complete); old-style fall-through is commented.
- [ ] `@Override` present everywhere legal.
- [ ] No caught-and-ignored exception without a comment explaining why (or it's a test's `expected`/`_` idiom).
- [ ] Static members invoked via `ClassName.member`, not through an instance.
- [ ] `UPPER_SNAKE_CASE` used only for genuinely immutable constants — check that a "constant" field's referent can't actually mutate.
- [ ] No stray `mFoo`/`s_foo`/`foo_`/`kFoo` style prefixes/suffixes.
- [ ] Visible classes/members have Javadoc with a proper summary fragment (not `@return X` as the whole doc).
