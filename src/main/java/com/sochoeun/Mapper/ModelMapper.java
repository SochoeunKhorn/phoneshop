package com.sochoeun.Mapper;

import com.sochoeun.dto.ModelDTO;
import com.sochoeun.entity.Model;
import com.sochoeun.service.BrandService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BrandService.class})
public interface ModelMapper {
    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

    @Mapping(target = "brand",source = "brandId")
    Model toModel(ModelDTO modelDTO);

    @Mapping(target = "brandId",source = "brand.id")
    ModelDTO toModelDto(Model model);
}
