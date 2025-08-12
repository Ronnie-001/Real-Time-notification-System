package com.example.kent_notifier.app.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kent_notifier.app.Role.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

    Role findByName(String roleName);  
}
