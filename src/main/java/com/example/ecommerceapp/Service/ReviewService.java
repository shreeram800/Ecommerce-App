package com.example.ecommerceapp.Service;

import com.example.ecommerceapp.Entity.Review;
import com.example.ecommerceapp.Entity.User;
import com.example.ecommerceapp.requests.ReviewRequest;

import java.util.List;

public interface ReviewService {
     Review creatingReview(ReviewRequest request, User user);

     List<Review> getProductsReviews(Long productId);


}
