package com.shivam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.model.Restaurant;
import com.shivam.model.User;
import com.shivam.request.CreateRestaurantRequest;
import com.shivam.response.Messageresponse;
import com.shivam.service.RestaurantService;
import com.shivam.service.UserService;




@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminController {
	
	
      @Autowired     
	   private RestaurantService restaurantService ; 
      
	   @Autowired
	   private UserService userService ; 
	   
	   @PostMapping 
	   public ResponseEntity<Restaurant> createRestaurant ( @RequestBody   CreateRestaurantRequest req , 
			                                                                                                 @RequestHeader("Authorization" ) String jwt ) throws Exception
	   {
		    User user  = userService.findUserbyJwttoken(jwt);
		   
		   Restaurant restaurant = restaurantService.createRestaurant(req, user);
		   return new  ResponseEntity<>(restaurant , HttpStatus.CREATED);
	   }
	   
	   
	   
	   @PutMapping("/{id}") 
	   public ResponseEntity<Restaurant> updateRestaurant ( @RequestBody   CreateRestaurantRequest req , 
			                                                                                                   @RequestHeader("Authorization" ) String jwt, 
			                                                                                                   @PathVariable Long id) throws Exception
	   {
		   User user  = userService.findUserbyJwttoken(jwt);
		  
		   Restaurant restaurant = restaurantService.updateRestaurant(id, req);
		   return new  ResponseEntity<>(restaurant , HttpStatus.CREATED);
		   
	   }
	   
	   
	   
	   
	   @DeleteMapping("/{id}") 
	   public ResponseEntity<Messageresponse> DeleteRestaurant ( @RequestBody   CreateRestaurantRequest req , 
			                                                                                                   @RequestHeader("Authorization" ) String jwt, 
			                                                                                                   @PathVariable Long id) throws Exception
	   {
		   User user  = userService.findUserbyJwttoken(jwt);
		  restaurantService.deleteRestaurant(id);
		   
           Messageresponse res = new Messageresponse();
		   res.setMessage("Restaurant is deleted successfully ");
		   
		   return new  ResponseEntity<>( res , HttpStatus.OK);
		   
	   }
	   
	   
	   
	   
	   @PutMapping("/{id}/status") 
	   public ResponseEntity<Restaurant> updateReataurantStatus ( @RequestBody   CreateRestaurantRequest req , 
			                                                                                                   @RequestHeader("Authorization" ) String jwt, 
			                                                                                                   @PathVariable Long id) throws Exception
	   {
		   User user  = userService.findUserbyJwttoken(jwt);
		  
		 Restaurant restaurant =   restaurantService.updateRestaurantStatus(id);
		   return new  ResponseEntity<>( restaurant , HttpStatus.OK);
		   
	   }
	   
	   
	   
	   
	   @GetMapping("/{id}") 
	   public ResponseEntity<Restaurant> findReataurantByUserId ( @RequestBody   CreateRestaurantRequest req , 
			                                                                                                   @RequestHeader("Authorization" ) String jwt
			                                                                                                   ) throws Exception
	   {
		   User user  = userService.findUserbyJwttoken(jwt);
		  
		   Restaurant restaurant =   restaurantService.getRestaurantbyUserId(user.getId());
		   return new  ResponseEntity<>( restaurant , HttpStatus.OK);
		   
	   }
	   
	   
	   
	   
}
