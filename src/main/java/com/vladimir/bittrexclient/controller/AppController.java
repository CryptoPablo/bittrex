package com.vladimir.bittrexclient.controller;

import com.vladimir.bittrexclient.model.BittrexResult;
import com.vladimir.bittrexclient.service.BittrexConsumerService;
import com.vladimir.bittrexclient.config.BittrexApiCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    @Autowired
    private BittrexConsumerService bittrexConsumerService;
    @Autowired
    private BittrexApiCredentials bittrexApiCredentials;

    @RequestMapping("/open-orders")
    public BittrexResult getAllOpenOrders() {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "market", "getopenorders", null, null);
    }

    @RequestMapping("/open-orders/{market}")
    public BittrexResult getAllOpenOrdersByMarket(@PathVariable(name = "market") String market) {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "market", "getopenorders", "market", market);
    }

    @RequestMapping("/balances")
    public BittrexResult getAllBalances() {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "account", "getbalances", null, null);
    }

    @RequestMapping("/balances/{currency}")
    public BittrexResult getBalanceByCurrency(@PathVariable(name = "currency") String currency) {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "account", "getbalance", "currency", currency);
    }

    @RequestMapping("/order/{uuid}")
    public BittrexResult getOrder(@PathVariable(name = "uuid") String uuid) {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "account", "getorder", "uuid", uuid);
    }

    @RequestMapping("/order-history")
    public BittrexResult getOrderHistory() {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "account", "getorderhistory", null, null);
    }

    @RequestMapping("/order-history/{market}")
    public BittrexResult getOrderHistoryByMarket(@PathVariable(name = "market") String market) {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "account", "getorderhistory", "market", market);
    }

    @RequestMapping("/withdrawal-history")
    public BittrexResult getWithdrawalHistory() {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "account", "getwithdrawalhistory", null, null);
    }

    @RequestMapping("/withdrawal-history/{currency}")
    public BittrexResult getWithdrawalHistoryByCurrency(@PathVariable(name = "currency") String currency) {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "account", "getwithdrawalhistory", "currency", currency);
    }

    @RequestMapping("/deposit-history")
    public BittrexResult getDepositHistory() {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "account", "getdeposithistory", null, null);
    }

    @RequestMapping("/deposit-history/{currency}")
    public BittrexResult getDepositHistoryByCurrency(@PathVariable(name = "currency") String currency) {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "account", "getdeposithistory", "currency", currency);
    }
}

