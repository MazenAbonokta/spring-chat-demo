package com.demo.chatdemo.service;

import com.demo.chatdemo.entity.User;

import java.util.List;

public interface UserService {
    public User findByEmail(String email);
    public List<User> findAllExceptSelf(Long Id);
    public User getUserById(Long Id);

}
