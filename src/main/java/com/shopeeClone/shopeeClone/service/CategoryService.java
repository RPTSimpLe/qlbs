package com.shopeeClone.shopeeClone.service;

import java.util.List;
import java.util.Map;

import com.shopeeClone.shopeeClone.dto.category.CategoryDTO;
import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.category.GetCountAndCategory;

public interface CategoryService {
	CategoryDTO createCategory(CategoryDTO dto);
	List<CategoryDTO> getAll();
	GetCountAndCategory getAllInUser();
	PageDTO<CategoryDTO> getCategories(Map<String, String> params);
	
	CategoryDTO updateCategory(Long categoryId ,CategoryDTO categoryDTO);
	
	void deleteCategoryById(Long id);

	CategoryDTO getCategoryByCategoryId(Long categoryId);
}
