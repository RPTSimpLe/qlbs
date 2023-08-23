package com.shopeeClone.shopeeClone.converter.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopeeClone.shopeeClone.converter.image.ImageConverter;
import com.shopeeClone.shopeeClone.dto.CreateProductDTO;
import com.shopeeClone.shopeeClone.dto.ProductDTO;
import com.shopeeClone.shopeeClone.entity.ProductEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.CategoryRepository;
import com.shopeeClone.shopeeClone.repository.SupplierRepository;


@Component
public class ProductConverter {
    
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ImageConverter imageConverter;

    public ProductDTO toDTO(ProductEntity productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(productEntity.getProductId());
        productDTO.setName(productEntity.getName());
        productDTO.setImportPrice(productEntity.getImportPrice());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setCategory(productEntity.getCategory().getName());
        productDTO.setSuppilier(productEntity.getSuppilier().getName());
        productDTO.setDiscountPercent(productEntity.getDiscountPercent());
        productDTO.setCreateDate(productEntity.getCreateDate());
        productDTO.setDescription(productEntity.getDescription());
        productDTO.setCreateBy(productEntity.getCreateBy());
        productDTO.setModifierDate(productEntity.getModifierDate());
        productDTO.setModifierBy(productEntity.getModifierBy());
        productDTO.setImageDTOs(imageConverter.toDTOs(productEntity.getImageEntities()));
        return productDTO;
    }
    public ProductEntity toEntity(CreateProductDTO productDTO){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDTO.getName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setImportPrice(productDTO.getImportPrice());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setDiscountPercent(productDTO.getDiscountPercent());
        productEntity.setCategory(categoryRepository.findByName(productDTO.getCategory()).orElseThrow(() -> new ValidateException("Khong tim thay category")));
        productEntity.setSupplier(supplierRepository.findByName(productDTO.getSuppilier()).orElseThrow(() -> new ValidateException("Khong tim thay suppilier")));
        return productEntity;
    }
    public ProductEntity toEntity(ProductEntity productEntity,ProductDTO productDTO){
        productEntity.setName(productDTO.getName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setImportPrice(productDTO.getImportPrice());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setDiscountPercent(productDTO.getDiscountPercent());
        return productEntity;
    }
    public List<ProductDTO> toDTOList(List<ProductEntity> prorEntities){
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(ProductEntity productEntity : prorEntities){
            productDTOs.add(toDTO(productEntity));
        }
        return productDTOs;
    }
    public List<ProductEntity> toEntityList(List<CreateProductDTO> productDTOs){
        List<ProductEntity> productEntities = new ArrayList<>();
        for(CreateProductDTO productDTO : productDTOs){
            productEntities.add(toEntity(productDTO));
        }
        return productEntities;
    }
}
