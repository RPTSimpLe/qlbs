package com.shopeeClone.shopeeClone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shopeeClone.shopeeClone.entity.AddressEntity;
import com.shopeeClone.shopeeClone.entity.OrderEntity;
import com.shopeeClone.shopeeClone.entity.OrderProductEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	@Query("select r from OrderEntity r join r.address q where q =: address")
	List<OrderEntity> findByAddress(@Param ("address") AddressEntity address);	
}
