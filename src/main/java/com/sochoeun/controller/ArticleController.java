package com.sochoeun.controller;

import com.sochoeun.Mapper.ArticleMapper;
import com.sochoeun.dto.ArticleDTO;
import com.sochoeun.dto.PageDTO;
import com.sochoeun.entity.Article;
import com.sochoeun.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/articles")
@Slf4j
public class ArticleController {
    private final ArticleService articleService;
    private final ArticleMapper articleMapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ArticleDTO dto){
        Article article = articleMapper.toArticle(dto);
        article = articleService.create(article);
        return ResponseEntity.ok(articleMapper.toArticleDto(article));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getArticle(@PathVariable("id") Integer id){
        Article article = articleService.getArticleById(id);
        return ResponseEntity.ok(articleMapper.toArticleDto(article));
    }
    @GetMapping()
    public ResponseEntity<?> getAllArticles(){
        List<Article> allArticle = articleService.getAllArticle();
        /*List<ArticleDTO> list = articleService.getAllArticle(pageNo,pageSize)
                .stream()
                .map(articleMapper::toArticleDto)
                .collect(Collectors.toList());*/
        return ResponseEntity.ok(allArticle);
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<?> getAllArticleByCategoryId(@PathVariable("id") Integer id,
                                                       @RequestParam(value = "_pageNo",required = false,defaultValue = "0") Integer pageNo,
                                                       @RequestParam(value = "_pageSize",required = false,defaultValue = "5") Integer pageSize){
        Page<Article> page = articleService.getArticles(id,pageNo,pageSize);
        PageDTO pageDTO = new PageDTO(page);
        return ResponseEntity.ok(pageDTO);
    }
}
