package com.dsainmotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dsainmotion.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.userId = :userid")
    User getUserById(@Param("userid") String userid);

    boolean existsByUserId(String userId);

    boolean existsByEmail(String email);

    User findByEmail(String email);
}