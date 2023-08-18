package com.shopeeClone.shopeeClone.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopeeClone.shopeeClone.converter.product.ProductConverter;
import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.ProductDTO;
import com.shopeeClone.shopeeClone.entity.CategoryEntity;
import com.shopeeClone.shopeeClone.entity.ProductEntity;
import com.shopeeClone.shopeeClone.entity.SupplierEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.CategoryRepository;
import com.shopeeClone.shopeeClone.repository.ProductRepository;
import com.shopeeClone.shopeeClone.repository.SupplierRepository;
import com.shopeeClone.shopeeClone.service.ProductService;
import com.shopeeClone.shopeeClone.utils.AppStringUtils;
import com.shopeeClone.shopeeClone.utils.validate;

import jakarta.persistence.EntityManager;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductConverter productConverter;
    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        String name = dto.getName();
        if(AppStringUtils.hasText(name)){
            throw new ValidateException("product cannot empty");
        }
        ProductEntity newProductEntity = productConverter.toEntity(dto);
        productRepository.save(newProductEntity);
        ProductDTO newProductDTO = productConverter.toDTO(newProductEntity);
        return newProductDTO;
    }

    @Override
    public PageDTO<ProductDTO> getProducts(Map<String, String> params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProducts'");
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public void deleteProductById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProductById'");
    }

    @Override
    public ProductDTO getProductByProductId(Long productId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductByProductId'");
    }
    
}
