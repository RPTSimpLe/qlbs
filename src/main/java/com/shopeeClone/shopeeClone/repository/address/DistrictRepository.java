package com.shopeeClone.shopeeClone.repository.address;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeeClone.shopeeClone.entity.DistrictEntity;

public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {
    Optional<DistrictEntity> findByName(String name);
}
