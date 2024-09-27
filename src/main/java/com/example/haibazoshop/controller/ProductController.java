package com.example.haibazoshop.controller;

import com.example.haibazoshop.dto.request.CreateProductRequest;
import com.example.haibazoshop.dto.request.UpdateProductRequest;
import com.example.haibazoshop.dto.response.ApiResponse;
import com.example.haibazoshop.dto.response.ProductResponse;
import com.example.haibazoshop.entity.Product;
import com.example.haibazoshop.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("${api.prefix}/products")
public class ProductController {
    ProductService productService;
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ProductResponse> updateProduct(@PathVariable Long id, @Valid @ModelAttribute UpdateProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .message("Product updated successfully")
                .result(productService.updateProduct(id, request))
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<Void> softDeleteProduct(@PathVariable Long id) {
        productService.softDeleteProduct(id);
        return ApiResponse.<Void>builder()
                .message("Product soft deleted successfully")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProductById(@PathVariable Long id) {
        return ApiResponse.<ProductResponse>builder()
                .message("Product retrieved successfully")
                .result(productService.getProductById(id))
                .build();
    }
    @GetMapping
    public ApiResponse<Page<ProductResponse>> getAllProducts(Pageable pageable) {
        return ApiResponse.<Page<ProductResponse>>builder()
                .message("Products retrieved successfully")
                .result(productService.getAllProducts(pageable))
                .build();
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ProductResponse> createProduct(@Valid @ModelAttribute CreateProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .message("Product created successfully")
                .result(productService.createProduct(request))
                .build();
    }

}
