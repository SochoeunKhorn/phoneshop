package com.sochoeun.service.impl;

import com.sochoeun.Mapper.ArticleMapper;
import com.sochoeun.dto.ArticleDTO;
import com.sochoeun.dto.PageDTO;
import com.sochoeun.entity.Article;
import com.sochoeun.exception.NotFoundException;
import com.sochoeun.repository.ArticleRepository;
import com.sochoeun.service.ArticleService;
import com.sochoeun.service.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    @Override
    public Article create(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article getArticleById(Integer articleId) {
        return articleRepository.findById(articleId).orElseThrow(()-> new NotFoundException("Article",articleId));
    }

    @Override
    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }


    @Override
    public Page<Article> getArticles(Integer categoryId,Integer pageNo,Integer pageSize) {
        Pageable pageable = PageUtil.getPageable(pageSize,pageNo);
        Page<Article> pages = articleRepository.findAllByCategory_Id(categoryId,pageable);
        return pages;
    }



}
