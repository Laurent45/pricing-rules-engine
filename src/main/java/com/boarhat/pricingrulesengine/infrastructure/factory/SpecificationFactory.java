package com.boarhat.pricingrulesengine.infrastructure.factory;

import com.boarhat.pricingrulesengine.domain.model.Duration;
import com.boarhat.pricingrulesengine.domain.model.Location;
import com.boarhat.pricingrulesengine.domain.model.PricingContext;
import com.boarhat.pricingrulesengine.domain.specification.ClientLocationSpecification;
import com.boarhat.pricingrulesengine.domain.specification.CommercialRelationshipDurationGreaterThanSpecification;
import com.boarhat.pricingrulesengine.domain.specification.CommercialRelationshipDurationLessThanSpecification;
import com.boarhat.pricingrulesengine.domain.specification.FreelancerLocationSpecification;
import com.boarhat.pricingrulesengine.domain.specification.ProjectDurationGreaterThanSpecification;
import com.boarhat.pricingrulesengine.domain.specification.ProjectDurationLessThanSpecification;
import com.boarhat.pricingrulesengine.domain.specification.Specification;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;

import java.util.Map;

@Component
public class SpecificationFactory {

    public Specification<PricingContext> create(JsonNode restrictions) {
        if (restrictions == null || restrictions.isEmpty()) {
            return (_) -> true;
        }

        return restrictions.propertyStream()
                .map(this::parseRestriction)
                .reduce(Specification::and)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Restrictions object is empty or contains no valid properties"));
    }

    private Specification<PricingContext> parseRestriction(Map.Entry<String, JsonNode> restriction) {
        String key = restriction.getKey();
        JsonNode value = restriction.getValue();
        RestrictionType type = RestrictionType.fromJsonKey(key);

        return switch (type) {
            case OR -> parseOr(value);
            case AND -> parseAnd(value);
            case NOT -> parseNot(value);
            case CLIENT_LOCATION -> parseClientLocation(value);
            case FREELANCER_LOCATION -> parseFreelancerLocation(value);
            case PROJECT_DURATION -> parseProjectDuration(value);
            case COMMERCIAL_RELATIONSHIP_DURATION -> parseCommercialRelationshipDuration(value);
        };
    }

    private Specification<PricingContext> parseClientLocation(JsonNode node) {
        if (!node.has("country")) {
            throw new IllegalArgumentException(
                    "@client.location requires 'country' field. Got: " + node);
        }
        if (node.propertyNames().size() != 1) {
            throw new IllegalArgumentException(
                    "@client.location must have exactly one field ('country'). Got: " + node.propertyNames());
        }

        String country = node.get("country").asString();
        return new ClientLocationSpecification(new Location(country));
    }

    private Specification<PricingContext> parseFreelancerLocation(JsonNode node) {
        if (!node.has("country")) {
            throw new IllegalArgumentException(
                    "@freelancer.location requires 'country' field. Got: " + node);
        }
        if (node.propertyNames().size() != 1) {
            throw new IllegalArgumentException(
                    "@freelancer.location must have exactly one field ('country'). Got: " + node.propertyNames());
        }

        String country = node.get("country").asString();
        return new FreelancerLocationSpecification(new Location(country));
    }

    private Specification<PricingContext> parseProjectDuration(JsonNode node) {
        if (node.propertyNames().size() != 1) {
            throw new IllegalArgumentException(
                    "@project.duration must have exactly one operator ('gt' or 'lt'). Got: " + node.propertyNames());
        }

        if (node.has("gt")) {
            String duration = node.get("gt").asString();
            return new ProjectDurationGreaterThanSpecification(Duration.of(duration));
        } else if (node.has("lt")) {
            String duration = node.get("lt").asString();
            return new ProjectDurationLessThanSpecification(Duration.of(duration));
        }
        throw new IllegalArgumentException(
                "@project.duration requires 'gt' or 'lt' operator. Got: " + node.propertyNames());
    }

    private Specification<PricingContext> parseCommercialRelationshipDuration(JsonNode node) {
        if (node.propertyNames().size() != 1) {
            throw new IllegalArgumentException(
                    "@commercialRelationship.duration must have exactly one operator ('gt' or 'lt'). Got: " + node.propertyNames());
        }

        if (node.has("gt")) {
            String duration = node.get("gt").asString();
            return new CommercialRelationshipDurationGreaterThanSpecification(Duration.of(duration));
        } else if (node.has("lt")) {
            String duration = node.get("lt").asString();
            return new CommercialRelationshipDurationLessThanSpecification(Duration.of(duration));
        }
        throw new IllegalArgumentException(
                "@commercialRelationship.duration requires 'gt' or 'lt' operator. Got: " + node.propertyNames());
    }

    private Specification<PricingContext> parseNot(JsonNode value) {
        return value.propertyStream()
                .map(this::parseRestriction)
                .reduce(Specification::and)
                .orElseThrow(() -> new IllegalArgumentException(
                        "@not must contain at least one specification to negate"))
                .not();
    }

    private Specification<PricingContext> parseAnd(JsonNode value) {
        return value.propertyStream()
                .map(this::parseRestriction)
                .reduce(Specification::and)
                .orElseThrow(() -> new IllegalArgumentException(
                        "@and must contain at least two specifications to combine"));
    }

    private Specification<PricingContext> parseOr(JsonNode value) {
        return value.propertyStream()
                .map(this::parseRestriction)
                .reduce(Specification::or)
                .orElseThrow(() -> new IllegalArgumentException(
                        "@or must contain at least two specifications to combine"));
    }

}
