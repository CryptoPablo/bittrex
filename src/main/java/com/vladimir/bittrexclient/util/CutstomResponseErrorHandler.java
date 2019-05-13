package com.vladimir.bittrexclient.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class CutstomResponseErrorHandler implements ResponseErrorHandler {
    private Logger logger = LoggerFactory.getLogger(CutstomResponseErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {

        return (httpResponse
                .getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR || httpResponse
                .getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {

        if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            logger.error("Response error: {} {}", httpResponse.getStatusCode(), httpResponse.getStatusText());
            throw new HttpServerErrorException(httpResponse.getStatusCode());
        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            logger.error("Response error: {} {}", httpResponse.getStatusCode(), httpResponse.getStatusText());
            throw new HttpServerErrorException(httpResponse.getStatusCode());
        }
    }
}