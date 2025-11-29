package com.boarhat.pricingrulesengine.infrastructure.repository;

import com.boarhat.pricingrulesengine.domain.model.PricingRule;
import com.boarhat.pricingrulesengine.domain.repository.PricingRuleRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryPricingRuleRepository implements PricingRuleRepository {

    private final Map<String, PricingRule> rules = Collections.synchronizedMap(new LinkedHashMap<>());

    @Override
    public void save(PricingRule rule) {
        rules.put(rule.getName(), rule);
    }

    @Override
    public Optional<PricingRule> findByName(String name) {
        return Optional.ofNullable(rules.get(name));
    }

    @Override
    public List<PricingRule> findAll() {
        return List.copyOf(rules.values());
    }
}
