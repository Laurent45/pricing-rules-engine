package com.boarhat.pricingrulesengine.domain.model;

import java.util.Objects;

public record Party(IpAddress ip, Location location) {
    public Party {
        Objects.requireNonNull(ip, "IP address cannot be null");
        Objects.requireNonNull(location, "Location cannot be null");
    }
}
