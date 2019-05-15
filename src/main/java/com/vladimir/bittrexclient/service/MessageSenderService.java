package com.vladimir.bittrexclient.service;

import com.vladimir.bittrexclient.config.bittrex.BittrexNotificationLimits;
import com.vladimir.bittrexclient.config.twilio.TwilioMessageHandler;
import com.vladimir.bittrexclient.controller.BittrexRequestController;
import com.vladimir.bittrexclient.model.bittrex.Balance;
import org.springframework.beans.factory.annotation.Autowired;
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
    private TwilioMessageHandler twilioMessageHandler;
    @Autowired
    private BittrexNotificationLimits bittrexNotificationLimits;

    @Scheduled(cron = "${bittrex.request.fixedDelay.in.cron}")
    public void checkBalancesAndSendNotification() {
        List<Balance> actualBalances = bittrexRequestController.getAllBalances().getResult();
        twilioMessageHandler.sendNotification(actualBalances, bittrexNotificationLimits);
    }
}





