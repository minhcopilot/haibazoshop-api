package com.example.haibazoshop.controller;

import com.example.haibazoshop.dto.request.CreateReviewRequest;
import com.example.haibazoshop.dto.request.UpdateReviewRequest;
import com.example.haibazoshop.dto.response.ApiResponse;
import com.example.haibazoshop.dto.response.ReviewResponse;
import com.example.haibazoshop.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ReviewResponse> createReview(@Valid @RequestBody CreateReviewRequest request) {
        return ApiResponse.<ReviewResponse>builder()
                .message("Review created successfully")
                .result(reviewService.createReview(request))
                .build();
    }

    @GetMapping("/product/{productId}")
    public ApiResponse<Page<ReviewResponse>> getAllReviewsByProductId(
            @PathVariable Long productId,
            Pageable pageable
    ) {
        return ApiResponse.<Page<ReviewResponse>>builder()
                .message("Reviews retrieved successfully")
                .result(reviewService.getAllReviewsByProductId(productId, pageable))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ReviewResponse> getReviewById(@PathVariable Long id) {
        return ApiResponse.<ReviewResponse>builder()
                .message("Review retrieved successfully")
                .result(reviewService.getReviewById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ReviewResponse> updateReview(
            @PathVariable Long id,
            @Valid @RequestBody UpdateReviewRequest request
    ) {
        return ApiResponse.<ReviewResponse>builder()
                .message("Review updated successfully")
                .result(reviewService.updateReview(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ApiResponse.<Void>builder()
                .message("Review deleted successfully")
                .build();
    }
}