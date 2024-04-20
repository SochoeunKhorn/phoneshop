package com.sochoeun.controller;

import com.sochoeun.Mapper.BrandMapper;
import com.sochoeun.Mapper.CategoryMapper;
import com.sochoeun.dto.BrandDTO;
import com.sochoeun.dto.CategoryDTO;
import com.sochoeun.entity.Brand;
import com.sochoeun.entity.Category;
import com.sochoeun.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CategoryDTO categoryDTO){
        Category category = CategoryMapper.INSTANCE.toCategory(categoryDTO);
        category = categoryService.create(category);
        return ResponseEntity.ok(CategoryMapper.INSTANCE.toCategoryDto(category));
    }

    @GetMapping
    public ResponseEntity<?> getCategories() {
        List<CategoryDTO> list = categoryService.getCategories()
                .stream()
                .map(CategoryMapper.INSTANCE::toCategoryDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") Integer categoryID){
        var category = categoryService.getCategory(categoryID);
        return ResponseEntity.ok(CategoryMapper.INSTANCE.toCategoryDto(category));
    }
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer categoryID,@RequestBody CategoryDTO categoryDTO){
        Category category = CategoryMapper.INSTANCE.toCategory(categoryDTO);
        categoryService.update(categoryID,category);
        return ResponseEntity.ok(CategoryMapper.INSTANCE.toCategoryDto(category));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer categoryId){
        var category = categoryService.getCategory(categoryId);
        categoryService.delete(categoryId);
        return ResponseEntity.ok(CategoryMapper.INSTANCE.toCategoryDto(category));
    }
}
