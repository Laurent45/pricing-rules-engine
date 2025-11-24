package com.boarhat.pricingrulesengine.domain.specification;

import com.boarhat.pricingrulesengine.domain.model.Duration;
import com.boarhat.pricingrulesengine.domain.model.PricingContext;

import java.util.Objects;

public record CommercialRelationshipDurationGreaterThanSpecification(Duration duration) implements Specification<PricingContext> {
    public CommercialRelationshipDurationGreaterThanSpecification {
        Objects.requireNonNull(duration, "Duration cannot be null");
    }

    @Override
    public boolean isSatisfiedBy(PricingContext candidate) {
        return candidate.commercialRelationship().getDuration().isGreaterThan(duration);
    }
}
