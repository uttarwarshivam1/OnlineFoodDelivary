package com.shivam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.model.Food;
import com.shivam.model.Restaurant;
import com.shivam.model.User;
import com.shivam.response.Messageresponse;
import com.shivam.service.CreateFoodRequest;
import com.shivam.service.FoodService;
import com.shivam.service.RestaurantService;
import com.shivam.service.UserService;

@RestController
@RequestMapping
public class AdminFoodController {

	@Autowired
	private FoodService foodService;
	
	@Autowired
	private UserService userService ;
	
	@Autowired
	private RestaurantService restaurantService;
	
	
	@PostMapping("/createfood")
	public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req ,
			                               @RequestHeader("Authorization") String jwt ) throws Exception{
		
		User user =userService.findUserbyJwttoken(jwt);
		Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
		Food food = foodService.createFood(req, req.getCategory(), restaurant);
		
		return new ResponseEntity<>(food,HttpStatus.CREATED);
	}
	
	
	
	@PostMapping("/{id}")
	public ResponseEntity<Messageresponse> deleteFood (@PathVariable Long id ,
			                               @RequestHeader("Authorization") String jwt ) throws Exception{
		
		User user =userService.findUserbyJwttoken(jwt);
		
		
		foodService.deleteFood(id);
		Messageresponse messageresponse = new Messageresponse();
		messageresponse.setMessage("Food is deleted");
		
		
		return new  ResponseEntity<>(messageresponse ,HttpStatus.CREATED);
	}
	
	
	@PostMapping("/{id}/update")
	public ResponseEntity<Messageresponse> updateFoodAvalabilityStatus (@PathVariable Long id ,
			                               @RequestHeader("Authorization") String jwt ) throws Exception{
		
		User user =userService.findUserbyJwttoken(jwt);
		Food food = foodService.updateAvalabilityStatus(id);
		
		foodService.deleteFood(id);
		return new  ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	
	
}
