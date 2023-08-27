package com.shopeeClone.shopeeClone.dto.order;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CreateOrderForm {
	private List<Long> cartIds;
	private Long userId;
	private Long addressId;
	private String description;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date createDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private String createBy;
	private List<Long> productIds;
	private List<Integer> quantities;
	
	public List<Long> getCartIds() {
		return cartIds;
	}
	public void setCartIds(List<Long> cartIds) {
		this.cartIds = cartIds;
	}
	public List<Integer> getQuantities() {
		return quantities;
	}
	public void setQuantities(List<Integer> quantities) {
		this.quantities = quantities;
	}
	public List<Long> getProductIds() {
		return productIds;
	}
	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
