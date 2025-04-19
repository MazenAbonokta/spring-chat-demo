package com.demo.chatdemo.repository;

import com.demo.chatdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u  where u.id!=:Id")
    List<User> findAllExceptSelf(@Param("Id") Long Id);
}
