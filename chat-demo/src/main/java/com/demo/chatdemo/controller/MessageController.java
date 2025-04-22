package com.demo.chatdemo.controller;

import com.demo.chatdemo.dto.request.MessageRequest;
import com.demo.chatdemo.dto.response.MessageResponse;
import com.demo.chatdemo.service.MessageService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
@Tag(name = "Message", description = "Message API")
public class MessageController {
    @Autowired
    MessageService messageService;
    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendMessage(@RequestBody MessageRequest messageRequest) {
        messageService.SaveMessage(messageRequest);
    }
    @PostMapping(value = "/upload-media",consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadMediaMessage(@RequestParam("chat-id") String chatId,
                                   @Parameter()
                                   @RequestParam("file") MultipartFile file,
                                   Authentication currentUser) {
        messageService.uploadMediaMessage(chatId,file,currentUser);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void setMessagesToSeen(@RequestParam("chat-id") String chatId, Authentication currentUser) {
        messageService.setMessageStateToSeen(chatId,currentUser);
    }
    @GetMapping("/chat/{chat-id}")
    public ResponseEntity<List<MessageResponse>> getMessagesByChatId(@PathVariable("chat-id") String chatId) {
        List<MessageResponse> messageResponses=messageService.findAllMessagesByChatId(chatId);
        return ResponseEntity.ok(messageResponses);
    }
}
