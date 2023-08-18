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

import com.shopeeClone.shopeeClone.dto.AddressDTO;
import com.shopeeClone.shopeeClone.service.AddressService;

@RestController
@RequestMapping("api/v1/addresses")
public class AddressV1Api {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("{userId}")
	public AddressDTO create(@RequestBody AddressDTO dto, @PathVariable Long userId) {
		return addressService.create(dto, userId);
	}
	
	@GetMapping
	public List<AddressDTO> getAll(){
		return addressService.getAll();
	}
	
	@DeleteMapping("{id}/{userId}")
	public void delete(@PathVariable String id, @PathVariable Long userId) {
		addressService.delete(id, userId);
	}
	
	@PutMapping("{id}")
	public AddressDTO update(@RequestBody AddressDTO dto, @PathVariable String id) {
		return addressService.update(dto, id);
	}
}
