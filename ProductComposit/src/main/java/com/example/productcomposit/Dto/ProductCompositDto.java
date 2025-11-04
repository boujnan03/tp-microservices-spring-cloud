package com.example.productcomposit.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCompositDto {
    private Long productId;
    private String name;
    private double weight;
    private List<ReviewResponse> reviews;
    private List<RecommendationResponse> recommendations;
}
