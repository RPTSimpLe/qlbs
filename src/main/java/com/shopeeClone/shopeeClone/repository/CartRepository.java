package com.shopeeClone.shopeeClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeeClone.shopeeClone.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
	
}
