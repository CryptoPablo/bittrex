package com.vladimir.bittrexclient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AppController {
    @Autowired
    private RestTemplate restTemplate;
    private static final String apiKey = "ae490f442eca49509d9f2cf9deaeb05f";
    private static final String apiSecret = "a19050a26d3942aabf91c84c114e4134";

    @RequestMapping("/getBalances")
    public String getBalances(){
        String nonce = ApiKeySigningUtil.createNonce();
        String uri = "https://api.bittrex.com/api/v1.1/account/getbalances?apikey=" + apiKey + "&nonce=" + nonce;
        String sign = ApiKeySigningUtil.createSign(uri, apiSecret);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("apisign", sign);
        HttpEntity entity = new HttpEntity(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }
}
