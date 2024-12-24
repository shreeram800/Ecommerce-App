package com.example.ecommerceapp.Controller;

import com.example.ecommerceapp.Entity.Product;
import com.example.ecommerceapp.Service.ProductService;
import com.example.ecommerceapp.requests.AddProductRequest;
import com.example.ecommerceapp.requests.UpdateProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody AddProductRequest request) {
        return ResponseEntity.ok(productService.addProduct(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductById(@RequestBody UpdateProductRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(productService.updateProductById(request, id));
    }

    @GetMapping("/category/{name}")
    public ResponseEntity<?> getProductByCategory(@PathVariable String name) {
            return ResponseEntity.ok(productService.getAllProductsByCategory(name));
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getProductByName(@PathVariable String name) {
        Product product = productService.getProductByName(name);

        return ResponseEntity.ok(product);

    }
}
