package com.demo.chatdemo.controller;

import com.demo.chatdemo.dto.ChatResponse;
import com.demo.chatdemo.dto.StringResponse;
import com.demo.chatdemo.entity.Chat;
import com.demo.chatdemo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")

public class ChatController {

    @Autowired
    ChatService chatService;

    @PostMapping("/create")
    public ResponseEntity<StringResponse> createChat(Long senderId, Long receiverId) {
        String chatId= chatService.createChat(senderId,receiverId);
        StringResponse stringResponse=StringResponse.builder()
                .response(chatId)
                .build();
        return ResponseEntity.ok(stringResponse);
    }
    @GetMapping
    public ResponseEntity<List<ChatResponse>> getChatsByReceiverId(Authentication currentUser) {
     return ResponseEntity.ok(chatService.getChatsByReceiverId(currentUser));
    }
}
