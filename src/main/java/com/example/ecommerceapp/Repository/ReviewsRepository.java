package com.example.ecommerceapp.Repository;

import com.example.ecommerceapp.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Review,Long> {
    @Query("SELECT r FROM Review r WHERE r.product.id=:productId")
    List<Review> getAllByProduct(@Param("productId") Long productId);
}
