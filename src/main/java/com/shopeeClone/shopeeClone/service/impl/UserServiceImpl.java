package com.shopeeClone.shopeeClone.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopeeClone.shopeeClone.converter.UserConverter;
import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.UserDTO;
import com.shopeeClone.shopeeClone.entity.RoleEntity;
import com.shopeeClone.shopeeClone.entity.UserEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.RoleRepository;
import com.shopeeClone.shopeeClone.repository.UserRepository;
import com.shopeeClone.shopeeClone.service.UserService;
import com.shopeeClone.shopeeClone.utils.AppStringUtils;
import com.shopeeClone.shopeeClone.utils.validate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;
	@Autowired
	private UserConverter converter;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private EntityManager entityManager;
	
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
		UserEntity userEntity = getUserByName(id1);
		RoleEntity roleEntity = roleRepository.findByCode(code)
				.orElseThrow(() -> new ValidateException("Không tìm thấy role"));
		userEntity.getRoles().add(roleEntity);
		roleEntity.getUsers().add(userEntity);
		roleRepository.save(roleEntity);
		repository.save(userEntity);
		return converter.toDTO(userEntity);
	}

	@Override
	public PageDTO<UserDTO> getUs(Map<String, String> params) {
		String pageStr = params.get("page");
		String limitStr = params.get("limit");
		
		Integer page =1;
		Integer limit = 10;
		if (AppStringUtils.hasTextAnd(pageStr)) {
			page = Integer.valueOf(pageStr);
		}
		if (AppStringUtils.hasTextAnd(limitStr)) {
			limit = Integer.valueOf(limitStr);
		}
		StringBuilder selectQueryBuilder = new StringBuilder("Select c from UserEntity c ");
		StringBuilder countQueryBuilder = new StringBuilder("Select count(c.userId) from UserEntity c ");
		
		String nameStr = params.get("username");
		if (AppStringUtils.hasTextAnd(nameStr)) {
			selectQueryBuilder.append(" where c.username like :username");
			countQueryBuilder.append(" where c.username like :username");			
		}
		TypedQuery<UserEntity> selectQuery = entityManager.createQuery(selectQueryBuilder.toString(), UserEntity.class);
		TypedQuery<Long> countQuery = entityManager.createQuery(countQueryBuilder.toString(), Long.class);
		
		Integer firstItems = (page-1)*limit;
		if (AppStringUtils.hasTextAnd(nameStr)) {
			selectQuery.setParameter("username", "%"+nameStr+"%");
			countQuery.setParameter("username", "%"+nameStr+"%");
		}
		
		selectQuery.setFirstResult(firstItems);
		selectQuery.setMaxResults(limit);
		
		List<UserEntity> entities = selectQuery.getResultList();
		Long totalItems = countQuery.getSingleResult();
		
		List<UserDTO> dtos = converter.toDTO(entities);
		return new PageDTO<>(page, limit, totalItems, dtos);
	}

	@Override
	public void deleteRole(Long id, String roleName) {
		RoleEntity roleEntity = roleRepository.findByCode(roleName).orElseThrow();
		UserEntity userEntity = repository.findById(id).orElseThrow();
		userEntity.getRoles().remove(roleEntity);
		roleEntity.getUsers().remove(userEntity);
		roleRepository.save(roleEntity);
		repository.save(userEntity);
	}
	
}
