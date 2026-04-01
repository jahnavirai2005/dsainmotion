package com.dsainmotion;

import com.dsainmotion.entity.StudyVault;
import com.dsainmotion.repository.StudyVaultRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private StudyVaultRepository studyVaultRepository;

    @GetMapping("/{slug}")
    public ResponseEntity<?> getContent(@PathVariable String slug) {
        Optional<StudyVault> optionalStudyVault = studyVaultRepository.findBySlug(slug);
        
        if (optionalStudyVault.isPresent()) {
            StudyVault studyVault = optionalStudyVault.get();
            
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> content = objectMapper.readValue(studyVault.getContentJson(), Map.class);
                
                Map<String, Object> response = new HashMap<>();
                response.put("topic", studyVault.getTopic());
                response.put("subtopic", studyVault.getSubtopic());
                response.put("content", content);
                
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Invalid JSON content");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}