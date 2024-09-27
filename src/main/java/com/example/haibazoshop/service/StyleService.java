package com.example.haibazoshop.service;

import com.example.haibazoshop.dto.request.CreateStyleRequest;
import com.example.haibazoshop.dto.response.StyleResponse;
import com.example.haibazoshop.entity.Style;
import com.example.haibazoshop.exception.AppException;
import com.example.haibazoshop.exception.ErrorCode;
import com.example.haibazoshop.repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StyleService {
    private final StyleRepository styleRepository;

    @Transactional
    public StyleResponse createStyle(CreateStyleRequest request) {
        if (styleRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.STYLE_ALREADY_EXISTS);
        }
        Style style = Style.builder()
                .name(request.getName())
                .build();
        style = styleRepository.save(style);
        return mapToStyleResponse(style);
    }

    @Transactional(readOnly = true)
    public Page<StyleResponse> getAllStyles(Pageable pageable) {
        return styleRepository.findAll(pageable)
                .map(this::mapToStyleResponse);
    }

    @Transactional(readOnly = true)
    public StyleResponse getStyleById(Long id) {
        Style style = styleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.STYLE_NOT_FOUND));
        return mapToStyleResponse(style);
    }
    @Transactional
    public void deleteStyle(Long id) {
        Style style = styleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.STYLE_NOT_FOUND));
        styleRepository.delete(style);
    }
    @Transactional
    public StyleResponse updateStyle(Long id, CreateStyleRequest request) {
        Style style = styleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.STYLE_NOT_FOUND));
        if (styleRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.STYLE_ALREADY_EXISTS);
        }
        style.setName(request.getName());
        style = styleRepository.save(style);
        return mapToStyleResponse(style);
    }

    private StyleResponse mapToStyleResponse(Style style) {
        return StyleResponse.builder()
                .id(style.getId())
                .name(style.getName())
                .build();
    }

}