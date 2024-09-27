package com.example.haibazoshop.controller;

import com.example.haibazoshop.dto.request.CreateSizeRequest;
import com.example.haibazoshop.dto.request.UpdateSizeRequest;
import com.example.haibazoshop.dto.response.ApiResponse;
import com.example.haibazoshop.dto.response.SizeResponse;
import com.example.haibazoshop.service.SizeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/sizes")
public class SizeController {
    private final SizeService sizeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<SizeResponse> createSize(@Valid @RequestBody CreateSizeRequest request) {
        return ApiResponse.<SizeResponse>builder()
                .message("Size created successfully")
                .result(sizeService.createSize(request))
                .build();
    }

    @GetMapping
    public ApiResponse<Page<SizeResponse>> getAllSizes(Pageable pageable) {
        return ApiResponse.<Page<SizeResponse>>builder()
                .message("Sizes retrieved successfully")
                .result(sizeService.getAllSizes(pageable))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<SizeResponse> getSizeById(@PathVariable Long id) {
        return ApiResponse.<SizeResponse>builder()
                .message("Size retrieved successfully")
                .result(sizeService.getSizeById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<SizeResponse> updateSize(@PathVariable Long id, @Valid @RequestBody UpdateSizeRequest request) {
        return ApiResponse.<SizeResponse>builder()
                .message("Size updated successfully")
                .result(sizeService.updateSize(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> deleteSize(@PathVariable Long id) {
        sizeService.deleteSize(id);
        return ApiResponse.<Void>builder()
                .message("Size deleted successfully")
                .build();
    }
}