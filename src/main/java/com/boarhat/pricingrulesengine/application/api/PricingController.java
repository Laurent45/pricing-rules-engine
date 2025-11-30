package com.boarhat.pricingrulesengine.application.api;

import com.boarhat.pricingrulesengine.application.api.dto.CalculateCommissionRequest;
import com.boarhat.pricingrulesengine.application.api.dto.CalculateCommissionResponse;
import com.boarhat.pricingrulesengine.application.service.PricingApplicationService;
import com.boarhat.pricingrulesengine.domain.model.CommissionResult;
import com.boarhat.pricingrulesengine.domain.model.PricingContext;
import com.boarhat.pricingrulesengine.domain.model.PricingRule;
import com.boarhat.pricingrulesengine.infrastructure.factory.PricingRuleFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.JsonNode;

@RestController
public class PricingController {

    private final PricingApplicationService pricingApplicationService;
    private final PricingRuleFactory pricingRuleFactory;
    private final PricingApiMapper pricingApiMapper;

    public PricingController(PricingApplicationService pricingApplicationService,
                             PricingRuleFactory pricingRuleFactory, PricingApiMapper pricingApiMapper) {
        this.pricingApplicationService = pricingApplicationService;
        this.pricingRuleFactory = pricingRuleFactory;
        this.pricingApiMapper = pricingApiMapper;
    }

    @PostMapping("/rules")
    public ResponseEntity<Void> createRule(@RequestBody JsonNode ruleJson) {
        PricingRule rule = pricingRuleFactory.create(ruleJson);
        pricingApplicationService.createRule(rule);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/calculate")
    public ResponseEntity<CalculateCommissionResponse> calculateCommission(
            @RequestBody CalculateCommissionRequest request) {

        PricingContext context = pricingApiMapper.toContext(request);
        CommissionResult result = pricingApplicationService.calculateCommission(context);
        return ResponseEntity.ok(pricingApiMapper.toResponse(result));
    }
}
