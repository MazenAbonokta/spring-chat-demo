package com.demo.chatdemo.repository;

import com.demo.chatdemo.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat,String> {
    List<Chat> findDistinctBySenderIdOrReceiverId(Long senderId, Long receiverId);
    Optional<Chat> findDistinctBySenderIdAndReceiverId(Long senderId, Long receiverId);

    List<Chat> findBySenderId(Long senderId);
}
