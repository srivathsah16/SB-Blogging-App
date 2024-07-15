package com.srivath.blog.app.services;

import java.util.List;

import com.srivath.blog.app.dtos.CategoryDto;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    CategoryDto getCategory(Integer categoryId);

    List<CategoryDto> getAllCategory();

    void deleteCategory(Integer categoryId);
}
