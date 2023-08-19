package com.shopeeClone.shopeeClone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/products")
public class ProductController {
	
	@GetMapping("add")
	public String showAddProductPage() {
		return "admin/product/add-product";
	}
	@GetMapping("search")
	public String showSearchProductsPage(){
		return "admin/product/search-product";
	}

}
