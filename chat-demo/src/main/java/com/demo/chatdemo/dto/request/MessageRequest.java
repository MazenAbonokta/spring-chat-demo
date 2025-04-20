package com.demo.chatdemo.dto.request;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest  {
    private String content;
    private String receiverId;
    private String senderId;
    private String chatId;
    private String messageType;
}
