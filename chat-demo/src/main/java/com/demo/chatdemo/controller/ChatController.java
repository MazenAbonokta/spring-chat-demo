package com.demo.chatdemo.controller;

import com.demo.chatdemo.dto.response.ChatResponse;
import com.demo.chatdemo.dto.response.StringResponse;
import com.demo.chatdemo.service.ChatService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Chat", description = "Chat API")
@OpenAPIDefinition(tags = { @Tag(name = "Chat", description = "Chat API") },info = @Info(title = "Chat API", version = "1.0"))
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
