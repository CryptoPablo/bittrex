package com.vladimir.bittrexclient.service;

import com.vladimir.bittrexclient.controller.BittrexRequestController;
import com.vladimir.bittrexclient.model.bittrex.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationSender {
    @Autowired
    private BittrexRequestController bittrexRequestController;
    @Autowired
    private TwilioNotificationService twilioNotificationService;
    private List<Balance> balances;

    @Scheduled(cron = "${bittrex.request.fixedDelay.in.cron}")
    public void checkBittrexBalances() {
        balances = bittrexRequestController.getAllBalances().getResult();
    }

    @Scheduled(cron = "${bittrex.notification.fixedDelay.in.cron}")
    public void sendNotification(){
        twilioNotificationService.sendNotification(balances);
    }
}
