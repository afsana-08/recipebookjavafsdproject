package com.bbproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbproject.entity.CategoryEntity;
import com.bbproject.entity.RecipeEntity;
import com.bbproject.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
 // Add methods for handling relationships
    public List<CategoryEntity> findCategoriesByRecipe(RecipeEntity recipe) {
        return categoryRepository.findByRecipes(recipe);
    }
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    public CategoryEntity getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public CategoryEntity saveCategory(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
    // Add more service methods as needed
}