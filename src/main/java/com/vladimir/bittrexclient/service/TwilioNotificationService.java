package com.vladimir.bittrexclient.service;

import com.vladimir.bittrexclient.config.bittrex.NotificationLimits;
import com.vladimir.bittrexclient.config.twilio.TwilioMessageStatusHandler;
import com.vladimir.bittrexclient.config.twilio.TwilioReceivers;
import com.vladimir.bittrexclient.config.twilio.TwilioClient;
import com.vladimir.bittrexclient.model.bittrex.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class TwilioNotificationService {
    @Autowired
    private TwilioClient twilioClient;
    @Autowired
    private TwilioReceivers twilioReceivers;
    @Autowired
    private TwilioMessageStatusHandler twilioMessageStatusHandler;

    public void sendNotification(List<Balance> actualBalances, NotificationLimits establishedLimits) {
        Map<String, BigDecimal> limits = establishedLimits.getLimits();
        for (Balance actualBalance : actualBalances) {
            for (String currency : limits.keySet()) {
                if (actualBalance.getCurrency().equals(currency)) {
                    if (actualBalance.getBalance().compareTo(establishedLimits.getLimits().get(currency)) < 0) {
                        if (!twilioMessageStatusHandler.getSmsStatusByCurrency(currency)) {
                            sentNotificationToLowLimitBalance(actualBalance);
                        }
                        twilioMessageStatusHandler.setSmsStatusToCurrency(currency, true);
                    } else {
                        twilioMessageStatusHandler.setSmsStatusToCurrency(currency, false);
                    }
                }
            }
        }
    }

    private void sentNotificationToLowLimitBalance(Balance balance) {
        for (String receiver : twilioReceivers.getAllReceivers()) {
            twilioClient.sendMessage(receiver, generateMessage(balance));
        }
    }

    private String generateMessage(Balance balance) {
        return balance.getCurrency() + " balance is " + balance.getBalance() + "\n" +
                "\n" + "Please refill address: " + balance.getCryptoAddress();
    }
}
