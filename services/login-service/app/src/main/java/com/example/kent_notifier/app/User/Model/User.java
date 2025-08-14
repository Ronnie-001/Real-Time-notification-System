package com.example.kent_notifier.app.User.Model;

import com.example.kent_notifier.app.Role.Model.Role;

import jakarta.persistence.*;

import java.util.HashSet;

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
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    ) 
    private HashSet<Role> roles = new HashSet<>();

    public User() {}
    
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setEmailAddress(String newEmailAddress) {
        this.emailAddress = newEmailAddress;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setRoles(HashSet<Role> rolesToAdd) {
        for (Role role : rolesToAdd) {
            this.roles.add(role);
        } 
    }
    
    public HashSet<Role> getRoles() {
        return this.roles;
    }
}
