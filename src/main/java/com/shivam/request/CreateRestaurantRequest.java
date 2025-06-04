package com.shivam.request;

import java.util.List;

import com.shivam.model.Address;
import com.shivam.model.ContactInformation;

import lombok.Data;

@Data
public class CreateRestaurantRequest {

	private Long id ; 
	
	private String name;
	
	private String description;
	
	private String cuisineType;
	
	private Address address;
	
	private ContactInformation contactInformation;
	
	private String openingHours;
	
	private List<String> images;
	
	
	
	
}
