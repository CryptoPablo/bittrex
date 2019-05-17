package com.vladimir.bittrexclient.config.twilio;

import com.vladimir.bittrexclient.config.bittrex.BalanceNotificationLimits;
import com.vladimir.bittrexclient.model.bittrex.Balance;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BalanceNotificationHandler {
    public BalanceNotificationHandler(BalanceNotificationLimits notificationLimits) {
        generateBalanceNotificationStatuses(notificationLimits.getAllLimits());
    }
    private static Map<String, Boolean> notificationStatuses = new HashMap<>();

    public void sendNotificationToLowLimitBalances(NotificationService notificationService, List<String> notificationRecipients, List<Balance> balances, Map<String, BigDecimal> balanceNotificationLimits) {
        for (Balance balance : balances) {
            for (String currency : balanceNotificationLimits.keySet()) {
                if (balance.getCurrency().equals(currency)) {
                    if (balance.getBalance().compareTo(balanceNotificationLimits.get(currency)) < 0) {
                        if (!getNotificationStatusByElement(currency)) {
                            sendNotificationAboutBalance(notificationService, balance, notificationRecipients);
                        }
                        setNotificationStatusToElement(currency, true);
                    } else {
                        setNotificationStatusToElement(currency, false);
                    }
                }
            }
        }

    }

    private void sendNotificationAboutBalance(NotificationService notificationService, Balance balance, List<String> notificationRecipients) {
        for (String recipient : notificationRecipients) {
            notificationService.sendMessage(recipient, generateMessageAboutLowBalance(balance));
        }
    }

    private String generateMessageAboutLowBalance(Balance lowBalance) {
        return lowBalance.getCurrency() + " balance is " + lowBalance.getBalance() + "\n" +
                "\n" + "Please refill address: " + lowBalance.getCryptoAddress();
    }

    private boolean getNotificationStatusByElement(String element) {
        return notificationStatuses.get(element);

    }

    private void setNotificationStatusToElement(String element, boolean notificationSent) {
        notificationStatuses.put(element, notificationSent);
    }

    private static void generateBalanceNotificationStatuses(Map<String, BigDecimal> balanceNotificationLimits) {
        for (String element : balanceNotificationLimits.keySet()) {
            notificationStatuses.put(element, false);
        }
    }
}
