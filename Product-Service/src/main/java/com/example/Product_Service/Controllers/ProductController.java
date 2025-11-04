package com.example.Product_Service.Controllers;

import com.example.Product_Service.Dto.ProductRequest;
import com.example.Product_Service.Dto.ProductResponse;
import com.example.Product_Service.Services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductResponse addProduct(@Valid @RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

    // for get all the products
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    // for get one product
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable long id) {
        ProductResponse productResponse = productService.getProduct(id);
        return ResponseEntity.ok().body(productResponse);
    }

    // for delete a product
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }

    // for update a product
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable long id, @Valid @RequestBody ProductRequest productRequest) {
        productService.updateProduct(id, productRequest);
    }
}