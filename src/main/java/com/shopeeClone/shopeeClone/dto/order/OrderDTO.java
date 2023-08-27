package com.shopeeClone.shopeeClone.dto.order;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopeeClone.shopeeClone.dto.ProductDTO;
import com.shopeeClone.shopeeClone.dto.address.AddressDTO;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class OrderDTO {
	
	private String name;
	private String address;
	private String description;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date createDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private String createBy;
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
