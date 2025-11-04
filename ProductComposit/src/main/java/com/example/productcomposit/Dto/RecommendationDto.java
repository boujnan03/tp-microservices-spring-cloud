package com.example.productcomposit.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecommendationDto {
    private Long recommendationId;
    private Long productId;
    private String author;
    private int rate;
    private String content;
}
