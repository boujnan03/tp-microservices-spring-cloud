package com.example.restclient.service;

import com.example.restclient.dto.ProductCompositeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiClientService {

    private final RestTemplate restTemplate;

    @Autowired
    public ApiClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductCompositeResponse getProductComposite(Long productId) {
        String url = "http://localhost:8070/composites/" + productId;
        try {
            return restTemplate.getForObject(url, ProductCompositeResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'appel à l'API: " + e.getMessage());
        }
    }

    public String testResilience(boolean shouldFail) {
        String url = "http://localhost:8070/composites/test/resilience?fail=" + shouldFail;
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return "Erreur: " + e.getMessage();
        }
    }

    public String getHealth() {
        String url = "http://localhost:8070/composites/health";
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return "Service indisponible: " + e.getMessage();
        }
    }
}