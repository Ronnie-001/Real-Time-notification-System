package com.example.kent_notifier.app.User.Model;

import com.example.kent_notifier.app.Role.Model.Role;

import jakarta.persistence.*;

import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "users")
public class User { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password; 
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    ) 

    private Set<Role> roles = new HashSet<>();

    public User() {}
    
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setRoles(Set<Role> rolesToAdd) {
        for (Role role : rolesToAdd) {
            this.roles.add(role);
        } 
    }
    
    public Set<Role> getRoles() {
        return this.roles;
    }
}
