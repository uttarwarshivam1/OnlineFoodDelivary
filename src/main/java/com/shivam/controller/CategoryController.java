package com.shivam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.model.Category;
import com.shivam.model.User;
import com.shivam.service.CategoryService;
import com.shivam.service.UserService;

@RestController
@RequestMapping("/api/admin/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@Autowired
	private UserService userService ;
	
	
	@PostMapping("/createcategory")
	public ResponseEntity<Category> createCategory(@RequestBody Category category  , 
			                                       @RequestHeader("Authorization")String jwt) throws Exception  
	{
			                                    	
	User user = userService.findUserbyJwttoken(jwt);
	Category createdCategory = categoryService.createCategory(category.getName(),user.getId() );
	
	return new ResponseEntity<>(createdCategory , HttpStatus.CREATED);
	
	 }
	
	
	
	@GetMapping("/get/restaurant/category")
	public ResponseEntity<List<Category>> getRestaurantCategory(@RequestHeader("Authorization")String jwt) throws Exception  
	{
			                                    	
	User user = userService.findUserbyJwttoken(jwt);
	List<Category> categories = categoryService.findCategoryByRestaurantId(user.getId());
	
	return new ResponseEntity<>(categories , HttpStatus.CREATED);
	
	 }
	
	
			                                       

     
    
}
