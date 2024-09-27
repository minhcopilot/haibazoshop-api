package com.example.haibazoshop.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    Long id;
    String name;
    String description;
    BigDecimal price;
    BigDecimal originalPrice;
    StyleResponse style;
    int views;
    CategoryResponse category;
    Set<ColorResponse> colors;
    Set<SizeResponse> sizes;
    List<ImageResponse> images;
}