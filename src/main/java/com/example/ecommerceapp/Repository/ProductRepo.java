package com.example.ecommerceapp.Repository;
import com.example.ecommerceapp.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryName(String category);

    List<Product> findAllByBrand(String brand);

    List<Product> findAllByCategoryNameAndBrand(String category, String brand);

    Product findByName(String name);

    Product findByNameAndBrand(String name, String brand);

    Long countByNameAndBrand(String name, String brand);
}
