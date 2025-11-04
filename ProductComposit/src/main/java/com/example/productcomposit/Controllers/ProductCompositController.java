package com.example.productcomposit.Controllers;

import com.example.productcomposit.Dto.ProductCompositDto;
import com.example.productcomposit.Services.ProductCompositServiceImpl;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/composites")
public class ProductCompositController {

    @Autowired
    private ProductCompositServiceImpl productCompositService;

    // Compteurs pour les métriques
    private final Counter getRequestCounter;
    private final Counter testRequestCounter;
    private final Counter postPutRequestCounter;

    @Autowired
    public ProductCompositController(MeterRegistry meterRegistry) {
        // Compteur pour les requêtes GET
        this.getRequestCounter = Counter.builder("http.requests")
                .tag("method", "GET")
                .tag("endpoint", "/composites")
                .description("Nombre total de requêtes GET")
                .register(meterRegistry);

        // Compteur pour les requêtes TEST
        this.testRequestCounter = Counter.builder("http.requests")
                .tag("method", "TEST")
                .tag("endpoint", "/composites/test")
                .description("Nombre total de requêtes TEST")
                .register(meterRegistry);

        // Compteur pour les requêtes POST/PUT (pour l'avenir)
        this.postPutRequestCounter = Counter.builder("http.requests")
                .tag("method", "POST_PUT")
                .tag("endpoint", "/composites")
                .description("Nombre total de requêtes POST/PUT")
                .register(meterRegistry);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductCompositDto> getProductComposite(@PathVariable("productId") long id) {
        getRequestCounter.increment(); // ← Incrémente le compteur GET
        ProductCompositDto productCompositDto = productCompositService.getAllProducts(id);
        return ResponseEntity.ok(productCompositDto);
    }

    @GetMapping("/test/resilience")
    public String testResilience(@RequestParam(defaultValue = "false") boolean fail) {
        testRequestCounter.increment(); // ← Incrémente le compteur TEST
        return productCompositService.testResilience(fail);
    }

    @GetMapping("/health")
    public String healthCheck() {
        getRequestCounter.increment(); // ← Incrémente le compteur GET
        return "ProductComposite Service is UP - Circuit Breaker status available at /actuator/health";
    }

    @GetMapping("/test/circuit-breaker")
    public String testCircuitBreaker() {
        getRequestCounter.increment(); // ← Incrémente le compteur GET
        // Simuler plusieurs échecs pour ouvrir le circuit breaker
        for (int i = 0; i < 15; i++) {
            try {
                productCompositService.testResilience(true);
            } catch (Exception e) {
                // Ignorer les exceptions
            }
        }
        return "Circuit Breaker test activé - Vérifiez l'état dans /actuator/health";
    }
}