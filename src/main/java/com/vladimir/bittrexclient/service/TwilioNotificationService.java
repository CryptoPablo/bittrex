package com.vladimir.bittrexclient.service;

import com.vladimir.bittrexclient.config.bittrex.BittrexBalanceLimits;
import com.vladimir.bittrexclient.config.twilio.TwilioReceivers;
import com.vladimir.bittrexclient.config.twilio.TwilioClient;
import com.vladimir.bittrexclient.model.bittrex.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TwilioNotificationService {
    @Autowired
    private TwilioClient twilioClient;
    @Autowired
    private TwilioReceivers twilioReceivers;
    @Autowired
    private BittrexBalanceLimits bittrexBalanceLimits;

    public void sendNotification(List<Balance> balanceList) {
        for (Balance balance : balanceList) {
            for (String receiver : twilioReceivers.getAllReceivers()) {
                twilioClient.sendMessage(receiver, generateMessage(balance));
            }
        }
    }

    public List<Balance> findLowLimitBalances(List<Balance> actualBalances) {
        List<Balance> lowLimitBalances = new ArrayList<>();
        for (Balance actualBalance : actualBalances) {
            if (balanceLowerThanLimit(actualBalance, bittrexBalanceLimits)) {
                lowLimitBalances.add(actualBalance);
            }
        }
        return lowLimitBalances;
    }

    private static boolean balanceLowerThanLimit(Balance balance, BittrexBalanceLimits establishedLimits) {
        Map<String, BigDecimal> limits = establishedLimits.getLimits();
        for (String currency : limits.keySet()) {
            if (balance.getCurrency().equals(currency) && balance.getBalance().compareTo(limits.get(currency)) < 0) {
                return true;
            }
        }
        return false;
    }

    private String generateMessage(Balance balance) {
        return balance.getCurrency() + " balance is " + balance.getBalance() + "\n" +
                "\n" + "Please refill address: " + balance.getCryptoAddress();
    }
}
