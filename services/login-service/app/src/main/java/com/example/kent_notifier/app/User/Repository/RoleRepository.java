package com.example.kent_notifier.app.User.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kent_notifier.app.Role.ERole;
import com.example.kent_notifier.app.Role.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

    Optional<Role> findByName(ERole roleName);  
}
