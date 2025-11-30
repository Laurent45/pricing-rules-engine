package com.boarhat.pricingrulesengine.domain.model;

import java.util.Objects;

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
