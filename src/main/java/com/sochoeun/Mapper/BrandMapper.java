package com.sochoeun.Mapper;

import com.sochoeun.dto.BrandDTO;
import com.sochoeun.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
    Brand toBrand(BrandDTO dto);
    BrandDTO toBrandDTO(Brand entity);
}
