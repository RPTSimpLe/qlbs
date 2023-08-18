package com.shopeeClone.shopeeClone.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopeeClone.shopeeClone.converter.UserConverter;
import com.shopeeClone.shopeeClone.dto.UserDTO;
import com.shopeeClone.shopeeClone.entity.RoleEntity;
import com.shopeeClone.shopeeClone.entity.UserEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.RoleRepository;
import com.shopeeClone.shopeeClone.repository.UserRepository;
import com.shopeeClone.shopeeClone.service.UserService;
import com.shopeeClone.shopeeClone.utils.validate;


@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;
	@Autowired
	private UserConverter converter;
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDTO createUser(UserEntity entity) {
		Optional<RoleEntity> roleEntities = roleRepository.findByCode("USER");
		RoleEntity roleEntity = roleEntities.get();
		entity.getRoles().add(roleEntity);
		repository.save(entity);

		validateEntity(entity);
		roleEntity.getUsers().add(entity);
		roleRepository.save(roleEntity);
		
		return converter.toDTO(entity);
	}
	
	private void validateEntity(UserEntity entity) {
	    if (entity.getUsername() == null || entity.getUsername().isEmpty()) {
	        throw new ValidateException("Username is required");
	    }
	    if (entity.getPassword() == null || entity.getPassword().isEmpty()) {
	        throw new ValidateException("Password is required");
	    }
	    if (entity.getFirstName() == null || entity.getFirstName().isEmpty()) {
	        throw new ValidateException("First name is required");
	    }
	    if (entity.getLastName() == null || entity.getLastName().isEmpty()) {
	        throw new ValidateException("Last name is required");
	    }
	    if (entity.getPhoneNumber() == null || !entity.getPhoneNumber().matches("\\d{10}")) {
	        throw new ValidateException("Phone number must be a valid 10-digit number");
	    }
	    if (entity.getAddress() == null || entity.getAddress().isEmpty()) {
	        throw new ValidateException("Address is required");
	    }
	}

	
	@Override
	public List<UserDTO> getAll() {
		List<UserEntity>entities = repository.findAll();
		List<UserDTO>dtos = converter.toDTO(entities);
		return dtos;
	}

	@Override
	public UserDTO findAllByName(String name) {
		Optional<UserEntity> entities = repository.findByUserName(name);
		UserDTO dto = converter.toDTO(entities.get());
		return dto;
	}

	@Override
	public void deleteById(Long id) {
		UserEntity userEntity = getUserByName(id);
		List<RoleEntity> roleEntities = userEntity.getRoles();
		for (RoleEntity roleEntity : roleEntities) {
			if (roleEntity.getUsers().contains(userEntity)) {
	            roleEntity.getUsers().remove(userEntity);
	            roleRepository.save(roleEntity);
	        }
		}
		userEntity.getRoles().clear();
		repository.save(userEntity);
		repository.delete(userEntity);
	}

	private UserEntity getUserByName(Long id) {
		UserEntity userEntity = repository.findById(id)
				.orElseThrow(() -> new ValidateException("Không tìm thấy user"));
		return userEntity;
	}

	@Override
	public UserDTO updateByPatch(String id, UserDTO dto) {
		Long id1 = validate.validateId(id);
		UserEntity userEntity = getUserByName(id1);
		userEntity.setUsername(dto.getUsername());
		repository.save(userEntity);
		return converter.toDTO(userEntity);
	}

	@Override
	public UserDTO updateRole(String id, String code) {
		Long id1 = validate.validateId(id);
		System.out.println(code);
		UserEntity userEntity = getUserByName(id1);
		RoleEntity roleEntity = roleRepository.findByCode(code)
				.orElseThrow(() -> new ValidateException("Không tìm thấy role"));
		userEntity.getRoles().add(roleEntity);
		repository.save(userEntity);
		return converter.toDTO(userEntity);
	}
	
}
