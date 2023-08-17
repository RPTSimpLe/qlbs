package com.shopeeClone.shopeeClone.converter.cart;

import org.springframework.stereotype.Component;

import com.shopeeClone.shopeeClone.dto.CartDTO;
import com.shopeeClone.shopeeClone.entity.CartEntity;

@Component
public class CartConverter {
	public CartDTO toDTO(CartEntity cartEntity) {
		CartDTO cartDTO = new CartDTO();
		cartDTO.setCartId(cartEntity.getCartId());
		cartDTO.setQuantity(cartEntity.getQuantity());
		return cartDTO;
	}
	
	public CartEntity toEntity(CartDTO cartDTO) {
		CartEntity cartEntity = new CartEntity();
		cartEntity.setQuantity(cartDTO.getQuantity());
		return cartEntity;
	}
}
