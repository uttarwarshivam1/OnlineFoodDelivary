package com.shivam.service;

import com.shivam.model.User;

public interface UserService {

	public User findUserbyJwttoken (String jwt) throws Exception;
		
		public User findUserByEmail (String email) throws Exception ;
	
}
