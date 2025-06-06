package com.shivam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivam.model.Category;


public interface CategoryRepository  extends JpaRepository<Category, Long>{

	public List<Category> findByRestaurantId (Long restaurantId);
	
	
 	
}
