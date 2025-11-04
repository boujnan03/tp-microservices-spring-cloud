package com.example.Recommendation_Service.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecommendationRequest {
    private long productId;  // ← AJOUTEZ CETTE LIGNE
    private long RecommendationId;
    private String author;

    @Min(value = 0, message = "Rate cannot be negative")
    @Max(value = 100, message = "Rate cannot exceed 100%")
    private int rate;

    private String content;
}