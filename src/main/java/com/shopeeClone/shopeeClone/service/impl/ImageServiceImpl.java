package com.shopeeClone.shopeeClone.service.impl;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shopeeClone.shopeeClone.dto.ImageDTO;
import com.shopeeClone.shopeeClone.entity.ImageEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.ImageRepository;
import com.shopeeClone.shopeeClone.service.ImageService;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
	@Autowired
	private ImageRepository imageRepository;
	@Override
	public List<ImageDTO> saveImage(List<MultipartFile> files) {
		List<ImageDTO> imageDTOs = new ArrayList<>();
		for(MultipartFile file : files){
			String fileName = file.getOriginalFilename();
			try {
				// Lưu file
				InputStream inputStream = file.getInputStream();
				byte[] buffer = new byte[inputStream.available()];
				inputStream.read(buffer);
				File newFile = new File("src/main/resources/static/ProductImages/" + fileName);
				OutputStream outputStream
					= new FileOutputStream(newFile);
				outputStream.write(buffer);
			} catch (IOException e) {
				throw new ValidateException("Server error");
			}

			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setUrl("/ProductImages/" + fileName);
			imageDTOs.add(imageDTO);

		}
		return imageDTOs;
	}

	@Override
	public void deleteImage(List<ImageEntity> imageEntities) {
		for(ImageEntity imageEntity : imageEntities){
			//Xoa image trong static
			File deleteImage = new File("src/main/resources/static" + imageEntity.getUrl());
			deleteImage.delete();
			imageRepository.findById(imageEntity.getImageId())
			.orElseThrow(() -> new ValidateException("Khong tim thay image trong database"));
			imageRepository.deleteById(imageEntity.getImageId());
		}
	}

	@Override
	public void deleteEachImage(Long imageId) {
		ImageEntity imageEntity = imageRepository.findById(imageId).orElseThrow(() -> new ValidateException("khong tim thay anh"));
		File deleteImage = new File("src/main/resources/static" + imageEntity.getUrl());
		System.out.println(imageEntity.getUrl());
		deleteImage.delete();
		imageRepository.deleteById(imageEntity.getImageId());

	}

}
