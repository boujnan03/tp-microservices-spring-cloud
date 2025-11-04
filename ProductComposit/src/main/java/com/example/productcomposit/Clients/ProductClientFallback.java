package com.example.productcomposit.Clients;

import com.example.productcomposit.Dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class ProductClientFallback implements ProductClient {

    public ProductDto addProduct (ProductDto productDto){
        return new ProductDto();
    };


    public ProductDto getProductById(Long productId){
        return new ProductDto();
    };

    public List<ProductDto> getAllProducts(){
        return List.of(new ProductDto());
    };

    public void deleteProduct(Long productId){

    };

}
