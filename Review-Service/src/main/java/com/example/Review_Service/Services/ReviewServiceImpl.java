package com.example.Review_Service.Services;

import com.example.Review_Service.Dao.ReviewRepository;
import com.example.Review_Service.Dto.ReviewRequest;
import com.example.Review_Service.Dto.ReviewResponse;
import com.example.Review_Service.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;


    // for add a product
    @Override
    public ReviewResponse addReview(ReviewRequest reviewRequest) {
        Review review = new Review();
        review.setProductId(reviewRequest.getProductId()); // ← AJOUTEZ CETTE LIGNE
        review.setAuthor(reviewRequest.getAuthor());
        review.setSubject(reviewRequest.getSubject());
        review.setContent(reviewRequest.getContent());
        reviewRepository.save(review);
        return new ReviewResponse(review);
    }

    public ReviewResponse getReview(long id) {
        Optional<Review> review = reviewRepository.findById(id);
        ReviewResponse reviewResponse = new ReviewResponse();
        if (review.isPresent()) {
            Review review1 = review.get();
            return new ReviewResponse(review1);
        }
        return reviewResponse;

    }


    public List<ReviewResponse> getAllReviews() {
        List <Review> reviews = reviewRepository.findAll();
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        for (Review review : reviews) {
            ReviewResponse reviewResponse = new ReviewResponse();
            reviewResponse.setReviewId(review.getReviewId());
            reviewResponse.setAuthor(review.getAuthor());
            reviewResponse.setSubject(review.getSubject());
            reviewResponse.setContent(review.getContent());

            reviewResponses.add(reviewResponse);
        }
        return reviewResponses;
    }

    @Override
    public void updateReview(long id, ReviewRequest reviewRequest) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent()) {
            Review review1 = review.get();
            review1.setReviewId(review1.getReviewId());
            review1.setAuthor(reviewRequest.getAuthor());
            review1.setSubject(reviewRequest.getSubject());
            review1.setContent(reviewRequest.getContent());
            reviewRepository.save(review1);
        }
    }

    @Override
    public void deleteReview(long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent()) {
            reviewRepository.delete(review.get());
        }

    }
    public List<ReviewResponse> getReviewsByProduct(long id) {
        List<Review> reviews = reviewRepository.findReviewByProductId(id);
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        for (Review review : reviews) {
            ReviewResponse reviewResponse = new ReviewResponse();
            reviewResponse.setReviewId(review.getReviewId());
            reviewResponse.setAuthor(review.getAuthor());
            reviewResponse.setSubject(review.getSubject());
            reviewResponse.setContent(review.getContent());
            reviewResponses.add(reviewResponse);
        }

      return reviewResponses;
    }

}
