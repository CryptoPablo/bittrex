package com.vladimir.bittrexclient.config.notifications;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class BalanceNotificationLimits {
    @Value("#{${bittrex.balance.notification.limits}}")
    private Map<String, BigDecimal> establishedLimits;

    public Map<String, BigDecimal> getAllLimits() {
        return establishedLimits;
    }
}

