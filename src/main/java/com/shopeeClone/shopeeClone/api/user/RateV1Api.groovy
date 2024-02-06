package com.shopeeClone.shopeeClone.api.user

import com.shopeeClone.shopeeClone.dto.rate.CreateRateDTO
import com.shopeeClone.shopeeClone.dto.rate.RateDTO
import com.shopeeClone.shopeeClone.service.RateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
    @GetMapping("/getAll")
    public List<RateDTO> getAll(){
        return rateService.getAll();
    }
}
