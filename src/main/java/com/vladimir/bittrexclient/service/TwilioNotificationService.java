package com.vladimir.bittrexclient.service;

import com.twilio.type.Client;
import com.vladimir.bittrexclient.config.bittrex.Limit;
import com.vladimir.bittrexclient.config.bittrex.SolveLimit;
import com.vladimir.bittrexclient.config.twilio.TwilioClient;
import com.vladimir.bittrexclient.model.bittrex.Balance;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TwilioNotificationService {
    private BigDecimal solveLimit = new BigDecimal(10);
    private BigDecimal btcLimit = new BigDecimal(0);
    private BigDecimal ethLimit = new BigDecimal(0);
    private SolveLimit solveLimit2 = new SolveLimit();



    public String checkBalanсe(List<Balance> balances){
        String result = "";
        for (Balance b: balances){
            if (b.getCurrency().equals("SOLVE") && b.getBalance().compareTo(solveLimit) < 0) {
                result = generateMessage(b);
            }
            if (b.getCurrency().equals("BTC") && b.getBalance().compareTo(btcLimit) < 0) {
                result = generateMessage(b);
            }
            if (b.getCurrency().equals("ETH") && b.getBalance().compareTo(ethLimit) < 0) {
                result = generateMessage(b);
            }
        }
        return result;
    }
    //this method should send notification to one objeect
    public void sendNotification(List<Balance> balanceList){
        for (Balance balance: balanceList){
            if (balanceLowerThanLimit(balance, solveLimit2)){
                System.out.println(generateMessage(balance));
            }

        }
    }
    //This should method check collection
    private static boolean balanceLowerThanLimit(Balance balance, Limit limit){
        return balance.getCurrency().equals(limit.getTicker()) && balance.getBalance().compareTo(limit.getLimit()) < 0;
    }

    private String generateMessage(Balance balance){
        return balance.getCurrency() + " balance is " + balance.getBalance() + "\n"
                + "Please refill address " + balance.getCryptoAddress();
    }
}
