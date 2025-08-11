package com.example.kent_notifier.app.Role.Model;

import jakarta.persistence.*; 

import com.example.kent_notifier.app.Role.ERole; 

@Entity
@Table(name = "roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ERole name; 

    public Role() {}    
    
    public void setRoleName(ERole name) {
        this.name = name;
    }

    public ERole getRoleName() {
        return this.name;
    }
}

