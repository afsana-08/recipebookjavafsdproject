package com.bbproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bbproject.entity.CategoryEntity;
import com.bbproject.entity.RecipeEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>
{
	List<CategoryEntity> findByRecipes(RecipeEntity recipe);
}

