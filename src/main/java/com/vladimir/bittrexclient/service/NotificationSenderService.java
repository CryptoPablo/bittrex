package com.vladimir.bittrexclient.service;

import com.vladimir.bittrexclient.controller.BittrexRequestController;
import com.vladimir.bittrexclient.model.bittrex.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationSenderService {
    @Autowired
    private BittrexRequestController bittrexRequestController;
    @Autowired
    private TwilioNotificationService twilioNotificationService;
    private List<Balance> actualBalances;
    private List<Balance> lowLimitBalances;
    private boolean addressRefilled = true;

    @Scheduled(cron = "${bittrex.request.fixedDelay.in.cron}")
    public void checkBittrexBalances() {
        actualBalances = bittrexRequestController.getAllBalances().getResult();
        if (addressRefilled) {
            lowLimitBalances = twilioNotificationService.findLowLimitBalances(actualBalances);
            if (!lowLimitBalances.isEmpty()) {
                twilioNotificationService.sendNotification(lowLimitBalances);
            }
            addressRefilled = isAddressRefilled(actualBalances, lowLimitBalances);
        }
    }

    private boolean isAddressRefilled(List<Balance> actualBalances, List<Balance> lowLimitBalances) {
        boolean refilled = false;
        for (Balance actualBalance : actualBalances) {
            for (Balance lowLimitBalance : lowLimitBalances) {
                if (actualBalance.getBalance().compareTo(lowLimitBalance.getBalance()) > 0) {
                    refilled = true;
                }
            }
        }
        return refilled;
    }
}
