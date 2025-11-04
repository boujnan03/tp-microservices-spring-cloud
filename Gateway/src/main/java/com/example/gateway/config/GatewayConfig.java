package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final AuthorizationFilter authorizationFilter;

    // Injection du filtre via le constructeur
    public GatewayConfig(AuthorizationFilter authorizationFilter) {
        this.authorizationFilter = authorizationFilter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Route pour le service d'autorisation
                .route("authorization_route", r -> r
                        .path("/auth/**")
                        .uri("lb://authorization-service"))

                // Route sécurisée pour product-composite
                .route("product_composite_secure", r -> r
                        .path("/api/composite/**")
                        .filters(f -> f
                                .filter(authorizationFilter.apply(new AuthorizationFilter.Config()))  // ← CORRECTION
                                .rewritePath("/api/composite/(?<segment>.*)", "/composites/${segment}"))
                        .uri("lb://product-composite"))

                // Routes pour les autres services (non sécurisées pour l'instant)
                .route("product_service", r -> r
                        .path("/products/**")
                        .uri("lb://product-service"))

                .route("recommendation_service", r -> r
                        .path("/recommendations/**")
                        .uri("lb://recommendation-service"))

                .route("review_service", r -> r
                        .path("/reviews/**")
                        .uri("lb://review-service"))
                .build();
    }
}