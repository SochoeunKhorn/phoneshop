package com.sochoeun.service.impl;

import com.sochoeun.entity.Category;
import com.sochoeun.exception.NotFoundException;
import com.sochoeun.repository.CategoryRepository;
import com.sochoeun.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public Category create(Category category) {
        //Category save = categoryRepository.save(category);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getCategories() {
        //List<Category> all = categoryRepository.findAll();
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Integer id) {
        categoryRepository.findById(id);
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category",id));
    }

    @Override
    public Category update(Integer categoryId,Category category) {
        Category update = getCategory(categoryId);
        update.setName(category.getName());
        return categoryRepository.save(update);
    }

    @Override
    public void delete(Integer id) {
        Category category = getCategory(id);
        if(category != null){
            categoryRepository.deleteById(id);
        }
    }
}
