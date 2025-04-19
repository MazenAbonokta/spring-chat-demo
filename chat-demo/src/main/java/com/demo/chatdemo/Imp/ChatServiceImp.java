package com.demo.chatdemo.Imp;

import com.demo.chatdemo.entity.Chat;
import com.demo.chatdemo.repository.ChatRepository;
import com.demo.chatdemo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class ChatServiceImp implements ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Override
    public List<Chat> findBySenderIdOrReceiverIdChats(Long senderId, Long receiverId) {
        return chatRepository.findDistinctBySenderIdOrReceiverId(senderId,receiverId);
    }

    @Override
    public List<Chat> findBySenderIdAndReceiverIdChats(Long senderId, Long receiverId) {
        return chatRepository.findDistinctBySenderIdAndReceiverId(senderId,receiverId);
    }
}
