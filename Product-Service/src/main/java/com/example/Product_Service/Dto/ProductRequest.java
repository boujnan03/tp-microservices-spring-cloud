package com.example.Product_Service.Dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequest {
    private long id;
    private String name;

    @Min(value = 0, message = "Weight cannot be negative")
    @Max(value = 100, message = "Weight cannot exceed 100kg")
    private double weight;
}