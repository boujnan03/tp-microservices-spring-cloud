package com.example.Product_Service.Dto;

import com.example.Product_Service.Entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private long id;
    private String name;
    private double weight;


    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.weight = product.getWeight();
    }
}
