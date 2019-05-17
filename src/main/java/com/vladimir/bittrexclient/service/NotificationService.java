package com.vladimir.bittrexclient.service;

import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    void sendMessage(String to, String message);
}
