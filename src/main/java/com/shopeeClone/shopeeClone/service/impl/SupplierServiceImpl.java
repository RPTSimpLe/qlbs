package com.shopeeClone.shopeeClone.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.SupplierDTO;
import com.shopeeClone.shopeeClone.entity.SupplierEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.SupplierRepository;
import com.shopeeClone.shopeeClone.service.SupplierService;
import com.shopeeClone.shopeeClone.utils.AppStringUtils;
import com.shopeeClone.shopeeClone.utils.validate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRepository repository;
	@Autowired
	EntityManager entityManager;
	
	@Override
	public SupplierDTO create(SupplierDTO dto) {
		SupplierEntity entity = convertToEntity(dto);
		repository.save(entity);
		return convertToDto(entity);
	}
	
	public SupplierDTO convertToDto(SupplierEntity supplierEntity) {
	    SupplierDTO supplierDto = new SupplierDTO();
	    supplierDto.setSupplierId(supplierEntity.getSupplierId());
	    supplierDto.setName(supplierEntity.getName());
	    supplierDto.setEmail(supplierEntity.getEmail());
	    supplierDto.setPhone(supplierEntity.getPhone());
	    supplierDto.setDescription(supplierEntity.getDescription());
	    supplierDto.setCreateBy(supplierEntity.getCreateBy());
	    supplierDto.setCreateDate(supplierEntity.getCreateDate());
	    supplierDto.setModifierBy(supplierEntity.getModifierBy());
	    supplierDto.setModifierDate(supplierEntity.getModifierDate());
	    return supplierDto;
	}
	
	public List<SupplierDTO> convertToDto(List<SupplierEntity> entities){
		List<SupplierDTO> dtos = new ArrayList<>();
		for (SupplierEntity entity : entities) {
			dtos.add(convertToDto(entity));
		}
		return dtos;
	}

	
	public SupplierEntity convertToEntity(SupplierDTO supplierDto) {
	    SupplierEntity supplierEntity = new SupplierEntity();
	    supplierEntity.setSupplierId(supplierDto.getSupplierId());
	    supplierEntity.setName(supplierDto.getName());
	    supplierEntity.setPhone(supplierDto.getPhone());
	    supplierEntity.setDescription(supplierDto.getDescription());
	    supplierEntity.setEmail(supplierDto.getEmail());
	    return supplierEntity;
	}

	
	@Override
	public PageDTO<SupplierDTO> getSupplier(Map<String, String> params) {
		String pageStr = params.get("page");
		String limitStr = params.get("limit");
		Integer page =1;
		Integer limit = 5;
		if (AppStringUtils.hasTextAnd(pageStr)) {
			page = Integer.valueOf(pageStr);
		}
		if (AppStringUtils.hasTextAnd(limitStr)) {
			limit = Integer.valueOf(limitStr);
		}
		
		StringBuilder selectQueryBuilder = new StringBuilder("Select c from SupplierEntity c ");
		StringBuilder countQueryBuilder = new StringBuilder("Select count(c.supplierId) from SupplierEntity c ");
		
		String nameStr = params.get("name");
		if (AppStringUtils.hasTextAnd(nameStr)) {
			selectQueryBuilder.append(" where c.name like :name");
			countQueryBuilder.append(" where c.name like :name");			
		}
		
		TypedQuery<SupplierEntity> selectQuery = entityManager.createQuery(selectQueryBuilder.toString(), SupplierEntity.class);
		TypedQuery<Long> countQuery = entityManager.createQuery(countQueryBuilder.toString(), Long.class);
		
		Integer firstItems = (page-1)*limit;
		if (AppStringUtils.hasTextAnd(nameStr)) {
			selectQuery.setParameter("name", "%"+nameStr+"%");
			countQuery.setParameter("name", "%"+nameStr+"%");
		}

		
		selectQuery.setFirstResult(firstItems);
		selectQuery.setMaxResults(limit);
		
		List<SupplierEntity> entities = selectQuery.getResultList();
		Long totalItems = countQuery.getSingleResult();
		
		List<SupplierDTO> dtos = convertToDto(entities);
		return new PageDTO<>(page, limit, totalItems, dtos);
	}

	@Override
	public SupplierDTO update(String id, SupplierDTO dto) {
		Long id1 = validate.validateId(id);
		SupplierEntity entity = repository.findById(id1).orElseThrow(() -> new ValidateException("suppplier not found"));
		entity = convertToEntity(dto);
		repository.save(entity);
		return convertToDto(entity);
	}

	@Override
	public void delete(Long id) {
		SupplierEntity entity = repository.findById(id).orElseThrow(() -> new ValidateException("suppplier not found"));
		repository.delete(entity);
	}

	@Override
	public SupplierDTO getSupplierBySupplierId(String supplierId) {
		Long id1 = validate.validateId(supplierId);
		SupplierEntity entity = repository.findById(id1).orElseThrow(()-> new ValidateException("khong thay"));
		return convertToDto(entity);
	}

}
