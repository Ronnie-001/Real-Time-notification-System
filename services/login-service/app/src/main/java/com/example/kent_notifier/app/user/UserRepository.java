package com.example.kent_notifier.app.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
    // emails are made unique, only one method needed to grab a user from Postgres.
    User findByEmail(String emailAddress); 
}
