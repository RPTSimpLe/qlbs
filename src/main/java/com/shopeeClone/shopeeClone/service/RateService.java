package com.shopeeClone.shopeeClone.service;

import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.rate.CreateRateDTO;
import com.shopeeClone.shopeeClone.dto.rate.RateDTO;

import java.util.List;
import java.util.Map;

public interface RateService {
    RateDTO createRate(CreateRateDTO rateDTO);
    List<RateDTO> getFeedBack(Long productId);
    void deleteFeedback(Long id);
    PageDTO<RateDTO>getRate(Map<String,String> params);
    PageDTO<RateDTO>getRatesByProductId(Map<String,String> params, Long productId);
}
