package com.vladimir.bittrexclient;

import com.vladimir.bittrexclient.config.bittrex.BittrexApiCredentials;
import com.vladimir.bittrexclient.config.twilio.TwilioApiCredentials;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties({BittrexApiCredentials.class, TwilioApiCredentials.class})

public class BittrexClientApplication {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(BittrexClientApplication.class, args);
    }
}
