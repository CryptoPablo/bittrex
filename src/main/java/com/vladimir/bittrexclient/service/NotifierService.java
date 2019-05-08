package com.vladimir.bittrexclient.service;

import org.springframework.stereotype.Service;

@Service
public interface NotifierService {
     void sendNotification();
     String getMessage();
}
