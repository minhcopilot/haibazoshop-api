package com.example.haibazoshop.controller;

import com.example.haibazoshop.dto.request.CreateProductRequest;
import com.example.haibazoshop.dto.response.ApiResponse;
import com.example.haibazoshop.entity.Product;
import com.example.haibazoshop.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("${api.prefix}/products")
public class ProductController {
    ProductService productService;
    @PostMapping
    ApiResponse<Product> createProduct(@Valid @RequestBody CreateProductRequest request){
        return ApiResponse.<Product>builder()
                .message("Product created successfully")
                .result(productService.createProduct(request))
                .build();
    }
}
