package com.sochoeun.service;

import com.sochoeun.entity.Brand;

public interface BrandService {
    Brand create(Brand brand);
    Brand getById(Integer brandId);
    Brand update(Integer brandId,Brand brand);
}
