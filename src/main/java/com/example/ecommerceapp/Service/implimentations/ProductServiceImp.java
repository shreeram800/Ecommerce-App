package com.example.ecommerceapp.Service.implimentations;
import com.example.ecommerceapp.Entity.Category;
import com.example.ecommerceapp.Entity.Product;
import com.example.ecommerceapp.Exceptions.ProductNotFoundException;
import com.example.ecommerceapp.Repository.CategoryRepo;
import com.example.ecommerceapp.Repository.ProductRepo;
import com.example.ecommerceapp.Service.ProductService;
import com.example.ecommerceapp.requests.AddProductRequest;
import com.example.ecommerceapp.requests.UpdateProductRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final CategoryRepo categoryRepo;
    
    private final ProductRepo productRepo;

    @Override
    public Product addProduct(AddProductRequest product) {
        Category category= Optional.ofNullable(categoryRepo.findByName(product.getCategory()))
                .orElseGet(()->{
                    Category newCategory = new Category(product.getCategory());
                    return categoryRepo.save(newCategory);
                });
        return productRepo.save(createProduct(product,category));
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

        Product product = productRepo.findById(id)
                .orElseThrow(()->new ProductNotFoundException("product by Id "+ id + " Not found" ));

        return ResponseEntity.ok(product);
    }

    @Transactional
    @Override
    public String deleteProductById(Long id) {

        if (!productRepo.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }

        productRepo.deleteById(id);

        return "Product with ID " + id + " deleted successfully";
    }

    @Transactional
    @Override
    public Product updateProductById(UpdateProductRequest request, Long id) {
        validateProductExists(id);

        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));

        return updateProductFields(existingProduct, request);
    }

    private void validateProductExists(Long id) {
        if (!productRepo.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }
    }

    private Product updateProductFields(Product existingProduct, UpdateProductRequest request) {
        if(request.getName() != null){
            existingProduct.setName(request.getName());
        }

        if(request.getBrand() != null){
            existingProduct.setBrand(request.getBrand());
        }

        if(request.getCategory() != null){
            existingProduct.setDescription(request.getDescription());
        }
        if(request.getDescription() != null){
            existingProduct.setPrice(request.getPrice());
        }
        if(request.getPrice() != null){
            existingProduct.setInventory(request.getInventory());
        }

        Category category = null;
        if(request.getCategory() != null){
            category = fetchOrCreateCategoryByName(request.getCategory().getName());
        }
        existingProduct.setCategory(category);
        return existingProduct;
    }

    private Category fetchOrCreateCategoryByName(String categoryName) {

        return Optional.ofNullable(categoryRepo.findByName(categoryName))
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(categoryName);
                    return categoryRepo.save(newCategory);
                });
    }
    @Override
    public ResponseEntity<?> getAllProducts() {
        List<?> products = productRepo.findAll();

        if(products.isEmpty()) {
            ResponseEntity.noContent();
        }

        return ResponseEntity.ok(products);
    }

    public List<Product> getAllProductsByCategory(String category) {
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        return productRepo.findAllByCategoryName(category);
    }
    public List<Product> getAllProductByBrand(String brand) {
        if (brand == null || brand.isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be null or empty");
        }
        return productRepo.findAllByBrand(brand);
    }

    public List<Product> getAllProductByBrandAndCategory(String brand, String category) {
        if ((brand == null || brand.isEmpty()) && (category == null || category.isEmpty())) {
            throw new IllegalArgumentException("Both brand and category cannot be null or empty");
        }

        return productRepo.findAllByCategoryNameAndBrand(category, brand);
    }

    public Product getProductByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }

        Product product = productRepo.findByName(name);
        if (product == null) {
            throw new EntityNotFoundException("Product with name " + name + " not found");
        }
        return product;
    }


    public Product getProductByNameAndBrand(String name, String brand) {
        if ((name == null || name.isEmpty()) || (brand == null || brand.isEmpty())) {
            throw new IllegalArgumentException("Product name and brand cannot be null or empty");
        }

        Product product = productRepo.findByNameAndBrand(name, brand);
        if (product == null) {
            throw new EntityNotFoundException("Product with name " + name + " and brand " + brand + " not found");
        }
        return product;
    }

    public Long countProductByNameAndBrand(String name, String brand) {
        if ((name == null || name.isEmpty()) || (brand == null || brand.isEmpty())) {
            throw new IllegalArgumentException("Product name and brand cannot be null or empty");
        }
        return productRepo.countByNameAndBrand(name, brand);
    }
}
