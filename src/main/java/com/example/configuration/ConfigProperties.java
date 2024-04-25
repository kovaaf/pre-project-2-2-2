package com.example.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "myapplication")
public class ConfigProperties {
    private final String sortFields;

    public ConfigProperties(String sortFields) {
        this.sortFields = sortFields;
    }

    public String getSortFields() {
        return sortFields;
    }
}
