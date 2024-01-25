package com.shopeeClone.shopeeClone.controller;


import java.util.List;

import com.shopeeClone.shopeeClone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopeeClone.shopeeClone.dto.product.ProductDTO;
import com.shopeeClone.shopeeClone.service.ProductService;



@Controller
public class UserController {
	@Autowired
	private ProductService productService;
	@Autowired
	private UserRepository repository;
	@GetMapping
	public String home(Model model) {
		List<ProductDTO> productDTOs = productService.getProducts();
		model.addAttribute("products", productDTOs);
		return "user/homePage";
	}
}
