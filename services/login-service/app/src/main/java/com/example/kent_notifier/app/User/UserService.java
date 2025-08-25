package com.example.kent_notifier.app.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import com.example.kent_notifier.app.User.Model.*;
import com.example.kent_notifier.app.Role.Model.Role;
import com.example.kent_notifier.app.Role.ERole;
import com.example.kent_notifier.app.User.Repository.*;
import com.example.kent_notifier.app.User.DTO.*;

@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    public User createUser(SignupRequestDTO signupRequestDTO) {
        User user = UserMapper.toEntity(signupRequestDTO, bCryptPasswordEncoder);

        // search role repository to find roles
        Role role = roleRepository.findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("ERROR: Role not found.")); 

        System.out.println("It's this ->>> " + role.getRoleName());

        HashSet<Role> roles = new HashSet<>();
        roles.add(role);

        user.setRoles(roles); 
        return user; 
    }
}
