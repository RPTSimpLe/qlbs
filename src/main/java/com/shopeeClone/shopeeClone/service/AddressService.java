package com.shopeeClone.shopeeClone.service;

import java.util.List;

import com.shopeeClone.shopeeClone.dto.AddressDTO;
import com.shopeeClone.shopeeClone.dto.DistrictDTO;
import com.shopeeClone.shopeeClone.entity.DistrictEntity;

public interface AddressService {

	AddressDTO create(AddressDTO dto);

	List<AddressDTO> getAll();

	void delete(String id);

	AddressDTO update(AddressDTO dto, String id);

}
