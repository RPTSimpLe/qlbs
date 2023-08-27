package com.shopeeClone.shopeeClone.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopeeClone.shopeeClone.dto.address.ProvinceDTO;
import com.shopeeClone.shopeeClone.service.ProvinceService;

@RestController
@RequestMapping("admin/api/v1/provinces")
public class ProvinceV1Api {
	@Autowired
	private ProvinceService provinceService;
	
	@PostMapping
	public ProvinceDTO create(@RequestBody ProvinceDTO dto) {
		return provinceService.create(dto);
	}
	
	@GetMapping
	public List<ProvinceDTO> getAll(){
		return provinceService.getAll();
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable String id) {
		provinceService.delete(id);
	}
	
	@PatchMapping("{id}")
	public ProvinceDTO update(@RequestBody ProvinceDTO dto, @PathVariable String id) {
		return provinceService.update(dto, id);
	}
}
