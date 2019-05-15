package com.vladimir.bittrexclient.service;

import com.vladimir.bittrexclient.config.bittrex.NotificationLimits;
import com.vladimir.bittrexclient.config.twilio.TwilioMessageStatusHandler;
import com.vladimir.bittrexclient.controller.BittrexRequestController;
import com.vladimir.bittrexclient.model.bittrex.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
public class NotificationSenderService {
    @Autowired
    private BittrexRequestController bittrexRequestController;
    @Autowired
    private TwilioNotificationService twilioNotificationService;
    @Autowired
    private NotificationLimits notificationLimits;

    @Scheduled(cron = "${bittrex.request.fixedDelay.in.cron}")
    public void checkBalancesAndSendNotification() {
        List<Balance> actualBalances = bittrexRequestController.getTest().getResult();
        twilioNotificationService.sendNotification(actualBalances, notificationLimits);
    }
}





