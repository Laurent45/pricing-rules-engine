package com.boarhat.pricingrulesengine.domain.model;

import java.util.Objects;

public record Project(Duration duration) {
    public Project {
        Objects.requireNonNull(duration, "Duration cannot be null");
    }
}
