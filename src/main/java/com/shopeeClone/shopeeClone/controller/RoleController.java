package com.shopeeClone.shopeeClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopeeClone.shopeeClone.dto.RoleDTO;
import com.shopeeClone.shopeeClone.service.RoleService;

@Controller
@RequestMapping("/admin/role")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@GetMapping("create")
	public String create() {
		return "admin/role/create-role";
	}
	
	@GetMapping("search")
	public String search() {
		return "admin/role/search-role";
	}
	
	@GetMapping("{roleId}")
	public String update(@PathVariable Long roleId, Model model) {
		RoleDTO dto = roleService.getRoleById(roleId);
		model.addAttribute("a", dto);
		return "admin/role/update-role";
	}
	
	@GetMapping("user")
	public String searchUser() {
		return "admin/role/search-user";
	}
}
