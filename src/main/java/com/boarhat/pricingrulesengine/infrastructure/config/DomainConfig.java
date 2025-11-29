package com.boarhat.pricingrulesengine.infrastructure.config;

import com.boarhat.pricingrulesengine.domain.service.CommissionCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {
    @Bean
    public CommissionCalculator commissionCalculator() {
        return new CommissionCalculator();
    }
}
