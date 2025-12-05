package com.boarhat.pricingrulesengine.domain.model;

import java.util.Objects;

/**
 * Value object representing the result of a commission calculation.
 * <p>
 * Contains the calculated commission rate and the name of the rule that triggered it.
 * When no rule matches, {@link #defaultRate()} returns a result with a 10% rate and null rule name.
 *
 * @param rate the calculated commission rate
 * @param ruleName the name of the matching rule, or null if the default rate was applied
 */
public record CommissionResult(CommissionRate rate, String ruleName) {
    public CommissionResult {
        Objects.requireNonNull(rate,  "Commission Rate cannot be null");
    }

    public static CommissionResult fromRule(PricingRule rule) {
        return new CommissionResult(rule.getRate(), rule.getName());
    }

    public static CommissionResult defaultRate() {
        return new CommissionResult(CommissionRate.defaultRate(), null);
    }

}
