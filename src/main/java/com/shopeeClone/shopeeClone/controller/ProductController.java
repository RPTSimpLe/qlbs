package com.shopeeClone.shopeeClone.controller;

import com.shopeeClone.shopeeClone.service.CategoryService;
import com.shopeeClone.shopeeClone.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.shopeeClone.shopeeClone.converter.product.ProductConverter;
import com.shopeeClone.shopeeClone.dto.CreateProductDTO;
import com.shopeeClone.shopeeClone.dto.product.ProductDTO;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductConverter productConverter;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SupplierService supplierService;
	@GetMapping("admin/products/add")
	public String showAddProductPage(Model model) {
		model.addAttribute("suppliers", supplierService.getAll());
		model.addAttribute("categoris", categoryService.getAll());
		model.addAttribute("form", new ProductDTO());
		return "admin/product/add-product";
	}
	@PostMapping("admin/products/add")
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
	@GetMapping("admin/products/search")
	public String showSearchProductsPage(){
		return "admin/product/search-product";
	}
	@GetMapping("admin/products/{productId}")
	public String update(@PathVariable String productId, Model model){
		ProductDTO productDTO = productService.getProductByProductId(Long.parseLong(productId));
		model.addAttribute("product", productDTO);
		return "admin/product/update-product";
	}
	@GetMapping("user/product/detail/{productId}")
	public String detail(@PathVariable Long productId, Model model){
		ProductDTO productDTO =productService.getProductByProductId(productId);
		model.addAttribute("productDTO",productDTO);
		return "user/product/DetailProduct";
	}
}