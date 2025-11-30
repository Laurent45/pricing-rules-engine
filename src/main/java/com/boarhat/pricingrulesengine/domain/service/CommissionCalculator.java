package com.boarhat.pricingrulesengine.domain.service;

import com.boarhat.pricingrulesengine.domain.model.CommissionResult;
import com.boarhat.pricingrulesengine.domain.model.PricingContext;
import com.boarhat.pricingrulesengine.domain.model.PricingRule;

import java.util.List;

public class CommissionCalculator {

    /**
     * Calculates the commission rate for a given context.
     * <p>
     * Rules are evaluated in order - the first matching rule wins.
     * If no rule matches, the default commission rate (10%) is returned.
     *
     * @param context the pricing context to evaluate
     * @param rules the list of pricing rules to check (evaluated in order)
     * @return the commission rate from the first matching rule, or default rate
     */
    public CommissionResult calculate(PricingContext context, List<PricingRule> rules) {
        return rules.stream()
                .filter(rule -> rule.matches(context))
                .findFirst()
                .map(CommissionResult::fromRule)
                .orElse(CommissionResult.defaultRate());
    }
}
