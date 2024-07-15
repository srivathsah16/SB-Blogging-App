package com.srivath.blog.app.serviceimpls;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srivath.blog.app.dtos.CategoryDto;
import com.srivath.blog.app.entities.Category;
import com.srivath.blog.app.exceptions.ResourceNotFoundException;
import com.srivath.blog.app.repositories.CategoryRepository;
import com.srivath.blog.app.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private ModelMapper mapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category savedCategory = categoryRepo.save(mapDtoToEntity(categoryDto));
        return mapEntityToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryID", categoryId));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());

        return mapEntityToDto(categoryRepo.save(category));
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        return mapEntityToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> list = categoryRepo.findAll();
        return list.stream().map(category -> mapEntityToDto(category)).collect(Collectors.toList());

    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        categoryRepo.delete(category);
    }

    public Category mapDtoToEntity(CategoryDto categoryDto) {
        return mapper.map(categoryDto, Category.class);
    }

    public CategoryDto mapEntityToDto(Category category) {
        return mapper.map(category, CategoryDto.class);
    }
}
