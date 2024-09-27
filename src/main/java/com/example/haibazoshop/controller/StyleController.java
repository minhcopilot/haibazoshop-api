package com.example.haibazoshop.controller;

import com.example.haibazoshop.dto.request.CreateStyleRequest;
import com.example.haibazoshop.dto.response.ApiResponse;
import com.example.haibazoshop.dto.response.StyleResponse;
import com.example.haibazoshop.service.StyleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/styles")
public class StyleController {
    private final StyleService styleService;

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteStyle(@PathVariable Long id) {
        styleService.deleteStyle(id);
        return ApiResponse.<Void>builder()
                .message("Style deleted successfully")
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<StyleResponse> updateStyle(@PathVariable Long id, @Valid @RequestBody CreateStyleRequest request) {
        return ApiResponse.<StyleResponse>builder()
                .message("Style updated successfully")
                .result(styleService.updateStyle(id, request))
                .build();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<StyleResponse> createStyle(@Valid @RequestBody CreateStyleRequest request) {
        return ApiResponse.<StyleResponse>builder()
                .message("Style created successfully")
                .result(styleService.createStyle(request))
                .build();
    }

    @GetMapping
    public ApiResponse<Page<StyleResponse>> getAllStyles(Pageable pageable) {
        return ApiResponse.<Page<StyleResponse>>builder()
                .message("Styles retrieved successfully")
                .result(styleService.getAllStyles(pageable))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<StyleResponse> getStyleById(@PathVariable Long id) {
        return ApiResponse.<StyleResponse>builder()
                .message("Style retrieved successfully")
                .result(styleService.getStyleById(id))
                .build();
    }
}