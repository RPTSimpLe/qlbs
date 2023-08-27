package com.shopeeClone.shopeeClone.service;

import java.util.List;

import com.shopeeClone.shopeeClone.dto.address.DistrictDTO;

public interface DistrictService {

	DistrictDTO create(DistrictDTO dto);

	List<DistrictDTO> getAll();

	void delete(String id);

	DistrictDTO update(DistrictDTO dto, String id);

	List<DistrictDTO> getByProvinceId(Long provinceId);
}
