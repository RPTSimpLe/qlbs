package com.shopeeClone.shopeeClone.dto;

import com.shopeeClone.shopeeClone.entity.ProductEntity;

public class CartDTO {
	private Long cartId;
	private Integer quantity;
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
