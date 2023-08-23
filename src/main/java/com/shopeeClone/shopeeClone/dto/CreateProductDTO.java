package com.shopeeClone.shopeeClone.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CreateProductDTO {
    private Long productId;
    private String name;
    private Double importPrice;
    private Double price;
    private Integer discountPercent;
    private String description;
    private String category;  // Sử dụng String cho category
    private String suppilier;
    private List<MultipartFile> multipartFiles = new ArrayList<>();
    public void addFiles(List<MultipartFile> multipartFiles){
        for(MultipartFile multipartFile : multipartFiles){
            this.multipartFiles.add(multipartFile);
        }
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date createDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date modifierDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private String createBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private String modifierBy;
}
