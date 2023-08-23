package com.shopeeClone.shopeeClone.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopeeClone.shopeeClone.converter.cart.CartConverter;
import com.shopeeClone.shopeeClone.dto.cart.CartDTO;
import com.shopeeClone.shopeeClone.dto.cart.CartRequestForm;
import com.shopeeClone.shopeeClone.dto.cart.UpdateCart;
import com.shopeeClone.shopeeClone.entity.CartEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
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
	public CartDTO create(CartRequestForm requestForm) {
		CartEntity cartEntity = cartConverter.toEntity(requestForm);
		cartRepository.save(cartEntity);
		return cartConverter.toDTO(cartEntity);
	}

	@Override
	public List<CartDTO> getAll() {
		List<CartEntity>entities = cartRepository.findAll();
		return cartConverter.toDTO(entities);
	}

	@Override
	public void delete(Long cartId) {
		CartEntity cartEntity = cartRepository.findById(cartId).orElseThrow(() -> new ValidateException("không tìm thấy cart"));
		cartEntity.setUser(null);
		cartEntity.setProduct(null);
		cartRepository.save(cartEntity);
		cartRepository.delete(cartEntity);
	}

	@Override
	public void multiDelete(List<Long> cartIds) {
		for (Long long1 : cartIds) {
			delete(long1);
		}
		
	}

	@Override
	public CartDTO update(Long id, UpdateCart cart) {
		CartEntity cartEntity = cartRepository.findById(id).orElseThrow(() -> new ValidateException("không tìm thấy cart"));
		cartEntity.setQuantity(cart.getQuantity());
		cartRepository.save(cartEntity);
		return cartConverter.toDTO(cartEntity);
	}
	
}
