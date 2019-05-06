package com.vladimir.bittrexclient.controller;

import com.vladimir.bittrexclient.config.ApiCredentials;
import com.vladimir.bittrexclient.model.common.ApiResult;
import com.vladimir.bittrexclient.util.ApiKeySigningUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
public class AppController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApiCredentials apiCredentials;
    private static final String baseUrl = "https://api.bittrex.com/api/v1.1/";

    @RequestMapping("/open-orders")
    public ApiResult getAllOpenOrders() {
        return getApi(apiCredentials, "market", "getopenorders", null, null);
    }

    @RequestMapping("/open-orders/{market}")
    public ApiResult getAllOpenOrders(@PathVariable(name = "market") String market) {
        return getApi(apiCredentials, "market", "getopenorders", "market", market);
    }

    @RequestMapping("/balances")
    public ApiResult getAllBalances() {
        return getApi(apiCredentials, "account", "getbalances", null, null);
    }

    @RequestMapping("/balances/{currency}")
    public ApiResult getBalanceByCurrency(@PathVariable(name = "currency") String currency) {
        return getApi(apiCredentials, "account", "getbalance", "currency", currency);
    }

    @RequestMapping("/order/{uuid}")
    public ApiResult getOrder(@PathVariable(name = "uuid") String uuid) {
        return getApi(apiCredentials, "account", "getorder", "uuid", uuid);
    }

    @RequestMapping("/order-history")
    public ApiResult getOrdersHistory() {
        return getApi(apiCredentials, "account", "getorderhistory", null, null);
    }

    @RequestMapping("/order-history/{market}")
    public ApiResult getOrdersHistoryByMarket(@PathVariable(name = "market") String market) {
        return getApi(apiCredentials, "account", "getorderhistory", "market", market);
    }

    @RequestMapping("/withdrawal-history")
    public ApiResult getWithdrawalHistory() {
        return getApi(apiCredentials, "account", "getwithdrawalhistory", null, null);
    }

    @RequestMapping("/withdrawal-history/{currency}")
    public ApiResult getWithdrawalHistoryByCurrency(@PathVariable(name = "currency") String currency) {
        return getApi(apiCredentials, "account", "getwithdrawalhistory", "currency", currency);
    }

    @RequestMapping("/deposit-history")
    public ApiResult getDepositHistory() {
        return getApi(apiCredentials, "account", "getdeposithistory", null, null);
    }

    @RequestMapping("/deposit-history/{currency}")
    public ApiResult getDepositHistoryByCurrency(@PathVariable(name = "currency") String currency) {
        return getApi(apiCredentials, "account", "getdeposithistory", "currency", currency);
    }

    private ApiResult getApi(ApiCredentials apiCredentials, String apiType, String method,
                             @Nullable String parameter,
                             @Nullable String value) {
        String uri = baseUrl + "/" + apiType + "/" + method;
        ;
        String nonce = ApiKeySigningUtil.createNonce();

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("nonce", nonce);
        queryParams.add("apikey", apiCredentials.getApiKey());
        queryParams.add(parameter, value);
        if (parameter == null) {
            queryParams.remove(parameter);
        }

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri).queryParams(queryParams);

        String sign = ApiKeySigningUtil.createSign(builder.toUriString(), apiCredentials.getApiSecret());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("apisign", sign);
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity entity = new HttpEntity(httpHeaders);

        ResponseEntity<ApiResult> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
                new ParameterizedTypeReference<ApiResult>() {
                });
        return responseEntity.getBody();
    }
}

