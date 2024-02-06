package com.shopeeClone.shopeeClone.dto.rate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopeeClone.shopeeClone.dto.product.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RateDTO {
    private Long id;
    private String name;
    private String telephone;
    private Integer star;
    private String feedBack;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date modifierDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private String createBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private String modifierBy;
}
