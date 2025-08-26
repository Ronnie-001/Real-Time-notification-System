package com.example.kent_notifier.app.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.kent_notifier.app.User.DTO.SignupRequestDTO;
import com.example.kent_notifier.app.User.Model.*;

@Component
public class UserMapper {
    
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserMapper(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User toEntity(SignupRequestDTO signupRequestDTO) {
        if (signupRequestDTO == null) return null;

        User user = new User();
        
        user.setEmail(signupRequestDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(signupRequestDTO.getPassword()));

        return user;
    }
}
