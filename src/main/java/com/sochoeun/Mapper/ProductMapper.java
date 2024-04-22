package com.sochoeun.Mapper;

import com.sochoeun.dto.ImportProductDTO;
import com.sochoeun.dto.ProductDTO;
import com.sochoeun.entity.Product;
import com.sochoeun.entity.ProductImportHistory;
import com.sochoeun.service.ColorService;
import com.sochoeun.service.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {ModelService.class, ColorService.class})
public interface ProductMapper {

    @Mapping(target = "model",source = "modelId")
    @Mapping(target = "color",source = "colorId")
    Product toProduct (ProductDTO dto);

    @Mapping(target = "product",source = "product")
    @Mapping(target = "id",ignore = true)
    ProductImportHistory toProductImportHistory(ImportProductDTO importDTO,Product product);
}
