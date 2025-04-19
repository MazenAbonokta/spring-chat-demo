package com.demo.chatdemo.service;

import com.demo.chatdemo.entity.Message;

import java.util.List;

public interface MessageService {
    public List<Message> findAllByChatId(Long chatId);
    void updateMessageStateById(Long id, String state);
}
