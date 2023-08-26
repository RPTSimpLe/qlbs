package com.shopeeClone.shopeeClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopeeClone.shopeeClone.converter.product.ProductConverter;
import com.shopeeClone.shopeeClone.dto.CreateProductDTO;
import com.shopeeClone.shopeeClone.dto.ProductDTO;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.service.ProductService;

@Controller
@RequestMapping("admin/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductConverter productConverter;
	
	@GetMapping("add")
	public String showAddProductPage(Model model) {
		model.addAttribute("form", new ProductDTO());
		return "admin/product/add-product";
	}
	@PostMapping("add")
	public String validateProduct(@ModelAttribute CreateProductDTO form,Model model){
		try {
			productConverter.toEntity(form);
			return "admin/product/search-product";
		} catch (ValidateException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("form", form);
			return "admin/product/add-product";
		}
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