package com.example.kent_notifier.app.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.kent_notifier.app.User.Model.*;
import com.example.kent_notifier.app.User.Repository.*;

public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("ERROR: User not found."));

        if (user == null) throw new UsernameNotFoundException("Email address not found");

        UserDetailsImpl userDetails = new UserDetailsImpl(user.getEmail(), user.getPassword(), user.getRoles());
        return userDetails;
    } 
}
