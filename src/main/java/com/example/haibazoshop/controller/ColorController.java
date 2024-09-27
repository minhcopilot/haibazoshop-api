package com.example.haibazoshop.controller;

import com.example.haibazoshop.dto.request.CreateColorRequest;
import com.example.haibazoshop.dto.request.UpdateColorRequest;
import com.example.haibazoshop.dto.response.ApiResponse;
import com.example.haibazoshop.dto.response.ColorResponse;
import com.example.haibazoshop.service.ColorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/colors")
public class ColorController {
    private final ColorService colorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ColorResponse> createColor(@Valid @RequestBody CreateColorRequest request) {
        return ApiResponse.<ColorResponse>builder()
                .message("Color created successfully")
                .result(colorService.createColor(request))
                .build();
    }

    @GetMapping
    public ApiResponse<Page<ColorResponse>> getAllColors(Pageable pageable) {
        return ApiResponse.<Page<ColorResponse>>builder()
                .message("Colors retrieved successfully")
                .result(colorService.getAllColors(pageable))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ColorResponse> getColorById(@PathVariable Long id) {
        return ApiResponse.<ColorResponse>builder()
                .message("Color retrieved successfully")
                .result(colorService.getColorById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ColorResponse> updateColor(@PathVariable Long id, @Valid @RequestBody UpdateColorRequest request) {
        return ApiResponse.<ColorResponse>builder()
                .message("Color updated successfully")
                .result(colorService.updateColor(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> deleteColor(@PathVariable Long id) {
        colorService.deleteColor(id);
        return ApiResponse.<Void>builder()
                .message("Color deleted successfully")
                .build();
    }
}