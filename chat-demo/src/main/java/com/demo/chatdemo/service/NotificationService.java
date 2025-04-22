package com.demo.chatdemo.service;

import com.demo.chatdemo.entity.Notification;

public interface NotificationService {
    void sendNotification(String userId, Notification notification);
}
