package com.example.haibazoshop.service;

import com.example.haibazoshop.dto.request.CreateColorRequest;
import com.example.haibazoshop.dto.request.UpdateColorRequest;
import com.example.haibazoshop.dto.response.ColorResponse;
import com.example.haibazoshop.entity.Color;
import com.example.haibazoshop.exception.AppException;
import com.example.haibazoshop.exception.ErrorCode;
import com.example.haibazoshop.repository.ColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ColorService {
    private final ColorRepository colorRepository;

    @Transactional
    public ColorResponse createColor(CreateColorRequest request) {
        Color color = Color.builder()
                .name(request.getName())
                .hexCode(request.getHexCode())
                .build();
        color = colorRepository.save(color);
        return mapToColorResponse(color);
    }

    @Transactional(readOnly = true)
    public Page<ColorResponse> getAllColors(Pageable pageable) {
        return colorRepository.findAll(pageable)
                .map(this::mapToColorResponse);
    }

    @Transactional(readOnly = true)
    public ColorResponse getColorById(Long id) {
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COLOR_NOT_FOUND));
        return mapToColorResponse(color);
    }

    @Transactional
    public ColorResponse updateColor(Long id, UpdateColorRequest request) {
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COLOR_NOT_FOUND));
        color.setName(request.getName());
        color.setHexCode(request.getHexCode());
        color = colorRepository.save(color);
        return mapToColorResponse(color);
    }

    @Transactional
    public void deleteColor(Long id) {
        if (!colorRepository.existsById(id)) {
            throw new AppException(ErrorCode.COLOR_NOT_FOUND);
        }
        colorRepository.deleteById(id);
    }

    private ColorResponse mapToColorResponse(Color color) {
        return ColorResponse.builder()
                .id(color.getId())
                .name(color.getName())
                .hexCode(color.getHexCode())
                .build();
    }
}