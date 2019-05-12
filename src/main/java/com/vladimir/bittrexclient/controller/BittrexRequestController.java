package com.vladimir.bittrexclient.controller;

import com.google.common.reflect.TypeToken;
import com.vladimir.bittrexclient.model.bittrex.*;
import com.vladimir.bittrexclient.model.BittrexResult;
import com.vladimir.bittrexclient.service.BittrexConsumerService;
import com.vladimir.bittrexclient.config.bittrex.BittrexApiCredentials;
import com.vladimir.bittrexclient.service.TwilioNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class BittrexRequestController {
    @Autowired
    private BittrexConsumerService bittrexConsumerService;
    @Autowired
    private BittrexApiCredentials bittrexApiCredentials;


    @RequestMapping("/balances")
    public BittrexResult<List<Balance>> getAllBalances() {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "account", "getbalances", null, null, new TypeToken<List<Balance>>() {
        });
    }

    @RequestMapping("/balances/{currency}")
    public BittrexResult<Balance> getBalanceByCurrency(@PathVariable(name = "currency") String currency) {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "account", "getbalance", "currency", currency, new TypeToken<Balance>() {
        });
    }

    @RequestMapping("/order/{uuid}")
    public BittrexResult<Order> getOrder(@PathVariable(name = "uuid") String uuid) {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "account", "getorder", "uuid", uuid, new TypeToken<Order>() {
        });
    }

    @RequestMapping("/open-orders")
    public BittrexResult<List<Order>> getAllOpenOrders() {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "market", "getopenorders", null, null, new TypeToken<List<Order>>() {
        });
    }

    @RequestMapping("/open-orders/{market}")
    public BittrexResult<List<Order>> getAllOpenOrdersByMarket(@PathVariable(name = "market") String market) {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "market", "getopenorders", "market", market, new TypeToken<List<Order>>() {
        });
    }

    @RequestMapping("/order-history")
    public BittrexResult<List<OrderHistoryEntry>> getOrderHistory() {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "account", "getorderhistory", null, null, new TypeToken<List<OrderHistoryEntry>>() {
        });
    }

    @RequestMapping("/order-history/{market}")
    public BittrexResult<List<OrderHistoryEntry>> getOrderHistoryByMarket(@PathVariable(name = "market") String market) {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "account", "getorderhistory", "market", market, new TypeToken<List<OrderHistoryEntry>>() {
        });
    }

    @RequestMapping("/withdrawal-history")
    public BittrexResult<List<WithdrawalHistoryEntry>> getWithdrawalHistory() throws IOException {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "account", "getwithdrawalhistory", null, null, new TypeToken<List<WithdrawalHistoryEntry>>() {
        });
    }

    @RequestMapping("/withdrawal-history/{currency}")
    public BittrexResult<List<WithdrawalHistoryEntry>> getWithdrawalHistoryByCurrency(@PathVariable(name = "currency") String currency) {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "account", "getwithdrawalhistory", "currency", currency, new TypeToken<List<WithdrawalHistoryEntry>>() {
        });
    }

    @RequestMapping("/deposit-history")
    public BittrexResult<List<DepositHistoryEntry>> getDepositHistory() {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "account", "getdeposithistory", null, null, new TypeToken<List<DepositHistoryEntry>>() {
        });
    }

    @RequestMapping("/deposit-history/{currency}")
    public BittrexResult<List<DepositHistoryEntry>> getDepositHistoryByCurrency(@PathVariable(name = "currency") String currency) {
        return bittrexConsumerService.makeRequest(bittrexApiCredentials, "account", "getdeposithistory", "currency", currency, new TypeToken<List<DepositHistoryEntry>>() {
        });
    }
}

