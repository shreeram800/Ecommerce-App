package com.example.ecommerceapp.Service;
import com.example.ecommerceapp.Entity.Product;
import com.example.ecommerceapp.Exceptions.ProductNotFoundException;
import com.example.ecommerceapp.requests.AddProductRequest;
import com.example.ecommerceapp.requests.UpdateProductRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ProductService {

    Product addProduct(AddProductRequest product);

    ResponseEntity<?> getProductById(Long id) throws ProductNotFoundException;

    String deleteProductById(Long id);

    Product updateProductById(UpdateProductRequest product, Long id);

    ResponseEntity<?> getAllProducts();

    List<Product> getAllProductsByCategory(String category);

    Product getProductByName(String name);


}
