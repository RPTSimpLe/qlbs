package com.shopeeClone.shopeeClone.dto.cart;

import java.util.Date;

import com.shopeeClone.shopeeClone.dto.ProductDTO;
import com.shopeeClone.shopeeClone.entity.ProductEntity;

public class CartDTO {
	private Long cartId;
	private Integer quantity;
	private ProductDTO product;
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
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	
}
