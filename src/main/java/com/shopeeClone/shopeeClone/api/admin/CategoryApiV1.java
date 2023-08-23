package com.shopeeClone.shopeeClone.api.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopeeClone.shopeeClone.dto.CategoryDTO;
import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.service.CategoryService;

@RestController
@RequestMapping("/admin/api/v1/categories")
public class CategoryApiV1 {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public CategoryDTO createCategory(
			@RequestBody CategoryDTO dto
			) {
				return categoryService.createCategory(dto);
	}
	
	@PutMapping("{categoryId}")
	public CategoryDTO updateCategory(@PathVariable (value = "categoryId") Long categoryId, 
			@RequestBody CategoryDTO dto) {
		return categoryService.updateCategory(categoryId, dto);
	}
	
	@DeleteMapping("{categoryId}")
	public void deleteCategoryById(@PathVariable (value = "categoryId") Long categoryId){
		categoryService.deleteCategoryById(categoryId);
	}
	
	@GetMapping
	public PageDTO<CategoryDTO> getCategories(@RequestParam Map<String, String> params){
		return categoryService.getCategories(params);
	}
	
}
