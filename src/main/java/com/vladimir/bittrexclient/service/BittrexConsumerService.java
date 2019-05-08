package com.vladimir.bittrexclient.service;

import com.vladimir.bittrexclient.config.ApiCredentials;
import com.vladimir.bittrexclient.model.ApiResult;
import com.vladimir.bittrexclient.util.ApiKeySigningUtil;
import com.vladimir.bittrexclient.util.CutstomResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ApiConsumerService {
    @Autowired
    private RestTemplate restTemplate;
    private static final String baseUrl = "https://api.bittrex.com/api/v1.1/";

    public ApiResult makeRequest(ApiCredentials apiCredentials, String apiType, String method, @Nullable String parameter, @Nullable String value) {
        String uri = baseUrl + "/" + apiType + "/" + method;
        String nonce = ApiKeySigningUtil.createNonce();

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("nonce", nonce);
        queryParams.add("apikey", apiCredentials.getApiKey());
        if (parameter != null) {
            queryParams.add(parameter, value);
        }

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri).queryParams(queryParams);

        String sign = ApiKeySigningUtil.createSign(builder.toUriString(), apiCredentials.getApiSecret());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("apisign", sign);
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity entity = new HttpEntity(httpHeaders);
        restTemplate.setErrorHandler(new CutstomResponseErrorHandler());
        ResponseEntity<ApiResult> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, ApiResult.class);

        return responseEntity.getBody();
    }
}
