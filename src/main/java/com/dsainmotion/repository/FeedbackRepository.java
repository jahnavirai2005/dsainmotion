package com.dsainmotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dsainmotion.entity.Feedback;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findAllByOrderByCreatedOnDesc();
}
