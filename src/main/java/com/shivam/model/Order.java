package com.shivam.model;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
  
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private User customer;
	
	@JsonIgnore
	@ManyToOne
	private Restaurant  restaurant;
	
	
	private Long totalAmount;
	
	private String orderStatus;
	
	private Date createdAt;
	
	
	@ManyToOne
	private Address delivaryAddress;
	
	@OneToMany
	private List<Orderitem> items; 
	
	
	//private Payment paymentMethod;
	
	
	private int totalItem;
	
	
	private int totalPrice;
	
	
	
	
	
	
	
	
	
}
