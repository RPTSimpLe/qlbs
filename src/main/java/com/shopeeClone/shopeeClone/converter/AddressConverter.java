package com.shopeeClone.shopeeClone.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopeeClone.shopeeClone.dto.address.AddressDTO;
import com.shopeeClone.shopeeClone.dto.address.CreateAddressForm;
import com.shopeeClone.shopeeClone.dto.address.DistrictDTO;
import com.shopeeClone.shopeeClone.dto.address.ProvinceDTO;
import com.shopeeClone.shopeeClone.dto.address.WardDTO;
import com.shopeeClone.shopeeClone.entity.AddressEntity;
import com.shopeeClone.shopeeClone.entity.DistrictEntity;
import com.shopeeClone.shopeeClone.entity.ProvinceEntity;
import com.shopeeClone.shopeeClone.entity.UserEntity;
import com.shopeeClone.shopeeClone.entity.WardEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.UserRepository;
import com.shopeeClone.shopeeClone.repository.address.DistrictRepository;
import com.shopeeClone.shopeeClone.repository.address.ProvinceRepository;
import com.shopeeClone.shopeeClone.repository.address.WardRepository;

@Component
public class AddressConverter {
	@Autowired
	private ProvinceRepository provinceRepository;
	@Autowired
	private WardRepository wardRepository;
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private UserRepository userRepository;
	
	public AddressDTO toDto(AddressEntity addressEntity) {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddress(addressEntity.getDescription()+"-"+addressEntity.getWard().getName()+"-"+addressEntity.getDistrict().getName()+"-"+addressEntity.getProvince().getName());
		return addressDTO;
	}
	
	public AddressEntity toEntity(CreateAddressForm form) {
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setDescription(form.getDescription());
		ProvinceEntity provinceEntity = provinceRepository.findById(form.getProvinceId())
                .orElseThrow(() -> new ValidateException("Province not found"));
		DistrictEntity districtEntity = districtRepository.findById(form.getDistrictId())
                .orElseThrow(() -> new ValidateException("district not found"));
		WardEntity wardEntity = wardRepository.findById(form.getWardId())
                .orElseThrow(() -> new ValidateException("ward not found"));
		addressEntity.setDescription(form.getDescription());
		addressEntity.setProvince(provinceEntity);
		addressEntity.setWard(wardEntity);
		addressEntity.setDistrict(districtEntity);
		return addressEntity;
	}
	
	public ProvinceEntity toEntity(ProvinceDTO dto) {
		ProvinceEntity provinceEntity = new ProvinceEntity();
		provinceEntity.setName(dto.getName());
		provinceEntity.setCode(dto.getCode());
		return provinceEntity;
	}
	
	public WardEntity toEntity(WardDTO dto) {
		WardEntity wardEntity = new WardEntity();
		DistrictEntity districtEntity = districtRepository.findById(dto.getDistrictId())
                .orElseThrow(() -> new ValidateException("district not found"));
		wardEntity.setDistrict(districtEntity);
		wardEntity.setName(dto.getName());
		wardEntity.setCode(dto.getCode());
		return wardEntity;
	}
	
	public DistrictEntity toEntity(DistrictDTO dto) {
		DistrictEntity districtEntity = new DistrictEntity();
		ProvinceEntity provinceEntity = provinceRepository.findById(dto.getProvinceId())
                .orElseThrow(() -> new ValidateException("Province not found"));
		districtEntity.setProvince(provinceEntity);
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
		wardDTO.setDistrictId(dto.getDistrict().getDistrictId());
		wardDTO.setName(dto.getName());
		wardDTO.setCode(dto.getCode());
		return wardDTO;
	}
	
	public DistrictDTO toDTO(DistrictEntity dto) {
		DistrictDTO districtDTO = new DistrictDTO();
		districtDTO.setProvinceId(dto.getProvince().getProvinceId());
		districtDTO.setName(dto.getName());
		districtDTO.setCode(dto.getCode());
		return districtDTO;
	}
}
