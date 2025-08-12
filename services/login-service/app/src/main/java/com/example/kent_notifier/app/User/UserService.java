package com.example.kent_notifier.app.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import com.example.kent_notifier.app.User.Model.*;
import com.example.kent_notifier.app.Role.Model.Role;
import com.example.kent_notifier.app.Role.ERole;
import com.example.kent_notifier.app.User.Repository.*;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void registerNewUser(SigninCredentials signinCredentials) {
        User user = UserMapper.toEntity(signinCredentials, bCryptPasswordEncoder);

        HashSet<Role> roles = new HashSet<>();

        // search role repository to find roles
        Role role = new Role();
        role.setRoleName(ERole.USER);

        roles.add(role);

        // save to database;
        userRepository.save(user);        
    }
}
