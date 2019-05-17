package com.vladimir.bittrexclient.config.twilio;

import com.twilio.http.TwilioRestClient;
import com.vladimir.bittrexclient.config.notifications.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwilioClient implements NotificationService {
    private TwilioApiCredentials credentials;
    private TwilioMessageCreator messageCreator;

    @Autowired
    public TwilioClient(TwilioApiCredentials twilioApiCredentials) {
        this.credentials = twilioApiCredentials;
        this.messageCreator = new TwilioMessageCreator(new TwilioRestClient.Builder(credentials.getAccountSid(), credentials.getAuthToken()).build());
    }

    @Override
    public void sendMessage(String to, String message) {
        messageCreator.create(to, credentials.getPhoneNumber(), message);
    }
}
