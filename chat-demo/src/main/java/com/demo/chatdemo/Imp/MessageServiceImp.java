package com.demo.chatdemo.Imp;

import com.demo.chatdemo.entity.Message;
import com.demo.chatdemo.repository.MessageRepository;
import com.demo.chatdemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class MessageServiceImp implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> findAllByChatId(Long chatId) {
        return messageRepository.findAllByChatId(chatId);
    }

    @Override
    public void updateMessageStateById(Long id, String state) {
        messageRepository.updateMessageStateById(id,state);
    }
}
