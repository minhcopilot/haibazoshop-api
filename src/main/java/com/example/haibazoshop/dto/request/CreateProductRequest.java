package com.example.haibazoshop.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateProductRequest {
    @NotBlank(message = "Name can't be empty")
            @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    String name;

    @NotBlank(message = "Description can't be empty")
            @Size(min = 3, message = "Description must be at least 3 characters")
    String description;

    @NotNull(message = "Price can't be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    BigDecimal price;
    @NotNull(message = "originalPrice can't be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "originalPrice must be greater than zero")
    BigDecimal originalPrice;

    @NotNull(message = "Style ID can't be null")
    Long styleId;
    @NotEmpty(message = "Images can't be empty")
    List<MultipartFile> images;

    @NotNull(message = "Category ID can't be null")
    Long categoryId;

    @NotEmpty(message = "Color IDs can't be empty")
    Set<Long> colorIds;

    @NotEmpty(message = "Size IDs can't be empty")
    Set<Long> sizeIds;
}