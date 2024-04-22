package com.sochoeun.repository;

import com.sochoeun.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer>, PagingAndSortingRepository<Article,Integer> {
    List<Article> findAllByCategory_Id(Integer id);
    Page<Article> findAllByCategory_Id(Integer categoryId, Pageable pageable);
}
