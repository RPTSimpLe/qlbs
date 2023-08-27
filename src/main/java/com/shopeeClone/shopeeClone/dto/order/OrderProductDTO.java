package com.shopeeClone.shopeeClone.dto.order;

import com.shopeeClone.shopeeClone.dto.ProductDTO;
import com.shopeeClone.shopeeClone.entity.OrderEntity;
import com.shopeeClone.shopeeClone.entity.ProductEntity;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class OrderProductDTO {
	
	private Long orderProductId;
	private Integer quantity;
	private ProductDTO dto;
	private String address;

	public Long getOrderProductId() {
		return orderProductId;
	}
	public void setOrderProductId(Long orderProductId) {
		this.orderProductId = orderProductId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public ProductDTO getDto() {
		return dto;
	}
	public void setDto(ProductDTO dto) {
		this.dto = dto;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
