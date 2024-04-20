package com.sochoeun.service;

import com.sochoeun.entity.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);
    List<Category> getCategories();
    Category getCategory(Integer id);
    Category update(Integer categoryId,Category category);
    void delete(Integer id);
}
