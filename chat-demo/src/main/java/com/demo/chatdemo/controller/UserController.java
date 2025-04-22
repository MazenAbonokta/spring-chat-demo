package com.demo.chatdemo.controller;

import com.demo.chatdemo.dto.response.UserResponse;
import com.demo.chatdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(Authentication currentUser) {
        Long id=Long.parseLong(currentUser.getName());
        List<UserResponse> userResponses=userService.findAllExceptSelf(id);
        return ResponseEntity.ok(userResponses);
    }

}
