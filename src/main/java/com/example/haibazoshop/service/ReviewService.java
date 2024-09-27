package com.example.haibazoshop.service;

import com.example.haibazoshop.dto.request.CreateReviewRequest;
import com.example.haibazoshop.dto.request.UpdateReviewRequest;
import com.example.haibazoshop.dto.response.ReviewResponse;
import com.example.haibazoshop.entity.Product;
import com.example.haibazoshop.entity.Review;
import com.example.haibazoshop.exception.AppException;
import com.example.haibazoshop.exception.ErrorCode;
import com.example.haibazoshop.repository.ProductRepository;
import com.example.haibazoshop.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ReviewResponse createReview(CreateReviewRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        Review review = Review.builder()
                .product(product)
                .rating(request.getRating())
                .comment(request.getComment())
                .datePosted(LocalDateTime.now())
                .build();

        review = reviewRepository.save(review);
        return mapToReviewResponse(review);
    }

    @Transactional(readOnly = true)
    public Page<ReviewResponse> getAllReviewsByProductId(Long productId, Pageable pageable) {
        return reviewRepository.findAllByProductId(productId, pageable)
                .map(this::mapToReviewResponse);
    }

    @Transactional(readOnly = true)
    public ReviewResponse getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND));
        return mapToReviewResponse(review);
    }

    @Transactional
    public ReviewResponse updateReview(Long id, UpdateReviewRequest request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND));

        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review = reviewRepository.save(review);
        return mapToReviewResponse(review);
    }

    @Transactional
    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new AppException(ErrorCode.REVIEW_NOT_FOUND);
        }
        reviewRepository.deleteById(id);
    }

    private ReviewResponse mapToReviewResponse(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .productId(review.getProduct().getId())
                .rating(review.getRating())
                .comment(review.getComment())
                .datePosted(review.getDatePosted())
                .build();
    }
}