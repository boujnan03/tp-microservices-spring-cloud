package com.example.Recommendation_Service.dto;

import com.example.Recommendation_Service.entity.Recommendation;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RecommendationResponse {
    private long recommendationId;
    private String author;
    private int  rate ;
    private String content ;
}
