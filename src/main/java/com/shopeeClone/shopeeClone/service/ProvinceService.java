package com.shopeeClone.shopeeClone.service;

import java.util.List;

import com.shopeeClone.shopeeClone.dto.address.ProvinceDTO;

public interface ProvinceService {

	ProvinceDTO create(ProvinceDTO dto);

	List<ProvinceDTO> getAll();

	void delete(String id);

	ProvinceDTO update(ProvinceDTO dto, String id);

}
