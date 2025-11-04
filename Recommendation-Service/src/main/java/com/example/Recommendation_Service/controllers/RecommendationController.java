package com.example.Recommendation_Service.controllers;

import com.example.Recommendation_Service.Services.RecommendationService;
import com.example.Recommendation_Service.dto.RecommendationRequest;
import com.example.Recommendation_Service.dto.RecommendationResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @PostMapping
    public long addRecommendation(@Valid @RequestBody RecommendationRequest recommendationRequest) {
        System.out.println("Author: " + recommendationRequest.getAuthor());
        System.out.println("Rate: " + recommendationRequest.getRate());
        System.out.println("Content: " + recommendationRequest.getContent());
        return recommendationService.addRecommendation(recommendationRequest);
    }

    // for get all the recommendations
    @GetMapping
    public List<RecommendationResponse> getAllRecommendations() {
        return recommendationService.getAllRecommendations();
    }

    // for get one recommendation
    @GetMapping("/{id}")
    public ResponseEntity<RecommendationResponse> getRecommendation(@PathVariable long id) {
        RecommendationResponse productResponse = recommendationService.getRecommendation(id);
        return ResponseEntity.ok().body(productResponse);
    }

    // for delete a recommendation
    @DeleteMapping("/{id}")
    public void deleteRecommendation(@PathVariable long id) {
        recommendationService.deleteRecommendation(id);
    }

    // for update a recommendation
    @PutMapping("/{id}")
    public void updateRecommendation(@PathVariable long id, @Valid @RequestBody RecommendationRequest recommendationRequest) {
        recommendationService.updateRecommendation(id, recommendationRequest);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<List<RecommendationResponse>> getRecommendationsByProductId(@PathVariable long id) {
        List<RecommendationResponse> recommendationResponses = recommendationService.getRecommendationsByProductId(id);
        return ResponseEntity.ok().body(recommendationResponses);
    }
}