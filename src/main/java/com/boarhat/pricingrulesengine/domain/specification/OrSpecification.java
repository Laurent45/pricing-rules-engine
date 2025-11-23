package com.boarhat.pricingrulesengine.domain.specification;

import java.util.Objects;

public record OrSpecification<T>(Specification<T> left, Specification<T> right) implements Specification<T> {
    public OrSpecification {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return left.isSatisfiedBy(candidate) || right.isSatisfiedBy(candidate);
    }

    @Override
    public String toString() {
        return "(" + left + " OR " + right + ")";
    }
}
