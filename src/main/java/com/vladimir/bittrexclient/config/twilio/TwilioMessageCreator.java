package com.vladimir.bittrexclient.config.twilio;

import com.twilio.exception.TwilioException;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

public class TwilioMessageCreator {
    private final TwilioRestClient client;

    public TwilioMessageCreator(TwilioRestClient client) {
        this.client = client;
    }

    public void create(String to, String from, String body) {
        MessageCreator messageCreator = new MessageCreator(
                new PhoneNumber(to),
                new PhoneNumber(from),
                body);
        try {
            messageCreator.create(this.client);
        } catch (TwilioException e) {
           e.getMessage();
        }
    }
}
