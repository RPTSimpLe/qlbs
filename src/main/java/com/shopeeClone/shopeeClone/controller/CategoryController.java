package com.shopeeClone.shopeeClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopeeClone.shopeeClone.dto.CategoryDTO;
import com.shopeeClone.shopeeClone.service.CategoryService;


@Controller
@RequestMapping("admin/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("add")
	public String showAddCategoryPage() {
		
		return "admin/category/add-category";
	}
	
	@GetMapping("search")
	public String showSearchCategoriesPage() {
		return "admin/category/search-category";
	}
	
	@GetMapping("{categoryId}")
	public String update(@PathVariable String categoryId, Model model) {
		CategoryDTO categoryDTO = categoryService.getCategoryByCategoryId(Long.parseLong(categoryId));
		model.addAttribute("category", categoryDTO);
		return "admin/category/update-category";
	}
}
