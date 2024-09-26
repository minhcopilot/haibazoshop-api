package com.example.haibazoshop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateProductRequest {
    @NotBlank(message = "Name can't be empty")
    String name;
    String description;
    BigDecimal price;
    String style;
    Long categoryId;
    Set<Long> colorIds;
    Set<Long> sizeIds;
}
