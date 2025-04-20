package com.demo.chatdemo.service;

import com.demo.chatdemo.dto.ChatResponse;
import com.demo.chatdemo.entity.Chat;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ChatService {
    List<Chat> findBySenderIdOrReceiverIdChats(Long senderId, Long receiverId);
    List<Chat> findBySenderIdAndReceiverIdChats(Long senderId, Long receiverId);
    List<ChatResponse> getChatsByReceiverId(Authentication currentUser);

    String createChat(Long senderId, Long receiverId);
}
