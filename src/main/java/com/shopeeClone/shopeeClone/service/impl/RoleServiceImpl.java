package com.shopeeClone.shopeeClone.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopeeClone.shopeeClone.converter.RoleConverter;
import com.shopeeClone.shopeeClone.dto.RoleDTO;
import com.shopeeClone.shopeeClone.entity.RoleEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.RoleRepository;
import com.shopeeClone.shopeeClone.service.RoleService;
import com.shopeeClone.shopeeClone.utils.AppStringUtils;
import com.shopeeClone.shopeeClone.utils.validate;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleConverter roleConverter;
	
	@Override
	public RoleDTO createRole(RoleDTO roleDto) {
		// Validate
		String code = roleDto.getCode();
		validateCode(code);
		// Logic
		// dto -> entity
		RoleEntity newRoleEntity = roleConverter.toEntity(roleDto);
		roleRepository.save(newRoleEntity);
		
		// entity -> dto
		RoleDTO newRoleDto = roleConverter.toDto(newRoleEntity);
		
		return newRoleDto;
	}

	private void validateCode(String code) {
		if (code == null || code.isEmpty()) { // null, ""
			// Bắn lỗi
			throw new ValidateException("Code không được để trống");
		}
		Optional<RoleEntity> roleEntity = roleRepository.findByCode(code);
		if (roleEntity.isPresent()) {
			throw new ValidateException("Role đã tồn tại");
		}
	}

	@Override
	public List<RoleDTO> getAll(String code) {
		if(AppStringUtils.hasTextAnd(code)) {
			Optional<RoleEntity> roleEntities = roleRepository.findByCode(code);
			List<RoleEntity> entities = roleEntities.stream()
                    .collect(Collectors.toList());
			List<RoleDTO> roleDtos = roleConverter.toDto(entities);
			return roleDtos;			
		}
	List<RoleEntity> roleEntities = roleRepository.findAll();
	List<RoleDTO> roleDtos = roleConverter.toDto(roleEntities);
	return roleDtos;
	}
	
	@Override
	public RoleDTO updateRole(String roleId, RoleDTO roleDTO) {
		// Validate
		String code = roleDTO.getCode();
		if (code == null || code.isEmpty()) { // null, ""
			// Bắn lỗi
			throw new ValidateException("Code không được để trống");
		}
		Long roleId1 = validate.validateId(roleId);
		// Logic
		// Lấy entity mà muốn update
		RoleEntity roleEntity = getRoleEntityById(roleId1);
		
		// Set dữ liệu mới cho entity
		roleEntity.setName(roleDTO.getName());
		roleEntity.setCode(roleDTO.getCode());
		
		roleRepository.save(roleEntity);
		
		return roleConverter.toDto(roleEntity);
	}

	private RoleEntity getRoleEntityById(Long id) {
		RoleEntity roleEntity = roleRepository.findById(id)
				.orElseThrow(() -> new ValidateException("Không tìm thấy role"));
		return roleEntity;
	}
	
	@Override
	public void deleteById(Long roleId) {
		RoleEntity roleEntity = getRoleEntityById(roleId);
		roleEntity.getUsers().removeAll(getAll(""));
		roleRepository.save(roleEntity);
		roleRepository.delete(roleEntity);
	}

	@Override
	public RoleDTO getRoleById(Long roleId) {
		RoleEntity entity = roleRepository.findById(roleId).orElseThrow(() -> new ValidateException("khong thay"));
		return roleConverter.toDto(entity);
	}
	
	
}
