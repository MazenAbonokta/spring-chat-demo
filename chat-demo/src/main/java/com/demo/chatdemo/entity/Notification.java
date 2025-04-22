package com.demo.chatdemo.entity;

import com.demo.chatdemo.constant.MessageType;
import com.demo.chatdemo.constant.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private String chatId;
    private String senderId;
    private String receiverId;
    private String content;
    private String chatName;
    private MessageType messageType;
    private NotificationType type;
    private byte[] media;
}
