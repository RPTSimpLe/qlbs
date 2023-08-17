package com.shopeeClone.shopeeClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopeeClone.shopeeClone.dto.SupplierDTO;
import com.shopeeClone.shopeeClone.service.SupplierService;

@Controller
@RequestMapping("/admin/supplier")
public class SupplierController {
	@Autowired
	SupplierService supplierService;
	
	@GetMapping("add")
	public String create() {
		return "admin/supplier/add-supplier";
	}
	
	@GetMapping("search")
	public String search() {
		return "admin/supplier/search-supplier";
	}
	@GetMapping("{supplierId}")
	public String update(@PathVariable String supplierId, Model model) {
		SupplierDTO dto = supplierService.getSupplierBySupplierId(supplierId);
		model.addAttribute("supplier", dto);
		return "admin/supplier/update-supplier";
	}
}
