package com.shivam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.config.JwtProvider;
import com.shivam.model.User;
import com.shivam.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository ;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Override
	public User findUserbyJwttoken(String jwt) throws Exception {
		
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		
		User user = findUserByEmail(email);
		
		return user;
	}

	
	@Override
	public User findUserByEmail(String email) throws Exception {
		User user = userRepository.findByEmail(email);
		
		if(user == null ) {
			throw new Exception("User not found");
		}
		
		return user;
	}

	
}
