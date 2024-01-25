package com.shopeeClone.shopeeClone.api.user;

import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.product.ProductDTO;
import com.shopeeClone.shopeeClone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/api/v1/products")
public class ProductV1Api {
    @Autowired
    private ProductService productService;

    @GetMapping("getAll")
    public List<ProductDTO> getAll(){
        return productService.getAll();
    }
    @GetMapping
    public PageDTO<ProductDTO> getProducts(@RequestParam Map<String,String> params){
        return productService.getProducts(params);
    }
}
