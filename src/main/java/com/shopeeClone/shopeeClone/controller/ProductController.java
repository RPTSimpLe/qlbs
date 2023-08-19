package com.shopeeClone.shopeeClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopeeClone.shopeeClone.dto.ProductDTO;
import com.shopeeClone.shopeeClone.service.ProductService;

@Controller
@RequestMapping("admin/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("add")
	public String showAddProductPage() {
		return "admin/product/add-product";
	}
	@GetMapping("search")
	public String showSearchProductsPage(){
		return "admin/product/search-product";
	}
	@GetMapping("{productId}")
	public String update(@PathVariable String productId, Model model){
		ProductDTO productDTO = productService.getProductByProductId(Long.parseLong(productId));
		model.addAttribute("product", productDTO);
		return "admin/product/update-product";
	}

}
