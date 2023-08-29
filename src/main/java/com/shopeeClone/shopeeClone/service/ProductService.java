package com.shopeeClone.shopeeClone.service;

import java.util.List;
import java.util.Map;

import com.shopeeClone.shopeeClone.dto.CreateProductDTO;
import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.ProductDTO;

public interface ProductService {
    ProductDTO createProduct(CreateProductDTO dto);
    PageDTO<ProductDTO> getProducts(Map<String,String> params);
    List<ProductDTO> getProducts();
    ProductDTO updateProduct(Long productId,ProductDTO productDTO);
    void deleteProductById(Long id);
    ProductDTO getProductByProductId(Long productId);
}
