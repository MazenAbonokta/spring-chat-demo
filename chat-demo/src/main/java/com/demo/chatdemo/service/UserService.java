package com.demo.chatdemo.service;

import com.demo.chatdemo.dto.response.UserResponse;
import com.demo.chatdemo.entity.User;

import java.util.List;

public interface UserService {
    public User findByEmail(String email);
    public List<UserResponse> findAllExceptSelf(Long Id);
    public User getUserById(Long Id);

}
