package com.shopeeClone.shopeeClone.converter;

import com.shopeeClone.shopeeClone.converter.product.ProductConverter;
import com.shopeeClone.shopeeClone.dto.rate.CreateRateDTO;
import com.shopeeClone.shopeeClone.dto.rate.RateDTO;
import com.shopeeClone.shopeeClone.entity.ProductEntity;
import com.shopeeClone.shopeeClone.entity.RateEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RateConverter {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductConverter productConverter;
    public RateEntity toEntity(CreateRateDTO createRateDTO){
        ProductEntity productEntity = productRepository.findById(createRateDTO.getProductId())
                .orElseThrow(()-> new ValidateException("Product not found!"));
        RateEntity rateEntity = new RateEntity();
        rateEntity.setEntity(productEntity);
        rateEntity.setName(createRateDTO.getName());
        rateEntity.setTelephone(createRateDTO.getTelephone());
        rateEntity.setStar(createRateDTO.getStar());
        rateEntity.setFeedBack(createRateDTO.getFeedBack());
        return rateEntity;
    }
    public RateDTO toDto(RateEntity rateEntity){
        RateDTO rateDTO = new RateDTO();
        rateDTO.setId(rateEntity.getId());
        rateDTO.setName(rateEntity.getName());
        rateDTO.setTelephone(rateEntity.getTelephone());
        rateDTO.setStar(rateEntity.getStar());
        rateDTO.setFeedBack(rateEntity.getFeedBack());
        rateDTO.setCreateDate(rateEntity.getCreateDate());
        rateDTO.setCreateBy(rateEntity.getCreateBy());
        rateDTO.setModifierBy(rateEntity.getModifierBy());
        rateDTO.setModifierDate(rateEntity.getModifierDate());
        rateDTO.setProductDTO(productConverter.toDTO(rateEntity.getEntity()));
        return rateDTO;
    }
    public List<RateDTO> rateDTOList(List<RateEntity> rateEntities){
        List<RateDTO> dtos = new ArrayList<>();
        for(RateEntity rateEntity : rateEntities){
            dtos.add(toDto(rateEntity));
        }
        return dtos;
    }
}
