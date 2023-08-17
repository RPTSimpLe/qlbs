package com.shopeeClone.shopeeClone.service;

import java.util.List;

import com.shopeeClone.shopeeClone.dto.WardDTO;

public interface WardService {

	WardDTO create(WardDTO dto);

	List<WardDTO> getAll();

	void delete(String id);

	WardDTO update(WardDTO dto, String id);

}
