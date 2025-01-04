package com.example.ecommerceapp.Service.implimentations;

import com.example.ecommerceapp.Entity.Product;
import com.example.ecommerceapp.Entity.Review;
import com.example.ecommerceapp.Entity.User;
import com.example.ecommerceapp.Repository.ReviewsRepository;
import com.example.ecommerceapp.Service.ProductService;
import com.example.ecommerceapp.Service.ReviewService;
import com.example.ecommerceapp.requests.ReviewRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewsServiceImp implements ReviewService {

    private ReviewsRepository reviewsRepository;
    private ProductService productService;

    public ReviewsServiceImp(ReviewsRepository reviewsRepository, ProductService productService) {
        this.reviewsRepository = reviewsRepository;
        this.productService= productService;
    }

    @Override
    public Review creatingReview(ReviewRequest request, User user) {
        Product product = productService.getProductById(request.getProductId().getId());
        Review review = new Review();
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setCreatedAt(LocalDateTime.now());
        review.setProduct(product);
        return reviewsRepository.save(review);
    }

    @Override
    public List<Review> getProductsReviews(Long productId) {

        return reviewsRepository.getAllByProduct(productId);

    }
}
