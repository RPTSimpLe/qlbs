package com.shopeeClone.shopeeClone.service.impl;

import com.shopeeClone.shopeeClone.converter.RateConverter;
import com.shopeeClone.shopeeClone.dto.rate.CreateRateDTO;
import com.shopeeClone.shopeeClone.dto.rate.RateDTO;
import com.shopeeClone.shopeeClone.entity.ProductEntity;
import com.shopeeClone.shopeeClone.entity.RateEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.ProductRepository;
import com.shopeeClone.shopeeClone.repository.RateRepository;
import com.shopeeClone.shopeeClone.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RateServiceImpl implements RateService {
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private RateConverter rateConverter;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public RateDTO createRate(CreateRateDTO createRateDTO) {
        RateEntity rateEntity = rateConverter.toEntity(createRateDTO);
        rateRepository.save(rateEntity);
        return rateConverter.toDto(rateEntity);
    }

    @Override
    public List<RateDTO> getFeedBack(Long productId) {
        List<RateEntity> rateEntities = rateRepository.findByProductId(productId);
        return rateConverter.rateDTOList(rateEntities);
    }

    @Override
    public void deleteFeedback(Long id) {
        RateEntity rateEntity = rateRepository.findById(id).orElseThrow(()-> new ValidateException("FeedBack not found"));
        rateRepository.delete(rateEntity);
    }

    @Override
    public List<RateDTO> getAll() {
        List<RateEntity> rateEntities = rateRepository.findAll();
        return rateConverter.rateDTOList(rateEntities);
    }
}
