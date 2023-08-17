package com.shopeeClone.shopeeClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeeClone.shopeeClone.entity.OrderProductEntity;

public interface OrderProductRepository  extends JpaRepository<OrderProductEntity, Long> {

}
