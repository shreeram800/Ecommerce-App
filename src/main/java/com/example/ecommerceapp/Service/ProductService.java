package com.example.ecommerceapp.Service;
import com.example.ecommerceapp.Entity.Enums.Size;
import com.example.ecommerceapp.Entity.Product;
import com.example.ecommerceapp.Exceptions.ProductNotFoundException;
import com.example.ecommerceapp.requests.AddProductRequest;
import com.example.ecommerceapp.requests.UpdateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ProductService {

     public Product addProduct(AddProductRequest product);

    public Product getProductById(Long id) throws ProductNotFoundException;

    public String deleteProductById(Long id) throws ProductNotFoundException;

    public  Product updateProduct(UpdateProductRequest product, Long id) throws ProductNotFoundException;

    public Page<Product> getAllProducts(String category, List<String> colors, List<Size> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);


}
