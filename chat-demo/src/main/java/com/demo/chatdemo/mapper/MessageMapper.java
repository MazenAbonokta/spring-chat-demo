package com.demo.chatdemo.mapper;

import com.demo.chatdemo.constant.MessageState;
import com.demo.chatdemo.constant.MessageType;
import com.demo.chatdemo.dto.request.MessageRequest;
import com.demo.chatdemo.dto.response.MessageResponse;
import com.demo.chatdemo.entity.Chat;
import com.demo.chatdemo.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public Message fromMessageRequestToMessage(MessageRequest request, Chat chat){

        return Message.builder()
                .type(MessageType.valueOf( request.getMessageType()))
                .content(request.getContent())
                .senderId(request.getSenderId())
                .receiverId(request.getReceiverId())
                .chat(chat)
                .state(MessageState.SENT)
                .build();
    }

    public MessageResponse fromMessageToMessageResponse(Message message){
        return MessageResponse.builder()
                .senderId(message.getSenderId())
                .receiverId(message.getReceiverId())
                .content(message.getContent())
                .createdAt(message.getCreatedAt())
                .messageType(message.getType())
                .messageState(message.getState())
                .id(message.getId())
                .build();
    }
}
