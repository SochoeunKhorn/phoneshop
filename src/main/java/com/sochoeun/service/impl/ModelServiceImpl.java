package com.sochoeun.service.impl;

import com.sochoeun.entity.Model;
import com.sochoeun.repository.ModelRepository;
import com.sochoeun.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    @Override
    public Model save(Model model) {
        return modelRepository.save(model);
    }
}
