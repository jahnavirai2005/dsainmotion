package com.dsainmotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dsainmotion.entity.StudyVault;

public interface StudyVaultRepository extends JpaRepository<StudyVault, String> {
}
