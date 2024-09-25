package com.example.ecommerceapp.Repository;


import com.example.ecommerceapp.Entity.Category;
import com.example.ecommerceapp.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryName(String category);
}
