package com.boarhat.pricingrulesengine.domain.service;

import com.boarhat.pricingrulesengine.domain.model.IpAddress;
import com.boarhat.pricingrulesengine.domain.model.Location;

/**
 * Port for resolving IP addresses to locations.
 * The domain defines this contract; infrastructure provides the implementation.
 */
public interface GeolocationService {

    /**
     * Resolves an IP address to its geographic location.
     *
     * @param ipAddress the IP address to resolve
     * @return the location associated with the IP address
     * @throws GeolocationException if the IP address cannot be resolved
     */
    Location resolve(IpAddress ipAddress);
}
