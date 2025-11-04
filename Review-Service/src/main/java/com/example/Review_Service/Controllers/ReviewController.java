package com.example.Review_Service.Controllers;

import com.example.Review_Service.Dto.ReviewRequest;
import com.example.Review_Service.Dto.ReviewResponse;
import com.example.Review_Service.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private  ReviewService reviewService;
    @PostMapping
    public ReviewResponse addProduct(@RequestBody ReviewRequest reviewRequest) {
        return reviewService.addReview(reviewRequest);
    }
    //for get all the reviews
    @GetMapping
    public List<ReviewResponse> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // for get one review
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReview(@PathVariable long id) {
        ReviewResponse reviewResponse = reviewService.getReview(id);
        return ResponseEntity.ok().body(reviewResponse);
    }
    // for delete a review
    @DeleteMapping("/{id}")
    public void deleteReview( @PathVariable long id) {
        reviewService.deleteReview(id);
    }
    // for update a Review
    @PutMapping("/{id}")
    public void updateProduct( @PathVariable long id, ReviewRequest reviewRequest) {
        reviewService.updateReview(id, reviewRequest);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<List<ReviewResponse>> getReviewbyProduct( @PathVariable long id) {
        List<ReviewResponse> reviewResponse = reviewService.getReviewsByProduct(id);

//        if (id % 2 == 0) {
//            throw new RuntimeException("Erreur simulée pour les avis du produit " + id);
//        }
        return ResponseEntity.ok().body(reviewResponse);

    }
}
