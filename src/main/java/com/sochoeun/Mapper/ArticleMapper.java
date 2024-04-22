package com.sochoeun.Mapper;

import com.sochoeun.dto.ArticleDTO;
import com.sochoeun.entity.Article;
import com.sochoeun.service.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {CategoryService.class})
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);
    @Mapping(target = "category",source = "categoryId")
    Article toArticle (ArticleDTO dto);

    @Mapping(target = "categoryId",source = "category.id")
    ArticleDTO toArticleDto(Article article);
}
