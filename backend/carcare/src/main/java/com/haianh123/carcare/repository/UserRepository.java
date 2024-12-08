package com.haianh123.carcare.repository;

import com.haianh123.carcare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT u FROM User u WHERE u.email =:email")
    Optional<User> findByEmail(@Param(value = "email") String email);
}