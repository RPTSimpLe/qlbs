package com.shopeeClone.shopeeClone.api.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.SupplierDTO;
import com.shopeeClone.shopeeClone.service.SupplierService;

@RestController
@RequestMapping("admin/api/V1/supplier")
public class SupplierApiV1 {
	
	@Autowired
	SupplierService service;
	
	@PostMapping()
	public SupplierDTO create(@RequestBody SupplierDTO dto) {
		return service.create(dto);
	}
	
	@GetMapping()
	public PageDTO<SupplierDTO> getSupplier(@RequestParam Map<String, String>params) {
		return service.getSupplier(params);
	}
	
	@PutMapping("{id}")
	public SupplierDTO update(@PathVariable String id,@RequestBody SupplierDTO dto) {
		return service.update(id,dto);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		 service.delete(id);
	}
}
