package com.boarhat.pricingrulesengine.infrastructure.factory;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents the valid restriction types that can appear in pricing rule JSON.
 * This enum maps JSON keys to their corresponding restriction types.
 */
public enum RestrictionType {
    CLIENT_LOCATION("@client.location"),
    FREELANCER_LOCATION("@freelancer.location"),
    PROJECT_DURATION("@project.duration"),
    COMMERCIAL_RELATIONSHIP_DURATION("@commercialRelationship.duration"),
    OR("@or"),
    AND("@and"),
    NOT("@not");

    private final String jsonKey;

    RestrictionType(String jsonKey) {
        this.jsonKey = jsonKey;
    }

    public String getJsonKey() {
        return jsonKey;
    }

    public static RestrictionType fromJsonKey(String key) {
        return Arrays.stream(values())
                .filter(type -> type.jsonKey.equals(key))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unknown restriction key: '" + key + "'. Valid keys are: " + validKeys()));
    }

    private static String validKeys() {
        return Arrays.stream(values())
                .map(RestrictionType::getJsonKey)
                .collect(Collectors.joining(", "));
    }
}
