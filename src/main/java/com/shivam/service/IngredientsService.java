package com.shivam.service;

import java.util.List;

import com.shivam.model.IngredientCategory;
import com.shivam.model.IngredientsItem;

public interface IngredientsService {
	
	
	public IngredientCategory createIngredientCategory (String name , Long restaurantId) throws Exception;
	
	public IngredientCategory findIngredientCategoryByID (Long id) throws Exception;
	
	public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;
	
	public IngredientsItem createIngredientItem(Long restaurantId , String ingredientName , Long categoryId)
	throws Exception ; 
	
	public  List<IngredientsItem> findrestaurantIngredients(Long restaurantId);
	
	public IngredientsItem updateStock(Long id) throws Exception;
	
  }



