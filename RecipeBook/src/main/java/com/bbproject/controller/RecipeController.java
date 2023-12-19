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
import com.bbproject.entity.IngredientEntity;
import com.bbproject.entity.RecipeEntity;
import com.bbproject.service.CategoryService;
import com.bbproject.service.IngredientService;
import com.bbproject.service.RecipeService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public List<RecipeEntity> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public RecipeEntity getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @PostMapping
    public RecipeEntity createRecipe(@RequestBody RecipeEntity recipe) {
        return recipeService.saveRecipe(recipe);
    }

    @PutMapping("/{id}")
    public RecipeEntity updateRecipe(@PathVariable Long id, @RequestBody RecipeEntity recipe) throws Exception {
        // Check if the recipe with the given id exists
        RecipeEntity existingRecipe = recipeService.getRecipeById(id);
        if (existingRecipe == null) {
            throw new Exception("Recipe not found with id " + id);
        }

        // Update the existing recipe
        existingRecipe.setName(recipe.getName());
        existingRecipe.setDescription(recipe.getDescription());
        // Update other fields as needed

        return recipeService.saveRecipe(existingRecipe);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }
    // Add more request mappings and methods as needed
 // Endpoint to add an ingredient to a recipe
    @PostMapping("/{recipeId}/ingredients")
    public ResponseEntity<RecipeEntity> addIngredientToRecipe(
            @PathVariable Long recipeId,
            @RequestBody IngredientEntity ingredient) {
        RecipeEntity updatedRecipe = recipeService.addIngredientToRecipe(recipeId, ingredient);
        if (updatedRecipe != null) {
            return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to add a category to a recipe
    @PostMapping("/{recipeId}/categories")
    public ResponseEntity<RecipeEntity> addCategoryToRecipe(
            @PathVariable Long recipeId,
            @RequestBody CategoryEntity category) {
        RecipeEntity updatedRecipe = recipeService.addCategoryToRecipe(recipeId, category);
        if (updatedRecipe != null) {
            return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to find recipes by ingredient
    @GetMapping("/byIngredient/{ingredientId}")
    public ResponseEntity<List<RecipeEntity>> findRecipesByIngredient(@PathVariable Long ingredientId) {
        IngredientEntity ingredient = ingredientService.getIngredientById(ingredientId);
        if (ingredient != null) {
            List<RecipeEntity> recipes = recipeService.findRecipesByIngredient(ingredient);
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to find recipes by category
    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<List<RecipeEntity>> findRecipesByCategory(@PathVariable Long categoryId) {
        CategoryEntity category = categoryService.getCategoryById(categoryId);
        if (category != null) {
            List<RecipeEntity> recipes = recipeService.findRecipesByCategory(category);
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}