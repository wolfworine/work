---
name: ai-assisted-software-development
description: Use whenever the user wants guidance on how to prompt an AI coding assistant more effectively, is starting a new feature/project and wants a spec-first (not vibe-coded) workflow, wants help writing a clear task prompt for code generation/review/refactoring/testing/debugging/documentation, needs to turn meeting notes or a rough idea into user stories and acceptance criteria before coding, wants to prime an agent with project context so it stops hallucinating conventions, or is deciding how much upfront specification/ADR work to do before letting an agent write code.
---

# AI-Assisted Software Development

Operational guidance for working with AI coding assistants (Claude Code, Copilot, Cursor, ChatGPT, etc.) as a professional tool rather than a slot machine. Two halves: (1) a library of concrete, reusable prompt patterns by task type, and (2) a Spec-Driven Development (SDD) workflow for going from idea to code without "vibe coding."

Core stance underlying everything below: the AI is a copilot, not an autopilot. It generates plausible patterns; it does not know your business goal, your architecture, or what "done" means. The person or agent directing it is responsible for defining the target, auditing every output, and never merging code nobody understands.

## 1. The anatomy of a good prompt

Before reaching for a template, check a prompt has these elements (skip what's irrelevant, but check for it):

1. **Role** — who the model should "be" (`"Act as a senior backend developer specialized in security"`). Sets expertise level and tone; a security expert and a patient teacher will answer the same question very differently.
2. **Objective/Task** — the single concrete thing you want, stated as an action ("create a function that validates emails"), not a topic ("tell me about validation").
3. **Context** — stack, framework, existing conventions, constraints, what the surrounding system already does. This is the single highest-leverage addition: "make it fast" produces generic code, "reduce this from O(n²) to O(n log n), input is a 50k-row list" produces a specific fix.
4. **Steps/Instructions** (for multi-part tasks) — break complex asks into an ordered checklist so the model can't skip a piece and you can tell which step failed if the output is wrong.
5. **Output format** — code blocks only vs. explanation, one file vs. many, a table, a specific docstring style. State it or you'll spend time reformatting the answer.
6. **Constraints** — things that must NOT change ("keep the external behavior identical," "no new dependencies," "follow the Airbnb style guide").
7. **Clarification clause** (optional but valuable) — "if information is missing, ask me before assuming." Prevents silent hallucination on missing details.

## 2. Ten operating principles

1. **Define the objective before writing the prompt.** If you can't state the desired outcome in one sentence, the prompt will be vague too.
2. **Specificity beats ambiguity.** "Optimize this code" is a weak prompt. "Refactor this function to reduce cyclomatic complexity and remove global state" is a strong one.
3. **Divide and conquer.** One monolithic prompt for a complex problem produces mediocre results. Split into focused sub-prompts (design → implement → test → document) and compose the outputs.
4. **Treat it as a dialogue, not a transaction.** The first answer is rarely final — use it to refine the next prompt instead of accepting or discarding wholesale.
5. **Use positive instructions.** "Write concise code with explicit variable names" works better than "don't write complicated code with short names." Models follow directives toward a target more reliably than lists of things to avoid.
6. **Ask it to "think step by step" for reasoning-heavy tasks** (debugging, architecture, algorithm design). This chain-of-thought cue measurably improves correctness on multi-step logic.
7. **Verify, never trust blindly.** Treat every generated line as a suggestion from a talented but occasionally-wrong colleague. Understanding, testing, and validating correctness and security is always the developer's job, not the model's.
8. **Build a personal prompt library.** When a prompt produces an excellent result, save and categorize it. This is literally what section 3 of this skill is — keep extending it with your own high-value patterns.
9. **Know the model's limits.** No real-time information (unless tool-augmented), a training cutoff, potential bias from training data, and no real semantic "understanding" — it manipulates patterns. Use it to amplify your judgment, not replace it.
10. **Experiment deliberately.** Try different personas, different output formats, different levels of decomposition, and notice what changes the result. Prompt engineering is empirical, not theoretical.

## 3. Prompt pattern library by task type

Each pattern below is a template — fill the brackets, drop sections that don't apply, keep the role + context + explicit deliverables structure.

### 3.1 Architecture / system design ("the Architect")
Use before writing any code for a new project or non-trivial feature.
```
Act as a senior software architect with 15+ years designing scalable systems.
Design the complete technical architecture for the following project:

## Project
[2-3 sentences describing what it does and for whom]

## Key requirements
- Expected users: [number]
- Application type: [web / mobile / API / CLI]
- Technical constraints: [mandatory stack, or "none, recommend one"]

## Deliverables
1. Recommended stack (frontend, backend, database, infra) — justify each choice in one line.
2. Initial folder structure (file tree).
3. Data model: main entities, key fields, relationships.
4. Main user flow as Step 1 -> Step 2 -> ... .
5. The 3-5 most important architectural decisions and why.
6. 2-3 technical risks and how to mitigate them.
```

### 3.2 Code generation ("the Builder")
Use once you know exactly what to build. Specificity is what separates usable output from a toy example.
```
Act as a senior developer specialized in [language/framework].
Implement the following:

## Functionality
[Exact behavior. E.g.: "A POST /api/auth/register endpoint that accepts email and
password, validates fields, hashes the password with bcrypt, stores the user in
PostgreSQL, and returns a JWT."]

## Technical context
- Stack: [language, framework, ORM]
- Database: [type, schema exists? paste it if so]
- Conventions: [naming/style, or "use standard conventions"]

## Delivery format
Code in separate blocks per file, path as a heading. End with a short
"How to test this" section with exact steps.

## Requirements
1. Production-ready code, not a simplified example.
2. Full input validation and error handling.
3. Single Responsibility Principle.
4. Types/interfaces where the language supports them.
5. Comments only where the logic isn't obvious.
6. If an external dependency is needed, name it with its install command up front.
```

### 3.3 Debugging ("the Detective") — chain-of-thought
The single most valuable addition here is "what I already tried" — without it the model re-suggests the obvious fix you already ruled out.
```
Act as an expert debugger. Analyze this problem methodically.

## The problem
- Expected behavior: [...]
- Actual behavior: [...]
- Exact error message (if any): [...]
- When it happens: [always / intermittently / only in production]

## Relevant code
[paste it]

## Additional context
- Language/framework: [...]
- What I already tried: [list — this is what steers the model away from the obvious/already-ruled-out fix]

## How to respond
1. Hypotheses: list 3 possible causes, ordered by likelihood.
2. Line-by-line analysis: point to exactly where the failure could be.
3. Root cause: identify the most likely one and explain WHY it produces the observed symptom.
4. Fix: corrected code with the changes highlighted.
5. Prevention: a practice or pattern to avoid this class of bug in the future.
```
Related narrower prompts: "there's an infinite loop, walk me through how you'd isolate it," "this endpoint returns 500 with no details, how would you find the root cause," "this compiles and runs but the output is wrong, how would you isolate the logic error."

### 3.4 Code review ("the Critic")
Run this even on code you think is already fine — security and performance issues are rarely obvious to the author.
```
Act as a demanding but constructive senior code reviewer. Review this as if it
were a pull request on a professional team.

## Code
[paste it]

## Context
- Language/framework: [...]
- What this code does: [...]
- This is: [API / frontend / service / script]

## Analyze each dimension
1. Security — SQL injection, XSS, sensitive data exposure, hardcoded secrets.
2. Performance — N+1 queries, O(n²) operations, unnecessary loads.
3. Clean code — single responsibility, naming, duplication.
4. Patterns/structure — appropriate patterns for the framework.
5. Error handling — are edge cases handled or silently swallowed?

## Output format
For each dimension: Status (Good / Needs work / Problem). If Problem: explain
what/where and show the corrected code.
End with an overall score (1-10) and a one-line summary.
Close with the 3 highest-impact changes you'd make if you could only pick 3.
```

### 3.5 Refactoring ("the Optimizer")
The rule embedded in the template ("don't accept a change you don't understand") is the actual safety mechanism — enforce it.
```
Act as a performance/clean-code engineer specialized in [language/framework].
Refactor the following code. It works, but needs improvement.

## Current code
[paste it]
## What it does
[brief description]
## What concerns you
[pick any: "it's slow" / "hard to read" / "hard to extend" / "not sure it scales" / "too much duplication"]

## Deliverables
- Full refactored code.
- Change table: What changed | Why | Expected impact.
- If performance-related, estimate complexity before (e.g. O(n²)) and after (e.g. O(n log n)).
- Justify any new dependency or pattern introduced.

## Rules
1. Do not change external behavior — same inputs must produce the same outputs.
2. Explain every change; don't hand back new code without a reason for each diff.
3. Show before/after for each modified block.
```

### 3.6 Test generation ("the Shield")
The model will push you toward edge cases you wouldn't have thought to test yourself — that is where most of its value is here.
```
Act as a senior QA engineer specialized in [test framework].
Write a complete test suite for:

## Code under test
[paste it]
## What it does
[brief description]
## External dependencies
[APIs, DB, services — list what needs mocking]

## Coverage requirements (all four mandatory)
1. Happy path (min 2 tests).
2. Edge cases: empty/null inputs, extreme values, wrong types, special characters (min 3 tests).
3. Error handling: controlled failure where it should fail (min 2 tests).
4. Integrations: mocked dependencies, verifying they're called with correct arguments.

## Format
Descriptive test names stating what they verify. Group with describe/context
blocks. Include needed mocks/fixtures. End with a summary list of all scenarios covered.
```

### 3.7 Documentation ("the Narrator")
Optimize for "how do I run this in under 30 seconds" — that's the question a README actually needs to answer.
```
Act as a senior technical writer experienced in open-source documentation.
Generate complete technical documentation for:

[paste code, or describe the project/modules if too long to paste]

## Context
- Project name: [...] / Stack: [...] / Audience: [team / OSS contributors / future you]

## Generate
1. README.md — problem it solves (2-3 sentences), prerequisites, copy-pasteable
   install steps, quick-start example, project structure with description per
   folder, env vars table (name/description/example/required), how to run tests,
   how to contribute.
2. Inline docs — docstrings/JSDoc per public function: purpose, params
   (name/type/description), return value, exceptions, usage example.
3. API guide (if applicable) — per endpoint: method, route, description,
   params, example request/response, possible errors.

Direct, technical tone. No filler sentences.
```

### 3.8 Codebase / repository onboarding
Distinct from code review: this is for understanding an unfamiliar codebase (yours or a dependency) fast, before touching it. A vague "analyze this repo" prompt gets you a shallow summary; the structured version below gets you an actionable map.
```
Act as a senior software engineer specialized in codebase analysis.
Deliver a structured, actionable analysis of {repo/module}.

## Output
1. Repo map — table: [Path/File] [Main responsibility] [Language/stack] [Internal/Public]. Flag which folders are core, which are support (tests/utils/docs), which are config.
2. Main execution flow — how the app starts (entrypoints), and in 150 words or
   less, the typical path from input (e.g. HTTP request) to output (e.g. JSON response).
3. Dependencies and APIs — main external dependencies and their role; which
   modules are exposed as a public API.
4. Hotspots — files with the most complexity/coupling/centrality; 2-3 technical
   risks and a suggested incremental refactor.
5. Recommended next step — how a new developer could make a useful first contribution.

Mark anything not determinable from the source as "not available." Do not
narrate your process — deliver only the final result.
```
Escalate to a "deep research" mode (Gemini Deep Research, ChatGPT extended thinking, etc.) when the above isn't enough — those modes run for several minutes and produce a much more thorough report with cited sources, which is worth it once per unfamiliar dependency/codebase, not for routine lookups.

### 3.9 Security review
A plain "build me a login system" prompt reliably produces hardcoded default credentials and broken access control (pages are reachable by URL with no auth check) — because the model is pattern-matching on public training code, which includes plenty of insecure examples. Never accept default-generated auth/access-control code without this pass.
```
Act as a cybersecurity expert. Scan this code for common vulnerabilities:
SQL injection, XSS, CSRF, outdated crypto, missing input validation, broken
route authorization, hardcoded secrets, weak/default credentials.
For each vulnerability found: explain the risk and how to mitigate it.
[paste code]
```
Bake prevention into generation prompts up front rather than only catching it after: state explicitly "enforce strong password policy and rate-limit failed logins," "hash passwords, never store plaintext," "every request to a protected resource must be authorized server-side" as constraints in the code-generation prompt (§3.2), not as an afterthought review.

### 3.10 Technical spec / ADR generation
Bridges into the SDD workflow (§5). Use to turn a rough idea into the SPEC.md and first ADR before generation.
```
Act as a world-class software engineer. Draft a technical specification for:
[system/feature description]
Include: executive summary, architecture, components, APIs, data model,
security, implementation notes, risks.
```

## 4. Priming an agent with project context

An agent without project context hallucinates: it will invent database columns, variable names, or code styles that don't exist in your project, because it's filling gaps with generic patterns from training data. Fix this once per project, not once per prompt:

- **A context/source-of-truth folder.** Put the real schema (SQL DDL), API contracts, or other ground-truth artifacts in a `context/` (or similar) folder in the repo. An agent that can read the real DB schema stops guessing table/column names and writes correct queries without you describing the schema in every prompt.
- **A root agent-instructions file** (`CLAUDE.md`, `AGENTS.md`, or equivalent — the exact filename varies by tool but the convention is now cross-tool). Put in it: code style rules (naming convention, formatting), where to find sources of truth ("the DB schema lives in /context/database.sql"), and the project's purpose/mission so the agent's decisions are guided by intent, not guesswork. Measured effect: the identical prompt run against the same codebase produces standards-conformant output with this file in place, and inconsistent style/no docs without it.
- Regenerate/update this file when conventions change — a stale one is worse than none, because it actively misleads the agent.

## 5. Matching the model/agent to the task

Not every step needs the most powerful (and most expensive/slowest) model. Route by task weight:
- **Heavy reasoning** (deep security audits, complex architecture, hard debugging) → the strongest/most expensive reasoning-tier model available.
- **Default day-to-day work** (most generation, analysis, ADR writing, review) → the mid-tier flagship model — the best cost/capability balance for most tasks.
- **High-volume, low-complexity tasks** (classification, quick lookups, boilerplate, a PM-style requirement breakdown) → the fastest/cheapest tier.
When orchestrating multiple specialized agents (an architect agent, a security-auditor agent, a PM agent, a framework-mentor agent), assign each one the cheapest tier that doesn't degrade its output — this is a cost lever, not just a speed one.

## 6. Requirements-to-code pipeline (lightweight alternative/on-ramp to full SDD)

For a single feature that doesn't warrant the full SDD ceremony in §7, this shorter pipeline still avoids vibe coding:

1. **Raw input** — meeting notes, a Slack thread, a rough idea. Expect it to be unstructured.
2. **User stories** — convert to `As a [role], I want [action], so that [benefit]`. Prompt: *"Generate user stories in the format AS (role), I WANT (action), SO THAT (benefit) based on these meeting notes."* Keep each story small enough to build in one short cycle.
3. **Acceptance criteria in Gherkin** — convert each story into `Given/When/Then` scenarios. Prompt: *"Generate acceptance criteria in Gherkin style. Only the minimum criteria needed to guarantee the feature meets the requirements."* This is the artifact that removes ambiguity — "should be fast" is not a criterion, "p99 latency < 300ms" is.
4. **Ticket creation** — turn the story + criteria into a tracker ticket (Jira/Linear/Asana), ideally via direct tool integration so this step is mechanical, not manual re-typing.
5. **TDD implementation** — on an isolated branch: write the tests first (from the Gherkin criteria — they translate almost directly into test names/assertions), confirm they fail (red), then implement until they pass (green). Giving the agent the acceptance criteria before it writes code is what keeps it from inventing its own interpretation of "done."

## 7. Spec-Driven Development (SDD) — full workflow

The alternative to vibe coding ("fire a prompt, copy the result, cross your fingers") for anything beyond a small isolated feature. Evidence cited by the source material for why this matters: unspecified AI-generated code carries security vulnerabilities at a high rate, and a precise spec lets an agent produce clean code in one pass where an unspecified one needs many corrective iterations — each iteration re-sending context, multiplying token cost by an order of magnitude.

### 7.1 Six pillars
1. **Spec before code** — define the problem, flows, and contracts before the AI generates a single line.
2. **Versioned decisions** — ADRs capture *why*; the AI gets context instead of improvising.
3. **Coordinated agents** — use specialized models per role (security audit vs. code generation vs. domain/UX validation) rather than one general model for everything.
4. **Verifiable metrics** — tokens, cost per change, test coverage: tracked from sprint 1, not retrofitted.
5. **No vibe coding** — unspecified AI generation produces architecture drift and technical debt within weeks, not months.
6. **Senior supervision** — the AI amplifies the architect; a senior still validates the spec, the ADRs, and every critical decision. It never validates itself.

### 7.2 The seven-step flow (idea → code)
Each step: responsible party → artifact produced → exit criterion.

| # | Step | Owner | Artifact | Exit criterion |
|---|------|-------|----------|-----------------|
| 1 | Problem & context | Senior architect | Problem Statement Doc | All stakeholders approve |
| 2 | Flow mapping | Architect + Product | Sequence diagram (Mermaid/PlantUML), happy path + all error paths | Edge cases documented |
| 3 | Detailed technical SPEC | Senior architect | `SPEC.md`, versioned in git | Reviewed by peer + QA |
| 4 | AI-agent validation | AI orchestrator | Multi-agent report (security/domain/UX) | Zero security blockers |
| 5 | Code generation | AI + dev | PR with code + tests | Coverage >80%, green build |
| 6 | Review + ADR | Senior architect | Updated ADR, approved PR | Spec fulfilled 100% |
| 7 | Deploy + observability | DevOps + architect | Metrics dashboard | SLOs defined in the spec are met |

### 7.3 Anatomy of a good SPEC

**Include:**
- Problem description and business context
- User flows — happy path *and* error paths
- API contracts: endpoints, schemas, response codes
- Explicit business rules and validations
- Measurable acceptance criteria
- Security and compliance considerations
- Performance constraints (latency, throughput)

**Avoid:**
- Ambiguous language ("should be fast")
- Undocumented implicit flows
- Assumptions about current system state left unstated
- Implementation decisions with no justification
- Subjective acceptance criteria
- Omitted edge cases / error states
- A spec that's allowed to drift out of sync with the code (worse than no spec — it creates false confidence)

**Minimal SPEC skeleton** (adapt fields per domain):
```
CONTEXT: [current state, why this is needed now, who's affected, deadline]
MAIN FLOW: [step -> step -> step, one line]
API CONTRACTS: [method /path -> request -> response(s), including error codes]
BUSINESS RULES: [explicit numeric/logical rules, limits, lockout policy, audit requirements]
ACCEPTANCE CRITERIA: [coverage target, security requirement, latency budget, no-regression statement]
```

### 7.4 ADRs (Architecture Decision Records)

"Decisions that aren't documented don't exist for the AI." Each ADR: title, status (Proposed/Accepted/In review/Superseded), Context, Decision.
```
ADR-00N: [decision title]                              STATUS: Accepted
Context: [why this came up — what problem/incident forced the decision]
Decision: [what was decided, concretely — not just the direction, the rule]
```
Examples of the kind of decision worth an ADR: adopting SDD itself after a costly vibe-coded sprint; which model handles which role in a multi-agent setup; where specs live in the repo and what merging requires; a team's token/cost budget policy per feature.

### 7.5 Risk of skipping SPEC (use to justify the upfront cost)

| Severity | Risk | Why it happens | Mitigation |
|----------|------|-----------------|------------|
| High | Architecture drift | AI code silently diverges from the intended architecture; undetected until unmaintainable | Spec + architect review on every PR |
| High | Dead end | Thousands of lines nobody on the team understands; can't be maintained, extended, or debugged | Spec limits scope; mandatory review |
| High | Critical vulnerabilities | Unspecified AI code has a materially higher rate of injection/XSS/weak-auth bugs | Security agent validates the spec + pentesting |
| Medium | Inefficient token spend | Every correction re-sends full context; iterations multiply cost | Spec up front + per-sprint cost alerts |
| Medium | Stale spec | Worse than no spec — produces false confidence and decisions based on wrong information | PR merge requires an updated spec |
| Low | Documentation overhead | A spec takes 1-2 days per feature; teams under pressure try to skip it | Predefined templates; cost recovered on the very first avoided rework cycle |

### 7.6 The senior's non-negotiables
1. **Spec first** — define the full problem before burning tokens.
2. **AI to analyze, human to decide** — the AI validates and proposes; the architect decides.
3. **Document in ADRs** — every architectural decision recorded with its context.
4. **Senior supervision** — the senior reviews the implementation and validates exit criteria; this step is never delegated to the AI itself.

## 8. Applying this skill

- Drafting a prompt for someone → pick the matching template from §3, fill it in with their actual context, don't hand back a generic version.
- New feature/project request, scope beyond a single function → recommend §6 (lightweight) or §7 (full SDD) depending on size/risk, and produce the first artifact (user stories, or the Problem Statement) rather than jumping to code.
- User says "just build it," code touches auth/payments/PII, or scope is genuinely unclear → push back toward a spec first; this is exactly the vibe-coding failure mode both source documents warn about.
- Long-lived project with repeated agent mistakes about conventions → recommend §4 (context file / source-of-truth folder) before anything else.
