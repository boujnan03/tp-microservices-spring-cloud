package com.example.Recommendation_Service.repository;

import com.example.Recommendation_Service.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findRecommendationsByProductId(long id);
}
