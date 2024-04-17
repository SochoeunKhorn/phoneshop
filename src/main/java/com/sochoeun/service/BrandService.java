package com.sochoeun.service;

import com.sochoeun.entity.Brand;

import java.util.List;

public interface BrandService {
    Brand create(Brand brand);
    Brand getById(Integer brandId);
    Brand updated(Integer brandId,Brand brand);
    List<Brand> getBrands();

    List<Brand> getBrands(String name);
}
