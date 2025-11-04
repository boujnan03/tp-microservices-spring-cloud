package com.example.Recommendation_Service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "Recommendation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recommendationId;
    private long productId;

    @Column
    private String author;

    @Column
    @Min(value = 0, message = "Rate cannot be negative")
    @Max(value = 100, message = "Rate cannot exceed 100%")
    private int rate;

    @Column
    private String content;
}