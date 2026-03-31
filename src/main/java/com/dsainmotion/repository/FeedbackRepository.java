package com.dsainmotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dsainmotion.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
