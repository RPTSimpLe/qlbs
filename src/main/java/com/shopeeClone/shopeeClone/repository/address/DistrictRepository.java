package com.shopeeClone.shopeeClone.repository.address;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shopeeClone.shopeeClone.entity.DistrictEntity;

public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {
    Optional<DistrictEntity> findByName(String name);
    
    @Query("SELECT d FROM DistrictEntity d WHERE d.province.provinceId = :provinceId")
    List<DistrictEntity> findByProvinceId(@Param("provinceId") Long provinceId);
}
