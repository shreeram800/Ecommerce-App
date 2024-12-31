package com.example.ecommerceapp.Entity;

import com.example.ecommerceapp.Entity.Enums.Gender;
import com.example.ecommerceapp.Entity.Enums.Size;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String brand;

    private BigDecimal price;

    private Integer inventory;

    private String description;

    private Gender gender;

    @Column(name = "discount_percentage")
    private int discountPercentage;


    @Column(name = "size")
    @ElementCollection
    private Set<Size> sizes = new HashSet<>();

    @Column(nullable = true)
    private String color;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private Category category;

    @Column(name = "discounted_price")
    private BigDecimal discountedPrice;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return discountPercentage == product.discountPercentage && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(brand, product.brand) && Objects.equals(price, product.price) && Objects.equals(inventory, product.inventory) && Objects.equals(description, product.description) && gender == product.gender && Objects.equals(sizes, product.sizes) && Objects.equals(color, product.color) && Objects.equals(imageUrl, product.imageUrl) && Objects.equals(reviews, product.reviews) && Objects.equals(category, product.category) && Objects.equals(images, product.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, price, inventory, description, gender, discountPercentage, sizes, color, imageUrl, reviews, category, images);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", inventory=" + inventory +
                ", description='" + description + '\'' +
                ", gender=" + gender +
                ", discountPercentage=" + discountPercentage +
                ", sizes=" + sizes +
                ", color='" + color + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", reviews=" + reviews +
                ", category=" + category +
                ", images=" + images +
                '}';
    }
}
