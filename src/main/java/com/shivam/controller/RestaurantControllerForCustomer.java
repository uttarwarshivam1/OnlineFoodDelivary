package com.shivam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.dto.RestaurantDto;
import com.shivam.model.Restaurant;
import com.shivam.model.User;
import com.shivam.request.CreateRestaurantRequest;
import com.shivam.service.RestaurantService;
import com.shivam.service.UserService;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantControllerForCustomer {

	                            
    @Autowired     
	   private RestaurantService restaurantService ; 
    
	   @Autowired
	   private UserService userService ; 
	   
	   @GetMapping("/search") 
	   public ResponseEntity<List<Restaurant>> searchRestaurant ( @RequestParam String Keyword ,	
			                                                                                                 @RequestHeader("Authorization" ) String jwt ) throws Exception
	   {
		    User user  = userService.findUserbyJwttoken(jwt);
		   
		   List<Restaurant> restaurant = restaurantService.searchRestaurant(Keyword);
		   return new  ResponseEntity<>(restaurant , HttpStatus.OK);
	   }
	   
	   
	   
	   @GetMapping
	   public ResponseEntity<List<Restaurant>> getAllRestaurant (  @RequestHeader("Authorization" ) String jwt )
			   throws Exception   {
		    User user  = userService.findUserbyJwttoken(jwt);
		   
		   List<Restaurant> restaurant = restaurantService.getAllRestaurant();
		   return new  ResponseEntity<>(restaurant , HttpStatus.OK);
	   }
	   
	   
	   @GetMapping("/{id}") 
	   public ResponseEntity<Restaurant>findRestaurantById (  @RequestHeader("Authorization" ) String jwt , @PathVariable Long id )
			   throws Exception   {
		    User user  = userService.findUserbyJwttoken(jwt);
		   
		   Restaurant restaurant = restaurantService.findRestaurantById(id);
		   return new  ResponseEntity<>(restaurant , HttpStatus.OK);
	   }
	   
	   
	   @PutMapping("/{id}/add-favorites") 
	   public ResponseEntity<RestaurantDto> addToFavourites (  @RequestHeader("Authorization" ) String jwt , @PathVariable Long id )
			   throws Exception   {
		    User user  = userService.findUserbyJwttoken(jwt);
		   
		    RestaurantDto restaurant = restaurantService.addToFavorites(id, user);
  		    return new  ResponseEntity<>(restaurant , HttpStatus.OK);
	   }
	   
	   
	   
	   
	   
	   
	   
	   
}
