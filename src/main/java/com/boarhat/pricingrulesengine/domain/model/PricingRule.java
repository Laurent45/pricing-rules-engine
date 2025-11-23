package com.boarhat.pricingrulesengine.domain.model;

import com.boarhat.pricingrulesengine.domain.specification.Specification;

import java.util.Objects;

public final class PricingRule {
    private final String name;
    private final CommissionRate rate;
    private final Specification<PricingContext> specification;

    public PricingRule(String name, CommissionRate rate, Specification<PricingContext> specification) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        Objects.requireNonNull(rate, "CommissionRate cannot be null");
        Objects.requireNonNull(specification, "Specification cannot be null");

        this.name = name;
        this.rate = rate;
        this.specification = specification;
    }

    public String getName() {
        return name;
    }

    public CommissionRate getRate() {
        return rate;
    }

    public Specification<PricingContext> getSpecification() {
        return specification;
    }

    public boolean matches(PricingContext pricingContext) {
        return specification.isSatisfiedBy(pricingContext);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PricingRule that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
