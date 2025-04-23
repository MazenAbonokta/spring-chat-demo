package com.demo.chatdemo.Imp;

import com.demo.chatdemo.config.FileUtils;
import com.demo.chatdemo.constant.MessageState;
import com.demo.chatdemo.constant.MessageType;
import com.demo.chatdemo.constant.NotificationType;
import com.demo.chatdemo.dto.request.MessageRequest;
import com.demo.chatdemo.dto.response.MessageResponse;
import com.demo.chatdemo.entity.Chat;
import com.demo.chatdemo.entity.Message;
import com.demo.chatdemo.entity.Notification;
import com.demo.chatdemo.exception.ResourceNotFoundException;
import com.demo.chatdemo.mapper.MessageMapper;
import com.demo.chatdemo.repository.ChatRepository;
import com.demo.chatdemo.repository.MessageRepository;
import com.demo.chatdemo.service.FileService;
import com.demo.chatdemo.service.MessageService;
import com.demo.chatdemo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public  class MessageServiceImp implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    FileService fileService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    MessageMapper messageMapper;
    @Override
    public List<Message> findAllByChatId(Long chatId) {
        return messageRepository.findAllByChatId(chatId.toString());
    }

    @Override
    public void updateMessageStateById(Long id, String state) {
        messageRepository.updateMessageStateById(id,state);
    }

    @Override
    public void SaveMessage(MessageRequest messageRequest) {
        Chat chat=chatRepository.findById(messageRequest.getChatId()).orElseThrow(()->new ResourceNotFoundException("Chat not found with id :"+messageRequest.getChatId()));

        Message message=messageMapper.fromMessageRequestToMessage(messageRequest,chat);
        messageRepository.save(message);
        Notification notification=Notification.builder()
                .chatId(messageRequest.getChatId())
                .messageType(message.getType())
                .chatName(message.getChat().getChatName(message.getSenderId()))
                .senderId(message.getSenderId())
                .receiverId(message.getReceiverId())
                .content(message.getContent())
                .type(NotificationType.MESSAGE)
                .build();
        notificationService.sendNotification(message.getReceiverId(),notification);
    }

    @Override
    public List<MessageResponse> findAllMessagesByChatId(String chatId) {

        List<Message> messages=  messageRepository.findAllByChatId(chatId);

        List<MessageResponse> messageResponses=messages.stream().map(message -> messageMapper.fromMessageToMessageResponse(message))
                .collect(Collectors.toList());
        return messageResponses;
    }

    @Override
    @Transactional
    public void setMessageStateToSeen(String chatId, Authentication currentUser) {
        Chat chat=chatRepository.findById(chatId).orElseThrow(()->new ResourceNotFoundException("Chat not found with id :"+chatId));
              String recipientId=getRecipientId(chat,currentUser);
        updateMessageStateById(Long.parseLong(chatId),"SEEN");
        Notification notification=Notification.builder()
                .chatId(chat.getId().toString())

                .senderId(getSenderId(chat,currentUser))
                .receiverId(recipientId)

                .type(NotificationType.SEEN)
                .build();
        notificationService.sendNotification(recipientId,notification);

    }

    @Override
    public void uploadMediaMessage(String chatId, MultipartFile file, Authentication currentUser) {
        Chat chat=chatRepository.findById(chatId).orElseThrow(()->new ResourceNotFoundException("Chat not found with id :"+chatId));
        String senderId=getSenderId(chat,currentUser);
        String recipientId = getRecipientId(chat,currentUser);
        String filePath=fileService.saveFile(file,senderId);
        Message message=Message.builder()
            .type(MessageType.IMAGE)
            .state(MessageState.SENT)
            .receiverId(recipientId)
            .senderId(senderId)
            .mediaFilePath(filePath)
            .chat(chat)
            .build();
        messageRepository.save(message);
        Notification notification=Notification.builder()
                .chatId(chatId)
                .chatName(chat.getChatName(message.getSenderId()))
                .senderId(senderId)
                .receiverId(recipientId)
                .media(FileUtils.getFileBytes(message.getMediaFilePath()))
                .type(NotificationType.IMAGE)
                .messageType(MessageType.IMAGE)
                .build();
        notificationService.sendNotification(recipientId,notification);

    }

    private String getRecipientId(Chat chat, Authentication currentUser) {
        if(chat.getSender().getId().equals(currentUser.getName())){
            return chat.getReceiver().getId().toString();
        }
        return chat.getSender().getId().toString();
    }
    private String getSenderId(Chat chat, Authentication currentUser) {
        if(chat.getSender().getId().equals(currentUser.getName())){
            return chat.getReceiver().getId().toString();
        }
        return chat.getSender().getId().toString();
    }
}
