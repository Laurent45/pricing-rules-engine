package com.boarhat.pricingrulesengine.domain.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.Objects;

public record CommercialRelationship(Instant firstProject, Instant lastProject) {

    public CommercialRelationship {
        Objects.requireNonNull(firstProject, "First project cannot be null");
        Objects.requireNonNull(lastProject, "Last project cannot be null");

        if (firstProject.isAfter(lastProject)) {
            throw new IllegalArgumentException("First project cannot be after last project");
        }
    }

    public Duration getDuration() {
        LocalDate start = LocalDate.ofInstant(firstProject, ZoneOffset.UTC);
        LocalDate end = LocalDate.ofInstant(lastProject, ZoneOffset.UTC);
        Period period = Period.between(start, end);
        return Duration.of(period);
    }
}