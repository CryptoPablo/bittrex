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

    public boolean getMessageStatusByElement(String element) {
        return statusMap.get(element);

    }

    public void setMessageStatusToElement(String element, boolean smsSent) {
        statusMap.put(element, smsSent);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void generateMessageStatusMap() {
        statusMap = new HashMap<>();
        for (String element : notificationLimits.getLimits().keySet()) {
            statusMap.put(element, false);
        }
    }
}
