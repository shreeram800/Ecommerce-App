package com.example.ecommerceapp.requests;

import com.example.ecommerceapp.Entity.Category;
import com.example.ecommerceapp.Entity.Enums.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class UpdateProductRequest {

    private  String name;

    private String description;

    private String brand;

    private BigDecimal price;

    private int discount;

    private String color;

    private int quantity;

    private String ImageUrl;

    private int inventry;

    private Category topLevelCategory;

    private Category secondLevelCategory;

    private Category thirdLevelCategory;

    private BigDecimal discountedPrice;

    private Set<Size> Sizes;

}
