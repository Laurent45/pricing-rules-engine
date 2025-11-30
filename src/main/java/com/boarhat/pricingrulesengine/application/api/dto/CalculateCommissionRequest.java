package com.boarhat.pricingrulesengine.application.api.dto;

import java.time.Instant;

public record CalculateCommissionRequest(
        PartyDto client,
        PartyDto freelancer,
        CommercialRelationshipDto commercialRelationship,
        ProjectDto project
) {
    public record PartyDto(String ip) {}

    public record CommercialRelationshipDto(Instant firstProject, Instant lastProject) {}

    public record ProjectDto(String duration) {}
}
