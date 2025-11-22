package com.boarhat.pricingrulesengine.domain.model;

import java.net.Inet4Address;
import java.util.Objects;

public record IpAddress(String ip) {

    public IpAddress {
        Objects.requireNonNull(ip, "IP address cannot be null");

        try {
            Inet4Address.ofLiteral(ip);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    String.format("Invalid IP address format: '%s'. Expected IPv4 format (e.g., 192.168.1.1)", ip), e
            );
        }
    }
}
