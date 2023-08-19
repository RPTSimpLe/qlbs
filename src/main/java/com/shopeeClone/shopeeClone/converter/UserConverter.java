package com.shopeeClone.shopeeClone.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shopeeClone.shopeeClone.dto.UserDTO;
import com.shopeeClone.shopeeClone.entity.RoleEntity;
import com.shopeeClone.shopeeClone.entity.UserEntity;

@Component
public class UserConverter {
	
	public UserEntity toEntity(UserDTO dto) {
		UserEntity entity = new UserEntity();
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setUserId(dto.getUserId());
		entity.setUsername(dto.getUsername());
		return entity;
	}
	
	public UserDTO toDTO(UserEntity entity) {
		UserDTO dto = new UserDTO();
		dto.setCreatedDate(entity.getCreateDate());
		dto.setModifierDate(entity.getModifierDate());
		dto.setCreatedBy(entity.getCreateBy());
		dto.setModifierBy(entity.getModifierBy());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setUserId(entity.getUserId());
		dto.setUsername(entity.getUsername());
		
		List<RoleEntity> roleEntities = entity.getRoles();
		for (RoleEntity roleEntity : roleEntities) {
			dto.getRoleName().add(roleEntity.getCode());
		};
		
		return dto;
	}
	
	public List<UserDTO> toDTO(List<UserEntity> entities) {
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		for (UserEntity entity : entities) {
			dtos.add(toDTO(entity));
		}
		return dtos;
	}
}
