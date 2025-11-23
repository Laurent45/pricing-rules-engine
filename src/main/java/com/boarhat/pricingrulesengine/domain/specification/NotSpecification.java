package com.boarhat.pricingrulesengine.domain.specification;

import java.util.Objects;

public record NotSpecification<T>(Specification<T> specification) implements Specification<T> {
    public NotSpecification {
        Objects.requireNonNull(specification);
    }

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return !specification.isSatisfiedBy(candidate);
    }

    @Override
    public String toString() {
        return "NOT(" + specification + ")";
    }
}
