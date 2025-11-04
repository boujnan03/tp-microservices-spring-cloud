package com.example.restclient.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProductCompositeResponse {
    private Long productId;
    private String name;
    private Double weight;
    private List<Review> reviews;
    private List<Recommendation> recommendations;

    @Data
    public static class Review {
        private String author;
        private String subject;
        private String content;
        private Long reviewId;
    }

    @Data
    public static class Recommendation {
        private Long recommendationId;
        private String author;
        private Integer rate;
        private String content;
    }
}