package com.shopeeClone.shopeeClone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeeClone.shopeeClone.entity.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	Optional<CategoryEntity> findByName(String name);

	@Query("SELECT COUNT(r) FROM ProductEntity r JOIN r.category q WHERE q = :category")
	Optional<Integer> countProductByCate(@Param("category") CategoryEntity category);

}
