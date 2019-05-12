package com.vladimir.bittrexclient.config.twilio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "twilio.sms.receivers")
public class TwilioReceivers {
    @Value("#{'${twilio.sms.receivers}'.split(',')}")
    private List<String> receivers;

    public List<String> getAllReceivers(){
        return receivers;
    }
}
