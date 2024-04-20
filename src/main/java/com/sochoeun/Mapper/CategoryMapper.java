package com.sochoeun.Mapper;

import com.sochoeun.dto.CategoryDTO;
import com.sochoeun.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toCategory (CategoryDTO dto);
    CategoryDTO toCategoryDto(Category category);
}
