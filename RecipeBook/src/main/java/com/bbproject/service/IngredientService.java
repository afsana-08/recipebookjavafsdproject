package com.bbproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbproject.entity.IngredientEntity;
import com.bbproject.entity.RecipeEntity;
import com.bbproject.repository.IngredientRepository;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;
    public List<IngredientEntity> findIngredientsByRecipe(RecipeEntity recipe) {
        return ingredientRepository.findByRecipe(recipe);
    }
    public List<IngredientEntity> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public IngredientEntity getIngredientById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    public IngredientEntity saveIngredient(IngredientEntity ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }
    // Add more service methods as needed
}