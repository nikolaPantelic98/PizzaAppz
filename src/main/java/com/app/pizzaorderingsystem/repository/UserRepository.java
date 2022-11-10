package com.app.pizzaorderingsystem.repository;

import com.app.pizzaorderingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.emailAddress = ?1")
    User findByEmailAddress(String emailAddress);
}
