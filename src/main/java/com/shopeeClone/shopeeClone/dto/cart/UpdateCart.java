package com.shopeeClone.shopeeClone.dto.cart;

public class UpdateCart {
	private Integer quantity;

	public UpdateCart(Integer quantity) {
		this.quantity = quantity;
	}

	public UpdateCart() {
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
