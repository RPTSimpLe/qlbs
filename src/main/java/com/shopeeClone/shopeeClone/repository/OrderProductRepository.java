package com.shopeeClone.shopeeClone.repository;

import java.util.List;

import com.shopeeClone.shopeeClone.dto.user.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shopeeClone.shopeeClone.entity.AddressEntity;
import com.shopeeClone.shopeeClone.entity.DistrictEntity;
import com.shopeeClone.shopeeClone.entity.OrderEntity;
import com.shopeeClone.shopeeClone.entity.OrderProductEntity;

public interface OrderProductRepository  extends JpaRepository<OrderProductEntity, Long> {
	@Query("select r from OrderProductEntity r join r.order q where q =: order")
	List<OrderProductEntity> findByOrder(@Param ("order") OrderEntity order);	
	
	List<OrderProductEntity> findByOrderProductId(Long orderProductId);
	@Query("select r from OrderProductEntity r join r.order o where o.user.userId =:userId")
    List<OrderProductEntity> findByUser(Long userId);
}
