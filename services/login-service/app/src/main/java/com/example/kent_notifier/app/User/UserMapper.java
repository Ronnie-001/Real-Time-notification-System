package com.example.kent_notifier.app.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.kent_notifier.app.User.Model.User;

public class UserMapper {
    
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserMapper(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public static User toEntity(UserDTO userDTO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        if (userDTO == null) return null;

        User user = new User();

        user.setEmailAddress(userDTO.getEmailAddress());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        return user;
    }
}
