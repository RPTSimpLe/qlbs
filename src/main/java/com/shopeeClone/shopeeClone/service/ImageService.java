package com.shopeeClone.shopeeClone.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shopeeClone.shopeeClone.dto.ImageDTO;
import com.shopeeClone.shopeeClone.dto.ProductDTO;
import com.shopeeClone.shopeeClone.entity.ImageEntity;

public interface ImageService {
	List<ImageDTO> saveImage(List<MultipartFile> multipartFiles);
	void deleteImage(List<ImageEntity> imageEntities);
	void deleteEachImage(Long imageId);
	ProductDTO addImages(Long productId,List<MultipartFile> multipartFiles);
}
