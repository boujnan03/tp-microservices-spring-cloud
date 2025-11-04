package com.example.Recommendation_Service.Services;

import com.example.Recommendation_Service.dto.RecommendationRequest;
import com.example.Recommendation_Service.dto.RecommendationResponse;

import java.util.List;

public interface RecommendationService {
    public long addRecommendation(RecommendationRequest recommendationRequest) ;    public RecommendationResponse getRecommendation( long id);
    public List<RecommendationResponse> getAllRecommendations();
    public void updateRecommendation(long id, RecommendationRequest recommendationRequest);
    public void deleteRecommendation(long id);
    public List<RecommendationResponse> getRecommendationsByProductId(long id);
}

