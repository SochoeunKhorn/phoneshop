package com.sochoeun.service;

import com.sochoeun.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    Article create(Article article);
    Article getArticleById(Integer articleId);
    List<Article> getAllArticle();
    Page<Article> getArticles(Integer categoryId,Integer pageNo,Integer pageSize);
}
