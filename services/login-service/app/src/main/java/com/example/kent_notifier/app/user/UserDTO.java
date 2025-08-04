package com.example.kent_notifier.app.user;

import lombok.ToString;

@ToString
public class UserDTO {
    
    private String emailAddress;
    private String password;
    
    public void setemailAddress(String newUsername) {
        this.emailAddress = newUsername;
    }
    
    public void setPassword(String newPassword) {
        this.emailAddress = newPassword;
    }
    
    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getPassword() {
        return this.password;
    }
}
