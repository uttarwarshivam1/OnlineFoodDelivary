package com.shivam.service;

import java.util.List;

import com.shivam.model.Category;
import com.shivam.model.Food;
import com.shivam.model.Restaurant;

public interface FoodService {

	public Food createFood(CreateFoodRequest req , Category category ,Restaurant restaurant );
	
	void deleteFood(Long foodId) throws Exception;
	
	public List<Food> getrestaurantsFodd(Long restaurantId,boolean isvegitarian , boolean isNonveg
			                             ,boolean isSeasonal ,String foodCategory  );
	
	public List<Food> searchFood (String Keyword);
	
	public Food findFoodById(Long foodId) throws Exception;
	
	public Food updateAvalabilityStatus(Long foodId ) throws Exception;
}
