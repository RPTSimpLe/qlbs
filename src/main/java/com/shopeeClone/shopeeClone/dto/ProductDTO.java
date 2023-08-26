package com.shopeeClone.shopeeClone.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
   	private Date createDate;
   	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
   	private Date modifierDate;
   	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
   	private String createBy;
   	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
   	private String modifierBy;
    private List<ImageDTO> imageDTOs = new ArrayList<>();
    public void addImageDTO(ImageDTO imageDTO){
        this.imageDTOs.add(imageDTO);
    }
}
