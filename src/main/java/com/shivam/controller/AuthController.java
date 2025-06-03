package com.shivam.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.config.JwtProvider;
import com.shivam.model.Cart;
import com.shivam.model.USER_ROLE;
import com.shivam.model.User;
import com.shivam.repository.CartRepository;
import com.shivam.repository.UserRepository;
import com.shivam.request.LoginRequest;
import com.shivam.response.AuthResponse;
import com.shivam.service.CustomerUserDetailsService;





@RestController
@RequestMapping("/auth")
public class AuthController {
    
	
	@Autowired       
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private CustomerUserDetailsService customerUserDetailsService ;
	
	@Autowired
	private CartRepository cartRepository;
	
	
	
	
	
	
	@PostMapping("/signUp")
	public  ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception{
	
		User isEmailExist = userRepository.findByEmail(user.getEmail());
		
		if(isEmailExist != null) {
			throw new Exception("Email is already used with another account");
		}
		
		
		User createdUser = new User();
		
		createdUser.setEmail(user.getEmail());
		createdUser.setFullname(user.getFullname());
		createdUser.setRole(user.getRole());
		createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser = userRepository.save(createdUser);
		
		Cart cart = new Cart();
		
		cart.setCustomer(savedUser);
		cartRepository.save(cart);
		
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtProvider.generatetoken(authentication);
		AuthResponse authResponse = new  AuthResponse();
		
		
		authResponse.setJwt(jwt);
		authResponse.setMessage("Registration success");
		authResponse.setRole(savedUser.getRole());
		
		
		return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
	}
	
	
	@PostMapping("/signIn")
    public ResponseEntity<AuthResponse> signIn(@RequestBody LoginRequest request)  {
		
		String userName = request.getEmail();
		String passWord = request.getPassword();
		
		Authentication authentication = authenticate(userName , passWord);
		
		Collection<? extends GrantedAuthority>authorities=authentication.getAuthorities();
		String role=authorities.isEmpty()?null:authorities.iterator().next().getAuthority(); 
		String jwt = jwtProvider.generatetoken(authentication);
		AuthResponse authResponse = new  AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("Login successfull");
		authResponse.setRole(USER_ROLE.valueOf(role));
		
		
		
		return new ResponseEntity<>(authResponse, HttpStatus.OK);
		
		
		
		
	}


private Authentication authenticate(String userName, String passWord) {
	UserDetails userDetails  = customerUserDetailsService.loadUserByUsername(userName);
	
	if(userDetails == null) {
		throw new BadCredentialsException("Invalid username ...");
	}
	
	if(!passwordEncoder.matches(passWord,userDetails.getPassword())) {
		throw new BadCredentialsException("Invalid Password...");
	}
	
	
	
	return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
}
	
	
	
	
	
	
	 
}
