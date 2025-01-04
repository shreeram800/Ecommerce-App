package com.example.ecommerceapp.Service.implimentations;

import com.example.ecommerceapp.Entity.Category;
import com.example.ecommerceapp.Entity.Enums.Size;
import com.example.ecommerceapp.Entity.Product;
import com.example.ecommerceapp.Exceptions.ProductNotFoundException;
import com.example.ecommerceapp.Repository.CategoryRepo;
import com.example.ecommerceapp.Repository.ProductRepo;
import com.example.ecommerceapp.Service.ProductService;
import com.example.ecommerceapp.requests.AddProductRequest;
import com.example.ecommerceapp.requests.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final CategoryRepo categoryRepo;

    private final ProductRepo productRepo;

    @Override
    public Product addProduct(AddProductRequest product) {
        Category topLevel = categoryRepo.findByName(product.getTopLevelCategory());

        if (topLevel != null) {
            Category topLevelCategory = new Category();
            topLevelCategory.setName(product.getTopLevelCategory());
            topLevelCategory.setLevel(1);

            topLevel = categoryRepo.save(topLevelCategory);

        }
        Category secondLevel = categoryRepo.findByNameAndParentCategory(product.getSecondLevelCategory(), topLevel.getName());
        if (secondLevel == null) {
            Category secondLevelCategory = new Category();
            secondLevelCategory.setName(product.getSecondLevelCategory());
            secondLevelCategory.setParentCategory(topLevel);
            secondLevelCategory.setLevel(2);

            secondLevelCategory = categoryRepo.save(secondLevelCategory);
        }
        Category thirdLevel = categoryRepo.findByNameAndParentCategory(product.getThirdLevelCategory(), secondLevel.getName());
        if (thirdLevel == null) {
            Category thirdLevelCategory = new Category();
            thirdLevelCategory.setName(product.getSecondLevelCategory());
            thirdLevelCategory.setParentCategory(secondLevel);
            thirdLevelCategory.setLevel(3);

            thirdLevelCategory = categoryRepo.save(thirdLevelCategory);
        }

        Product product1 = new Product();
        product1.setName(product.getName());
        product1.setColor(product.getColor());
        product1.setDescription(product.getDescription());
        product1.setPrice(product.getPrice());
        product1.setImageUrl(product.getImageUrl());
        product1.setBrand(product.getBrand());
        product1.setDiscountedPrice(product.getDiscountedPrice());
        product1.setSizes(product.getSizes());
        product1.setInventory(product.getInventry());
        product1.setCategory(thirdLevel);
        product1.setCreatedAt(LocalDateTime.now());

        return productRepo.save(product1);
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {

        return productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product by Id " + id + " Not found"));
    }


    @Transactional
    @Override
    public String deleteProductById(Long id) throws ProductNotFoundException {
        Product product = getProductById(id);
        product.getSizes().clear();
        productRepo.deleteById(id);
        return "Product with ID " + id + " deleted successfully";
    }

    @Transactional
    @Override
    public Product updateProduct(UpdateProductRequest request, Long id) throws ProductNotFoundException {
        validateProductExists(id);

        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        if (request.getQuantity() != 0) {
            existingProduct.setInventory(request.getQuantity());
        }
        return existingProduct;
    }

    @Override
    public Page<Product> getAllProducts(String category, List<String> colors, List<Size> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<Product> products = productRepo.findAllProduct(category, minPrice, maxPrice, minDiscount, sort);

        if (!colors.isEmpty()) {
            products = products.stream().filter(p -> colors.stream()
                            .anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
                    .toList();
        }
        if (stock != null) {
            if (stock.equals("in_Stock")) {
                products = products.stream().filter(p -> p.getInventory() > 0).collect(Collectors.toList());
            } else if (stock.equals("out_of_stock")) {
                products = products.stream().filter(p -> p.getInventory() < 1).collect(Collectors.toList());

            }
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());
        List<Product> pageContent = products.subList(startIndex, endIndex);

        return new PageImpl<>(pageContent, pageable, products.size());
    }
    private void validateProductExists(Long id) {
        if (!productRepo.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }
    }
}
