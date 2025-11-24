package com.boarhat.pricingrulesengine.domain.specification;

import com.boarhat.pricingrulesengine.domain.model.Location;
import com.boarhat.pricingrulesengine.domain.model.PricingContext;

import java.util.Objects;

public record ClientLocationSpecification(Location location) implements Specification<PricingContext> {
    public ClientLocationSpecification {
        Objects.requireNonNull(location, "Location cannot be null");
    }

    @Override
    public boolean isSatisfiedBy(PricingContext candidate) {
        return candidate.client().location().equals(location);
    }
}
