package com.shopeeClone.shopeeClone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shopeeClone.shopeeClone.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	@Query("SELECT r FROM RoleEntity r WHERE r.code = :code")
	Optional<RoleEntity> findByCode(@Param(value ="code") String code);
	
	@Query("select r from RoleEntity r where r.name =: name")
	Optional<RoleEntity> findByName(@Param (value = "name") String name);	
	
//	@Query("select r from RoleEntity r join r.user r where r.user =: user")
//	List<RoleEntity> findByUser(@Param (value = "user") UserEntity user);
//	
}	
