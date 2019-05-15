package com.vladimir.bittrexclient.config.twilio;

import com.vladimir.bittrexclient.config.bittrex.NotificationLimits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class TwilioMessageStatusHandler {
    @Autowired
    private NotificationLimits notificationLimits;
    private Map<String, Boolean> statusMap;

    public boolean getSmsStatusByCurrency(String currency) {
        return statusMap.get(currency);

    }

    public void setSmsStatusToCurrency(String currency, boolean smsSent) {
        statusMap.put(currency, smsSent);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void generateSmsStatusMap() {
        statusMap = new HashMap<>();
        for (String currency : notificationLimits.getLimits().keySet()) {
            statusMap.put(currency, false);
        }
    }
}
