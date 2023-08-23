package com.shopeeClone.shopeeClone.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shopeeClone.shopeeClone.dto.ImageDTO;

public interface ImageService {
	ImageDTO saveImage(List<MultipartFile> multipartFiles);
}
