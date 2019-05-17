package com.vladimir.bittrexclient.config.notifications;

import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    void sendMessage(String to, String message);
}
