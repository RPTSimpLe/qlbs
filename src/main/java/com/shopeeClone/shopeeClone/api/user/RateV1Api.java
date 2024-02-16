package com.shopeeClone.shopeeClone.api.user;

import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.rate.CreateRateDTO;
import com.shopeeClone.shopeeClone.dto.rate.RateDTO;
import com.shopeeClone.shopeeClone.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/rates")
class RateV1Api {

    @Autowired
    private RateService rateService;

    @PostMapping("/create")
    public RateDTO create(@RequestBody CreateRateDTO dto){
        return rateService.createRate(dto);
    }
    @GetMapping("/{productId}")
    public List<RateDTO> create(@PathVariable Long productId){
        return rateService.getFeedBack(productId);
    }
    @DeleteMapping("admin/delete/{id}")
    public void delete(@PathVariable Long id){
        rateService.deleteFeedback(id);
    }
    @GetMapping()
    public PageDTO<RateDTO> getAll(@RequestParam Map<String,String> params){
        return rateService.getRate(params);
    }
    @GetMapping("/getRatesByProductId/{productId}")
    public PageDTO<RateDTO> getRatesByProductId(@RequestParam Map<String,String> params,@PathVariable("productId") Long productId){
        return rateService.getRatesByProductId(params,productId);
    }
}
