package com.shopeeClone.shopeeClone.api.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopeeClone.shopeeClone.dto.PageDTO;
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


    @PutMapping("{productId}")
    public ProductDTO updateProduct(@PathVariable (value = "productId") Long productId,
    @RequestBody ProductDTO dto){
        return productService.updateProduct(productId, dto);
    }

    @DeleteMapping("{productId}")
    public void deleteProduct(@PathVariable (value = "productId") Long id){
        productService.deleteProductById(id);
    }
    @GetMapping("{productId}")
    public ProductDTO getProductById(@PathVariable (value = "productId") Long id){
        return productService.getProductByProductId(id);

    }
    @GetMapping
    public PageDTO<ProductDTO> getProducts(@RequestParam Map<String,String> params){
        return productService.getProducts(params);
    }

}

