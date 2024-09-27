package com.example.haibazoshop.service;

import com.example.haibazoshop.dto.request.CreateSizeRequest;
import com.example.haibazoshop.dto.request.UpdateSizeRequest;
import com.example.haibazoshop.dto.response.SizeResponse;
import com.example.haibazoshop.entity.Size;
import com.example.haibazoshop.exception.AppException;
import com.example.haibazoshop.exception.ErrorCode;
import com.example.haibazoshop.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SizeService {
    private final SizeRepository sizeRepository;

    @Transactional
    public SizeResponse createSize(CreateSizeRequest request) {
        Size size = Size.builder()
                .name(request.getName())
                .build();
        size = sizeRepository.save(size);
        return mapToSizeResponse(size);
    }

    @Transactional(readOnly = true)
    public Page<SizeResponse> getAllSizes(Pageable pageable) {
        return sizeRepository.findAll(pageable)
                .map(this::mapToSizeResponse);
    }

    @Transactional(readOnly = true)
    public SizeResponse getSizeById(Long id) {
        Size size = sizeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SIZE_NOT_FOUND));
        return mapToSizeResponse(size);
    }

    @Transactional
    public SizeResponse updateSize(Long id, UpdateSizeRequest request) {
        Size size = sizeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SIZE_NOT_FOUND));
        size.setName(request.getName());
        size = sizeRepository.save(size);
        return mapToSizeResponse(size);
    }

    @Transactional
    public void deleteSize(Long id) {
        if (!sizeRepository.existsById(id)) {
            throw new AppException(ErrorCode.SIZE_NOT_FOUND);
        }
        sizeRepository.deleteById(id);
    }

    private SizeResponse mapToSizeResponse(Size size) {
        return SizeResponse.builder()
                .id(size.getId())
                .name(size.getName())
                .build();
    }
}