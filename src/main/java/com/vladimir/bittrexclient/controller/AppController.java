package com.vladimir.bittrexclient.controller;
import com.vladimir.bittrexclient.config.ApiCredentials;
import com.vladimir.bittrexclient.util.ApiKeySigningUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.client.RestTemplate;

@Controller
public class AppController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApiCredentials apiCredentials;
    private static final String baseUrl = "https://api.bittrex.com/api/v1.1/";

    @RequestMapping("/")
    public String home(){
        return "index";
    }

    @RequestMapping("/open-orders")
    public String getAllOpenOrders(){
        String nonce = ApiKeySigningUtil.createNonce();
        String uri = baseUrl + "market/getopenorders?apikey=" + apiCredentials.getApiKey() + "&nonce=" + nonce;
        String sign = ApiKeySigningUtil.createSign(uri, apiCredentials.getApiSecret());
        HttpEntity entity = setHeaders(sign);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }

    @RequestMapping("/open-orders/{market}")
    public String getAllOpenOrders(@PathVariable (name = "market") String market){
        String nonce = ApiKeySigningUtil.createNonce();
        String uri = baseUrl + "market/getopenorders?apikey=" + apiCredentials.getApiKey() + "&nonce=" + nonce + "&market="+market;
        String sign = ApiKeySigningUtil.createSign(uri, apiCredentials.getApiSecret());
        HttpEntity entity = setHeaders(sign);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }

    @RequestMapping("/balances")
    public String getAllBalances(){
        String nonce = ApiKeySigningUtil.createNonce();
        String uri = baseUrl + "account/getbalances?apikey=" + apiCredentials.getApiKey() + "&nonce=" + nonce;
        String sign = ApiKeySigningUtil.createSign(uri, apiCredentials.getApiSecret());
        HttpEntity entity = setHeaders(sign);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }

    @RequestMapping("/balances/{currency}")
    public String getBalanceByCurrency(@PathVariable (name = "currency") String currency){
        String nonce = ApiKeySigningUtil.createNonce();
        String uri = baseUrl + "account/getbalance?apikey=" + apiCredentials.getApiKey() + "&nonce=" + nonce+"&currency="+currency;
        String sign = ApiKeySigningUtil.createSign(uri, apiCredentials.getApiSecret());
        HttpEntity entity = setHeaders(sign);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }

    @RequestMapping("/order/{uuid}")
    public String getOrder(@PathVariable (name = "uuid") String uuid){
        String nonce = ApiKeySigningUtil.createNonce();
        String uri = baseUrl + "account/getorder?apikey=" + apiCredentials.getApiKey()+ "&nonce=" + nonce + "&uuid=" + uuid;
        String sign = ApiKeySigningUtil.createSign(uri, apiCredentials.getApiSecret());
        HttpEntity entity = setHeaders(sign);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }

    @RequestMapping("/order-history")
    public String getOrdersHistory(){
        String nonce = ApiKeySigningUtil.createNonce();
        String uri = baseUrl + "account/getorderhistory?apikey=" + apiCredentials.getApiKey() + "&nonce=" + nonce;
        String sign = ApiKeySigningUtil.createSign(uri, apiCredentials.getApiSecret());
        HttpEntity entity = setHeaders(sign);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }

    @RequestMapping("/order-history/{market}")
    public String getOrdersHistoryByMarket(@PathVariable (name = "market") String market){
        String nonce = ApiKeySigningUtil.createNonce();
        String uri = baseUrl + "account/getorderhistory?apikey=" + apiCredentials.getApiKey() + "&nonce=" + nonce + "&market=" + market;
        String sign = ApiKeySigningUtil.createSign(uri, apiCredentials.getApiSecret());
        HttpEntity entity = setHeaders(sign);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }

    @RequestMapping("/withdrawal-history")
    public String getWithdrawalHistory(){
        String nonce = ApiKeySigningUtil.createNonce();
        String uri = baseUrl + "account/getwithdrawalhistory?apikey=" + apiCredentials.getApiKey() + "&nonce=" + nonce;
        String sign = ApiKeySigningUtil.createSign(uri, apiCredentials.getApiSecret());
        HttpEntity entity = setHeaders(sign);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }

    @RequestMapping("/withdrawal-history/{currency}")
    public String getWithdrawalHistoryByCurrency(@PathVariable (name = "currency") String currency){
        String nonce = ApiKeySigningUtil.createNonce();
        String uri = baseUrl + "account/getwithdrawalhistory?apikey=" + apiCredentials.getApiKey() + "&nonce=" + nonce + "&currency=" + currency;
        String sign = ApiKeySigningUtil.createSign(uri, apiCredentials.getApiSecret());
        HttpEntity entity = setHeaders(sign);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }

    @RequestMapping("/deposit-history")
    public String getDepositHistory(){
        String nonce = ApiKeySigningUtil.createNonce();
        String uri = baseUrl + "account/getdeposithistory?apikey=" + apiCredentials.getApiKey() + "&nonce=" + nonce;
        String sign = ApiKeySigningUtil.createSign(uri, apiCredentials.getApiSecret());
        HttpEntity entity = setHeaders(sign);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }

    @RequestMapping("/deposit-history/{currency}")
    public String getDepositHistoryByCurrency(@PathVariable(name = "currency") String currency){
        String nonce = ApiKeySigningUtil.createNonce();
        String uri = baseUrl + "account/getdeposithistory?apikey=" + apiCredentials.getApiKey() + "&nonce=" + nonce + "&currency=" + currency;
        String sign = ApiKeySigningUtil.createSign(uri, apiCredentials.getApiSecret());
        HttpEntity entity = setHeaders(sign);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    private static HttpEntity setHeaders(String sign){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("apisign", sign);
        return new HttpEntity(httpHeaders);
    }
}
