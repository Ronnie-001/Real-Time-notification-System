package com.example.kent_notifier.app.User;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.kent_notifier.app.User.Model.SigninCredentials;

@Controller
public class UserController {
    
    @PostMapping("login-service/auth/v1/signup")
    public ResponseEntity signUp(@RequestBody SigninCredentials signinCredentials) {
        // check if the email already exists within the userRepository
        
        // create the user
    }
}
