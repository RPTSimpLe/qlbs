package com.shopeeClone.shopeeClone.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopeeClone.shopeeClone.converter.product.ProductConverter;
import com.shopeeClone.shopeeClone.dto.CreateProductDTO;
import com.shopeeClone.shopeeClone.dto.ImageDTO;
import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.ProductDTO;
import com.shopeeClone.shopeeClone.entity.ImageEntity;
import com.shopeeClone.shopeeClone.entity.ProductEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.ImageRepository;
import com.shopeeClone.shopeeClone.repository.ProductRepository;
import com.shopeeClone.shopeeClone.service.ImageService;
import com.shopeeClone.shopeeClone.service.ProductService;
import com.shopeeClone.shopeeClone.utils.AppStringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public ProductDTO createProduct(CreateProductDTO dto) {
        String name = dto.getName();
        if(AppStringUtils.hasText(name)){
            throw new ValidateException("product cannot empty");
        }
        ProductEntity newProductEntity = productConverter.toEntity(dto);

        List<ImageDTO> imageDTOs = imageService.saveImage(dto.getMultipartFiles());
        productRepository.save(newProductEntity);
        for(ImageDTO imageDTO : imageDTOs){
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setUrl(imageDTO.getUrl());
            imageEntity.setProduct(newProductEntity);
            imageRepository.save(imageEntity);
            newProductEntity.addImageEntity(imageEntity);
        }
        productRepository.save(newProductEntity);
        ProductDTO newProductDTO = productConverter.toDTO(newProductEntity);
        
        return newProductDTO;
    }
    @Override
    public List<ProductDTO> getProducts() {
        StringBuilder selectQueryBuilder = new StringBuilder("Select c from ProductEntity c ");
        TypedQuery<ProductEntity> selectQuery = entityManager.createQuery(selectQueryBuilder.toString(),
				ProductEntity.class);
        List<ProductEntity> productEntities = selectQuery.getResultList();
        List<ProductDTO> productDTOs = productConverter.toDTOList(productEntities);
        return productDTOs;
    }

    @Override
    public PageDTO<ProductDTO> getProducts(Map<String, String> params) {
        // hql
		// http://localhost:8080/admin/api/v1/products?page=1?limit=10
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
		StringBuilder selectQueryBuilder = new StringBuilder("Select c from ProductEntity c ");
		StringBuilder countQueryBuilder = new StringBuilder("Select count(c.productId) " + "from ProductEntity c ");

		String name = params.get("name");
		if (AppStringUtils.hasTextAnd(name)) {
			selectQueryBuilder.append(" Where c.name like :name");
			countQueryBuilder.append(" Where c.name like :name");
		}

		TypedQuery<ProductEntity> selectQuery = entityManager.createQuery(selectQueryBuilder.toString(),
				ProductEntity.class);
		TypedQuery<Long> countQuery = entityManager.createQuery(countQueryBuilder.toString(), Long.class);

		Integer firstItems = (page - 1) * limit;

		if (AppStringUtils.hasTextAnd(name)) {
			selectQuery.setParameter("name", "%"+name+"%");
			countQuery.setParameter("name", "%"+name+"%");
		}

		selectQuery.setFirstResult(firstItems);
		selectQuery.setMaxResults(limit);

		List<ProductEntity> productEntities = selectQuery.getResultList();
		Long totalItems = countQuery.getSingleResult();

		// entity -> dto
		List<ProductDTO> dtos = productConverter.toDTOList(productEntities);

		return new PageDTO<>(page, limit, totalItems, dtos);
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new ValidateException("Khong tim thay product"));
        productConverter.toEntity(productEntity, productDTO);
        productRepository.save(productEntity);
       return productConverter.toDTO(productEntity);
    }

    @Override
    public void deleteProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ValidateException("Khong tim thay product"));
        List<ImageEntity> imageEntities = productEntity.getImageEntities();
        imageService.deleteImage(imageEntities);
        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO getProductByProductId(Long productId) {
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new ValidateException("Khong tim thay product"));

        return productConverter.toDTO(productEntity);
    }

    
    
}
