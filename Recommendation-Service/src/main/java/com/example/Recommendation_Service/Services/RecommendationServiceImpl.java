package com.example.Recommendation_Service.Services;

import com.example.Recommendation_Service.dto.RecommendationRequest;
import com.example.Recommendation_Service.dto.RecommendationResponse;
import com.example.Recommendation_Service.entity.Recommendation;
import com.example.Recommendation_Service.repository.RecommendationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Override
    public long addRecommendation(RecommendationRequest recommendationRequest) {
        Recommendation recommendation = new Recommendation();
        recommendation.setProductId(recommendationRequest.getProductId()); // ← AJOUTEZ CETTE LIGNE
        recommendation.setAuthor(recommendationRequest.getAuthor());
        recommendation.setRate(recommendationRequest.getRate());
        recommendation.setContent(recommendationRequest.getContent());
        recommendationRepository.save(recommendation);
        return recommendation.getRecommendationId();
    }

    public RecommendationResponse getRecommendation(long id) {
        Optional<Recommendation> recommend = recommendationRepository.findById(id);
        RecommendationResponse recommendationResponse = new RecommendationResponse();
        if (recommend.isPresent()) {
            Recommendation recommendation = recommend.get();
            recommendationResponse.setContent(recommendation.getContent());
            recommendationResponse.setAuthor(recommendation.getAuthor());
            recommendationResponse.setRate(recommendation.getRate());
            recommendationResponse.setRecommendationId(recommendation.getRecommendationId());
            return recommendationResponse;
        }
        return recommendationResponse;

    }

    public List<RecommendationResponse> getAllRecommendations() {
        List <Recommendation> recommendation1 = recommendationRepository.findAll();
        List<RecommendationResponse> recommendationResponses = new ArrayList<>();
        for (Recommendation recomen : recommendation1) {
            RecommendationResponse recommendationResponse = new RecommendationResponse();
            recommendationResponse.setRecommendationId(recomen.getRecommendationId());
            recommendationResponse.setAuthor(recomen.getAuthor());
            recommendationResponse.setRate(recomen.getRate());
            recommendationResponse.setContent(recomen.getContent());
            recommendationResponses.add(recommendationResponse);
        }
        return recommendationResponses;
    }

    @Override
    public void updateRecommendation(long id, RecommendationRequest recommendationRequest) {
        Optional<Recommendation> recommendation1 = recommendationRepository.findById(id);
        if(recommendation1.isPresent()) {
            Recommendation recommendation = recommendation1.get();
            recommendation.setAuthor(recommendation1.get().getAuthor());
            recommendation.setRate(recommendation1.get().getRate());
            recommendation.setContent(recommendation1.get().getContent());
            recommendationRepository.save(recommendation);

        }
    }

    @Override
    public void deleteRecommendation(long id) {
        Optional<Recommendation> recommendation = recommendationRepository.findById(id);
        recommendation.ifPresent(value -> recommendationRepository.delete(value));

    }
    @Override
    public List<RecommendationResponse> getRecommendationsByProductId(long id) {
        List<Recommendation> recommendationList=recommendationRepository.findRecommendationsByProductId(id);
        List<RecommendationResponse> recommendationResponses = new ArrayList<>();
        for (Recommendation recommendation : recommendationList) {
            RecommendationResponse recommendationResponse = new RecommendationResponse();
            recommendationResponse.setRecommendationId(recommendation.getRecommendationId());
            recommendationResponse.setAuthor(recommendation.getAuthor());
            recommendationResponse.setRate(recommendation.getRate());
            recommendationResponse.setContent(recommendation.getContent());
            recommendationResponses.add(recommendationResponse);

        }
        return recommendationResponses;
    }


}
