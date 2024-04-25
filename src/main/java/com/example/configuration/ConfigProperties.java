package com.example.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "myapplication")
public class ConfigProperties {
    private final Integer maxCar;
    private final String sortFields;

    public ConfigProperties(Integer maxCar, String sortFields) {
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
