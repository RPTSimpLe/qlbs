package com.shopeeClone.shopeeClone.api.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shopeeClone.shopeeClone.dto.CreateProductDTO;
import com.shopeeClone.shopeeClone.dto.ImageDTO;
import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.ProductDTO;
import com.shopeeClone.shopeeClone.service.ImageService;
import com.shopeeClone.shopeeClone.service.ProductService;

@RestController
@RequestMapping("/admin/api/v1/products")
public class ProductApiV1 {
    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductDTO createProduct(@RequestParam("name") String name,
                         @RequestParam("description") String description,
                         @RequestParam("importPrice") Double importPrice,
                         @RequestParam("price") Double price,
                         @RequestParam("discountPercent") Integer discountPercent,
                         @RequestParam("category") String category,
                         @RequestParam("suppilier") String suppilier,
                         @RequestPart(value = "multipartFiles",required = false) List<MultipartFile> multipartFiles){
        CreateProductDTO createProductDTO = new CreateProductDTO();
        createProductDTO.setName(name);
        createProductDTO.setDescription(description);
        createProductDTO.setImportPrice(importPrice);
        createProductDTO.setPrice(price);
        createProductDTO.setDiscountPercent(discountPercent);
        createProductDTO.setCategory(category);
        createProductDTO.setSuppilier(suppilier);
        createProductDTO.addFiles(multipartFiles);
        return productService.createProduct(createProductDTO);
    }


    @PutMapping("{productId}")
    public ProductDTO updateProduct(@PathVariable (value = "productId") Long productId,
    @RequestBody ProductDTO dto){
        return productService.updateProduct(productId, dto);
    }
    @DeleteMapping("/deleteImage/{imageId}")
    public void deleteImage(@PathVariable (value = "imageId") Long imageId){
        imageService.deleteEachImage(imageId);
    }
    @PostMapping(path = "/addImages",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductDTO addImages(@RequestParam("productId") Long productId,
    @RequestPart(value = "multipartFiles",required = false) List<MultipartFile> multipartFiles){
        return imageService.addImages(productId, multipartFiles);
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
    @GetMapping("/userproduct")
    public List<ProductDTO> getProducts(){
        return productService.getProducts();
    }

}

