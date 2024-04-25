package com.example.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

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

    public Long getMinimalIncome() {
        return minimalIncome;
    }

    public Long getMinimalCarPrice() {
        return minimalCarPrice;
    }

    public Long getMaxShare() {
        return maxShare;
    }

    public String getUrl() {
        return url;
    }
}
