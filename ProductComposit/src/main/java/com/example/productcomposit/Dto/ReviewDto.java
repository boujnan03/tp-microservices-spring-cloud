package com.example.productcomposit.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewDto {
    private Long productId;
    private String author;
    private String subject;
    private String content;
}
