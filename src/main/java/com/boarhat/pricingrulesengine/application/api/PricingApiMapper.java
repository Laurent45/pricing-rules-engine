package com.boarhat.pricingrulesengine.application.api;

import com.boarhat.pricingrulesengine.application.api.dto.CalculateCommissionRequest;
import com.boarhat.pricingrulesengine.application.api.dto.CalculateCommissionResponse;
import com.boarhat.pricingrulesengine.domain.model.CommercialRelationship;
import com.boarhat.pricingrulesengine.domain.model.CommissionResult;
import com.boarhat.pricingrulesengine.domain.model.Duration;
import com.boarhat.pricingrulesengine.domain.model.IpAddress;
import com.boarhat.pricingrulesengine.domain.model.Party;
import com.boarhat.pricingrulesengine.domain.model.PricingContext;
import com.boarhat.pricingrulesengine.domain.model.Project;
import com.boarhat.pricingrulesengine.domain.service.GeolocationService;
import org.springframework.stereotype.Component;

@Component
public class PricingApiMapper {

    private final GeolocationService geolocationService;

    public PricingApiMapper(GeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }

    public PricingContext toContext(CalculateCommissionRequest request) {
        Party client = toParty(request.client());
        Party freelancer = toParty(request.freelancer());

        Project project = new Project(Duration.of(request.project().duration()));

        CommercialRelationship commercialRelationship = new CommercialRelationship(
                request.commercialRelationship().firstProject(),
                request.commercialRelationship().lastProject()
        );

        return new PricingContext(client, freelancer, project, commercialRelationship);
    }

    public CalculateCommissionResponse toResponse(CommissionResult result) {
        String fees = result.rate().value().toPlainString();

        if (result.ruleName() == null) {
            return new CalculateCommissionResponse(fees, null);
        }

        return new CalculateCommissionResponse(fees, result.ruleName());
    }

    private Party toParty(CalculateCommissionRequest.PartyDto partyDto) {
        IpAddress ipAddress = new IpAddress(partyDto.ip());
        var location = geolocationService.resolve(ipAddress);
        return new Party(ipAddress, location);
    }
}
