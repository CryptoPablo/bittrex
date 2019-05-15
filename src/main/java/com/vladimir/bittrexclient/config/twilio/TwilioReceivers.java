package com.vladimir.bittrexclient.config.twilio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "twilio.sms.receivers")
public class TwilioReceivers {
    @Value("#{'${twilio.sms.receivers}'.split(',')}")
    private List<String> messageReceivers;

    public List<String> getAllMessageReceivers(){
        return messageReceivers;
    }
}
