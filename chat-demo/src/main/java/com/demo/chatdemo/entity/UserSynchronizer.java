package com.demo.chatdemo.entity;

import com.demo.chatdemo.mapper.UserMapper;
import com.demo.chatdemo.repository.UserRepository;
import com.demo.chatdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserSynchronizer {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    public void synchronizeWithIdp(Jwt token) {
        log.info("Synchronizing user with idp");
       // getUserEmailFromToken(token).ifPresent(email->userRepository.findByEmail(email).ifPresentOrElse(user->log.info("User already exists"),()->{}))
        getUserEmailFromToken(token).ifPresent(
                email->{
                    log.info("User email :"+email);
                 //   Optional<User> optUser=userRepository.findByEmail(email);
                    User user= userMapper.fromTokenAttributes(token.getClaims());
                  //  optUser.ifPresent(value->user.setId(value.getId()));
                    userRepository.save(user);
                }

        );
    }

    private Optional<String> getUserEmailFromToken(Jwt token) {
        return Optional.ofNullable(token.getClaimAsString("email"));
    }
}
