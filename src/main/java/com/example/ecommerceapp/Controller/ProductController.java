package com.example.ecommerceapp.Controller;

import com.example.ecommerceapp.Entity.Enums.Size;
import com.example.ecommerceapp.Entity.Product;
import com.example.ecommerceapp.Exceptions.ProductNotFoundException;
import com.example.ecommerceapp.Service.ProductService;
import com.example.ecommerceapp.requests.AddProductRequest;
import com.example.ecommerceapp.requests.UpdateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ProductController {


    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public ResponseEntity<?> getProductByCategoryHandler(@RequestParam String category, @RequestParam List<String> color,
                                                         @RequestParam List<Size> size, @RequestParam Integer minPrice,
                                                         @RequestParam Integer maxPrice, @RequestParam Integer minDiscount,
                                                         @RequestParam String sort, @RequestParam String stock,
                                                         @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        Page<Product> res= productService.getAllProducts(
                category, color,size, minPrice,maxPrice,
                minDiscount,sort,stock,pageNumber,pageSize);

        System.out.println("Complete products");
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @GetMapping("/product/id/{productId}")
    public ResponseEntity<Product> findProductByHandler(@PathVariable Long productId)throws ProductNotFoundException{

        Product product = productService.getProductById(productId);

        return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
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
        return ResponseEntity.ok(productService.updateProduct(request, id));
    }


}
