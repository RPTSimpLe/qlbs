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

import com.shopeeClone.shopeeClone.dto.address.DistrictDTO;
import com.shopeeClone.shopeeClone.dto.address.WardDTO;
import com.shopeeClone.shopeeClone.service.WardService;


@RestController
@RequestMapping("admin/api/v1/wards")
public class WardV1Api {
	@Autowired
	private WardService wardService;
	
	@PostMapping
	public WardDTO create(@RequestBody WardDTO dto) {
		return wardService.create(dto);
	}
	
	@GetMapping
	public List<WardDTO> getAll(){
		return wardService.getAll();
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable String id) {
		wardService.delete(id);
	}
	
	@GetMapping("{districtId}")
	public List<WardDTO> getByWardId(@PathVariable Long districtId){
		return wardService.getByProvinceWardId(districtId);
	}
	
	@PatchMapping("{id}")
	public WardDTO update(@RequestBody WardDTO dto, @PathVariable String id) {
		return wardService.update(dto, id);
	}
}
