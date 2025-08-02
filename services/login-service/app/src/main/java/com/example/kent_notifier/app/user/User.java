package com.example.kent_notifier.app.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User { 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;
    
    @Column
    private String password; 

    @Column
    private String emailAddress;

    @Column
    private String role;

    public User() {}

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setEmailAddress(String newEmailAddress) {
        this.emailAddress = newEmailAddress;
    }

    public void setRole(String newRole) {
        this.role = newRole;
    }

    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }
}
