package com.shopeeClone.shopeeClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeeClone.shopeeClone.entity.CartEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    @Query("SELECT r FROM CartEntity r where r.user.userId = :uId")
    List<CartEntity> findAllByUId(@Param("uId") Long uId);

    @Query("select count(r) from CartEntity r where r.user.userId =:uId")
    Long countCart(@Param("uId") Long uId);
}
