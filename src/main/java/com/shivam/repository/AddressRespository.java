package com.shivam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivam.model.Address;

public interface AddressRespository extends JpaRepository<Address, Long> {

}
