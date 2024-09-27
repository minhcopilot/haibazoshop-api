package com.example.haibazoshop.controller;

import com.example.haibazoshop.dto.request.CreateCategoryRequest;
import com.example.haibazoshop.dto.request.UpdateCategoryRequest;
import com.example.haibazoshop.dto.response.ApiResponse;
import com.example.haibazoshop.dto.response.CategoryResponse;
import com.example.haibazoshop.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .message("Category created successfully")
                .result(categoryService.createCategory(request))
                .build();
    }

    @GetMapping
    public ApiResponse<Page<CategoryResponse>> getAllCategories(Pageable pageable) {
        return ApiResponse.<Page<CategoryResponse>>builder()
                .message("Categories retrieved successfully")
                .result(categoryService.getAllCategories(pageable))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return ApiResponse.<CategoryResponse>builder()
                .message("Category retrieved successfully")
                .result(categoryService.getCategoryById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CategoryResponse> updateCategory(@PathVariable Long id, @Valid @RequestBody UpdateCategoryRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .message("Category updated successfully")
                .result(categoryService.updateCategory(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ApiResponse.<Void>builder()
                .message("Category deleted successfully")
                .build();
    }
}