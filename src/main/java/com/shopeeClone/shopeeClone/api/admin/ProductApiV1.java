package com.shopeeClone.shopeeClone.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopeeClone.shopeeClone.dto.ProductDTO;
import com.shopeeClone.shopeeClone.service.ProductService;

@RestController
@RequestMapping("/admin/api/v1/products")
public class ProductApiV1 {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO dto){
        return productService.createProduct(dto);
    }
}

