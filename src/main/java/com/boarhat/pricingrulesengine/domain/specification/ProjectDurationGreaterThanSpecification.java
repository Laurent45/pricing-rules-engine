package com.boarhat.pricingrulesengine.domain.specification;

import com.boarhat.pricingrulesengine.domain.model.Duration;
import com.boarhat.pricingrulesengine.domain.model.PricingContext;

import java.util.Objects;

public record ProjectDurationGreaterThanSpecification(Duration duration) implements Specification<PricingContext> {
    public ProjectDurationGreaterThanSpecification {
        Objects.requireNonNull(duration, "Project duration cannot be null");
    }

    @Override
    public boolean isSatisfiedBy(PricingContext candidate) {
        return candidate.project().duration().isGreaterThan(duration);
    }
}
