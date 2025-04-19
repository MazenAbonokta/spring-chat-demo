package com.demo.chatdemo.Imp;

import com.demo.chatdemo.entity.User;
import com.demo.chatdemo.exception.ResourceNotFoundException;
import com.demo.chatdemo.repository.UserRepository;
import com.demo.chatdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository  userRepository;
    @Override
    public User findByEmail(String email) {

     var user=userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found with email :"+email));

        return user;
    }

    @Override
    public List<User> findAllExceptSelf(Long Id) {

        List<User> users= userRepository.findAllExceptSelf(Id);
        return users;
    }

    @Override
    public User getUserById(Long Id) {
        var user=userRepository.findById(Id).orElseThrow(()->new ResourceNotFoundException("User not found with id :"+Id));
        return user;
    }
}
