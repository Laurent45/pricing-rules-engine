package com.boarhat.pricingrulesengine.domain.model;

import com.boarhat.pricingrulesengine.domain.specification.Specification;

import java.util.Objects;

/**
 * Aggregate root representing a pricing rule.
 * <p>
 * A pricing rule associates a commission rate with a business specification.
 * When the specification is satisfied by a {@link PricingContext}, the rule's
 * commission rate applies to the transaction.
 * <p>
 * Rules are identified by their unique name and are immutable once created.
 */
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

    /**
     * Evaluates whether this rule applies to the given pricing context.
     *
     * @param pricingContext the context containing client, freelancer, and project details
     * @return true if the rule's specification is satisfied by the context
     */
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
