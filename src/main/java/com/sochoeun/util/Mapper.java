package com.sochoeun.util;

import com.sochoeun.dto.BrandDTO;
import com.sochoeun.model.Brand;

public class Mapper {
    public static Brand toBrand(BrandDTO brandDTO){
        Brand brand = new Brand();
        //brand.setId(brandDTO.getId());
        brand.setName(brandDTO.getName());
        return brand;
    }

    public static BrandDTO toBrandDTO(Brand brand){
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setName(brand.getName());
        return brandDTO;
    }
}
