package com.dsainmotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dsainmotion.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {

    @Query("SELECT a FROM Admin a WHERE a.adminId = :adminId")
    Admin getAdminById(@Param("adminId") String adminId);

    boolean existsByAdminId(String adminId);

    boolean existsByEmail(String email);

    Admin findByEmail(String email);
    
}