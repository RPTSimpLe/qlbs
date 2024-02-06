package com.shopeeClone.shopeeClone.repository;

import com.shopeeClone.shopeeClone.entity.CategoryEntity;
import com.shopeeClone.shopeeClone.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RateRepository extends JpaRepository<RateEntity,Long> {
    @Query("select r from  RateEntity r where r.entity.productId = :productId")
    List<RateEntity> findByProductId(@Param(value = "productId") Long productId);
}
