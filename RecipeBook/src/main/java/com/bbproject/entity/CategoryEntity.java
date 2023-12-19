package com.bbproject.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryid;
    private String categoryname;
    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private List<RecipeEntity> recipes = new ArrayList<>();
    public List<RecipeEntity> getRecipes() {
		return recipes;
	}
	public void setRecipes(List<RecipeEntity> recipes) {
		this.recipes = recipes;
	}
	public Long getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	
    // Add more fields and relationships as needed

    // Getters and setters
}
