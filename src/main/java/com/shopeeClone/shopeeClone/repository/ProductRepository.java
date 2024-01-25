package com.shopeeClone.shopeeClone.repository;

import com.shopeeClone.shopeeClone.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeeClone.shopeeClone.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    @Query("SELECT DISTINCT p FROM ProductEntity p ")
    List<ProductEntity> findAllProAndImg();
}
