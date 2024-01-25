package com.shopeeClone.shopeeClone.api.user;

import com.shopeeClone.shopeeClone.dto.category.CategoryDTO;
import com.shopeeClone.shopeeClone.dto.category.GetCountAndCategory;
import com.shopeeClone.shopeeClone.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/api/v1/categories")
public class CategoryV1Api {
	
	@Autowired
	private CategoryService categoryService;
	@GetMapping("/getAll")
	public GetCountAndCategory getAll(){
		return categoryService.getAllInUser();
	}
}
