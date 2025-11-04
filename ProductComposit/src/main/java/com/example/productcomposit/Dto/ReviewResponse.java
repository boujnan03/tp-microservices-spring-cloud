package com.example.productcomposit.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponse {
    private long ReviewId;
    private String Author;
    private String Subject;
    private String Content;
}
