package com.shopeeClone.shopeeClone.service.impl.address;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopeeClone.shopeeClone.converter.AddressConverter;
import com.shopeeClone.shopeeClone.dto.address.AddressDTO;
import com.shopeeClone.shopeeClone.dto.address.CreateAddressForm;
import com.shopeeClone.shopeeClone.entity.AddressEntity;
import com.shopeeClone.shopeeClone.entity.DistrictEntity;
import com.shopeeClone.shopeeClone.entity.OrderEntity;
import com.shopeeClone.shopeeClone.entity.ProvinceEntity;
import com.shopeeClone.shopeeClone.entity.UserEntity;
import com.shopeeClone.shopeeClone.entity.WardEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.OrderRepository;
import com.shopeeClone.shopeeClone.repository.UserRepository;
import com.shopeeClone.shopeeClone.repository.address.AddressRepository;
import com.shopeeClone.shopeeClone.repository.address.DistrictRepository;
import com.shopeeClone.shopeeClone.repository.address.ProvinceRepository;
import com.shopeeClone.shopeeClone.repository.address.WardRepository;
import com.shopeeClone.shopeeClone.service.AddressService;
import com.shopeeClone.shopeeClone.utils.validate;


@Service
@Transactional
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ProvinceRepository provinceRepository;
	@Autowired
	private WardRepository wardRepository;
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private AddressConverter addressConverter;
	@Autowired 
	private UserRepository userRepository;
	@Autowired 
	private OrderRepository orderRepository;
	
	@Override
	public AddressDTO create(CreateAddressForm form, Long userId) {
		AddressEntity addressEntity  = addressConverter.toEntity(form);
		UserEntity userEntity = userRepository.findById(userId)
				.orElseThrow(() -> new ValidateException("user not found"));
		addressRepository.save(addressEntity);
		
		return addressConverter.toDto(addressEntity);
	}
	
	@Override
	public List<AddressDTO> getAll() {
		
		List<AddressEntity> addressEntities = addressRepository.findAll();
		List<AddressDTO> addressDTOs = new ArrayList<AddressDTO>();
		for (AddressEntity addressEntity : addressEntities) {
			AddressDTO addressDTO = addressConverter.toDto(addressEntity);
			addressDTOs.add(addressDTO);
		}
		return addressDTOs;
	}

	@Override
	public void delete(String id, Long userId) {
		Long id1 = validate.validateId(id);
		AddressEntity addressEntity = addressRepository.findById(id1).orElseThrow(() -> new ValidateException("khong thay dia chi"));
		addressEntity.setUser(null);
		addressEntity.setDistrict(null);
		addressEntity.setWard(null);
		addressEntity.setProvince(null);
		addressRepository.delete(addressEntity);
	}

	@Override
	public AddressDTO update(CreateAddressForm form, Long id) {
		ProvinceEntity provinceEntity = provinceRepository.findById(form.getProvinceId())
                .orElseThrow(() -> new ValidateException("Province not found"));
		DistrictEntity districtEntity = districtRepository.findById(form.getDistrictId())
                .orElseThrow(() -> new ValidateException("district not found"));
		WardEntity wardEntity = wardRepository.findById(form.getWardId())
                .orElseThrow(() -> new ValidateException("ward not found"));
		AddressEntity addressEntity = addressRepository.findById(id).orElseThrow(() -> new ValidateException("khong thay dia chi"));
		List<OrderEntity> orderEntities = orderRepository.findByAddress(addressEntity);
		
		for (OrderEntity orderEntity : orderEntities) {
			orderEntity.setAddress(null);
		}
		addressEntity.setDescription(form.getDescription());
		addressEntity.setDistrict(districtEntity);
		addressEntity.setProvince(provinceEntity);
		addressEntity.setWard(wardEntity);
		return addressConverter.toDto(addressEntity);
	}


}
