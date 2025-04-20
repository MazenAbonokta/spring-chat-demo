package com.demo.chatdemo.mapper;

import com.demo.chatdemo.dto.ChatResponse;
import com.demo.chatdemo.entity.Chat;
import org.springframework.stereotype.Component;

@Component
public class ChatMapper {

    public ChatResponse toChatResponse(Chat chat,String userId){
        return ChatResponse.builder()
                .id(Long.getLong(chat.getId()))
                .name(chat.getRecipient().getFirstName()+" "+chat.getRecipient().getLastName())
                .lastMessage(chat.getLastMessage())
                .receiverId(chat.getRecipient().id.toString())
                .senderId(userId)
                .unreadCount(chat.getUnreadMessage(userId))
                .isReceiverOnline(chat.getRecipient().isOnline())
                .build();
    }

}
