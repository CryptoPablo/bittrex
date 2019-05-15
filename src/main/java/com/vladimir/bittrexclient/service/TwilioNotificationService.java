package com.vladimir.bittrexclient.service;

import com.vladimir.bittrexclient.config.bittrex.NotificationLimits;
import com.vladimir.bittrexclient.config.twilio.TwilioReceivers;
import com.vladimir.bittrexclient.config.twilio.TwilioClient;
import com.vladimir.bittrexclient.model.bittrex.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TwilioNotificationService {
    @Autowired
    private TwilioClient twilioClient;
    @Autowired
    private TwilioReceivers twilioReceivers;
    @Autowired
    private NotificationLimits notificationLimits;

    public void sendNotification(List<Balance> balanceList) {
        for (Balance balance : balanceList) {
            for (String receiver : twilioReceivers.getAllReceivers()) {
                twilioClient.sendMessage(receiver, generateMessage(balance));
            }
        }
    }

    public void sentNotificationToOneEntity(Balance balance) {
        for (String receiver : twilioReceivers.getAllReceivers()) {
            twilioClient.sendMessage(receiver, generateMessage(balance));
        }
    }

    public List<Balance> findLowLimitBalances(List<Balance> actualBalances, NotificationLimits notificationLimits) {
        List<Balance> lowLimitBalances = new ArrayList<>();
        for (Balance actualBalance : actualBalances) {
            if (balanceLowerThanLimit(actualBalance, notificationLimits)) {
                lowLimitBalances.add(actualBalance);
            }
        }
        return lowLimitBalances;
    }


    public Map<Balance, Boolean> findlowLimitBalancesMap(List<Balance> lowLimitBalance, Boolean smsSent) {
        Map<Balance, Boolean> map = new HashMap<>();
        for (Balance lowLimit : lowLimitBalance) {
            map.put(lowLimit, smsSent);
        }
        return map;
    }


    public void balanceLowerThanLimit(List<Balance> actualBalances, NotificationLimits establishedLimits) {
        Map<String, BigDecimal> limits = establishedLimits.getLimits();
        for (Balance actualBalance : actualBalances) {
            for (String currency : limits.keySet()) {
                if (actualBalance.getCurrency().equals(currency)) {
                    if (actualBalance.getBalance().compareTo(establishedLimits.getLimits().get(currency)) < 0){
                        if(!notificationLimits.getStatusElement(currency)){
                            System.out.println("sms sent");
                            sentNotificationToOneEntity(actualBalance);
                        }
                        notificationLimits.setStatusElement(currency, true);
                    } else {
                        notificationLimits.setStatusElement(currency, false);
                    }
                    System.out.println(currency + " " + notificationLimits.getStatusElement(currency));

                }
            }
        }
    }



    public boolean balanceLowerThanLimit(Balance balance, NotificationLimits establishedLimits) {
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
