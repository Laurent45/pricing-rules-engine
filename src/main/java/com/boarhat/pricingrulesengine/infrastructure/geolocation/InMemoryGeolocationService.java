package com.boarhat.pricingrulesengine.infrastructure.geolocation;

import com.boarhat.pricingrulesengine.domain.model.IpAddress;
import com.boarhat.pricingrulesengine.domain.model.Location;
import com.boarhat.pricingrulesengine.domain.service.GeolocationException;
import com.boarhat.pricingrulesengine.domain.service.GeolocationService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * In-memory implementation of GeolocationService using hardcoded IP-to-country mappings.
 * As specified in the requirements for Step 1.
 */
@Service
public class InMemoryGeolocationService implements GeolocationService {

    private static final Map<String, String> IP_TO_COUNTRY = Map.of(
            "102.129.156.42", "DE",
            "217.127.206.227", "ES",
            "8.29.230.164", "FR",
            "1.186.0.7", "GB"
    );

    @Override
    public Location resolve(IpAddress ipAddress) {
        String countryCode = IP_TO_COUNTRY.get(ipAddress.ip());

        if (countryCode == null) {
            throw new GeolocationException("Unable to resolve location for IP: " + ipAddress.ip());
        }

        return new Location(countryCode);
    }
}
