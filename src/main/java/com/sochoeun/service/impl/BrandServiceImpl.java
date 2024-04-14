package com.sochoeun.service.impl;

import com.sochoeun.model.Brand;
import com.sochoeun.repository.BrandRepository;
import com.sochoeun.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    @Override
    public Brand create(Brand brand) {

        return brandRepository.save(brand);
    }
}
