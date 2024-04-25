package com.example.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "car")
public class CarProperties {
    private final Integer maxCar;

    public CarProperties(Integer maxCar) {
        this.maxCar = maxCar;
    }

    public Integer getMaxCar() {
        return maxCar;
    }
}
