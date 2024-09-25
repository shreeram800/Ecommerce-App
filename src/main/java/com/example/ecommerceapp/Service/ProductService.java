package com.example.ecommerceapp.Service;

import com.example.ecommerceapp.Entity.Category;
import com.example.ecommerceapp.Entity.Product;
import com.example.ecommerceapp.Exceptions.ProductNotFoundException;

import java.util.List;


public interface ProductService {


    void addProduct(Product product);
    Product getProductById(Long id) throws ProductNotFoundException;

    void deleteProductById(Long id);

    void updateProductById(Product product, Long id);

    List<Product> getAllProducts();
    List<Product> getAllProductsByCategory(String category);

    List<Product> getAllProductByBrand(String Brand);

    List<Product> getAllProductByBrandAndCategory(String brand, Category category);

    Product getProductByName(String name);

    Product getProductByNameAndBrand(String name,String Brand);

    Long countProductByNameAndBrand(String name, String brand);


}
