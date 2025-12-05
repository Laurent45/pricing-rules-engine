package com.boarhat.pricingrulesengine.domain.model;

/**
 * Value object representing the context for commission calculation.
 * <p>
 * Contains all the information needed to evaluate pricing rules:
 * the client, freelancer, project details, and their commercial relationship history.
 *
 * @param client the client party involved in the transaction
 * @param freelancer the freelancer party involved in the transaction
 * @param project the project being commissioned
 * @param commercialRelationship the history between client and freelancer
 */
public record PricingContext(Party client,
                             Party freelancer,
                             Project project,
                             CommercialRelationship commercialRelationship) {}
