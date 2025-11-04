package com.example.productcomposit.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
