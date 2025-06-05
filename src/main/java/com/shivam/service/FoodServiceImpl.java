package com.shivam.service;

import java.nio.file.DirectoryStream.Filter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.model.Category;
import com.shivam.model.Food;
import com.shivam.model.Restaurant;
import com.shivam.repository.FoodRepository;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepository foodRepository;
	
	
	
	
	
	@Override
	public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
		Food food = new Food();
		
		food.setFoodCategory(category);
		food.setRestaurant(restaurant);
		food.setDescription(req.getDescription());
		food.setImages(req.getImages());
		food.setName(req.getName());
		food.setPrice(req.getPrice());
		food.setIngredients(req.getIngredients());
		food.setSeasonal(req.isSeasional());
		food.setVegetarian(req.isVegitarian());
	
		
		Food savedFood =  foodRepository.save(food);
		restaurant.getFoods().add(savedFood);
		
		return savedFood;
	}

	@Override
	public void deleteFood(Long foodId) throws Exception {
		Food food = findFoodById(foodId);
	    food.setRestaurant(null);
	    foodRepository.save(food);
	  
	}

	
	@Override
	public List<Food> getrestaurantsFodd(Long restaurantId, boolean isvegitarian, boolean isNonveg,
			boolean isSeasonal,
			String foodCategory) {
		
		List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
		
		if(isvegitarian) {
			foods = filterByVegitarian(foods,isvegitarian);
			
		}
		
		if(isNonveg) {
			 foods = filterByIsNonVeg(foods, isNonveg);
		}
		
		if(isSeasonal) {
			foods = filterByIsSeasonal(foods,isSeasonal);
		}
		
		if(foodCategory !=null &&  !foodCategory.equals(""))
		{
			foods = filterByCategory(foods,foodCategory);
		}
		return foods;
		
	}

	private List<Food> filterByIsSeasonal(List<Food> foods, boolean isSeasonal) {
		
		return foods.stream().filter(food -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());

	}

	private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
		
		return foods.stream().filter(food->{
			if(food.getFoodCategory()!=null) {
				return food.getFoodCategory().getName().equals(foodCategory);
			}
			return false;
		}).collect(Collectors.toList());
	}

	private List<Food> filterByIsNonVeg(List<Food> foods, boolean isNonveg) {
		
		return foods.stream().filter(food -> food.isVegetarian()==false).collect(Collectors.toList());

		
	}

	private List<Food> filterByVegitarian(List<Food> foods, boolean isvegitarian) {
		
		return foods.stream().filter(food -> food.isVegetarian()==isvegitarian).collect(Collectors.toList());
	}

	@Override
	public List<Food> searchFood(String Keyword) {
		
		return foodRepository.searchFood(Keyword);
	}

	@Override
	public Food findFoodById(Long foodId) throws Exception {
		Optional<Food> optionalFood =foodRepository.findById(foodId); 
		
		if(optionalFood.isEmpty()) {
			throw new Exception("The food is not available");
		}
		return optionalFood.get();
	}

	@Override
	public Food updateAvalabilityStatus(Long foodId) throws Exception {
		Food food = findFoodById(foodId);
		food.setAvailable(food.isAvailable());
		
		
		return foodRepository.save(food);
	}

}
