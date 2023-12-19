package com.bbproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bbproject.entity.IngredientEntity;
import com.bbproject.entity.RecipeEntity;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Long>
{
	List<IngredientEntity> findByRecipe(RecipeEntity recipe);
}
