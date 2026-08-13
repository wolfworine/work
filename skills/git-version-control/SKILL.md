---
name: git-version-control
description: Use whenever running git commands, deciding between merge and rebase, writing or cleaning up commit messages, resolving merge conflicts, setting up or reviewing a .gitignore, opening or reviewing a pull request, choosing a branching strategy (trunk-based, GitFlow, feature branches), or when the user says they messed up a commit/branch/history and need to undo, recover, or fix something (wrong branch, lost commit, accidental push, need to amend/revert/reset).
---

# Git Version Control

Operational reference for Git and GitHub workflows. Distilled from *Git y GitHub desde cero* (Brais Moure) for day-to-day workflow/commands, *Pro Git* (Scott Chacon & Ben Straub — the canonical free reference) for internals/hooks/submodules/advanced tools, plus standard industry practice for the areas neither source touches (protected branches, commit conventions). Applies to any codebase, any language — this is process guidance, not project-specific.

## Mental model

Three areas, one direction of travel:

```
Working directory  --git add-->  Staging area (index)  --git commit-->  Local repo (branch history)  --git push-->  Remote
        ^                              |
        |__________git reset__________|   (moves HEAD/branch pointer, optionally unstages/discards)
        ^
        |__git checkout -- <file> / git restore <file>__|  (discard working-dir changes to match last commit)
```

- **Working directory**: files as they currently sit on disk. Anything here that isn't staged is invisible to Git history.
- **Staging area / index**: a draft of the next commit. `git add` copies a file's current state into this draft. You can stage part of your work and leave the rest for a later commit — this is how you keep commits atomic.
- **Commit**: an immutable snapshot of the whole staged tree at a point in time, with a hash, author, timestamp, and message. A branch is just a movable pointer to a commit; `HEAD` is a pointer to "the branch/commit you're currently on."
- **Remote**: a copy of the repo (history + branches) living elsewhere (GitHub). `push`/`pull`/`fetch` sync local branch pointers with remote ones. Nothing you do locally affects the remote until you `push`.

The single rule that explains most Git behavior: **local history is yours to rewrite; shared/pushed history is not** (see Merge vs rebase below).

## Git internals: what a commit actually is

Everything above is the porcelain (user-facing commands). Underneath, Git is a content-addressable key-value store — understanding this makes `reset`/`rebase`/`reflog` predictable instead of magic. `git init` creates `.git/{objects,refs,HEAD,index,hooks,info}` — copying just this directory is a full backup of the repo.

Three object types, each identified by the SHA-1 hash of its own content:
- **Blob** — raw file content, no filename attached. `git hash-object -w test.txt` stores the file's bytes and returns the hash that now retrieves them (`git cat-file -p <hash>`); two files with identical content are the *same* blob, stored once.
- **Tree** — one directory snapshot: a list of entries, each `<mode> <blob-or-tree> <sha1> <filename>` (mode `100644` normal file, `100755` executable, `120000` symlink, `040000` subdirectory → another tree). This is what maps hashes back to filenames, since a blob alone doesn't know its own name.
- **Commit** — points to exactly one tree (the full snapshot at that point), zero or more parent commits (more than one for a merge commit), plus author, committer, timestamp, and message. A branch is nothing more than a file under `.git/refs/heads/<name>` containing a commit's SHA-1; `HEAD` is (usually) a pointer to the current branch's ref, not a commit directly.

```bash
git cat-file -t <sha>          # what type of object is this (blob/tree/commit/tag)
git cat-file -p <sha>          # print it — a blob's content, a tree's entries, or a commit's metadata+tree+parent
git cat-file -p master^{tree}  # the tree object at the tip of master
```

This is also why amend/rebase/reset are "cheap": they never mutate an existing object (objects are immutable and content-addressed — changing content produces a *new* hash), they only build new commit/tree/blob objects and repoint a ref to the new tip. The old objects stick around, unreferenced, until garbage collected — which is exactly what `git reflog` exploits to recover "lost" work (see Disaster recovery below).

Loose objects (one file per object) get periodically compacted by `git gc` into a **packfile**: a single file storing many objects, using delta compression against similar objects (e.g., successive versions of the same file store only the diff, not the full content again) — this is what makes `.git` stay small even after thousands of commits, and what actually gets transferred on `push`/`fetch` (client and server negotiate the minimal set of missing objects, then exchange one packfile instead of one round-trip per object).

## Branching strategies — decision table

| Strategy | How it works | Best for | Trade-off |
|---|---|---|---|
| **Trunk-based / GitHub Flow** | One long-lived branch (`main`), short-lived feature branches merged back via PR frequently (days, not weeks). `main` is always deployable. | Continuous deployment, small-to-mid teams, web apps/services | Requires strong CI, feature flags for incomplete work; little structure for coordinating releases |
| **GitFlow** | Two long-lived branches: `develop` (integration) and `main` (production). Three supporting branch types: `feature/*` (from `develop`, merge back to `develop`), `release/*` (from `develop`, merge to both `main` and `develop`, tags `main`), `hotfix/*` (from `main`, merge to both `main` and `develop`, tags `main`). | Versioned/released software (desktop apps, libraries, mobile apps) with scheduled releases and a need to patch production independently of in-progress work | More ceremony/overhead; easy to over-engineer for a team that ships continuously |
| **Simple feature branches** | Branch per feature/fix off `main`, PR, merge, delete. No `develop`, no release branches. | Small teams/personal projects, prototypes | No formal support for parallel release lines or hotfix isolation |

Pick trunk-based by default for anything deployed continuously; reach for GitFlow only when you actually have discrete, coordinated releases and need to patch an older production version while development continues. Whatever you pick, write it down and have the whole team follow it — the value is in the shared convention, not the specific model (source: book's own conclusion — "more important than using GitFlow is knowing that structured collaboration flows exist and picking one").

GitFlow command reference (via `git-flow` plugin):
```bash
git flow init                                   # sets up main/develop + naming conventions
git flow feature start <name>                   # branch from develop
git flow feature finish <name>                  # merge into develop, delete branch
git flow release start <version>
git flow release finish <version>               # merge into main AND develop, tags main
git flow hotfix start <name>                     # branch from main
git flow hotfix finish <name>                    # merge into main AND develop, tags main
```

## Merge vs rebase — decision table

| Situation | Use | Why |
|---|---|---|
| Bringing a shared/public branch's changes into your feature branch | `merge` | Non-destructive; doesn't rewrite commits others may have based work on |
| Integrating a finished feature branch into `main`/`develop` via PR | `merge` (or squash-merge) | Preserves an honest record of when work was integrated; matches what the GitHub UI does |
| Cleaning up your **own local, not-yet-pushed** commits before opening a PR | `rebase -i` | Lets you squash/reorder/reword commits into a clean, atomic story before anyone else sees it |
| Updating your local feature branch with the latest `main` while you're still the only one working on it | `rebase main` | Produces a linear history — looks like you started your branch from the tip of `main` |
| The branch is **already pushed and shared** (others have pulled it or based work on it) | `merge` — never rebase it | Rebase rewrites commit hashes; anyone who already pulled the old ones gets duplicated/conflicting history and force-push chaos |

**The rule that matters most:** never rebase a branch other people have already pulled from or are basing commits on. Rebase rewrites history (new hashes for every rewritten commit); merge only adds a new commit on top and leaves existing history untouched. If you're unsure whether a branch is "yours alone," treat it as shared and use merge.

```bash
# Merge: creates a new merge commit combining both histories, safe on shared branches
git checkout main
git merge feature/login

# Rebase: replays your branch's commits on top of another branch, rewrites hashes
git checkout feature/login
git rebase main
# ...resolve conflicts if any, then:
git rebase --continue
# or bail out entirely and return to pre-rebase state:
git rebase --abort

# Interactive rebase to clean up local commits before a PR (squash/reword/reorder)
git rebase -i HEAD~5
```

`cherry-pick` is the scalpel version of merge: pull one specific commit from another branch instead of the whole branch.
```bash
git cherry-pick <hash>            # apply that one commit onto the current branch
git cherry-pick --abort           # bail out, return to pre-cherry-pick state
git cherry-pick --continue        # after resolving a conflict mid-pick
```
Prefer clean branches and normal merges over reaching for `cherry-pick`/`rebase` as a first resort — they're powerful but they rewrite or selectively replay history, which is easy to get wrong under pressure.

## Writing a good commit

**Atomic commits**: one commit = one logical change. Not "end of day save" — a commit should be revertible on its own without dragging unrelated changes with it. If your commit message needs "and" to describe what changed, it's probably two commits.

**Message convention** (Conventional Commits style — adopt whatever your team standardizes on, but be consistent):
```
<type>(<optional scope>): <short imperative summary, ≤50 chars>

<optional body: what changed and WHY, wrapped at ~72 chars>

<optional footer: BREAKING CHANGE:, Refs: #123, Co-authored-by:>
```
Types: `feat`, `fix`, `refactor`, `docs`, `test`, `chore`, `perf`, `style`, `build`, `ci`.

Example:
```
fix(auth): reject expired refresh tokens on renewal

Previously an expired refresh token silently issued a new access
token, extending a session indefinitely. Now renewal checks the
token's exp claim and returns 401 if it has passed.

Refs: #482
```

Rules of thumb:
- Imperative mood ("add", not "added"/"adds") — a commit message completes the sentence "If applied, this commit will ___".
- Explain *why*, not just *what* — the diff already shows what changed.
- Reference a task/ticket ID when one exists; makes history searchable.
- Squash noisy WIP commits (`fix typo`, `wip`, `address review comment`) into the meaningful commit before merging — via `git rebase -i` locally, or a squash-merge on the PR. Only do this to commits nobody else has based work on.

## Staging workflow cheat-sheet

```bash
git status                  # what's staged / unstaged / untracked
git add <file>               # stage one file
git add .                    # stage everything in the current directory tree
git add -p                   # stage interactively, hunk by hunk (best tool for atomic commits)
git commit -m "<message>"    # commit what's staged
git diff                     # unstaged changes vs last commit
git diff --staged            # staged changes vs last commit
git log --graph --oneline --decorate --all   # visual, compact history across all branches
```

## .gitignore essentials

- Lives at the repo root, named exactly `.gitignore`. Adding a pattern doesn't delete or untrack already-committed files — it only stops *future* untracked files from being offered for staging.
- Common patterns:
  ```gitignore
  # by exact name, anywhere in the tree
  **/secrets.env

  # by extension
  *.log
  *.tmp

  # whole directory
  node_modules/
  build/
  dist/

  # negate a previous rule (un-ignore a specific file)
  !important.log
  ```
- If a file was already committed before you ignored it, `.gitignore` won't remove it from history — untrack it explicitly:
  ```bash
  git rm --cached <file>
  git commit -m "chore: stop tracking <file>"
  ```
- Never rely on `.gitignore` alone to keep secrets out of history — if a credential was ever committed, rotate it; removing the file later doesn't remove it from old commits (that needs history rewriting tools like `git filter-repo`, and counts as rewriting shared history — coordinate with the team first).

## Conflict resolution workflow

A conflict happens when two branches changed the same lines of the same file and Git can't pick a winner automatically. It surfaces during `merge`, `rebase`, or `cherry-pick`.

1. Run the operation (`git merge <branch>`); Git stops and lists conflicted files.
2. Open each conflicted file. Git marks the competing versions inline:
   ```
   <<<<<<< HEAD
   your branch's version
   =======
   incoming branch's version
   >>>>>>> feature/other-branch
   ```
3. Edit the file by hand to the version you actually want — keep one side, the other, or a hand-merged combination. Delete the `<<<<<<<`/`=======`/`>>>>>>>` markers themselves.
4. Use `git diff` beforehand if you need to see exactly what each side changed.
5. Mark each file resolved and finish the operation:
   ```bash
   git add <file>                # marks this file's conflict as resolved
   git commit                    # completes a merge (message pre-filled)
   git rebase --continue          # completes a rebase step instead
   git cherry-pick --continue     # completes a cherry-pick instead
   ```
6. If it's a mess and you want out entirely:
   ```bash
   git merge --abort
   git rebase --abort
   git cherry-pick --abort
   ```
7. Shortcuts when you know one side is simply correct (rare — use with care, you're discarding the other side's changes to that file entirely):
   ```bash
   git checkout --ours  <file>    # keep current branch's version of this file
   git checkout --theirs <file>   # keep incoming branch's version of this file
   git add <file>
   ```

## Disaster recovery — symptom to fix

| Symptom | Fix |
|---|---|
| Staged a file by mistake, haven't committed | `git restore --staged <file>` (or older Git: `git reset <file>`) |
| Want to discard uncommitted changes to one file | `git checkout -- <file>` (or `git restore <file>`) |
| Want to discard ALL uncommitted changes, keep commits | `git reset` (soft reset to last commit; then discard remaining working-dir diffs per file, or `git reset --hard` — see below) |
| Last commit message is wrong, or forgot to add a file — **and it is NOT pushed yet** | `git add <forgotten-file>` (if needed) then `git commit --amend -m "correct message"` |
| Committed but haven't pushed, want to undo the commit and keep the changes staged | `git reset --soft HEAD~1` |
| Committed but haven't pushed, want to undo the commit and keep changes unstaged (in working dir) | `git reset HEAD~1` (mixed reset, the default) |
| Committed but haven't pushed, want to nuke the commit AND its changes entirely | `git reset --hard HEAD~1` — destructive, only if you're sure |
| Already **pushed** a commit that needs undoing (shared/public history) | `git revert <hash>` — never `reset` or amend a pushed commit. `revert` adds a new commit that undoes the change, preserving history for everyone who already pulled |
| `git reset --hard` (or a rebase) wiped commits you actually needed | `git reflog` — lists every HEAD movement, including "deleted" commits. Find the hash from before the mistake, then `git reset --hard <hash>` to return to it |
| Committed to the wrong branch (not pushed yet) | `git branch correct-branch` (creates a branch at current commit, doesn't move anything) then `git reset --hard HEAD~1` on the original branch to remove the commit from it, then `git switch correct-branch` to continue there |
| Need to move several uncommitted-to-wrong-branch commits | On the wrong branch: note the hashes with `git log`, `git reset --hard <hash-before-your-work>`, switch/create the correct branch, `git cherry-pick <hash1> <hash2> ...` in order |
| Need to save work-in-progress without committing, to switch branches cleanly | `git stash` then later `git stash pop` (or `git stash apply` to keep it in the stash list) |
| Amending a commit that's already pushed and others may have pulled | **Don't.** Amending changes the commit hash — anyone who pulled the old one now has diverged history and needs to force-sync. If you must fix it publicly, `git revert` the old commit and commit the fix fresh, or coordinate an explicit force-push window with the whole team |
| Need to recover a specific "lost" commit's content only (not restore the whole branch) | `git reflog` to find the hash, then `git cherry-pick <hash>` onto your current branch instead of resetting |

General safety habits:
- Before any `reset --hard`, `checkout .`, or rebase, check `git status` — if there's uncommitted work you might want, `git stash` it first.
- `git reflog` is local-only and expires eventually (default ~90 days for unreachable commits) — it's a safety net, not permanent storage.
- Treat `--force` push as a last resort; prefer `--force-with-lease` (fails if the remote has commits you haven't seen, protecting a teammate's work from being silently overwritten).

## Git hooks — automating policy at commit/push time

Scripts in `.git/hooks/` that Git fires automatically on specific events. `git init` populates the directory with `*.sample` examples (shell, but any executable script in any language works) — rename to drop the `.sample` extension to activate one. **Client-side hooks are not copied on clone** — they live only in your local `.git`, so they can't be used to *enforce* a team-wide policy (a teammate can just not have it installed); use a server-side hook for that.

| Hook | Fires | Typical use | Exit non-zero to abort? |
|---|---|---|---|
| `pre-commit` | Before the commit message editor opens | Lint, run fast tests, check trailing whitespace | Yes (bypass with `git commit --no-verify`) |
| `commit-msg` | After the message is written | Enforce a message format (e.g., Conventional Commits) | Yes |
| `prepare-commit-msg` | Before the editor opens, after the default message is generated | Programmatically pre-fill a template | No (edits the message, doesn't gate) |
| `post-commit` | After the commit completes | Notifications | No (informational only) |
| `pre-rebase` | Before a rebase starts | Block rebasing commits already pushed | Yes |
| `pre-push` | After remote refs update, before objects transfer | Validate what's about to be pushed | Yes |

Server-side (run on the remote when it receives a push — this is where you actually *enforce* policy, since every client goes through it regardless of local hook config):

| Hook | Fires | Granularity |
|---|---|---|
| `pre-receive` | First, for the whole push | Once total — reject the entire push (e.g., block non-fast-forwards, access control) |
| `update` | Per branch being updated | Once per ref — can reject just one branch out of a multi-branch push |
| `post-receive` | After the push is fully accepted | Once total — notify CI, update a ticket tracker; can't stop the push, it already happened |

## Submodules — a Git repo nested inside another

For depending on another Git repository (a shared library, a third-party component) while keeping its history and commits fully separate from the parent repo — as opposed to vendoring (copying the code in, losing upstream traceability) or a package-manager dependency (fine for published artifacts, not for "I need to develop against a specific commit of a repo I can also patch").

```bash
git submodule add https://github.com/org/lib-name        # clones lib-name/ as a submodule, writes .gitmodules
git status                                                # shows new files: .gitmodules + the submodule directory
```
`.gitmodules` (itself a tracked, committed file) records the URL→local-path mapping so anyone who clones the parent repo knows where to fetch the submodule from. The parent repo doesn't store the submodule's files — it stores a pointer to one specific commit SHA of the submodule repo, which is why cloning a repo with submodules needs an extra step to actually populate them:
```bash
git clone --recurse-submodules <url>       # clone parent + populate submodules in one step
# or, if already cloned without that flag:
git submodule update --init --recursive
```
The parent repo pins an exact commit, not a branch — the submodule doesn't move when its upstream does. Updating it is a deliberate two-step: `cd` into the submodule, pull/checkout the commit you want, then `cd` back out and commit the parent repo's now-changed pointer.

**Don't over-apply when:** the dependency is just consumed, never patched locally — a normal package manager (npm/Maven/pip) is simpler for that case. Submodules earn their complexity specifically when you need to develop against and occasionally patch the nested repo's own history.

## `git bisect` — binary search for the commit that broke something

When something is broken, you know a past commit was good, but you don't know which of the (possibly hundreds) commits in between introduced the regression — `git bisect` binary-searches the range instead of you checking commits one by one.

```bash
git bisect start
git bisect bad                 # current commit is broken
git bisect good v1.0            # this earlier tag/commit was known-good
# Git checks out the midpoint; test it, then tell it what you found:
git bisect good                 # midpoint was fine -> bug is in the newer half
git bisect bad                  # midpoint was broken -> bug is in the older half
# ...repeat (Git narrows the range by half each time) until it reports:
# "<sha> is the first bad commit"
git bisect reset                # IMPORTANT: return to your original HEAD when done
```
Fully automatable if you have a script/test that exits `0` on good and non-zero on bad — Git will drive the entire search itself without you eyeballing each checkout:
```bash
git bisect start HEAD v1.0
git bisect run ./run-tests.sh   # or `make test`, etc.
```
For `N` commits between good and bad, this takes roughly `log2(N)` steps — finding the culprit among 500 commits takes about 9 checks, not 500.

## `git rerere` — stop resolving the same conflict twice

"Reuse recorded resolution": once enabled, Git remembers how you manually resolved a specific conflicting hunk, and auto-applies the same resolution next time the identical conflict recurs.
```bash
git config --global rerere.enabled true
```
Genuinely useful for two recurring situations: (1) keeping a long-lived branch continuously rebased/merged against a fast-moving `main` — you resolve the same conflicts on every sync otherwise; (2) merging a set of evolving topic branches together repeatedly to test them, backing out and redoing the merge when one branch fails tests. In both cases, rerere means you resolve each real conflict once, and Git replays that resolution automatically on every subsequent encounter — you only get prompted again if the conflicting content actually changes.

## Pull request workflow and review etiquette

1. Branch from the up-to-date base branch; keep the PR scoped to one concern.
2. Push the branch, open a PR against the target branch (same repo) or against the upstream repo (if you're contributing via a fork: fork, branch, commit, push to your fork, then open the PR from your fork's branch to upstream).
3. Write a PR description that states *why*, not just *what* — link the issue/ticket it addresses.
4. Reviewer reads the diff, comments on specific lines, requests changes or approves. Author addresses comments with new commits (don't force-push over review comments mid-review — it hides what reviewers already looked at; squash/rebase-clean only after approval, if the team's convention wants a clean history).
5. Resolve conflicts with the base branch before merge is allowed: sync the branch (`merge` the base into your feature branch is the safe default; only rebase if the branch is still yours alone) and push again.
6. Once approved, merge via the platform (regular merge / squash / rebase-merge, per team convention) — this is the moment the change becomes part of the shared branch's permanent history.
7. Delete the feature branch after merge to keep the branch list meaningful.

Protected branches / required checks (standard practice on any team, even where the book doesn't dive deep): configure the base branch (`main`/`develop`) to require at least one approving review and passing CI checks before merge is allowed, and to block direct pushes — all changes go through a PR. This is what actually enforces "review before integration" instead of relying on discipline alone.

## The 10 + 10 checklist (source: book's closing "best practices")

**Git**
1. One repository per project — don't mix unrelated projects in one repo.
2. Use branches for every feature/fix/experiment; keep `main` stable.
3. Make atomic commits — one logical change each.
4. Write clear, descriptive commit messages (why, and reference the task/ticket).
5. Keep history clean — merge deliberately, avoid noisy/conflicting commits.
6. Tag meaningful points (releases, production deploys) with `git tag`.
7. Review code before merging into the main line.
8. Resolve conflicts carefully — verify the resolution doesn't break either side's intent.
9. Push regularly; the remote is your backup, not just a publishing step.
10. Keep learning Git's more advanced features as you need them.

**GitHub**
1. Keep your profile/README current — it's a portfolio.
2. Every repo gets a `README.md`: what it is, how to install/run it, how to contribute.
3. Add a license so others know how they may use the code.
4. Propose changes via Pull Request instead of pushing directly to the main line.
5. Review PRs before merging; automate checks with CI (e.g., GitHub Actions).
6. Track bugs/work with Issues instead of side channels.
7. Use labels and milestones to categorize and phase work.
8. Keep documentation (wiki, docs folder) up to date alongside the code.
9. Share and collaborate — contribute back to projects you depend on.
10. Keep exploring the platform; its tooling goes well beyond core Git.
