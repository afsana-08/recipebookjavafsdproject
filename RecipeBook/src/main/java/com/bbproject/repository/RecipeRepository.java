package com.bbproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbproject.entity.CategoryEntity;
import com.bbproject.entity.IngredientEntity;
import com.bbproject.entity.RecipeEntity;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long>
{
	// Add custom query methods for RecipeEntity
    List<RecipeEntity> findByIngredients(IngredientEntity ingredient);
    List<RecipeEntity> findByCategories(CategoryEntity category);
}
