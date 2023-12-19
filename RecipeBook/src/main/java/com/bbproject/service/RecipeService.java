package com.bbproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbproject.entity.CategoryEntity;
import com.bbproject.entity.IngredientEntity;
import com.bbproject.entity.RecipeEntity;
//import com.bbproject.repository.CategoryRepository;
//import com.bbproject.repository.IngredientRepository;
import com.bbproject.repository.RecipeRepository;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
    // Add methods for handling relationships
    public List<RecipeEntity> findRecipesByIngredient(IngredientEntity ingredient) {
        return recipeRepository.findByIngredients(ingredient);
    }

    public List<RecipeEntity> findRecipesByCategory(CategoryEntity category) {
        return recipeRepository.findByCategories(category);
    }

    public RecipeEntity addIngredientToRecipe(Long recipeId, IngredientEntity ingredient) {
        RecipeEntity recipe = recipeRepository.findById(recipeId).orElse(null);
        if (recipe != null) {
            ingredient.setRecipe(recipe);
            recipe.getIngredients().add(ingredient);
            return recipeRepository.save(recipe);
        }
        return null;
    }
    public RecipeEntity addCategoryToRecipe(Long recipeId, CategoryEntity category) {
        RecipeEntity recipe = recipeRepository.findById(recipeId).orElse(null);
        if (recipe != null) {
            recipe.getCategories().add(category);
            return recipeRepository.save(recipe);
        }
        return null;
    }
    public List<RecipeEntity> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public RecipeEntity getRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public RecipeEntity saveRecipe(RecipeEntity recipe) {
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
    // Add more service methods as needed
}