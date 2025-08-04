package com.example.kent_notifier.app.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    
    // Define role prefix for user authorities
    private final String ROLE_PREFIX = "ROLE_";
    
    // fields of user.
    private Long id;

    private String emailAddress;

    private String password;

    private Role role; 

    public UserDetailsImpl(String emailAddress, String password, Role role) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.role = role;
    }
    
    public String getEmailAddress() {
        return this.emailAddress;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public Role getRole() {
        return this.role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + this.getRole()));

        return list;
    }
   
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return null;
    } 
}
