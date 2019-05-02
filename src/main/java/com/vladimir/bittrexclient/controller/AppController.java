package com.vladimir.bittrexclient;
import com.vladimir.bittrexclient.model.ApiResult;
import com.vladimir.bittrexclient.model.Balance;
import com.vladimir.bittrexclient.model.Currency;
import com.vladimir.bittrexclient.model.Ticker;
import com.vladimir.bittrexclient.util.ApiKeySigningUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@RestController
public class AppController {
    @Autowired
    private RestTemplate restTemplate;
    private static final String apiKey = "ae490f442eca49509d9f2cf9deaeb05f";
    private static final String apiSecret = "a19050a26d3942aabf91c84c114e4134";

    @RequestMapping("/getBalances")
    public ApiResult<List<Balance>> getBalances(){
        String nonce = ApiKeySigningUtil.createNonce();
        String uri = "https://api.bittrex.com/api/v1.1/account/getbalances?apikey=" + apiKey + "&nonce=" + nonce;
        String sign = ApiKeySigningUtil.createSign(uri, apiSecret);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("apisign", sign);
        HttpEntity entity = new HttpEntity(httpHeaders);
        ResponseEntity<ApiResult<List<Balance>>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity,
                new ParameterizedTypeReference<ApiResult<List<Balance>>>(){});
        ApiResult<List<Balance>> balanceApiResult = responseEntity.getBody();
        System.out.println(balanceApiResult.getResult());
        return balanceApiResult;
    }

    @RequestMapping("/test")
    public ApiResult<Ticker> test(){
        String uri = "https://api.bittrex.com/api/v1.1/public/getticker?market=BTC-LTC";
        ApiResult<Ticker> currencyResponseEntity = restTemplate.getForObject(uri, ApiResult.class);
        return currencyResponseEntity;

    }
}
