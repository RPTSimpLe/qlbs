package com.shopeeClone.shopeeClone.converter.image;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shopeeClone.shopeeClone.dto.ImageDTO;
import com.shopeeClone.shopeeClone.entity.ImageEntity;

@Component
public class ImageConverter {
    public ImageDTO toDTO(ImageEntity imageEntity){
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setImageId(imageEntity.getImageId());
        imageDTO.setUrl(imageEntity.getUrl());
        imageDTO.setDescription(imageEntity.getDescription());
        return imageDTO;
    }
    public List<ImageDTO> toDTOs(List<ImageEntity> imageEntities){
        List<ImageDTO> imageDTOs = new ArrayList<>();
        for(ImageEntity imageEntity : imageEntities){
            imageDTOs.add(toDTO(imageEntity));
        }
        return imageDTOs;
    }
}
