package com.bbproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class IngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredientid;
    private String ingredientname;
    private String ingredientamount;
    // Add more fields as needed
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonIgnore
    private RecipeEntity recipe;
	public RecipeEntity getRecipe() {
		return recipe;
	}
	public void setRecipe(RecipeEntity recipe) {
		this.recipe = recipe;
	}
	public Long getIngredientid() {
		return ingredientid;
	}
	public void setIngredientid(Long ingredientid) {
		this.ingredientid = ingredientid;
	}
	public String getIngredientname() {
		return ingredientname;
	}
	public void setIngredientname(String ingredientname) {
		this.ingredientname = ingredientname;
	}
	public String getIngredientamount() {
		return ingredientamount;
	}
	public void setIngredientamount(String ingredientamount) {
		this.ingredientamount = ingredientamount;
	}
    

    // Getters and setters
}
