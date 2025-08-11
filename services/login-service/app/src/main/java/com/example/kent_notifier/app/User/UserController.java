package com.example.kent_notifier.app.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    
    @PostMapping("login-service/auth/v1/signup")
    public String login() {
        return "login successful!";
    }

}
