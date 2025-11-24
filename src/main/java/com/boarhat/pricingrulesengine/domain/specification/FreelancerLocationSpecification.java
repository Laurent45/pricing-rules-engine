package com.boarhat.pricingrulesengine.domain.specification;

import com.boarhat.pricingrulesengine.domain.model.Location;
import com.boarhat.pricingrulesengine.domain.model.PricingContext;

import java.util.Objects;

public record FreelancerLocationSpecification(Location location) implements Specification<PricingContext> {
    public FreelancerLocationSpecification {
        Objects.requireNonNull(location, "Location cannot be null");
    }

    @Override
    public boolean isSatisfiedBy(PricingContext candidate) {
        return candidate.freelancer().location().equals(location);
    }
}
