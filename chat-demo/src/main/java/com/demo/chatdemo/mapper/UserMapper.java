package com.demo.chatdemo.mapper;

import com.demo.chatdemo.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
public class UserMapper {
    public User fromTokenAttributes(Map<String, Object> attributes) {
        User user=new User();
        if(attributes.containsKey("sub")){
            user.setId(Long.parseLong((String) attributes.get("sub")));

        }
        if(attributes.containsKey("given_name"))
        {
            user.setFirstName((String) attributes.get("given_name"));
        }
        else if(attributes.containsKey("nickname")){
            user.setFirstName((String) attributes.get("nickname"));
        }
        if(attributes.containsKey("family_name")){
            user.setLastName((String) attributes.get("family_name"));
        }
        if(attributes.containsKey("email")){
            user.setEmail((String) attributes.get("email"));
        }
        user.setLastSeen(LocalDateTime.now());
        return user;
    }
}
