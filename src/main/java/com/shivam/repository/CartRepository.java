package com.shivam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivam.model.Cart;




public interface CartRepository extends JpaRepository<Cart, Long> {

}
