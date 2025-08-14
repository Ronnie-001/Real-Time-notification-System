package com.example.kent_notifier.app.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kent_notifier.app.User.Model.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
    // emails are made unique, only one method needed to grab a user from Postgres.
    User findByEmail(String emailAddress); 

    boolean emailExists(String emailAddress);
}
