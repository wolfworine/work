---
name: solid-design-principles
description: Use whenever writing, reviewing, or refactoring object-oriented (especially Java) backend code and the user asks about SOLID, single responsibility, open/closed, Liskov substitution, interface segregation, dependency inversion, or when a class/method looks like it's doing too much, is hard to extend without editing existing code, breaks when a subtype is swapped in, forces implementers to stub out unused methods, or is directly `new`-ing up a concrete dependency instead of depending on an interface. Also use when someone is about to add "just one more interface" or split a class further and you need to judge whether that's genuine design improvement or over-engineering.
---

# SOLID Design Principles

Operational reference for applying the five SOLID principles to object-oriented backend code (examples in Java, principles apply to any OOP language). Distilled from *SOLID: The Software Design and Architecture Handbook* (Stemmler) plus canonical Robert C. Martin definitions where the source material was thin. Use this to review a diff, decide how to refactor a smelly class, or sanity-check whether a proposed abstraction earns its keep.

**Verified against the full text (not just a sample):** the book's own "Design Principles" chapter lists all five SOLID principles in its table of contents and section headers, but the actual prose that follows only develops **SRP** and **DIP** in depth (via a Components/Dependency Injection/IoC-container discussion, originally in TypeScript — adapted to Java below for consistency with the rest of this library). **OCP, LSP, and ISP appear only as headings** with no dedicated explanatory prose or examples anywhere else in the 76k-word book — this was re-confirmed by grepping the full text for every plausible spelling/spacing variant of each principle's name, not just the original PyPDF2 sample. Their sections below remain built from canonical Robert C. Martin definitions, as before.

**Ground rule before applying any of these:** SOLID exists to reduce the cost of *change* and enable *testing in isolation*. If a proposed refactor doesn't make some future change cheaper or some unit test faster/simpler, it's not paying for itself — it's decoration. Apply principles to real, observed pain (a class keeps getting edited for unrelated reasons, a test needs a live database, a switch statement keeps growing), not to hypothetical future requirements.

## Single Responsibility Principle (SRP)

**Definition:** A class should have one reason to change — it should be answerable to a single actor or stakeholder. "Reason to change" means a business/organizational role that could request a modification, not "does more than one thing" in a literal sense.

**Smells:**
- A class/controller method that mixes concerns from a request lifecycle: auth, input parsing, authorization, business rules, and persistence all in one method.
- A class name like `Manager`, `Utils`, `Helper`, `AppController` that no one can describe in one sentence.
- Multiple unrelated teams (billing, notifications, analytics) all have a reason to edit the same class.
- A change requested by one stakeholder breaks behavior another stakeholder depends on, because both lived in the same class.

**Before:**
```java
public class AppController {
    public Response createUser(Request req) {
        // 1. parse & validate input
        String username = req.get("username");
        if (username.length() < 3 || username.length() > 30) {
            return Response.status(400).body("Invalid username");
        }
        // 2. check uniqueness against the database directly
        if (userRepo.findByUsername(username) != null) {
            return Response.status(409).body("Username taken");
        }
        // 3. create the user
        User user = userRepo.save(new User(username, req.get("email")));
        // 4. side effects unrelated to "creating a user in a controller"
        emailService.sendVerificationEmail(user.getEmail());
        mailingListClient.subscribe(user.getEmail());
        return Response.ok(user);
    }
}
```
One class now has a reason to change if: validation rules change, the persistence mechanism changes, the email provider changes, or the marketing team changes mailing-list behavior. Four stakeholders, one file.

**After:**
```java
public class UserController {                  // reason to change: HTTP/transport concerns only
    private final CreateUserUseCase createUser;

    public Response createUser(Request req) {
        CreateUserCommand cmd = CreateUserCommand.from(req); // parsing lives here
        CreateUserResult result = createUser.execute(cmd);
        return result.isSuccess() ? Response.ok(result.value()) : Response.status(409).body(result.error());
    }
}

public class CreateUserUseCase {                // reason to change: application/business rules
    public CreateUserResult execute(CreateUserCommand cmd) {
        if (userRepo.findByUsername(cmd.username()) != null) {
            return CreateUserResult.usernameTaken();
        }
        User user = userRepo.save(User.create(cmd));
        notificationService.onUserCreated(user);  // delegates side effects, doesn't own them
        return CreateUserResult.success(user);
    }
}
```
Each class now answers to one actor: the controller only changes for transport/protocol reasons, the use case only for business-rule reasons.

**Don't over-apply when:** the "different responsibilities" are all going to change for the same reason, at the same time, forever (e.g., a tiny value object's `equals()` and `toString()`). Splitting a cohesive, small class into three files because it technically "does two things" adds indirection without reducing anyone's future edit-blast-radius — SRP is about decoupling reasons to change, not minimizing lines per class.

## Open-Closed Principle (OCP)

**Definition:** Software entities (classes, modules, functions) should be open for extension but closed for modification — you should be able to add new behavior by adding new code (a new class implementing an existing abstraction), without editing and re-testing code that already works.

**Smells:**
- A `switch`/`if-else` chain on a type code or enum that a developer has to revisit and add a branch to every time a new variant appears.
- Comments like `// add new case here` inside a method that's grown over several PRs.
- Adding a new feature requires touching a class that has nothing to do with that feature's team/domain.

**Before:**
```java
public class PaymentProcessor {
    public void process(Payment payment) {
        if (payment.getType() == PaymentType.CREDIT_CARD) {
            chargeCreditCard(payment);
        } else if (payment.getType() == PaymentType.PAYPAL) {
            chargePaypal(payment);
        } else if (payment.getType() == PaymentType.CRYPTO) {   // every new method = edit this file
            chargeCrypto(payment);
        }
    }
}
```

**After:**
```java
public interface PaymentMethod {
    void charge(Payment payment);
}

public class CreditCardPayment implements PaymentMethod { public void charge(Payment p) { ... } }
public class PaypalPayment implements PaymentMethod { public void charge(Payment p) { ... } }

public class PaymentProcessor {
    public void process(Payment payment, PaymentMethod method) {
        method.charge(payment);       // new payment type = new class, this method never changes
    }
}
```
Adding cryptocurrency support now means adding `CryptoPayment implements PaymentMethod` — zero edits to `PaymentProcessor`, zero risk of regressing existing payment types.

**Don't over-apply when:** there's only one variant today and no second one on a concrete roadmap. Building a `PaymentMethod` interface, a factory, and a registry for a single hard-coded payment type is speculative generality — it adds a layer of indirection that earns nothing until a second implementation actually shows up. Wait for the second real case before extracting the abstraction (rule of three: tolerate duplication once, extract on the second or third repetition).

## Liskov Substitution Principle (LSP)

**Definition:** Objects of a subtype must be substitutable for objects of the supertype without altering the correctness of the program — a subclass must honor the behavioral contract (preconditions, postconditions, invariants) that callers of the base type rely on, not just match its method signatures.

**Smells:**
- An overridden method throws `UnsupportedOperationException` or silently does nothing for a case the base type promises to handle.
- Callers do `if (obj instanceof SpecificSubtype)` to special-case behavior — a sign the subtype doesn't actually behave like its parent everywhere.
- A subclass strengthens preconditions (rejects inputs the base class accepts) or weakens postconditions (returns something callers of the base type don't expect).
- The classic case: `Square extends Rectangle` and overrides `setWidth`/`setHeight` to keep both sides equal, breaking any code that sets width and height independently and expects the area to update predictably.

**Before:**
```java
public class Rectangle {
    protected int width, height;
    public void setWidth(int w) { this.width = w; }
    public void setHeight(int h) { this.height = h; }
    public int area() { return width * height; }
}

public class Square extends Rectangle {
    @Override public void setWidth(int w) { this.width = w; this.height = w; }  // surprises callers
    @Override public void setHeight(int h) { this.width = h; this.height = h; }
}

// Anywhere this ran against a Rectangle, it silently breaks for a Square:
void resize(Rectangle r) {
    r.setWidth(5);
    r.setHeight(10);
    assert r.area() == 50;   // fails for Square: area() == 100
}
```

**After:** don't force an is-a relationship where the behavior isn't actually substitutable. Model the shared concept without inheritance forcing a false contract:
```java
public interface Shape {
    int area();
}
public final class Rectangle implements Shape {
    private final int width, height;
    public Rectangle(int width, int height) { this.width = width; this.height = height; }
    public int area() { return width * height; }
}
public final class Square implements Shape {
    private final int side;
    public Square(int side) { this.side = side; }
    public int area() { return side * side; }
}
```
Every `Shape` in the system now honors exactly one contract (`area()`), and there's no mutable setter pair implying an invariant that only one subtype actually enforces.

**Don't over-apply when:** it tempts you to add speculative interfaces to types that will only ever have one real implementation "in case LSP matters later." LSP is a constraint on inheritance/polymorphism you're *already using* — it's a reason to fix or avoid a bad hierarchy, not a reason to introduce a hierarchy where a single concrete class would do.

## Interface Segregation Principle (ISP)

**Definition:** Clients should not be forced to depend on methods they don't use. Prefer several small, role/client-specific interfaces over one broad interface that bundles unrelated capabilities together.

**Smells:**
- An implementer has to override a method with `throw new UnsupportedOperationException()` or an empty body because the interface forced it on them.
- A consumer imports/depends on an interface with a dozen methods but calls only one or two.
- Changing a method used only by one client forces every other implementer of the same fat interface to be touched, recompiled, or re-reviewed.
- (Book's own example: this is precisely why interface segregation is awkward in plain JavaScript — without real interfaces, there's no language-level way to express "this client only needs this narrow slice of behavior," so fat, implicit contracts creep in by default.)

**Before:**
```java
public interface Worker {
    void work();
    void eat();
}

public class HumanWorker implements Worker {
    public void work() { ... }
    public void eat() { ... }
}

public class RobotWorker implements Worker {
    public void work() { ... }
    public void eat() { throw new UnsupportedOperationException(); }  // forced, meaningless method
}
```

**After:**
```java
public interface Workable { void work(); }
public interface Eatable  { void eat(); }

public class HumanWorker implements Workable, Eatable {
    public void work() { ... }
    public void eat() { ... }
}

public class RobotWorker implements Workable {   // only depends on what it actually does
    public void work() { ... }
}
```
Code that schedules work only needs to depend on `Workable`; it never has to know or care that `eat()` exists.

**Don't over-apply when:** it produces a sea of single-method "role interfaces" for a codebase where every implementer needs every method anyway. Segregate along real, differing client usage patterns — if all current and foreseeable clients use the whole interface identically, splitting it is busywork that just adds files to navigate.

## Dependency Inversion Principle (DIP)

**Definition:** High-level modules should not depend on low-level modules — both should depend on abstractions (interfaces). Abstractions should not depend on details; details should depend on abstractions. In practice: business/application code depends on an interface it defines the shape of, and the concrete infrastructure implementation is injected into it (usually via constructor), rather than the business code instantiating or importing the concrete class directly.

**Smells:**
- A class does `new ConcreteRepository()` (or any concrete infra class) inside a constructor or method body instead of receiving it as a parameter.
- A unit test for business logic needs a live database, HTTP server, or filesystem to pass.
- Application/domain code has an import statement pointing at an infrastructure package (`repos.*`, `http.*`, a specific ORM class).

**Before:**
```java
public class UserController {
    private final UserRepo userRepo = new UserRepo();   // hard-wired to a concrete class

    public Response handleGetUsers() {
        List<User> users = userRepo.getUsers();          // needs a real DB to test this at all
        return Response.ok(users);
    }
}
```
`UserController` cannot be unit tested without a live `UserRepo`, and `UserRepo` cannot be swapped for a test double.

**After:**
```java
public interface UserRepo {                              // abstraction, owned by the consumer's layer
    List<User> getUsers();
}

public class SqlUserRepo implements UserRepo { ... }      // detail, depends on the abstraction

public class UserController {
    private final UserRepo userRepo;
    public UserController(UserRepo userRepo) {            // injected — dependency arrow now points at UserRepo interface
        this.userRepo = userRepo;
    }
    public Response handleGetUsers() {
        return Response.ok(userRepo.getUsers());
    }
}

// Test — no database required:
class InMemoryUserRepo implements UserRepo {
    public List<User> getUsers() { return List.of(); }
}
new UserController(new InMemoryUserRepo()).handleGetUsers();  // fast, isolated, deterministic
```
This is the same refactor the source book walks through in depth: flipping the dependency arrow from `UserController → UserRepo` (concrete) to `UserController → IUserRepo ← UserRepo` (interface in the middle) is what makes the controller testable, makes the repository substitutable (its plugin can change without touching the controller — this is also what makes LSP and OCP payoffs possible), and creates a real architectural boundary between layers.

**Don't over-apply when:** the codebase is small or a single team owns the whole wiring path. Reaching for a full IoC/DI container (Spring, Guice, etc.) for a handful of classes scatters container-specific annotations and registration boilerplate across the codebase for a problem manual constructor injection already solves. Add a container when the graph of dependencies is large enough that manual wiring itself becomes the maintenance burden — not by default.

**Related concept — Inversion of Control (the "Hollywood Principle"):** DIP is about which direction the *dependency arrow* points (an abstraction, not a concrete class); IoC is the related-but-distinct idea that a framework/container calls *your* code rather than your code driving the framework — "don't call us, we'll call you." A DI container annotation (`@Component`, `@Bean`), a lifecycle hook, or an ORM hook (`@PrePersist`, a Sequelize/Hibernate `beforeSave` callback) are all IoC in practice: you register a piece of behavior and the framework decides when to invoke it. Recognizing this distinction matters when debugging "why is my code running before/after I expected" — the answer is almost always "the framework/container owns the invocation, not your call stack."

## Combined review checklist

Run through this when reviewing a class, PR, or proposed refactor:

- [ ] **SRP** — Can I name one actor/stakeholder this class answers to? If naming it requires "and," it probably has two.
- [ ] **OCP** — Does adding the next likely variant require editing this class's existing logic, or just adding a new implementation of an existing abstraction?
- [ ] **LSP** — If I hand a subtype anywhere the supertype is expected, does it behave the way callers of the supertype already assume? Any `instanceof` special-casing is a red flag.
- [ ] **ISP** — Does every client of this interface actually use most of its methods? Any `UnsupportedOperationException` or empty override is a red flag.
- [ ] **DIP** — Can I unit-test this class's logic without a database, network call, or filesystem? If not, find the `new ConcreteThing()` and inject an interface instead.
- [ ] **Over-application check** — For every abstraction/interface/split just introduced: is there a second real implementation or a second real reason to change *today* — not hypothetically? If not, prefer the simpler, un-abstracted version until the second case actually arrives.
