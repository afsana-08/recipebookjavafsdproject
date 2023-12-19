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

import com.bbproject.entity.IngredientEntity;
import com.bbproject.entity.RecipeEntity;
import com.bbproject.service.IngredientService;
import com.bbproject.service.RecipeService;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private RecipeService recipeService;
    @GetMapping
    public List<IngredientEntity> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/{id}")
    public IngredientEntity getIngredientById(@PathVariable Long id) {
        return ingredientService.getIngredientById(id);
    }

    @PostMapping
    public IngredientEntity createRecipe(@RequestBody IngredientEntity recipe) {
        return ingredientService.saveIngredient(recipe);
    }

    @PutMapping("/{id}")
    public IngredientEntity updateRecipe(@PathVariable Long id, @RequestBody IngredientEntity recipe) throws Exception {
        // Check if the recipe with the given id exists
        IngredientEntity existingRecipe = ingredientService.getIngredientById(id);
        if (existingRecipe == null) {
            throw new Exception("Recipe not found with id " + id);
        }

        // Update the existing recipe
        existingRecipe.setIngredientname(recipe.getIngredientname());
        existingRecipe.setIngredientamount(recipe.getIngredientamount());
        // Update other fields as needed

        return ingredientService.saveIngredient(existingRecipe);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
    }
    // Add more request mappings and methods as needed
 // Endpoint to find ingredients by recipe
    @GetMapping("/byRecipe/{recipeId}")
    public ResponseEntity<List<IngredientEntity>> findIngredientsByRecipe(@PathVariable Long recipeId) {
        RecipeEntity recipe = recipeService.getRecipeById(recipeId);
        if (recipe != null) {
            List<IngredientEntity> ingredients = ingredientService.findIngredientsByRecipe(recipe);
            return new ResponseEntity<>(ingredients, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}