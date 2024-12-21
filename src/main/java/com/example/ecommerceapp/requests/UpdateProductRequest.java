package com.example.ecommerceapp.requests;

import com.example.ecommerceapp.Entity.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRequest {
    private long id;
    private  String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;

}
