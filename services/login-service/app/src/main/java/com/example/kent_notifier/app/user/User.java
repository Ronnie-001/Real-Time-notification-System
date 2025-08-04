package com.example.kent_notifier.app.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User { 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "email", nullable = false, unique = true)
    private String emailAddress;
    
    @Column(name = "password", nullable = false)
    private String password; 

    @Column(name = "role")
    private Role role;

    public User() {}

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setEmailAddress(String newEmailAddress) {
        this.emailAddress = newEmailAddress;
    }

    public void setRole(Role newRole) {
        this.role = newRole;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }
    
    public Role getRole() {
        return this.role;
    }
}
