package com.vladimir.bittrexclient.config.notifications;

import com.vladimir.bittrexclient.model.bittrex.Balance;
import com.vladimir.bittrexclient.service.NotificationService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BalanceNotificationHandler {
    public BalanceNotificationHandler(BalanceNotificationLimits notificationLimits) {
        initializeBalanceNotificationStatuses(notificationLimits.getAllLimits());
    }

    private static Map<String, Boolean> notificationStatuses = new HashMap<>();

    public void sendLowLimitBalanceNotification(NotificationService notificationService, List<String> notificationRecipients, List<Balance> balances, Map<String, BigDecimal> balanceNotificationLimits) {
        for (Balance balance : balances) {
            for (String currency : balanceNotificationLimits.keySet()) {
                if (balance.getCurrency().equals(currency)) {
                    if (balance.getBalance().compareTo(balanceNotificationLimits.get(currency)) < 0) {
                        if (!getNotificationStatusByElement(currency)) {
                            sendBalanceNotification(notificationService, balance, notificationRecipients);
                        }
                        setNotificationStatusToElement(currency, true);
                    } else {
                        setNotificationStatusToElement(currency, false);
                    }
                }
            }
        }

    }

    private void sendBalanceNotification(NotificationService notificationService, Balance balance, List<String> notificationRecipients) {
        for (String recipient : notificationRecipients) {
            notificationService.sendMessage(recipient, generateLowBalanceMessage(balance));
        }
    }

    private String generateLowBalanceMessage(Balance lowBalance) {
        return lowBalance.getCurrency() + " balance is " + lowBalance.getBalance() + "\n" +
                "\n" + "Please refill address: " + lowBalance.getCryptoAddress();
    }

    private boolean getNotificationStatusByElement(String element) {
        return notificationStatuses.get(element);

    }

    private void setNotificationStatusToElement(String element, boolean notificationSent) {
        notificationStatuses.put(element, notificationSent);
    }

    private static void initializeBalanceNotificationStatuses(Map<String, BigDecimal> balanceNotificationLimits) {
        for (String element : balanceNotificationLimits.keySet()) {
            notificationStatuses.put(element, false);
        }
    }
}
