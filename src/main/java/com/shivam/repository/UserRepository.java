package com.shivam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivam.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
 
	       public User findByEmail(String username);
	       
	       
}
