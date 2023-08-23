package com.shopeeClone.shopeeClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeeClone.shopeeClone.entity.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity,Long>{
    
}
