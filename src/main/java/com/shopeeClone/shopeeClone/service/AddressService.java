package com.shopeeClone.shopeeClone.service;

import java.util.List;

import com.shopeeClone.shopeeClone.dto.address.AddressDTO;
import com.shopeeClone.shopeeClone.dto.address.CreateAddressForm;


public interface AddressService {

	AddressDTO create(CreateAddressForm form, Long userId);

	List<AddressDTO> getAll();

	void delete(String id, Long userId);

	AddressDTO update(CreateAddressForm form, Long id);

}
