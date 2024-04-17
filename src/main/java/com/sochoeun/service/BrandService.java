package com.sochoeun.service;

import com.sochoeun.entity.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {
    Brand create(Brand brand);
    Brand getById(Integer brandId);
    Brand updated(Integer brandId,Brand brand);
    List<Brand> getBrands();

    List<Brand> getBrands(String name);
    List<Brand> getBrands(Map<String,String> params);
}
