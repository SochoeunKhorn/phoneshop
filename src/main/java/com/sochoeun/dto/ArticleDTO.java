package com.sochoeun.dto;

import lombok.Data;

@Data
public class ArticleDTO {
    private Integer id;
    private String title;
    private String description;
    private String imageUrl;
    private Integer categoryId;
}
