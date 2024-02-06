package com.shopeeClone.shopeeClone.dto.rate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRateDTO {
    private Long id;
    private String name;
    private String telephone;
    private Integer star;
    private String feedBack;
    private Long productId;
}
