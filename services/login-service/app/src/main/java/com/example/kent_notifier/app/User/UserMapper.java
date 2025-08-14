package com.example.kent_notifier.app.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.kent_notifier.app.User.DTO.SignupRequestDTO;
import com.example.kent_notifier.app.User.Model.*;

public class UserMapper {
    
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserMapper(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public static User toEntity(SignupRequestDTO signupRequestDTO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        if (signupRequestDTO == null) return null;

        User user = new User();

        user.setEmailAddress(signupRequestDTO.getEmailAddress());
        user.setPassword(bCryptPasswordEncoder.encode(signupRequestDTO.getPassword()));

        return user;
    }
}
