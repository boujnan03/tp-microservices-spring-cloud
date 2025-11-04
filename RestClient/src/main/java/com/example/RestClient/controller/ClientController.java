package com.example.restclient.controller;

import com.example.restclient.dto.ProductCompositeResponse;
import com.example.restclient.service.ApiClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ApiClientService apiClientService;

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductCompositeResponse> getProduct(@PathVariable Long id) {
        ProductCompositeResponse response = apiClientService.getProductComposite(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test/resilience")
    public String testResilience(@RequestParam(defaultValue = "false") Boolean fail) {
        return apiClientService.testResilience(fail);
    }

    @GetMapping("/health")
    public String getHealth() {
        return apiClientService.getHealth();
    }

    @GetMapping("/test/all")
    public String testAllEndpoints() {
        StringBuilder result = new StringBuilder();

        result.append("=== Test du Client REST ===\n\n");

        // Test santé
        result.append("1. Santé: ").append(apiClientService.getHealth()).append("\n\n");

        // Test résilience
        result.append("2. Résilience (false): ").append(apiClientService.testResilience(false)).append("\n\n");

        // Test produit
        try {
            ProductCompositeResponse product = apiClientService.getProductComposite(1L);
            result.append("3. Produit composite: SUCCÈS\n");
            result.append("   - Nom: ").append(product.getName()).append("\n");
            result.append("   - Poids: ").append(product.getWeight()).append("kg\n");
            result.append("   - Reviews: ").append(product.getReviews().size()).append("\n");
            result.append("   - Recommendations: ").append(product.getRecommendations().size()).append("\n");
        } catch (Exception e) {
            result.append("3. Produit composite: ÉCHEC - ").append(e.getMessage()).append("\n");
        }

        return result.toString();
    }
}