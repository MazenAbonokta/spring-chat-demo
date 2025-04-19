package com.demo.chatdemo.service;

import com.demo.chatdemo.entity.Chat;

import java.util.List;

public interface ChatService {
    List<Chat> findBySenderIdOrReceiverIdChats(Long senderId, Long receiverId);
    List<Chat> findBySenderIdAndReceiverIdChats(Long senderId, Long receiverId);
}
