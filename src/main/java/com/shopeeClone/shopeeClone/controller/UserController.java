package com.shopeeClone.shopeeClone.controller;


import java.util.List;

import com.shopeeClone.shopeeClone.dto.address.DistrictDTO;
import com.shopeeClone.shopeeClone.dto.address.ProvinceDTO;
import com.shopeeClone.shopeeClone.dto.address.WardDTO;
import com.shopeeClone.shopeeClone.dto.user.UserDTO;
import com.shopeeClone.shopeeClone.repository.UserRepository;
import com.shopeeClone.shopeeClone.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopeeClone.shopeeClone.dto.product.ProductDTO;


@Controller
public class UserController {
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private WardService wardService;
	@Autowired
	private ProvinceService provinceService;

	@GetMapping
	public String home(Model model) {
		List<ProductDTO> productDTOs = productService.getProducts();
		model.addAttribute("products", productDTOs);
		return "user/homePage";
	}
	@GetMapping("/profile")
	public String profile(Model model) {
		UserDTO userDTO = userService.getUser();
		model.addAttribute("userDTO", userDTO);
		List<DistrictDTO> districtDTOS = districtService.getAll();
		List<ProvinceDTO> provinceDTOS = provinceService.getAll();
		List<WardDTO> wardDTOS = wardService.getAll();
		model.addAttribute("provinceDTOS",provinceDTOS);
		return "user/profile";
	}
}
