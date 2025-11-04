package com.example.productcomposit.Clients;


import com.example.productcomposit.Dto.RecommendationResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
@Component
@FeignClient(name = "RECOMMENDATION-SERVICE")
public interface RecommendationClient {

    @GetMapping("/recommendations/products/{id}")
    List<RecommendationResponse> getRecommendationsByProductId(@PathVariable long id);


}
