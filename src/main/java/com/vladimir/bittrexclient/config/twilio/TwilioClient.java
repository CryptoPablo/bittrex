package com.vladimir.bittrexclient.config.twilio;

import com.twilio.http.TwilioRestClient;

public class TwilioClient {
    private TwilioApiCredentials credentials;
    private TwilioMessageCreator messageCreator;

    public TwilioClient() {
        this.credentials = new TwilioApiCredentials();
        this.messageCreator = new TwilioMessageCreator(
                new TwilioRestClient.Builder(credentials.getAccountSid(), credentials.getAuthToken()).build()
        );
    }

    public TwilioClient(TwilioMessageCreator messageCreator, TwilioApiCredentials credentials) {
        this.credentials = credentials;
        this.messageCreator = messageCreator;
    }

    public void sendMessage(String to, String message) {
        messageCreator.create(to, credentials.getPhoneNumber(), message);
    }
}
