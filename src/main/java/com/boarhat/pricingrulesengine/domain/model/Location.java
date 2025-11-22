package com.boarhat.pricingrulesengine.domain.model;

public record Location(String country) {
    public Location {
        if (country == null) {
            throw new IllegalArgumentException("Country code cannot be null");
        }

        if (!country.matches("[A-Za-z]{2}")) {
            throw new IllegalArgumentException(
                    String.format("Invalid country code '%s'. Must be 2 letters (ISO 3166-1 alpha-2)",
                            country)
            );
        }

        country = country.toUpperCase();
    }
}
