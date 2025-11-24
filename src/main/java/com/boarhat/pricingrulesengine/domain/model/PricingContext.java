package com.boarhat.pricingrulesengine.domain.model;

public record PricingContext(Party client,
                             Party freelancer,
                             Project project,
                             CommercialRelationship commercialRelationship) {}
