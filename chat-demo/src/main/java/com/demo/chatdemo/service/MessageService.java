package com.demo.chatdemo.service;

import com.demo.chatdemo.dto.request.MessageRequest;
import com.demo.chatdemo.dto.response.MessageResponse;
import com.demo.chatdemo.entity.Message;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MessageService {
    public List<Message> findAllByChatId(Long chatId);
    void updateMessageStateById(Long id, String state);
    void SaveMessage(MessageRequest messageRequest);
    List<MessageResponse> findAllMessagesByChatId(String chatId);

    void setMessageStateToSeen(String chatId, Authentication currentUser);

    void uploadMediaMessage(String chatId, MultipartFile file, Authentication currentUser);
}
