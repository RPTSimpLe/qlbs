package com.shopeeClone.shopeeClone.converter;

import org.springframework.stereotype.Component;

import com.shopeeClone.shopeeClone.dto.AddressDTO;
import com.shopeeClone.shopeeClone.dto.DistrictDTO;
import com.shopeeClone.shopeeClone.dto.ProvinceDTO;
import com.shopeeClone.shopeeClone.dto.WardDTO;
import com.shopeeClone.shopeeClone.entity.AddressEntity;
import com.shopeeClone.shopeeClone.entity.DistrictEntity;
import com.shopeeClone.shopeeClone.entity.ProvinceEntity;
import com.shopeeClone.shopeeClone.entity.WardEntity;

@Component
public class AddressConverter {
	
	public AddressDTO toDto(AddressEntity addressEntity) {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setDistrictName(addressEntity.getDistrict().getName());
		addressDTO.setProvinceName(addressEntity.getProvince().getName());
		addressDTO.setWardName(addressEntity.getWard().getName());
		addressDTO.setDescription(addressEntity.getDescription());
		return addressDTO;
	}
	
	public AddressEntity toEntity(AddressDTO dto) {
		AddressEntity addressEntityity = new AddressEntity();
		addressEntityity.setDescription(dto.getDescription());
		return addressEntityity;
	}
	
	public ProvinceEntity toEntity(ProvinceDTO dto) {
		ProvinceEntity provinceEntity = new ProvinceEntity();
		provinceEntity.setName(dto.getName());
		provinceEntity.setCode(dto.getCode());
		return provinceEntity;
	}
	
	public WardEntity toEntity(WardDTO dto) {
		WardEntity wardEntity = new WardEntity();
		wardEntity.setName(dto.getName());
		wardEntity.setCode(dto.getCode());
		return wardEntity;
	}
	
	public DistrictEntity toEntity(DistrictDTO dto) {
		DistrictEntity districtEntity = new DistrictEntity();
		districtEntity.setName(dto.getName());
		districtEntity.setCode(dto.getCode());
		return districtEntity;
	}
	
	public ProvinceDTO toDTO(ProvinceEntity entity) {
		ProvinceDTO provinceDTO = new ProvinceDTO();
		provinceDTO.setName(entity.getName());
		provinceDTO.setCode(entity.getCode());
		return provinceDTO;
	}
	
	public WardDTO toDTO(WardEntity dto) {
		WardDTO wardDTO = new WardDTO();
		wardDTO.setName(dto.getName());
		wardDTO.setCode(dto.getCode());
		return wardDTO;
	}
	
	public DistrictDTO toDTO(DistrictEntity dto) {
		DistrictDTO districtDTO = new DistrictDTO();
		districtDTO.setName(dto.getName());
		districtDTO.setCode(dto.getCode());
		return districtDTO;
	}
}
