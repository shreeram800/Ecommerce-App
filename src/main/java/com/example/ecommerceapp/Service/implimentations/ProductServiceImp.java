package com.example.ecommerceapp.Service.implimentations;

import com.example.ecommerceapp.Entity.Category;
import com.example.ecommerceapp.Entity.Product;
import com.example.ecommerceapp.Exceptions.ProductNotFoundException;
import com.example.ecommerceapp.Repository.ProductRepo;
import com.example.ecommerceapp.Service.ProductService;

import java.util.List;

public class ProductServiceImp implements ProductService {
    private  ProductRepo productRpo;
    @Override
    public void addProduct(Product product) {

    }

    @Override
    public Product getProductById(Long id) {
        return productRpo.findById(id).orElseThrow(()->
                new ProductNotFoundException("Product Not Found By this ID"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRpo.findById(id).ifPresentOrElse(productRpo::delete,()->{
            throw new ProductNotFoundException("Product by this id doesn't exists..!!");});

    }

    @Override
    public void updateProductById(Product product, Long id) {

    }

    @Override
    public List<Product> getAllProducts() {
        return productRpo.findAll();
    }

    @Override
    public List<Product> getAllProductsByCategory(String category) {
        return productRpo.findAllByCategoryName(category);
    }

    @Override
    public List<Product> getAllProductByBrand(String Brand) {
        return null;
    }

    @Override
    public List<Product> getAllProductByBrandAndCategory(String brand, Category category) {
        return null;
    }

    @Override
    public Product getProductByName(String name) {
        return null;
    }

    @Override
    public Product getProductByNameAndBrand(String name, String Brand) {
        return null;
    }

    @Override
    public Long countProductByNameAndBrand(String name, String brand) {
        return null;
    }
}
