package com.shivam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivam.model.IngredientsItem;



public interface IngredientItemRepository  extends JpaRepository<IngredientsItem, Long>{

	
}
