package com.demo.chatdemo.mapper;

import com.demo.chatdemo.dto.response.ChatResponse;
import com.demo.chatdemo.entity.Chat;
import org.springframework.stereotype.Component;

@Component
public class ChatMapper {

    public ChatResponse toChatResponse(Chat chat,String userId){
        return ChatResponse.builder()
                .id(Long.getLong(chat.getId()))
                .name(chat.getReceiver().getFirstName()+" "+chat.getReceiver().getLastName())
                .lastMessage(chat.getLastMessage())
                .receiverId(chat.getReceiver().id.toString())
                .senderId(userId)
                .unreadCount(chat.getUnreadMessage(userId))
                .isReceiverOnline(chat.getReceiver().isOnline())
                .lastMessageTime(chat.getLastMessageTime().toString())
                .build();
    }

}
