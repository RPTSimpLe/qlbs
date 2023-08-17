package com.shopeeClone.shopeeClone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopeeClone.shopeeClone.converter.cart.CartConverter;
import com.shopeeClone.shopeeClone.dto.CartDTO;
import com.shopeeClone.shopeeClone.entity.CartEntity;
import com.shopeeClone.shopeeClone.repository.CartRepository;
import com.shopeeClone.shopeeClone.service.CartService;


@Service
@Transactional
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartConverter cartConverter;
	
	@Override
	public CartDTO create(CartDTO dto) {
		CartEntity cartEntity = new CartEntity();
		cartConverter.toEntity(dto);
		cartRepository.save(cartEntity);
		return cartConverter.toDTO(cartEntity);
	}
	
}
