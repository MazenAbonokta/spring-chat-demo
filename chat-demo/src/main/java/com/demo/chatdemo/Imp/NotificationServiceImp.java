package com.demo.chatdemo.Imp;

import com.demo.chatdemo.entity.Notification;
import com.demo.chatdemo.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationServiceImp implements NotificationService {

   private final SimpMessagingTemplate simpMessagingTemplate;

    public NotificationServiceImp(SimpMessagingTemplate simpMessagingTemplates) {
        this.simpMessagingTemplate = simpMessagingTemplates;
    }
@Override
 public    void sendNotification(String userId, Notification notification) {
      log.info("Sending notification to user {} with payload:", userId, notification);
        simpMessagingTemplate.convertAndSendToUser(userId, "/chat"+userId, notification);
    }
}
