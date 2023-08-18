package com.shopeeClone.shopeeClone.api.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopeeClone.shopeeClone.dto.UserDTO;
import com.shopeeClone.shopeeClone.entity.UserEntity;
import com.shopeeClone.shopeeClone.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserV1Api {
	
	@Autowired
	private UserService service;
	
	@PostMapping
	public UserDTO createUser(@RequestBody UserEntity entity) {
		return service.createUser(entity);
	}
	
	@GetMapping("/admin")
	public List<UserDTO> getAll(){
		return service.getAll();
	}
	
	@GetMapping("{name}")
	public UserDTO findByName(@PathVariable String name){
		return service.findAllByName(name);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		service.deleteById(id);
	}
	
	@PutMapping("{id}")
	public UserDTO update(@PathVariable String id, @RequestBody UserDTO dto) {
		return service.updateByPatch(id, dto);
	}
	
	@PatchMapping("/admin/{id}/{code}")
	public UserDTO updateRole(@PathVariable String id, @PathVariable String code) {
		return service.updateRole(id, code);
	}
}
