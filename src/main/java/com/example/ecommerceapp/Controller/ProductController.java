package com.example.ecommerceapp.Controller;

import com.example.ecommerceapp.Service.ProductService;
import com.example.ecommerceapp.requests.AddProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        return  productService.getProductById(id);
    }
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody  AddProductRequest request){
        return  ResponseEntity.ok(productService.addProduct(request));
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable  Long id){
        return ResponseEntity.ok(productService.deleteProductById(id));
    }


}
