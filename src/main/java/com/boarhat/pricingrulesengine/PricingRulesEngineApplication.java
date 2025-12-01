package com.boarhat.pricingrulesengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.boarhat.pricingrulesengine.application",
        "com.boarhat.pricingrulesengine.infrastructure"
})
public class PricingRulesEngineApplication {

    static void main(String[] args) {
        SpringApplication.run(PricingRulesEngineApplication.class, args);
    }

}
