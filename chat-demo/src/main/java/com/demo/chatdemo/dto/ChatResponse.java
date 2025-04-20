package com.demo.chatdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponse {
    private Long id;
    private String name;
    private long unreadCount;
    private String lastMessage;
    private String lastMessageTime;
    private String senderId;
    private String receiverId;
    private boolean isReceiverOnline;

}
