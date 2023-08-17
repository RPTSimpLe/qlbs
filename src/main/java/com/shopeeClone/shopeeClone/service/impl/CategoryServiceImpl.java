package com.shopeeClone.shopeeClone.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopeeClone.shopeeClone.converter.category.CategoryConverter;
import com.shopeeClone.shopeeClone.dto.CategoryDTO;
import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.entity.CategoryEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.CategoryRepository;
import com.shopeeClone.shopeeClone.service.CategoryService;
import com.shopeeClone.shopeeClone.utils.AppStringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryConverter categoryConverter;
	@Autowired
	private EntityManager entityManager;

	@Override
	public CategoryDTO createCategory(CategoryDTO dto) {
		String name = dto.getName();
		if (AppStringUtils.hasText(name)) {
			throw new ValidateException("category cannot empty");
		}

		CategoryEntity newCategoryEntity = categoryConverter.toEntity(dto);
		categoryRepository.save(newCategoryEntity);
		CategoryDTO newCategoryDTO = categoryConverter.toDTO(newCategoryEntity);
		return newCategoryDTO;
	}

	@Override
	public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {
		CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ValidateException("category not found"));
		categoryConverter.toEntity(categoryEntity, categoryDTO);
		categoryRepository.save(categoryEntity);
		return categoryConverter.toDTO(categoryEntity);
	}

	@Override
	public void deleteCategoryById(Long id) {
		categoryRepository.findById(id).orElseThrow(() -> new ValidateException("category not found"));
		categoryRepository.deleteById(id);
	}

	@Override
	public PageDTO<CategoryDTO> getCategories(Map<String, String> params) {
		// hql
		// http://localhost:8080/admin/api/v1/categories?page=1?limit=10
		System.out.println(params);
		String pageStr = params.get("page");
		String limitStr = params.get("limit");
		Integer page = 1;
		Integer limit = 10;
		if (AppStringUtils.hasTextAnd(pageStr)) {
			page = Integer.valueOf(pageStr);
		}
		if (AppStringUtils.hasTextAnd(limitStr)) {
			limit = Integer.valueOf(limitStr);
		}

		// lay data
		// dem data
		StringBuilder selectQueryBuilder = new StringBuilder("Select c from CategoryEntity c ");
		StringBuilder countQueryBuilder = new StringBuilder("Select count(c.categoryId) " + "from CategoryEntity c ");

		String name = params.get("name");
		if (AppStringUtils.hasTextAnd(name)) {
			selectQueryBuilder.append(" Where c.name like :name");
			countQueryBuilder.append(" Where c.name like :name");
		}

		TypedQuery<CategoryEntity> selectQuery = entityManager.createQuery(selectQueryBuilder.toString(),
				CategoryEntity.class);
		TypedQuery<Long> countQuery = entityManager.createQuery(countQueryBuilder.toString(), Long.class);

		Integer firstItems = (page - 1) * limit;

		if (AppStringUtils.hasTextAnd(name)) {
			selectQuery.setParameter("name", "%"+name+"%");
			countQuery.setParameter("name", "%"+name+"%");
		}

		selectQuery.setFirstResult(firstItems);
		selectQuery.setMaxResults(limit);

		List<CategoryEntity> categoryEntities = selectQuery.getResultList();
		Long totalItems = countQuery.getSingleResult();

		// entity -> dto
		List<CategoryDTO> dtos = categoryConverter.toDTOList(categoryEntities);

		return new PageDTO<>(page, limit, totalItems, dtos);
	}

	@Override
	public CategoryDTO getCategoryByCategoryId(Long categoryId) {
		CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(() -> new ValidateException("khong tim thay danh muc"));
		return categoryConverter.toDTO(categoryEntity);
	}

}
