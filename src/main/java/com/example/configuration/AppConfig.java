package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean(name = "defaultRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
