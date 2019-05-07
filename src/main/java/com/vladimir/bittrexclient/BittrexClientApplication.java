package com.vladimir.bittrexclient;

import com.vladimir.bittrexclient.config.ApiCredentials;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApiCredentials.class)
public class BittrexClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(BittrexClientApplication.class, args);
    }

}
