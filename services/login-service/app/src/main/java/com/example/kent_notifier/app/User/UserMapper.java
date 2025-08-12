package com.example.kent_notifier.app.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.kent_notifier.app.User.Model.*;

public class UserMapper {
    
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserMapper(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public static User toEntity(SigninCredentials signinCredentials, BCryptPasswordEncoder bCryptPasswordEncoder) {
        if (signinCredentials == null) return null;

        User user = new User();

        user.setEmailAddress(signinCredentials.getEmailAddress());
        user.setPassword(bCryptPasswordEncoder.encode(signinCredentials.getPassword()));

        return user;
    }
}
