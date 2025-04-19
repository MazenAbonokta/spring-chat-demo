package com.demo.chatdemo.repository;

import com.demo.chatdemo.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,String> {
    List<Chat> findDistinctBySenderIdOrReceiverId(Long senderId, Long receiverId);
    List<Chat> findDistinctBySenderIdAndReceiverId(Long senderId, Long receiverId);
}
