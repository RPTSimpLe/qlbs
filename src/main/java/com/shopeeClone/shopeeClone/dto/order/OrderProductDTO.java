package com.shopeeClone.shopeeClone.dto.order;

import com.shopeeClone.shopeeClone.dto.product.ProductDTO;

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
