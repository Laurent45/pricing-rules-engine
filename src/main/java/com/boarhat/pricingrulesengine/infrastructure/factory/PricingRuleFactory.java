package com.boarhat.pricingrulesengine.infrastructure.factory;

import com.boarhat.pricingrulesengine.domain.model.CommissionRate;
import com.boarhat.pricingrulesengine.domain.model.PricingContext;
import com.boarhat.pricingrulesengine.domain.model.PricingRule;
import com.boarhat.pricingrulesengine.domain.specification.Specification;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;

import java.math.BigDecimal;

@Component
public class PricingRuleFactory {

    private final SpecificationFactory specificationFactory;

    public PricingRuleFactory(SpecificationFactory specificationFactory) {
        this.specificationFactory = specificationFactory;
    }

    public PricingRule create(JsonNode ruleJson) {
        String name = ruleJson.get("name").asString();
        BigDecimal rate = new BigDecimal(ruleJson.get("rate").get("percent").asString());
        Specification<PricingContext> spec = specificationFactory.create(ruleJson.get("restrictions"));

        return new PricingRule(name, CommissionRate.of(rate), spec);
    }
}
