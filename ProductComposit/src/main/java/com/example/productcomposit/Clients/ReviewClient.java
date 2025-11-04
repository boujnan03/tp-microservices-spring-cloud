package com.example.productcomposit.Clients;


import com.example.productcomposit.Dto.RecommendationResponse;
import com.example.productcomposit.Dto.ReviewResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
@Component
@FeignClient(name = "REVIEW-SERVICE")
public interface ReviewClient {

    @GetMapping("/reviews/products/{id}")
    List<ReviewResponse> getReviewsByProductId(@PathVariable long id);




}
