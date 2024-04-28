package com.example.configuration.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "loan")
public class LoanProperties {
    private final Long minimalIncome;
    private final Long minimalCarPrice;
    private final Long maxShare;
    private final String url;

    public LoanProperties(Long minimalIncome, Long minimalCarPrice, Long maxShare, String url) {
        this.minimalIncome = minimalIncome;
        this.minimalCarPrice = minimalCarPrice;
        this.maxShare = maxShare;
        this.url = url;
    }
}
