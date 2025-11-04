package com.example.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

    private final WebClient webClient;

    public AuthorizationFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClient = webClientBuilder.build();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            System.out.println("🎯 AUTHORIZATION FILTER EXECUTED!");

            ServerHttpRequest request = exchange.getRequest();

            // Récupérer les headers
            String username = request.getHeaders().getFirst("username");
            String password = request.getHeaders().getFirst("password");
            String role = request.getHeaders().getFirst("role");

            System.out.println("Headers - Username: " + username + ", Role: " + role);

            // 🔒 BLOQUER si pas d'authentification
            if (username == null || password == null || role == null) {
                System.out.println("❌ Missing auth headers - 401");
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                // ⚠️ IMPORTANT: Ajouter un corps de réponse
                return exchange.getResponse().writeWith(
                        Mono.just(exchange.getResponse()
                                .bufferFactory()
                                .wrap("Unauthorized: Missing authentication headers".getBytes()))
                );
            }

            // Créer le body JSON pour l'auth service
            String requestBody = String.format(
                    "{\"username\":\"%s\", \"password\":\"%s\", \"role\":\"%s\"}",
                    username, password, role
            );

            System.out.println("🔄 Calling auth service: " + requestBody);

            // 🔐 APPEL RÉEL au service d'authentification
            return webClient.post()
                    .uri("http://localhost:8085/auth/validate-body") // ⚠️ URL DIRECTE d'abord
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .flatMap(isValid -> {
                        System.out.println("✅ Auth service response: " + isValid);
                        if (Boolean.TRUE.equals(isValid)) {
                            System.out.println("✅ Authentication SUCCESS");
                            return chain.filter(exchange);
                        } else {
                            System.out.println("❌ Authentication FAILED - 403");
                            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                            return exchange.getResponse().writeWith(
                                    Mono.just(exchange.getResponse()
                                            .bufferFactory()
                                            .wrap("Forbidden: Invalid credentials".getBytes()))
                            );
                        }
                    })
                    .onErrorResume(e -> {
                        System.out.println("💥 Auth service error: " + e.getMessage());
                        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                        return exchange.getResponse().writeWith(
                                Mono.just(exchange.getResponse()
                                        .bufferFactory()
                                        .wrap("Authentication service unavailable".getBytes()))
                        );
                    });
        };
    }

    public static class Config {
        // Configuration
    }
}