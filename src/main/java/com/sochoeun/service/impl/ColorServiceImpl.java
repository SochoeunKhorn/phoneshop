package com.sochoeun.service.impl;

import com.sochoeun.entity.Color;
import com.sochoeun.exception.NotFoundException;
import com.sochoeun.repository.ColorRepository;
import com.sochoeun.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;
    @Override
    public Color getColor(Long id) {
        return colorRepository.findById(id).orElseThrow(()-> new NotFoundException("Color",id));
    }
}
