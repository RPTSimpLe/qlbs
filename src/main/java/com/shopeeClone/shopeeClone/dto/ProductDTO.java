package com.shopeeClone.shopeeClone.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import lombok.Data;

@Data
public class ProductDTO {
    private Long productId;
    private String name;
    private Double importPrice;
    private Double price;
    private Integer discountPercent;
    private String description;
    private String category;  // Sử dụng String cho category
    private String suppilier;
	private Date createDate;
	private Date modifierDate;
	private String createBy;
	private String modifierBy;
    private List<ImageDTO> imageDTOs = new ArrayList<>();
}
