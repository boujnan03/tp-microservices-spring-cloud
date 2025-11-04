package com.example.productcomposit.Clients;

import com.example.productcomposit.Dto.ProductDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Component
@FeignClient(name = "PRODUCT-SERVICE", fallback = ProductClientFallback.class)
public interface ProductClient {


    @PostMapping("/products")
    ProductDto addProduct (@RequestBody ProductDto productDto);


    @GetMapping("/products/{id}")
    ProductDto getProductById(@PathVariable("id") Long productId);

    @GetMapping("/products")
    List<ProductDto> getAllProducts();

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable("id") Long productId);

}
