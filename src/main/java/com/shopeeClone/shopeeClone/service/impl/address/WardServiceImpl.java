package com.shopeeClone.shopeeClone.service.impl.address;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopeeClone.shopeeClone.converter.AddressConverter;
import com.shopeeClone.shopeeClone.dto.address.WardDTO;
import com.shopeeClone.shopeeClone.entity.AddressEntity;
import com.shopeeClone.shopeeClone.entity.WardEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.address.AddressRepository;
import com.shopeeClone.shopeeClone.repository.address.WardRepository;
import com.shopeeClone.shopeeClone.service.WardService;
import com.shopeeClone.shopeeClone.utils.AppStringUtils;
import com.shopeeClone.shopeeClone.utils.validate;

@Service
public class WardServiceImpl implements WardService {
	@Autowired
	private WardRepository wardRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AddressConverter converter;
	
	@Override
	public WardDTO create(WardDTO dto) {
		WardEntity wardEntity = converter.toEntity(dto);
		wardRepository.save(wardEntity);
		return converter.toDTO(wardEntity);
	}

	@Override
	public List<WardDTO> getAll() {
		List<WardEntity> entities = wardRepository.findAll();
		List<WardDTO> dtos = new ArrayList<WardDTO>();
		for (WardEntity wardEntity : entities) {
			WardDTO dto = new WardDTO();
			dto = converter.toDTO(wardEntity);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public void delete(String id) {
		Long wardId = validate.validateId(id);
		if(AppStringUtils.hasNumber(wardId)) {
			throw new ValidateException("id la 1 so");
		}
		WardEntity ward = wardRepository.findById(wardId).orElseThrow(() -> new ValidateException("Ward not found"));
		if(!addressRepository.findByWard(ward).isEmpty()) {
			List<AddressEntity> addressEntities = addressRepository.findByWard(ward);
			for (AddressEntity addressEntity : addressEntities) {
				addressEntity.setWard(null);
				addressRepository.save(addressEntity);
			}			
		}
		ward.setDistrict(null);
		wardRepository.delete(ward);
	}
	
	@Override
	public WardDTO update(WardDTO dto, String id) {
		Long wardId = validate.validateId(id);
		WardEntity entity = wardRepository.findById(wardId).orElseThrow(() -> new ValidateException("Ward not found"));
		entity.setCode(dto.getCode());
		entity.setName(dto.getName());
		wardRepository.save(entity);
		return converter.toDTO(entity);
	}

	@Override
	public List<WardDTO> getByProvinceWardId(Long districtId) {
			List<WardEntity> entities = wardRepository.findByDistrictId(districtId);
			List<WardDTO> dtos = new ArrayList<>();
			for (WardEntity entity : entities) {
				WardDTO dto = new WardDTO();
				dto = converter.toDTO(entity);
				dtos.add(dto);
			}
		return dtos;
	}
}
