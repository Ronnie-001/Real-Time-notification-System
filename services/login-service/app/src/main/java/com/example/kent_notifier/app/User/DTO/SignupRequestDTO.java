package com.example.kent_notifier.app.User.DTO;

public class SignupRequestDTO {
    
    private String email;

    private String password;

    private String role;
    
    public String getEmailAddress() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return this.role;
    }
}
