package com.shopeeClone.shopeeClone.repository.address;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeeClone.shopeeClone.entity.WardEntity;

public interface WardRepository extends JpaRepository<WardEntity, Long> {
    Optional<WardEntity> findByName(String name);
}
