package com.vladimir.bittrexclient.config.bittrex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.math.BigDecimal;
import java.util.Map;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties("bittrex.balance.limits")
public class BittrexBalanceLimits {
    @Value("#{${bittrex.balance.limits}}")
    private Map<String, BigDecimal> limit;

    public Map<String, BigDecimal> getCurrentLimits() {
        return limit;
    }
}
