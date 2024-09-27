package com.example.haibazoshop.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateReviewRequest {
    @NotNull(message = "Product ID can't be null")
    Long productId;

    @NotNull(message = "Rating can't be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    Integer rating;

    @NotBlank(message = "Comment can't be empty")
    @Size(min = 3, message = "Comment must be at least 3 characters")
    String comment;
}