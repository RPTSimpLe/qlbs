package com.shopeeClone.shopeeClone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product")
public class ProductController {
	
	@GetMapping
	public String home() {
		return "product/homePage";
	}
}
