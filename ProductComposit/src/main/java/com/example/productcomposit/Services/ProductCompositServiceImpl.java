package com.example.productcomposit.Services;

import com.example.productcomposit.Clients.ProductClient;
import com.example.productcomposit.Clients.RecommendationClient;
import com.example.productcomposit.Clients.ReviewClient;
import com.example.productcomposit.Dto.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductCompositServiceImpl {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private RecommendationClient recommendationClient;

    @Autowired
    private ReviewClient reviewClient;

    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackGetProduct")
    @Retry(name = "productService", fallbackMethod = "fallbackGetProduct")
    public ProductCompositDto getAllProducts(long id) {
        ProductDto productDto = productClient.getProductById(id);
        List<RecommendationResponse> recommendations = recommendationClient.getRecommendationsByProductId(id);
        List<ReviewResponse> reviewClients = reviewClient.getReviewsByProductId(id);

        ProductCompositDto productCompositDto = new ProductCompositDto();
        productCompositDto.setProductId(id);
        productCompositDto.setName(productDto.getName());
        productCompositDto.setWeight(productDto.getWeight());
        productCompositDto.setReviews(reviewClients);
        productCompositDto.setRecommendations(recommendations);

        return productCompositDto;
    }

    // Méthode de fallback CORRIGÉE (sans setMessage)
    public ProductCompositDto fallbackGetProduct(long id, Throwable t) {
        // Retourne un objet composite avec des valeurs par défaut
        ProductCompositDto fallbackDto = new ProductCompositDto();
        fallbackDto.setProductId(id);
        fallbackDto.setName("Service temporairement indisponible");
        fallbackDto.setWeight(0);
        fallbackDto.setReviews(Collections.emptyList());
        fallbackDto.setRecommendations(Collections.emptyList());
        // SUPPRIMEZ cette ligne : fallbackDto.setMessage("Fallback activé: " + t.getMessage());

        return fallbackDto;
    }

    // Méthode pour tester la résilience (simuler des erreurs)
    @CircuitBreaker(name = "testService", fallbackMethod = "testFallback")
    @Retry(name = "testService")
    public String testResilience(boolean shouldFail) {
        if (shouldFail) {
            throw new RuntimeException("Erreur simulée pour tester le Circuit Breaker");
        }
        return "Succès - Service fonctionne normalement";
    }

    public String testFallback(boolean shouldFail, Throwable t) {
        return "Fallback activé - Service en mode dégradé: " + t.getMessage();
    }
}