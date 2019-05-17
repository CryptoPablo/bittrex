package com.vladimir.bittrexclient.service;

import com.vladimir.bittrexclient.config.notifications.BalanceNotificationLimits;
import com.vladimir.bittrexclient.config.notifications.NotificationRecipients;
import com.vladimir.bittrexclient.config.twilio.TwilioClient;
import com.vladimir.bittrexclient.config.notifications.BalanceNotificationHandler;
import com.vladimir.bittrexclient.controller.BittrexRequestController;
import com.vladimir.bittrexclient.model.bittrex.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
public class MessageSenderService {
    @Autowired
    private BittrexRequestController bittrexRequestController;
    @Autowired
    private TwilioClient twilioClient;
    @Autowired
    private NotificationRecipients notificationRecipients;
    @Autowired
    private BalanceNotificationLimits balanceNotificationLimits;
    @Autowired
    private BalanceNotificationHandler balanceNotificationHandler;

    @Scheduled(cron = "${bittrex.request.fixedDelay.in.cron}")
    public void checkBalancesAndSendNotification() {
        List<Balance> actualBalances = bittrexRequestController.getAllBalances().getResult();
        balanceNotificationHandler.sendLowLimitBalanceNotification(twilioClient, notificationRecipients.getAllRecipients(), actualBalances, balanceNotificationLimits.getAllLimits());
    }
}





