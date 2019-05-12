package com.vladimir.bittrexclient.service;

import com.vladimir.bittrexclient.config.bittrex.BittrexBalanceLimits;
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
    private BittrexBalanceLimits bittrexBalanceLimits;
    @Autowired
    private TwilioReceivers twilioReceivers;
    @Autowired
    private TwilioClient twilioClient;

    public void sendNotification(List<Balance> balanceList) {
        for (Balance balance : balanceList) {
            if (balanceLowerThanLimit(balance, bittrexBalanceLimits)) {
                for (String receiver : twilioReceivers.getAllReceivers()) {
                    twilioClient.sendMessage(receiver, generateMessage(balance));
                }
            }
        }
    }

    private static boolean balanceLowerThanLimit(Balance balance, BittrexBalanceLimits limits) {
        Map<String, BigDecimal> limit = limits.getLimits();
        for (String currency : limit.keySet()) {
            if (balance.getCurrency().equals(currency) && balance.getBalance().compareTo(limit.get(currency)) < 0) {
                return true;
            }
        }
        return false;
    }

    private String generateMessage(Balance balance) {
        return balance.getCurrency() + " balance is " + balance.getBalance() + "\n"
                + "\n" + "Please refill address: " + balance.getCryptoAddress();
    }
}
