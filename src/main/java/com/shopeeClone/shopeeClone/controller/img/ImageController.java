package com.shopeeClone.shopeeClone.controller.img;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shopeeClone.shopeeClone.dto.ImageDTO;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.service.ImageService;

@RestController
@RequestMapping("images")
public class ImageController {
	
	private ImageService imageService;
	
	@PostMapping(path =  "fileUpLoad", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ImageDTO uploadFile(@RequestParam("file") MultipartFile multipartFile,
								@RequestParam(value =  "description",required = false) String description) {
	
		return null;
	}
	
}
