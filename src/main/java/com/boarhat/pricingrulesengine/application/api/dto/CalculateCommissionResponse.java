package com.boarhat.pricingrulesengine.application.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CalculateCommissionResponse(String fees, String reason) {}
