package com.shopeeClone.shopeeClone.service;

import java.util.List;

import com.shopeeClone.shopeeClone.dto.AddressDTO;


public interface AddressService {

	AddressDTO create(AddressDTO dto, Long userId);

	List<AddressDTO> getAll();

	void delete(String id, Long userId);

	AddressDTO update(AddressDTO dto, String id);

}
