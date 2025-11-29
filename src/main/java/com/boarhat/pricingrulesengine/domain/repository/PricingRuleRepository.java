package com.boarhat.pricingrulesengine.domain.repository;

import com.boarhat.pricingrulesengine.domain.model.PricingRule;

import java.util.List;
import java.util.Optional;

public interface PricingRuleRepository {

    /**
     * Saves a pricing rule to the repository.
     * If a rule with the same name exists, it will be replaced.
     *
     * @param rule the pricing rule to save
     */
    void save(PricingRule rule);

    /**
     * Finds a pricing rule by its unique name.
     *
     * @param name the rule name
     * @return an Optional containing the rule if found, empty otherwise
     */
    Optional<PricingRule> findByName(String name);

    /**
     * Returns all pricing rules in the repository.
     *
     * @return a list of all rules (never null, may be empty)
     */
    List<PricingRule> findAll();
}
