package com.shopeeClone.shopeeClone.service;

import org.springframework.web.multipart.MultipartFile;

import com.shopeeClone.shopeeClone.dto.ImageDTO;

public interface ImageService {
	ImageDTO saveImage(MultipartFile multipartFile);
}
