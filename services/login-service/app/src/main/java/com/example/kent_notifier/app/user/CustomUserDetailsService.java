package com.example.kent_notifier.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return null; 
    } 

    public UserDetails loadUserByEmailAddress(String emailAddress) throws EmailNotFoundException{
        
        User user = userRepository.findByEmail(emailAddress);

        if (user == null) throw new EmailNotFoundException("Email address not found");

        UserDetailsImpl userDetails = new UserDetailsImpl(user.getEmailAddress(), user.getPassword(), user.getRole());
        return userDetails;
    } 
}

// create custom execption to handle no username being found
class EmailNotFoundException extends Exception {
    public EmailNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
