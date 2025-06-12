package com.shivam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivam.model.IngredientCategory;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long> {

	 List<IngredientCategory> findByRestaurantId(Long restaurantId) throws Exception;
	 
	 
}
