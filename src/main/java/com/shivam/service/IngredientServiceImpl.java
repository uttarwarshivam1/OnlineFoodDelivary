package com.shivam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.model.IngredientCategory;
import com.shivam.model.IngredientsItem;
import com.shivam.model.Restaurant;
import com.shivam.repository.IngredientCategoryRepository;
import com.shivam.repository.IngredientItemRepository;

@Service
public class IngredientServiceImpl implements IngredientsService {

	@Autowired
	private IngredientItemRepository ingredientItemRepository;
	
	@Autowired
	private IngredientCategoryRepository ingredientCategoryRepository;	
	
	@Autowired
	private RestaurantService restaurantService; 
	
	
	
	@Override
	public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
		Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
		IngredientCategory category = new IngredientCategory();
		category.setRestaurant(restaurant);
		category.setName(name);
		
		return ingredientCategoryRepository.save(category);
		
		
	}

	@Override
	public IngredientCategory findIngredientCategoryByID(Long id) throws Exception {
		
		Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new Exception("Not Found");
		}
		
		return opt.get();
	}
	
	

	@Override
	public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
		
		restaurantService.findRestaurantById(id);
		return ingredientCategoryRepository.findByRestaurantId(id);
	}

	@Override
	public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId)
			throws Exception {
		
		Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
		IngredientCategory category = findIngredientCategoryByID(categoryId);
		
		IngredientsItem item = new IngredientsItem();
		
		item.setName(ingredientName);
		item.setRestaurant(restaurant);
		item.setCategory(category);
		
	    IngredientsItem ingredient = ingredientItemRepository.save(item);
	    category.getIngredients().add(ingredient);
		return ingredient;
	}

	@Override
	public List<IngredientsItem> findrestaurantIngredients(Long restaurantId) {
		
		return null;
	}

	@Override
	public IngredientsItem updateStock(Long id) throws Exception {
		
		return null;
	}

}
