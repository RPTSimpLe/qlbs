package com.shopeeClone.shopeeClone.repository.address;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shopeeClone.shopeeClone.entity.DistrictEntity;
import com.shopeeClone.shopeeClone.entity.WardEntity;

public interface WardRepository extends JpaRepository<WardEntity, Long> {
    Optional<WardEntity> findByName(String name);
    
    @Query("SELECT d FROM WardEntity d WHERE d.district.districtId = :districtId")
    List<WardEntity> findByDistrictId(@Param("districtId") Long districtId);
}
