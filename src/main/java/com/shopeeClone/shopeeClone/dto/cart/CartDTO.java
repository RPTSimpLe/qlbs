package com.shopeeClone.shopeeClone.dto.cart;

import java.util.Date;
import java.util.List;

import com.shopeeClone.shopeeClone.entity.ProductEntity;

public class CartDTO {
	private Long cartId;
	private Integer quantity;
	private ProductEntity product;
	private Date createDate;
	private Date modifiDate;
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifiDate() {
		return modifiDate;
	}
	public void setModifiDate(Date date) {
		this.modifiDate = date;
	}
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
	
	public ProductEntity getProduct() {
		return product;
	}
	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	
	
}
