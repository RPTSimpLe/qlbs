package com.shopeeClone.shopeeClone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopeeClone.shopeeClone.entity.SupplierEntity;
import org.springframework.data.jpa.repository.Query;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Long>  {
    Optional<SupplierEntity> findByName(String name);
}
