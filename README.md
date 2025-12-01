# Pricing Rules Engine

A DDD reference implementation of a commission calculation engine.

## Business Summary

Freelance marketplace platforms charge commission fees on transactions between clients and freelancers. A flat rate is rarely optimal due to varying market dynamics, regional differences, and the need to reward loyalty.

This engine calculates dynamic commission rates by evaluating contextual factors:

| Factor | Description |
|--------|-------------|
| **Freelancer Location** | Country where the freelancer operates |
| **Client Location** | Country where the client is based |
| **Project Duration** | Length of the engagement (weeks, months, years) |
| **Commercial Relationship** | History between the client and freelancer |

Rules can be composed using logical operators (AND, OR, NOT) and are evaluated in priority order. The first matching rule determines the commission rate, with a 10% default fallback when no rule applies.

## Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                      INFRASTRUCTURE LAYER                        │
│         (REST Controllers, Repositories, External Services)      │
├─────────────────────────────────────────────────────────────────┤
│                       APPLICATION LAYER                          │
│                 (Use Cases, DTOs, Mappers)                       │
├─────────────────────────────────────────────────────────────────┤
│                         DOMAIN LAYER                             │
│       (Entities, Value Objects, Domain Services, Specifications) │
└─────────────────────────────────────────────────────────────────┘
```

### Layer Responsibilities

| Layer | Purpose | Dependencies |
|-------|---------|--------------|
| **Domain** | Pure business logic, no framework dependencies | None |
| **Application** | Orchestrates use cases, coordinates domain objects | Domain |
| **Infrastructure** | Technical implementations (DB, APIs, Web) | Application, Domain |

### Package Structure

```
src/main/java/com/boarhat/pricingrulesengine/
├── domain/
│   ├── model/           # Entities & Value Objects
│   ├── service/         # Domain Services
│   ├── specification/   # Specification Pattern
│   └── repository/      # Repository Interfaces (Ports)
│
├── application/
│   ├── service/         # Application Services
│   └── api/             # Controllers, DTOs, Mappers
│
└── infrastructure/
    ├── repository/      # Repository Implementations
    ├── geolocation/     # External Service Adapters
    ├── factory/         # Object Factories
    └── config/          # Spring Configuration
```

## DDD Patterns

### Specification Pattern

Composable business rules using AND, OR, NOT operators:

```java
Specification<PricingContext> rule =
    new FreelancerLocationSpecification(new Location("DE"))
        .and(new ProjectDurationGreaterThanSpecification(Duration.of("3 months")));
```

### Repository Pattern

Domain defines interfaces, infrastructure implements:

```java
// Domain (port)
public interface PricingRuleRepository {
    List<PricingRule> findAll();
}

// Infrastructure (adapter)
public class InMemoryPricingRuleRepository implements PricingRuleRepository { }
```

### Domain Service

Stateless operations that don't belong to a single entity:

```java
public class CommissionCalculator {
    public CommissionResult calculate(PricingContext context, List<PricingRule> rules);
}
```

### Value Objects

Immutable, self-validating domain concepts: `CommissionRate`, `Location`, `Duration`, `Party`, `Project`.

## Getting Started

```bash
./mvnw spring-boot:run
```

API available at `http://localhost:8080`.

## Tech Stack

Java 25 | Spring Boot 4.0.0 | Maven
