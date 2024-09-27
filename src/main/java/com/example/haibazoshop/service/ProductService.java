package com.example.haibazoshop.service;

import com.example.haibazoshop.dto.request.CreateProductRequest;
import com.example.haibazoshop.dto.request.UpdateProductRequest;
import com.example.haibazoshop.dto.response.*;
import com.example.haibazoshop.entity.*;
import com.example.haibazoshop.exception.AppException;
import com.example.haibazoshop.exception.ErrorCode;
import com.example.haibazoshop.repository.CategoryRepository;
import com.example.haibazoshop.repository.ColorRepository;
import com.example.haibazoshop.repository.ProductRepository;
import com.example.haibazoshop.repository.SizeRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService{
    ProductRepository productRepository;
    SizeRepository sizeRepository;
    ColorRepository colorRepository;
    CategoryRepository categoryRepository;
    CloudinaryService cloudinaryService;

    @Transactional
    public ProductResponse updateProduct(Long id, UpdateProductRequest request) {
        Product product = productRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        if (request.getName() != null && !request.getName().equals(product.getName())) {
            if (productRepository.existsByNameAndIsDeletedFalse(request.getName())) {
                throw new AppException(ErrorCode.PRODUCT_NAME_ALREADY_EXISTS);
            }
            product.setName(request.getName());
        }

        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }

        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }

        if (request.getStyle() != null) {
            product.setStyle(request.getStyle());
        }

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
            product.setCategory(category);
        }

        if (request.getColorIds() != null && !request.getColorIds().isEmpty()) {
            Set<Color> colors = new HashSet<>();
            for (Long colorId : request.getColorIds()) {
                Color color = colorRepository.findById(colorId)
                        .orElseThrow(() -> new AppException(ErrorCode.COLOR_NOT_FOUND));
                colors.add(color);
            }
            product.setColors(colors);
        }

        if (request.getSizeIds() != null && !request.getSizeIds().isEmpty()) {
            Set<Size> sizes = new HashSet<>();
            for (Long sizeId : request.getSizeIds()) {
                Size size = sizeRepository.findById(sizeId)
                        .orElseThrow(() -> new AppException(ErrorCode.SIZE_NOT_FOUND));
                sizes.add(size);
            }
            product.setSizes(sizes);
        }

        if (request.getNewImages() != null && !request.getNewImages().isEmpty()) {
            // Delete old images from Cloudinary
            for (Image oldImage : product.getImages()) {
                String publicId = extractPublicIdFromUrl(oldImage.getUrl());
                cloudinaryService.deleteImage(publicId);
            }

            // Clear old images
            product.getImages().clear();

            // Add new images
            List<Image> newImages = new ArrayList<>();
            for (MultipartFile file : request.getNewImages()) {
                String imageUrl = cloudinaryService.uploadImage(file);
                Image image = Image.builder()
                        .url(imageUrl)
                        .isPrimary(newImages.isEmpty())
                        .product(product)
                        .build();
                newImages.add(image);
            }
            product.setImages(newImages);
        }

        Product updatedProduct = productRepository.save(product);
        return mapToProductResponse(updatedProduct);
    }
    @Transactional
    public ProductResponse createProduct(CreateProductRequest request) {
        //check name is unique
        if (productRepository.existsByNameAndIsDeletedFalse(request.getName())) {
            throw new AppException(ErrorCode.PRODUCT_NAME_ALREADY_EXISTS);
        }
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        Set<Color> colors = new HashSet<>();
        for (Long colorId : request.getColorIds()) {
            Color color = colorRepository.findById(colorId)
                    .orElseThrow(() -> new AppException(ErrorCode.COLOR_NOT_FOUND));
            colors.add(color);
        }
        Set<Size> sizes = new HashSet<>();
        for (Long sizeId : request.getSizeIds()) {
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
                .isDeleted(false)
                .build();

        List<Image> images = new ArrayList<>();
        for (MultipartFile file : request.getImages()) {
            String imageUrl = cloudinaryService.uploadImage(file);
            Image image = Image.builder()
                    .url(imageUrl)
                    .isPrimary(images.isEmpty()) // First image is primary
                    .product(product)
                    .build();
            images.add(image);
        }
        product.setImages(images);

        Product savedProduct = productRepository.save(product);
        return mapToProductResponse(savedProduct);
    }
    @Transactional(readOnly = true)
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        return productRepository.findAllByIsDeletedFalse(pageable)
                .map(this::mapToProductResponse);
    }

    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return mapToProductResponse(product);
    }

    @Transactional
    public void softDeleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        product.setDeleted(true);
        productRepository.save(product);
    }
    private String extractPublicIdFromUrl(String url) {
        String[] urlParts = url.split("/");
        String fileName = urlParts[urlParts.length - 1];
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }
    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .style(product.getStyle())
                .views(product.getViews())
                .category(mapToCategoryResponse(product.getCategory()))
                .colors(product.getColors().stream().map(this::mapToColorResponse).collect(Collectors.toSet()))
                .sizes(product.getSizes().stream().map(this::mapToSizeResponse).collect(Collectors.toSet()))
                .images(product.getImages().stream().map(this::mapToImageResponse).collect(Collectors.toList()))
                .build();
    }
    private CategoryResponse mapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
    private ColorResponse mapToColorResponse(Color color) {
        return ColorResponse.builder()
                .id(color.getId())
                .name(color.getName())
                .build();
    }
    private SizeResponse mapToSizeResponse(Size size) {
        return SizeResponse.builder()
                .id(size.getId())
                .name(size.getName())
                .build();
    }
    private ImageResponse mapToImageResponse(Image image) {
        return ImageResponse.builder()
                .id(image.getId())
                .url(image.getUrl())
                .isPrimary(image.isPrimary())
                .build();
    }
}
