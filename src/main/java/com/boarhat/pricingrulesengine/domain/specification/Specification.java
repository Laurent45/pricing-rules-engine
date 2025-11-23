package com.boarhat.pricingrulesengine.domain.specification;

/**
 * Specification pattern: encapsulates a business rule that can be
 * checked against a candidate object.
 *
 * @param <T> the type of object to evaluate
 */
public interface Specification<T> {
    /**
     * Check if the candidate satisfies this specification.
     *
     * @param candidate the object to check
     * @return true if the specification is satisfied
     */
    boolean isSatisfiedBy(T candidate);

    /**
     * Combine this specification with another using AND logic.
     */
    default Specification<T> and(Specification<T> other) {
        return new AndSpecification<>(this, other);
    }

    /**
     * Combine this specification with another using OR logic.
     */
    default Specification<T> or(Specification<T> other) {
        return new OrSpecification<>(this, other);
    }

    /**
     * Negate this specification.
     */
    default Specification<T> not() {
        return new NotSpecification<>(this);
    }
}
