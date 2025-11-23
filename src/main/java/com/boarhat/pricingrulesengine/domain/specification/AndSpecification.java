package com.boarhat.pricingrulesengine.domain.specification;

import java.util.Objects;

public record AndSpecification<T>(Specification<T> left, Specification<T> right) implements Specification<T> {
    public AndSpecification {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return left.isSatisfiedBy(candidate) && right.isSatisfiedBy(candidate);
    }

    @Override
    public String toString() {
        return "(" + left + " AND " + right + ")";
    }
}
