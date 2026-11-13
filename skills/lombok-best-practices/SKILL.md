---
name: lombok-best-practices
description: "Lombok best practices, when to use vs Java 21 records, common pitfalls. Triggers on: lombok, @Data, @Builder, @Value, @Getter, @Setter, @NoArgsConstructor, @AllArgsConstructor, boilerplate, POJO."
---

# Lombok Best Practices — Quick Reference

## 1. Decision: Record vs Lombok

| Scenario | Use | Why |
|----------|-----|-----|
| Immutable Value Object | **Record** | Inmutabilidad por diseño, pattern matching |
| DTO Response | **Record** | Sin setters, serializable |
| DTO Request | **Record** | Validación en canonical constructor |
| Domain Event | **Record** | Inmutabilidad, audit trail |
| JPA Entity | **Lombok** | Necesita setters, `@Entity`, mutabilidad |
| Entity mutable | **Lombok** | Lifecycle, state changes |
| Builder pattern | **Record + @Builder** | Inmutabilidad + builder |
| Builder mutable | **Lombok @Builder** | Para objetos mutables |

## 2. Lombok Annotations Reference

### For JPA Entities (Mutable)

```java
@Entity
@Table(name = "operator")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OperatorJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentTypeEnum paymentType;
}
```

### For Domain Models (Immutable)

```java
// PREFERRED: Use records instead
public record Operator(
    Long id,
    String name,
    String bankAccount,
    PaymentTypeEnum paymentType
) {
    public Operator {
        Objects.requireNonNull(name, "Name must not be null");
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }
    }
}
```

## 3. Common Conflicts

### @Data + @Builder (Constructor Conflict)

```java
// PROBLEM
@Data @Builder
class Point {
    private Double x;
    private Double y;
}
// Error: constructor conflict

// SOLUTION
@Data @Builder @NoArgsConstructor @AllArgsConstructor
class Point {
    private Double x;
    private Double y;
}
```

### @Data on JPA Entity (equals/hashCode on all fields)

```java
// PROBLEM
@Data @Entity
public class Operator {
    @Id private Long id;
    private String name;
}
// Two objects with same name but different ID are "equal" — WRONG

// SOLUTION
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Operator {
    @Id @EqualsAndHashCode.Include
    private Long id;
    @Getter @Setter
    private String name;
}
```

## 4. Lombok + Google Style

| Annotation | Compatible? | Notes |
|------------|------------|-------|
| `@Getter`/`@Setter` | **Sí** | Generates camelCase methods |
| `@ToString` | **Sí** | Generates `toString()` |
| `@EqualsAndHashCode` | **Sí** | Configure for entities |
| `@NoArgsConstructor` | **Sí** | Required for JPA |
| `@AllArgsConstructor` | **Sí** | Required for Builder |
| `@Builder` | **Sí** | Generates builder pattern |
| `@Value` | **Sí** | = `@Data` + final fields |
| `@Data` | **Con caution** | Configure equals/hashCode |

## 5. When NOT to Use Lombok

| Situation | Why | Alternative |
|-----------|-----|-------------|
| Immutable value objects | Records are better | `record Money(BigDecimal amount)` |
| DTOs | Records are simpler | `record OperatorResponse(Long id, String name)` |
| Sealed hierarchy leaves | Records enable pattern matching | `record PendingState(...)` |
| Domain events | Records for immutability | `record OrderCreatedEvent(...)` |

## 6. Jakarta Validation on Records

Records support Jakarta Validation annotations on components:

```java
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public record OperatorRequest(
    @NotBlank(message = "Name must not be blank")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    String name,
    
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email must not be blank")
    String email,
    
    @NotNull(message = "Payment type must not be null")
    PaymentTypeEnum paymentType
) {}
```

### Validation Annotations Reference

| Annotation | Use Case | Example |
|------------|----------|---------|
| `@NotNull` | Non-nullable fields | `@NotNull Long id` |
| `@NotBlank` | Non-blank strings | `@NotBlank String name` |
| `@Size(max)` | String length limit | `@Size(max = 255) String name` |
| `@Email` | Email format | `@Email String email` |
| `@Min`/`@Max` | Numeric bounds | `@Min(0) Integer quantity` |
| `@Pattern` | Regex validation | `@Pattern(regexp = "\\d{8}") String dni` |
| `@Past`/`@Future` | Date validation | `@Past LocalDate birthDate` |

### Triggering Validation

Use `@Valid` on controller parameters:

```java
@PostMapping
public ResponseEntity<Operator> createOperator(
        @Valid @RequestBody OperatorRequest request) {
    // Validation runs automatically before method execution
}
```

## 6. Lombok Config

```properties
# lombok.config
lombok.addLombokGeneratedAnnotation = true
lombok.anyConstructor.addConstructorProperties = true
lombok.equalsAndHashCode.callSuper = warn
```

## 7. Anti-Patterns

```java
// BAD: @Data on JPA entity (includes ID in equals)
@Data @Entity
public class Operator { @Id private Long id; }

// BAD: @Setter on record (defeats immutability)
public record Operator(String name) {
    public void setName(String name) { } // WRONG
}

// BAD: @Data + @Builder without explicit constructors
@Data @Builder
class Point { private Double x; }  // Constructor conflict

// GOOD: Exclude lazy fields from toString
@ToString(exclude = "items")
@Entity
public class Order { }
```
