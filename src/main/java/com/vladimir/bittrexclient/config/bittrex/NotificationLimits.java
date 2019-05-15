package com.vladimir.bittrexclient.config.bittrex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties("bittrex.balance.limits")
public class NotificationLimits {
    @Value("#{${bittrex.balance.limits}}")
    private Map<String, BigDecimal> establishedLimits;
    private Map<String, Boolean> statusMap;

    public Map<String, BigDecimal> getLimits() {
        return establishedLimits;
    }

    public Map<String, Boolean> getStatusMap() {
        return statusMap;
    }

    public boolean getStatusElement(String key) {
        return statusMap.get(key);

    }

    public void setStatusElement(String key, boolean status){
        statusMap.put(key, status);
    }


    public Map<String, Boolean> generateMap() {
        statusMap = new HashMap<>();
        for (String currency : establishedLimits.keySet()) {
            statusMap.put(currency, false);
        }
        return statusMap;
    }
}
