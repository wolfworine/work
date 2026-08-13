---
name: sql-fundamentals
description: Use whenever writing, reading, or reviewing a SQL query in any language/framework (raw SQL, an ORM's generated SQL, a query builder) — choosing a JOIN type, deciding between a subquery/CTE/JOIN, debugging why GROUP BY or a NOT IN subquery returns wrong/empty results, reasoning about NULL comparisons, designing a schema's normalization level, writing a multi-statement transaction, or explaining why a query returns duplicate/missing rows. Engine-agnostic (ANSI SQL — applies equally to Postgres, MySQL, SQL Server, SQLite). For PostgreSQL-specific syntax (EXPLAIN ANALYZE, JSONB, Postgres data types, connection pooling), see `postgresql-practical-reference` instead — this skill deliberately stays at the language/paradigm level.
---

# SQL Fundamentals

Operational reference for SQL as a query language, independent of database engine. Distilled from *Learning SQL* (Alan Beaulieu) and *SQL QuickStart Guide* (Walter Shields) for query mechanics, joins, subqueries, transactions, and indexing/constraint basics; supplemented with standard industry practice for topics neither source book covers in depth (window functions, CTEs, and formal normalization forms — flagged explicitly below). Applies to any codebase that touches a relational database, regardless of the host language.

**Companion skill**: `postgresql-practical-reference` owns everything Postgres-specific (EXPLAIN plans, JSONB, TIMESTAMPTZ vs TIMESTAMP, isolation-level mechanics, connection pooling internals). This skill covers the SQL that looks the same everywhere; reach for that one when the question is "how does *Postgres* do X."

## Mental model: clauses execute in a different order than you write them

You write a query in this order:
```sql
SELECT ... FROM ... WHERE ... GROUP BY ... HAVING ... ORDER BY ...
```
The server *logically* evaluates it in a different order:
```
FROM/JOIN  →  WHERE  →  GROUP BY  →  HAVING  →  SELECT  →  ORDER BY  →  LIMIT/OFFSET
```
This explains several things that otherwise look arbitrary:
- You can't reference a `SELECT`-clause column alias in the same query's `WHERE` clause (WHERE runs before SELECT) — but you *can* reference it in `ORDER BY` (which runs after SELECT). Engine support for the alias-in-ORDER-BY case varies; when in doubt, repeat the expression.
- `WHERE` filters individual rows *before* grouping; `HAVING` filters *groups* after aggregation — see the GROUP BY section below for why this means they can't be swapped.
- The optimizer is free to reorder joins/scans internally to whatever's cheapest — the logical order above is about *what the result means*, not the physical execution plan.

The `FROM` clause is more than "which table": it defines every table *and* how they link (join conditions), and "table" itself covers three things — a real (permanent) table, a subquery's result set (a derived table, scoped to just this query), and a view (a stored query that looks like a table but has no data of its own).

## JOIN types — decision table

| Join | Returns | Use when |
|---|---|---|
| `INNER JOIN` | Only rows with a match on both sides | Default choice — you only want rows that exist in both tables |
| `LEFT [OUTER] JOIN` | All rows from the left table, matched columns from the right (`NULL` where no match) | You want every row from the "primary" table even if the related data is missing (e.g., all accounts, with a business name only if the customer is a business) |
| `RIGHT [OUTER] JOIN` | Mirror of LEFT — all rows from the right table | Rare in practice; almost always rewritten as a LEFT JOIN with the tables swapped for readability/consistency |
| `FULL [OUTER] JOIN` | All rows from both sides, `NULL` on whichever side has no match | Reconciliation queries — "what's in A but not B, and vice versa" |
| `CROSS JOIN` | Every row of A paired with every row of B (Cartesian product) | Rare, deliberate use only (e.g., generating a date × store grid) — see anti-patterns below for the *accidental* version |
| Self-join | A table joined to itself via an alias | Hierarchical/recursive relationships in one table (employee → manager, category → parent category) |

```sql
-- LEFT JOIN: every account, business name only if the customer is a business
SELECT a.account_id, a.cust_id, b.name
FROM account a LEFT OUTER JOIN business b
  ON a.cust_id = b.cust_id;
-- rows for individual customers show NULL in the `name` column — that's expected,
-- it means "no matching business row", not an error.
```

**LEFT vs RIGHT is purely about which side is allowed to have gaps** — `A LEFT JOIN B` and `B RIGHT JOIN A` return identical result sets. Pick LEFT by convention and keep the "primary" table first; mixing LEFT and RIGHT in the same codebase makes joins harder to scan.

**Three-way outer join** (list every account with either a person's name or a business name):
```sql
SELECT a.account_id, a.product_cd,
       CONCAT(i.fname, ' ', i.lname) person_name,
       b.name business_name
FROM account a
LEFT OUTER JOIN individual i ON a.cust_id = i.cust_id
LEFT OUTER JOIN business  b ON a.cust_id = b.cust_id;
-- each row has exactly one of person_name/business_name populated, the other NULL
```

## Subqueries — types and when to use which

A subquery is a query nested inside another statement, always parenthesized. Two independent axes classify it:

**By dependency:**
- **Noncorrelated** — self-contained, can run alone (`SELECT MAX(account_id) FROM account`). The optimizer typically runs it once.
- **Correlated** — references a column from the outer query, so conceptually re-evaluates per outer row (`WHERE EXISTS (SELECT 1 FROM orders o WHERE o.customer_id = c.customer_id)`). Common in `UPDATE`/`DELETE` statements and in `EXISTS` checks.

**By shape of the result:**
- **Scalar** (one row, one column) — usable anywhere a single value is expected: `WHERE open_emp_id <> (SELECT e.emp_id FROM ... WHERE ...)`. If the subquery unexpectedly returns more than one row, the engine raises an error at runtime ("subquery returns more than 1 row") — this is a common bug when the `WHERE` inside the subquery isn't as selective as assumed.
- **Multi-row, single-column** — pair with `IN`/`NOT IN`, `ANY`, `ALL`: `WHERE emp_id IN (SELECT superior_emp_id FROM employee)`.
- **Multi-row, multi-column / table-shaped** — used in the `FROM` clause as a derived table, or with `EXISTS`.

**Subquery vs JOIN vs CTE — decision table:**

| Situation | Prefer |
|---|---|
| You need columns from both tables in the final result | `JOIN` |
| You only need to filter/check existence, not pull columns from the other table | `EXISTS`/`IN` subquery — often clearer intent than a `JOIN` you then have to remember not to `SELECT` from |
| The same derived result is referenced more than once in one query, or the query is deep enough that inline subqueries hurt readability | `WITH` (CTE) — gives the derived table a name once, reference it repeatedly |
| The relationship is hierarchical/recursive (org chart, category tree, bill of materials) | Recursive CTE (`WITH RECURSIVE`) |

**`IN`/`NOT IN` with `NULL` — the classic footgun** (industry-standard gotcha, not from either source book): if the subquery feeding `NOT IN` can return even one `NULL`, the *entire* `NOT IN` condition stops matching anything, because `x <> NULL` evaluates to `UNKNOWN` for every row, not `TRUE`:
```sql
-- If any customer_id in `orders` is NULL, this returns ZERO rows — silently.
SELECT * FROM customers
WHERE customer_id NOT IN (SELECT customer_id FROM orders);

-- Fix: exclude NULLs explicitly, or use NOT EXISTS (which doesn't have this problem)
SELECT * FROM customers c
WHERE NOT EXISTS (SELECT 1 FROM orders o WHERE o.customer_id = c.customer_id);
```
Default to `NOT EXISTS` over `NOT IN` for "rows with no matching row elsewhere" — it's immune to this NULL trap and is usually the query the author actually meant.

### Common Table Expressions (CTEs) — *not covered in the source books; standard SQL (since SQL:1999), included because it's essential modern practice*

```sql
WITH high_value_orders AS (
  SELECT customer_id, SUM(amount) AS total
  FROM orders
  GROUP BY customer_id
  HAVING SUM(amount) > 10000
)
SELECT c.name, h.total
FROM customers c
JOIN high_value_orders h ON h.customer_id = c.customer_id
ORDER BY h.total DESC;
```
A CTE is a named, scoped-to-this-statement derived table — same execution semantics as an inline subquery in `FROM` in most engines, but readable top-to-bottom instead of nested inside-out, and referenceable multiple times without repeating the definition. `WITH RECURSIVE` extends this to self-referential queries (walk a tree/graph one level per iteration until the recursive term returns no more rows).

## GROUP BY / HAVING / aggregates

- `WHERE` filters rows *before* grouping; `HAVING` filters *groups* after aggregation. Consequence: `WHERE` cannot reference an aggregate function (`COUNT(*)`, `SUM(x)`) — the aggregate doesn't exist yet at the point `WHERE` runs. Use `HAVING` for that.
- Every column in `SELECT` that isn't wrapped in an aggregate function must appear in `GROUP BY` (strict SQL mode enforces this; some engines silently pick an arbitrary row's value if you skip it — don't rely on that behavior).

```sql
-- WHERE vs HAVING: filter individual orders (WHERE), then filter the resulting groups (HAVING)
SELECT customer_id, COUNT(*) AS order_count
FROM orders
WHERE status = 'completed'        -- row-level filter, before grouping
GROUP BY customer_id
HAVING COUNT(*) > 5;              -- group-level filter, after aggregation
```

### Window functions — *not covered in the source books; standard SQL since SQL:2003*

Reach for a window function instead of `GROUP BY` when you need an aggregate value **alongside** individual row detail, not collapsed into one row per group:
```sql
SELECT order_id, customer_id, amount,
       SUM(amount) OVER (PARTITION BY customer_id) AS customer_total,
       ROW_NUMBER() OVER (PARTITION BY customer_id ORDER BY amount DESC) AS rank_within_customer
FROM orders;
-- every order row is preserved; customer_total repeats per customer instead of collapsing rows
```
`PARTITION BY` = the GROUP BY-equivalent grouping key, but rows aren't collapsed. Common functions: `ROW_NUMBER()` (unique sequential, no ties), `RANK()`/`DENSE_RANK()` (ties share a rank, RANK leaves gaps, DENSE_RANK doesn't), `LAG()`/`LEAD()` (previous/next row's value — great for period-over-period deltas without a self-join). Use `ROW_NUMBER() OVER (PARTITION BY key ORDER BY ...) = 1` as the standard "top N per group" pattern, wrapped in an outer query's `WHERE` (window functions can't be filtered directly in the same query's `WHERE`/`HAVING` — they run after those clauses).

## Set operations

| Operator | Result |
|---|---|
| `UNION` | Rows from both queries, duplicates removed (implies a sort/dedup pass — costs something) |
| `UNION ALL` | Rows from both queries, duplicates kept — cheaper, prefer it whenever you know the two sides can't overlap or duplicates are fine |
| `INTERSECT` | Only rows present in both queries |
| `EXCEPT` (`MINUS` in Oracle) | Rows in the first query not present in the second |

All four require both queries to return the **same number of columns with compatible types** — column names come from the first query. Default to `UNION ALL` unless you specifically need deduplication; reaching for `UNION` out of habit silently adds a sort over the whole combined result.

## NULL — three-valued logic

SQL comparisons involving `NULL` don't return `TRUE`/`FALSE`, they return `UNKNOWN`, and rows where the `WHERE`/`HAVING` condition evaluates to `UNKNOWN` are excluded (same as `FALSE` for filtering purposes, but not the same for `NOT`):
- `NULL = NULL` → `UNKNOWN`, not `TRUE`. Never write `WHERE col = NULL` — it matches nothing. Use `WHERE col IS NULL` / `WHERE col IS NOT NULL`.
- `NOT UNKNOWN` is still `UNKNOWN` — this is *why* the `NOT IN` footgun above happens: negating an `UNKNOWN` doesn't flip it to `TRUE`.
- Aggregate functions (`SUM`, `AVG`, `COUNT(column)`) silently ignore `NULL` values — `COUNT(*)` counts rows, `COUNT(column)` counts non-NULL values in that column; these two can legitimately return different numbers on the same table.
- `COALESCE(col, default)` returns the first non-NULL argument — the standard way to substitute a default for a possibly-NULL value, works across all engines (portable; engine-specific equivalents like `IFNULL`/`ISNULL` are not).

## Transactions and ACID

A transaction groups multiple statements so that **either all or none** take effect — critical any time related writes must stay consistent (e.g., debit one account and credit another):
```sql
START TRANSACTION;
UPDATE account SET balance = balance - 500 WHERE account_id = 9988 AND balance >= 500;
-- application checks exactly one row was updated (sufficient funds) before continuing
UPDATE account SET balance = balance + 500 WHERE account_id = 9989;
COMMIT;   -- or ROLLBACK if either step failed / the funds check failed
```
- **Atomicity**: the whole transaction succeeds or none of it does — a mid-transaction crash rolls back automatically when the server restarts.
- **Durability**: once `COMMIT` returns, the change survives a crash — the server must be able to reapply it from a durable log even if it hadn't yet been flushed to the main data files.
- **Consistency** and **Isolation** are the other two ACID letters (standard terminology, not elaborated in the source book beyond atomicity/durability/locking) — isolation is about what one transaction can see of another's uncommitted/concurrent changes; see `postgresql-practical-reference` for concrete isolation-level behavior and trade-offs.
- Underlying mechanism is locking (or MVCC/versioning, engine-dependent): writers need an exclusive lock on what they change; two locking granularities you'll see referenced are table-level (cheap to manage, serializes all writers) and row-level (more bookkeeping, lets concurrent writers touch different rows freely). Always wrap multi-statement writes that must be atomic in an explicit transaction — don't rely on each statement being "small enough" to not need one.

## Constraints

| Constraint | Enforces |
|---|---|
| `PRIMARY KEY` | Uniquely identifies each row; implicitly `NOT NULL` + unique; a table has at most one |
| `FOREIGN KEY` | A column's value must exist as a key in another (or the same) table — the mechanism that makes referential integrity a database guarantee instead of an application convention |
| `UNIQUE` | No two rows share the same value in this column (or column combination) — unlike primary key, a table can have several, and `NULL` is generally allowed (and multiple `NULL`s don't violate uniqueness, since `NULL <> NULL`) |
| `NOT NULL` | Column must always have a value |
| `CHECK` | Column value must satisfy a boolean expression (e.g., `CHECK (price >= 0)`) — push simple business rules into the schema so they can't be bypassed by a forgotten application-layer validation |

A `PRIMARY KEY` automatically creates a supporting index (that's how uniqueness is enforced efficiently) — you don't need to separately index the PK column.

## Indexing (concept-level — see `postgresql-practical-reference` for engine-specific index types/tuning)

Without an index, the server must scan every row to answer a filter (a **table scan**) — fine for small tables, prohibitively slow past some size. An index is a separate, ordered structure over one or more columns that lets the server jump directly to matching rows instead of scanning everything, at the cost of extra storage and slower writes (every `INSERT`/`UPDATE`/`DELETE` must also update each affected index). Index columns that are actually used to filter (`WHERE`), join (`ON`), or sort (`ORDER BY`) — indexing a column nobody filters on is pure write-cost with no read benefit.

## Anti-patterns and pitfalls

- **`SELECT *` in application code (not ad hoc exploration)**: pulls columns you don't use (wasted I/O/network), and silently breaks if the table gains/loses/reorders columns and the caller relied on column order. Name the columns you need.
- **Accidental Cartesian join**: an old-style comma-separated `FROM t1, t2` with a missing or wrong `WHERE`/`ON` condition silently produces every row of `t1` paired with every row of `t2` (an *n × m* result) instead of erroring. Prefer explicit `JOIN ... ON` syntax — it makes a missing join condition a syntax error instead of a silent correctness bug.
- **Filtering an outer join's optional side in `WHERE` instead of `ON`**: putting a condition on the right-hand (nullable) table's column in `WHERE` (`WHERE b.status = 'active'`) discards the `NULL`-filled non-matching rows the `LEFT JOIN` was supposed to preserve — it quietly turns the outer join back into an inner join. Put conditions that should still allow "no match" in the `ON` clause instead: `LEFT JOIN business b ON a.cust_id = b.cust_id AND b.status = 'active'`.
- **Function wrapped around an indexed column in `WHERE`** (`WHERE LOWER(email) = 'x'` against a plain index on `email`): the index can't be used because the stored values and the filter expression no longer match literally — either normalize data at write time, or build an expression index if the engine supports one (Postgres does; see `postgresql-practical-reference`).
- **N+1 queries**: issuing one query to fetch a list, then one additional query per row to fetch related data (common ORM default for lazy-loaded associations) instead of a single `JOIN` or a single `WHERE id IN (...)` batch fetch. Invisible in dev with 5 rows, catastrophic in prod with 5,000.
- **Large `OFFSET` pagination**: `LIMIT 20 OFFSET 100000` still makes the server generate and discard the first 100,000 rows before returning the next 20 — cost grows with page depth. Prefer keyset/cursor pagination (`WHERE id > :last_seen_id ORDER BY id LIMIT 20`), which is constant-cost regardless of how deep you page.
- **String-concatenated SQL from user input**: building queries by concatenating raw strings instead of parameterized queries/prepared statements is a SQL-injection vector, not just a style issue — always bind user-supplied values as parameters, never interpolate them into the SQL text.

### Normalization — *not covered in the source books; standard relational-design terminology, included as a quick reference*

| Form | Rule | Fixes |
|---|---|---|
| 1NF | Every column holds a single, atomic value (no comma-separated lists in a cell, no repeating groups) | "Multiple values crammed in one column" |
| 2NF | 1NF + every non-key column depends on the *whole* primary key, not part of it (matters only for composite keys) | Partial-key dependency |
| 3NF | 2NF + no non-key column depends on another non-key column (no transitive dependency) | "Derived/duplicated data that should live in its own table" |

Normalize to reduce update anomalies (the same fact stored in two places can drift out of sync); deliberately denormalize (duplicate data, pre-aggregate) only for a measured read-performance need, and document why — don't denormalize speculatively "for performance" without a query that actually needed it.

## Review checklist

- [ ] Does every `JOIN` have an explicit `ON` condition? (No bare comma joins.)
- [ ] For any `LEFT JOIN`, are optional-side filter conditions in `ON`, not `WHERE`, unless you specifically intend to collapse it to an inner join?
- [ ] Any `NOT IN (subquery)` — could the subquery's column contain `NULL`? If unsure, use `NOT EXISTS` instead.
- [ ] Does every non-aggregated `SELECT` column appear in `GROUP BY`?
- [ ] Any `WHERE col = NULL` or equivalent? Should be `IS NULL`.
- [ ] Any multi-statement write that must be atomic — is it wrapped in an explicit transaction?
- [ ] `SELECT *` in application code — can it be a named column list instead?
- [ ] Deep `OFFSET` pagination on a growing table — would keyset pagination be safer as the table scales?
