package com.shivam.service;

import java.util.List;

import com.shivam.dto.RestaurantDto;
import com.shivam.model.Restaurant;
import com.shivam.model.User;
import com.shivam.request.CreateRestaurantRequest;

public interface RestaurantService {
   
	                      public Restaurant  createRestaurant  (CreateRestaurantRequest req, User user );
	                      
	                      public Restaurant updateRestaurant (Long restaurantId , CreateRestaurantRequest updatedRestaurant) throws Exception ;
	                      
	                      public void deleteRestaurant (Long restaurantId) throws Exception ;
	                      
	                      public List<Restaurant> getAllRestaurant();
	                      
	                      public List<Restaurant> searchRestaurant( String Keyword);
	                      
	                      public Restaurant findRestaurantById(Long id)  throws Exception ;
	                      
	                      public Restaurant getRestaurantbyUserId(Long userId) throws Exception;
	                      
	                      public RestaurantDto addToFavorites(Long restaurantId , User user) throws Exception;
	                      
	                      public Restaurant updateRestaurantStatus(Long id ) throws Exception; 
	                      
	                      
}
