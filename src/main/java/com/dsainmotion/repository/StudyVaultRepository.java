package com.dsainmotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dsainmotion.entity.StudyVault;

import java.util.Optional;

public interface StudyVaultRepository extends JpaRepository<StudyVault, Integer> {
    Optional<StudyVault> findBySlug(String slug);
}
