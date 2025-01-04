package com.example.ecommerceapp.requests;

import com.example.ecommerceapp.Entity.Product;
import lombok.Data;

@Data
public class ReviewRequest {

    private String comment;

    private int rating;

    private Product productId;

    private Long userId;

}

