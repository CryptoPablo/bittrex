package com.vladimir.bittrexclient.config.bittrex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
@ConfigurationProperties("bittrex.notification.limits")
public class BalanceNotificationLimits {
    @Value("#{${bittrex.notification.limits}}")
    private Map<String, BigDecimal> establishedLimits;

    public Map<String, BigDecimal> getAllLimits() {
        return establishedLimits;
    }
}

