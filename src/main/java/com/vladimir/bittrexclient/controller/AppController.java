package com.vladimir.bittrexclient.controller;
import com.vladimir.bittrexclient.model.ApiResult;
import com.vladimir.bittrexclient.model.accountapi.Balance;
import com.vladimir.bittrexclient.model.Ticker;
import com.vladimir.bittrexclient.model.accountapi.Order;
import com.vladimir.bittrexclient.model.accountapi.OrderHistoryEntry;
import com.vladimir.bittrexclient.util.ApiKeySigningUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class AppController {
    @Autowired
    private RestTemplate restTemplate;

    private static final String apiKey = "ae490f442eca49509d9f2cf9deaeb05f";
    private static final String apiSecret = "a19050a26d3942aabf91c84c114e4134";
    private static final String baseUrl = "https://api.bittrex.com/api/v1.1/";

    @RequestMapping("/balances")
    public ApiResult<List<Balance>> getBalances(){
        String nonce = ApiKeySigningUtil.createNonce();
        String uri = baseUrl + "account/getbalances?apikey=" + apiKey + "&nonce=" + nonce;
        String sign = ApiKeySigningUtil.createSign(uri, apiSecret);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("apisign", sign);
        HttpEntity entity = new HttpEntity(httpHeaders);
        ResponseEntity<ApiResult<List<Balance>>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity,
                new ParameterizedTypeReference<ApiResult<List<Balance>>>(){});
        return responseEntity.getBody();
    }

    @RequestMapping("/balance/{currency}")
    public ApiResult getBalance(@PathVariable (name = "currency") String currency){
        String nonce = ApiKeySigningUtil.createNonce();
        String uri = baseUrl + "account/getbalance?apikey=" + apiKey + "&nonce=" + nonce+"&currency="+currency;
        String sign = ApiKeySigningUtil.createSign(uri, apiSecret);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("apisign", sign);
        HttpEntity entity = new HttpEntity(httpHeaders);
        ResponseEntity<ApiResult> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, ApiResult.class);
        return responseEntity.getBody();
    }

    @RequestMapping("/order/{uuid}")
    public ApiResult<List<Order>> getOrder(@PathVariable (name = "uuid") String uuid){
        String nonce = ApiKeySigningUtil.createNonce();
        String uri = baseUrl + "account/getorder?apikey=" + apiKey + "&nonce=" + nonce + "&uuid=" + uuid;
        String sign = ApiKeySigningUtil.createSign(uri, apiSecret);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("apisign", sign);
        HttpEntity entity = new HttpEntity(httpHeaders);
        ResponseEntity<ApiResult<List<Order>>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity,
                new ParameterizedTypeReference<ApiResult<List<Order>>>(){});

        return responseEntity.getBody();
    }

    @RequestMapping("/order-history")
    public ApiResult<List<OrderHistoryEntry>> getOrdersHistory(){
        String nonce = ApiKeySigningUtil.createNonce();
        String uri = baseUrl + "account/getorderhistory?apikey=" + apiKey + "&nonce=" + nonce;
        String sign = ApiKeySigningUtil.createSign(uri, apiSecret);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("apisign", sign);
        HttpEntity entity = new HttpEntity(httpHeaders);
        ResponseEntity<ApiResult<List<OrderHistoryEntry>>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity,
                new ParameterizedTypeReference<ApiResult<List<OrderHistoryEntry>>>(){});
        System.out.println(responseEntity.getBody().getResult());
        return responseEntity.getBody();
    }

    @RequestMapping("/test")
    public ApiResult<Ticker> test(){
        String uri = "https://api.bittrex.com/api/v1.1/public/getticker?market=BTC-LTC";
        ApiResult<Ticker> currencyResponseEntity = restTemplate.getForObject(uri, ApiResult.class);
        return currencyResponseEntity;

    }
}
