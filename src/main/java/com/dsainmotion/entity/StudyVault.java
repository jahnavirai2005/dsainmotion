package com.dsainmotion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "study_vault")
public class StudyVault {

    @Id
    @Column(name = "topic")
    private String topic;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    // Default constructor
    public StudyVault() {}

    // Parameterized constructor
    public StudyVault(String topic, String content) {
        this.topic = topic;
        this.content = content;
    }

    // Getters and Setters
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
