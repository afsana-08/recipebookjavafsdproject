package com.bbproject.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<IngredientEntity> ingredients = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "recipe_category",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonIgnore
    private List<CategoryEntity> categories = new ArrayList<>();
    // Add more fields and relationships as needed
    
	public Long getId() {
		return id;
	}
	public List<IngredientEntity> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<IngredientEntity> ingredients) {
		this.ingredients = ingredients;
	}
	public List<CategoryEntity> getCategories() {
		return categories;
	}
	public void setCategories(List<CategoryEntity> categories) {
		this.categories = categories;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

    // Getters and setters
}
