package com.demo.chatdemo.repository;

import com.demo.chatdemo.entity.Message;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
List<Message> findAllByChatId(Long chatId);
    @Modifying
    @Transactional
    @Query("UPDATE Message m SET m.state = :state WHERE m.chat.id = :id")
    void updateMessageStateById(@Param("id") Long id, @Param("state") String state);


}
