package com.example.configuration.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "car")
public class CarProperties {
    private final Integer maxCars;
    private final String sortFields;

    public CarProperties(Integer maxCars, String sortFields) {
        this.maxCars = maxCars;
        this.sortFields = sortFields;
    }
}
