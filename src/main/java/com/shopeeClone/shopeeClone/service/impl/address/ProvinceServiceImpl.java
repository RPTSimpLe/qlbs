package com.shopeeClone.shopeeClone.service.impl.address;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopeeClone.shopeeClone.converter.AddressConverter;
import com.shopeeClone.shopeeClone.dto.address.ProvinceDTO;
import com.shopeeClone.shopeeClone.entity.AddressEntity;
import com.shopeeClone.shopeeClone.entity.ProvinceEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.address.AddressRepository;
import com.shopeeClone.shopeeClone.repository.address.ProvinceRepository;
import com.shopeeClone.shopeeClone.service.ProvinceService;
import com.shopeeClone.shopeeClone.utils.validate;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {
	
	@Autowired
	private ProvinceRepository provinceRepository;
	@Autowired
	private AddressConverter converter;
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public ProvinceDTO create(ProvinceDTO dto) {
		ProvinceEntity provinceEntity = converter.toEntity(dto);
		provinceRepository.save(provinceEntity);
		return converter.toDTO(provinceEntity);
	}
	
	@Override
	public List<ProvinceDTO> getAll() {
		List<ProvinceEntity> entities = provinceRepository.findAll();
		List<ProvinceDTO> dtos = new ArrayList<ProvinceDTO>();
		for (ProvinceEntity provinceEntity : entities) {
			ProvinceDTO dto = new ProvinceDTO();
			dto = converter.toDTO(provinceEntity);
			dtos.add(dto);
		}
		return dtos;
	}
	
	@Override
	public void delete(String id) {
		Long provinceId = validate.validateId(id);
		ProvinceEntity entity = provinceRepository.findById(provinceId).orElseThrow(() -> new ValidateException("province not found"));
		if(!addressRepository.findByProvince(entity).isEmpty()) {
			List<AddressEntity> addressEntities = addressRepository.findByProvince(entity);
			for (AddressEntity addressEntity : addressEntities) {
				addressEntity.setWard(null);
				addressRepository.save(addressEntity);
			}			
		}
		provinceRepository.delete(entity);
	}
	
	@Override
	public ProvinceDTO update(ProvinceDTO dto, String id) {
		Long provinceId = validate.validateId(id);
		ProvinceEntity entity = provinceRepository.findById(provinceId).orElseThrow(() -> new ValidateException("province not found"));
		entity.setCode(dto.getCode());
		entity.setName(dto.getName());
		provinceRepository.save(entity);
		return converter.toDTO(entity);
	}
}
