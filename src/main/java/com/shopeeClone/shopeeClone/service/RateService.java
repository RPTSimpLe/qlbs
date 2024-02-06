package com.shopeeClone.shopeeClone.service;

import com.shopeeClone.shopeeClone.dto.rate.CreateRateDTO;
import com.shopeeClone.shopeeClone.dto.rate.RateDTO;

import java.util.List;

public interface RateService {
    RateDTO createRate(CreateRateDTO rateDTO);
    List<RateDTO> getFeedBack(Long productId);
    void deleteFeedback(Long id);
    List<RateDTO> getAll();
}
