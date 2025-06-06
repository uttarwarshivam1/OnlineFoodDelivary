package com.shivam.service;

import java.util.List;

import com.shivam.model.Category;
import com.shivam.model.IngredientsItem;
import com.shivam.model.Restaurant;

import lombok.Data;

@Data
public class CreateFoodRequest {
     
	private String name;
	
	private String description;
	
	private Long price;
	
	private Long restaurantId;
	 
	private Category category;
	
	private List<String> images;
	
	private boolean vegitarian;
	
	private boolean seasional;
	
	private List<IngredientsItem> ingredients;
	
	
}
