package com.example.Review_Service.Dao;

import com.example.Review_Service.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findReviewByProductId(Long id);
}
