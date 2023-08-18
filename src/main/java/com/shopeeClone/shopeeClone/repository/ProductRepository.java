package com.shopeeClone.shopeeClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeeClone.shopeeClone.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    
}
