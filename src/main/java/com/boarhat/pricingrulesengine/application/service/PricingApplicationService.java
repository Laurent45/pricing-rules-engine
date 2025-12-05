package com.boarhat.pricingrulesengine.application.service;

import com.boarhat.pricingrulesengine.domain.model.CommissionResult;
import com.boarhat.pricingrulesengine.domain.model.PricingContext;
import com.boarhat.pricingrulesengine.domain.model.PricingRule;
import com.boarhat.pricingrulesengine.domain.repository.PricingRuleRepository;
import com.boarhat.pricingrulesengine.domain.service.CommissionCalculator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Application service that orchestrates pricing use cases.
 * <p>
 * This service coordinates domain objects to fulfill business operations.
 * It contains no business logic itself, delegating all domain logic to
 * the domain layer (e.g., {@link CommissionCalculator}).
 */
@Service
public class PricingApplicationService {
    private final PricingRuleRepository pricingRuleRepository;
    private final CommissionCalculator commissionCalculator;

    public PricingApplicationService(PricingRuleRepository pricingRuleRepository, CommissionCalculator commissionCalculator) {
        this.pricingRuleRepository = pricingRuleRepository;
        this.commissionCalculator = commissionCalculator;
    }

    /**
     * Creates a new pricing rule.
     *
     * @param rule the pricing rule to persist
     */
    public void createRule(PricingRule rule) {
        pricingRuleRepository.save(rule);
    }

    /**
     * Calculates the commission for a given pricing context.
     *
     * @param context the pricing context to evaluate
     * @return the commission result with the applicable rate
     */
    public CommissionResult calculateCommission(PricingContext context) {
        List<PricingRule> rules = pricingRuleRepository.findAll();
        return commissionCalculator.calculate(context, rules);
    }
}
