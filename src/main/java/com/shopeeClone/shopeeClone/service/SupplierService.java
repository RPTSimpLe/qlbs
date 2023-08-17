package com.shopeeClone.shopeeClone.service;

import java.util.Map;

import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.SupplierDTO;

public interface SupplierService {

	SupplierDTO create(SupplierDTO dto);

	PageDTO<SupplierDTO> getSupplier(Map<String, String> params);

	SupplierDTO update(String id, SupplierDTO dto);

	void delete(Long id);

	SupplierDTO getSupplierBySupplierId(String supplierId);

}
