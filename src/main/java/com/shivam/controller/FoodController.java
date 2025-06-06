package com.shivam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.model.Food;
import com.shivam.model.Restaurant;
import com.shivam.model.User;
import com.shivam.service.CreateFoodRequest;
import com.shivam.service.FoodService;
import com.shivam.service.RestaurantService;
import com.shivam.service.UserService;

@RestController
@RequestMapping("/api/food")
public class FoodController {

	@Autowired
	private FoodService foodService;
	
	@Autowired
	private UserService userService ;
	
	@Autowired
	private RestaurantService restaurantService;
	
	
	@GetMapping("/searchfood")
	public ResponseEntity <List<Food>> searchFood(@RequestParam String name ,
			                               @RequestHeader("Authorization") String jwt ) throws Exception{
		
		User user =userService.findUserbyJwttoken(jwt);
        List<Food> foods = foodService.searchFood(name);
		
		return new ResponseEntity<>(foods,HttpStatus.OK);
	}
	
	
	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity <List<Food>> getRestaurantFood(@RequestParam boolean vegitarian,
			@RequestParam boolean nonveg,
			@RequestParam boolean seasonal,
			@RequestParam(required = false) String food_category,
			@PathVariable Long restaurantId ,
			                               @RequestHeader("Authorization") String jwt ) throws Exception{
		
		User user =userService.findUserbyJwttoken(jwt);
        List<Food> foods = foodService.getrestaurantsFood(restaurantId, vegitarian, nonveg, seasonal, food_category);	
		
		return new ResponseEntity<>(foods,HttpStatus.OK);
	}
	
	
	
	
	
}
