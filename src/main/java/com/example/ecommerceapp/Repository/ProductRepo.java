package com.example.ecommerceapp.Repository;
import com.example.ecommerceapp.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryName(String category);

    List<Product> findAllByBrand(String brand);

    List<Product> findAllByCategoryNameAndBrand(String category, String brand);

    Product findByName(String name);

    @Query("SELECT p FROM Product p " +
            "WHERE (:category IS NULL OR p.category = :category) " +
            "AND (:minPrice IS NULL OR :maxPrice IS NULL OR p.price BETWEEN :minPrice AND :maxPrice) " +
            "AND (:minDiscount IS NULL OR p.price >= :minDiscount) " +
            "ORDER BY " +
            "CASE WHEN :sort = 'price_low' THEN p.price END ASC, " +
            "CASE WHEN :sort = 'price_high' THEN p.price END DESC")
    List<Product> findAllProduct(
            @Param("category") String category,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice,
            @Param("minDiscount") Integer minDiscount,
            @Param("sort") String sort);



    Product findByNameAndBrand(String name, String brand);

    Long countByNameAndBrand(String name, String brand);
}
