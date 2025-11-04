package com.example.Review_Service.Dto;

import com.example.Review_Service.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponse {
    private long ReviewId;
    private String Author;
    private String Subject;
    private String Content;


    public ReviewResponse(Review review1) {
        this.ReviewId=review1.getReviewId();
        this.Author=review1.getAuthor();
        this.Subject=review1.getSubject();
        this.Content=review1.getContent();
    }


}
