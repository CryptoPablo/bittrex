package com.vladimir.bittrexclient.controller;

import com.vladimir.bittrexclient.service.ConsumerService;
import com.vladimir.bittrexclient.config.ApiCredentials;
import com.vladimir.bittrexclient.model.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    @Autowired
    private ConsumerService consumerService;
    @Autowired
    private ApiCredentials apiCredentials;

    @RequestMapping("/open-orders")
    public ApiResult getAllOpenOrders() {
        return consumerService.makeRequest(apiCredentials, "market", "getopenorders", null, null);
    }

    @RequestMapping("/open-orders/{market}")
    public ApiResult getAllOpenOrdersByMarket(@PathVariable(name = "market") String market) {
        return consumerService.makeRequest(apiCredentials, "market", "getopenorders", "market", market);
    }

    @RequestMapping("/balances")
    public ApiResult getAllBalances() {
        return consumerService.makeRequest(apiCredentials, "account", "getbalances", null, null);
    }

    @RequestMapping("/balances/{currency}")
    public ApiResult getBalanceByCurrency(@PathVariable(name = "currency") String currency) {
        return consumerService.makeRequest(apiCredentials, "account", "getbalance", "currency", currency);
    }

    @RequestMapping("/order/{uuid}")
    public ApiResult getOrder(@PathVariable(name = "uuid") String uuid) {
        return consumerService.makeRequest(apiCredentials, "account", "getorder", "uuid", uuid);
    }

    @RequestMapping("/order-history")
    public ApiResult getOrderHistory() {
        return consumerService.makeRequest(apiCredentials, "account", "getorderhistory", null, null);
    }

    @RequestMapping("/order-history/{market}")
    public ApiResult getOrderHistoryByMarket(@PathVariable(name = "market") String market) {
        return consumerService.makeRequest(apiCredentials, "account", "getorderhistory", "market", market);
    }

    @RequestMapping("/withdrawal-history")
    public ApiResult getWithdrawalHistory() {
        return consumerService.makeRequest(apiCredentials, "account", "getwithdrawalhistory", null, null);
    }

    @RequestMapping("/withdrawal-history/{currency}")
    public ApiResult getWithdrawalHistoryByCurrency(@PathVariable(name = "currency") String currency) {
        return consumerService.makeRequest(apiCredentials, "account", "getwithdrawalhistory", "currency", currency);
    }

    @RequestMapping("/deposit-history")
    public ApiResult getDepositHistory() {
        return consumerService.makeRequest(apiCredentials, "account", "getdeposithistory", null, null);
    }

    @RequestMapping("/deposit-history/{currency}")
    public ApiResult getDepositHistoryByCurrency(@PathVariable(name = "currency") String currency) {
        return consumerService.makeRequest(apiCredentials, "account", "getdeposithistory", "currency", currency);
    }
}

