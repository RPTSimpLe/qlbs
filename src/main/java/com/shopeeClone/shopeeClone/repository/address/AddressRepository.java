package com.shopeeClone.shopeeClone.repository.address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shopeeClone.shopeeClone.entity.AddressEntity;
import com.shopeeClone.shopeeClone.entity.DistrictEntity;
import com.shopeeClone.shopeeClone.entity.ProvinceEntity;
import com.shopeeClone.shopeeClone.entity.WardEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
	
	@Query("SELECT a FROM AddressEntity a JOIN a.ward w WHERE w = :ward")
	List<AddressEntity> findByWard(@Param("ward") WardEntity ward);
	
	@Query("select a from AddressEntity a join a.district w where w = :district")
	List<AddressEntity> findByDistrict(@Param("district") DistrictEntity district);
	
	@Query("select a from AddressEntity a join a.province w where w = :province")
	List<AddressEntity> findByProvince(@Param("province") ProvinceEntity province);
}
