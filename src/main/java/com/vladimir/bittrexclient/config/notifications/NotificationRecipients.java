package com.vladimir.bittrexclient.config.notifications;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationRecipients {
    @Value("#{'${twilio.sms.receivers}'.split(',')}")
    private List<String> recipients;

    public List<String> getAllRecipients() {
        return recipients;
    }
}
