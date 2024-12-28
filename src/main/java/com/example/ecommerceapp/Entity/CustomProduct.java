package com.example.ecommerceapp.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CustomProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer quantity;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "original_product")
    private Product originalProduct;
    public CustomProduct(String name, String description, Double price, Integer quantity, String imageUrl, Product originalProduct) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.originalProduct = originalProduct;
    }

}
