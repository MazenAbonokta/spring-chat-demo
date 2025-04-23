package com.demo.chatdemo.Imp;

import com.demo.chatdemo.dto.response.ChatResponse;
import com.demo.chatdemo.entity.Chat;
import com.demo.chatdemo.entity.User;
import com.demo.chatdemo.exception.ResourceNotFoundException;
import com.demo.chatdemo.mapper.ChatMapper;
import com.demo.chatdemo.repository.ChatRepository;
import com.demo.chatdemo.repository.UserRepository;
import com.demo.chatdemo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public  class ChatServiceImp implements ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    UserRepository  userRepository;
    @Autowired
    ChatMapper chatMapper;
    @Override
    public List<Chat> findBySenderIdOrReceiverIdChats(Long senderId, Long receiverId) {
        return chatRepository.findDistinctBySenderIdOrReceiverId(senderId,receiverId);
    }

    @Override
    public List<Chat> findBySenderIdAndReceiverIdChats(Long senderId, Long receiverId) {
        return null;// chatRepository.findDistinctBySenderIdAndReceiverId(senderId,receiverId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatResponse> getChatsByReceiverId(Authentication currentUser) {
        String userId=currentUser.getName();
        List<Chat> chats=chatRepository.findBySenderId(Long.parseLong(userId));
        List<ChatResponse> chatResponses= chats.stream().map(chat->chatMapper.toChatResponse(chat,userId)).toList();
      return chatResponses;

    }

    @Override
    public String createChat(Long senderId, Long receiverId) {
        Optional<Chat> existingChat= chatRepository.findDistinctBySenderIdAndReceiverId(senderId,receiverId);
        if(existingChat.isPresent())
        {
            return existingChat.get().getId().toString();
        }

        User user=userRepository.findById(senderId).orElseThrow(()->new ResourceNotFoundException("User not found with id :"+senderId));
        User recipient=userRepository.findById(receiverId).orElseThrow(()->new ResourceNotFoundException("User not found with id :"+receiverId));
        Chat chat=new Chat();
        chat.setSender(user);
        chat.setReceiver(recipient);
        chatRepository.save(chat);
        return chat.getId().toString();
    }
}
