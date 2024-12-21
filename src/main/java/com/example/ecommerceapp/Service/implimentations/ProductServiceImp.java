package com.example.ecommerceapp.Service.implimentations;
import com.example.ecommerceapp.Entity.Category;
import com.example.ecommerceapp.Entity.Product;
import com.example.ecommerceapp.Exceptions.ProductNotFoundException;
import com.example.ecommerceapp.Repository.CategoryRepo;
import com.example.ecommerceapp.Repository.ProductRepo;
import com.example.ecommerceapp.Service.ProductService;
import com.example.ecommerceapp.requests.AddProductRequest;
import com.example.ecommerceapp.requests.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    
    private final ProductRepo productRpo;
    private final CategoryRepo categoryRepo;

    @Override
    public Product addProduct(AddProductRequest product) {
        Category category= Optional.ofNullable(categoryRepo.findByName(product.getCategory()))
                .orElseGet(()->{
                    Category newCategory = new Category(product.getCategory());
                    return categoryRepo.save(newCategory);
                });
        return productRpo.save(createProduct(product,category));
    }

    private Product createProduct(AddProductRequest request, Category category){
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }

    @Override
    public ResponseEntity<?> getProductById(Long id) {
        Product product = productRpo.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "product by Id "+ id + " Not found" ));
        return ResponseEntity.ok(product);
    }

    @Override
    public String deleteProductById(Long id) {
        Product product = productRpo.findById(id).orElse(null);
        if(product==null){
            return "Product by Id "+ id + " does not found...!!";
        }
        else
        {
            productRpo.deleteById(product.getId());
        }
        return "Product Deleted Successfully..!!!";
    }

    @Override
    public Product updateProductById(UpdateProductRequest request , Long id) {
        return productRpo.findById(id).map(existingProduct -> updateExistingProduct(existingProduct,request))
                .map(productRpo::save)
                .orElseThrow(()-> new ProductNotFoundException(HttpStatus.NOT_FOUND,"Product does not found"));
    }

    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest request){
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        Category category = categoryRepo.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }

    @Override
    public ResponseEntity<?> getAllProducts() {
        List<?> products = productRpo.findAll();
        if(products.isEmpty()) {
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok(products);
    }

    @Override
    public List<Product> getAllProductsByCategory(String category) {
        return productRpo.findAllByCategoryName(category);
    }

    @Override
    public List<Product> getAllProductByBrand(String brand) {
        return productRpo.findAllByBrand(brand);
    }

    @Override
    public List<Product> getAllProductByBrandAndCategory(String brand, String category) {
        return productRpo.findAllByCategoryNameAndBrand(category,brand);
    }

    @Override
    public Product getProductByName(String name) {
        return productRpo.findByName(name);
    }

    @Override
    public Product getProductByNameAndBrand(String name, String brand) {
        return productRpo.findByNameAndBrand(name, brand);
    }

    @Override
    public Long countProductByNameAndBrand(String name, String brand) {
        return productRpo.countByNameAndBrand(name,brand);

    }
}
