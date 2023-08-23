package com.shopeeClone.shopeeClone.repository.address;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeeClone.shopeeClone.entity.ProvinceEntity;

public interface ProvinceRepository extends JpaRepository<ProvinceEntity, Long> {
    Optional<ProvinceEntity> findByName(String name);
}
