package com.dsainmotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dsainmotion.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}