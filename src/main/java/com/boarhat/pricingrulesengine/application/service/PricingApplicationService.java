package com.boarhat.pricingrulesengine.application.service;

import com.boarhat.pricingrulesengine.domain.model.CommissionRate;
import com.boarhat.pricingrulesengine.domain.model.PricingContext;
import com.boarhat.pricingrulesengine.domain.model.PricingRule;
import com.boarhat.pricingrulesengine.domain.repository.PricingRuleRepository;
import com.boarhat.pricingrulesengine.domain.service.CommissionCalculator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingApplicationService {
    private final PricingRuleRepository pricingRuleRepository;
    private final CommissionCalculator commissionCalculator;

    public PricingApplicationService(PricingRuleRepository pricingRuleRepository, CommissionCalculator commissionCalculator) {
        this.pricingRuleRepository = pricingRuleRepository;
        this.commissionCalculator = commissionCalculator;
    }

    public void createRule(PricingRule rule) {
        pricingRuleRepository.save(rule);
    }

    public CommissionRate calculateCommission(PricingContext context) {
        List<PricingRule> rules = pricingRuleRepository.findAll();
        return commissionCalculator.calculate(context, rules);
    }
}
