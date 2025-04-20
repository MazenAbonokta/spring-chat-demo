package com.demo.chatdemo.dto.response;

import com.demo.chatdemo.constant.MessageState;
import com.demo.chatdemo.constant.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    private Long id;
    private String content;
    private String senderId;
    private String receiverId;

    private MessageType messageType;
    private MessageState messageState;
    private LocalDateTime createdAt;
    private byte[] media;

}
