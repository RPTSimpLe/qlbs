package com.shopeeClone.shopeeClone.service;

import java.util.Map;

import com.shopeeClone.shopeeClone.dto.CategoryDTO;
import com.shopeeClone.shopeeClone.dto.PageDTO;

public interface CategoryService {
	CategoryDTO createCategory(CategoryDTO dto);
	
	PageDTO<CategoryDTO> getCategories(Map<String, String> params);
	
	CategoryDTO updateCategory(Long categoryId ,CategoryDTO categoryDTO);
	
	void deleteCategoryById(Long id);

	CategoryDTO getCategoryByCategoryId(Long categoryId);
}
