package com.example.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "car")
public class CarProperties {
    private final Integer maxCar;
    private final String sortFields;

    public CarProperties(Integer maxCar, String sortFields) {
        this.maxCar = maxCar;
        this.sortFields = sortFields;
    }

    public Integer getMaxCar() {
        return maxCar;
    }

    public String getSortFields() {
        return sortFields;
    }
}
