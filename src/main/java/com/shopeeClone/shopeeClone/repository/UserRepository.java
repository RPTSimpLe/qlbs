package com.shopeeClone.shopeeClone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shopeeClone.shopeeClone.entity.RoleEntity;
import com.shopeeClone.shopeeClone.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
//	@Query("SELECT r FROM UserEntity r WHERE r.username = :username")
//	List<UserEntity> findByUserName(@Param(value ="username") String username);
	
	@Query("SELECT r FROM UserEntity r WHERE r.username = :username")
	Optional<UserEntity> findByUserName(@Param(value ="username") String username);
}
