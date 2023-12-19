package com.bbproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbproject.entity.CategoryEntity;
import com.bbproject.entity.RecipeEntity;
import com.bbproject.service.CategoryService;
import com.bbproject.service.RecipeService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RecipeService recipeService;
    @GetMapping
    public List<CategoryEntity> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryEntity getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public CategoryEntity createCategory(@RequestBody CategoryEntity category) {
        return categoryService.saveCategory(category);
    }

    @PutMapping("/{id}")
    public CategoryEntity updateCategory(@PathVariable Long id, @RequestBody CategoryEntity recipe) throws Exception {
        // Check if the recipe with the given id exists
        CategoryEntity existingCategory = categoryService.getCategoryById(id);
        if (existingCategory == null) {
            throw new Exception("Recipe not found with id " + id);
        }

        // Update the existing recipe
        existingCategory.setCategoryname(recipe.getCategoryname());
       // existingCategory.setIngredientamount(recipe.getIngredientamount());
        // Update other fields as needed

        return categoryService.saveCategory(existingCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
    // Add more request mappings and methods as needed
 // Endpoint to find categories by recipe
    @GetMapping("/byRecipe/{recipeId}")
    public ResponseEntity<List<CategoryEntity>> findCategoriesByRecipe(@PathVariable Long recipeId) {
        RecipeEntity recipe = recipeService.getRecipeById(recipeId);
        if (recipe != null) {
            List<CategoryEntity> categories = categoryService.findCategoriesByRecipe(recipe);
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
