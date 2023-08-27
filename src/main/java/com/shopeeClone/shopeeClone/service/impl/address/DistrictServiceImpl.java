package com.shopeeClone.shopeeClone.service.impl.address;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopeeClone.shopeeClone.converter.AddressConverter;
import com.shopeeClone.shopeeClone.dto.address.DistrictDTO;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.entity.AddressEntity;
import com.shopeeClone.shopeeClone.entity.DistrictEntity;
import com.shopeeClone.shopeeClone.repository.address.AddressRepository;
import com.shopeeClone.shopeeClone.repository.address.DistrictRepository;
import com.shopeeClone.shopeeClone.repository.address.ProvinceRepository;
import com.shopeeClone.shopeeClone.service.DistrictService;
import com.shopeeClone.shopeeClone.utils.validate;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {
	
	@Autowired
	private AddressConverter converter;
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public DistrictDTO create(DistrictDTO dto) {
		DistrictEntity districtEntity = converter.toEntity(dto);
		districtRepository.save(districtEntity);
		return converter.toDTO(districtEntity);
	}
	
	@Override
	public List<DistrictDTO> getAll() {
		List<DistrictEntity> entities = districtRepository.findAll();
		List<DistrictDTO> dtos = new ArrayList<DistrictDTO>();
		for (DistrictEntity districtEntity : entities) {
			DistrictDTO dto = new DistrictDTO();
			dto = converter.toDTO(districtEntity);
			dtos.add(dto);
		}
		return dtos;
	}
	
	@Override
	public void delete(String id) {
		Long districtId = validate.validateId(id);
		DistrictEntity entity = districtRepository.findById(districtId).orElseThrow(() -> new ValidateException("District not found"));
		if(!addressRepository.findByDistrict(entity).isEmpty()) {
			List<AddressEntity> addressEntities = addressRepository.findByDistrict(entity);
			for (AddressEntity addressEntity : addressEntities) {
				addressEntity.setDistrict(entity);
				addressRepository.save(addressEntity);
			}			
		}
		entity.setProvince(null);
		districtRepository.delete(entity);
	}

	@Override
	public DistrictDTO update(DistrictDTO dto, String id) {
		Long districtId = validate.validateId(id);
		DistrictEntity entity = districtRepository.findById(districtId).orElseThrow(() -> new ValidateException("District not found"));
		entity.setCode(dto.getCode());
		entity.setName(dto.getName());
		districtRepository.save(entity);
		return converter.toDTO(entity);
	}

	@Override
	public List<DistrictDTO> getByProvinceId(Long provinceId) {
		List<DistrictEntity> entities = districtRepository.findByProvinceId(provinceId);
		List<DistrictDTO> dtos = new ArrayList<DistrictDTO>();
		for (DistrictEntity entity : entities) {
			DistrictDTO dto = new DistrictDTO();
			dto = converter.toDTO(entity);
			dtos.add(dto);
		}
		return dtos;
	}


}
