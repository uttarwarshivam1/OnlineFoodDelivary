package com.shivam.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.dto.RestaurantDto;
import com.shivam.model.Address;
import com.shivam.model.Restaurant;
import com.shivam.model.User;
import com.shivam.repository.AddressRespository;
import com.shivam.repository.RestaurantRespositiory;
import com.shivam.repository.UserRepository;
import com.shivam.request.CreateRestaurantRequest;


@Service
public class RestaurantServiceImpl implements RestaurantService {

	  @Autowired
	  private RestaurantRespositiory restaurantRespositiory ;
	  
	  @Autowired
	  private AddressRespository addressRespository ;
	  
	  @Autowired
	  private UserRepository userRepository;
	
	
	  
	  
	  
	  
	@Override
	public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
		Address  address  = addressRespository.save(req.getAddress());
		
		Restaurant restaurant = new Restaurant();
		restaurant.setAddress(address);
		restaurant.setContactInformation(req.getContactInformation());
		restaurant.setCuisineType(req.getCuisineType());
		restaurant.setDescription(req.getDescription());
		restaurant.setImages(req.getImages());
		restaurant.setName(req.getName());
		restaurant.setOpeningHours(req.getOpeningHours());
		restaurant.setRegistrationDate(LocalDateTime.now());
		restaurant.setOwner(user);
		
		
		
		return restaurantRespositiory.save(restaurant);
	}
	
	
	
	
	
	@Override
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
		 
		Restaurant restaurant = findRestaurantById(restaurantId);
		
		if (restaurant.getCuisineType() != null ) {
			restaurant.setCuisineType(updatedRestaurant.getCuisineType());
		}
		
		if(restaurant.getDescription() != null) {
			restaurant.setDescription(updatedRestaurant.getDescription());
		}
		
		if(restaurant.getName() != null) {
			restaurant.setName(updatedRestaurant.getName());
		}
		
	
		return restaurantRespositiory.save(restaurant);
		
		
	}
	
	
	
	
	@Override
	public void deleteRestaurant(Long restaurantId) throws Exception {
		 
		Restaurant restaurant = findRestaurantById(restaurantId);
		
		restaurantRespositiory.delete(restaurant);
		
	}
	
	
	
	
	@Override
	public List<Restaurant> getAllRestaurant() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public List<Restaurant> searchRestaurant(String Keyword) {
	   	return restaurantRespositiory.findBySearchQuery(Keyword);
	}

	
	
	@Override
	public Restaurant findRestaurantById(Long restaurantId) throws Exception {
		  Optional<Restaurant> opt = restaurantRespositiory.findById(restaurantId);
		  if (opt.isEmpty()) {
			  throw new Exception("Restaurant not found with Id : "+restaurantId);
		  }
		  	return opt.get();
	}


	
	@Override
	public Restaurant getRestaurantbyUserId(Long userId) throws Exception {
         Restaurant restaurant = restaurantRespositiory.findByOwnerId(userId);
         if(restaurant == null) {
        	 throw new Exception("Restaurant is not found with ownerId  : " +userId);
         }
         return restaurant;
	}

	
	
	
	
	
	
	@Override
	public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
		 
		  Restaurant restaurant = findRestaurantById(restaurantId);
		  
		  RestaurantDto  dto = new RestaurantDto();
		  
		  dto.setDescription(restaurant.getDescription());
		  dto.setImages(restaurant.getImages());
		  dto.setTitle(restaurant.getName());
		  dto.setId(restaurantId);
		  
		  if(user.getFavorites().contains(dto)) {
			  user.getFavorites().remove(dto);
		  }
		  else
			  user.getFavorites().add(dto);
		  
		  userRepository.save(user);
		  return dto;
	}
	
	
	
	

	@Override
	public Restaurant updateRestaurantStatus(Long id) throws Exception {
	
		Restaurant restaurant = findRestaurantById(id);
		restaurant.setOpen(!restaurant.isOpen());
		return  restaurantRespositiory.save(restaurant);
				
	}
	
	
	
	
	
	

}
