package com.example.haibazoshop.service;

import com.example.haibazoshop.dto.request.CreateProductRequest;
import com.example.haibazoshop.entity.Category;
import com.example.haibazoshop.entity.Color;
import com.example.haibazoshop.entity.Product;
import com.example.haibazoshop.entity.Size;
import com.example.haibazoshop.exception.AppException;
import com.example.haibazoshop.exception.ErrorCode;
import com.example.haibazoshop.repository.CategoryRepository;
import com.example.haibazoshop.repository.ColorRepository;
import com.example.haibazoshop.repository.ProductRepository;
import com.example.haibazoshop.repository.SizeRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService{
    ProductRepository productRepository;
    SizeRepository sizeRepository;
    ColorRepository colorRepository;
    CategoryRepository categoryRepository;

    @Transactional
    public Product createProduct(CreateProductRequest request){
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        Set<Color> colors= new HashSet<>();
        for(Long colorId : request.getColorIds()){
            Color color = colorRepository.findById(colorId)
                    .orElseThrow(() -> new AppException(ErrorCode.COLOR_NOT_FOUND));
            colors.add(color);
        }
        Set<Size> sizes = new HashSet<>();
        for(Long sizeId : request.getSizeIds()){
            Size size = sizeRepository.findById(sizeId)
                    .orElseThrow(() -> new AppException(ErrorCode.SIZE_NOT_FOUND));
            sizes.add(size);
        }
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .style(request.getStyle())
                .category(category)
                .colors(colors)
                .sizes(sizes)
                .build();
        return productRepository.save(product);
    }
}
