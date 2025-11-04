package com.example.Review_Service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ReviewId;
    private long productId;
    private String Author;
    private String Subject;
    private String Content;
}

