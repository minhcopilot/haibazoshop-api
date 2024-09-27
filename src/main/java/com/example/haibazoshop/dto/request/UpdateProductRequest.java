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
public class UpdateProductRequest {
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
            @NotBlank(message = "Name can't be empty")
    String name;

    @NotBlank(message = "Description can't be empty")
    @Size(min = 3, message = "Description must be at least 3 characters")
    String description;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    BigDecimal price;

    @NotBlank(message = "Style can't be empty")
    @Size(min = 3, message = "Style must be at least 3 characters")
    String style;

    @NotEmpty(message = "Images can't be empty")
    List<MultipartFile> newImages;
    @NotNull(message = "Category ID can't be null")
    Long categoryId;
    @NotEmpty(message = "Color IDs can't be empty")
    Set<Long> colorIds;
    @NotEmpty(message = "Size IDs can't be empty")
    Set<Long> sizeIds;
}