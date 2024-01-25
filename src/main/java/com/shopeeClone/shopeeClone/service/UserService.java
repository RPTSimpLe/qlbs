package com.shopeeClone.shopeeClone.service;

import java.util.Map;

import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.user.CreateUserform;
import com.shopeeClone.shopeeClone.dto.user.UserDTO;
import com.shopeeClone.shopeeClone.entity.UserEntity;

public interface UserService {

	UserDTO createUser(UserEntity entity);

	UserDTO findAllByName(String name);
	UserDTO createUserByAdmin(CreateUserform createUserform);
	UserDTO updateByPatch(String id, UserDTO dto);
	void deleteById(Long id);
	UserDTO getUser();
	UserDTO updateRole(String id, String code);

	PageDTO<UserDTO> getUs(Map<String, String> params);

	void deleteRole(Long id, String roleId);
	
}
