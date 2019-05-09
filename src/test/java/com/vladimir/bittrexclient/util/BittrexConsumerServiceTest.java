package com.vladimir.bittrexclient.util;

import com.vladimir.bittrexclient.service.BittrexConsumerService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class BittrexConsumerServiceTest {
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    BittrexConsumerService bittrexConsumerService = new BittrexConsumerService();

}
