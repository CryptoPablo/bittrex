package com.vladimir.bittrexclient.service;

import com.vladimir.bittrexclient.config.bittrex.NotificationLimits;
import com.vladimir.bittrexclient.controller.BittrexRequestController;
import com.vladimir.bittrexclient.model.bittrex.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationSenderService {
    @Autowired
    private BittrexRequestController bittrexRequestController;
    @Autowired
    private TwilioNotificationService twilioNotificationService;
    private List<Balance> actualBalances;
    private List<Balance> lowLimitBalances;
    @Autowired
    private NotificationLimits notificationLimits;
    private boolean smsSent = false;
    private Map<Balance, Boolean> secondMap = new HashMap<>();


//    public void checkBittrexBalances() {
//        actualBalances = bittrexRequestController.getTest().getResult();
//        if (twilioNotificationService.balanceLowerThanLimit(actualBalances, bittrexBalanceLimits)) {
//            lowLimitBalances = twilioNotificationService.findLowLimitBalances(actualBalances, bittrexBalanceLimits);
//            if (!smsSent) {
//                twilioNotificationService.sendNotification(lowLimitBalances);
//                smsSent = true;
//            }
//        } else {
//            smsSent = false;
//        }
//        System.out.println(smsSent);
//    }
    @EventListener(ContextRefreshedEvent.class)
    public void before(){
        notificationLimits.generateMap();
    }
    @Scheduled(cron = "${bittrex.request.fixedDelay.in.cron}")
    public void test() {
        actualBalances = bittrexRequestController.getTest().getResult();
        twilioNotificationService.balanceLowerThanLimit(actualBalances, notificationLimits);

    }
}





