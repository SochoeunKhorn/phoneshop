package com.sochoeun.service;

import com.sochoeun.entity.Brand;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BrandService {
    Brand create(Brand brand);
    Brand getById(Long brandId);
    Brand updated(Long brandId,Brand brand);
    List<Brand> getBrands();

    List<Brand> getBrands(String name);
    //List<Brand> getBrands(Map<String,String> params);

    Page<Brand> getBrands(Map<String,String> params);
}
