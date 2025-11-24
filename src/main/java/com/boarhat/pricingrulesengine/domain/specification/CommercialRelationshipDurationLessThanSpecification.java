package com.boarhat.pricingrulesengine.domain.specification;

import com.boarhat.pricingrulesengine.domain.model.Duration;
import com.boarhat.pricingrulesengine.domain.model.PricingContext;

import java.util.Objects;

public record CommercialRelationshipDurationLessThanSpecification(Duration duration) implements Specification<PricingContext> {
    public CommercialRelationshipDurationLessThanSpecification {
        Objects.requireNonNull(duration, "Duration cannot be null");
    }

    @Override
    public boolean isSatisfiedBy(PricingContext candidate) {
        return candidate.commercialRelationship().getDuration().isLessThan(duration);
    }
}
